function bandDsTable(id, flag){
			var tab=document.getElementById(id);//TABLE到手
			if(!tab)
			{
				return;
			}
			if(flag == undefined){
				flag = true;
			}
			
			for(var i=0;i<tab.rows.length;i++){//按行来循环,不存在兼容问题
			   if(tab.rows[i].claName){
			    tab.rows[i].claName="";
			   }
			   if(i%2==0){//这个不用我解释了吧...
					tab.rows[i].className="alt";//间隔行的样式
					tab.rows[i].claName="alt";//另外用个属性把这个样式名保存
			   }
			   if(flag){
					tab.rows[i].onmouseover=function(){//添加鼠标事件
						this.className="over";//变成高亮样式
					}
					tab.rows[i].onmouseout=function(){//鼠标移走咯....
						this.className=this.claName;//把保存的属性还原
					}
			   }
			}
		}
var cxf={
//公共提交方法, 此方法包含 重复提交判断
IsSubmit:function(formName,btn)
{
	var Bform=document[formName];
	if(!Bform)
	{
		alert("参数传入错误, 请传入你要提交的form名称!");
		return false;	
	}
	
	$('#'+btn).linkbutton({disabled:true});//确定不可用
	 if ($("#"+formName).form('validate')){
		 Bform.submit();
	 }else{
		 $.messager.alert('温馨提示：','验证失败,请填写相关内容！','warning'); 
		 $('#'+btn).linkbutton({disabled:false});
		 return false;
	 }
},
IsPSubmit:function(formName,btn)
{
	var Bform=document[formName];
	if(!Bform)
	{
		alert("参数传入错误, 请传入你要提交的form名称!");
		return false;	
	}
	
	$('#'+btn).linkbutton({disabled:true});//确定不可用
	 if ($("#"+formName).form('validate')){
		 Bform.submit();
	 }else{
		 parent.$.messager.alert('温馨提示：','验证失败,请填写相关内容！','warning'); 
		 $('#'+btn).linkbutton({disabled:false});
		 return false;
	 }
},
showMessage:function(aMessage){
	$.messager.alert('温馨提示：',aMessage,'info'); 
},
pshowMessage:function(aMessage){
	parent.$.messager.alert('温馨提示：',aMessage,'info'); 
},
ajaxGet:function(url,params,func)
{
	$.get(url,params,func);
},
ajaxPost:function(url,params,func){
	$.post(url,params,func);
},
ajaxAsync:function(url,params,func){
	$.ajax({
		  type: "POST",
		  cache: false,
		  async: false,
		  url:url,
		  data:params,
		  success: func
		 });
},
 trimkeyup:function(e) {
    lucene_objInput = $(this);
    if (e.keyCode != 38 && e.keyCode != 40 && e.keyCode != 13) {
        var im = $.trim(lucene_objInput.val());
        lucene_objInput.val(im); 
    }
},
setCurrentcy:function(s){
		s = String(s);
		if(s.indexOf('-')==0){
		   //计算负数
		   s= s.substring(1,s.length);
		   if(/[^0-9\.\-]/.test(s)) return "0.00";
		   s =(Math.round(s*100)/100).toString();
		   s=s.replace(/^(\d*)$/,"$1.");
		
		   s=(s+"00").replace(/(\d*\.\d\d)\d*/,"$1");//取小数点后两位
		   var re=/(\d)(\d{3},)/;
		   while(re.test(s))
		    s=s.replace(re,"$1,$2");
		    s=s.replace(/,(\d\d)$/,".$1");//取小数点后两位
		   
		   return '-'+s.replace(/^\./,"0.")
		}else{
		   //计算正数
		   if(/[^0-9\.\-]/.test(s)) return "0.00";
		   s =(Math.round(s*100)/100).toString();
		   s=s.replace(/^(\d*)$/,"$1.");
		
		   s=(s+"00").replace(/(\d*\.\d\d)\d*/,"$1");//取小数点后两位
		   var re=/(\d)(\d{3},)/;
		   while(re.test(s))
		    s=s.replace(re,"$1,$2");
		    s=s.replace(/,(\d\d)$/,".$1");//取小数点后两位
		   
		   return s.replace(/^\./,"0.")
		}
	}
}

