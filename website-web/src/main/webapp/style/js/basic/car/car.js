var jessrun = typeof $ === "function" ? window.$ : {};



$(function() {
	//表格高亮
	$(".mod_table_bd").tableUI();
	//车辆查询
	jessrun.formSubmit("search_car_btn","search_car_form","loadFormSubmit","车辆查询中");
	//添加车辆
	jessrun.formSubmit("editCarBtn","editCarForm","loadFormSubmit","车辆信息保存中");
	//配置驾驶员查询
	jessrun.formSubmit("search_driver_btn","search_driver_form","loadFormSubmit","配置车辆驾驶员查询中");
	
})

function getStateVal(){
	$("#text_carStateName").attr("value",$("#ddl_carStateName option:selected").text());
}


 function changestate(){
		$("#hid_stateName").attr("value",$("#ddl_state option:selected").text());
	}
 function changecarType(){
		$("#hid_carTypeName").attr("value",$("#ddl_car option:selected").text());
	}
 var carsize=$("#hid_carSize").val();
 if(carsize==""){
	 $('input[name=carSize][value=大车]').attr("checked",true);
 }else{
	 $('input[name=carSize][value='+carsize+']').attr("checked",true);
 }