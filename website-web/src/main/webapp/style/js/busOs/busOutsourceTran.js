var jessrun = typeof $ === "function" ? window.$ : {};

jessrun.loadCSS(jessrun.config.domain+"/style/css/dialog.css","dialog");
jessrun.loadJS(jessrun.config.domain+"/style/js/dialog-min.js",function(){
	//
},function(){
	alert("弹出层载入失败");
});


$(function() {
	//jessrun.loadToSateToStaff("ddl_stateStart","ddl_stateEnd","ddl_loadStaffId");
	//站点取站点 站点取员工

	//$("#ddl_stateStart").change(function(){
	//	jessrun.loadToState("ddl_stateStart","ddl_stateEnd");
	//	jessrun.loadToStaff("ddl_stateStart","ddl_loadStaffId");
	//	$("#hid_stateStartId").val($(this).find("option:selected").val());
	//});
	//获取收货站点赋值
	$("#ddl_stateEnd").change(function(){
		$("#hid_stateEndId").val($(this).find("option:selected").val());
	});

	//表格高亮
	$(".mod_table_bd").tableUI();
	//时间控件
	
	getPayList(1);
	getGetList(1);
	getMessageGetList(1);
	
	getSum();
	//选择货物弹出层
	jessrun.chooseCarry = function (){
		var url = "";
		var urlTitle = "";
		if($("#hid_outsourceTypeId").val()=="3")
		{
			urlTitle="选择外包承运单";
			url=jessrun.config.domain+"bus/outsourceCarry/gotoList.action?carryStateStartId="+$('#ddl_stateStart').val()+"&&outsourceId="+$('#hid_outsourceId').val();
		}
		else
		{
			urlTitle="选择中转承运单";
			url=jessrun.config.domain+"bus/outsourceCarry/gotoTraList.action?carryStateEndId="+$('#ddl_stateStart').val()+"&&outsourceId="+$('#hid_outsourceId').val();
		}
		jessrun.Dialog({
			title:urlTitle,
			width:900,
			height:420,
			showbg:true,
			drag:false,
			content:"iframe:"+url
		});
	}

	//关闭弹出层
	jessrun.closeIframeDialog = function (){
		parent.jessrun.Dialog.removeBox();
	};

	$("[id^='txt_cost_']").change(function(){
		checkMoney($(this));
		var sumAll = Number($("#txt_cost_oursource").val())
					+Number($("#txt_cost_other").val());
		sumAll = Number($("#txt_cost_oursource").val())
				+Number($("#txt_cost_other").val());
		//计算合计
		$("#txt_costOursource_all").val(sumAll);
		//根据付款方式分配金额
		getPayList();
	});
	
	$("#txt_costMessage").change(function(){
		checkMoney($("#txt_costMessage"));
		getMessageGetList();
	});

	//更改付款方式事件
	$("#ddl_outsourcePayTypeId").change(function(){
		//根据付款方式分配金额
		getPayList();
	});
	
	//更改收款方式事件
	$("#ddl_afterGetTypeId").change(function(){
		//根据收款方式分配金额
		getGetList();
	});
	
	//信息费收款方式事件
	$("#ddl_messageGetTypeId").change(function(){
		getMessageGetList();
	});

	//完结时的选择事件
	$("[id^='chk_']").change(function(){
		changeOutsourceIdName();
	});

	$("#chk_all").change(function(){
		$("[id^='chk_']").attr("checked",$("#chk_all").attr("checked"));
		changeOutsourceIdName();
	});
	
	//外包费用现付更改
	$("#txt_costOutsource_Cur").change(function(){
		var sum = Number($("#txt_costOursource_all").val());
		checkMoney($("#txt_costOutsource_Cur"));
		if(Number($("#txt_costOutsource_Cur").val()) >= sum)
		{
			$("#txt_costOutsource_Cur").val(sum);
			$("#txt_costOutsource_Return").val("0");
			$("#txt_costOutsource_Month").val("0");
		}
		else
		{
			$("#txt_costOutsource_Return").val(sum-Number($("#txt_costOutsource_Cur").val()));
			$("#txt_costOutsource_Month").val("0");
			checkMoney($("#txt_costOutsource_Return"));
		}
	});
	
	//外包费用月结更改
	$("#txt_costOutsource_Return").change(function(){
		var sum = Number($("#txt_costOursource_all").val())
				- Number($("#txt_costOutsource_Cur").val());
		checkMoney($("#txt_costOutsource_Return"));
		if(Number($("#txt_costOutsource_Return").val()) >= sum)
		{
			$("#txt_costOutsource_Return").val(sum);
			$("#txt_costOutsource_Month").val("0");
		}
		else
		{
			$("#txt_costOutsource_Month").val(sum-Number($("#txt_costOutsource_Return").val()));
			checkMoney($("#txt_costOutsource_Month"));
		}
	});
	
	//外包费用月结更改
	$("#txt_costOutsource_Month").change(function(){
		var sum = Number($("#txt_costOursource_all").val())
				- Number($("#txt_costOutsource_Cur").val())
				- Number($("#txt_costOutsource_Return").val());
		$("#txt_costOutsource_Month").val(sum);
		checkMoney($("#txt_costOutsource_Month"));
	});
	
	//外包提收现收更改
	$("#txt_costAgentCur").change(function(){
		var sum = Number($("#span_sumAfter").text());
		checkMoney($("#txt_costAgentCur"));
		if(Number($("#txt_costAgentCur").val()) >= sum)
		{
			$("#txt_costAgentCur").val(sum);
			$("#txt_costAgentReturn").val("0");
			$("#txt_costAgentMonth").val("0");
		}
		else
		{
			$("#txt_costAgentReturn").val(sum-Number($("#txt_costAgentCur").val()));
			$("#txt_costAgentMonth").val("0");
			checkMoney($("#txt_costAgentReturn"));
		}
	});
	
	//外包提收回收更改
	$("#txt_costAgentReturn").change(function(){
		var sum = Number($("#span_sumAfter").text())
				- Number($("#txt_costAgentCur").val());
		checkMoney($("#txt_costAgentReturn"));
		if(Number($("#txt_costAgentReturn").val()) >= sum)
		{
			$("#txt_costAgentReturn").val(sum);
			$("#txt_costAgentMonth").val("0");
		}
		else
		{
			$("#txt_costAgentMonth").val(sum-Number($("#txt_costAgentReturn").val()));
			checkMoney($("#txt_costAgentMonth"));
		}
	});
	
	//外包提收月结更改
	$("#txt_costAgentMonth").change(function(){
		var sum = Number($("#span_sumAfter").text())
				- Number($("#txt_costAgentCur").val())
				- Number($("#txt_costAgentReturn").val());
		$("#txt_costAgentMonth").val(sum);
		checkMoney($("#txt_costAgentMonth"));
	});
	
	//信息费现收更改
	$("#txt_costMessageCur").change(function(){
		var sum = Number($("#txt_costMessage").val());
		checkMoney($("#txt_costMessageCur"));
		if(Number($("#txt_costMessageCur").val()) >= sum)
		{
			$("#txt_costMessageCur").val(sum);
			$("#txt_costMessageReturn").val("0");
			$("#txt_costMessageMonth").val("0");
		}
		else
		{
			$("#txt_costMessageReturn").val(sum-Number($("#txt_costMessageCur").val()));
			$("#txt_costMessageMonth").val("0");
			checkMoney($("#txt_costMessageReturn"));
		}
	});
	
	//信息费回收更改
	$("#txt_costMessageReturn").change(function(){
		var sum = Number($("#txt_costMessage").val())
				- Number($("#txt_costMessageCur").val());
		checkMoney($("#txt_costMessageReturn"));
		if(Number($("#txt_costMessageReturn").val()) >= sum)
		{
			$("#txt_costMessageReturn").val(sum);
			$("#txt_costMessageMonth").val("0");
		}
		else
		{
			$("#txt_costMessageMonth").val(sum - Number($("#txt_costMessageReturn").val()));
			checkMoney($("#txt_costMessageMonth"));
		}
	});
	
	//外包提收月结更改
	$("#txt_costMessageMonth").change(function(){
		var sum = Number($("#txt_costMessage").val())
				- Number($("#txt_costMessageCur").val())
				- Number($("#txt_costMessageReturn").val());
		$("#txt_costMessageMonth").val(sum);
		checkMoney($("#txt_costMessageMonth"));
	});
});

