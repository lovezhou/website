var title = "数据字典" ;
//获取查询参数
function queryParams1(){
	var name =  EasyUI.getValue("txt_name1");
	var code =  EasyUI.getValue("txt_code1");
	var params = {
			  name:name,
			  code:code
	};
	return params;
}

//点击查询按钮
function  search1(){
  var params = queryParams1();
  EasyUI.setQueryParams('grid1',params);
  //EasyUI.reload('grid1');
}


function addRow1(){
    $('#dlg1').dialog('open').dialog('setTitle','新建'+title);
    $('#fm_dd').form('clear');
    EasyUI.disableForm('fm_dd',false);
}

function editRow1(index){
    //var row = $('#grid').datagrid('getSelected');
	var row =  $('#grid1').datagrid('getRows')[index];
    if (row){
        $('#dlg1').dialog('open').dialog('setTitle','编辑'+title);
        $('#fm_dd').form('load',row);
        EasyUI.disableForm('fm_dd',false);
    }
}

function viewRow1(index){
	//var row = $('#dg').datagrid('getSelected');
	var row =  $('#grid1').datagrid('getRows')[index];
    if (row){
        $('#dlg1').dialog('open').dialog('setTitle','查看'+title);
        $('#fm_dd').form('load',row);
        EasyUI.disableForm('fm_dd',true);
    }
}
function saveRow1(){
	$.messager.progress();
	$('#fm_dd').form('submit',{
	    url:"sysDictDetail/save.do",
	    onSubmit: function(param){
	    	param._jsonData=EasyUI.serializeJson2str('fm_dd');
	    	var isValid = $(this).form('validate');
			if (!isValid){
				$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
			}
			return isValid;	// 返回false终止表单提交
	    },
	    success:function(result){
	    	$.messager.progress('close');	// 如果提交成功则隐藏进度条
	    	result = eval('('+result+')');
            if (result.success){
            	$('#dlg1').dialog('close');        // close the dialog
                search1();
               // $('#dg').datagrid('reload');  // reload the user data
            } else {
            	$.messager.show({
                    title: result.title,
                    msg: result.errorMsg
                });
            }
	    }
	});
}


function deleteRow1(id){
    if (id){
        $.messager.confirm('确认','确实删除这条数据?',function(r){
            if (r){
                $.post('sysDictDetail/deleteById.do',{id:id},function(result){
                    if (result.success){
                    	search1();
                    } else {
                        $.messager.show({    // show error message
                            title: 'Error',
                            msg: result.errorMsg
                        });
                    }
                },'json');
            }
        });
    }
}

function deleteRows1(){
	var rows = $('#grid1').datagrid('getSelections');
	if(rows.length<1){
		EasyUI.warningDialog('警告','请至少选择一条数据！');
	}else{
		var ids=EasyUI.serializeIds(rows);
		$.messager.confirm('确认','确实删除['+rows.length+']条数据?',function(r){
	            if (r){
	                $.post('sysDictDetail/deleteByIds.do',{ids:ids},function(result){
	                    if (result.success){
	                    	search1();
	                    } else {
	                        $.messager.show({    // show error message
	                            title: 'Error',
	                            msg: result.errorMsg
	                        });
	                    }
	                },'json');
	            }
	        });
	}

}

function rowformater1(value,row,index){
	return '<a href="javascript:void(0)" onclick="editRow1('+index+')">编辑</a>&nbsp;&nbsp;' +
	 		'<a href="javascript:void(0)" onclick="deleteRow1(\''+row.id+'\')">删除</a>&nbsp;&nbsp;'+
	 		'<a href="javascript:void(0)" onclick="viewRow1('+index+')">查看</a>';
}