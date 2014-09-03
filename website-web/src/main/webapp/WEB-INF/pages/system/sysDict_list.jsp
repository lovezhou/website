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
    <title>代码生成器</title>
     <%@include file="../head_include.jsp" %>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <body  style="margin: 0px">
	<table class="easyui-datagrid" style="width:100%;height:100%"
			url="data/datagrid_data.json" 
			title="数据字典维护" toolbar="#tb"
			singleSelect="true" fitColumns="true">
		<thead>
			<tr>
				<th field="itemid" width="60">Item ID</th>
				<th field="productid" width="80">Product ID</th>
				<th field="listprice" align="right" width="70">List Price</th>
				<th field="unitcost" align="right" width="70">Unit Cost</th>
				<th field="attr1" width="200">Address</th>
				<th field="status" width="50">Status</th>
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
			Date From: <input class="easyui-datebox" style="width:80px">
			To: <input class="easyui-datebox" style="width:80px">
			Language: 
			<input class="easyui-combobox" style="width:100px"
					url="data/combobox_data.json"
					valueField="id" textField="text">
			<a href="#" class="easyui-linkbutton" iconCls="icon-search">Search</a>
		</div>
	</div>
	

  </body>
</html>
<script type="text/javascript">
 //选中所以行
 function selectChkCode(){
	 var flag= false; 
	 if($("#all_code_chk").attr("checked")){
		 $("#all_code_chk").attr("checked",true);
		 flag=true;
	 }else{
		 $("#all_code_chk").attr("checked",false);
		 flag=false;
	 }
	 $("input[id^='chk_code_']").each(function(){
		 $(this).attr("checked",flag);
	 });
	 
 }
 
 //选中所以行
 function selectChkCond(){
	 var flag= false; 
	 if($("#all_cond_chk").attr("checked")){
		 $("#all_cond_chk").attr("checked",true);
		 flag=true;
	 }else{
		 $("#all_cond_chk").attr("checked",false);
		 flag=false;
	 }
	 $("input[id^='chk_cond_']").each(function(){
		 $(this).attr("checked",flag);
	 });
	 
 }
</script>