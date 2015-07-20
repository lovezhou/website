<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>数据字典维护</title>
     <%@include file="../head_include.jsp" %>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="<%=basePath%>js/system/sysDict_list.js" charset="utf-8"></script>
  </head>
  <body  style="margin: 0px">
	<table  id="grid" class="easyui-datagrid" style="width:100%;height:100%"
			url="sysDict/query.json" data-options="rownumbers:true,singleSelect:true,autoRowHeight:false,singleSelect:false,pagination:true,pageSize:10"
		    toolbar="#tb"   fitColumns="true">
		<thead>
			<tr>
				<th field="" align="center" width="150">操作</th>
				<th field="dictName" align="center" width="100">字典名称</th>
				<th field="dictCode" align="center" width="100">字典代码</th>
				<th field="remark"   align="center" width="150">备注</th>
			</tr>
		</thead>
	</table>
	<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"  onclick="javascript:addData();return false ;">新增</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteData()">删除</a>
		</div>
		<div>
			字典名称：<input id="txt_dictName" class="easyui-textbox" name="dictName" style="width:20%;height:20px">
			字典代码：<input id="txt_dictCode" class="easyui-textbox" name="dictCode" style="width:20%;height:20px">
			<a href="javascript:search();" class="easyui-linkbutton"  iconCls="icon-search" style="width:15%;height:20px">查询</a>
		</div>
	</div>
	
	<div  id="formWindow" class="easyui-dialog" style="width:350px;height:216p;display: none;"
		data-options="title:'My Dialog',buttons:'#dlgBtn',modal:true" >
		<form id="form1" method="post" >   
		    <div>   
		        <label for="dictName">字典名称:</label>   
		        <input class="easyui-validatebox" type="text" name="dictName" data-options="required:true"  maxlength="30" />   
		    </div>   
		    <div>   
		        <label for="dictCode">字典代码:</label>   
		        <input class="easyui-validatebox" type="text" name="dictCode" data-options="required:true" maxlength="30" />   
		    </div>  
		     <div>   
		        <label for="remark">备注:</label>   
		        <input class="easyui-validatebox" type="text" name="remark"  maxlength="100" />   
		    </div>   
		</form>  
	</div>  
	
	<div id="dlgBtn">
		<a href="#" class="easyui-linkbutton">保存</a>
		<a href="#" class="easyui-linkbutton">关闭</a>
	</div>
	
	
	<div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons">
        <div class="ftitle">User Information</div>
        <form id="fm" method="post" novalidate>
            <div class="fitem">
                <label>First Name:</label>
                <input name="firstname" class="easyui-textbox" required="true">
            </div>
            <div class="fitem">
                <label>Last Name:</label>
                <input name="lastname" class="easyui-textbox" required="true">
            </div>
            <div class="fitem">
                <label>Phone:</label>
                <input name="phone" class="easyui-textbox">
            </div>
            <div class="fitem">
                <label>Email:</label>
                <input name="email" class="easyui-textbox" validType="email">
            </div>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">Save</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">Cancel</a>
    </div>

	
  </body>
</html>