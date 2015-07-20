var jessrun = typeof $ === "function" ? window.$ : {};

jessrun.loadCSS(jessrun.config.domain+"/style/css/dialog.css","dialog");
jessrun.loadJS(jessrun.config.domain+"/style/js/dialog-min.js",function(){
	//
},function(){
	alert("弹出层载入失败");
});
var jessrun = typeof $ === "function" ? window.$ : {};

$(function() {
	//取经办员工
	jessrun.loadToStaff("ddl_outState","ddl_outStaffId",-1);
	
	//表格高亮
	$(".mod_table_bd").tableUI();

	//选择货物弹出层
	jessrun.chooseCargo = function (){
		var stateEndId = $('#ddl_outState').val();
		if(stateEndId == null){
			stateEndId = "";
		}
		var outdepotId = $('#hid_outdepotId').val();
		if(outdepotId == null){
			outdepotId = "";
		}
		if(stateEndId=="")
		{
			alert("请选择送货站点！");
			return;
		}
		var url=jessrun.config.domain+"bus/outdepot/gotoList.action?carryStateEndId="
			+stateEndId+"&outId="+outdepotId;
		jessrun.Dialog({
			title:"选择货物",
			width:900,
			height:420,
			showbg:true,
			drag:false,
			content:"iframe:"+url
		});
	};
	
	jessrun.check_Number = function(cargoId){
		var depotCargoNumber=$("#txt_loadCargoNumber_"+cargoId).attr("depotCargoNumber");
		//获取填入值
		checkIntDefault($("#txt_loadCargoNumber_"+cargoId), depotCargoNumber);
		//验证货物数量不为0
		if(Number($("#txt_loadCargoNumber_"+cargoId).val()) == 0)
			$("#txt_loadCargoNumber_"+cargoId).val(depotCargoNumber);
		//如果和相关字段的加和大于最大值
		if(Number(depotCargoNumber)<Number($("#txt_loadCargoNumber_"+cargoId).val()))
			$("#txt_loadCargoNumber_"+cargoId).val(depotCargoNumber);
		//计算主体页面的合计
		getSum();
	};

	//关闭弹出层
	jessrun.closeIframeDialog = function (){
		parent.jessrun.Dialog.removeBox();
	};
	
	//确认送货选择事件
	$("[id^='chk_']").change(function(){
		changeOutIdName();
	});

	$("#chk_all").change(function(){
		$("[id^='chk_']").attr("checked",$("#chk_all").attr("checked"));
		changeOutIdName();
	});
});

//更新装车单提交操作中的LoadId的Name
function changeOutIdName(){
	var lineNo = 0;
	var selectedNo = 0;
	$("[id^='chk_']").each(function(){
		var outdepotId=$(this).attr("loadId");
		$("#hid_loadId_"+outdepotId).removeAttr("name");
		if($(this).attr("checked"))
		{
			$("#hid_loadId_"+outdepotId).attr("name","outdepotList["+selectedNo+"].outdepotId");
			selectedNo=Number(selectedNo)+1;
		}
		//主体页面序号
		lineNo=Number(lineNo)+1;
		$("#span_no_"+outdepotId).text(lineNo);
	});
}

//判断站点是否可以修改
function stateReadonly()
{
	var orgaType = $("#ddl_outState").attr("orgaType");
	if(Number(orgaType)<3)
		$("#ddl_outState").removeAttr("disabled");
	//如果主体页面上有货物信息，则不能修改
	var lineNo = 0;

	$("[id^='tr_']").each(function(){
		var cargoId = $(this).attr("cargoID");
		$("#ddl_outState").attr("disabled",true);
		//更新提交控件名称
		$("#txt_costLoadCargoAfter_"+cargoId).attr("name","busDepotCargo["+lineNo+"].costCarrySendcargo");
		$("#txt_loadCargoNumber_"+cargoId).attr("name","busDepotCargo["+lineNo+"].outCargoNumber");
		$("#hid_cargoId_"+cargoId).attr("name","busDepotCargo["+lineNo+"].carryCargoId");
		//主体页面序号
		lineNo=Number(lineNo)+1;
		$("#span_no_"+cargoId).html(lineNo);

	});
	
	$("#ddl_outStaffId").change(function(){
		$("#hid_outStaffName").val($(this).find("option:selected").text());
	});
}

function costLoadCargoChange(obj)
{
	checkMoney($(obj));
	getSum();
}

//主体页面删除方法
function removeTr(cargoId)
{
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
	var costLoadCargoSum = 0;
	var depotSum = 0;
	var loadSum = 0;
	//计算合计
	$("[id^='txt_costLoadCargoAfter_']").each(function(){
		costLoadCargoSum += Number($(this).val());
	});
	
	$("[id^='span_depotCargo_']").each(function(){
		depotSum += Number($(this).text());
	});

	$("[id^='txt_loadCargoNumber_']").each(function (){
		loadSum += Number($(this).val());
	});
	//合计赋值
	$("#span_sumCostLoadCargo").text(costLoadCargoSum);
	$("#span_sumDepotCargo").text(depotSum);
	$("#span_sumLoadCargo").text(loadSum);
}

function pageFormSubmit()
{
	document.forms["loadListForm"].submit();
}

function gotoPage(pageNo)
{
	if(Number(pageNo)>Number($("#ddl-pageSize").val())){
		alert("超过最大的页码数");
		return;
	}
	$("#pageNoId").val(pageNo);

	document.forms["loadListForm"].submit();
}


$(function(){
	//提交数据
	jessrun.formArrivein("btn_loadArrivein","form_loadArrivein","loadFormSubmit","数据保存中");
	jessrun.formLoadAdd("carry_loadAdd","form_loadAdd","loadFormSubmit","数据保存中");
});

jessrun.formLoadAdd=function(btnId,formId,loadClass,loadHtml){
	$("#"+btnId).click(function(){
		if($("tr[id^='tr_']").length == 0){
			alert("请选择货物！");
			return;
		}
		if ($("#"+formId).valid()) {
			$("#"+btnId).hide();
			$("<span class='"+loadClass+"'><span><em>"+loadHtml+"</em></span></span>").insertAfter("#"+btnId);
			function doSubmit(){
				//向隐藏控件传值
				$("#hid_outStateId").val($("#ddl_outState").val());
				$("#hid_outStateName").val($("#ddl_outState").find("option:selected").text());
				$("#hid_outStaffName").val($("#ddl_outStaffId").find("option:selected").text());
				$("#hid_carName").val($("#ddl_carId").find("option:selected").text());
				$("#hid_driverName").val($("#ddl_driverId").find("option:selected").text());
				$("#"+formId).submit();
			}
			setTimeout(doSubmit,0);
		}
	});
};

jessrun.formArrivein=function(btnId,formId,loadClass,loadHtml){
	$("#"+btnId).click(function(){
		
		if ($("#"+formId).valid()) {
			$("#"+btnId).hide();
			$("<span class='"+loadClass+"'><span><em>"+loadHtml+"</em></span></span>").insertAfter("#"+btnId);
			function doSubmit(){
				
				//var canSubmit = true;

				//向隐藏控件传值
				//经办员工
				$("#hid_outdepotStaffName").val($("#ddl_outdepotStaffId").find("option:selected").text());
				$("#"+formId).submit();
			}
			setTimeout(doSubmit,0);
		}
	});
};