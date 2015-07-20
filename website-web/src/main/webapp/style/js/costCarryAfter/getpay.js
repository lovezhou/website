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
		//如果值不在范围
		if(costType=="now")
		{
			if(Number(sumVal)<Number(nowValue))
				$("#txt_"+txtId+"_"+carryId).val(defaultVal);
		}
		else
		{
			if((Number(sumVal)-Number(otherVal))<Number(nowValue))
				$("#txt_"+txtId+"_"+carryId).val(defaultVal);
		}
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

//运保费提收改变事件
function changeTransinsurAfter(obj)
{
	//取默认值
	var defaultVal=$(obj).attr("defalutVal");
	//验证金额
	var nowValue=checkMoneyAndDefault($(obj), defaultVal);
	//取实际收款值
	var trueVal = $(obj).attr("trueVal");
	//取优惠金额
	var lossVal = $(obj).attr("lossVal");
	//如果填写的金额小于实际收款值，则还原为实际收款值
	if(nowValue < trueVal)
		$(obj).val(defaultVal);
	//取承运单ID
	var carryId = $(obj).attr("carryId");
	//打开应收和优惠的编辑
	sumVal = Number(nowValue) - Number(trueVal);
	$("#txt_costGetTransinsurAfterNow_"+carryId).removeAttr("disabled");
	$("#txt_costGetTransinsurAfterNow_"+carryId).attr("sumVal",sumVal);
	$("#txt_costGetTransinsurAfterLoss_"+carryId).removeAttr("disabled");
	$("#txt_costGetTransinsurAfterLoss_"+carryId).attr("sumVal",sumVal);
	getSum();
}

