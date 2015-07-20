$(function(){
	//公司资料修改
	
	jessrun.formSubmit("companyEditBtn","companyEditForm","loadFormSubmit","公司信息保存中");
});
function getDdlVal(){
	$("#txt_bankName").attr("value",$("#ddl_bank option:selected").text());
}