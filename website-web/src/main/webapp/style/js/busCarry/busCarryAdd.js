$(document).ready(function(){
	//jessrun.loadCSS("${pageContext.request.contextPath}/style/css/calendar_blue.css");
	//表格高亮
	$(".mod_table_bd").tableUI();

	$("#hid_tr").val(Number(i));

	//目的地不为空 处理为中转
	$("#txt_carryDestination").change(function(){
		if($("#txt_carryDestination").val()!="" && $("#ddl_carryModeId").val()=="1"){
			$("#ddl_carryModeId").val("2");
			$("#isCheckType").addClass("{required:true}");
			$("#isCheckType").attr("value","");
			$("#isCheckType").attr("checked","checked");
		}
		if($("#txt_carryDestination").val()=="" && $("#ddl_carryModeId").val()=="2"){
			$("#ddl_carryModeId").val("1");
			$("#isCheckType").removeClass("{required:true}");
			$("#isCheckType").attr("value","1");
			$("#isCheckType").attr("checked","");
		}
	});

	$("#ddl_carryModeId").change(function(){
		if($("#ddl_carryModeId").val()=="1"){
			$("#ddl_carryStateEndId").addClass("{required:true}");
			$("#ddl_carryDeptEndId").addClass("{required:true}");
			//$("#ddl_carryStateEndId").removeAttr("disabled");
			//$("#ddl_carryDeptEndId").removeAttr("disabled")
			$("#isCheckType").removeClass("{required:true}");
			$("#isCheckType").attr("value","1");
			$("#isCheckType").attr("checked","");
			$("#th_end_state").show();
			$("#td_end_state").show();
			$("#th_end_dept").show();
			$("#td_end_dept").show();
			//$("#txt_customerEndName").attr("disabled","disabled");
			//$("#txt_customerEndLinkmanName").attr("disabled","disabled");
			//$("#txt_carryCustomerEndLinkmanTel").attr("disabled","disabled");
			//$("#txt_carryCustomerEndLinkmanAdd").attr("disabled","disabled");
			//$("#txt_customerEndName").val("");
			//$("#txt_customerEndLinkmanName").attr("");
			//$("#txt_carryCustomerEndLinkmanTel").attr("");
			//$("#txt_carryCustomerEndLinkmanAdd").attr("");
			//$("#selectCustomerEndLinkmanName").hide();
		}
		if($("#ddl_carryModeId").val()=="2"){
			$("#ddl_carryStateEndId").addClass("{required:true}");
			$("#ddl_carryDeptEndId").addClass("{required:true}");
			//$("#ddl_carryStateEndId").removeAttr("disabled");
			//$("#ddl_carryDeptEndId").removeAttr("disabled");
			$("#isCheckType").addClass("{required:true}");
			//$("#txt_carryDestination").parent().next().show();
			$("#isCheckType").attr("value","");
			$("#isCheckType").attr("checked","checked");
			$("#th_end_state").show();
			$("#td_end_state").show();
			$("#th_end_dept").show();
			$("#td_end_dept").show();
			
			//$("#txt_customerEndName").attr("disabled","disabled");
			//$("#txt_customerEndLinkmanName").attr("disabled","disabled");
			//$("#txt_carryCustomerEndLinkmanTel").attr("disabled","disabled");
			//$("#txt_carryCustomerEndLinkmanAdd").attr("disabled","disabled");
			//$("#txt_customerEndName").val("");
			//$("#txt_customerEndLinkmanName").attr("");
			//$("#txt_carryCustomerEndLinkmanTel").attr("");
			//$("#txt_carryCustomerEndLinkmanAdd").attr("");
			//$("#selectCustomerEndLinkmanName").hide();
		}

		if($("#ddl_carryModeId").val()=="3")
		{
			$("#ddl_carryStateEndId").removeClass("{required:true}");
			$("#ddl_carryDeptEndId").removeClass("{required:true}");
			//$("#ddl_carryStateEndId").attr("disabled","disabled");
			//$("#ddl_carryDeptEndId").attr("disabled","disabled");
			$("#isCheckType").attr("value","");
			$("#isCheckType").attr("checked","checked");
			$("#isCheckType").addClass("{required:true}");
			//$("#txt_carryDestination").parent().next().show();
			$("#th_end_state").hide();
			$("#td_end_state").hide();
			$("#th_end_dept").hide();
			$("#td_end_dept").hide();
			
			//	$("#txt_customerEndName").removeAttr("disabled");
			//	$("#txt_customerEndLinkmanName").removeAttr("disabled");
			//	$("#txt_carryCustomerEndLinkmanTel").removeAttr("disabled");
			//	$("#txt_carryCustomerEndLinkmanAdd").removeAttr("disabled");
			//	$("#selectCustomerEndLinkmanName").show();

		}

	});



	//运保费锁定
	carryTransinsurTypeIdChanged(1);
	//运保费
	$("#txt_costCarryTransinsur").attr({disabled:'disabled'});
	//代收款锁定
	carryAgentTypeIdChanged(1);
	//额外费用锁定
	carrySendcargoTypeChange(1);
	//信息费用锁定
	carryMessageTypeChange(1);
	//返款费用锁定
	carryReturnTypeChange(1);

	//合计部分不能编辑
	$("input[id$='Total']").each(function(){
		$(this).attr({disabled:'disabled'});
	});

	//运保费收款方式改变
	$("#ddl_carryTransinsurTypeId").change(function(){
		carryTransinsurTypeIdChanged();
	});

	//代收款付款方式改变
	$("#ddl_carryAgentTypeId").change(function(){
		carryAgentTypeIdChanged();
	});

	//当代收款改变
	$("#txt_costCarryAgent").change(function(){
		checkMoney($("#txt_costCarryAgent"));
		carryAgentTypeIdChanged();
	});

	//当返中转费改变
	$("#txt_costCarryTransfer").change(function(){
		checkMoney($("#txt_costCarryTransfer"));
		carryAgentTypeIdChanged();
	});

	//额外费用改变
	$("#txt_costCarrySendcargo").change(function(){
		checkMoney($("#txt_costCarrySendcargo"));
		$("#ddl_carrySendcargoGettypeId").change();
	});

	//信息费改变
	$("#txt_costCarryMessage").change(function(){
		checkMoney($("#txt_costCarryMessage"));
		$("#ddl_carryMessageTypeId").change();
	});

	//返款改变
	$("#txt_costCarryReturn").change(function(){
		checkMoney($("#txt_costCarryReturn"));
		$("#ddl_carryReturnTypeId").change();
	});

	jessrun.formCarryAdd("carryAddBtns","carryAddForm","loadFormSubmit","数据保存中");

	$("#ddl_carrySendcargoGettypeId").change(function(){
		carrySendcargoTypeChange();
	});

	$("#ddl_carryMessageTypeId").change(function(){
		carryMessageTypeChange();
	});

	$("#ddl_carryReturnTypeId").change(function(){
		carryReturnTypeChange();
	});

	//运保费现收款更改
	$("#txt_costCarryTransinsurCur").change(function(){
		var sum = Number($("#txt_costCarryTransinsur").val());
		//取改变之前的值
		var reduce = $("#txt_costCarryAgentReduce").val();
		checkMoney($("#txt_costCarryTransinsurCur"));
		if(Number($("#txt_costCarryTransinsurCur").val())>=sum)
		{
			$("#txt_costCarryTransinsurCur").val(sum);
			$("#txt_costCarryTransinsurReturn").val("0");
			$("#txt_costCarryTransinsurAfter").val("0");
			$("#txt_costCarryTransinsurMonth").val("0");
			$("#txt_costCarryAgentReduce").val("0");
			$("#txt_costCarryTransinsurDriver").val("0");
		}
		else
		{
			$("#txt_costCarryTransinsurReturn").val(sum-Number($("#txt_costCarryTransinsurCur").val()));
			$("#txt_costCarryTransinsurAfter").val("0");
			$("#txt_costCarryTransinsurMonth").val("0");
			$("#txt_costCarryAgentReduce").val("0");
			$("#txt_costCarryTransinsurDriver").val("0");
			checkMoney($("#txt_costCarryTransinsurReturn"));
		}
		//如果改变了运从代扣的值，则更新代收款付款
		if(Number($("#txt_costCarryAgentReduce").val())!=Number(reduce))
			carryAgentTypeIdChanged();
	});

	//运保费回收款更改
	$("#txt_costCarryTransinsurReturn").change(function(){
		var sum = Number($("#txt_costCarryTransinsur").val())
				- Number($("#txt_costCarryTransinsurCur").val());
		//取改变之前的值
		var reduce = $("#txt_costCarryAgentReduce").val();
		checkMoney($("#txt_costCarryTransinsurReturn"));
		if(Number($("#txt_costCarryTransinsurReturn").val())>=sum)
		{
			$("#txt_costCarryTransinsurReturn").val(sum);
			$("#txt_costCarryTransinsurAfter").val("0");
			$("#txt_costCarryTransinsurMonth").val("0");
			$("#txt_costCarryAgentReduce").val("0");
			$("#txt_costCarryTransinsurDriver").val("0");
		}
		else
		{
			$("#txt_costCarryTransinsurAfter").val(sum-Number($("#txt_costCarryTransinsurReturn").val()));
			$("#txt_costCarryAgentReduce").val("0");
			$("#txt_costCarryTransinsurDriver").val("0");
			$("#txt_costCarryTransinsurMonth").val("0");
			checkMoney($("#txt_costCarryTransinsurAfter"));
		}
		//如果改变了运从代扣的值，则更新代收款付款
		if(Number($("#txt_costCarryAgentReduce").val())!=Number(reduce))
			carryAgentTypeIdChanged();
	});

	//运保费提收款更改
	$("#txt_costCarryTransinsurAfter").change(function(){
		var sum = Number($("#txt_costCarryTransinsur").val())
				- Number($("#txt_costCarryTransinsurCur").val())
				- Number($("#txt_costCarryTransinsurReturn").val());
		checkMoney($("#txt_costCarryTransinsurAfter"));
		if(Number($("#txt_costCarryTransinsurAfter").val())>=sum)
		{
			$("#txt_costCarryTransinsurAfter").val(sum);
			$("#txt_costCarryTransinsurMonth").val("0");
			$("#txt_costCarryAgentReduce").val("0");
			$("#txt_costCarryTransinsurDriver").val("0");
		}
		else
		{
			$("#txt_costCarryTransinsurMonth").val(sum-Number($("#txt_costCarryTransinsurAfter").val()));
			$("#txt_costCarryAgentReduce").val("0");
			$("#txt_costCarryTransinsurDriver").val("0");
			checkMoney($("#txt_costCarryTransinsurMonth"));
		}
		//如果改变了运从代扣的值，则更新代收款付款
		if(Number($("#txt_costCarryAgentReduce").val())!=Number(reduce))
			carryAgentTypeIdChanged();
	});

	//运保费月结更改
	$("#txt_costCarryTransinsurMonth").change(function(){
		var sum = Number($("#txt_costCarryTransinsur").val())
				- Number($("#txt_costCarryTransinsurCur").val())
				- Number($("#txt_costCarryTransinsurReturn").val())
				- Number($("#txt_costCarryTransinsurAfter").val());
		//取改变之前的值
		var reduce = $("#txt_costCarryAgentReduce").val();
		checkMoney($("#txt_costCarryTransinsurMonth"));
		if(Number($("#txt_costCarryTransinsurMonth").val())>=sum)
		{
			$("#txt_costCarryTransinsurMonth").val(sum);
			$("#txt_costCarryAgentReduce").val("0");
			$("#txt_costCarryTransinsurDriver").val("0");
		}
		else
		{
			$("#txt_costCarryAgentReduce").val(sum-Number($("#txt_costCarryTransinsurMonth").val()));
			$("#txt_costCarryTransinsurDriver").val("0");
			checkMoney($("#txt_costCarryAgentReduce"));
		}
		//如果改变了运从代扣的值，则更新代收款付款
		if(Number($("#txt_costCarryAgentReduce").val())!=Number(reduce))
			carryAgentTypeIdChanged();
	});

	//运保费运从代扣更改
	$("#txt_costCarryAgentReduce").change(function(){
		var sum = Number($("#txt_costCarryTransinsur").val())
				- Number($("#txt_costCarryTransinsurCur").val())
				- Number($("#txt_costCarryTransinsurReturn").val())
				- Number($("#txt_costCarryTransinsurAfter").val())
				- Number($("#txt_costCarryTransinsurMonth").val());
		//取改变之前的值
		var reduce = $("#txt_costCarryAgentReduce").val();
		checkMoney($("#txt_costCarryAgentReduce"));
		if(Number($("#txt_costCarryAgentReduce").val())>=sum)
		{
			$("#txt_costCarryAgentReduce").val(sum);
			$("#txt_costCarryTransinsurDriver").val("0");
		}
		else
		{
			//$("#txt_costCarryTransinsurDriver").val(sum-Number($("#txt_costCarryAgentReduce").val()));
			$("#txt_costCarryAgentReduce").val(sum);
			checkMoney($("#txt_costCarryAgentReduce"));
		}
		//如果改变了运从代扣的值，则更新代收款付款
		if(Number($("#txt_costCarryAgentReduce").val())!=Number(reduce))
			carryAgentTypeIdChanged();
	});

	//运保费司机代收更改
	$("#txt_costCarryTransinsurDriver").change(function(){
		var sum = Number($("#txt_costCarryTransinsur").val())
				- Number($("#txt_costCarryTransinsurCur").val())
				- Number($("#txt_costCarryTransinsurReturn").val())
				- Number($("#txt_costCarryTransinsurAfter").val())
				- Number($("#txt_costCarryTransinsurMonth").val())
				- Number($("#txt_costCarryAgentReduce").val());
		//取改变之前的值
		var reduce = $("#txt_costCarryAgentReduce").val();
		$("#txt_costCarryTransinsurDriver").val(sum);
		checkMoney($("#txt_costCarryTransinsurDriver"));
		//如果改变了运从代扣的值，则更新代收款付款
		if(Number($("#txt_costCarryAgentReduce").val())!=Number(reduce))
			carryAgentTypeIdChanged();
	});

	//代收款现付更改
	$("#txt_costCarryAgentCur").change(function(){
		var sum = Number($("#txt_costCarryAgent").val())
				+ Number($("#txt_costCarryTransfer").val())
				- Number($("#txt_costCarryAgentReduce").val());
		checkMoney($("#txt_costCarryAgentCur"));
		if(Number($("#txt_costCarryAgentCur").val())>=sum)
		{
			$("#txt_costCarryAgentCur").val(sum);
			$("#txt_costCarryAgentReturn").val("0");
		}
		else
		{
			$("#txt_costCarryAgentReturn").val(sum-Number($("#txt_costCarryAgentCur").val()));
			checkMoney($("#txt_costCarryAgentReturn"));
		}
	});

	//代收款回付更改
	$("#txt_costCarryAgentReturn").change(function(){
		var sum = Number($("#txt_costCarryAgent").val())
				+ Number($("#txt_costCarryTransfer").val())
				- Number($("#txt_costCarryAgentReduce").val());
		checkMoney($("#txt_costCarryAgentReturn"));
		if(Number($("#txt_costCarryAgentReturn").val())>=sum)
		{
			$("#txt_costCarryAgentReturn").val(sum);
			$("#txt_costCarryAgentCur").val("0");
		}
		else
		{
			$("#txt_costCarryAgentCur").val(sum-Number($("#txt_costCarryAgentReturn").val()));
			checkMoney($("#txt_costCarryAgentCur"));
		}
	});

	//额外费用现收款更改
	$("#txt_costCarrySendcargoCur").change(function(){
		var sum = Number($("#txt_costCarrySendcargo").val());
		checkMoney($("#txt_costCarrySendcargoCur"));
		if(Number($("#txt_costCarrySendcargoCur").val())>=sum)
		{
			$("#txt_costCarrySendcargoCur").val(sum);
			$("#txt_costCarrySendcargoReturn").val("0");
			$("#txt_costCarrySendcargoAfter").val("0");
		}
		else
		{
			$("#txt_costCarrySendcargoReturn").val(sum-Number($("#txt_costCarrySendcargoCur").val()));
			$("#txt_costCarrySendcargoAfter").val("0");
			checkMoney($("#txt_costCarrySendcargoReturn"));
		}
	});

	//额外费用回收款更改
	$("#txt_costCarrySendcargoReturn").change(function(){
		var sum = Number($("#txt_costCarrySendcargo").val())
				- Number($("#txt_costCarrySendcargoCur").val());
		checkMoney($("#txt_costCarrySendcargoReturn"));
		if(Number($("#txt_costCarrySendcargoReturn").val())>=sum)
		{
			$("#txt_costCarrySendcargoReturn").val(sum);
			$("#txt_costCarrySendcargoAfter").val("0");
		}
		else
		{
			$("#txt_costCarrySendcargoAfter").val(sum-Number($("#txt_costCarrySendcargoReturn").val()));
		}
	});

	//额外费用回收款更改
	$("#txt_costCarrySendcargoAfter").change(function(){
		var sum = Number($("#txt_costCarrySendcargo").val())
				- Number($("#txt_costCarrySendcargoCur").val())
				- Number($("#txt_costCarrySendcargoReturn").val());
		//checkMoney($("#txt_costCarrySendcargoAfter"));
		$("#txt_costCarrySendcargoAfter").val(sum);
	});

	//信息费现付款更改
	$("#txt_costCarryMessageCur").change(function(){
		var sum = Number($("#txt_costCarryMessage").val());
		checkMoney($("#txt_costCarryMessageCur"));
		if(Number($("#txt_costCarryMessageCur").val())>=sum)
		{
			$("#txt_costCarryMessageCur").val(sum);
			$("#txt_costCarryMessageReturn").val("0");
		}
		else
		{
			$("#txt_costCarryMessageReturn").val(sum-Number($("#txt_costCarryMessageCur").val()));
			checkMoney($("#txt_costCarryMessageReturn"));
		}
	});

	//信息费回付款更改
	$("#txt_costCarryMessageReturn").change(function(){
		var sum = Number($("#txt_costCarryMessage").val());
		checkMoney($("#txt_costCarryMessageReturn"));
		if(Number($("#txt_costCarryMessageReturn").val())>=sum)
		{
			$("#txt_costCarryMessageReturn").val(sum);
			$("#txt_costCarryMessageCur").val("0");
		}
		else
		{
			$("#txt_costCarryMessageCur").val(sum-Number($("#txt_costCarryMessageReturn").val()));
			checkMoney($("#txt_costCarryMessageCur"));
		}
	});

	//返款现付款更改
	$("#txt_costCarryReturnCur").change(function(){
		var sum = Number($("#txt_costCarryReturn").val());
		checkMoney($("#txt_costCarryReturnCur"));
		if(Number($("#txt_costCarryReturnCur").val())>=sum)
		{
			$("#txt_costCarryReturnCur").val(sum);
			$("#txt_costCarryReturnReturn").val("0");
		}
		else
		{
			$("#txt_costCarryReturnReturn").val(sum-Number($("#txt_costCarryReturnCur").val()));
			checkMoney($("#txt_costCarryReturnReturn"));
		}
	});

	//返款回付款更改
	$("#txt_costCarryReturnReturn").change(function(){
		var sum = Number($("#txt_costCarryReturn").val());
		checkMoney($("#txt_costCarryReturnReturn"));
		if(Number($("#txt_costCarryReturnReturn").val())>=sum)
		{
			$("#txt_costCarryReturnReturn").val(sum);
			$("#txt_costCarryReturnCur").val("0");
		}
		else
		{
			$("#txt_costCarryReturnCur").val(sum-Number($("#txt_costCarryReturnReturn").val()));
			checkMoney($("#txt_costCarryReturnCur"));
		}
	});
	
	getSum();
});

