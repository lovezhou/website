//根据实现id得到datagrid里面的实际table的位置
var getRealTablePosition = function (tableId){
    var $sourceTable = $("#"+tableId);
    if(sourceTable.length()>0){
      return $(".datagrid-view2 .datagrid-body>table");
    }
 };
 
 var changeCenterMenu = function (){
   $("a").click(function (){
       $(this).attr("style","color:red;");
       var mainUrl =  $(this).attr("href");
       if(mainUrl=="#"){
        return ;
       }
       if(mainUrl=="javascript:void(0);"){
         mainUrl =$(this).attr("hre"); 
       }
       $(this).attr("hre",mainUrl);
       $(this).attr("href","javascript:void(0);");
       $(this).removeAttr("target");
       $("div[region='center'] iframe",parent.document).attr("src",mainUrl);
       
   });
 };
 
 //弹出层(iframe)
 var openIframe = function (divId,title,height,width,url){
	 	$('#'+divId).css("display","block");
	    $('#'+divId).css("width",width);
	    $('#'+divId).css("height",height);
	    $('#'+divId).css("padding",5);
	    $('#'+divId).css("background","#fafafa");
    $('#'+divId).html('<iframe scrolling="yes" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>');
			$('#'+divId).window({
				title: title,
				width: width,
				modal: true,
				shadow: false,
				closed: false,
				height: height
			});
};

 //父窗口弹出层(iframe)
 var popenIframe = function (divId,title,height,width,url){
	 	parent.$('#'+divId).css("display","block");
	   parent.$('#'+divId).css("width",width);
	    parent.$('#'+divId).css("height",height);
	    parent.$('#'+divId).css("padding",5);
	    parent.$('#'+divId).css("background","#fafafa");
    	parent.$('#'+divId).html('<iframe scrolling="yes" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>');
			parent.$('#'+divId).window({
				title: title,
				width: width,
				modal: true,
				shadow: false,
				closed: false,
				height: height
			});
};

 //弹出层(div)
 var openDiv = function (divId,title,height,width){
    $('#'+divId).css("display","block");
    $('#'+divId).css("width",width);
    $('#'+divId).css("height",height);
    $('#'+divId).css("padding",5);
    $('#'+divId).css("background","#fafafa");
			 $('#'+divId).window({
				title: title,
				width: width,
				modal: true,
				shadow: false,
				closed: false,
				height: height
			});
};


 
 //alert警告窗口
 var alertWarning = function (message){
   $.messager.alert('温馨提示：',message,'warning', function(r){});
 };
  //父窗口alert警告窗口
 var palertWarning = function (message){
   parent.$.messager.alert('温馨提示：',message,'warning', function(r){});
 };
 
 //alert错误窗口
 var alertError = function (message){
   $.messager.alert('温馨提示：',message,'error', function(r){});
 };
 
 
  //alert提醒窗口
 var alertMessage = function (message){
   $.messager.alert('温馨提示：',message,'info', function(r){});
 };
 
 
   //alert问题窗口
 var alertQuestion = function (message){
   $.messager.alert('温馨提示：',message,'question', function(r){});
 };
 
    //alert成功
 var alertSucc = function (message){
   $.messager.alert('温馨提示：',message, 'success', function(r){});
 };
 
 
 //自动补全
 var autocomplete =function (selectIds){
      for(var i=0;i<selectIds.length;i++){
          $('#'+selectIds[i]).combobox();
       }
     
    
   	
 };
 
 //等待页面
 var blockwait = function (message){
    if(!message){
     message='数据加载中，请稍等......';
    }
    $.blockUI({ 
        message: message, 
          css: { 
            border: 'none', 
            padding: '15px', 
            backgroundColor: '#000', 
            '-webkit-border-radius': '10px', 
            '-moz-border-radius': '10px', 
            opacity: .5, 
            color: '#fff' 
        },
        // 遮罩样式
        overlayCSS:  { 
            backgroundColor: '#000', //颜色
            opacity:         0 //透明度
        }
    }); 
 };
 
 //取消等待页面
 var unblockwait = function (){
     $.unblockUI();
 };
 
 var checkFormRepeat = function (message){
   if(!message){
     message='表单提交中，请稍等......';
    }
    try{
     blockwait(message);
     var content ="struts\\.token\\.name";
    var $tokenName = $("input:hidden[name='"+content+"']");
    if($tokenName.length==1){
      var tokenName = $tokenName.val();
       if(tokenName){
        var $tokenValue = $("input:hidden[name='"+tokenName+"']");
        if($tokenValue.length==1){
         tokenValue = $tokenValue.val();
        }
         var flagStatus =true;
        if(tokenValue&&tokenName){
           $.ajax({
					type 	: "post",
					url     : "repeat.xhtml",
					cache   : false,
					data  : {"struts.token.name":tokenName,"struts.token":""+tokenValue}, 
					async   :  false, 
					success : function(data, status){	
						 if(data == "error"){
						     flagStatus =false
						     sAlertError("表单已经提交！")
						     unblockwait();
						  }
						  }
						});
				if(!flagStatus)	{
				  return false;
				}	
					
        }
       }
       return true;  
    }
    }catch(ex){
     unblockwait();
    }
     
 };
 
 
 
 
 //当td中文本过多的时候，做的处理
 (function($){
    $.fn.tableUI = function(options){
        var defaults = {
        }
        var options = $.extend(defaults, options);
        this.each(function(){
            var thisTable=$(this);
            thisTable.css("tableLayout","fixed");
            thisTable.find("td").each(function (){
            // $(this).attr("title",$(this).text());
             $(this).css("textOverflow","ellipsis");
             $(this).css("overflow","hidden");
             $(this).css("whiteSpace","nowrap");
            });
            thisTable.find("th").each(function (){
            // $(this).attr("title",$(this).text());
             $(this).css("textOverflow","ellipsis");
             $(this).css("overflow","hidden");
             $(this).css("whiteSpace","nowrap");
            });
            
        });
    };
})(jQuery);

