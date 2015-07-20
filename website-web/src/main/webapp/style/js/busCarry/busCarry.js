var jessrun = typeof $ === "function" ? window.$ : {};




//主体页面删除方法
function removeTr(cargoId)
{
	if($("#chk_top_"+cargoId).attr("checked")==true){
		$("#ddl_stateStart").attr("readonly",true);

		$("#ddl_stateEnd").attr("readonly",true);
	}else{
		$("#ddl_stateStart").removeAttr("readonly");
		$("#ddl_stateEnd").removeAttr("readonly");
	}
	var target_obj = $("#tr_"+cargoId);
	//如果主页面元素存在则删除
	if (target_obj.length > 0)
		$("#tr_"+cargoId).remove();

	//计算合计
	getSum();
	//判断主体页面的站点是否可以修改
	stateReadonly();
}

//主体页面删除方法
function remove_top_Tr(cargoId)
{

}

//主体页面删除方法
function remove_Tr(cargoId)
{
	removeTr(cargoId);
}

function getSum()
{
	var depotSum =0;
	var loadSum =0;
	//计算合计
	$("[id^='span_depotCargo_']").each(function(){
		depotSum+=Number($(this).text());
	});

	$("[id^='txt_loadCargoNumber_']").each(function (){
		loadSum+=Number($(this).val());
	});
	//合计赋值
	$("#span_sumDepotCargo").text(depotSum);
	$("#span_sumLoadCargo").text(loadSum);
}

//根据付款方式分配金额
function getPayList()
{
	var typeId = $("#ddl_loadPayTypeId").val();
	var costLoad = $("#txt_costLoad").val();
	//如果是现付
	if(typeId=="1"){
		$("#txt_costLoad_Cur").val(costLoad);
		$("#txt_costLoad_After").val("0");
		$("#txt_costLoad_Return").val("0");
		$("#txt_costLoad_Month").val("0");
		$("#txt_costLoad_Cur").attr("disabled",true);
		$("#txt_costLoad_After").attr("disabled",true);
		$("#txt_costLoad_Return").attr("disabled",true);
		$("#txt_costLoad_Month").attr("disabled",true);
	}
	//如果是回付
	else if(typeId=="2"){
		$("#txt_costLoad_Cur").val("0");
		$("#txt_costLoad_After").val("0");
		$("#txt_costLoad_Return").val(costLoad);
		$("#txt_costLoad_Month").val("0");
		$("#txt_costLoad_Cur").attr("disabled",true);
		$("#txt_costLoad_After").attr("disabled",true);
		$("#txt_costLoad_Return").attr("disabled",true);
		$("#txt_costLoad_Month").attr("disabled",true);
	}
	//如果是到付
	else if(typeId=="3"){
		$("#txt_costLoad_Cur").val("0");
		$("#txt_costLoad_After").val(costLoad);
		$("#txt_costLoad_Return").val("0");
		$("#txt_costLoad_Month").val("0");
		$("#txt_costLoad_Cur").attr("disabled",true);
		$("#txt_costLoad_After").attr("disabled",true);
		$("#txt_costLoad_Return").attr("disabled",true);
		$("#txt_costLoad_Month").attr("disabled",true);
	}
	//如果是月结
	else if(typeId=="4"){
		$("#txt_costLoad_Cur").val("0");
		$("#txt_costLoad_After").val("0");
		$("#txt_costLoad_Return").val("0");
		$("#txt_costLoad_Month").val(costLoad);
		$("#txt_costLoad_Cur").attr("disabled",true);
		$("#txt_costLoad_After").attr("disabled",true);
		$("#txt_costLoad_Return").attr("disabled",true);
		$("#txt_costLoad_Month").attr("disabled",true);
	}
	//如果是混付
	else if(typeId=="5"){
		$("#txt_costLoad_Cur").attr("disabled",false);
		$("#txt_costLoad_After").attr("disabled",false);
		$("#txt_costLoad_Return").attr("disabled",false);
		$("#txt_costLoad_Month").attr("disabled",false);
	}
}

