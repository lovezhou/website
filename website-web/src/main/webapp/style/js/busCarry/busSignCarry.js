var jessrun = typeof $ === "function" ? window.$ : {};

$("#chk_all").change(function(){
	$("[id^='chk_top_']").attr("checked",$("#chk_all").attr("checked"));
	$("[id^='chk_top_']").each(function(){
		var carryId = $(this).attr("carryId");
		var target_obj = parent.$("#tr_"+carryId);
		if($(this).attr("checked"))
		{
			//如果主页面元素不存在则添加
			if (target_obj.length <= 0)
			{
				var topTr = "<tr id='tr_"+carryId+"' carryId='"+carryId+"'>"+$("#tr_"+carryId).html()+"</tr>";
				//checkbox替换成button,
				topTr = topTr.replace("checkbox","button");
				//button的ID替换成btn
				topTr = topTr.replace("chk_top_","btn_delete_tr_");
				topTr = topTr.replace(/_top_/gm,"_");
				parent.$("#tbl_carryList").append(topTr);
			}
		}
		else
		{
			if (target_obj.length > 0)
				parent.removeTr(carryId);
		}
	});
	//计算主体页面的数量合计
	//parent.getSum();
	//parent.stateReadonly();
});

//弹出窗口选择事件
$("[id^='chk_top_']").change(function(){
	var carryId = $(this).attr("carryId");
	if($(this).attr("checked"))
	{
		var target_obj = parent.$("#tr_"+carryId);
		//如果主页面元素不存在则添加
		if (target_obj.length <= 0)
		{
			var topTr = "<tr id='tr_"+carryId+"' carryId='"+carryId+"'>"+$("#tr_"+carryId).html()+"</tr>";
			//checkbox替换成button,
			topTr = topTr.replace("checkbox","button");
			//button的ID替换成btn
			topTr = topTr.replace("chk_top_","btn_delete_tr_");
			topTr = topTr.replace(/_top_/gm,"_");
			parent.$("#tbl_carryList").append(topTr);
		}
	}
	else
	{
		parent.removeTr(carryId);
	}
	//计算主体页面的数量合计
	//parent.getSum();
	//parent.stateReadonly();
});


//主体页面删除方法
function removeTr(cargoId)
{
	if($("#chk_top_"+cargoId).attr("checked")==true){
		$("#ddl_stateEnd").attr("readonly",true);

		$("#ddl_stateStart").attr("readonly",true);
	}else{
		$("#ddl_stateEnd").removeAttr("readonly");
		$("#ddl_stateStart").removeAttr("readonly");
	}
	var target_obj = $("#tr_"+cargoId);
	//如果主页面元素存在则删除
	if (target_obj.length > 0)
		$("#tr_"+cargoId).remove();
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
