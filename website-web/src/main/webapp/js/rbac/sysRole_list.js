var title = "角色" ;
//获取查询参数
function queryParams(){
	var roleName =  EasyUI.getValue("txt_roleName");
	var params = {
			roleName:roleName
	};
	return params;
}

//点击查询按钮
function  search(){
  var params = queryParams();
  EasyUI.setQueryParams('grid',params);
  //EasyUI.reload('grid');
}


function addRow(){
    $('#dlg').dialog('open').dialog('setTitle','新建'+title);
    $('#fm').form('clear');
    EasyUI.disableForm('fm',false);
}

function editRow(index){
    //var row = $('#grid').datagrid('getSelected');
	var row =  $('#grid').datagrid('getRows')[index];
    if (row){
        $('#dlg').dialog('open').dialog('setTitle','编辑'+title);
        $('#fm').form('load',row);
        EasyUI.disableForm('fm',false);
    }
}

function viewRow(index){
	//var row = $('#dg').datagrid('getSelected');
	var row =  $('#grid').datagrid('getRows')[index];
    if (row){
        $('#dlg').dialog('open').dialog('setTitle','查看'+title);
        $('#fm').form('load',row);
        EasyUI.disableForm('fm',true);
    }
}
function saveRow(){
	$.messager.progress();
	$('#fm').form('submit',{
	    url:"sysRole/save.do",
	    onSubmit: function(param){
	    	param._jsonData=EasyUI.serializeJson2str('fm');
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
            	$('#dlg').dialog('close');        // close the dialog
                search();
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


function deleteRow(id){
    if (id){
        $.messager.confirm('确认','确实删除这条数据?',function(r){
            if (r){
                $.post('sysRole/deleteById.do',{id:id},function(result){
                    if (result.success){
                    	search();
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

function deleteRows(){
	var rows = $('#grid').datagrid('getSelections');
	if(rows.length<1){
		EasyUI.warningDialog('警告','请至少选择一条数据！');
	}else{
		var ids=EasyUI.serializeIds(rows);
		$.messager.confirm('确认','确实删除['+rows.length+']条数据?',function(r){
	            if (r){
	                $.post('sysRole/deleteByIds.do',{ids:ids},function(result){
	                    if (result.success){
	                    	search();
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

function rowformater(value,row,index){
	return '<a href="javascript:void(0)" onclick="editRow('+index+')">编辑</a>&nbsp;&nbsp;' +
	 		'<a href="javascript:void(0)" onclick="deleteRow(\''+row.id+'\')">删除</a>&nbsp;&nbsp;'+
	 		'<a href="javascript:void(0)" onclick="viewRow('+index+')">查看</a>';
}