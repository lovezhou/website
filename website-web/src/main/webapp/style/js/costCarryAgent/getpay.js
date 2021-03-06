var jessrun = typeof $ === "function" ? window.$ : {};
$(function() {
	//表格高亮
	$(".mod_table_bd").tableUI();
	//时间控件
	//日期附值
	$("#ddl_LogStaffId").change(function(){
		$("#hid_logStaffName").val($(this).find("option:selected").text());
	});
	
	//jessrun.formSubmitGetPay('carry_get','frm_cost','loadFormSubmit','数据保存中');
	
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

/*jessrun.formSubmitGetPay=function(btnId,formId,loadClass,loadHtml){
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
};*/


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
		$("#txt_costPayAgentReturnNow_"+carryId).attr("name","agentlist["+lineNo+"].costPayAgentReturnNow");
		$("#txt_costGetBank_"+carryId).attr("name","agentlist["+lineNo+"].costGetBank");
		$("#hid_carryId_"+carryId).attr("name","agentlist["+lineNo+"].carryId");
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
	
	var sumAgent = 0;
	var sumAgentReduce = 0;
	var sumAgentAfterTrue = 0;
	var sumAgentReturn = 0;
	var sumAgentReturnTrue = 0;
	var sumAgentReturnNow = 0;
	var sumBankGet = 0;
	$("[id^='span_costCarryAgentAll_']").each(function (){
		sumAgent+=Number($(this).text());
	});
	$("[id^='span_costCarryAgentReduce_']").each(function (){
		sumAgentReduce+=Number($(this).text());
	});
	$("[id^='span_top_costGetAgentAfterTrue_']").each(function (){
		sumAgentAfterTrue+=Number($(this).text());
	});
	$("[id^='span_top_costCarryAgentReturn_']").each(function (){
		sumAgentReturn+=Number($(this).text());
	});
	$("[id^='span_top_costPayAgentReturnTrue_']").each(function (){
		sumAgentReturnTrue+=Number($(this).text());
	});
	$("[id^='txt_costPayAgentReturnNow_']").each(function (){
		sumAgentReturnNow+=Number($(this).val());
	});
	$("[id^='txt_costGetBank_']").each(function (){
		sumBankGet+=Number($(this).val());
	});
	$("#span_sumAgent").text(sumAgent);
	$("#span_sumAgentReduce").text(sumAgentReduce);
	$("#span_sumAgentAfterTrue").text(sumAgentAfterTrue);
	$("#span_sumAgentReturn").text(sumAgentReturn);
	$("#span_sumAgentReturnTrue").text(sumAgentReturnTrue);
	$("#span_sumAgentReturnNow").text(sumAgentReturnNow);
	$("#span_sumAgentBankget").text(sumBankGet);
	
	
	
	$("#hid_sum").val(sumAgentReturnNow);
}