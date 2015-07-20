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

//判断站点是否可以修改
function stateReadonly()
{
	var lineNo = 0;
	$("[id^='tr_']").each(function(){
		var carryId = $(this).attr("carryId");
		//更新提交控件名称
		$("#txt_costGetTransinsurMonthLoss_"+carryId).attr("name","monthlist["+lineNo+"].costGetTransinsurMonthLoss");
		$("#txt_costGetTransinsurMonthNow_"+carryId).attr("name","monthlist["+lineNo+"].costGetTransinsurMonthNow");
		$("#hid_carryId_"+carryId).attr("name","monthlist["+lineNo+"].carryId");
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
	
	var TransinsurSum = 0;
	var TransinsurMonthSum = 0;
	var TransinsurMonthTrueSum = 0;
	var TransinsurMonthLossSum = 0;
	var TransinsurMonthNowSum = 0;
	//计算合计
	
	$("[id^='span_costCarryTransinsur_']").each(function(){
		TransinsurSum+=Number($(this).text());
	});
	
	$("[id^='span_costCarryTransinsurMonth_']").each(function (){
		TransinsurMonthSum+=Number($(this).text());
	});
	
	$("[id^='span_costGetTransinsurMonthTrue_']").each(function (){
		TransinsurMonthTrueSum+=Number($(this).text());
	});
	
	$("[id^='txt_costGetTransinsurMonthLoss_']").each(function (){
		TransinsurMonthLossSum+=Number($(this).val());
	});
	
	$("[id^='txt_costGetTransinsurMonthNow_']").each(function (){
		TransinsurMonthNowSum+=Number($(this).val());
	});
	//合计赋值
	$("#span_sumTransinser").text(TransinsurSum);
	$("#span_sumMonth").text(TransinsurMonthSum);
	$("#span_sumMonthTrue").text(TransinsurMonthTrueSum);
	$("#span_sumMonthLoss").text(TransinsurMonthLossSum);
	$("#span_sumMonthNow").text(TransinsurMonthNowSum);
	$("#hid_sum").val(TransinsurMonthNowSum);
}