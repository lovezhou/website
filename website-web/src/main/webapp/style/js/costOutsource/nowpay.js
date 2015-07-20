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
		$("#txt_costOursourceCurNow_"+outsourceId).attr("name","nowlist["+lineNo+"].costOutsourceCurNow");
		$("#txt_costMessageCurNow_"+outsourceId).attr("name","nowlist["+lineNo+"].costMessageCurNow");
		$("#txt_costAgentCurNow_"+outsourceId).attr("name","nowlist["+lineNo+"].costAgentCurNow");
		$("#hid_outsourceId_"+outsourceId).attr("name","nowlist["+lineNo+"].outsourceId");
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
	
	var CostOursourceCurSum = 0;
	var CostOursourceCurTrueSum = 0;
	var CostOursourceCurNowSum = 0;
	$("[id^='span_costOursourceCur_']").each(function (){
		CostOursourceCurSum+=Number($(this).text());
	});
	$("[id^='span_costOursourceCurTrue_']").each(function (){
		CostOursourceCurTrueSum+=Number($(this).text());
	});
	$("[id^='txt_costOursourceCurNow_']").each(function (){
		CostOursourceCurNowSum+=Number($(this).val());
	});
	$("#span_sumCostOursourceCur").text(CostOursourceCurSum);
	$("#span_sumCostOursourceCurTrue").text(CostOursourceCurTrueSum);
	$("#span_sumCostOursourceCurNow").text(CostOursourceCurNowSum);

	var CostMessageCurSum = 0;
	var CostMessageCurTrueSum = 0;
	var CostMessageCurNowSum = 0;
	$("[id^='span_costMessageCur_']").each(function (){
		CostMessageCurSum+=Number($(this).text());
	});
	$("[id^='span_costMessageCurTrue_']").each(function (){
		CostMessageCurTrueSum+=Number($(this).text());
	});
			 
	$("[id^='txt_costMessageCurNow_']").each(function (){
		CostMessageCurNowSum+=Number($(this).val());
	});
	$("#span_sumCostMessageCur").text(CostMessageCurSum);
	$("#span_sumCostMessageCurTrue").text(CostMessageCurTrueSum);
	$("#span_sumCostMessageCurNow").text(CostMessageCurNowSum);

	var CostAgentCurSum = 0;
	var CostAgentCurTrueSum = 0;
	var CostAgentCurNowSum = 0;
	$("[id^='span_costAgentCur_']").each(function (){
		CostAgentCurSum+=Number($(this).text());
	});
	$("[id^='span_costAgentCurTrue_']").each(function (){
		CostAgentCurTrueSum+=Number($(this).text());
	});
	$("[id^='txt_costAgentCurNow_']").each(function (){
		CostAgentCurNowSum+=Number($(this).val());
	});
	$("#span_sumCostAgentCur").text(CostAgentCurSum);
	$("#span_sumCostAgentCurTrue").text(CostAgentCurTrueSum);
	$("#span_sumCostAgentCurNow").text(CostAgentCurNowSum);
	
	$("#hid_sum").val(Number(CostAgentCurNowSum)
			+Number(CostMessageCurNowSum)
			-Number(CostOursourceCurNowSum));
}