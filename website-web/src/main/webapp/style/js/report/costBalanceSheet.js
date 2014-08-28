$(function(){
	getSum();
});
//计算收入、支出、利润合计
function getSum()
{
	var inSum =0;
	var outSum =0;
	//计算合计
	$("[id^='span_in_']").each(function(){
		inSum+=Number($(this).text());
	});

	$("[id^='span_out_']").each(function (){
		outSum+=Number($(this).text());
	});
	//合计赋值
	$("#span_income_sum").text(inSum);
	$("#span_expense_sum").text(outSum);
	$("#span_profit_sum").text(inSum-outSum);
}