//判断站点是否可以修改
function stateReadonly()
{
	var orgaType = $("#ddl_stateStart").attr("orgaType");
	if(Number(orgaType)<3)
		$("#ddl_stateStart").removeAttr("disabled");
	//如果主体页面上有货物信息，则不能修改
	var lineNo = 0;

	$("[id^='tr_']").each(function(){
		var carryId = $(this).attr("carryId");
		$("#ddl_stateStart").attr("disabled",true);
		//更新提交控件名称
		$("#hid_carryId_"+carryId).attr("name","busCarryGoing["+lineNo+"].carryId");
		//主体页面序号
		lineNo=Number(lineNo)+1;
		$("#span_no_"+carryId).html(lineNo);

	});
}

//更新装车单提交操作中的LoadId的Name
function changeOutsourceIdName(){
	var lineNo = 0;
	var selectedNo = 0;
	$("[id^='chk_']").each(function(){
		var outsourceId=$(this).attr("outsourceId");
		$("#hid_outsourceId_"+outsourceId).removeAttr("name");
		if($(this).attr("checked"))
		{
			$("#hid_outsourceId_"+loadId).attr("name","outsourceList["+selectedNo+"].outsourceId");
			selectedNo=Number(selectedNo)+1;
		}
		//主体页面序号
		lineNo=Number(lineNo)+1;
		$("#span_no_"+carryId).html(lineNo);
	});
}

