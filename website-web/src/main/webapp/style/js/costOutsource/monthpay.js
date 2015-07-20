var jessrun = typeof $ === "function" ? window.$ : {};
$(function() {
	//表格高亮
	$(".mod_table_bd").tableUI();
	//时间控件
	
	jessrun.formSubmitGetPay('carry_get','frm_cost','loadFormSubmit','数据保存中');
	
	//下面数量改变事件
	jessrun.chect_Number = function(txtId,otherId,costType,outsourceId){
		//取总金额
		var sumVal=$("#txt_"+txtId+"_"+outsourceId).attr("sumVal");
		//取相关字段的值
		var otherVal=0;
		var target_obj = $("#txt_"+otherId+"_"+outsourceId);
		//如果存在相关字段则赋值
		if (target_obj.length > 0)
			otherVal=$("#txt_"+otherId+"_"+outsourceId).val();
		//取默认值
		var defaultVal=Number(sumVal)-Number(otherVal);
		//验证金额
		var nowValue=checkMoneyAndDefault($("#txt_"+txtId+"_"+outsourceId), defaultVal);
		//如果值不在范围
		if(costType=="now")
		{
			if(Number(sumVal)<Number(nowValue))
				$("#txt_"+txtId+"_"+outsourceId).val(defaultVal);
		}
		else
		{
			if((Number(sumVal)-Number(otherVal))<Number(nowValue))
				$("#txt_"+txtId+"_"+outsourceId).val(defaultVal);
		}
		nowValue=$("#txt_"+txtId+"_"+outsourceId).val();
		//如果存在相关字段且是应收则联动优惠
		if (target_obj.length > 0 && costType=="now")
		{
			$("#txt_"+otherId+"_"+outsourceId).val(Number(sumVal)-Number(nowValue));
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
function remove_Tr(outsourceId)
{
	removeTr(outsourceId);
}
//主体页面删除方法
function removeTr(outsourceId)
{
	var target_obj = $("#tr_"+outsourceId);
	//如果主页面元素存在则删除
	if (target_obj.length > 0)
		$("#tr_"+outsourceId).remove();
	//计算合计
	getSum();
}

//判断站点是否可以修改
function stateReadonly()
{
	var lineNo = 0;
	$("[id^='tr_']").each(function(){
		var outsourceId = $(this).attr("outsourceId");
		//更新提交控件名称txt_costAgentMonthLoss_txt_costAgentMonthLoss_
		$("#txt_costOursourceMonthNow_"+outsourceId).attr("name","monthlist["+lineNo+"].costOutsourceMonthNow");
		$("#txt_costMessageMonthNow_"+outsourceId).attr("name","monthlist["+lineNo+"].costMessageMonthNow");
		$("#txt_costMessageMonthLoss_"+outsourceId).attr("name","monthlist["+lineNo+"].costMessageMonthLoss");
		$("#txt_costAgentMonthNow_"+outsourceId).attr("name","monthlist["+lineNo+"].costAgentMonthNow");
		$("#txt_costAgentMonthLoss_"+outsourceId).attr("name","monthlist["+lineNo+"].costAgentMonthLoss");
		$("#hid_outsourceId_"+outsourceId).attr("name","monthlist["+lineNo+"].outsourceId");
		//主体页面序号
		lineNo=Number(lineNo)+1;
		$("#span_no_"+outsourceId).html(lineNo);
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
		var outsourceId = $(this).attr("outsourceId");
		//主体页面序号
		lineNo=Number(lineNo)+1;
		$("#span_no_"+outsourceId).html(lineNo);
	});
	var CostOursourceAllSum = 0;
	var CostOursourceMonthSum = 0;
	var CostOursourceMonthTrueSum = 0;
	var CostOursourceMonthNowSum = 0;
	$("[id^='span_costOursourceAll_']").each(function (){
		CostOursourceAllSum+=Number($(this).text());
	});
	$("[id^='span_costOursourceMonth_']").each(function (){
		CostOursourceMonthSum+=Number($(this).text());
	});
	$("[id^='span_costOursourceMonthTrue_']").each(function (){
		CostOursourceMonthTrueSum+=Number($(this).text());
	});
	$("[id^='txt_costOursourceMonthNow_']").each(function (){
		CostOursourceMonthNowSum+=Number($(this).val());
	});
	$("#span_sumCostOursourceAll").text(CostOursourceAllSum);
	$("#span_sumCostOursourceMonth").text(CostOursourceMonthSum);
	$("#span_sumCostOursourceMonthTrue").text(CostOursourceMonthTrueSum);
	$("#span_sumCostOursourceMonthNow").text(CostOursourceMonthNowSum);

	var CostMessageMonthSum = 0;
	var CostMessageMonthTrueSum = 0;
	var CostMessageMonthNowSum = 0;
	var CostMessageMonthLossSum = 0;
	$("[id^='span_costMessageMonth_']").each(function (){
		CostMessageMonthSum+=Number($(this).text());
	});
	$("[id^='span_costMessageMonthTrue_']").each(function (){
		CostMessageMonthTrueSum+=Number($(this).text());
	});
	$("[id^='txt_costMessageMonthNow_']").each(function (){
		CostMessageMonthNowSum+=Number($(this).val());
	});
	$("[id^='txt_costMessageMonthLoss_']").each(function (){
		CostMessageMonthLossSum+=Number($(this).val());
	});
	$("#span_sumCostMessageMonth").text(CostMessageMonthSum);
	$("#span_sumCostMessageMonthTrue").text(CostMessageMonthTrueSum);
	$("#span_sumCostMessageMonthNow").text(CostMessageMonthNowSum);
	$("#span_sumCostMessageMonthLoss").text(CostMessageMonthLossSum);

	var CostAgentMonthSum = 0;
	var CostAgentMonthTrueSum = 0;
	var CostAgentMonthNowSum = 0;
	var CostAgentMonthLossSum = 0;
	$("[id^='span_costAgentMonth_']").each(function (){
		CostAgentMonthSum+=Number($(this).text());
	});
	$("[id^='span_costAgentMonthTrue_']").each(function (){
		CostAgentMonthTrueSum+=Number($(this).text());
	});
	$("[id^='txt_costAgentMonthNow_']").each(function (){
		CostAgentMonthNowSum+=Number($(this).val());
	});
	$("[id^='txt_costAgentMonthLoss_']").each(function (){
		CostAgentMonthLossSum+=Number($(this).val());
	});
	$("#span_sumCostAgentMonth").text(CostAgentMonthSum);
	$("#span_sumCostAgentMonthTrue").text(CostAgentMonthTrueSum);
	$("#span_sumCostAgentMonthNow").text(CostAgentMonthNowSum);
	$("#span_sumCostAgentMonthLoss").text(CostAgentMonthLossSum);
	
	$("#hid_sum").val(Number(CostAgentMonthNowSum)
			+Number(CostMessageMonthNowSum)
			-Number(CostOursourceMonthNowSum));
}