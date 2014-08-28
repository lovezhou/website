var jessrun = typeof $ === "function" ? window.$ : {};

jessrun.loadCSS(jessrun.config.domain+"/style/css/dialog.css","dialog");
jessrun.loadJS(jessrun.config.domain+"/style/js/dialog-min.js",function(){
	//
},function(){
	alert("弹出层载入失败");
});
var jessrun = typeof $ === "function" ? window.$ : {};

$(function() {
	//获取收货站点赋值
	$("#ddl_stateEnd").change(function(){
		$("#hid_stateEndId").val($(this).find("option:selected").val());
	});
	//表格高亮
	$(".mod_table_bd").tableUI();
	//选择货物弹出层
	jessrun.chooseCargo = function (){
		var stateStartId = $('#ddl_stateStart').val();
		if(stateStartId == null){
			stateStartId = "";
		}
		var stateEndId = $('#ddl_stateEnd').val();
		if(stateEndId == null){
			stateEndId = "";
		}
		var loadId = $('#hid_loadId').val();
		if(loadId == null){
			loadId = "";
		}
		if(stateStartId==""||stateEndId=="")
		{
			alert("请选择启运站点和到达站点！");
			return;
		}
		var url=jessrun.config.domain+"bus/depotCargo/gotoList.action?carryStateStartId="+stateStartId+"&carryStateEndId="+stateEndId
							+"&loadId="+loadId + "&isFromLoad=1&fromDialog=1";
		//alert(url);
		jessrun.Dialog({
			title:"选择货物",
			width:900,
			height:420,
			showbg:true,
			drag:false,
			content:"iframe:"+url
		});
	};

	//关闭弹出层
	jessrun.closeIframeDialog = function (){
		parent.jessrun.Dialog.removeBox();
	};

	//主页面数量改变事件
	jessrun.chect_Number = function(cargoId){
		var depotCargoNumber=$("#txt_loadCargoNumber_"+cargoId).attr("depotCargoNumber");
		//验证整数
		checkIntDefault($("#txt_loadCargoNumber_"+cargoId), depotCargoNumber);
		//验证货物数量不为0
		if(Number($("#txt_loadCargoNumber_"+cargoId).val()) == 0)
			$("#txt_loadCargoNumber_"+cargoId).val(depotCargoNumber);
		//如果和相关字段的加和大于最大值
		if(Number(depotCargoNumber)<Number($("#txt_loadCargoNumber_"+cargoId).val()))
			$("#txt_loadCargoNumber_"+cargoId).val(depotCargoNumber);
		//计算合计
		getSum();
	};

	$("[id^='txt_costLoad_']").change(function(){
		//验证金额
		checkMoney($(this));
	});

	$("[id^='txt_cost_']").change(function(){
		//验证金额
		checkMoney($(this));
		if(Number($("#txt_cost_Trans").val())
			-Number($("#txt_cost_Withhold").val())<0)
			{
				alert("扣款金额大于付款金额！");
				$("#txt_cost_Withhold").val("0");
				$(this).focus();
			}
		//计算合计
		$("#txt_costLoad").val(
				Number($("#txt_cost_Trans").val())-
				Number($("#txt_cost_Withhold").val()));
		//根据付款方式分配金额
		getPayList();
	});

	//更改付款方式事件
	$("#ddl_loadPayTypeId").change(function(){
		getPayList();
	});


	//发车、到货签到选择事件
	$("[id^='chk_loadId_']").change(function(){
		changeLoadIdName();
	});

	$("#chk_all").change(function(){
		$("[id^='chk_']").attr("checked",$("#chk_all").attr("checked"));
		changeLoadIdName();
	});
	
	//运输费用现付更改
	$("#txt_costLoad_Cur").change(function(){
		var sum = Number($("#txt_costLoad").val());
		checkMoney($("#txt_costLoad_Cur"));
		if(Number($("#txt_costLoad_Cur").val())>=sum)
		{
			$("#txt_costLoad_Cur").val(sum);
			$("#txt_costLoad_Return").val("0");
			$("#txt_costLoad_After").val("0");
			$("#txt_costLoad_Month").val("0");
		}
		else
		{
			$("#txt_costLoad_Return").val(sum-Number($("#txt_costLoad_Cur").val()));
			$("#txt_costLoad_After").val("0");
			$("#txt_costLoad_Month").val("0");
			checkMoney($("#txt_costLoad_Return"));
		}
	});
	
	//运输费用回付更改
	$("#txt_costLoad_Return").change(function(){
		var sum = Number($("#txt_costLoad").val())
				- Number($("#txt_costLoad_Cur").val());
		checkMoney($("#txt_costLoad_Return"));
		if(Number($("#txt_costLoad_Return").val())>=sum)
		{
			$("#txt_costLoad_Return").val(sum);
			$("#txt_costLoad_After").val("0");
			$("#txt_costLoad_Month").val("0");
		}
		else
		{
			$("#txt_costLoad_After").val(sum-Number($("#txt_costLoad_Return").val()));
			$("#txt_costLoad_Month").val("0");
			checkMoney($("#txt_costLoad_After"));
		}
	});
	
	//运输费用提付更改
	$("#txt_costLoad_After").change(function(){
		var sum = Number($("#txt_costLoad").val())
				- Number($("#txt_costLoad_Cur").val())
				- Number($("#txt_costLoad_Return").val());
		checkMoney($("#txt_costLoad_After"));
		if(Number($("#txt_costLoad_After").val())>=sum)
		{
			$("#txt_costLoad_After").val(sum);
			$("#txt_costLoad_Month").val("0");
		}
		else
		{
			$("#txt_costLoad_Month").val(sum-Number($("#txt_costLoad_After").val()));
			checkMoney($("#txt_costLoad_Month"));
		}
	});
	
	//运输费用月结更改
	$("#txt_costLoad_Month").change(function(){
		var sum = Number($("#txt_costLoad").val())
				- Number($("#txt_costLoad_Cur").val())
				- Number($("#txt_costLoad_Return").val())
				- Number($("#txt_costLoad_After").val());
		$("#txt_costLoad_Month").val(sum);
		checkMoney($("#txt_costLoad_Month"));
	});
	
	//装卸费更改
	$("#txt_costOther").change(function(){
		checkMoney($("#txt_costOther"));
	});
	
	//装卸费更改
	$("#txt_costLoadcargo").change(function(){
		checkMoney($("#txt_costLoadcargo"));
	});
	
	getSum();
});