//主体页面删除方法
function removeTr(carryId)
{
	if($("#chk_top_"+carryId).attr("checked")==true){
		$("#ddl_stateStart").attr("readonly",true);
	}else{
		$("#ddl_stateStart").removeAttr("readonly");
	}
	var target_obj = $("#tr_"+carryId);
	//如果主页面元素存在则删除
	if (target_obj.length > 0)
		$("#tr_"+carryId).remove();

	//计算合计
	getSum();
	//判断主体页面的站点是否可以修改
	stateReadonly();
	//根据收款方式分配金额
	getGetList();
}

//主体页面删除方法
function remove_top_Tr(carryId)
{

}

//主体页面删除方法
function remove_Tr(carryId)
{
	removeTr(carryId);
}

function getSum()
{
	var transinsurSum = 0;
	var transinsurAfterSum = 0;
	var sendcargoSum = 0;
	var sendcargoAfterSum = 0;
	var agentAllSum = 0;
	var agentAfterSum = 0;
	var transSum = 0;
	//计算合计
	$("[id^='span_transinser_']").each(function(){
		transinsurSum+=Number($(this).text());
	});
	$("[id^='span_transinserAfter_']").each(function(){
		transinsurAfterSum+=Number($(this).text());
	});
	$("[id^='span_sendcargo_']").each(function(){
		sendcargoSum+=Number($(this).text());
	});
	$("[id^='span_sendcargoAfter_']").each(function(){
		sendcargoAfterSum+=Number($(this).text());
	});
	$("[id^='span_agentAll_']").each(function(){
		agentAllSum+=Number($(this).text());
	});
	$("[id^='span_agentAfter_']").each(function(){
		agentAfterSum+=Number($(this).text());
	});
	$("[id^='span_trans_']").each(function(){
		transSum+=Number($(this).text());
	});
	//合计赋值
	$("#span_sumTransinser").text(transinsurSum);
	$("#span_sumTransinserAfter").text(transinsurAfterSum);
	$("#span_sumSendcargo").text(sendcargoSum);
	$("#span_sumSendcargoAfter").text(sendcargoAfterSum);
	$("#span_sumAgentAll").text(agentAllSum);
	$("#span_sumAgentAfter").text(agentAfterSum);
	$("#span_sumAfter").text(transinsurAfterSum+sendcargoAfterSum+agentAfterSum);
	$("#span_sumTrans").text(transSum);
	$("#txt_cost_oursource").val(transSum);
	//根据提收方式分配金额
	var sumAll = Number($("#txt_cost_oursource").val())
		+Number($("#txt_cost_other").val());
	sumAll = Number($("#txt_cost_oursource").val())
		   + Number($("#txt_cost_other").val());
	//计算合计
	$("#txt_costOursource_all").val(sumAll);
	getPayList();
	getGetList();
}

//根据付款方式分配金额
function getPayList(edit)
{
	var typeId = $("#ddl_outsourcePayTypeId").val();
	var costOutsource = $("#txt_costOursource_all").val();
	//如果是现付
	if(typeId=="1"){
		$("#txt_costOutsource_Cur").val(costOutsource);
		$("#txt_costOutsource_Return").val("0");
		$("#txt_costOutsource_Month").val("0");
		$("#txt_costOutsource_Cur").attr("disabled",true);
		$("#txt_costOutsource_Return").attr("disabled",true);
		$("#txt_costOutsource_Month").attr("disabled",true);
	}
	//如果是回付
	else if(typeId=="2"){
		$("#txt_costOutsource_Cur").val("0");
		$("#txt_costOutsource_Return").val(costOutsource);
		$("#txt_costOutsource_Month").val("0");
		$("#txt_costOutsource_Cur").attr("disabled",true);
		$("#txt_costOutsource_Return").attr("disabled",true);
		$("#txt_costOutsource_Month").attr("disabled",true);
	}
	//如果是月结
	else if(typeId=="3"){
		$("#txt_costOutsource_Cur").val("0");
		$("#txt_costOutsource_Return").val("0");
		$("#txt_costOutsource_Month").val(costOutsource);
		$("#txt_costOutsource_Cur").attr("disabled",true);
		$("#txt_costOutsource_Return").attr("disabled",true);
		$("#txt_costOutsource_Month").attr("disabled",true);
	}
	//如果是混付
	else if(typeId=="4"){
		if(edit==undefined)
		{
			$("#txt_costOutsource_Cur").val(costOutsource);
			$("#txt_costOutsource_Return").val("0");
			$("#txt_costOutsource_Month").val("0");
		}
		$("#txt_costOutsource_Cur").attr("disabled",false);
		$("#txt_costOutsource_Return").attr("disabled",false);
		$("#txt_costOutsource_Month").attr("disabled",false);
	}
}

