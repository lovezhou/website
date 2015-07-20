var jessrun = typeof $ === "function" ? window.$ : {};

$(function() {
	//表格高亮
	$(".mod_table_bd").tableUI();
	//线路查询
	jessrun.formSubmit("search_line_btn","search_line_form","loadFormSubmit","线路查询中");
});
