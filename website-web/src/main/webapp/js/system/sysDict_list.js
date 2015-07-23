var title = "" ;
var url; 
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


function addData(){
    $('#dlg').dialog('open').dialog('setTitle','新建字典');
    $('#fm').form('clear');
}
function editUser(){
    var row = $('#dg').datagrid('getSelected');
    if (row){
        $('#dlg').dialog('open').dialog('setTitle','Edit User');
        $('#fm').form('load',row);
    }
}
function saveUser(){
    $('#fm').form('submit',{
        url: url,
        onSubmit: function(){
            return $(this).form('validate');
        },
        success: function(result){
            var result = eval('('+result+')');
            if (result.errorMsg){
                $.messager.show({
                    title: 'Error',
                    msg: result.errorMsg
                });
            } else {
                $('#dlg').dialog('close');        // close the dialog
                $('#dg').datagrid('reload');    // reload the user data
            }
        }
    });
}
function deleteData(){
	var row = $('#dg').datagrid('getSelected');
    if (row){
        $.messager.confirm('Confirm','Are you sure you want to destroy this user?',function(r){
            if (r){
                $.post('destroy_user.php',{id:row.id},function(result){
                    if (result.success){
                        $('#dg').datagrid('reload');    // reload the user data
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
