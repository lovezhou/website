var jessrun = typeof $ === "function" ? window.$ : {};



$(function() {
	//表格高亮
	$(".mod_table_bd").tableUI();
	//添加站点
	jessrun.formSubmit("stateEditBtn","stateEditForm","loadFormSubmit","站点信息保存中");
	//站点查询
	jessrun.formSubmit("state_search_btn","state_search_from","loadFormSubmit","站点查询中");
});


	jessrun.loadCSS(jessrun.config.domain+"/style/css/dialog.css","dialog");
		jessrun.loadJS(jessrun.config.domain+"/style/js/dialog-min.js",function(){},function(){
			alert("弹出层载入失败");
		});
	//地理位置
	 jessrun.loadStateAreaId = function(){
		jessrun.Dialog({
			title:"地理位置",
			showbg:true,
			boxID:"stateAreaIdBox",
			content:"id:stateAreaIdTable"
		});
	 };
	 
	 $("#selectStateAreaId").click(function(){
			jessrun.loadStateAreaId();
			return false;
	});
	
	
	//选择地级市/区
	jessrun.selectCity = function(id){
		$(id).parent().addClass("cur").siblings().removeClass("cur");
		var proId=$(id).attr("val");
		$.ajax( {
			type :"GET",
			url :"../area/getList.act",
			cache:false,
			async: false,
			data:{"areaPid":proId},
			success : function(data) {
				$("#cityAreaList").empty();
				$("#subCityAreaList").empty();
				$("#stateCityAreaList").hide();
				$("#stateSubCityAreaList").hide();
			   var obj = eval("(" + data + ")");
			   if(obj.length>0){
				  $("#stateCityAreaList").show(); 
			   }
			   var li='';
			   $.each(obj, function(j,item){
				   li+="<li><a href='#' onclick='jessrun.selectSubCity(this)' val='"+item.areaId+"' name='"+item.areaName+"'>"+item.areaName+"</a></li>";
				 });
			   $("#cityAreaList").append(li);
			},
			beforeSend:function(data){jessrun.showLoading("#cityAreaList")},
			complete:function(data){
				jessrun.showComplete("#cityAreaList");
			},
			error:function(data){
				jessrun.showError("#cityAreaList");
			}
		});
		$("#h_proId").val(proId);
		$("#h_proName").val($(id).attr("name"));
		$("#h_cityId").val("");
		$("#h_subCityId").val("");
		$("#h_cityName").val("");
		$("#h_subCityName").val("");
	}
	//选择县级市/区
	jessrun.selectSubCity = function(id){
		$(id).parent().addClass("cur").siblings().removeClass("cur");
		var proId=$(id).attr("val");
		$.ajax( {
			type :"GET",
			url :"../area/getList.act",
			cache:false,
			async: false,
			data:{"areaPid":proId},
			success : function(data) {
				$("#subCityAreaList").empty();
			   var obj = eval("(" + data + ")");
			   if(obj.length>0){
					  $("#stateSubCityAreaList").show(); 
				   }
			   var li='';
			   $.each(obj, function(j,item){
				   li+="<li><a href='#' onclick='jessrun.selectedSubCity(this)' val='"+item.areaId+"' name='"+item.areaName+"'>"+item.areaName+"</a></li>";
				 });
			   $("#subCityAreaList").append(li);
			},
			beforeSend:function(data){jessrun.showLoading("#subCityAreaList")},
			complete:function(data){
				jessrun.showComplete("#subCityAreaList");
			},
			error:function(data){
				jessrun.showError("#subCityAreaList");
			}
		});
		$("#h_cityId").val(proId);
		$("#h_cityName").val($(id).attr("name"));
		$("#h_subCityId").val("");
		$("#h_subCityName").val("");
	}
	
	//确认县级市/去
	jessrun.selectedSubCity = function(id){
		$(id).parent().addClass("cur").siblings().removeClass("cur");
		var proId=$(id).attr("val");
		$("#h_subCityId").val(proId);
		$("#h_subCityName").val($(id).attr("name"));
	}
	//确认
	function sureArea(){
		var subCityId=$("#h_subCityId").val();
		var cityId=$("#h_cityId").val();
		var proId=$("#h_proId").val();
		if(subCityId!=''){
			$("#stateAreaId").val(subCityId);
			$("#stateAreaName").val($("#h_proName").val()+"-"+$("#h_cityName").val()+"-"+$("#h_subCityName").val());
		}else if(cityId!=''){
			$("#stateAreaId").val(cityId);
			$("#stateAreaName").val($("#h_proName").val()+"-"+$("#h_cityName").val());
		}else{
			$("#stateAreaId").val(proId);
			$("#stateAreaName").val($("#h_proName").val());
		}
		if($("#stateAreaName").val()==""){
			alert("请至少选择省份");
			return false;
		}else{
			jessrun.Dialog.removeBox();
		}
	}

var i=1;//记录开户信息数目
var stateId=0;
$(document).ready(function(){
	if($('#txt_bankNumber').val()>0){
		i=$('#txt_bankNumber').val();
	}
	 stateId=$('#txt_stateId').val();
});
function addBank(){
	//增加上级为空验证
	var flag;
	$("[id^='ddl_bank']").each(function(){
		if($(this).val()=="")
		{
			alert("上次新增站点开户银行信息未填写");
			flag=false;
			return false;
		}else{
			flag=true;
		}
	});
	if(flag){
		$.get("../dict/getAllBank.json", function(data){
			data=eval("(" + data + ")");
			if(data!=null){
				var option='';
				 $.each(data, function(j,item){
					 option+="<option value='"+item.code+"'>"+item.val+"</option>";
				 });
				var obj=$("<tr id='bank"+i+"'>" +
						"<th>站点开户银行：</th>" +
						"<td >" +
						"<span><select class='jessrun-select {required:true}' name='bicStateBankLists["+i+"].stateBankId' id='ddl_bank"+i+"' onchange=changeBank("+i+") ><option value=''>--请选择--</option>"+option+"</select><span class='requried'>*</span></span><input type='hidden' name='bicStateBankLists["+i+"].stateBankName' id='txt_stateBankName"+i+"' value=''  />" +
				       		"<input type='hidden' name='bicStateBankLists["+i+"].stateId' value='"+stateId+"'  />" +
			       		"</td>"+
			           	"<th>站点开户名：</th>" +
			           	"<td ><span><input type=text' name='bicStateBankLists["+i+"].stateBankManName' id='ddl_bankName"+i+"' class='jessrun-txt  {required:true}'/><span class='requried'>*</span></span></td>"+
			           	"<th>站点开户账号：</th>" +
			           	"<td ><span><input type='text' name='bicStateBankLists["+i+"].stateBankManCode'id='ddl_bankCode"+i+"'  class='jessrun-txt  {required:true}'/><span class='requried'>*</span></span></td>"+
			           	"<td><a href='javascript:void(0)' onclick='delBank("+i+")' class='jessrun-btn'><span>删除</span></a></td></tr>");
				obj.insertBefore("#tr_bank0");
				i++;
			}
		});
	}
}

function delBank(id){
	$("#bank"+id).remove();
}

function changeBank(id){
	$("#txt_stateBankName"+id).attr("value",$("#ddl_bank"+id+" option:selected").text());
	
}

