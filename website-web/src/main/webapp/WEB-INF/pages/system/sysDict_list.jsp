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
			url="sysDict/query.json" data-options="rownumbers:true,singleSelect:true,autoRowHeight:false,pagination:true,pageSize:10"
		    toolbar="#tb"   fitColumns="true">
		<thead>
			<tr>
				<th field="dictName" align="center" width="150">字典名称</th>
				<th field="dictCode" align="center" width="150">字典代码</th>
				<th field="remark"   align="center"   width="150">备注</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"></a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true"></a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true"></a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cut" plain="true"></a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a>
		</div>
		<div>
			字典名称：<input id="txt_dictName" class="easyui-textbox" name="dictName" style="width:10%;height:20px">
			字典代码：<input id="txt_dictCode" class="easyui-textbox" name="dictCode" style="width:10%;height:20px">
			<a href="javascript:search();" class="easyui-linkbutton"  iconCls="icon-search">查询</a>
		</div>
	</div>
	

  </body>
</html>