function getSum(){
	var sumCargoNumber = 0;
	$("input[id^='txt_cargoNumber_']").each(function() {
		sumCargoNumber += Number($(this).val());
	});
	$("#span_cargoNumber").text(sumCargoNumber);

	var sumNowGet = 0;
	$("input[id^='txt_cargoWeight_']").each(function() {
		sumNowGet += Number($(this).val());
	});
	$("#span_cargoWeight").text(sumNowGet.toFixed(2));

	var sumcargoVolumeGet = 0;
	$("input[id^='txt_cargoVolume_']").each(function() {
		sumcargoVolumeGet += Number($(this).val());
	});
	$("#span_cargoVolume").text(sumcargoVolumeGet.toFixed(2));

	var sumcargoCostsTransportGet = 0;
	$("input[id^='txt_cargoCostsTransport_']").each(function() {
		sumcargoCostsTransportGet += Number($(this).val());
	});
	$("#span_cargoCostTransport").text(sumcargoCostsTransportGet.toFixed(2));

	var sumcargoInsuranceCredit = 0;
	$("input[id^='txt_cargoInsuranceCredit_']").each(function() {
		sumcargoInsuranceCredit += Number($(this).val());
	});
	$("#span_costCarryInsuranceCredit")
			.text(sumcargoInsuranceCredit.toFixed(2));

	var sumcargoCostsInsurance = 0;
	$("input[id^='txt_cargoCostsInsurance_']").each(function() {
		sumcargoCostsInsurance += Number($(this).val());
	});
	$("#span_costCarryInsurance").text(sumcargoCostsInsurance.toFixed(2));
}