//更新装车单提交操作中的LoadId的Name
function changeLoadIdName(){
	var lineNo = 0;
	var selectedNo = 0;
	$("[id^='chk_loadId_']").each(function(){
		var loadId=$(this).attr("loadId");
		//alert(loadId);
		$("#hid_loadId_"+loadId).removeAttr("name");
		if($(this).attr("checked"))
		{
			$("#hid_loadId_"+loadId).attr("name","loadList["+selectedNo+"].loadId");
			selectedNo=Number(selectedNo)+1;
		}
		//主体页面序号
		lineNo=Number(lineNo)+1;
		$("#span_no_"+loadId).text(lineNo);
	});
}

//判断站点是否可以修改
function stateReadonly()
{
	var orgaType = $("#ddl_stateStart").attr("orgaType");
	if(Number(orgaType)<3)
		$("#ddl_stateStart").removeAttr("disabled");
	$("#ddl_stateEnd").removeAttr("disabled");
	//如果主体页面上有货物信息，则不能修改
	var lineNo = 0;

	$("[id^='tr_']").each(function(){
		var cargoId = $(this).attr("cargoID");
		$("#ddl_stateStart").attr("disabled",true);
		$("#ddl_stateEnd").attr("disabled",true);
		//更新提交控件名称
		$("#txt_loadCargoNumber_"+cargoId).attr("name","busDepotCargo["+lineNo+"].loadCargoNumber");
		$("#hid_cargoId_"+cargoId).attr("name","busDepotCargo["+lineNo+"].carryCargoId");
		//主体页面序号
		lineNo=Number(lineNo)+1;
		$("#span_no_"+cargoId).html(lineNo);

	});

	$("#ddl_LogStaffId").change(function(){
		$("#hid_logStaffName").val($(this).find("option:selected").text());
	});
}

