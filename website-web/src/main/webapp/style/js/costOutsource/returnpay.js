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
		//更新提交控件名称
		$("#txt_costOursourceReturnNow_"+outsourceId).attr("name","returnlist["+lineNo+"].costOutsourceReturnNow");
		$("#txt_costMessageReturnNow_"+outsourceId).attr("name","returnlist["+lineNo+"].costMessageReturnNow");
		$("#txt_costMessageReturnLoss_"+outsourceId).attr("name","returnlist["+lineNo+"].costMessageReturnLoss");
		$("#txt_costAgentReturnNow_"+outsourceId).attr("name","returnlist["+lineNo+"].costAgentReturnNow");
		$("#txt_costAgentReturnLoss_"+outsourceId).attr("name","returnlist["+lineNo+"].costAgentReturnLoss");
		$("#hid_outsourceId_"+outsourceId).attr("name","returnlist["+lineNo+"].outsourceId");
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
	
	var CostOursourceReturnSum = 0;
	var CostOursourceReturnTrueSum = 0;
	var CostOursourceReturnNowSum = 0;
	$("[id^='span_costOursourceReturn_']").each(function (){
		CostOursourceReturnSum+=Number($(this).text());
	});
	$("[id^='span_costOursourceReturnTrue_']").each(function (){
		CostOursourceReturnTrueSum+=Number($(this).text());
	});
	$("[id^='txt_costOursourceReturnNow_']").each(function (){
		CostOursourceReturnNowSum+=Number($(this).val());
	});
	$("#span_sumCostOursourceReturn").text(CostOursourceReturnSum);
	$("#span_sumCostOursourceReturnTrue").text(CostOursourceReturnTrueSum);
	$("#span_sumCostOursourceReturnNow").text(CostOursourceReturnNowSum);

	var CostMessageReturnSum = 0;
	var CostMessageReturnTrueSum = 0;
	var CostMessageReturnNowSum = 0;
	var CostMessageReturnLossSum = 0;
	$("[id^='span_costMessageReturn_']").each(function (){
		CostMessageReturnSum+=Number($(this).text());
	});
	$("[id^='span_costMessageReturnTrue_']").each(function (){
		CostMessageReturnTrueSum+=Number($(this).text());
	});
	$("[id^='txt_costMessageReturnNow_']").each(function (){
		CostMessageReturnNowSum+=Number($(this).val());
	});
	$("[id^='txt_costMessageReturnLoss_']").each(function (){
		CostMessageReturnLossSum+=Number($(this).val());
	});
	$("#span_sumCostMessageReturn").text(CostMessageReturnSum);
	$("#span_sumCostMessageReturnTrue").text(CostMessageReturnTrueSum);
	$("#span_sumCostMessageReturnNow").text(CostMessageReturnNowSum);
	$("#span_sumCostMessageReturnLoss").text(CostMessageReturnLossSum);

	var CostAgentReturnSum = 0;
	var CostAgentReturnTrueSum = 0;
	var CostAgentReturnNowSum = 0;
	var CostAgentReturnLossSum = 0;
	$("[id^='span_costAgentReturn_']").each(function (){
		CostAgentReturnSum+=Number($(this).text());
	});
	$("[id^='span_costAgentReturnTrue_']").each(function (){
		CostAgentReturnTrueSum+=Number($(this).text());
	});
	$("[id^='txt_costAgentReturnNow_']").each(function (){
		CostAgentReturnNowSum+=Number($(this).val());
	});
	$("[id^='txt_costAgentReturnLoss_']").each(function (){
		CostAgentReturnLossSum+=Number($(this).val());
	});
	$("#span_sumCostAgentReturn").text(CostAgentReturnSum);
	$("#span_sumCostAgentReturnTrue").text(CostAgentReturnTrueSum);
	$("#span_sumCostAgentReturnNow").text(CostAgentReturnNowSum);
	$("#span_sumCostAgentReturnLoss").text(CostAgentReturnLossSum);
	
	$("#hid_sum").val(Number(CostAgentReturnNowSum)
			+Number(CostMessageReturnNowSum)
			-Number(CostOursourceReturnNowSum));
}