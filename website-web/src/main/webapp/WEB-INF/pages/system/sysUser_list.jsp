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
    <title>用户管理</title>
     <%@include file="../head_include.jsp" %>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<script type="text/javascript" src="<%=basePath%>js/system/sysUser_list.js" charset="utf-8"></script>
  </head>
  <body  style="margin: 0px">
	<table  id="grid" class="easyui-datagrid" style="width:100%;height:100%"
			url="<%=basePath%>/sysUser/query.json" data-options="rownumbers:true,singleSelect:false,autoRowHeight:true,pagination:true,pageSize:10"
		    toolbar="#tb"   fitColumns="true">
		<thead>
			<tr>
				  <th  field="ck" align="center" checkbox=true></th>
				  <th data-options="field:'id',width:100,formatter:rowformater" align="center">操作</th>
		  	   	  <th field="name" align="center" width="100">姓名</th>
		  	   	  <th field="loginAccount" align="center" width="100">登录账户</th>
		  	   	  <th field="loginPasswd" align="center" width="100" hidden="true">登录密码</th>
		  	   	  <th field="email" align="center" width="100">邮箱</th>
		  	   	  <th field="address" align="center" width="100">地址</th>
		  	   	  <th field="phoneNum" align="center" width="100">联系电话</th>
		  	   	  <th field="accountId" align="center" width="100">身份证号</th>
			</tr>
		</thead>
	</table>
	<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true"  onclick="addRow()">新增</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteRows()">删除</a>
		</div>
		<div>
			  	   	  	姓名：<input id="txt_name" class="easyui-textbox" name="name" style="width:10%;height:20px">
			  	   	  	登录账户：<input id="txt_loginAccount" class="easyui-textbox" name="loginAccount" style="width:10%;height:20px">
			  	   	  	身份证号：<input id="txt_accountId" class="easyui-textbox" name="accountId" style="width:10%;height:20px">
			<a href="javascript:search()"  class="easyui-linkbutton"  iconCls="icon-search" style="width:5%;height:20px">查询</a>
		</div>
	</div>
	
	 <div id="dlg" class="easyui-dialog" style="width:400px;height:350px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons">
       <!--  <div class="ftitle">User Information</div> -->
        <form id="fm" method="post"  novalidate>
       				    <input name="id" id="input_dlg_fm_id" type="hidden">
						<div class="fitem">
			                <label>姓名</label>
			                <input name="name" class="easyui-textbox" required="true">
			            </div>
						<div class="fitem">
			                <label>登录账户</label>
			                <input name="loginAccount" class="easyui-textbox" required="true">
			            </div>
						<div class="fitem">
			                <label>登录密码</label>
			                <input type="password" name="loginPasswd" class="easyui-textbox" required="true">
			            </div>
						<div class="fitem">
			                <label>邮箱</label>
			                <input name="email" class="easyui-textbox" >
			            </div>
						<div class="fitem">
			                <label>地址</label>
			                <input name="address" class="easyui-textbox" >
			            </div>
						<div class="fitem">
			                <label>联系电话</label>
			                <input name="phoneNum" class="easyui-textbox" >
			            </div>
						<div class="fitem">
			                <label>身份证号</label>
			                <input name="accountId" class="easyui-textbox" >
			            </div>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" id="btn_save" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveRow()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
    </div>
  </body>
</html>