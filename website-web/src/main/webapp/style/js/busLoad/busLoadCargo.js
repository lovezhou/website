var jessrun = typeof $ === "function" ? window.$ : {};

$(function() {
	//表格高亮
	$(".mod_table_bd").tableUI();
	//关闭弹出层
	jessrun.closeIframeDialog = function (){
		parent.jessrun.Dialog.removeBox();
	};

	//弹出窗口数量改变事件
	jessrun.chect_top_Number = function(cargoId){
		var depotCargoNumber=$("#txt_top_loadCargoNumber_"+cargoId).attr("depotCargoNumber");
		//获取填入值
		checkIntDefault($("#txt_top_loadCargoNumber_"+cargoId), depotCargoNumber);
		if(Number($("#txt_top_loadCargoNumber_"+cargoId).val()) == 0)
			$("#txt_top_loadCargoNumber_"+cargoId).val(depotCargoNumber);
		//如果和相关字段的加和大于最大值
		if(Number(depotCargoNumber)<Number($("#txt_top_loadCargoNumber_"+cargoId).val()))
			$("#txt_top_loadCargoNumber_"+cargoId).val(depotCargoNumber);
		var nowValue =$("#txt_top_loadCargoNumber_"+cargoId).val();
		parent.$("#txt_loadCargoNumber_"+cargoId).val(nowValue);
		//计算主体页面的合计
		parent.getSum();
	};

	//弹出窗口选择事件
	$("[id^='chk_top_']").change(function(){
		var cargoId = $(this).attr("cargoID");
		if($(this).attr("checked"))
		{
			var target_obj = parent.$("#tr_"+cargoId);
			//如果主页面元素不存在则添加
			if (target_obj.length <= 0)
			{
				var topTr = "<tr id='tr_"+cargoId+"' cargoID='"+cargoId+"'>"+$("#tr_top_"+cargoId).html()+"</tr>";
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
			parent.removeTr(cargoId);
		}
		var nowValue =$("#txt_top_loadCargoNumber_"+cargoId).val();
		parent.$("#txt_loadCargoNumber_"+cargoId).val(nowValue);
		//计算主体页面的数量合计
		parent.getSum();
		//判断主体页面的站点是否可以修改
		parent.stateReadonly();
	});

	$("#chk_all").change(function(){
		$("[id^='chk_top_']").attr("checked",$("#chk_all").attr("checked"));
		$("[id^='chk_top_']").each(function(){
			var cargoId = $(this).attr("cargoID");
			var target_obj = parent.$("#tr_"+cargoId);
			if($(this).attr("checked"))
			{
				//如果主页面元素不存在则添加
				if (target_obj.length > 0)
					parent.removeTr(cargoId);
				var topTr = "<tr id='tr_"+cargoId+"' cargoID='"+cargoId+"'>"+$("#tr_top_"+cargoId).html()+"</tr>";
				//checkbox替换成button,
				topTr = topTr.replace("checkbox","button");
				//button的ID替换成btn
				topTr = topTr.replace("chk_top_","btn_delete_tr_");
				topTr = topTr.replace(/_top_/gm,"_");
				parent.$("#tb_list").append(topTr);
			}
			else
			{
				if (target_obj.length > 0)
					parent.removeTr(cargoId);
			}
			var nowValue =$("#txt_top_loadCargoNumber_"+cargoId).val();
			parent.$("#txt_loadCargoNumber_"+cargoId).val(nowValue);
		});
		
		//计算主体页面的数量合计
		parent.getSum();
		//判断主体页面的站点是否可以修改
		parent.stateReadonly();
	});
});

//主体页面删除方法
function remove_top_Tr(cargoId)
{

}