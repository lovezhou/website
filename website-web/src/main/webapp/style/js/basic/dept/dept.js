var jessrun = typeof $ === "function" ? window.$ : {};



$(function() {
	//表格高亮
	$(".mod_table_bd").tableUI();
	//部门查询
	jessrun.formSubmit("search_dept_btn","search_dept_form","loadFormSubmit","部门查询中");
	//部门
	jessrun.formSubmit("deptEditBtn","deptEditForm","loadFormSubmit","部门信息保存中");
});


//检查是否要删除
function check(){
	var flage=true;
	$.ajax ({
		cache:false,
		async: false,
		type: 'post',
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		dataType:'JSON',
		url:"getListForDept.act",
		data:{'pId':$("#hid_id").val()},
		success: function(data) {
			data=eval("(" + data + ")");
			if(data.length>0){
				alert("该部门下有子部门，不能删除。");
				 flage=false;
			}
		}
	});
	return flage;
}

function changstate(){
	
	$("#hid_stateName").attr("value",$("#ddl_stateId option:selected").text());
	/* jessrun.loadToDept('ddl_stateId','ddl_pdept',$("#ddl_stateId").val()); */
	$.ajax ({
		cache:false,
		async: false,
		type: 'post',
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		dataType:'JSON',
		url:"getListForDept.act",
		data:{'deptStateId':$("#ddl_stateId").val(),'deptTypeId':3},
		success: function(data) {
			 $("#ddl_pId").empty(); 
			 obj=eval( data);
				if(obj!=null){
					var option='';
					 $.each(obj, function(j,item){
						 option+="<option value='"+item.code+"'>"+item.val+"</option>";
					 });
					 option="<option value=''>--请选择--</option>"+option;
				}
				$("#ddl_pId").append(option);
				 var pid=$("#hid_pId").val();
				if(pid>0){
					$("#ddl_pId").val(parseInt(pid));
				}
				
		}
	});
	 changepdept();
}
function changdeptType(){
	$("#hid_deptTypeName").attr("value",$("#ddl_deptTypeId option:selected").text());
}
function changepdept(){
	var pdept=$("#ddl_pId").val();
	var stateName=$("#hid_stateName").val();
	var obj=$("#hid_pathName");
	var deptName=$("#txt_deptName").val();
	$("#hid_pName").val($("#ddl_pId option:selected").text());
	if(pdept>0){
		obj.val(stateName+"/"+$("#ddl_pId option:selected").text()+"/"+deptName);
		/* $("#hid_pathId").val($("#ddl_stateId").val()+"/"+pdept+"/"+$("#hid_id").val()); */
	}else{
		obj.val(stateName+"/"+deptName);
	}
	
}

function deptLevel2(){
	 $('#txt_pdept').html("上级部门：");
	 $('#ddl_pdept').html("<span><select id='ddl_pId' name='pId' onchange='changepdept()' class='jessrun-select {required:true}'></select><span class='requried'>*</span></span>");
	// $("#ddl_deptTypeId option [value='3']").remove();
	 if($("#ddl_deptTypeId").val()==3){
		 $("#ddl_deptTypeId").val('');
	 }
	 $("#ddl_deptTypeId option[value='3']").attr("disabled","disabled");
	 $("#ddl_deptTypeId option[value='10']").attr("disabled","disabled");
	 changstate();
	 var id=$("#hid_id").val();
	 if(id>0){
			 $("#ddl_pId option[value='"+parseInt(id)+"']").remove();
			 //$("#ddl_pId option[value='"+parseInt(id)+"']").attr("disabled","disabled");
		}
}
function deptLevel1(){
	 $('#txt_pdept').html("");
	 $('#ddl_pdept').html("");
	 $('#hid_pId').val('');
	 $('#hid_pName').val('');
	 //$("#ddl_deptTypeId").append("<option value='3'>收货点</option>"); 
	 $("#ddl_deptTypeId option[value='3']").removeAttr("disabled");
	 $("#ddl_deptTypeId option[value='10']").removeAttr("disabled");
}