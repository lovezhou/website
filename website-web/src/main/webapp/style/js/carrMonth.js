var jessrun = typeof $ === "function" ? window.$ : {};

$(function() {

	//表格高亮
	$(".mod_table_bd").tableUI();
	//时间控件
	

});



var jessrun = typeof $ === "function" ? window.$ : {};


$(function() {

	//表格高亮
	$(".mod_table_bd").tableUI();
	//时间控件
	



$("#chk_all").change(function(){
		$("[id^='chk_top_']").attr("checked",$("#chk_all").attr("checked"));
		$("[id^='chk_top_']").each(function(){
			var carryId = $(this).attr("carryId");
			var target_obj = parent.$("#tr_"+carryId);
			if($(this).attr("checked"))
			{
				//如果主页面元素不存在则添加
				if (target_obj.length > 0)
					parent.removeTr(carryId);
				var topTr = "<tr id='tr_"+carryId+"' carryId='"+carryId+"'>"+$("#tr_top_"+carryId).html()+"</tr>";
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
					parent.removeTr(carryId);
			}
		});
	});

//主体页面删除方法
function remove_top_Tr(carryId)
{

}

//判断站点是否可以修改
function stateReadonly()
{
	var orgaType = $("#ddl_carryStateStartId").attr("orgaType");
	//alert(orgaType);
	//if(Number(orgaType)<3)
	//	$("ddl_carryStateStartId").removeAttr("disabled");
	//$("#ddl_stateEnd").removeAttr("disabled");
	//如果主体页面上有货物信息，则不能修改
	//var lineNo = 0;

	//$("[id^='tr_']").each(function(){
		var carryId = $(this).attr("carryId");
		//$("ddl_carryStateStartId").attr("disabled",true);
		//$("#ddl_stateEnd").attr("disabled",true);
		//主体页面序号
	//	lineNo=Number(lineNo)+1;
	//	$("#span_no_"+carryId).html(lineNo);
	//});

}

//主体页面删除方法
function removeTr(carryId)
{
	/*
	if($("#chk_top_"+carryId).attr("checked")==true){
		$("#ddl_stateStart").attr("readonly",true);

		$("#ddl_stateEnd").attr("readonly",true);
	}else{
		$("#ddl_stateStart").removeAttr("readonly");
		$("#ddl_stateEnd").removeAttr("readonly");
	}
	*/
	var target_obj = $("#tr_"+carryId);
	//如果主页面元素存在则删除
	if (target_obj.length > 0)
		$("#tr_"+carryId).remove();

	//计算合计
	getSum();
	//判断主体页面的站点是否可以修改
	stateReadonly();
}

//主体页面删除方法
function remove_top_Tr(carryId)
{

}

//主体页面删除方法
function remove_Tr(carryId)
{
	removeTr(carryId);
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





})