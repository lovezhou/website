
$(function() {

	
	changeOutsourceIdName();

	//表格高亮
	$(".mod_table_bd").tableUI();
	//时间控件
	
	
	//回执完成选择事件
	$("[id^='chk_outsourceId_']").change(function(){
		changeOutsourceIdName();
	});

	$("#chkall").change(function(){
		$("[id^='chk_outsourceId_']").attr("checked",$("#chkall").attr("checked"));
		changeOutsourceIdName();
	});
});

//更新装车单提交操作中的LoadId的Name
function changeOutsourceIdName(){
	var v="";
	$("[id^='chk_outsourceId_']").each(function(){
		var loadId=$(this).attr("loadId");
		
		if($(this).attr("checked")==true)
		{
			
			v+=loadId+",";
		}
	});
	$("#hid_os_ids").val(v);
}