var i = 0;

jessrun.formCarryAdd=function(btnId,formId,loadClass,loadHtml){
	$("#"+btnId).click(function(){

		if($("#tbl_carryList tr").length == 0){
			alert("请输入货物信息！");
			return false;
		}
		
		//检查代收款是否大于等于运从代扣
		if(Number($("#txt_costCarryAgent").val())
			+ Number($("#txt_costCarryTransfer").val())
			- Number($("#txt_costCarryAgentReduce").val())<0)
		{
			alert("代收款总额不能小于运从代扣！");
			$("#txt_costCarryAgent").focus();
			return false;
		}
		//判断重单(承运单号/货物人都相等为重单)
		var carryNames = $("#txt_carryName").val();
		$.ajax({
			type:'POST',
			url:'assertExsited.action',
			data:'carryName='+carryNames,
			cache:false,
			success:function(data){
				var result = eval("("+data+")");
				if(result.success == true){
					alert($("#txt_carryName").val()+"号承运单已存在,请重新输入承运单号");
					$("#btn_sys_submit").show();
					return false;
				}else{
					if ($("#"+formId).valid()) {
						$("#"+btnId).hide();
						$("<span class='"+loadClass+"'><span><em>"+loadHtml+"</em></span></span>").insertAfter("#"+btnId);
						function doSubmit(){

							replaceBusHiddenField();
							replaceCostHiddenField();
							$("#"+formId).submit();
							}
						setTimeout(doSubmit,0);
					}
				}
			}
		});

		

	});
};


