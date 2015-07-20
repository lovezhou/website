var jessrun = typeof $ === "function" ? window.$ : {};
$(function() {
	//表格高亮
	$(".mod_table_bd").tableUI();
	//合作公司信息查询
	jessrun.formSubmit("search_btn","search_form","loadFormSubmit","合作公司信息查询中");
	//添加合作公司
	jessrun.formSubmit("editPartnerBtn","editPartnerForm","loadFormSubmit","合作公司信息保存中");
	//配置合作关系查询
	jessrun.formSubmit("search_partner_btn","search_partner_form","loadFormSubmit","配置合作关系查询中");
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

//配置合作关系

function changestate(){
	$("#hid_stateName").attr("value",$("#ddl_state option:selected").text());
}
function changepartner(){
	$("#hid_partnerName").attr("value",$("#ddl_partnerId option:selected").text());
}
function Checkform(){
	var state=$("#ddl_state").val();
	var partner=$("#ddl_partnerId").val();
	if(state==''){
		alert("请选择站点。");
		return  false;
	}
    if(partner==''){
		alert("请选择合作伙伴。");
		return  false;
	}
    if(isExit(state,partner)){
		return  false;
	}
}
function isExit(state,partner){
	var flag=false;
	$.ajax({
	       type:'GET',
	       async: false,
	       data:{"stateId":state,"partnerId":partner},
	       url:'../copartner/getList.act',
	       success: function(result){
	    	   var obj = eval(result);
	    	   if(obj.length>0){
	    		   alert("合作关系已存在");
	    		   flag= true;
	    	   }
	       }
	      });
	return flag;
}