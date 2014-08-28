var jessrun = typeof $ === "function" ? window.$ : {};
$(function() {
	//表格高亮
	$(".mod_table_bd").tableUI();
	//时间控件
	
	jessrun.formSubmitGetPay('carry_get','frm_cost','loadFormSubmit','数据保存中');
	
	//下面数量改变事件
	jessrun.chect_Number = function(txtId,otherId,costType,outdepotId){
		//取总金额
		var sumVal=$("#txt_"+txtId+"_"+outdepotId).attr("sumVal");
		//取相关字段的值
		var otherVal=0;
		var target_obj = $("#txt_"+otherId+"_"+outdepotId);
		//如果存在相关字段则赋值
		if (target_obj.length > 0)
			otherVal=$("#txt_"+otherId+"_"+outdepotId).val();
		//取默认值
		var defaultVal=Number(sumVal)-Number(otherVal);
		//验证金额
		var nowValue=checkMoneyAndDefault($("#txt_"+txtId+"_"+outdepotId), defaultVal);
		//如果
		if(Number(sumVal)<Number(nowValue))
			$("#txt_"+txtId+"_"+outdepotId).val(defaultVal);
		nowValue=$("#txt_"+txtId+"_"+outdepotId).val();
		//如果存在相关字段且是应收则联动优惠
		if (target_obj.length > 0 && costType=="now")
		{
			$("#txt_"+otherId+"_"+outdepotId).val(Number(sumVal)-Number(nowValue));
		}
		//计算合计
		getSum();
	};
	
	$("#ddl_LogStaffId").change(function(){
		$("#hid_logStaffName").val($(this).find("option:selected").text());
	});
});

jessrun.formSubmitGetPay=function(btnId,formId,loadClass,loadHtml){
	$("#"+btnId).click(function(){
		if($("#tb_list tr").length==0){
			alert("请查询之后选择一条记录");
			return false;
		}
		if ($("#"+formId).valid()){
			$("#"+btnId).hide();
			$("<span class='"+loadClass+"'><span><em>"+loadHtml+"</em></span></span>").insertAfter("#"+btnId);
			function doSubmit(){
				$("#"+formId).submit();
			}
			setTimeout(doSubmit,0);
		}
	});
};
//主体页面删除方法
function remove_Tr(outdepotId)
{
	removeTr(outdepotId);
}
//主体页面删除方法
function removeTr(outdepotId)
{
	var target_obj = $("#tr_"+outdepotId);
	//如果主页面元素存在则删除
	if (target_obj.length > 0)
		$("#tr_"+outdepotId).remove();
	//计算合计
	getSum();
}

//判断站点是否可以修改
function stateReadonly()
{
	var lineNo = 0;
	$("[id^='tr_']").each(function(){
		var outdepotId = $(this).attr("outdepotId");
		//更新提交控件名称
		$("#txt_paySendCargoNow_"+outdepotId).attr("name","depotlist["+lineNo+"].paySendCargoNow");
		$("#hid_outdepotId_"+outdepotId).attr("name","depotlist["+lineNo+"].outdepotId");
		//主体页面序号
		lineNo=Number(lineNo)+1;
		$("#span_no_"+outdepotId).html(lineNo);
	});
}

function getLogStaffList(stateId,deptId)
{
	//重新选择发货站点后，删除所有应收应付信息
	$("[id^='tr_']").remove();
	//计算合计
	getSum();
	jessrun.loadToStaff("hid_logStateId","ddl_LogStaffId",-1);
}

function getSum()
{
	//先将不要显示的字段删除
	$("[id^='td_noShow_']").remove();
	//将原来隐藏的显示
	$("[id^='td_hid_']").removeAttr("style");
	
	//更新序号
	var lineNo = 0;
	$("[id^='tr_']").each(function(){
		var outdepotId = $(this).attr("outdepotId");
		//主体页面序号
		lineNo=Number(lineNo)+1;
		$("#span_no_"+outdepotId).html(lineNo);
	});
	
	var paySendCargo = 0;
	var paySendCargoTrue = 0;
	var paySendcargoNows = 0;
	//计算合计
	$("[id^='txt_paySendCargoNow_']").each(function (){
		paySendcargoNows+=Number($(this).val());
	});
	$("[id^='span_paySendCargoTrue_']").each(function (){
		paySendCargoTrue+=Number($(this).text());
	});
	$("[id^='span_paySendCargo_']").each(function (){
		paySendCargo+=Number($(this).text());
	});
	$("#span_sumpaySendCargo").text(paySendCargo);
	$("#span_sumpaySendCargoTrue").text(paySendCargoTrue);
	$("#span_sumpaySendcargoNow").text(paySendcargoNows);
	
	$("#hid_sum").val(paySendcargoNows);
}