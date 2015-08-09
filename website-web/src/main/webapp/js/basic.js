var EasyUI = {
	  //获取id控件的值
      getValue : function(id){
    	  return $("#"+id).val();
      },
      //easyui datagrid id的值, 保持当前页面的值，load方法是第一页
      reload : function(id){
    	  $('#'+id).datagrid('reload');
      },
      //设置easyui datagrid id的值 ，设置查询参数
      setQueryParams :function(id,json){
    	  $('#'+id).datagrid({
    			queryParams: json
    		});
      },
      //设置datagrid的请求路径
      setUrl:function(id,url){
    	  $('#'+id).datagrid({
    		    url:url
    	  });
      },
      //将 json对象序列化为json字符串
      serializeJson2str: function(formId){  
    	   var o = {};  
    	   var a = $("#"+formId).serializeArray();  
    	   $.each(a, function() {  
    	       if (o[this.name]) {  
    	           if (!o[this.name].push) {  
    	               o[this.name] = [o[this.name]];  
    	           }  
    	           o[this.name].push(this.value || '');  
    	       } else {  
    	           o[this.name] = this.value || '';  
    	       }  
    	   });  
    	   return JSON.stringify(o);  
    	},
    	hide: function (formId) { 
    		$("#"+formId).hide();
    	},
    	warningDialog: function(title,message){
    		$.messager.alert(title,message);
    	},
    	serializeIds: function(rows){
    		var ids="";
    		for(var i=0 ;i<rows.length;i++){
    			ids=ids+rows[i].id+",";
    		}
    		return ids;
    	},
    	//禁用form表单中所有的input[文本框、复选框、单选框],select[下拉选],多行文本框[textarea]  
    	disableForm: function (formId,isDisabled) {  
            
          var attr="disable";  
          if(!isDisabled){  
             attr="enable";  
          }  
          $("form[id='"+formId+"'] :text").attr("disabled",isDisabled);  
          $("form[id='"+formId+"'] textarea").attr("disabled",isDisabled);  
          $("form[id='"+formId+"'] select").attr("disabled",isDisabled);  
          $("form[id='"+formId+"'] :radio").attr("disabled",isDisabled);  
          $("form[id='"+formId+"'] :checkbox").attr("disabled",isDisabled);  
            
          //禁用jquery easyui中的下拉选（使用input生成的combox）  
        
          $("#" + formId + " input[class='combobox-f combo-f']").each(function () {  
              if (this.id) {alert("input"+this.id);  
                  $("#" + this.id).combobox(attr);  
              }  
          });  
            
          //禁用jquery easyui中的下拉选（使用select生成的combox）  
          $("#" + formId + " select[class='combobox-f combo-f']").each(function () {  
              if (this.id) {  
              alert(this.id);  
                  $("#" + this.id).combobox(attr);  
              }  
          });  
            
          //禁用jquery easyui中的日期组件dataBox  
          $("#" + formId + " input[class='datebox-f combo-f']").each(function () {  
              if (this.id) {  
              alert(this.id)  
                  $("#" + this.id).datebox(attr);  
              }  
          }); 
          if($("#btn_save")){
        	  if(isDisabled){
        		  $("#btn_save").hide();
        	  }else{
        		  $("#btn_save").show();
        	  }
          }
      }  

      
};


/*
cycle.js
2015-02-25
Public Domain.
NO WARRANTY EXPRESSED OR IMPLIED. USE AT YOUR OWN RISK.
This code should be minified before deployment.
See http://javascript.crockford.com/jsmin.html
USE YOUR OWN COPY. IT IS EXTREMELY UNWISE TO LOAD CODE FROM SERVERS YOU DO
NOT CONTROL.
*/

/*jslint eval, for */

/*property 
$ref, apply, call, decycle, hasOwnProperty, length, prototype, push,
retrocycle, stringify, test, toString
*/

