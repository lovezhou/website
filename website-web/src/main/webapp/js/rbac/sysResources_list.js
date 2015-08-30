var title = "资源" ;
//获取查询参数
function queryParams(){
	var name =  EasyUI.getValue("txt_name");
	var params = {
			  name:name
	};
	return params;
}

//点击查询按钮
function  search(){
  var params = queryParams();
  $('#grid').treegrid('load',params);
}


function addRow(){
    $('#dlg').dialog('open').dialog('setTitle','新建'+title);
    $('#fm').form('clear');
    EasyUI.disableForm('fm',false);
}

function editRow(id){
	$('#grid').treegrid('select',id);//选中当前节点
    var row = $('#grid').datagrid('getSelected');
    console.log(row);
	//var row =  $('#grid').datagrid('getRows')[index];
    if (row){
        $('#dlg').dialog('open').dialog('setTitle','编辑'+title);
        $('#fm').form('load',row);
        $('#pname').textbox('setValue',$("#grid").treegrid('getParent',row.id).name);
        $('#query').combobox('setValue',row.sysFunctionsVO.query);
        $('#add').combobox('setValue',row.sysFunctionsVO.add);
        $('#update').combobox('setValue',row.sysFunctionsVO.update);
        $('#deleted').combobox('setValue',row.sysFunctionsVO.deleted);
        $('#view').combobox('setValue',row.sysFunctionsVO.view);
        $('#checked').combobox('setValue',row.sysFunctionsVO.checked);
        $('#unchecked').combobox('setValue',row.sysFunctionsVO.unchecked);
        $('#exported').combobox('setValue',row.sysFunctionsVO.exported);
        $('#upload').combobox('setValue',row.sysFunctionsVO.upload);
        $('#download').combobox('setValue',row.sysFunctionsVO.download);
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
	    url:"sysResources/save.do",
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
                $.post('sysResources/deleteById.do',{id:id},function(result){
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
	                $.post('sysResources/deleteByIds.do',{ids:ids},function(result){
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
	return '<a href="javascript:void(0)" onclick="addRow(\''+row.id+'\')">新增</a>&nbsp;&nbsp'+
	       '<a href="javascript:void(0)" onclick="editRow(\''+row.id+'\')">编辑</a>&nbsp;&nbsp;' +
	 		'<a href="javascript:void(0)" onclick="deleteRow(\''+row.id+'\')">删除</a>';
}

function checkboxformater(flag,name){
	if(flag){
		return '<input name="chk_"'+name+' value="checked" type="checkbox">';
	}else{
		return '<img src="images/icon/close.gif" width="16" height="16"/>';
	}
}

function queryformater(value,row,index){ //如果有多列用到value，那么第一次一定要格式化，不然后续格式化都没有效果
	return checkboxformater(value&&value.query=="1");
}
function addformater(value,row,index){
	return checkboxformater(value.add=="1");
}
function updateformater(value,row,index){
	return checkboxformater(value.update=="1");
}
function deletedformater(value,row,index){
	return checkboxformater(value.deleted=="1");
}
function viewformater(value,row,index){
	return checkboxformater(value.view=="1");
}
function checkedformater(value,row,index){
	return checkboxformater(value.checked=="1");
}
function uncheckformater(value,row,index){
	return checkboxformater(value.unchecked=="1");
}
function exportedformater(value,row,index){
	return checkboxformater(value.exported=="1");
}
function uploadformater(value,row,index){
	return checkboxformater(value.upload=="1");
}
function downloadformater(value,row,index){
	return checkboxformater(value.download=="1");
}