//主体页面删除方法
function removeTr(cargoId)
{
	if($("#chk_top_"+cargoId).attr("checked")==true){
		$("#ddl_stateStart").attr("readonly",true);

		$("#ddl_stateEnd").attr("readonly",true);
	}else{
		$("#ddl_stateStart").removeAttr("readonly");
		$("#ddl_stateEnd").removeAttr("readonly");
	}
	var target_obj = $("#tr_"+cargoId);
	//如果主页面元素存在则删除
	if (target_obj.length > 0)
		$("#tr_"+cargoId).remove();

	//计算合计
	getSum();
	//判断主体页面的站点是否可以修改
	stateReadonly();
}

//主体页面删除方法
function remove_top_Tr(cargoId)
{

}

//主体页面删除方法
function remove_Tr(cargoId)
{
	removeTr(cargoId);
}

function getSum()
{
	var volumeSum = 0;
	var weightSum = 0;
	var depotSum = 0;
	var loadSum = 0;
	//计算合计
	$("[id^='span_cargoVolume_']").each(function(){
		volumeSum+=Number($(this).text());
	});
	$("[id^='span_cargoWeight_']").each(function(){
		weightSum+=Number($(this).text());
	});
	$("[id^='span_depotCargo_']").each(function(){
		depotSum+=Number($(this).text());
	});

	$("[id^='txt_loadCargoNumber_']").each(function (){
		loadSum+=Number($(this).val());
	});
	//合计赋值
	$("#span_sumCargoVolume").text(volumeSum);
	$("#span_sumCargoWeight").text(weightSum);
	$("#span_sumDepotCargo").text(depotSum);
	$("#span_sumLoadCargo").text(loadSum);
}

//根据付款方式分配金额
function getPayList(edit)
{
	var typeId = $("#ddl_loadPayTypeId").val();
	var costLoad = $("#txt_costLoad").val();
	//如果是现付
	if(typeId=="1"){
		$("#txt_costLoad_Cur").val(costLoad);
		$("#txt_costLoad_After").val("0");
		$("#txt_costLoad_Return").val("0");
		$("#txt_costLoad_Month").val("0");
		$("#txt_costLoad_Cur").attr("disabled",true);
		$("#txt_costLoad_After").attr("disabled",true);
		$("#txt_costLoad_Return").attr("disabled",true);
		$("#txt_costLoad_Month").attr("disabled",true);
	}
	//如果是回付
	else if(typeId=="2"){
		$("#txt_costLoad_Cur").val("0");
		$("#txt_costLoad_After").val("0");
		$("#txt_costLoad_Return").val(costLoad);
		$("#txt_costLoad_Month").val("0");
		$("#txt_costLoad_Cur").attr("disabled",true);
		$("#txt_costLoad_After").attr("disabled",true);
		$("#txt_costLoad_Return").attr("disabled",true);
		$("#txt_costLoad_Month").attr("disabled",true);
	}
	//如果是到付
	else if(typeId=="3"){
		$("#txt_costLoad_Cur").val("0");
		$("#txt_costLoad_After").val(costLoad);
		$("#txt_costLoad_Return").val("0");
		$("#txt_costLoad_Month").val("0");
		$("#txt_costLoad_Cur").attr("disabled",true);
		$("#txt_costLoad_After").attr("disabled",true);
		$("#txt_costLoad_Return").attr("disabled",true);
		$("#txt_costLoad_Month").attr("disabled",true);
	}
	//如果是月结
	else if(typeId=="4"){
		$("#txt_costLoad_Cur").val("0");
		$("#txt_costLoad_After").val("0");
		$("#txt_costLoad_Return").val("0");
		$("#txt_costLoad_Month").val(costLoad);
		$("#txt_costLoad_Cur").attr("disabled",true);
		$("#txt_costLoad_After").attr("disabled",true);
		$("#txt_costLoad_Return").attr("disabled",true);
		$("#txt_costLoad_Month").attr("disabled",true);
	}
	//如果是混付
	else if(typeId=="5"){
		if(edit==undefined)
		{
			$("#txt_costLoad_Cur").val(costLoad);
			$("#txt_costLoad_After").val("0");
			$("#txt_costLoad_Return").val("0");
			$("#txt_costLoad_Month").val("0");
		}
		$("#txt_costLoad_Cur").attr("disabled",false);
		$("#txt_costLoad_After").attr("disabled",false);
		$("#txt_costLoad_Return").attr("disabled",false);
		$("#txt_costLoad_Month").attr("disabled",false);
	}
}

