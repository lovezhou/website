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
		<script type="text/javascript" src="<%=basePath%>js/rbac/sysResources_list.js" charset="utf-8"></script>
  </head>
  <body  style="margin: 0px">
	<table  id="grid" class="easyui-treegrid" style="width:100%;height:100%"
			url="<%=basePath%>/sysResources/queryTree.json" data-options="rownumbers:true,idField:'id',treeField:'name',lines:true,singleSelect:true,autoRowHeight:true"
		    toolbar="#tb"   fitColumns="true">
		<thead>
			<tr>
			    <th data-options="field:'id',width:100,formatter:rowformater" align="center">操作</th>
			  	<th field="name" align="center" width="100">菜单名称</th>
			  	<th field="url" align="center" width="100">菜单url</th>
			  	<th data-options="field:'sysFunctionsVO',formatter:queryformater"  align="center" >查询</th>
			  	<th data-options="field:'sysFunctionsVO',formatter:addformater" align="center" >新增</th>
			  	<th data-options="field:'sysFunctionsVO',formatter:updateformater" align="center" >编辑</th>
			  	<th data-options="field:'sysFunctionsVO',formatter:deletedformater" align="center" >删除</th>
			  	<th data-options="field:'sysFunctionsVO',formatter:viewformater" align="center" >查看</th>
			  	<th data-options="field:'sysFunctionsVO',formatter:checkedformater" align="center" >审核</th>
			  	<th data-options="field:'sysFunctionsVO',formatter:uncheckformater"" align="center" >反审核</th>
			  	<th data-options="field:'sysFunctionsVO',formatter:exportedformater" align="center" >导出excel</th>
			  	<th data-options="field:'sysFunctionsVO',formatter:uploadformater" align="center" >上传</th>
			  	<th data-options="field:'sysFunctionsVO',formatter:downloadformater"  align="center" >下载</th>
			</tr>
		</thead>
	</table>
	<div id="tb" style="padding:5px;height:auto">
		<!-- <div style="margin-bottom:5px">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true"  onclick="addRow()">新增</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteRows()">删除</a>
		</div> -->
		<div>
	    	菜单名称：<input id="txt_name" class="easyui-textbox" name="name" style="width:10%;height:20px">
			<a href="javascript:search()"  class="easyui-linkbutton"  iconCls="icon-search" style="width:8%;height:20px">查询</a>
		</div>
	</div>
	
	 <div id="dlg" class="easyui-dialog" style="width:650px;height:320px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons">
       <!--  <div class="ftitle">User Information</div> -->
        <form id="fm" method="post"  novalidate>
        	 			<input name="id" id="input_dlg_fm_id" type="hidden">
        	 			<input name="pid" id="input_dlg_fm_pid" type="hidden">
        			    <div class="fitem" >
			                <label  >上级菜单</label>
			                <input id="pname" name="pname" class="easyui-textbox" readonly="readonly">
			                <label>菜单名称</label>
			                <input name="name" class="easyui-textbox" required="true"  >
			            </div>
			            <div class="fitem" >
			                <label>菜单url</label>
			                <input name="url" class="easyui-textbox" required="true" width="240">
			            </div>
						<div class="fitem">
			                <label>查询</label>
			                <input id="query" class="easyui-combobox" name="query" panelHeight="50px"
			                data-options="valueField:'id',textField:'txt',data: [{id: '1',txt: '有'},{id: '0',txt: '无'}]" />  
			                  <label>新增</label>
			                <input id="add" class="easyui-combobox" name="add"  panelHeight="50px"
			                data-options="valueField:'id',textField:'txt',data: [{id: '1',txt: '有'},{id: '0',txt: '无'}]" /> 
			            </div>
			            <div class="fitem">
			                 <label>编辑</label>
			                <input id="update" class="easyui-combobox" name="update"  panelHeight="50px"
			                data-options="valueField:'id',textField:'txt',data: [{id: '1',txt: '有'},{id: '0',txt: '无'}]" />  
			                <label>删除</label>
			                <input id="deleted" class="easyui-combobox" name="deleted"  panelHeight="50px"
			                data-options="valueField:'id',textField:'txt',data: [{id: '1',txt: '有'},{id: '0',txt: '无'}]" /> 
			            </div>
			            <div class="fitem">
			                 <label>查看</label>
			                <input id="view" class="easyui-combobox" name="view" panelHeight="50px"
			                data-options="valueField:'id',textField:'txt',data: [{id: '1',txt: '有'},{id: '0',txt: '无'}]" />  
			                 <label>导出</label>
			                <input id="exported" class="easyui-combobox" name="exported"  panelHeight="50px"
			                data-options="valueField:'id',textField:'txt',data: [{id: '1',txt: '有'},{id: '0',txt: '无'}]" /> 
			            </div>
			            <div class="fitem">
			                <label>审核</label>
			                <input id="checked" class="easyui-combobox" name="checked"  panelHeight="50px"
			                data-options="valueField:'id',textField:'txt',data: [{id: '1',txt: '有'},{id: '0',txt: '无'}]" /> 
			                 <label>反审核</label>
			                <input id="unchecked" class="easyui-combobox" name="unchecked"  panelHeight="50px"
			                data-options="valueField:'id',textField:'txt',data: [{id: '1',txt: '有'},{id: '0',txt: '无'}]" />  
			            </div>
			             <div class="fitem">
			             	 <label>上传</label>
			                <input id="upload" class="easyui-combobox" name="upload"  panelHeight="50px"
			                data-options="valueField:'id',textField:'txt',data: [{id: '1',txt: '有'},{id: '0',txt: '无'}]" /> 
			                <label>下载</label>
			                <input id="download" class="easyui-combobox" name="download"  panelHeight="50px"
			                data-options="valueField:'id',textField:'txt',data: [{id: '1',txt: '有'},{id: '0',txt: '无'}]" /> 
			            </div>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" id="btn_save" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveRow()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
    </div>
  </body>
</html>