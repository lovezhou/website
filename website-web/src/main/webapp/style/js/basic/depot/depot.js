var jessrun = typeof $ === "function" ? window.$ : {};



$(function() {
	//表格高亮
	$(".mod_table_bd").tableUI();
	//仓库查询
	jessrun.formSubmit("search_dept_btn","search_dept_form","loadFormSubmit","仓库查询中");
	//添加仓库
	jessrun.formSubmit("editDpotBtn","editDpotForm","loadFormSubmit","仓库信息保存中");
})


function changestate(){
	$("#hid_stateName").attr("value",$("#ddl_state option:selected").text());
}
function changdeptType(){
	$("#hid_deptTypeName").attr("value",$("#ddl_deptTypeId option:selected").text());
}
function changedept(){
	$("#hid_deptName").attr("value",$("#ddl_dept option:selected").text());
}
function changeducation(){
	$("#hid_staffEducationName").attr("value",$("#ddl_education option:selected").text());
}

function changebank(){
	$("#hid_bankName").attr("value",$("#ddl_bank option:selected").text());
}