function pageFormSubmit()
{
	document.forms["loadListForm"].submit();
}

function gotoPage(pageNo)
{
	if(Number(pageNo)>Number($("#ddl-pageSize").val())){
		alert("超过最大的页码数");
		return;
	}
	$("#pageNoId").val(pageNo);

	document.forms["loadListForm"].submit();
}

//载入验证所需方法
jessrun.loadJS(jessrun.config.domain+"/style/js/init-form.js",function(){
},function(){
	alert(jessrun.url.jsError);
});

//配置提交表单Id
jessrun.formId={
	id:"form_loadAdd"
};
$(function(){
	//提交数据
	jessrun.formLoadAdd("carry_loadAdd","form_loadAdd","loadFormSubmit","数据保存中");
	jessrun.formLoadPullOut("btn_loadPullout","form_loadPullout","loadFormSubmit","发车数据保存中");
	jessrun.formLoadArrivein("btn_loadArrivein","form_loadArrivein","loadFormSubmit","签到数据保存中");
});


/**
 * 装车单的添加
 */
jessrun.formLoadAdd=function(btnId,formId,loadClass,loadHtml){
	$("#"+btnId).click(function(){

		if ($("#"+formId).valid()) {
			if($("tr[id^='tr_']").length == 0){
				alert("请选择货物！");
				return;
			}
			$("#"+btnId).hide();
			$("<span class='"+loadClass+"'><span><em>"+loadHtml+"</em></span></span>").insertAfter("#"+btnId);
			function doSubmit(){
				//向隐藏控件传值
				$("#hid_stateStartId").val($("#ddl_stateStart").val());
				$("#hid_stateStartName").val($("#ddl_stateStart").find("option:selected").text());
				$("#hid_stateEndId").val($("#ddl_stateEnd").val());
				$("#hid_stateEndName").val($("#ddl_stateEnd").find("option:selected").text());
				$("#hid_loadPayTypeName").val($("#ddl_loadPayTypeId").find("option:selected").text());
				$("#hid_loadCarName").val($("#ddl_loadCarId").find("option:selected").text());
				$("#hid_loadDriverName").val($("#ddl_loadDriverId").find("option:selected").text());
				$("#hid_loadStaffName").val($("#ddl_loadStaffId").find("option:selected").text());
				$("#hid_costLoad").val($("#txt_costLoad").val());
				$("#hid_costLoadCur").val($("#txt_costLoad_Cur").val());
				$("#hid_costLoadAfter").val($("#txt_costLoad_After").val());
				$("#hid_costLoadReturn").val($("#txt_costLoad_Return").val());
				$("#hid_costLoadMonth").val($("#txt_costLoad_Month").val());
				$("#"+formId).submit();
			}
			setTimeout(doSubmit,0);
			}
	});
};

/**
 * 发车
 */
jessrun.formLoadPullOut=function(btnId,formId,loadClass,loadHtml){
	$("#"+btnId).click(function(){
		if ($("#"+formId).valid()) {
			$("#"+btnId).hide();
			$("<span class='"+loadClass+"'><span><em>"+loadHtml+"</em></span></span>").insertAfter("#"+btnId);
			function doSubmit(){
				$("#hid_logStaffName").val($("#ddl_LogStaffId").find("option:selected").text());
				$("#"+formId).submit();
			}
			setTimeout(doSubmit,0);
		}
	});
};
/**
 * 到货签到
 */
jessrun.formLoadArrivein=function(btnId,formId,loadClass,loadHtml){
	$("#"+btnId).click(function(){
		if ($("#"+formId).valid()) {
		$("#"+btnId).hide();
		$("<span class='"+loadClass+"'><span><em>"+loadHtml+"</em></span></span>").insertAfter("#"+btnId);
		function doSubmit(){
			$("#hid_logStaffName").val($("#ddl_LogStaffId").find("option:selected").text());
			$("#hid_depotName").val($("#ddl_depotId").find("option:selected").text());
			
			$("#"+formId).submit();
		}
		setTimeout(doSubmit,0);
		}
	});
};