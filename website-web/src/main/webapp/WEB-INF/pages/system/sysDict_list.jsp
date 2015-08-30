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
			url="<%=basePath%>/sysDict/query.json" data-options="rownumbers:true,singleSelect:false,autoRowHeight:true,pagination:true,pageSize:10"
		    toolbar="#tb"   fitColumns="true">
		<thead>
			<tr>
				<th  field="ck" align="center" checkbox=true></th>
				<th data-options="field:'id',width:100,formatter:rowformater" align="center">操作</th>
				<th field="dictName" align="center" width="100">字典名称</th>
				<th field="dictCode" align="center" width="100">字典代码</th>
				<th field="remark"   align="center" width="150">备注</th>
			</tr>
		</thead>
	</table>
	<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true"  onclick="addRow()">新增</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteRows()">删除</a>
		</div>
		<div>
			字典名称：<input id="txt_dictName" class="easyui-textbox" name="dictName" style="width:20%;height:20px">
			字典代码：<input id="txt_dictCode" class="easyui-textbox" name="dictCode" style="width:20%;height:20px">
			<a href="javascript:search()"  class="easyui-linkbutton"  iconCls="icon-search" style="width:15%;height:20px">查询</a>
		</div>
	</div>
	
	 <div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons">
       <!--  <div class="ftitle">User Information</div> -->
        <form id="fm" method="post"  novalidate>
        	 <input name="id" id="input_dlg_fm_id" type="hidden">
            <div class="fitem">
                <label>字典名称:</label>
                <input name="dictName" class="easyui-textbox" required="true">
            </div>
            <div class="fitem">
                <label>字典代码:</label>
                <input name="dictCode" class="easyui-textbox" required="true">
            </div>
            <div class="fitem">
                <label>备注:</label>
                <input name="remark" class="easyui-textbox">
            </div>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" id="btn_save" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveRow()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
    </div>
  </body>
</html>