var iframeObj = "";
function loadApplet(url,print) {
	if(iframeObj != "")
		var object = document.getElementById(iframeObj);
  		if(object != null && object != undefined)
  		$("#" + iframeObj).remove();
		//removeObject(iframeObj);//删除之前打开的iframe
	iframeObj = "iframe";
	var obj = document.getElementById(iframeObj);
	var i = 0;
	while(obj == undefined || obj == null) {
		iframeObj += i + "";
		obj = $("#"+iframeObj);
		i++;
	}
	$("body").append("<iframe id='"+iframeObj+"' name='"+iframeObj+"' style='display:none;'></iframe>");
	var isTest = "";
	if($.trim(url) == "")
		isTest = "prestrain";
	var applet = "<APPLET  CODEBASE ='./ireport'"+ 
						" ARCHIVE = 'reportprint.jar,jasperreports-4.7.0.jar,commons-logging-1.1.jar,commons-digester-2.1.jar,commons-collections-3.2.1.jar,poi-3.7-20101029.jar,iTextAsian.jar,iText-2.1.4.jar'"+
						" CODE = 'com.cxf.ssj.print.PrintApplet.class'>"+
					    "<PARAM NAME = 'REPORT_URL' value='"+url+"'/>"+
					    "<PARAM NAME = 'REPORT_PRINT' value='"+print+"'/>"+
					    "<param name='PRESTRAIN_JRE' value='"+isTest+"' />"+
					    "<PARAM NAME= 'type' VALUE='application/x-java-applet;version=1.6.0'>"+
					"</APPLET>";
	var iframe = document.getElementById(iframeObj).contentWindow.document;//获取iframe的document对象
	iframe.write(applet);
}

