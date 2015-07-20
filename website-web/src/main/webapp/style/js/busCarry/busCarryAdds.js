var i = Number($("#hid_idForCarryCargo").val());

$(document).ready(function(){
	
	//表格高亮
	$(".mod_table_bd").tableUI();

	AddChild();AddChild();AddChild();AddChild();AddChild();AddChild();
	
	//承运方式改变事件
	$("#ddl_carryModeId").change(function(){
		if($("#ddl_carryModeId").val()=="1"){
			$("#ddl_carryStateEndId").addClass("{required:true}");
			$("#ddl_carryDeptEndId").addClass("{required:true}");
			//$("#ddl_carryStateEndId").removeAttr("disabled");
			//$("#ddl_carryDeptEndId").removeAttr("disabled")
			$("#txt_carryDestination").removeClass("{required:true}");
			$("#th_end_state").show();
			$("#td_end_state").show();
			$("#th_end_dept").show();
			$("#td_end_dept").show();
			
			$("#txt_carryDestination").addClass("{required:true}");
		}
		if($("#ddl_carryModeId").val()=="2"){
			$("#ddl_carryStateEndId").addClass("{required:true}");
			$("#ddl_carryDeptEndId").addClass("{required:true}");
			//$("#ddl_carryStateEndId").removeAttr("disabled");
			//$("#ddl_carryDeptEndId").removeAttr("disabled");
			$("#txt_carryDestination").addClass("{required:true}");
			$("#th_end_state").show();
			$("#td_end_state").show();
			$("#th_end_dept").show();
			$("#td_end_dept").show();
		}
		
		if($("#ddl_carryModeId").val()=="3")
		{
			$("#ddl_carryStateEndId").removeClass("{required:true}");
			$("#ddl_carryDeptEndId").removeClass("{required:true}");
			//$("#ddl_carryStateEndId").attr("disabled","disabled");
			//$("#ddl_carryDeptEndId").attr("disabled","disabled");
			$("#txt_carryDestination").addClass("{required:true}");
			$("#th_end_state").hide();
			$("#td_end_state").hide();
			$("#th_end_dept").hide();
			$("#td_end_dept").hide();
		}
		
	});
	//运保费
	$("#txt_costCarryTransinsur").attr({disabled:'disabled'});

	//合计部分不能编辑
	$("input[id$='Total']").each(function(){
		$(this).attr({disabled:'disabled'});
	});

	//运费总额改变事件
	$("#txt_cargoCostTransportTotal").change(function(){
		var iCargoCostTransportTotal = $(this).val();
		var iCargoCostInsuranceTotal = $("#txt_cargoCostInsuranceTotal").val();
		$("#txt_costCarryTransinsur").val(Number(iCargoCostTransportTotal)+Number(iCargoCostInsuranceTotal));
		$("#txt_costCarryTransinsur").change();
	});

	//保费总额改变事件
	$("#txt_cargoCostInsuranceTotal").change(function(){
		var iCargoCostInsuranceTotal = $(this).val();
		var iCargoCostTransportTotal = $("#txt_cargoCostTransportTotal").val();
		$("#txt_costCarryTransinsur").val(Number(iCargoCostTransportTotal)+Number(iCargoCostInsuranceTotal));
		$("#txt_costCarryTransinsur").change();
	});

	//运保费改变事件
	$("#txt_costCarryTransinsur").change(function(){
		var iCostCarryTransinsur = $(this).val();
		$("#hid_costCarryTransinsur").val(iCostCarryTransinsur);
		carryTransinsurTypeIdChanged();
	});

	//现收改变事件
	$("#txt_costCarryTransinsurCur").change(function(){
		//各方式和付款额度之和不能大于运保费
		if(testCostCarryTransinsurEqualTypesValue(2) == false){
			$(this).val("0");
		}
		var iCostCarryTransinsurCur = $(this).val();
		$("#hid_costCarryTransinsurCur").val(iCostCarryTransinsurCur);
	});

	//回收改变事件
	$("#txt_costCarryTransinsurReturn").change(function(){
		if(testCostCarryTransinsurEqualTypesValue(2) == false){
			$(this).val("0");
		}
		var iCostCarryTransinsurReturn = $(this).val();
		$("#hid_costCarryTransinsurReturn").val(iCostCarryTransinsurReturn);

	});

	//运从代扣改变事件
	$("#txt_costCarryAgentReduce").change(function(){
		if(testCostCarryTransinsurEqualTypesValue(2) == false){
			$(this).val("0");
		}
		var iCostCarryAgentReduce = $(this).val();
		$("#hid_costCarryAgentReduce").val(iCostCarryAgentReduce);
	});

	//提收改变事件
	$("#txt_costCarryTransinsurAfter").change(function(){
		if(testCostCarryTransinsurEqualTypesValue(2) == false){
			$(this).val("0");
		}
		var iCostCarryTransinsurAfter = $(this).val();
		$("#hid_costCarryTransinsurAfter").val(iCostCarryTransinsurAfter);

	});

	//司机代收改变事件
	$("#txt_costCarryTransinsurDriver").change(function(){
		if(testCostCarryTransinsurEqualTypesValue(2) == false){
			$(this).val("0");
		}
		var iCostCarryTransinsurDriver = $(this).val();
		$("#hid_costCarryTransinsurDriver").val(iCostCarryTransinsurDriver);

	});

	//月结改变事件
	$("#txt_costCarryTransinsurMonth").change(function(){
		if(testCostCarryTransinsurCurValue(2) == false){
			$(this).val("0");
		}
		var iCostCarryTransinsurMonth = $(this).val();
		$("#hid_costCarryTransinsurMonth").val(iCostCarryTransinsurMonth);

	});

	//代收款改变事件
	$("#txt_costCarryAgent").change(function(){
		carryAgentTypeIdChanged();
	});
	//返中转费改变事件
	$("#txt_costCarryTransfer").change(function(){
		carryAgentTypeIdChanged();
	});
	
	jessrun.formSubmitValidate("carryAddBatchBtns","carryAddForm","loadFormSubmit","数据保存中");
});

