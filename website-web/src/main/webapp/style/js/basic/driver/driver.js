var jessrun = typeof $ === "function" ? window.$ : {};



$(function() {
	//表格高亮
	$(".mod_table_bd").tableUI();
	//驾驶员查询
	jessrun.formSubmit("search_driver_btn","search_driver_form","loadFormSubmit","驾驶员查询中");
	//添加驾驶员
	jessrun.formSubmit("editDriverBtn","editDriverForm","loadFormSubmit","驾驶员信息保存中");
})
function getStateVal(){
		$("#text_driverStateName").attr("value",$("#ddl_driverStateName option:selected").text());
}


 function getState(){
		$("#hid_stateName").attr("value",$("#ddl_state option:selected").text());
}
 function getDriverCarType(){
		$("#hid_driverCarTypeName").attr("value",$("#ddl_driverCarType option:selected").text());
}
 function getDriverType(){
		$("#hid_driverTypeName").attr("value",$("#ddl_driverType option:selected").text());
}