//当td中文本过多的时候，做的处理
 (function($){
    $.fn.tableUI1 = function(options){
        var defaults = {
        }
        var options = $.extend(defaults, options);
        this.each(function(){
            var thisTable=$(this);
             thisTable.addClass("table06 center");
            thisTable.css("width","150%");
            thisTable.css("background","#E6EFFE");
            thisTable.css("padding-left","5px");
            thisTable.css("margin","0px auto");
        });
    };
})(jQuery);

;(function($) {
    // 看过jquery源码就可以发现$.fn就是$.prototype, 只是为了兼容早期版本的插件
    // 才保留了jQuery.prototype这个形式
    $.fn.mergeCell = function(options) {
        return this.each(function() {
            var cols = options.cols;
            for ( var i = cols.length - 1; cols[i] != undefined; i--) {
              // fixbug console调试
              // console.debug(cols[i]);
              mergeCell($(this), cols[i]);
            }
            dispose($(this));
        });
    };
    // 如果对javascript的closure和scope概念比较清楚, 这是个插件内部使用的private方法
    function mergeCell($table, colIndex) {
        $table.data('col-content', ''); // 存放单元格内容
        $table.data('col-rowspan', 1);  // 存放计算的rowspan值  默认为1
        $table.data('col-td', $());     // 存放发现的第一个与前一行比较结果不同td(jQuery封装过的), 默认一个"空"的jquery对象
        $table.data('trNum', $('tbody tr', $table).length); // 要处理表格的总行数, 用于最后一行做特殊处理时进行判断之用
         
        // 我们对每一行数据进行"扫面"处理 关键是定位col-td, 和其对应的rowspan
        $('tbody tr', $table).each(function(index) {
            // td:eq中的colIndex即列索引
            var $td = $('td:eq(' + colIndex + ')', this);
 
            // 取出单元格的当前内容
            var currentContent = $td.html();
 
            // 第一次时走此分支
            if ($table.data('col-content') == '') {
 
                $table.data('col-content', currentContent);
                $table.data('col-td', $td);
 
            } else {
                // 上一行与当前行内容相同
                if ($table.data('col-content') == currentContent) {
                    // 上一行与当前行内容相同则col-rowspan累加, 保存新值
                    var rowspan = $table.data('col-rowspan') + 1;
                    $table.data('col-rowspan', rowspan);
                    // 值得注意的是  如果用了$td.remove()就会对其他列的处理造成影响
                    $td.hide();
 
                    // 最后一行的情况比较特殊一点
                    // 比如最后2行 td中的内容是一样的, 那么到最后一行就应该把此时的col-td里保存的td设置rowspan
                    if (++index == $table.data('trNum'))
                        $table.data('col-td').attr('rowspan', $table.data('col-rowspan'));
             
                } else { // 上一行与当前行内容不同
                    // col-rowspan默认为1, 如果统计出的col-rowspan没有变化, 不处理
                    if ($table.data('col-rowspan') != 1) {
                        $table.data('col-td').attr('rowspan', $table.data('col-rowspan'));
                    }
                    // 保存第一次出现不同内容的td, 和其内容, 重置col-rowspan
                    $table.data('col-td', $td);
                    $table.data('col-content', $td.html());
                    $table.data('col-rowspan', 1);
                }
            }
        });
    }
     
    // 同样是个private函数  清理内存之用
    function dispose($table) {
        $table.removeData();
    }
})(jQuery);