function replaceCostHiddenField(){
	$("#hid_costCarryTransinsur").val($("#txt_costCarryTransinsur").val());
	$("#hid_costCarryTransinsurCur").val($("#txt_costCarryTransinsurCur").val());
	$("#hid_costCarryTransinsurReturn").val($("#txt_costCarryTransinsurReturn").val());
	$("#hid_costCarryTransinsurAfter").val($("#txt_costCarryTransinsurAfter").val());
	$("#hid_costCarryTransinsurDriver").val($("#txt_costCarryTransinsurDriver").val());
	$("#hid_costCarryTransinsurMonth").val($("#txt_costCarryTransinsurMonth").val());
	$("#hid_costCarryAgentReduce").val($("#txt_costCarryAgentReduce").val());
	$("#hid_costCarryAgentAll").val($("#txt_costCarryAgentAll").val());
	$("#hid_carryTransinsurTypeName").val($("#ddl_carryTransinsurTypeId").find("option:selected").text());
	$("#hid_carryAgentTypeName").val($("#ddl_carryAgentTypeId").find("option:selected").text());
	$("#hid_carrySendcargoGettypeName").val($("#ddl_carrySendcargoGettypeId").find("option:selected").text());
	$("#hid_carryMessageTypeName").val($("#ddl_carryMessageTypeId").find("option:selected").text());
	$("#hid_costCarryAgentCur").val($("#txt_costCarryAgentCur").val());
	$("#hid_costCarryAgentReturn").val($("#txt_costCarryAgentReturn").val());
	$("#hid_costCarrySendcargoCur").val($("#txt_costCarrySendcargoCur").val());
	$("#hid_costCarrySendcargoReturn").val($("#txt_costCarrySendcargoReturn").val());
	$("#hid_costCarrySendcargoAfter").val($("#txt_costCarrySendcargoAfter").val());
	$("#hid_costCarryMessageCur").val($("#txt_costCarryMessageCur").val());
	$("#hid_costCarryMessageReturn").val($("#txt_costCarryMessageReturn").val());
	$("#hid_costCarryReturnCur").val($("#txt_costCarryReturnCur").val());
	$("#hid_costCarryReturnReturn").val($("#txt_costCarryReturnReturn").val());
	$("#hid_costCarryTransport").val($("#span_cargoCostTransport").text());
	$("#hid_costCarryInsuranceCredit").val($("#span_costCarryInsuranceCredit").text());
	$("#hid_costCarryInsurance").val($("#span_costCarryInsurance").text());
	$("#hid_carryReturnTypeName").val($("#ddl_carryReturnTypeId").find("option:selected").text());
}

