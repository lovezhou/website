jessrun.Dialog=function(b){Dialogarr=new Array();var a=function(g){var e="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";for(var c=0,f="";c<g;c++){f+=e.charAt(Math.floor(Math.random()*62))}return f};defaults=$.extend({title:"",boxID:a(10),closeBtn:"",referID:"",content:"text:hello word",width:"",height:"",time:"",drag:false,resize:false,dscope:true,lockX:false,lockY:false,fixed:true,head:true,showbg:true,showtitle:true,borderopacity:"0.3",bordercolor:"#409DD6",button:"",callback:"",offsets:"",ofns:"",cfns:""},b);jessrun.Dialog.init(defaults)};$.extend(jessrun.Dialog,{init:function(c){BOXID=c;if($("#"+c.boxID).length>0){jessrun.Dialog({title:dialog.txt.tit_error,content:"text:"+jessrun.callBack.error(""+dialog.txt.call_error[0]+""+c.boxID+""+dialog.txt.call_error[1]+"")+"",button:[dialog.txt.tit_error]});return false}var b=$("#"+c.boxID);b.die().live("mouseenter",function(){BOXID=c});$(".ui_close,"+c.closeBtn,b).live("click",function(){jessrun.Dialog.removeBox();return false});if(c.showbg!=""&&c.showbg==true){var a='<div id="maskDialog" style="position:absolute;background:#000;filter:alpha(opacity=20);opacity:0.2;width:100%;left:0;top:0;z-index:870610;"><iframe src="about:blank" style="width:100%;height:'+$(document).height()+'px;filter:alpha(opacity=20);opacity:0.2;scrolling=no;border:none;z-index:870611;"></iframe></div>';$(a).appendTo("body").animate({opacity:0.1},200)}jessrun.Dialog.createWindows(c);jessrun.Dialog.loadContent(c);if(c.button!=""){jessrun.Dialog.dialog(c)}jessrun.Dialog.setDialogIndex(c);jessrun.Dialog.keyDown(c);if(typeof c.time==="number"){setTimeout(function(){jessrun.Dialog.removeBox()},c.time)}$(window).resize(function(){jessrun.Dialog.setPosition(c)});if(c.fixed){jessrun.fixed(c.boxID)}},createWindows:function(c){var a='<div id="'+c.boxID+'" class="ui_dialog">';a+='<table class="ui_table_wrap" cellspacing="0" cellpadding="0" border="0"><tbody>';a+='<tr><td class="ui_border ui_td_00"></td><td class="ui_border ui_td_01"></td><td class="ui_border ui_td_02"></td></tr>';a+='<tr><td class="ui_border ui_td_10"></td><td class="ui_td_11"><table class="ui_dialog_main" cellspacing="0" cellpadding="0" border="0"><tbody>';a+='<tr><td><div class="ui_title_wrap"><div class="ui_title"><div class="ui_title_text"><span class="ui_title_icon"></span><span class="ui_title_con">\u93cd\u56ec\ue57d</span></div><a href="#"class="ui_close">x</a></div></div></td></tr>';a+='<tr><td class="ui_content_wrap">';a+='<div class="ui_content"  id="'+c.boxID+'content"></div>';a+="</td></tr>";a+='<tr><td class="ui_bottom_wrap"><div class="ui_bottom"><div class="ui_btns"></div><div class="ui_resize"></div></div></td></tr></tbody></table>';a+='</td><td class="ui_border ui_td_12"></td></tr>';a+='<tr><td class="ui_border ui_td_20"></td><td class="ui_border ui_td_21"></td><td class="ui_border ui_td_22"></td></tr></tbody></table>';a+='<iframe src="about:blank" class="ui_iframe" style="position:absolute;left:0;top:0;width:100%;height:100%; filter:alpha(opacity=0);opacity:0; scrolling=no;border:none;z-index:10714;"></iframe>';a+="</div>";$(a).appendTo("body");var b=$("#"+c.boxID);b.find(".ui_content").css({position:"relative"});b.find(".ui_title_text").html("<span class=ui_title_icon></span><span class=ui_title_con>"+c.title+"</span>");b.find(".ui_close").addClass("ui_close_ico");if(c.showtitle!=true){if(c.showtitle=="remove"){$(".ui_title_wrap",b).remove()}else{$(".ui_title_text",b).css({background:"none",border:"none",boxShadow:"none"})}}if(c.borderopacity!=""){b.find(".ui_border").css({opacity:c.borderopacity})}if(c.bordercolor!=""){b.find(".ui_dialog_main").css({borderWidth:"1px",borderStyle:"solid",borderColor:c.bordercolor})}jessrun.Dialog.setPosition(c)},loadContent:function(d){var c=$("#"+d.boxID);var a=$(".ui_content",c);$contentType=d.content.substring(0,d.content.indexOf(":"));$content=d.content.substring(d.content.indexOf(":")+1,d.content.length);$.ajaxSetup({global:false});switch($contentType){case"text":a.html($content);jessrun.Dialog.setPosition(d);if(d.ofns!=""&&$.isFunction(d.ofns)){d.ofns(this)}break;case"id":$("#"+$content).children().appendTo(a);jessrun.Dialog.setPosition(d);if(d.ofns!=""&&$.isFunction(d.ofns)){d.ofns(this)}break;case"img":$.ajax({beforeSend:function(){c.find(".ui_content").css({padding:"20px 0px",textAlign:"center"});a.html("<span class='ui_box_loading'></span><span class='ui_box_loading_info'>"+dialog.txt.load_tips+"</span>")},error:function(){c.find(".ui_content").css({padding:"0"});c.find(".ui_content_wrap").css({width:d.width,height:d.height});a.html("<p class='ui_box_error'><span class='ui_box_callback_error'></span>"+dialog.txt.load_error+"</p>");jessrun.Dialog.setPosition(d)},success:function(e){jessrun.imgReady($content,function(){c.find(".ui_content").css({padding:"0"});c.find(".ui_content_wrap").css({width:this.width,height:this.height});a.html("<img src="+$content+" alt='' />");jessrun.Dialog.setPosition(d)});if(d.ofns!=""&&$.isFunction(d.ofns)){d.ofns(this)}}});break;case"swf":$.ajax({beforeSend:function(){c.find(".ui_content").css({padding:"20px 0px",textAlign:"center"});a.html("<span class='ui_box_loading'></span><span class='ui_box_loading_info'>"+dialog.txt.load_tips+"</span>")},error:function(){c.find(".ui_content").css({padding:"0"});c.find(".ui_content_wrap").css({width:d.width,height:d.height});a.html("<p class='ui_box_error'><span class='ui_box_callback_error'></span>"+dialog.txt.load_error+"</p>");jessrun.Dialog.setPosition(d)},success:function(e){c.find(".ui_content").css({padding:"0"});a.html("<div id='"+d.boxID+'swf\'><h1>Alternative content</h1><p><a href="http://www.adobe.com/go/getflashplayer"><img src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif" alt="Get Adobe Flash player" /></a></p></div><script type="text/javascript">swfobject.embedSWF(\''+$content+"', '"+d.boxID+"swf', '"+d.width+"', '"+d.height+"', '9.0.0', 'expressInstall.swf');<\/script>");$("#"+d.boxID+"swf").css({position:"absolute",left:"0",top:"0",textAlign:"center"});c.find(".ui_content_wrap").css({width:d.width,height:d.height});jessrun.Dialog.setPosition(d);if(d.ofns!=""&&$.isFunction(d.ofns)){d.ofns(this)}}});break;case"url":var b=$content.split("?");$.ajax({beforeSend:function(){c.find(".ui_content").css({padding:"20px 0px"});a.html("<span class='ui_box_loading'></span><span class='ui_box_loading_info'>"+dialog.txt.load_tips+"</span>")},type:b[0],url:b[1],data:b[2],error:function(){c.find(".ui_content").css({padding:"0"});c.find(".ui_content_wrap").css({width:d.width,height:d.height});a.html("<p class='ui_box_error'><span class='ui_box_callback_error'></span>"+dialog.txt.load_error+"</p>");jessrun.Dialog.setPosition(d)},success:function(e){c.find(".ui_content").css({padding:"0"});c.find(".ui_content_wrap").css({width:d.width,height:d.height});a.html(e);jessrun.Dialog.setPosition(d);if(d.ofns!=""&&$.isFunction(d.ofns)){d.ofns(this)}}});break;case"iframe":a.css({overflowY:"hidden"});$.ajax({beforeSend:function(){c.find(".ui_content").css({padding:"20px 0px"});a.html("<span class='ui_box_loading'></span><span class='ui_box_loading_info'>"+dialog.txt.load_tips+"</span>")},error:function(){c.find(".ui_content").css({padding:"0"});c.find(".ui_content_wrap").css({width:d.width,height:d.height});a.html("<p class='ui_box_error'><span class='ui_box_callback_error'></span>"+dialog.txt.load_error+"</p>");jessrun.Dialog.setPosition(d)},success:function(f){c.find(".ui_content").css({padding:"0"});c.find(".ui_content_wrap").css({width:d.width,height:d.height});a.html('<iframe src="'+$content+'" id="'+d.boxID+'frame" width="'+d.width+'"  height="'+d.height+'"scrolling="auto" frameborder="0" marginheight="0" marginwidth="0"></iframe>');$("#"+d.boxID+"frame").bind("load",function(){setInterval(e,200);jessrun.Dialog.setPosition(d)});var e=function(){var l=document.getElementById(d.boxID+"frame");try{var i=l.contentWindow.document,g=Math.max(i.body.scrollWidth,i.documentElement.scrollWidth);bodyHeight=Math.max(i.body.scrollHeight,i.documentElement.scrollHeight);var j=bodyHeight!=l.height?17:0;if(g!=l.width){l.width=d.width||g+j}if(bodyHeight!=l.height){l.height=d.height||bodyHeight}}catch(k){return false;l.width=800;l.height=450}};if(d.ofns!=""&&$.isFunction(d.ofns)){d.ofns(this)}}})}},setPosition:function(c){var d=jessrun.safeRange(c.boxID);var l,a=$("#"+c.boxID),g=a.find(".ui_content_wrap"),m=jessrun.pageSize.get(),n=c.offsets;var e=!n.right?n.left:m.clientWidth-d.width-parseInt(n.right),i=!n.bottom?n.top:m.clientHeight-d.height-parseInt(n.bottom);if(c.referID!=""){c.fixed=false;var f=jessrun.getPosition(c.referID,"html"),b=$("#"+c.referID).outerWidth(),j=$("#"+c.referID).outerHeight();e=!n.right?parseInt(e)+f.x:f.x+b-d.width-parseInt(n.right);i=!n.bottom?parseInt(i)+f.y:f.y+j-d.height-parseInt(n.bottom)}if(c.fixed){l=0;jessrun.fixed(c.boxID)}else{l=m.scrollTop;a.css({position:"absolute"})}l=c.head?0:m.scrollTop;if(c.offsets!=""){switch(e){case"left":e=d.minX;break;case"right":e=d.maxX;break;case"center":e=d.centerX;break}switch(i){case"top":i=d.minY;break;case"bottom":i=d.maxY;break;case"center":i=d.centerY;break}a.css({left:e,top:parseInt(i)+l,zIndex:"891208"})}else{a.css({left:d.centerX,top:d.centerY+l+"px",zIndex:"891208",display:"block"})}var k=$(".ui_iframe",a);k.css({width:d.width+"px",height:d.height+"px"});if(c.drag){$(".ui_title_wrap",a).die().live("mouseover",function(){jessrun.drag({obj:c.boxID,handle:".ui_title",lock:c.dscope,lockX:c.lockX,lockY:c.lockY,fixed:c.fixed});$(this).css("cursor","move")})}if(c.resize){a.find(".ui_resize").show();jessrun.Dialog.resizeBox(c)}},dialog:function(f){var e=$("#"+f.boxID);if(f.button!=""){var d={},c=[];if(f.button instanceof Array){for(var b=0;b<f.button.length;b++){d[f.button[b]]=f.button[b];c.push(f.button[b])}}else{for(var a in f.button){d[f.button[a]]=a;c.push(f.button[a])}}$(".ui_btns",e).show().html($.map(c,function(g){if(g==dialog.txt.btn_cancel||g==dialog.txt.btn_close||g==dialog.txt.btn_reset){return"<a href='javascript:;' class='ui_box_btn2' title='"+g+"' ><span>"+g+"</span></a>"}else{return"<a href='javascript:;' class='ui_box_btn' title='"+g+"' ><span>"+g+"</span></a>"}}).join(" "));$(".ui_box_btn2",".ui_bottom").click(function(){jessrun.Dialog.removeBox()});$(".ui_box_btn",".ui_bottom").click(function(){if(f.callback!=""&&$.isFunction(f.callback)){f.callback(this)}})}},resizeBox:function(b){res=false;var c=jessrun.safeRange(b.boxID);var a=$("#"+b.boxID);$Handle=$(".ui_resize",a);var g=b.button!=""?a.find(".ui_bottom",a).outerHeight():"0";var j=a.find(".ui_content_wrap",a),m=jessrun.pageSize.get();var k=jessrun.Browser.isIE?2:0;var e=j.outerWidth()-k,l=j.outerHeight();$Handle.mousedown(function(n){f(n)});var f=function(n){res=true;n=n||window.event;$(document).bind("mousemove",function(o){d(o)});$(document).bind("mouseup",function(){i()})};var d=function(t){var o=$(".ui_bottom",a).height();var s=$(".ui_title_wrap",a).height();var r=$(".ui_border").width()*2;var q=jessrun.getPosition(b.boxID,"html");var u=m.clientWidth-q.x,n=m.clientHeight-q.y-o-s-r;M=jessrun.getPosition(b.boxID+"content","html");if(res){t=t||window.event;window.getSelection?window.getSelection().removeAllRanges():document.selection.empty();w=M.width+t.clientX-M.x-M.width,h=M.height+t.clientY-M.y-M.height-o;if(w>e&&w<u){j.css("width",w)}if(h>l&&h<n){j.css("height",h)}}};var i=function(){res=false;$(document).unbind("mousemove");var n=jessrun.safeRange(b.boxID);var o=$(".ui_iframe",a);o.css({width:n.width+"px",height:n.height+"px"})}},setDialogIndex:function(c){Dialogarr.push(document.getElementById(c.boxID));var a="mousedown"||"mousemove"||"mouseup"||"click";var b=$("#"+c.boxID);b.live(a,function(){for(var d=0;d<Dialogarr.length;d++){Dialogarr[d].style.zIndex=870618}this.style.zIndex=891208})},removeBox:function(){var c=$("#"+BOXID.boxID);var b=$("#maskDialog");if(c.length!=0||b.length!=0){var a=$(".ui_content",c);$("iframe",c).remove();$contentType=BOXID.content.substring(0,BOXID.content.indexOf(":"));$content=BOXID.content.substring(BOXID.content.indexOf(":")+1,BOXID.content.length);if($contentType=="id"){a.children().appendTo("#"+$content);c.remove();if(jessrun.Browser.isIE6){c.parent().remove()}if(c.length==1){b.animate({opacity:"0"},500,function(){$(this).remove()})}}else{c.remove();if(jessrun.Browser.isIE6){c.parent().remove()}if(c.length==1){b.animate({opacity:"0"},500,function(){$(this).remove()})}}}if(BOXID.cfns!=""&&$.isFunction(BOXID.cfns)){BOXID.cfns(this)}},keyDown:function(){$(window).keydown(function(a){if(a.keyCode==27){jessrun.Dialog.removeBox()}})}});jessrun.drag=function(b){var g={obj:"",handle:"",lock:true,lockX:false,lockY:false,fixed:false,parent:"",sfns:function(){},mfns:function(){},ofns:function(){}};var b=$.extend(g,b);var i=false;var c=jessrun.safeRange(b.obj);var a=$("#"+b.obj);var f=0,d=0,m,k;if(b.fixed){if(b.parent!=""){b.parent=""}}if(b.parent!=""){$("#"+b.parent).css("position","relative")}if(b.handle!=""){$Handle=$(b.handle,a)}else{$Handle=a}$Handle.css("cursor","move");$Handle.mousedown(function(n){j(n);this.setCapture()});var j=function(o){i=true;if(b.sfns!=""&&$.isFunction(b.sfns)){b.sfns(this)}o=o||window.event;o.preventDefault();var n=jessrun.pageSize.get(),q=jessrun.getPosition(b.obj,"html");st=jessrun.Browser.isIE6?n.scrollTop:0;ny=b.fixed?jessrun.Browser.isIE6?n.scrollTop:0:0;f=o.clientX-q.x;d=o.clientY-q.y+ny;$(document).bind("mousemove",function(r){e(r)});$(document).bind("mouseup",function(){l()})};var e=function(o){o=o||window.event;window.getSelection?window.getSelection().removeAllRanges():document.selection.empty();m=o.clientX-f;k=o.clientY-d;if(b.lockX){m=p.x}if(b.lockY){k=p.y}if(b.lock){if(m<=0){m=c.minX}if(k<=0){k=c.minY}if(m>=c.maxX){m=c.maxX}if(k>=c.maxY){k=c.maxY}}if(b.parent!=""){var n=jessrun.getID(b.parent).clientWidth-p.width;var q=jessrun.getID(b.parent).clientHeight-p.height;if(m>=n){m=n}if(k>=q){k=q}}a.css({left:m+"px",top:k+"px",right:"auto",bottom:"auto",margin:"auto"});if(b.mfns!=""&&$.isFunction(b.mfns)){b.mfns(this)}$("#idShow").html("maxLeft ="+n+"; maxTop = "+q+";clientX = "+o.clientX+";clientY = "+o.clientY+"; ST = "+st+"; X = "+m+";Y = "+k)};var l=function(){i=false;$(document).unbind("mousemove");if(b.ofns!=""&&$.isFunction(b.ofns)){b.ofns(this)}document.releaseCapture()}};
