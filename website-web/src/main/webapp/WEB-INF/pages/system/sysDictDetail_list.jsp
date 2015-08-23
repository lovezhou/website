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
	<script type="text/javascript" src="<%=basePath%>js/system/sysDictDetail_list.js" charset="utf-8"></script>
  </head>
  <body  style="margin: 0px">
	<table  id="grid1" class="easyui-datagrid" style="width:98%;height:99%"
			url="<%=basePath%>/sysDictDetail/query.json" data-options="rownumbers:true,singleSelect:false,autoRowHeight:true,pagination:true,pageSize:10"
		    toolbar="#tb1"   fitColumns="true">
		<thead>
			<tr>
				<th  field="ck" align="center" checkbox=true></th>
					 <th data-options="field:'id',width:100,formatter:rowformater1" align="center">操作</th>
			  	   	  <th field="dictText" align="center" width="100">字典项</th>
			  	   	  <th field="name" align="center" width="100">字典名称</th>
			  	   	  <th field="code" align="center" width="100">字典代码</th>
			  	   	  <th field="order" align="center" width="100">序号</th>
			  	   	  <th field="remark" align="center" width="100">备注</th>
			</tr>
		</thead>
	</table>
	<div id="tb1" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true"  onclick="addRow1()">新增</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteRows1()">删除</a>
		</div>
		<div>
			  	   	  	字典名称：<input id="txt_code1" class="easyui-textbox" name="name" style="width:20%;height:20px">
			  	   	  	字典代码：<input id="txt_name1" class="easyui-textbox" name="code" style="width:20%;height:20px">
			<a href="javascript:search1()"  class="easyui-linkbutton"  iconCls="icon-search" style="width:15%;height:20px">查询</a>
		</div>
	</div>
	
	 <div id="dlg1" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons1">
       <!--  <div class="ftitle">User Information</div> -->
        <form id="fm_dd" method="post"  novalidate>
        	 			<input name="id"  type="hidden">
						<div class="fitem">
			                <label>字典项</label>
			                <input id="dictId" class="easyui-combobox" name="dictId" 
			                data-options="valueField:'id',textField:'dictName',url:'sysDict/queryDict.json'" />  
			            </div>
						<div class="fitem">
			                <label>字典名称</label>
			                <input name="name" class="easyui-textbox" required="true">
			            </div>
						<div class="fitem">
			                <label>字典代码</label>
			                <input name="code" class="easyui-textbox" required="true">
			            </div>
						<div class="fitem">
			                <label>序号</label>
			                <input name="order" class="easyui-textbox" required="true">
			            </div>
						<div class="fitem">
			                <label>备注</label>
			                <input name="remark" class="easyui-textbox" >
			            </div>
        </form>
    </div>
    <div id="dlg-buttons1">
        <a href="javascript:void(0)" id="btn_save" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveRow1()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg1').dialog('close')" style="width:90px">取消</a>
    </div>
  </body>
</html>