jQuery.fn.CloneTableHeader = function(headdiv, bodydiv) {
   try{
      var browserName = navigator.appName;//获取浏览器信息,用于后面代码区分浏览器
    var ver = navigator.appVersion;
    var browserVersion = 8;//parseFloat(ver.substring(ver.indexOf("MSIE") + 5, ver.lastIndexOf("Windows")));
    var content = document.getElementById(bodydiv);
    var scrollWidth = content.offsetWidth - content.clientWidth;

    var headdivjquery = jQuery("#" + headdiv);//头
    var bodydivjquery = jQuery("#" + bodydiv);//内容
    var colsWidths = bodydivjquery.find("tr:first td").map(function() {
        return jQuery(this).width();
    });//动态获取每一列的宽度
    var tableCloneCols = headdivjquery.find("tr:eq(1) th");
    if (colsWidths.size() > 0) {//根据浏览器为冻结的表头宽度赋值(主要是区分IE8)
        for (i = 0; i < tableCloneCols.size(); i++) {
            if (i == tableCloneCols.size() - 1) {
                if (browserVersion == 8.0)
                    tableCloneCols.eq(i).width(colsWidths[i] + scrollWidth);
                else
                    tableCloneCols.eq(i).width(colsWidths[i]);
            } else {
                tableCloneCols.eq(i).width(colsWidths[i]);
            }
        }
    }
   }catch(e){
   
   }
 
  
}

//可以编辑的文本框进行查询
function queryTableEdit(){
         //阻止事件冒泡
       var $span = $("span.qymedit");
       $span.each(function (index, domEle){
       var $span_ = $(domEle);
       $span_.click(function(){ 
	    //将span里面的的文本内容保存
	    var spanText = $span_.text();
	    //得到td的行号
	    var cellIndex = $span_.parent().get(0).cellIndex;
	    //得到标题的头
	    var spanTitle = $span_.attr("info");
	    //将span的内容清空
	    $span_.empty();
	    //新建一个输入框
	    var input = $("<input>");
	    //将保存的文本内容赋值给输入框
	   input.attr("value",spanText);
	    //选中当前文本
	    input.get(0).select();
	    //将输入框添加到td中
	    $span_.append(input);
	    //给输入框注册事件，当失去焦点时就可以将文本保存起来
	    input.blur(function(){
	        //将输入框的文本保存
	        var input = $(this);
	        var inputText = input.val();
	        //将span的内容，即输入框去掉,然后给span赋值
	        var span = input.parent("span");
	        if(inputText){
	        span.html(inputText);
	        }else{
	        span.html(spanTitle);
	        }
	        //让span重新拥有点击事件
	       // span.click(queryTableEdit);
	    });
	    input.keyup(function(event){
	        //1.获取当前用户按下的键值
	              //解决不同浏览器获得事件对象的差异,
	             // IE用自动提供window.event，而其他浏览器必须显示的提供，即在方法参数中加上event
	           var myEvent = event || window.event;
	           var keyCode = myEvent.keyCode;
	           //2.判断是否是ESC键按下
	           if(keyCode == 27){
	               //将input输入框的值还原成修改之前的值
	               input.val(spanTitle);
	           }else if(keyCode == 13){
	               $("tbody td").each(function (){
	                    if($(this).get(0).cellIndex == cellIndex){
	                       if($(this).text().indexOf(input.val()) !=-1){
						       $(this).parent().show();
						    }else{
						        $(this).parent().hide();
						    }
	                    }
	               });
	           }
	     });
	    
	    //将输入框中的文本高亮选中
	    //将jquery对象转化为DOM对象
	    var inputDom = input.get(0);
	    inputDom.select();
	    //将span的点击事件移除
	    $span_.unbind("click");
	    //组织事件冒泡
		return false; 
	   }); 
       });
	  
	
}