var jessrun = typeof $ === "function" ? window.$ : {};
$(function() {
	//表格高亮
	$(".mod_table_bd").tableUI();
	
	//jessrun.formSubmit('load_pay','frm_cost','loadFormSubmit','数据保存中');
	
	//下面数量改变事件
	jessrun.chect_Number = function(txtId,otherId,costType,loadId){
		//取总金额
		var sumVal=$("#txt_"+txtId+"_"+loadId).attr("sumVal");
		//取相关字段的值
		var otherVal=0;
		var target_obj = $("#txt_"+otherId+"_"+loadId);
		//如果存在相关字段则赋值
		if (target_obj.length > 0)
			otherVal=$("#txt_"+otherId+"_"+loadId).val();
		//取默认值
		var defaultVal=Number(sumVal)-Number(otherVal);
		//验证金额
		var nowValue=checkMoneyAndDefault($("#txt_"+txtId+"_"+loadId), defaultVal);
		//如果值不在范围
		if(costType=="now")
		{
			if(Number(sumVal)<Number(nowValue))
				$("#txt_"+txtId+"_"+loadId).val(defaultVal);
		}
		else
		{
			if((Number(sumVal)-Number(otherVal))<Number(nowValue))
				$("#txt_"+txtId+"_"+loadId).val(defaultVal);
		}
		nowValue=$("#txt_"+txtId+"_"+loadId).val();
		//如果存在相关字段且是应收则联动优惠
		if (target_obj.length > 0 && costType=="now")
		{
			$("#txt_"+otherId+"_"+loadId).val(Number(sumVal)-Number(nowValue));
		}
		//更新名称、序号
		stateReadonly();
		//计算合计
		getSum();
	};
	
	$("#ddl_LogStaffId").change(function(){
		$("#hid_logStaffName").val($(this).find("option:selected").text());
	});
});
//主体页面删除方法
function remove_Tr(loadId)
{
	removeTr(loadId);
}
//主体页面删除方法
function removeTr(loadId)
{
	var target_obj = $("#tr_"+loadId);
	//如果主页面元素存在则删除
	if (target_obj.length > 0)
		$("#tr_"+loadId).remove();
	//更新名称、序号
	stateReadonly();
	//计算合计
	getSum();
}

//判断站点是否可以修改
function stateReadonly()
{
	var lineNo = 0;
	$("[id^='tr_']").each(function(){
		var loadId = $(this).attr("loadId");
		//更新提交控件名称
		$("#txt_costLoadReturnNow_"+loadId).attr("name","costLoadReturnGoings["+lineNo+"].costLoadReturnNow");
		$("#hid_loadId_"+loadId).attr("name","costLoadReturnGoings["+lineNo+"].loadId");
		//主体页面序号
		lineNo=Number(lineNo)+1;
		$("#span_no_"+loadId).html(lineNo);
	});
}

function getLogStaffList(stateId,deptId)
{
	//重新选择发货站点后，删除所有应收应付信息
	$("[id^='tr_']").remove();
	//更新名称、序号
	stateReadonly();
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
	
	var costTransSum = 0;
	var costLoadcargoSum = 0;
	var costOtherSum = 0;
	var costWithholdSum = 0;
	var costLoadSum = 0;
	var costLoadReturnSum = 0;
	var costLoadReturnTrueSum = 0;
	var costLoadReturnNow = 0;
	//计算合计
	$("[id^='span_costTrans_']").each(function(){
		costTransSum+=Number($(this).text());
	});
	$("[id^='span_costLoadcargo_']").each(function (){
		costLoadcargoSum+=Number($(this).text());
	});
	$("[id^='span_costOther_']").each(function (){
		costOtherSum+=Number($(this).text());
	});
	$("[id^='span_costWithhold_']").each(function (){
		costWithholdSum+=Number($(this).text());
	});
	$("[id^='span_costLoad_']").each(function (){
		costLoadSum+=Number($(this).text());
	});
	$("[id^='span_costLoadReturn_']").each(function (){
		costLoadReturnSum+=Number($(this).text());
	});
	$("[id^='span_costLoadReturnTrue_']").each(function (){
		costLoadReturnTrueSum+=Number($(this).text());
	});
	$("[id^='txt_costLoadReturnNow_']").each(function (){
		costLoadReturnNow+=Number($(this).val());
	});
	$("#span_sumCostTrans").text(costTransSum);
	$("#span_sumCostLoadcargo").text(costLoadcargoSum);
	$("#span_sumCostOther").text(costOtherSum);
	$("#span_sumCostWithhold").text(costWithholdSum);
	$("#span_sumCostLoad").text(costLoadSum);
	$("#span_sumCostLoadReturn").text(costLoadReturnSum);
	$("#span_sumCostLoadReturnTrue").text(costLoadReturnTrueSum);
	$("#span_sumCostLoadReturnNow").text(costLoadReturnNow);
	
	$("#hid_sum").val(0-Number(costLoadReturnNow));
}