jessrun.formSubmitValidate = function(btnId,formId,loadClass,loadHtml){
	$("#"+btnId).click(function(){
		replaceBusHiddenField();
		//客户端判断是否输入了重复的单号
		var arrayCarryName = new Array();
		$("[id^='txt_carryName_'][value!='']").each(function(){
			arrayCarryName.push($(this).val());
		});
		for(var i = 0; i < arrayCarryName.length; i++) {
			for(var j = i+1; j< arrayCarryName.length; j++){
				if(arrayCarryName[i] == arrayCarryName[j]){
					alert('承运单号' + arrayCarryName[i]+'重复');
					return false;
				}
			}
        }
		
		var carryNumber = 0;
		$("[id^='txt_carryName_']").each(function(){
			if($(this).val() != ""){
				carryNumber++;
			}
		});
		if(carryNumber == 0){
			alert("请至少录入一张承运单！");
			return false;
		}
		var carryNames = "";
		$("[id^='txt_carryName_']").each(function(){
			if($(this).val() != ""){
				carryNames += "," + $(this).val();
			}
		});
		//判断输入的承运单是否重复，1、先用js判断页面的值是否重复，2、远程判断
		//repeat();
		$.ajax({
			type:'POST',
			url:'assertExsited.action',
			data:'carryNames='+carryNames,
			cache:false,
			success:function(data){
				var result = eval("("+data+")");
				if(result.success == true){
					//alert(result.msg);
					$("#btn_sys_submit").show();
					return false;
				}else{
					if ($("#"+formId).valid()) {
						$("#"+btnId).hide();
						$("<span class='"+loadClass+"'><span><em>"+loadHtml+"</em></span></span>").insertAfter("#"+btnId);
						function doSubmit(){
							$("#"+formId).submit();
						}
						setTimeout(doSubmit,0);
					}
				}
			}
		});
		
	});
};

//返回指定'yyyy-MM-dd'格式的日期
function returnNowDateAsString(){

	var dDate = new Date();
	var year = dDate.getFullYear();
	var month = dDate.getMonth()+1;
	var date = dDate.getDate();

	if(Number(date) < 10){
		date = '0'+date;
	}
	if(Number(month) < 10){
		month = '0'+month;
	}

	return year+'-'+month+"-"+date;
}

//删除一行的方法
function RemoveChild(j){
	$("#tr_" + j).remove();
}

//添加行的头
function addTitle(name){
	$("#tr_title").append("<th>" + name + "</th>");
}

function replaceBusHiddenField(){
	$("#hid_carryStateStartName").val($("#ddl_stateStart").find("option:selected").text());
	$("#hid_depotName").val($("#ddl_depotId").find("option:selected").text());
	$("#hid_carryDeptStartName").val($("#ddl_carryDeptStartId").find("option:selected").text());
	$("#hid_carryStaffStartName").val($("#ddl_carryStaffStartId").find("option:selected").text());
	$("#hid_carryStateEndName").val($("#ddl_carryStateEndId").find("option:selected").text());
	$("#hid_carryDeptEndName").val($("#ddl_carryDeptEndId").find("option:selected").text());
	$("#hid_carrySendModeName").val($("#ddl_carrySendModeId").find("option:selected").text());
	$("#hid_carryModeName").val($("#ddl_carryModeId").find("option:selected").text());
}
//选择包装
jessrun.showCargoPackName = function(id){
	var sid=$(id).attr("relId");
	$("#tempId").attr("relId",sid);
	jessrun.Dialog({
		 boxID: "selectCargoPack",
         title: "包装名称列表",
         showbg:true,
         content: "id:dialogCargoPackName"
            
	});
};

//选中选择包装填入
jessrun.showCargoPackNameEnt = function(id){
	var sid =$("#tempId").attr("relId");
	jessrun.Dialog.removeBox();
	$("#"+sid).val($(id).attr("relName"));
	
};

//选择货物
jessrun.showCargoName = function(id){
	var sid=$(id).attr("relId");
	//$("#tempId_").attr("relId",sid);
	jessrun.Dialog({
         title: "货物名称列表",
         width:800,
         height:500,
         showbg:true,
         content:"iframe:"+jessrun.config.domain+"/bic/cargo/gotoAllBicCargo.action?sid="+sid
	});
};


