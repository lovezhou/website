$(document).ready(function () {
	var sumCargoNumer=0;
	$("[id^='span_cargoNumber_']").each(function(){
		sumCargoNumer += Number($(this).text());
	});
	$("#span_cargoNumberSum").text(sumCargoNumer);

	var sumCargoWeight=0;
	$("[id^='span_cargoWeight_']").each(function(){
		sumCargoWeight += Number($(this).text());
	});
	$("#span_cargoWeightSum").text(sumCargoWeight);

	var sumCargoVolume=0;
	$("[id^='span_cargoVolume_']").each(function(){
		sumCargoVolume += Number($(this).text());
	});
	$("#span_cargoVolumeSum").text(sumCargoVolume);

	var sumCargoCostsTransport=0;
	$("[id^='span_cargoCostsTransport_']").each(function(){
		sumCargoCostsTransport += Number($(this).text());
	});
	$("#span_cargoCostsTransportSum").text(sumCargoCostsTransport);

	var sumCargoInsuranceCredit=0;
	$("[id^='span_cargoInsuranceCredit_']").each(function(){
		sumCargoInsuranceCredit += Number($(this).text());
	});
	$("#span_cargoInsuranceCreditSum").text(sumCargoInsuranceCredit);

	var sumCargoCostsInsurance=0;
	$("[id^='span_cargoCostsInsurance_']").each(function(){
		sumCargoCostsInsurance += Number($(this).text());
	});
	$("#span_cargoCostsInsuranceSum").text(sumCargoCostsInsurance);
});