function replaceBusHiddenField(){
	$("#hid_carryStateStartName").val($("#ddl_stateStart").find("option:selected").text());
	$("#hid_depotName").val($("#ddl_depotId").find("option:selected").text());
	$("#hid_carryDeptStartName").val($("#ddl_carryDeptStartId").find("option:selected").text());
	$("#hid_carryStaffStartName").val($("#ddl_carryStaffStartId").find("option:selected").text());
	$("#hid_carryStateEndName").val($("#ddl_carryStateEndId").find("option:selected").text());
	$("#hid_carryDeptEndName").val($("#ddl_carryDeptEndId").find("option:selected").text());
	$("#hid_carrySendModeName").val($("#ddl_carrySendModeId").find("option:selected").text());
	$("#hid_carryModeName").val($("#ddl_carryModeId").find("option:selected").text());
}

function carryAgentTypeIdChanged(edit){
	var iCarryAgentTypeId = $("#ddl_carryAgentTypeId").val();
	var sum = Number($("#txt_costCarryAgent").val())
			+ Number($("#txt_costCarryTransfer").val())
			- Number($("#txt_costCarryAgentReduce").val());
	switch(iCarryAgentTypeId){
	case '1':
		$("#txt_costCarryAgentCur").val(sum);
		$("#txt_costCarryAgentReturn").val("0");
		$("#txt_costCarryAgentCur").attr("disabled", true);
		$("#txt_costCarryAgentReturn").attr("disabled", true);
		break;
	case '2':
		$("#txt_costCarryAgentCur").val("0");
		$("#txt_costCarryAgentReturn").val(sum);
		$("#txt_costCarryAgentCur").attr("disabled", true);
		$("#txt_costCarryAgentReturn").attr("disabled", true);
		break;
	case '3':
		if(edit==undefined)
		{
			$("#txt_costCarryAgentCur").val(sum);
			$("#txt_costCarryAgentReturn").val("0");
		}
		$("#txt_costCarryAgentCur").attr("disabled", false);
		$("#txt_costCarryAgentReturn").attr("disabled", false);
	}
}

//运保费付款方式改变事件
function carryTransinsurTypeIdChanged(edit){

	var iCarryTransinsurTypeId = $("#ddl_carryTransinsurTypeId").val();
	var sum = Number($("#txt_costCarryTransinsur").val());
	//取改变之前的运从代扣金额
	var reduce = $("#txt_costCarryAgentReduce").val();
	switch(iCarryTransinsurTypeId){
	case '1':
		$("#txt_costCarryTransinsurCur").val(sum);
		$("#txt_costCarryTransinsurReturn").val("0");
		$("#txt_costCarryAgentReduce").val("0");
		$("#txt_costCarryTransinsurAfter").val("0");
		$("#txt_costCarryTransinsurDriver").val("0");
		$("#txt_costCarryTransinsurMonth").val("0");
		$("#txt_costCarryTransinsurCur").attr("disabled",true);
		$("#txt_costCarryTransinsurReturn").attr("disabled",true);
		$("#txt_costCarryTransinsurAfter").attr("disabled",true);
		$("#txt_costCarryTransinsurDriver").attr("disabled",true);
		$("#txt_costCarryAgentReduce").attr("disabled",true);
		$("#txt_costCarryTransinsurMonth").attr("disabled",true);
		break;
	case '2':
		$("#txt_costCarryTransinsurCur").val("0");
		$("#txt_costCarryTransinsurReturn").val(sum);
		$("#txt_costCarryAgentReduce").val("0");
		$("#txt_costCarryTransinsurAfter").val("0");
		$("#txt_costCarryTransinsurDriver").val("0");
		$("#txt_costCarryTransinsurMonth").val("0");
		$("#txt_costCarryTransinsurCur").attr("disabled",true);
		$("#txt_costCarryTransinsurReturn").attr("disabled",true);
		$("#txt_costCarryTransinsurAfter").attr("disabled",true);
		$("#txt_costCarryTransinsurDriver").attr("disabled",true);
		$("#txt_costCarryAgentReduce").attr("disabled",true);
		$("#txt_costCarryTransinsurMonth").attr("disabled",true);
		break;
	case '3':
		$("#txt_costCarryTransinsurCur").val("0");
		$("#txt_costCarryTransinsurReturn").val("0");
		$("#txt_costCarryTransinsurAfter").val(sum);
		$("#txt_costCarryAgentReduce").val("0");
		$("#txt_costCarryTransinsurMonth").val("0");
		$("#txt_costCarryTransinsurDriver").val("0");
		$("#txt_costCarryTransinsurCur").attr("disabled",true);
		$("#txt_costCarryTransinsurReturn").attr("disabled",true);
		$("#txt_costCarryTransinsurAfter").attr("disabled",true);
		$("#txt_costCarryTransinsurDriver").attr("disabled",true);
		$("#txt_costCarryAgentReduce").attr("disabled",true);
		$("#txt_costCarryTransinsurMonth").attr("disabled",true);
		break;
	case '4':
		$("#txt_costCarryTransinsurCur").val("0");
		$("#txt_costCarryTransinsurReturn").val("0");
		$("#txt_costCarryTransinsurAfter").val("0");
		$("#txt_costCarryAgentReduce").val("0");
		$("#txt_costCarryTransinsurMonth").val(sum);
		$("#txt_costCarryTransinsurDriver").val("0");
		$("#txt_costCarryTransinsurCur").attr("disabled",true);
		$("#txt_costCarryTransinsurReturn").attr("disabled",true);
		$("#txt_costCarryTransinsurAfter").attr("disabled",true);
		$("#txt_costCarryTransinsurDriver").attr("disabled",true);
		$("#txt_costCarryAgentReduce").attr("disabled",true);
		$("#txt_costCarryTransinsurMonth").attr("disabled",true);
		break;
	case '5':
		$("#txt_costCarryTransinsurCur").val("0");
		$("#txt_costCarryTransinsurReturn").val("0");
		$("#txt_costCarryAgentReduce").val("0");
		$("#txt_costCarryTransinsurAfter").val("0");
		$("#txt_costCarryTransinsurDriver").val(sum);
		$("#txt_costCarryTransinsurMonth").val("0");
		$("#txt_costCarryTransinsurCur").attr("disabled",true);
		$("#txt_costCarryTransinsurReturn").attr("disabled",true);
		$("#txt_costCarryTransinsurAfter").attr("disabled",true);
		$("#txt_costCarryTransinsurDriver").attr("disabled",true);
		$("#txt_costCarryAgentReduce").attr("disabled",true);
		$("#txt_costCarryTransinsurMonth").attr("disabled",true);
		break;
	case '6':
		$("#txt_costCarryTransinsurCur").val("0");
		$("#txt_costCarryTransinsurReturn").val("0");
		$("#txt_costCarryTransinsurMonth").val("0");
		$("#txt_costCarryTransinsurAfter").val("0");
		$("#txt_costCarryTransinsurDriver").val("0");
		$("#txt_costCarryAgentReduce").val(sum);
		$("#txt_costCarryTransinsurCur").attr("disabled",true);
		$("#txt_costCarryTransinsurReturn").attr("disabled",true);
		$("#txt_costCarryTransinsurAfter").attr("disabled",true);
		$("#txt_costCarryTransinsurDriver").attr("disabled",true);
		$("#txt_costCarryAgentReduce").attr("disabled",true);
		$("#txt_costCarryTransinsurMonth").attr("disabled",true);
		break;
	case '7':
		if(edit==undefined)
		{
			$("#txt_costCarryTransinsurCur").val(sum);
			$("#txt_costCarryTransinsurReturn").val("0");
			$("#txt_costCarryAgentReduce").val("0");
			$("#txt_costCarryTransinsurAfter").val("0");
			$("#txt_costCarryTransinsurDriver").val("0");
			$("#txt_costCarryTransinsurMonth").val("0");
		}
		$("#txt_costCarryTransinsurCur").attr("disabled",false);
		$("#txt_costCarryTransinsurReturn").attr("disabled",false);
		$("#txt_costCarryTransinsurAfter").attr("disabled",false);
		$("#txt_costCarryTransinsurDriver").attr("disabled",false);
		$("#txt_costCarryAgentReduce").attr("disabled",false);
		$("#txt_costCarryTransinsurMonth").attr("disabled",false);
		break;
	}
	//如果改变了运从代扣的值，则更新代收款付款
	if(Number($("#txt_costCarryAgentReduce").val())!=Number(reduce))
		carryAgentTypeIdChanged();
}

