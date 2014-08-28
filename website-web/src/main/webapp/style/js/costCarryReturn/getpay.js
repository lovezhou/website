var jessrun = typeof $ === "function" ? window.$ : {};
$(function() {
	//表格高亮
	$(".mod_table_bd").tableUI();
	//时间控件
	
	jessrun.formSubmitGetPay('carry_get','frm_cost','loadFormSubmit','数据保存中');
	
	//下面数量改变事件
	jessrun.chect_Number = function(txtId,otherId,costType,carryId){
		//取总金额
		var sumVal=$("#txt_"+txtId+"_"+carryId).attr("sumVal");
		//取相关字段的值
		var otherVal=0;
		var target_obj = $("#txt_"+otherId+"_"+carryId);
		//如果存在相关字段则赋值
		if (target_obj.length > 0)
			otherVal=$("#txt_"+otherId+"_"+carryId).val();
		//取默认值
		var defaultVal=Number(sumVal)-Number(otherVal);
		//验证金额
		var nowValue=checkMoneyAndDefault($("#txt_"+txtId+"_"+carryId), defaultVal);
		//如果
		if(Number(sumVal)<Number(nowValue))
			$("#txt_"+txtId+"_"+carryId).val(defaultVal);
		nowValue=$("#txt_"+txtId+"_"+carryId).val();
		//如果存在相关字段且是应收则联动优惠
		if (target_obj.length > 0 && costType=="now")
		{
			$("#txt_"+otherId+"_"+carryId).val(Number(sumVal)-Number(nowValue));
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
function remove_Tr(carryId)
{
	removeTr(carryId);
}
//主体页面删除方法
function removeTr(carryId)
{
	var target_obj = $("#tr_"+carryId);
	//如果主页面元素存在则删除
	if (target_obj.length > 0)
		$("#tr_"+carryId).remove();
	//计算合计
	getSum();
}

//判断站点是否可以修改
function stateReadonly()
{
	var lineNo = 0;
	$("[id^='tr_']").each(function(){
		var carryId = $(this).attr("carryId");
		//更新提交控件名称
		$("#txt_costGetTransinsurReturnNow_"+carryId).attr("name","returnlist["+lineNo+"].costGetTransinsurReturnNow");
		$("#txt_costGetTransinsurReturnLoss_"+carryId).attr("name","returnlist["+lineNo+"].costGetTransinsurReturnLoss");
		$("#txt_costGetSendcargoReturnNow_"+carryId).attr("name","returnlist["+lineNo+"].costGetSendcargoReturnNow");
		$("#txt_costGetSendcargoReturnLoss_"+carryId).attr("name","returnlist["+lineNo+"].costGetSendcargoReturnLoss");
		$("#txt_costPayMessageReturnNow_"+carryId).attr("name","returnlist["+lineNo+"].costPayMessageReturnNow");
		$("#txt_costPayReturnReturnNow_"+carryId).attr("name","returnlist["+lineNo+"].costPayReturnReturnNow");
		$("#txt_costPayAgentReturnNow_"+carryId).attr("name","returnlist["+lineNo+"].costPayAgentReturnNow");
		$("#txt_costGetBank_"+carryId).attr("name","returnlist["+lineNo+"].costGetBank");
		$("#hid_carryId_"+carryId).attr("name","returnlist["+lineNo+"].carryId");
		//主体页面序号
		lineNo=Number(lineNo)+1;
		$("#span_no_"+carryId).html(lineNo);
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
		var carryid = $(this).attr("carryId");
		//主体页面序号
		lineNo=Number(lineNo)+1;
		$("#span_no_"+carryid).html(lineNo);
	});
	
	var TansinsuerReturnSum = 0;
	var TansinsuerReturnTrueSum = 0;
	var TansinsuerNowSum = 0;
	var TansinsuerLossSum = 0;
	//计算合计
	$("[id^='span_costCarryTransinsurReturn_']").each(function (){
		TansinsuerReturnSum+=Number($(this).text());
	});
	$("[id^='span_costGetTransinsurReturnTrue_']").each(function (){
		TansinsuerReturnTrueSum+=Number($(this).text());
	});
	$("[id^='txt_costGetTransinsurReturnNow_']").each(function (){
		TansinsuerNowSum+=Number($(this).val());
	});
	$("[id^='txt_costGetTransinsurReturnLoss_']").each(function (){
		TansinsuerLossSum+=Number($(this).val());
	});
	$("#span_sumTransinserReturn").text(TansinsuerReturnSum);
	$("#span_sumTransinserTrue").text(TansinsuerReturnTrueSum);
	$("#span_sumTransinserNow").text(TansinsuerNowSum);
	$("#span_sumTransinserLoss").text(TansinsuerLossSum);


	var SendcargoReturnSum = 0;
	var SendcargoReturnTrueSum = 0;
	var SendcargoReturnNowSum = 0;
	var SendcargoReturnLossSum = 0;
	$("[id^='span_costCarrySendcargoReturn_']").each(function (){
		SendcargoReturnSum+=Number($(this).text());
	});
	$("[id^='span_costGetSendcargoReturnTrue_']").each(function (){
		SendcargoReturnTrueSum+=Number($(this).text());
	});
	$("[id^='txt_costGetSendcargoReturnNow_']").each(function (){
		SendcargoReturnNowSum+=Number($(this).val());
	});
	$("[id^='txt_costGetSendcargoReturnLoss_']").each(function (){
		SendcargoReturnLossSum+=Number($(this).val());
	});
	$("#span_sumSendCargoReturn").text(SendcargoReturnSum);
	$("#span_sumSendCargoTrue").text(SendcargoReturnTrueSum);
	$("#span_sumSendCargoNow").text(SendcargoReturnNowSum);
	$("#span_sumSendCargoLoss").text(SendcargoReturnLossSum);

	var MessageReturnSum = 0;
	var MessageReturnTrueSum = 0;
	var MessageReturnNowSum = 0;
	$("[id^='span_costCarryMessageReturn_']").each(function (){
		MessageReturnSum+=Number($(this).text());
	});
	$("[id^='span_costPayMessageReturnTrue_']").each(function (){
		MessageReturnTrueSum+=Number($(this).text());
	});
	$("[id^='txt_costPayMessageReturnNow_']").each(function (){
		MessageReturnNowSum+=Number($(this).val());
	});
	$("#span_sumMessageReturn").text(MessageReturnSum);
	$("#span_sumMessageTrue").text(MessageReturnTrueSum);
	$("#span_sumMessageNow").text(MessageReturnNowSum);

	var ReturnReturnSum = 0;
	var ReturnReturnTrueSum = 0;
	var ReturnReturnNowSum = 0;
	$("[id^='span_costCarryReturnReturn_']").each(function (){
		ReturnReturnSum+=Number($(this).text());
	});
	$("[id^='span_costPayReturnReturnTrue_']").each(function (){
		ReturnReturnTrueSum+=Number($(this).text());
	});
	$("[id^='txt_costPayReturnReturnNow_']").each(function (){
		ReturnReturnNowSum+=Number($(this).val());
	});
	$("#span_sumReturnReturn").text(ReturnReturnSum);
	$("#span_sumReturnTrue").text(ReturnReturnTrueSum);
	$("#span_sumReturnNow").text(ReturnReturnNowSum);

	var sumAgentReturn = 0;
	var sumAgentTrue = 0;
	var sumAgentNow = 0;
	var sumBankGet = 0;
	$("[id^='span_costCarryAgentReturn_']").each(function (){
		sumAgentReturn+=Number($(this).text());
	});
	$("[id^='span_costPayAgentReturnTrue_']").each(function (){
		sumAgentTrue+=Number($(this).text());
	});
	$("[id^='txt_costPayAgentReturnNow_']").each(function (){
		sumAgentNow+=Number($(this).val());
	});
	$("[id^='txt_costGetBank_']").each(function (){
		sumBankGet+=Number($(this).val());
	});
	
	$("#span_sumAgentReturn").text(sumAgentReturn);
	$("#span_sumAgentTrue").text(sumAgentTrue);
	$("#span_sumAgentNow").text(sumAgentNow);
	$("#span_sumAgentLoss").text(sumBankGet);
	
	$("#hid_sum").val(TansinsuerNowSum
			+SendcargoReturnNowSum
			-MessageReturnNowSum
			-ReturnReturnNowSum
			-sumAgentNow);
}