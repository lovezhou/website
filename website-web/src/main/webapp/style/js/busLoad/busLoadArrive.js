var jessrun = typeof $ === "function" ? window.$ : {};

jessrun.loadCSS(jessrun.config.domain+"/style/css/dialog.css","dialog");
jessrun.loadJS(jessrun.config.domain+"/style/js/dialog-min.js",function(){
	//
},function(){
	alert("弹出层载入失败");
});
var jessrun = typeof $ === "function" ? window.$ : {};

$(function() {
	

	//获取收货站点赋值
	//$("#ddl_stateEnd").change(function(){
	//	$("#hid_stateEndId").val($(this).find("option:selected").val());
	//});


	
	$(".mod_table_bd").tableUI();
	//时间控件
	
	//提交数据
	

	jessrun.formLoadPullOut("btn_loadPullout","form_loadPullout","loadFormSubmit","发车数据保存中");
	jessrun.formLoadArrivein("btn_loadArrivein","form_loadArrivein","loadFormSubmit","签到数据保存中");

	//发车、到货签到选择事件
	$("[id^='chk_loadId_']").change(function(){
		changeLoadIdName();
	});

	$("#chk_all").change(function(){
		$("[id^='chk_loadId_']").attr("checked",$("#chk_all").attr("checked"));
		changeLoadIdName();
	});
});

//更新装车单提交操作中的LoadId的Name
function changeLoadIdName(){
	var lineNo = 0;
	var selectedNo = 0;
	$("[id^='chk_loadId_']").each(function(){
		var loadId=$(this).attr("loadId");
		$("#hid_loadId_"+loadId).removeAttr("name");
		if($(this).attr("checked"))
		{
			$("#hid_loadId_"+loadId).attr("name","loadList["+selectedNo+"].loadId");
			selectedNo=Number(selectedNo)+1;
		}
		//主体页面序号
		lineNo=Number(lineNo)+1;
		$("#span_no_"+loadId).text(lineNo);
	});
}


/**
 * 发车
 */
jessrun.formLoadPullOut=function(btnId,formId,loadClass,loadHtml){
	$("#"+btnId).click(function(){
		var v="";
		$("[id^='chk_']").each(function(){
			var loadid=$(this).attr("loadid");
			if($(this).attr("checked")==true)
			{
				v+=loadid+",";
				$(this).attr("checked","checked");
				
			}else{
				$(this).removeAttr("checked");
				
			}
		});
		if ($.trim(v) == "") {     
	    	alert("请至少勾选一项");    
	        return;     
	    } 
		if ($("#"+formId).valid()) {
			
			$("#"+btnId).hide();
			$("<span class='"+loadClass+"'><span><em>"+loadHtml+"</em></span></span>").insertAfter("#"+btnId);
			function doSubmit(){
				$("#hid_logStaffName").val($("#ddl_LogStaffId").find("option:selected").text());
				$("#"+formId).submit();
				}
			setTimeout(doSubmit,0);
		}
		
	});
};
/**
 * 到货签到
 */
jessrun.formLoadArrivein=function(btnId,formId,loadClass,loadHtml){
	$("#"+btnId).click(function(){
		if ($("#"+formId).valid()) {
		$("#"+btnId).hide();
		$("<span class='"+loadClass+"'><span><em>"+loadHtml+"</em></span></span>").insertAfter("#"+btnId);
		function doSubmit(){
			$("#hid_logStaffName").val($("#ddl_LogStaffId").find("option:selected").text());
			$("#hid_depotName").val($("#ddl_depotId").find("option:selected").text());
			
			$("#"+formId).submit();
		}
		setTimeout(doSubmit,0);
		}
	});
};