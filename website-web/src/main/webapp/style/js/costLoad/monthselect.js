
$(function() {

	
	$("#ddl_stateStart").val($("#hid_stateStartId").val());

	//表格高亮
	$(".mod_table_bd").tableUI();
	//时间控件
	
	
	$("#chk_all").change(function(){
		$("[id^='chk_top_']").attr("checked",$("#chk_all").attr("checked"));
		$("[id^='chk_top_']").each(function(){
			var loadId = $(this).attr("loadId");
			var target_obj = parent.$("#tr_"+loadId);
			if($(this).attr("checked"))
			{
				//如果主页面元素不存在则添加
				if (target_obj.length <= 0)
				{
					var topTr = "<tr id='tr_"+loadId+"' loadId='"+loadId+"'>"+$("#tr_top_"+loadId).html()+"</tr>";
					//checkbox替换成button,
					topTr = topTr.replace("checkbox","button");
					//button的ID替换成btn
					topTr = topTr.replace("chk_top_","btn_delete_tr_");
					topTr = topTr.replace(/_top_/gm,"_");
					parent.$("#tb_list").append(topTr);
				}
			}
			else
			{
				if (target_obj.length > 0)
					parent.removeTr(loadId);
			}
		});
		//计算主体页面的数量合计
		parent.getSum();
		parent.stateReadonly();
	});
	
	//弹出窗口选择事件
	$("[id^='chk_top_']").change(function(){
		var loadId = $(this).attr("loadId");
		if($(this).attr("checked"))
		{
			var target_obj = parent.$("#tr_"+loadId);
			//如果主页面元素不存在则添加
			if (target_obj.length <= 0)
			{
				var topTr = "<tr id='tr_"+loadId+"' loadId='"+loadId+"'>"+$("#tr_top_"+loadId).html()+"</tr>";
				//checkbox替换成button,
				topTr = topTr.replace("checkbox","button");
				//button的ID替换成btn
				topTr = topTr.replace("chk_top_","btn_delete_tr_");
				topTr = topTr.replace(/_top_/gm,"_");
				parent.$("#tb_list").append(topTr);
			}
		}
		else
		{
			parent.removeTr(loadId);
		}
		//计算主体页面的数量合计
		parent.getSum();
		parent.stateReadonly();
	});
});

//上面页面删除方法
function remove_top_Tr(loadId)
{
}