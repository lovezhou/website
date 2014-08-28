var jessrun = typeof $ === "function" ? window.$ : {};



$(function() {
	//表格高亮
	$(".mod_table_bd").tableUI();
	//客户信息查询
	jessrun.formSubmit("search_customer_btn","search_customer_form","loadFormSubmit","客户信息查询中");
	//添加客户
	jessrun.formSubmit("add_customer_btn","add_customer_form","loadFormSubmit","客户信息保存中");
	//添加联系人
	jessrun.formSubmit("linkManEditBtn","linkManEditForm","loadFormSubmit","联系人信息保存中");
	//联系人查询
	jessrun.formSubmit("linkMan_search_btn","linkMan_search_form","loadFormSubmit","联系人信息查询中");
})

function changeBank(){
	$("#text_customerBankName").attr("value",$("#ddl_customerBankName option:selected").text());
}

function changeState(){
	  $("#hid_stateName").attr("value",$("#ddl_state option:selected").text());
}
function changeCustomer(){
	  $("#hid_customerName").attr("value",$("#ddl_customer option:selected").text());
}
