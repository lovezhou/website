$(document).ready(function () {
	
	var sumCargoNumber=0;
	$("[id^='span_cargoNumber_']").each(function(){
		sumCargoNumber += Number($(this).text());
	});
	$("#span_sumDepotCargo").text(sumCargoNumber);

	var sumLoadCargoNumber=0;
	$("[id^='span_loadCargoNumber_']").each(function(){
		sumLoadCargoNumber += Number($(this).text());
	});
	$("#span_sumLoadCargo").text(sumLoadCargoNumber);
	
	var sumCostCarryTransinsurCur=0;
	$("[id^='span_costCarryTransinsurCur_']").each(function(){
		sumCostCarryTransinsurCur += Number($(this).text());
	});
	$("#span_sumCostCarryTransinsurCur").text(sumCostCarryTransinsurCur);
	
	var sumCostCarryTransinsurAfter=0;
	$("[id^='span_costCarryTransinsurAfter_']").each(function(){
		sumCostCarryTransinsurAfter += Number($(this).text());
	});
	$("#span_sumCostCarryTransinsurAfter").text(sumCostCarryTransinsurAfter);
	
	var sumCostCarryTransinsurReturn=0;
	$("[id^='span_costCarryTransinsurReturn_']").each(function(){
		sumCostCarryTransinsurReturn += Number($(this).text());
	});
	$("#span_sumCostCarryTransinsurReturn").text(sumCostCarryTransinsurReturn);
	
	var sumCostCarryTransinsurMonth=0;
	$("[id^='span_costCarryTransinsurMonth_']").each(function(){
		sumCostCarryTransinsurMonth += Number($(this).text());
	});
	$("#span_sumCostCarryTransinsurMonth").text(sumCostCarryTransinsurMonth);
	
	var sumCostCarryAgentReduce=0;
	$("[id^='span_costCarryAgentReduce_']").each(function(){
		sumCostCarryAgentReduce += Number($(this).text());
	});
	$("#span_sumCostCarryAgentReduce").text(sumCostCarryAgentReduce);
	
	var sumCostCarryAgent=0;
	$("[id^='span_costCarryAgent_']").each(function(){
		sumCostCarryAgent += Number($(this).text());
	});
	$("#span_sumCostCarryAgent").text(sumCostCarryAgent);
	
	var sumCostCarryMessage=0;
	$("[id^='span_costCarryMessage_']").each(function(){
		sumCostCarryMessage += Number($(this).text());
	});
	$("#span_sumCostCarryMessage").text(sumCostCarryMessage);
	
	var sumCostCarryReturn=0;
	$("[id^='span_costCarryReturn_']").each(function(){
		sumCostCarryReturn += Number($(this).text());
	});
	$("#span_sumCostCarryReturn").text(sumCostCarryReturn);
});