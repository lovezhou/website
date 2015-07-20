var title = "" ;

//获取查询参数
function queryParams(){
	var dictName =  EasyUI.getValue("txt_dictName");
	var dictCode =  EasyUI.getValue("txt_dictCode");
	var params = {
			  dictName:dictName,
			  dictCode:dictCode
	};
	return params;
}

//点击查询按钮
function  search(){
  var params = queryParams();
  EasyUI.setQueryParams('grid',params);
  EasyUI.reload('grid');
}


//call 'submit' method of form plugin to submit the form    
$('#from1').form('submit', {    
    url:"",    
    onSubmit: function(){    
        // do some check    
        // return false to prevent submit;    
    },    
    success:function(data){    
        alert("");
    }    
});  


function addData(){
	$('#fromWindow').
	alert("hello");
}


function deleteData(){
	alert("hello");
}
