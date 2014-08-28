var jessrun = typeof $ === "function" ? window.$ : {};

$(function() {
	//表格高亮
	$(".mod_table_bd").tableUI();
	//站点查询
	jessrun.formSubmit("state_search_btn","state_search_from","loadFormSubmit","站点查询中");
})