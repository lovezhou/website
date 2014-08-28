var jessrun = typeof $ === "function" ? window.$ : {};

changestate();
var sex=$("#txt_staffSexName").val();
if(sex==''){
	 $('input[name=staffSexName][value=男]').attr("checked",true);
}else{
	 $('input[name=staffSexName][value='+sex+']').attr("checked",true);
}
//选站点
function changestate(){
	var stateId=$("#ddl_state").val();
	$("#hid_stateName").attr("value",$("#ddl_state option:selected").text());

	var option="";
	$.ajax ({
		cache:false,
		async: false,
		type: 'get',
		url:"../dept/getListForSimple.act",
		data:{"deptStateId":stateId},
		success: function(data) {
			 $("#ddl_dept").empty(); 
			 obj=eval("(" + data + ")");
				if(obj!=null){
					var option='';
					 $.each(obj, function(j,item){
						 option+="<option value='"+item.code+"'>"+item.val+"</option>";
					 });
					  option="<option value=''>--请选择--</option>"+option;
				}
				$("#ddl_dept").append(option);
				var dept=$("#deptId").val();
				if(dept!=''){
					$("#ddl_dept").val(dept);    
					
				}
				
		}
	});
	
}

function checkform(){
	if($("#hid_stateName").val()=="--请选择--"){
		$("#hid_stateName").val("");
	}
	if($("#hid_deptName").val()=="--请选择--"){
		$("#hid_deptName").val("");
	}
	if($("#hid_staffEducationName").val()=="--请选择--"){
		$("#hid_staffEducationName").val("");
	}
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


$(function() {
	//表格高亮
	$(".mod_table_bd").tableUI();
	//员工查询
	jessrun.formSubmit("search_btn","search_form","loadFormSubmit","员工查询中");
	//添加员工
	jessrun.formSubmit("staffEditBtn","staffEditForm","loadFormSubmit","员工信息保存中");
})