function carrySendcargoTypeChange(edit)
{
	var carrySendCargoTypeId = $("#ddl_carrySendcargoGettypeId").val();
	var sum = Number($("#txt_costCarrySendcargo").val());
	switch(carrySendCargoTypeId){
	case '1':
		$("#txt_costCarrySendcargoCur").val(sum);
		$("#txt_costCarrySendcargoReturn").val("0");
		$("#txt_costCarrySendcargoAfter").val("0");
		$("#txt_costCarrySendcargoCur").attr("disabled", true);
		$("#txt_costCarrySendcargoReturn").attr("disabled", true);
		$("#txt_costCarrySendcargoAfter").attr("disabled", true);
		break;
	case '2':
		$("#txt_costCarrySendcargoCur").val("0");
		$("#txt_costCarrySendcargoReturn").val(sum);
		$("#txt_costCarrySendcargoAfter").val("0");
		$("#txt_costCarrySendcargoCur").attr("disabled", true);
		$("#txt_costCarrySendcargoReturn").attr("disabled", true);
		$("#txt_costCarrySendcargoAfter").attr("disabled", true);
		break;
	case '3':
		$("#txt_costCarrySendcargoCur").val("0");
		$("#txt_costCarrySendcargoReturn").val("0");
		$("#txt_costCarrySendcargoAfter").val(sum);
		$("#txt_costCarrySendcargoCur").attr("disabled", true);
		$("#txt_costCarrySendcargoReturn").attr("disabled", true);
		$("#txt_costCarrySendcargoAfter").attr("disabled", true);
		break;
	case '4':
		if(edit==undefined)
		{
			$("#txt_costCarrySendcargoCur").val(sum);
			$("#txt_costCarrySendcargoReturn").val("0");
			$("#txt_costCarrySendcargoAfter").val("0");
		}
		$("#txt_costCarrySendcargoCur").attr("disabled", false);
		$("#txt_costCarrySendcargoReturn").attr("disabled", false);
		$("#txt_costCarrySendcargoAfter").attr("disabled", false);
		break;
	}
}

function carryMessageTypeChange(edit)
{
	var carryMessageTypeId = $("#ddl_carryMessageTypeId").val();
	var sum = Number($("#txt_costCarryMessage").val());
	switch(carryMessageTypeId){
	case '1':
		$("#txt_costCarryMessageCur").val(sum);
		$("#txt_costCarryMessageReturn").val("0");
		$("#txt_costCarryMessageCur").attr("disabled", true);
		$("#txt_costCarryMessageReturn").attr("disabled", true);
		break;
	case '2':
		$("#txt_costCarryMessageCur").val("0");
		$("#txt_costCarryMessageReturn").val(sum);
		$("#txt_costCarryMessageCur").attr("disabled", true);
		$("#txt_costCarryMessageReturn").attr("disabled", true);
		break;
	case '3':
		if(edit==undefined)
		{
			$("#txt_costCarryMessageCur").val(sum);
			$("#txt_costCarryMessageReturn").val("0");
		}
		$("#txt_costCarryMessageCur").attr("disabled", false);
		$("#txt_costCarryMessageReturn").attr("disabled", false);
		break;
	}
}