if (typeof JSON.decycle !== 'function') {
JSON.decycle = function decycle(object) {
    'use strict';

//Make a deep copy of an object or array, assuring that there is at most
//one instance of each object or array in the resulting structure. The
//duplicate references (which might be forming cycles) are replaced with
//an object of the form
//  {$ref: PATH}
//where the PATH is a JSONPath string that locates the first occurance.
//So,
//  var a = [];
//  a[0] = a;
//  return JSON.stringify(JSON.decycle(a));
//produces the string '[{"$ref":"$"}]'.

//JSONPath is used to locate the unique object. $ indicates the top level of
//the object or array. [NUMBER] or [STRING] indicates a child member or
//property.

    var objects = [],   // Keep a reference to each unique object or array
        paths = [];     // Keep the path to each unique object or array

    return (function derez(value, path) {

//The derez recurses through the object, producing the deep copy.

        var i,          // The loop counter
            name,       // Property name
            nu;         // The new object or array

//typeof null === 'object', so go on if this value is really an object but not
//one of the weird builtin objects.

        if (typeof value === 'object' && value !== null &&
                !(value instanceof Boolean) &&
                !(value instanceof Date) &&
                !(value instanceof Number) &&
                !(value instanceof RegExp) &&
                !(value instanceof String)) {

//If the value is an object or array, look to see if we have already
//encountered it. If so, return a $ref/path object. This is a hard way,
//linear search that will get slower as the number of unique objects grows.

            for (i = 0; i < objects.length; i += 1) {
                if (objects[i] === value) {
                    return {$ref: paths[i]};
                }
            }

//Otherwise, accumulate the unique value and its path.

            objects.push(value);
            paths.push(path);

//If it is an array, replicate the array.

            if (Object.prototype.toString.apply(value) === '[object Array]') {
                nu = [];
                for (i = 0; i < value.length; i += 1) {
                    nu[i] = derez(value[i], path + '[' + i + ']');
                }
            } else {

//If it is an object, replicate the object.

                nu = {};
                for (name in value) {
                    if (Object.prototype.hasOwnProperty.call(value, name)) {
                        nu[name] = derez(value[name],
                                path + '[' + JSON.stringify(name) + ']');
                    }
                }
            }
            return nu;
        }
        return value;
    }(object, '$'));
};
}


if (typeof JSON.retrocycle !== 'function') {
JSON.retrocycle = function retrocycle($) {
    'use strict';

//Restore an object that was reduced by decycle. Members whose values are
//objects of the form
//  {$ref: PATH}
//are replaced with references to the value found by the PATH. This will
//restore cycles. The object will be mutated.

//The eval function is used to locate the values described by a PATH. The
//root object is kept in a $ variable. A regular expression is used to
//assure that the PATH is extremely well formed. The regexp contains nested
//* quantifiers. That has been known to have extremely bad performance
//problems on some browsers for very long strings. A PATH is expected to be
//reasonably short. A PATH is allowed to belong to a very restricted subset of
//Goessner's JSONPath.

//So,
//  var s = '[{"$ref":"$"}]';
//  return JSON.retrocycle(JSON.parse(s));
//produces an array containing a single element which is the array itself.

    var px = /^\$(?:\[(?:\d+|\"(?:[^\\\"\u0000-\u001f]|\\([\\\"\/bfnrt]|u[0-9a-zA-Z]{4}))*\")\])*$/;

    (function rez(value) {

//The rez function walks recursively through the object looking for $ref
//properties. When it finds one that has a value that is a path, then it
//replaces the $ref object with a reference to the value that is found by
//the path.

        var i, item, name, path;

        if (value && typeof value === 'object') {
            if (Object.prototype.toString.apply(value) === '[object Array]') {
                for (i = 0; i < value.length; i += 1) {
                    item = value[i];
                    if (item && typeof item === 'object') {
                        path = item.$ref;
                        if (typeof path === 'string' && px.test(path)) {
                            value[i] = eval(path);
                        } else {
                            rez(item);
                        }
                    }
                }
            } else {
                for (name in value) {
                    if (typeof value[name] === 'object') {
                        item = value[name];
                        if (item) {
                            path = item.$ref;
                            if (typeof path === 'string' && px.test(path)) {
                                value[name] = eval(path);
                            } else {
                                rez(item);
                            }
                        }
                    }
                }
            }
        }
    }($));
    return $;
};
}


