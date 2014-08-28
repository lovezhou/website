var dialog = typeof $ === "function" ? window.$ : {};
dialog.txt = {
	load_tips : "数据加载中",
	load_error : "数据加载出错",
	tit_error : "出错了",
	btn_conf : "确定",
	btn_cancel : "取消",
	btn_close : "关闭",
	btn_reset : "重置",
	call_error : [ "对不起，创建弹出层失败！窗口", "已存在！" ]
};

jessrun.loadCSS("css/dialog.css", "dialog");
jessrun.loadJS("js/dialog-min.js", function() {

}, function() {

});

jessrun.showDialog = function(id, width, height, title, url, focusId){
	var cs = "?";//默认url没有携带参数
	if(url.indexOf("?", 0) > 0){cs = "&";}//判断url带有参数
	cs += "ggcs.id="+$(id).attr("id")+"&";
	var tid = $(id).attr("relId");//获取目标文本域id
	typeof tid == 'undefined' ? "" : cs += "ggcs.targetFieldId="+tid+"&";
	var srcprop = $(id).attr("srcprop");//指定带回哪些属性的值
	typeof srcprop == 'undefined' ? "" : cs += "ggcs.srcFieldId="+srcprop+"&";
	var multiChoise = $(id).attr("multi");//是否允许回传多个值
	typeof multiChoise == 'undefined' ? "" : cs += "ggcs.multiChoise="+multiChoise+"&";
	var multiMax = $(id).attr("multiMax");//限制回传值的个数
	typeof multiMax == 'undefined' ? "" : cs += "ggcs.multiMax="+multiMax+"&";
	
	if(cs.length <= 2){//无请求参数
		cs = "";
	}else{
		cs = cs.substring(0, cs.length-1);
	}
	//打个dialog
	jessrun.Dialog({
         title: title,
         width: width,
         height: height,
         showbg: true,
         content:"iframe:"+url+cs,
         cfns:function(){
        	 $("#"+focusId).focus();
         }
	});
};

jessrun.openDialog = function(width, height, title, url, focusId){
	//打个dialog
	jessrun.Dialog({
         title: title,
         width: width,
         height: height,
         showbg: true,
         content:"iframe:"+url,
         cfns:function(){
        	 $("#"+focusId).focus();
         }
	});
	return false;
};

//关闭弹出层
jessrun.closeIframeDialog = function (){
	parent.jessrun.Dialog.removeBox();
	return false;
};

jessrun.targetSelect = function(target, multi){
	var aTarget = target.split(","); //解析父页面中relId属性的值
	var size = 0;
	var index = 0;
	var aVal = new Array();
	
	$("input[id^='chk_']:checked").each(function(){
		if($(this).attr("relVal") != ""){
			var sVal = $(this).attr("relVal").split(",");
			if(index == 0){
				size = aTarget.length-sVal.length > 0 ? sVal.length : aTarget.length; //确认需要从子页面带回多少个属性的值
				aVal = new Array(size);
				index = index + 1;
			}
			//拼接返回值
			for(var i = 0; i < size; i++){
				if( typeof(aVal[i]) == "undefined"){
					aVal[i] = "";
				}
				aVal[i] += sVal[i];
				aVal[i] += ",";
			}
		}
	});
	//向父页面赋值
	if(size == 0){
		size = aTarget.length;
		for(var i = 0; i < size; i++){
			parent.$("#"+aTarget[i]).attr("value", "");
		}
	}else{
		for(var i = 0; i < size; i++){
			parent.$("#"+aTarget[i]).attr("value", aVal[i].substring(0, aVal[i].length-1));
		}
	}
	jessrun.closeIframeDialog();
	return false;
};

//树形结构选择返回
jessrun.treeSelect = function(target, aVal){
	var aTarget = target.split(",");
	var rval = aVal.split(",");
	for(var i = 0; i < 2; i++){
		parent.$("#"+aTarget[i]).attr("value", rval[i]);
	}
	jessrun.closeIframeDialog();
	return false;
};

///验证实数
function checkFloat(val,i)
{
	//如果为空，则为0
	var v = val;
	if(v=="")
		v="0";
	//如果不是数字
	else if(isNaN(v))
		v="0";
	//如果小于0
	else if(Number(v)<0)
		v="0";
	//取i小数
	return Number(v).toFixed(i);
}

/**
 * 前端编辑小工具
 */
jessrun.openEditWindow = function (url,name,width,height,focusId){
	//打个dialog
	jessrun.Dialog({
         title: name,
         width: width,
         height: height,
         showbg: true,
         content:"iframe:"+url,
         cfns:function(){
        	 $("#"+focusId).focus();
        	 return true;
         }
	});
	return false;
};

/**
 * 查看货物详情
 * @param url
 * @param name
 */
function showHwDetail(url,name){
	
	var iWidth = 980; //弹出窗口的宽度;
	var iHeight = 540; //弹出窗口的高度;
	var iTop = (window.screen.availHeight-30-iHeight)/2; 	//获得窗口的垂直位置;
	var iLeft = (window.screen.availWidth-10-iWidth)/2;	 	//获得窗口的水平位置;
	window.open(url,name,'height='+iHeight+' ,width='+iWidth+' ,top='+iTop+',left='+iLeft+',resizable=yes, scrollbars=yes');
}

/**
 * 对table的行注册点击事件
 */
$(function(){
	$("table.rclick td").not("td.nclick").click(function(){
		var $chkbox = $(this).closest("tr").find("td .check:enabled:first");
		if(typeof $chkbox != undefined){
			$chkbox.attr("checked",!$chkbox.attr("checked"));
			$chkbox.change();
		}
	});
});