function carryReturnTypeChange(edit)
{
	var carryReturnTypeId = $("#ddl_carryReturnTypeId").val();
	var sum = Number($("#txt_costCarryReturn").val());
	switch(carryReturnTypeId){
	case '1':
		$("#txt_costCarryReturnCur").val(sum);
		$("#txt_costCarryReturnReturn").val("0");
		$("#txt_costCarryReturnCur").attr("disabled", true);
		$("#txt_costCarryReturnReturn").attr("disabled", true);
		break;
	case '2':
		$("#txt_costCarryReturnCur").val("0");
		$("#txt_costCarryReturnReturn").val(sum);
		$("#txt_costCarryReturnCur").attr("disabled", true);
		$("#txt_costCarryReturnReturn").attr("disabled", true);
		break;
	case '3':
		if(edit==undefined)
		{
			$("#txt_costCarryReturnCur").val(sum);
			$("#txt_costCarryReturnReturn").val("0");
		}
		$("#txt_costCarryReturnCur").attr("disabled", false);
		$("#txt_costCarryReturnReturn").attr("disabled", false);
		break;
	}
}

function testCostCarryTransinsurEqualTypesValue(type){
	var iCostCarryTransinsurTypesValue = Number($("#txt_costCarryTransinsurReturn").val())
	+Number($("#txt_costCarryTransinsurAfter").val())
	+Number($("#txt_costCarryTransinsurDriver").val())
	+Number($("#txt_costCarryAgentReduce").val())
	+Number($("#txt_costCarryTransinsurMonth").val())
	+Number( $("#txt_costCarryTransinsurCur").val());

	var iCostCarryTransinsur = $("#txt_costCarryTransinsur").val();

	switch(type){
	case 1:
		if(Number(iCostCarryTransinsurTypesValue) != Number(iCostCarryTransinsur)){
			$.messager.alert("提示", "运保费各方式付款额度总和必须等于运保费！");
			return false;
		}
		break;
	case 2:
		if(Number(iCostCarryTransinsurTypesValue) > Number(iCostCarryTransinsur)){
			$.messager.alert("提示", "运保费各方式付款额度总和不可以大于运保费！");
			return false;
		}
		break;
	}

	return true;
}

//返回指定'yyyy-MM-dd'格式的日期
function returnNowDateAsString(){

	var dDate = new Date();
	var year = dDate.getFullYear();
	var month = dDate.getMonth()+1;
	var date = dDate.getDate();

	if(Number(date) < 10){
		date = '0'+date;
	}
	if(Number(month) < 10){
		month = '0'+month;
	}

	return year+'-'+month+"-"+date;
}

function clearCostCarryTransinsurValues(){
	$("#txt_costCarryTransinsurCur").val(0);
	$("#txt_costCarryTransinsurReturn").val(0);
	$("#txt_costCarryTransinsurAfter").val(0);
	$("#txt_costCarryTransinsurDriver").val(0);
	$("#txt_costCarryAgentReduce").val(0);
	$("#txt_costCarryTransinsurMonth").val(0);
}
function GetSumCargoNumber(id) {
	//验证整数
	checkInt($("#txt_cargoNumber_"+id));

	var sumCargoNumber = 0;
	$("input[id^='txt_cargoNumber_']").each(function() {
		sumCargoNumber += Number($(this).val());
	});
	$("#span_cargoNumber").text(sumCargoNumber);
};

function GetcargoWeightGet(id) {
	//验证两位小数
	checkMoney($("#txt_cargoWeight_"+id));
	var sumNowGet = 0;
	$("input[id^='txt_cargoWeight_']").each(function() {
		sumNowGet += Number($(this).val());
	});
	$("#span_cargoWeight").text(sumNowGet.toFixed(2));
};

function GetcargoVolumeGet(id) {
	//验证两位小数
	checkMoney($("#txt_cargoVolume_"+id));

	var sumcargoVolumeGet = 0;
	$("input[id^='txt_cargoVolume_']").each(function() {
		sumcargoVolumeGet += Number($(this).val());
	});
	$("#span_cargoVolume").text(sumcargoVolumeGet.toFixed(2));
}

function GetcargoCostsTransportGet(id) {
	//验证金额
	checkMoney($("#txt_cargoCostsTransport_"+id));
	var sumcargoCostsTransportGet = 0;
	$("input[id^='txt_cargoCostsTransport_']").each(function() {
		sumcargoCostsTransportGet += Number($(this).val());
	});
	$("#span_cargoCostTransport").text(sumcargoCostsTransportGet.toFixed(2));
	$("#hid_costCarryTransport").val(sumcargoCostsTransportGet.toFixed(2));
	$("#txt_cargoCostTransportTotal").change();
	//取运保费总额
	getTransinser();
};

function GetcargoInsuranceCredit(id) {
	//验证金额
	checkMoney($("#txt_cargoInsuranceCredit_"+id));
	var sumcargoInsuranceCredit = 0;
	$("input[id^='txt_cargoInsuranceCredit_']").each(function() {
		sumcargoInsuranceCredit += Number($(this).val());
	});
	$("#span_costCarryInsuranceCredit").text(sumcargoInsuranceCredit.toFixed(2));
	$("#hid_costCarryInsuranceCredit").val(sumcargoInsuranceCredit.toFixed(2));
};

function GetcargoCostsInsurance(id) {
	//验证金额
	checkMoney($("#txt_cargoCostsInsurance_"+id));
	var sumcargoCostsInsurance = 0;
	$("input[id^='txt_cargoCostsInsurance_']").each(function() {
		sumcargoCostsInsurance += Number($(this).val());
	});
	$("#span_costCarryInsurance").text(sumcargoCostsInsurance.toFixed(2));
	$("#hid_costCarryInsurance").val(sumcargoCostsInsurance.toFixed(2));
	//取运保费总额
	getTransinser();
}

function getTransinser()
{
	var sum=0;
	//运输费合计
	$("input[id^='txt_cargoCostsTransport_']").each(function() {
		sum += Number($(this).val());
	});
	//加保额
	$("input[id^='txt_cargoCostsInsurance_']").each(function() {
		sum += Number($(this).val());
	});
	$("#txt_costCarryTransinsur").val(sum);
	$("#hid_costCarryTransinsur").val(sum);
	carryTransinsurTypeIdChanged();
}

function checkTansinser()
{

}

function RemoveChild(i) {

	$("#tr_" + i).remove();
	getSum();
	//取运保费总额
	getTransinser();
}