//根据收款方式分配金额
function getGetList(edit)
{
	var typeId = $("#ddl_afterGetTypeId").val();
	var costAfter = $("#span_sumAfter").text();
	//如果是现收
	if(typeId=="1"){
		$("#txt_costAgentCur").val(costAfter);
		$("#txt_costAgentReturn").val("0");
		$("#txt_costAgentMonth").val("0");
		$("#txt_costAgentCur").attr("disabled",true);
		$("#txt_costAgentReturn").attr("disabled",true);
		$("#txt_costAgentMonth").attr("disabled",true);
	}
	//如果是回收
	else if(typeId=="2"){
		$("#txt_costAgentCur").val("0");
		$("#txt_costAgentReturn").val(costAfter);
		$("#txt_costAgentMonth").val("0");
		$("#txt_costAgentCur").attr("disabled",true);
		$("#txt_costAgentReturn").attr("disabled",true);
		$("#txt_costAgentMonth").attr("disabled",true);
	}
	//如果是月结
	else if(typeId=="3"){
		$("#txt_costAgentCur").val("0");
		$("#txt_costAgentReturn").val("0");
		$("#txt_costAgentMonth").val(costAfter);
		$("#txt_costAgentCur").attr("disabled",true);
		$("#txt_costAgentReturn").attr("disabled",true);
		$("#txt_costAgentMonth").attr("disabled",true);
	}
	//如果是混付
	else if(typeId=="4"){
		if(edit==undefined)
		{
			$("#txt_costAgentCur").val(costAfter);
			$("#txt_costAgentReturn").val("0");
			$("#txt_costAgentMonth").val("0");
		}
		$("#txt_costAgentCur").attr("disabled",false);
		$("#txt_costAgentReturn").attr("disabled",false);
		$("#txt_costAgentMonth").attr("disabled",false);
	}
}

//根据信息费收款方式分配金额
function getMessageGetList(edit)
{
	var typeId = $("#ddl_messageGetTypeId").val();
	var costAfter = $("#txt_costMessage").val();
	//如果是现收
	if(typeId=="1"){
		$("#txt_costMessageCur").val(costAfter);
		$("#txt_costMessageReturn").val("0");
		$("#txt_costMessageMonth").val("0");
		$("#txt_costMessageCur").attr("disabled",true);
		$("#txt_costMessageReturn").attr("disabled",true);
		$("#txt_costMessageMonth").attr("disabled",true);
	}
	//如果是回收
	else if(typeId=="2"){
		$("#txt_costMessageCur").val("0");
		$("#txt_costMessageReturn").val(costAfter);
		$("#txt_costMessageMonth").val("0");
		$("#txt_costMessageCur").attr("disabled",true);
		$("#txt_costMessageReturn").attr("disabled",true);
		$("#txt_costMessageMonth").attr("disabled",true);
	}
	//如果是月结
	else if(typeId=="3"){
		$("#txt_costMessageCur").val("0");
		$("#txt_costMessageReturn").val("0");
		$("#txt_costMessageMonth").val(costAfter);
		$("#txt_costMessageCur").attr("disabled",true);
		$("#txt_costMessageReturn").attr("disabled",true);
		$("#txt_costMessageMonth").attr("disabled",true);
	}
	//如果是混付
	else if(typeId=="4"){
		if(edit==undefined)
		{
			$("#txt_costMessageCur").val(costAfter);
			$("#txt_costMessageReturn").val("0");
			$("#txt_costMessageMonth").val("0");
		}
		$("#txt_costMessageCur").attr("disabled",false);
		$("#txt_costMessageReturn").attr("disabled",false);
		$("#txt_costMessageMonth").attr("disabled",false);
	}
}

