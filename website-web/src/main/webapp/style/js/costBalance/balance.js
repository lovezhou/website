var jessrun = typeof $ === "function" ? window.$ : {};
$(function() {
	//表格高亮
	$(".mod_table_bd").tableUI();
	//时间控件
	
	
	//现收现付款提交
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
//现收现付款提交
jessrun.formSubmitGetPay = function(btnId,formId,loadClass,loadHtml){
	$("#"+btnId).click(function(){
		if(document.getElementById("mainframe").contentDocument.getElementById("ddl_carryDeptStartId").value==""){
			alert("请选择发货部门");
			return false;
		}
		if ($("#"+formId).valid()) {
			$("#"+btnId).hide();
			$("<span class='"+loadClass+"'><span><em>"+loadHtml+"</em></span></span>").insertAfter("#"+btnId);
			function doSubmit(){
				
					$("#"+formId).submit();
					
				}
			setTimeout(doSubmit,0);
		}
	});
}
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
		$("#txt_costGetTransinsurCurNow_"+carryId).attr("name","nowlist["+lineNo+"].costGetTransinsurCurNow");
		$("#txt_costGetSendcargoCurNow_"+carryId).attr("name","nowlist["+lineNo+"].costGetSendcargoCurNow");
		$("#txt_costCarryMessageCurNow_"+carryId).attr("name","nowlist["+lineNo+"].costCarryMessageCurNow");
		$("#txt_costPayAgentCurNow_"+carryId).attr("name","nowlist["+lineNo+"].costPayAgentCurNow");
		$("#hid_carryId_"+carryId).attr("name","nowlist["+lineNo+"].carryId");
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
	
	var TansinsuerCurSum = 0;
	var TansinsuerCurTrueSum = 0;
	var TansinsuerNowSum = 0;
	$("[id^='span_costCarryTransinsurCur_']").each(function (){
		TansinsuerCurSum+=Number($(this).text());
	});
	$("[id^='span_costGetTransinsurCurTrue_']").each(function (){
		TansinsuerCurTrueSum+=Number($(this).text());
	});
	$("[id^='txt_costGetTransinsurCurNow_']").each(function (){
		TansinsuerNowSum+=Number($(this).val());
	});
	$("#span_sumTransinserCur").text(TansinsuerCurSum);
	$("#span_sumTransinserTrue").text(TansinsuerCurTrueSum);
	$("#span_sumTransinserNow").text(TansinsuerNowSum);

	var SendcargoCurSum = 0;
	var SendcargoCurTrueSum = 0;
	var SendcargoCurNowSum = 0;
	$("[id^='span_costCarrySendcargo_']").each(function(){
		SendcargoSum+=Number($(this).text());
	});
	$("[id^='span_costCarrySendcargoCur_']").each(function (){
		SendcargoCurSum+=Number($(this).text());
	});
	$("[id^='span_costGetSendcargoCurTrue_']").each(function (){
		SendcargoCurTrueSum+=Number($(this).text());
	});
	$("[id^='txt_costGetSendcargoCurNow_']").each(function (){
		SendcargoCurNowSum+=Number($(this).val());
	});
	$("#span_sumSendcargoCur").text(SendcargoCurSum);
	$("#span_sumSendcargoTrue").text(SendcargoCurTrueSum);
	$("#span_sumSendcargoNow").text(SendcargoCurNowSum);

	var MessageCurSum = 0;
	var MessageCurTrueSum = 0;
	var MessageCurNowSum = 0;
	$("[id^='span_costCarryMessageCur_']").each(function (){
		MessageCurSum+=Number($(this).text());
	});
	$("[id^='span_costPayMessageCurTrue_']").each(function (){
		MessageCurTrueSum+=Number($(this).text());
	});
	$("[id^='txt_costPayMessageCurNow_']").each(function (){
		MessageCurNowSum+=Number($(this).val());
	});
	$("#span_sumMessageCur").text(MessageCurSum);
	$("#span_sumMessageTrue").text(MessageCurTrueSum);
	$("#span_sumMessageNow").text(MessageCurNowSum);

	var sumAgentCur = 0;
	var sumAgentTrue = 0;
	var sumAgentNow = 0;
	$("[id^='span_costCarryAgentCur_']").each(function (){
		sumAgentCur+=Number($(this).text());
	});
	$("[id^='span_costPayAgentCurTrue_']").each(function (){
		sumAgentTrue+=Number($(this).text());
	});
	$("[id^='txt_costPayAgentCurNow_']").each(function (){
		sumAgentNow+=Number($(this).val());
	});
	$("#span_sumAgentCur").text(sumAgentCur);
	$("#span_sumAgentTrue").text(sumAgentTrue);
	$("#span_sumAgentNow").text(sumAgentNow);
	
	$("#hid_sum").val(Number(TansinsuerNowSum)
			+Number(SendcargoCurNowSum)
			-Number(MessageCurNowSum)
			-Number(sumAgentNow));
}