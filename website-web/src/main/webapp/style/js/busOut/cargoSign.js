var jessrun = typeof $ === "function" ? window.$ : {};
$(function() {
	//表格高亮
	$(".mod_table_bd").tableUI();
	//时间控件
	
	
	jessrun.formSubmitCargoSign('carry_get','frm_cost','loadFormSubmit','数据保存中');
	
	
	
	
	//下面数量改变事件
	jessrun.chect_Number = function(txtId,otherId,costType,carryCargoId){
		//取总金额
		var sumVal=$("#txt_"+txtId+"_"+carryCargoId).attr("sumVal");
		//取相关字段的值
		var otherVal=0;
		var target_obj = $("#txt_"+otherId+"_"+carryCargoId);
		//如果存在相关字段则赋值
		if (target_obj.length > 0)
			otherVal=$("#txt_"+otherId+"_"+carryCargoId).val();
		//取默认值
		var defaultVal=Number(sumVal)-Number(otherVal);
		//验证金额
		var nowValue=checkMoneyAndDefault($("#txt_"+txtId+"_"+carryCargoId), defaultVal);
		//如果值不在范围
		if(costType=="now")
		{
			if(Number(sumVal)<Number(nowValue))
				$("#txt_"+txtId+"_"+carryCargoId).val(defaultVal);
		}
		else
		{
			if((Number(sumVal)-Number(otherVal))<Number(nowValue))
				$("#txt_"+txtId+"_"+carryCargoId).val(defaultVal);
		}
		nowValue=$("#txt_"+txtId+"_"+carryCargoId).val();
		//如果存在相关字段且是应收则联动优惠
		if (target_obj.length > 0 && costType=="now")
		{
			$("#txt_"+otherId+"_"+carryCargoId).val(Number(sumVal)-Number(nowValue));
		}
		//计算合计
		getSum();
	};
	
	$("#ddl_LogStaffId").change(function(){
		$("#hid_logStaffName").val($(this).find("option:selected").text());
	});
});

jessrun.formSubmitCargoSign=function(btnId,formId,loadClass,loadHtml){
	$("#"+btnId).click(function(){
		if($("#tb_list tr").length==0){
			alert("请查询之后选择一条记录");
			return false;
		}
		if ($("#"+formId).valid()){
			$("#"+btnId).hide()
			$("<span class='"+loadClass+"'><span><em>"+loadHtml+"</em></span></span>").insertAfter("#"+btnId);
			function doSubmit(){
				$("#"+formId).submit();
			}
			setTimeout(doSubmit,0);
		}
	});
};
//主体页面删除方法
function remove_Tr(carryCargoId)
{
	removeTr(carryCargoId);
}
//主体页面删除方法
function removeTr(carryCargoId)
{
	var target_obj = $("#tr_"+carryCargoId);
	//如果主页面元素存在则删除
	if (target_obj.length > 0)
		$("#tr_"+carryCargoId).remove();
	//计算合计
	getSum();
}

//判断站点是否可以修改
function stateReadonly()
{
	var lineNo = 0;
	$("[id^='tr_']").each(function(){
		var carryCargoId = $(this).attr("carryCargoId");
		$("#txt_signName_"+carryCargoId).attr("name","cargoList["+lineNo+"].signName");
		$("#txt_signTel_"+carryCargoId).attr("name","cargoList["+lineNo+"].signTel");
		$("#txt_signNo_"+carryCargoId).attr("name","cargoList["+lineNo+"].signNo");
		$("#hid_carryCargoId_"+carryCargoId).attr("name","cargoList["+lineNo+"].carryCargoId");
		//主体页面序号
		lineNo=Number(lineNo)+1;
		$("#span_no_"+carryCargoId).html(lineNo);
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
		var carryCargoId = $(this).attr("carryCargoId");
		//主体页面序号
		lineNo=Number(lineNo)+1;
		$("#span_no_"+carryCargoId).html(lineNo);
	});
	
	var sumCargoNumber = 0;
	var sumTransinser = 0;
	var sumTransinserAfter = 0;
	var sumSendCargo = 0;
	var sumSendCargoAfter = 0;
	var sumAgent = 0;
	var sumAgentAfter = 0;
	//计算合计
	$("[id^='span_cargoNumber_']").each(function (){
		sumCargoNumber+=Number($(this).text());
	});
	$("[id^='span_costCarryTransinsur_']").each(function (){
		sumTransinser+=Number($(this).text());
	});
	$("[id^='span_costCarryTransinsurAfter_']").each(function (){
		sumTransinserAfter+=Number($(this).text());
	});
	$("[id^='span_costCarrySendcargo_']").each(function (){
		sumSendCargo+=Number($(this).text());
	});
	$("[id^='span_costCarrySendcargoAfter_']").each(function (){
		sumSendCargoAfter+=Number($(this).text());
	});
	$("[id^='span_costCarryAgentAll_']").each(function (){
		sumAgent+=Number($(this).text());
	});
	$("[id^='span_costCarryAgentAfter_']").each(function (){
		sumAgentAfter+=Number($(this).text());
	});
	$("#span_sumCargoNumber").text(sumCargoNumber);
	$("#span_sumTransinser").text(sumTransinser);
	$("#span_sumTransinserAfter").text(sumTransinserAfter);
	$("#span_sumSendCargo").text(sumSendCargo);
	$("#span_sumSendCargoAfter").text(sumSendCargoAfter);
	$("#span_sumAgent").text(sumAgent);
	$("#span_sumAgentAfter").text(sumAgentAfter);
}