//表单验证扩展
$.extend($.fn.validatebox.defaults.rules, {
	   idcard : {// 验证身份证 
	        validator : function(value) { 
	            return /^\d{15}(\d{2}[A-Za-z0-9])?$/i.test(value); 
	        }, 
	        message : '身份证号码格式不正确!' 
	    },
	    kongStr: {
	        validator: function(value){
	            return $.trim(value).length >0;
	        },
	        message: '内容不能为空字符串!'
	    },
	      minLength: {
	        validator: function(value, param){
	            return $.trim(value).length >= param[0];
	        },
	        message: '请输入至少{0}个字符!'
	    },
	    length:{
	    	validator:function(value,param){ 
	        var len=$.trim(value).length; 
	            return len>=param[0]&&len<=param[1]; 
	        }, 
	            message:"输入内容长度必须介于{0}和{1}之间!" 
	        }, 
	       lxdh:{
	    	validator:function(value,param){ 
	        var len=$.trim(value).length; 
	            return len>=param[0]&&len<=param[1]&&/^[+]?[0-9]+\d*$/i.test(value); 
	        }, 
	            message:"输入内容长度必须介于{0}和{1}之间的数字!" 
	        }, 
	    phone : {// 验证电话号码 
	        validator : function(value) { 
	            return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value); 
	        }, 
	        message : '格式不正确,请使用下面格式:020-12345678!' 
	    }, 
	    mobile : {// 验证手机号码 
	        validator : function(value) { 
	            return /^(13|15|18)\d{9}$/i.test(value); 
	        }, 
	        message : '手机号码格式不正确!' 
	    }, 
	    intOrFloat : {// 验证整数或小数 
	        validator : function(value) { 
	            return /^\d+(\.\d+)?$/i.test(value); 
	        }, 
	        message : '请输入数字，并确保格式正确!' 
	    }, 
	    current : {// 验证货币 
	        validator : function(value) { 
	            return /^\d+(\.\d+)?$/i.test(value); 
	        }, 
	        message : '货币格式不正确!' 
	    }, 
	    qq : {// 验证QQ,从10000开始 
	        validator : function(value) { 
	            return /^[1-9]\d{4,9}$/i.test(value); 
	        }, 
	        message : 'QQ号码格式不正确!' 
	    }, 
	    integer : {// 验证整数 
	        validator : function(value) { 
	            return /^[+]?[1-9]+\d*$/i.test(value); 
	        }, 
	        message : '请输入整数!' 
	    }, 
	    sz : {// 验证数字
	        validator : function(value) { 
	            return /^[+]?[0-9]+\d*$/i.test(value); 
	        }, 
	        message : '请输入0-9数字!' 
	    },
	    age : {   //或者排序号
	        validator : function(value) { 
	            return /^(?:[1-9][0-9]?|1[01][0-9]|120)$/i.test(value); 
	        }, 
	        message : '年龄必须是0到120之间的整数!' 
	    }, 
	    
	    pxh : {  //排序号
	        validator : function(value) { 
	         return /^(?:[1-9][0-9]?|[1-9][0-9][0-9]|1000)$/i.test(value); 
	        }, 
	        message : '排序号是1到1000之间的整数!' 
	    }, 
	    
	    chinese : {// 验证中文 
	        validator : function(value) { 
	            return /^[\Α-\￥]+$/i.test($.trim(value)); 
	        }, 
	        message : '请输入中文!' 
	    }, 
	    english : {// 验证英语 
	        validator : function(value) { 
	            return /^[A-Za-z]+$/i.test(value); 
	        }, 
	        message : '请输入英文!' 
	    }, 
	    unnormal : {// 验证是否包含空格和非法字符 
	        validator : function(value) { 
	            return /.+/i.test(value); 
	        }, 
	        message : '输入值不能为空和包含其他非法字符!' 
	    }, 
	    username : {// 验证用户名 
	        validator : function(value) { 
	            return /^[a-zA-Z][a-zA-Z0-9_]{5,15}$/i.test(value); 
	        }, 
	        message : '用户名不合法（字母开头，允许6-16字节，允许字母数字下划线）!' 
	    }, 
	    colName : {// 验证数据库字段名
	        validator : function(value) { 
	            return /^[a-zA-Z][a-zA-Z0-9_]{1,9}$/i.test(value); 
	        }, 
	        message : '数据库字段名不合法（字母开头，允许2-10字节，允许字母数字下划线）!' 
	    }, 
	    cz : {// 验证传真 
	        validator : function(value) { 
	            return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value); 
	        }, 
	        message : '传真号码不正确,请使用如下格式1234567!' 
	    }, 
	    zip : {// 验证邮政编码 
	        validator : function(value) { 
	            return /^[1-9]\d{5}$/i.test(value); 
	        }, 
	        message : '邮政编码格式不正确!' 
	    }, 
	    ip : {// 验证IP地址 
	        validator : function(value) { 
	            return /d+.d+.d+.d+/i.test(value); 
	        }, 
	        message : 'IP地址格式不正确!' 
	    }, 
	    name : {// 验证姓名，可以是中文或英文 
	            validator : function(value) { 
	                return /^[\Α-\￥]+$/i.test(value)|/^\w+[\w\s]+\w+$/i.test(value); 
	            }, 
	            message : '请输入姓名!' 
	    },
	    date : {// 验证姓名，可以是中文或英文 
	        validator : function(value) { 
	         //格式yyyy-MM-dd或yyyy-M-d
	            return /^(?:(?!0000)[0-9]{4}([-]?)(?:(?:0?[1-9]|1[0-2])\1(?:0?[1-9]|1[0-9]|2[0-8])|(?:0?[13-9]|1[0-2])\1(?:29|30)|(?:0?[13578]|1[02])\1(?:31))|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)([-]?)0?2\2(?:29))$/i.test(value); 
	        },
	        message : '清输入合适的日期格式!'
	    },
	    msn:{ 
	        validator : function(value){ 
	        return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value); 
	    }, 
	    message : '请输入有效的msn账号(例：abc@hotnail(msn/live).com)!' 
	    },
	    same:{ 
	        validator : function(value, param){ 
	            if($("#"+param[0]).val() != "" && value != ""){ 
	                return $("#"+param[0]).val() == value; 
	            }else{ 
	                return true; 
	            } 
	        }, 
	        message : '两次输入的密码不一致！'    
	    },
	    select: {  
	    	validator: function(value,param){
	    	return $.trim($("#"+param[0]).find("option:contains('"+value+"')").val()) != ""&&$("#"+param[0]).find("option:contains('"+value+"')").val()!='0';  
	    	},  
	    	message: '请选择一个类型！'  
	    	},
	   comselect: {  
	    	validator: function(value,param){
	    	return $.trim($("#"+param[0]).val()) != ""&&$("#"+param[0]).val()!='0';  
	    	},  
	    	message: '请选择一个类型！'  
	    	}  
	});