//载入验证所需方法
jessrun.loadJS(jessrun.config.domain+"/style/js/init-form.js",function(){
},function(){
	alert(jessrun.url.jsError)
});

//配置提交表单Id
jessrun.formId={
	id:"form_loadAdd"
};
$(function(){
	//公司资料修改


	jessrun.formOutsourceAdd('outsource_CarryAdd','form_outsourceAdd','loadFormSubmit','数据加载中');
});
/**-----------------个体商户-----------------------****/
//修改个人商户资料提交验证
$(function(){
	$("#"+jessrun.formId.id).validate({
		errorPlacement:function(error,element){
			error.appendTo(element.parent().parent());
		},
		//errorLabelContainer: "#errorSearch",		//显示错误信息的容器ID
		//wrapper: "span",
		onsubmit: false,
		success:"form-success",
		rules:{
			loadRemark:{
				isRmark:true
			}
		},
		messages:{
			loadRemark:{

			}
		}
	});
});

jessrun.formOutsourceAdd=function(btnId,formId,loadClass,loadHtml){
	$("#"+btnId).click(function(){
		if ($("#"+formId).valid()) {
			if($("tr[id^='tr_']").length == 0){
				alert("请选择承运单！");
				return;
			}
			$("#"+btnId).hide();
			$("<span class='"+loadClass+"'>"+loadHtml+"</span>").insertAfter("#"+btnId);
			function doSubmit(){
				//判断装车费用填定是否正确
				var costOutsource = $("#txt_costOursource_all").val();
				var costOutsourceCur = $("#txt_costOutsource_Cur").val();
				var costOutsourceReturn = $("#txt_costOutsource_Return").val();
				var costOutsourceMonth = $("#txt_costOutsource_Month").val();

				//提收总额
				var costAfter = Number($("#span_sumTransinser").text())
					+Number($("#span_sumSendcargo").text())
					+Number($("#span_sumLoadcargo").text())
					+Number($("#span_sumAgent").text());
				var costAfterCur = $("#txt_after_cur").val(costAfter);
				var costAfterReturn = $("#txt_after_return").val();
				var costAfterMonth = $("#txt_after_month").val();
				var canSubmit = (Number(costOutsource)==Number(costOutsourceCur)+Number(costOutsourceReturn)+Number(costOutsourceMonth)
						&& Number(costAfter)==Number(costAfterCur)+Number(costAfterReturn)+Number(costAfterMonth));

				//向隐藏控件传值
				$("#hid_stateStartId").val($("#ddl_stateStartId").val());
				$("#hid_stateStartName").val($("#ddl_stateStartId").find("option:selected").text());
				$("#hid_outsourceStateId").val($("#ddl_stateStart").val());
				$("#hid_outsourceStateName").val($("#ddl_stateStart").find("option:selected").text());
				$("#hid_outsourcePayTypeName").val($("#ddl_outsourcePayTypeId").find("option:selected").text());
				$("#hid_outsourceStaffName").val($("#ddl_outsourceStaffId").find("option:selected").text());
				$("#hid_costOutsource").val($("#txt_costOutsource").val());
				$("#hid_costOutsourceCur").val($("#txt_costOutsource_Cur").val());
				$("#hid_costOutsourceReturn").val($("#txt_costOutsource_Return").val());
				$("#hid_costOutsourceMonth").val($("#txt_costOutsource_Month").val());
				$("#hid_afterGetTypeName").val($("#ddl_afterGetTypeId").find("option:selected").text());
				
				$("#hid_costGetTransinsurAfter").val($("#span_sumTransinserAfter").text());
				$("#hid_costGetSendcargoAfter").val($("#span_sumSendcargoAfter").text());
				$("#hid_costGetLoadcargoAfter").val("0");
				$("#hid_costGetAgentAfter").val($("#span_sumAgentAfter").text());
				
				$("#hid_costAfterAll").val($("#span_sumAfter").text());
				$("#hid_costAgentCur").val($("#txt_costAgentCur").val());
				$("#hid_costAgentReturn").val($("#txt_costAgentReturn").val());
				$("#hid_costAgentMonth").val($("#txt_costAgentMonth").val());
				
				$("#hid_messageGetTypeName").val($("#ddl_messageGetTypeId").find("option:selected").text());
				$("#hid_costMessageCur").val($("#txt_costMessageCur").val());
				$("#hid_costMessageReturn").val($("#txt_costMessageReturn").val());
				$("#hid_costMessageMonth").val($("#txt_costMessageMonth").val());
				$("#"+formId).submit();
				}
			setTimeout(doSubmit,0);
		}
	});
};