function carryNameChange(obj)
{
	var rowNo = $(obj).attr("rowNo");
	if($(obj).val()!="")
	{
		$(obj).addClass("{required:true}");
		$("#txt_cargo_name_" + rowNo).addClass("{required:true}");
		$("#txt_cargoNumber_"+rowNo).addClass(" {required:true,min:1}");
	}
	else
	{
		$(obj).removeClass("{required:true}");
		$("#txt_cargo_name_" + rowNo).removeClass("{required:true}");
		$("#txt_cargoNumber_"+rowNo).removeClass(" {required:true,min:1}");
		
	}
	
}



//添加新行的方法
function AddChild() {
	var i = $("#hid_idForCarryCargo").val();
	var trObj = '<tr id="tr_' + i + '">';
	trObj += "<td>"
		+ '    <a id="btn_removeChild_' + i + '" trcount="' + i 
		+ '" class="jessrun-btn"  href="javascript:;" onclick="javascript:RemoveChild('+ i 
		+');" ><span>删除</span></a>'
		+ "</td>"
		+ "<td>"
		+ '    <span><input id="txt_carryName_' + i + '" name="busCarryBatchVos['+i+'].carryName" type="text" maxlength="25" rowNo='+i+' class="jessrun-txt w60" onchange="carryNameChange(this);" /></span>  '
		+ "</td>"
		+ "<td>"
		+ '    <span><input id="txt_cargo_name_' + i + '" name="busCarryBatchVos['+i+'].cargoName" maxlength="25" type="text" class="jessrun-txt w60" /> <a href="javascript:;" relId="txt_cargo_name_' + i + '" onclick="jessrun.showCargoName(this)">选择</a></span>'
		+ "</td>";
	$("[id^='hid_carryColumn_']").each(function(){
		var columnType = $(this).attr("columnType");
		var columnName = $(this).attr("columnName");
		var columnValue = $(this).val();
		var i = $("#hid_idForCarryCargo").val();
		if(columnType=="text")
		{
			trObj+= "<td>"
				+ '<span><input id="txt_'+columnName+'_' + i 
				+ '" name="busCarryBatchVos['+ i
				+'].'+columnName+'" type="text" maxlength="25" class="jessrun-txt w60" />'
				+ "</span></td>";
		}
		else if (columnType=="int")
		{
			trObj+= "<td>"
			+ '    <span><input id="txt_'+columnName+'_' + i 
			+ '" name="busCarryBatchVos['+ i
			+'].'+columnName+'" value="0" maxlength="8" class="jessrun-txt w-price w60" onchange="javascript:checkInt(this);" />'
			+ "</span></td>";
		}
		else if (columnType=="money")
		{
			trObj+= "<td>"
			+ '    <span><input id="txt_'+columnName+'_' + i 
			+ '" name="busCarryBatchVos['+ i
			+'].'+columnName+'" value="0" maxlength="8" class="jessrun-txt w-price w60" onchange="javascript:checkMoney(this);" />'
			+ "</span></td>";
		}
		else if (columnType=="select")
		{
			//alert(columnValue);
			trObj += "<td>"
			+ '<select style="width:80px" id="ddl_'+columnName+'_' + i 
			+ '" name="busCarryBatchVos['+ i +'].'+columnName+'" >';
			var valList = columnValue.split(';');
			var listLenght = valList.length;
			//alert();
			for(var i = 0; i < listLenght; i++)
			{
				var objList = valList[i].split(':');
				var objLength = objList.length;
				if(objLength > 1)
				{
					if(i == 0)
						trObj +=  '<option value="'+objList[0]+'" selected="selected">'+objList[1]+'</option>';
					else
						trObj +=  '<option value="'+objList[0]+'">'+objList[1]+'</option>';
				}
			}
			trObj += '</select>'
			+ "</td>";
		}
	});
	trObj += "</tr>";
	$("#tbl_carryList").append(trObj);
	i++;
	$("#hid_idForCarryCargo").val(i);
	//隔行换色
	hoverOddEven("#tbl_carryList","tr");
}


function hoverOddEven (em,o){
	if($(em).length>0){
		$(em+" >"+o+":odd").addClass("oddTd");
		$(em+" >"+o+":even").addClass("evenTd");
		$(em+" >"+o).mouseover(function(){
			$(this).addClass("onTd");
		}).mouseout(function(){
			$(this).removeClass("onTd");
		});
	}else{
		return;
	}
};

function repeat(){
	var arr = $("[id^='txt_carryName_']");
	alert(arr);
	if(arr.length > 0){
		var t = "";
		Each(arr.sort(), function(o,i){ 
		if(i>0 && o == t){ 
			alert("单号" + o+ "重复！"); 
			return;
		} 
		t = o; 
		});
	}
	
}

function Each(list, fun){
	alert(list);
	for (var i = 0, len = list.length; i < len; i++) { 
		if($(list[i]).val != ""){
			fun(list[i], i); 
		}
		
	} 
}