//选择包装
jessrun.showCargoPackName = function(id){
	var sid=$(id).attr("relId");
	$("#tempId").attr("relId",sid);
	jessrun.Dialog({
		 boxID: "selectCargoPack",
         title: "包装名称列表",
         showbg:true,
         content: "id:dialogCargoPackName"

	});
};

//选中选择包装填入
jessrun.showCargoPackNameEnt = function(id){
	var sid =$("#tempId").attr("relId");
	jessrun.Dialog.removeBox();
	$("#"+sid).val($(id).attr("relName"));

};

//选择货物
jessrun.showCargoName = function(id){
	var sid=$(id).attr("relId");
	//$("#tempId_").attr("relId",sid);
	jessrun.Dialog({
         title: "货物名称列表",
         width:800,
         height:500,
         showbg:true,
         content:"iframe:"+jessrun.config.domain+"/bic/cargo/gotoAllBicCargo.action?sid="+sid
	});
};

//选择联系人 联系电话
jessrun.showContactName = function(id,customerName){
	var _customerName = $("#"+customerName).val();
	var sid=$(id).attr("relId");
	var smobile=$(id).attr("relMobile");
	var stateId=$(id).attr("relStateId");
	jessrun.Dialog({
         title: "选择联系人列表",
         width:700,
         height:500,
         showbg:true,
         content:"iframe:"+jessrun.config.domain+"/bic/custlinkmn/gotoLinkman.action?stateId="+stateId+"&customerName="+_customerName+"&sid="+sid+"&smobile="+smobile
	});
	
};





function AddChild() {

	var $obj = $(
			'<tr id="tr_' + i + '">'
			+"<td>"
			+'		<a id="btn_removeChild_' + i + '" trcount="' + i
			+ '"  href="javascript:;" onclick="javascript:RemoveChild(' + i
			+ ');" class="jessrun-btn"><span>删除</span></a>'
			+ "</td>"
			+ "<td>"
			+ '    <span><input id="txt_cargoNo_' + i + '" name="busCarryCargo[' + i
			+ '].cargoNo" type="text" maxlength="25"  class="jessrun-txt w60 {required:true}"/>'
			+ "</span></td>"
			+ "<td>"
			+ '<span><input id="txt_cargoPackName_' + i + '" name="busCarryCargo[' + i
			+ '].cargoPackName" type="text" maxlength="25"  class="jessrun-txt w60 {required:true}"/>'
			+ '</span></td>'
			+ "<td>"
			+ '    <span><input id="txt_cargoName_' + i + '" name="busCarryCargo[' + i
					+ '].cargoName" type="text" maxlength="25" class="jessrun-txt w60 {required:true}"/>'
			+ ' <a href="javascript:;" relId="txt_cargoName_' + i + '" onclick="jessrun.showCargoName(this)">选择</a></span></td>'

			+ "<td>"
			+ '    <span><input id="txt_cargoNumber_'
					+ i
					+ '" name="busCarryCargo['
					+ i
					+ '].cargoNumber" type="text" onchange="javascript:GetSumCargoNumber('+i+');" value="0" class="jessrun-txt w60 {required:true,min:1} w-price" maxlength="8" class="jessrun-txt w60"/>件'
			+ "</span></td>"

			+ "<td>"
			+ '    <span><input id="txt_cargoWeight_'
					+ i
					+ '" name="busCarryCargo['
					+ i
					+ '].cargoWeight" type="text" onchange="javascript:GetcargoWeightGet('+i+');" value="0" class="jessrun-txt w60 {required:true} w-price" maxlength="8" />t'
			+ "</span></td>"

			+ "<td>"
			+ '<span><input id="txt_cargoVolume_'
					+ i
					+ '" name="busCarryCargo['
					+ i
					+ '].cargoVolume" type="text" onchange="javascript:GetcargoVolumeGet('+i+');" value="0" class="jessrun-txt w60 {required:true} w-price" maxlength="8" />M<sup>3</sup>'
			+ "</span></td>"

			+ "<td>"
			+ '<span><input id="txt_cargoCostsTransport_'
					+ i
					+ '" name="busCarryCargo['
					+ i
					+ '].cargoCostsTransport" type="text" onchange="javascript:GetcargoCostsTransportGet('+i+');" value="0" class="jessrun-txt w60 {required:true} w-price" maxlength="8" />元'
			+ "</span></td>"

			+ "<td>"
			+ '<span><input id="txt_cargoInsuranceCredit_'
					+ i
					+ '" type="text" name="busCarryCargo['
					+ i
					+ '].cargoInsuranceCredit" onchange="javascript:GetcargoInsuranceCredit('+i+');" value="0" class="jessrun-txt w60 {required:true} w-price" maxlength="8" />元'
			+ "</span></td>"

			+ "<td>"
			+ '<span><input id="txt_cargoCostsInsurance_'
					+ i
					+ '" type="text" name="busCarryCargo['
					+ i
					+ '].cargoCostsInsurance" onchange="javascript:GetcargoCostsInsurance('+i+');" value="0" class="jessrun-txt w60 {required:true} w-price" maxlength="8"/>元'
			+ "</span></td>"

			+ "</tr>"
	);
	//alert($obj.html());
	$("#tbl_carryList").append($obj);
//alert("i:" + i)
		i++;
		$(".mod_table_bd").tableUI();

}

function testCostCarryAgentEqualTypesValue(type){
	var iCostCarryAgent = $("#txt_costCarryAgent").val();
	var iCostCarryTransfer = $("#txt_costCarryTransfer").val();
	var iCostCarryAgentCur = $("#txt_costCarryAgentCur").val();
	var iCostCarryAgentReturn = $("#txt_costCarryAgentReturn").val();


	switch(type){
	case 1:
		if((Number(iCostCarryAgent) + Number(iCostCarryTransfer)) != (Number(iCostCarryAgentCur) + Number(iCostCarryAgentReturn))){
			$.messager.alert("提示", "代收款现付回付额度之和必须等于代收款加返中转费的总额！");
			return false;
		}
	case 2:
		if((Number(iCostCarryAgent) + Number(iCostCarryTransfer)) < (Number(iCostCarryAgentCur) + Number(iCostCarryAgentReturn))){
			$.messager.alert("提示", "代收款现付回付额度之和不可以大于代收款加返中转费的总额！");
			return false;
		}
	}
	return true;
}