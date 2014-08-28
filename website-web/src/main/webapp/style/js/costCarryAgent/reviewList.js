
	$("[id^='chk_']").change(function(){
		changeCarryIdName();
	});

	$("#chkall").change(function(){
		$("[id^='chk_']").attr("checked",$("#chkall").attr("checked"));
		changeCarryIdName();
	});
	
	   //经办员工隐藏域
	$("#ddl_LogStaffId").change(function(){
		$("#hid_logStaffName").val($(this).find("option:selected").text());
		$("#LogStaffId").val($(this).find("option:selected").val());
	});
	


function changeCarryIdName(){
	var v="";
	$("[id^='chk_']").each(function(){
		var carryId=$(this).attr("carryId");
		if($(this).attr("checked")==true)
		{
			v+=carryId+",";
			$(this).attr("checked","checked");
		}else{
			$(this).attr("checked","");
		}
	});
	
	$("#hd_applyCostCarryValue").attr("value",v);
}