function formLoadAdd() {
	//判断装车费用填定是否正确
	var costLoad = $("#txt_costLoad").val();
	var costLoadCur = $("#txt_costLoad_Cur").val();
	var costLoadAfter = $("#txt_costLoad_After").val();
	var costLoadReturn = $("#txt_costLoad_Return").val();
	var costLoadMonth = $("#txt_costLoad_Month").val();
	var canSubmit = (Number(costLoad)==Number(costLoadCur)+Number(costLoadAfter)+Number(costLoadReturn)+Number(costLoadMonth));

		//向隐藏控件传值
		$("#hid_stateStartId").val($("#ddl_stateStartId").val());
		$("#hid_stateStartName").val($("#ddl_stateStartId").find("option:selected").text());
		$("#hid_stateEndId").val($("#ddl_stateEndId").val());
		$("#hid_stateEndName").val($("#ddl_stateEndId").find("option:selected").text());
		$("#hid_loadPayTypeName").val($("#ddl_loadPayTypeId").find("option:selected").text());
		$("#hid_loadCarName").val($("#ddl_loadCarId").find("option:selected").text());
		$("#hid_loadStaffName").val($("#ddl_loadStaffId").find("option:selected").text());
		$("#hid_costLoad").val($("#txt_costLoad").val());
		$("#hid_costLoadCur").val($("#txt_costLoad_Cur").val());
		$("#hid_costLoadAfter").val($("#txt_costLoad_After").val());
		$("#hid_costLoadReturn").val($("#txt_costLoad_Return").val());
		$("#hid_costLoadMonth").val($("#txt_costLoad_Month").val());
		$("#form_loadAdd").submit();
}





//载入验证所需方法
jessrun.loadJS(jessrun.config.domain+"/style/js/init-form.js",function(){
},function(){
	alert(jessrun.url.jsError)
});

//配置提交表单Id
jessrun.formId={
	id:"form_loadAdd"
};
$(function(){
	//公司资料修改
	jessrun.formLoadAdd("carry_loadAdd","form_loadAdd","loadFormSubmit","数据保存中");
});

jessrun.formLoadAdd=function(btnId,formId,loadClass,loadHtml){
	$("#"+btnId).click(function(){
		if ($("#"+formId).valid()) {
			$("#"+btnId).hide()
			$("<span class='"+loadClass+"'><span><em>"+loadHtml+"</em></span></span>").insertAfter("#"+btnId);
			function doSubmit(){
				//判断装车费用填定是否正确
				var costLoad = $("#txt_costLoad").val();
				var costLoadCur = $("#txt_costLoad_Cur").val();
				var costLoadAfter = $("#txt_costLoad_After").val();
				var costLoadReturn = $("#txt_costLoad_Return").val();
				var costLoadMonth = $("#txt_costLoad_Month").val();
				var canSubmit = (Number(costLoad)==Number(costLoadCur)+Number(costLoadAfter)+Number(costLoadReturn)+Number(costLoadMonth));

				//向隐藏控件传值
				$("#hid_stateStartId").val($("#ddl_stateStartId").val());
				$("#hid_stateStartName").val($("#ddl_stateStartId").find("option:selected").text());
				$("#hid_stateEndId").val($("#ddl_stateEndId").val());
				$("#hid_stateEndName").val($("#ddl_stateEndId").find("option:selected").text());
				$("#hid_loadPayTypeName").val($("#ddl_loadPayTypeId").find("option:selected").text());
				$("#hid_loadCarName").val($("#ddl_loadCarId").find("option:selected").text());
				$("#hid_loadStaffName").val($("#ddl_loadStaffId").find("option:selected").text());
				$("#hid_costLoad").val($("#txt_costLoad").val());
				$("#hid_costLoadCur").val($("#txt_costLoad_Cur").val());
				$("#hid_costLoadAfter").val($("#txt_costLoad_After").val());
				$("#hid_costLoadReturn").val($("#txt_costLoad_Return").val());
				$("#hid_costLoadMonth").val($("#txt_costLoad_Month").val());
				$("#"+formId).submit();
				}
			setTimeout(doSubmit,0);
		}
	});
};