//判断站点是否可以修改
function stateReadonly()
{
	var lineNo = 0;
	$("[id^='tr_']").each(function(){
		var carryId = $(this).attr("carryId");
		//更新提交控件名称
		$("#txt_costCarryTransinsurAfter_"+carryId).attr("name","afterlist["+lineNo+"].costCarryTransinsurAfter");
		$("#txt_costGetTransinsurAfterNow_"+carryId).attr("name","afterlist["+lineNo+"].costGetTransinsurAfterNow");
		$("#txt_costGetTransinsurAfterLoss_"+carryId).attr("name","afterlist["+lineNo+"].costGetTransinsurAfterLoss");
		$("#txt_costGetTransinsurDriverNow_"+carryId).attr("name","afterlist["+lineNo+"].costGetTransinsurDriverNow");
		$("#txt_costGetTransinsurDriverLoss_"+carryId).attr("name","afterlist["+lineNo+"].costGetTransinsurDriverLoss");
		$("#txt_costGetSendcargoAfter_"+carryId).attr("name","afterlist["+lineNo+"].costGetSendcargoAfter");
		$("#txt_costGetSendcargoAfterNow_"+carryId).attr("name","afterlist["+lineNo+"].costGetSendcargoAfterNow");
		$("#txt_costGetSendcargoAfterLoss_"+carryId).attr("name","afterlist["+lineNo+"].costGetSendcargoAfterLoss");
		$("#txt_costGetAgentAfterNow_"+carryId).attr("name","afterlist["+lineNo+"].costGetAgentAfterNow");
		$("#txt_costGetAgentAfterLoss_"+carryId).attr("name","afterlist["+lineNo+"].costGetAgentAfterLoss");
		$("#hid_carryStateStartId_"+carryId).attr("name","afterlist["+lineNo+"].carryStateStartId");
		$("#hid_carryStateEndId_"+carryId).attr("name","afterlist["+lineNo+"].carryStateEndId");
		$("#hid_carryId_"+carryId).attr("name","afterlist["+lineNo+"].carryId");
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
	
	var TansinsuerAfterSum = 0;
	var TansinsuerAfterTrueSum = 0;
	var TansinsuerAfterNowSum = 0;
	var TansinsuerAfterLossSum = 0;
	//计算合计
	$("[id^='span_costCarryTransinsurAfter_']").each(function (){
		TansinsuerAfterSum+=Number($(this).text());
	});
	$("[id^='txt_costCarryTransinsurAfter_']").each(function (){
		TansinsuerAfterSum+=Number($(this).val());
	});
	$("[id^='span_costGetTransinsurAfterTrue_']").each(function (){
		TansinsuerAfterTrueSum+=Number($(this).text());
	});
	$("[id^='txt_costGetTransinsurAfterNow_']").each(function (){
		TansinsuerAfterNowSum+=Number($(this).val());
	});
	$("[id^='txt_costGetTransinsurAfterLoss_']").each(function (){
		TansinsuerAfterLossSum+=Number($(this).val());
	});
	$("#span_sumTransinserAfter").text(TansinsuerAfterSum);
	$("#span_sumTransinserAfterTrue").text(TansinsuerAfterTrueSum);
	$("#span_sumTransinserAfterNow").text(TansinsuerAfterNowSum);
	$("#span_sumTransinserAfterLoss").text(TansinsuerAfterLossSum);
	
	var TansinsuerDriverSum = 0;
	var TansinsuerDriverTrueSum = 0;
	var TansinsuerDriverNowSum = 0;
	var TansinsuerDriverLossSum = 0;
	//计算合计
	$("[id^='span_costCarryTransinsurDriver_']").each(function (){
		TansinsuerDriverSum+=Number($(this).text());
	});
	$("[id^='span_costGetTransinsurDriverTrue_']").each(function (){
		TansinsuerDriverTrueSum+=Number($(this).text());
	});
	$("[id^='txt_costGetTransinsurDriverNow_']").each(function (){
		TansinsuerDriverNowSum+=Number($(this).val());
	});
	$("[id^='txt_costGetTransinsurDriverLoss_']").each(function (){
		TansinsuerDriverLossSum+=Number($(this).val());
	});
	$("#span_sumTransinserDriver").text(TansinsuerDriverSum);
	$("#span_sumTransinserDriverTrue").text(TansinsuerDriverTrueSum);
	$("#span_sumTransinserDriverNow").text(TansinsuerDriverNowSum);
	$("#span_sumTransinserDriverLoss").text(TansinsuerDriverLossSum);

	var SendcargoAfterSum = 0;
	var SendcargoAfterTrueSum = 0;
	var SendcargoAfterNowSum = 0;
	var SendcargoAfterLossSum = 0;
	$("[id^='span_costCarrySendcargoAfter_']").each(function (){
		SendcargoAfterSum+=Number($(this).text());
	});
	$("[id^='span_costGetSendcargoAfterTrue_']").each(function (){
		SendcargoAfterTrueSum+=Number($(this).text());
	});
	$("[id^='txt_costGetSendcargoAfterNow_']").each(function (){
		SendcargoAfterNowSum+=Number($(this).val());
	});
	$("[id^='txt_costGetSendcargoAfterLoss_']").each(function (){
		SendcargoAfterLossSum+=Number($(this).val());
	});
		
	$("#span_sumSendCargoAfter").text(SendcargoAfterSum);
	$("#span_sumSendCargoTrue").text(SendcargoAfterTrueSum);
	$("#span_sumSendCargoNow").text(SendcargoAfterNowSum);
	$("#span_sumSendCargoLoss").text(SendcargoAfterLossSum);

	var sumAgentAfter = 0;
	var sumAgentTrue = 0;
	var sumAgentNow = 0;
	var sumAgentLoss = 0;
	$("[id^='span_costCarryAgentAfter_']").each(function (){
		sumAgentAfter+=Number($(this).text());
	});
	$("[id^='span_costGetAgentAfterTrue_']").each(function (){
		sumAgentTrue+=Number($(this).text());
	});
	$("[id^='txt_costGetAgentAfterNow_']").each(function (){
		sumAgentNow+=Number($(this).val());
	});
	$("[id^='txt_costGetAgentAfterLoss_']").each(function (){
		sumAgentLoss+=Number($(this).val());
	});
	$("#span_sumAgentAfter").text(sumAgentAfter);
	$("#span_sumAgentTrue").text(sumAgentTrue);	
	$("#span_sumAgentNow").text(sumAgentNow);
	$("#span_sumAgentLoss").text(sumAgentLoss);
	
	$("#hid_sum").val(TansinsuerAfterNowSum
			+TansinsuerDriverNowSum
			+SendcargoAfterNowSum
			+sumAgentNow);
}