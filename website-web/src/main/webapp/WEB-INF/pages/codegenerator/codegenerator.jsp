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
  <body>
  <div class="fieldset-tip"></div>
 	 <c:choose>
         <c:when test="${voList==null  }">
			<form action="codegenerator/toListView.do" name="form0" method="post" class="table03">
				<table class="table06 center"  align="left"  border="0" cellspacing="0"  style="width:30%">
				<tr>
					<td class="color">请输入表名</td>
					<td >
						<input class="input"  name="tableName" required="true"     style="width:100px">
					</td>
				</tr>
				<tr><td class="color">
						<input type="submit" id="generatorCode"  target="mainFrame" style="float: left;" iconCls="icon-ok">
					</td>
					<td class="color">
						<input type="reset" id="generatorReset" target="mainFrame"  style="float: left;" iconCls="icon-ok">
					</td>
				</tr>
				</table>
			</form>
		</c:when>
		<c:otherwise>
		 <form action="codegenerator/codeGenerator.do" name="form0" method="post" class="table03">
				<table class="table06 center"  align="left"  border="0" cellspacing="0"  style="width:90%">
				<tr> 
				  <th style="width:10%">序号</th>
				  <th>字段名</th>
				  <th>属性名称</th>
				  <th>数据库类型</th>
				  <th>备注</th>
				  <th>是否为NUll</th>
				  <th><input id="all_code_chk" type="checkbox" checked="checked"   onclick="selectChkCode()" >是否生成code</th>
				  <th><input id="all_cond_chk" type="checkbox" checked="checked"   onclick="selectChkCond()" >是否查询条件</th>
				</tr>
				<c:forEach  items="${voList}"  var="vo" varStatus="status"> 
				<tr>
					<td class="color" width="15%">字段${status.count }</td>
					<td width="10%"><input class="input"  name="columnName" value="${vo.columnName }" required="true"    style="width:100px">
					<td width="10%"><input class="input"  name="propertyName" value="${vo.propertyName }" required="true"    style="width:100px">
					<td width="10%" ><input class="input" name="dataType" value="${vo.dataType }" required="true"    style="width:100px">
					<td width="10%" ><input class="input" name="comments" value="${vo.comments }" required="true"    style="width:100px">
					<td width="10%" ><input class="input" name="nullable" value="${vo.nullable }"     style="width:100px">
					<td width="10%" ><input type="checkbox" id="chk_code_${status.count }" name="chk_code"  value="1"  checked="checked"      style="width:100px">
					<td width="10%" ><input type="checkbox" id="chk_cond_${status.count }" name="chk_cond"  value="1"  checked="checked"      style="width:100px">
				</tr>
				</c:forEach>
				</table>
				<table class="table06"   border="0" cellspacing="0"  style="width:90%">
					<tr>
							<td class="color" width="10%">类名：</td>
							<td class="color" width="20%"><input type="text" name="className" value="${className }"></td>
							<td class="color" width="10%">前台js,jspBasePath:</td>
					        <td class="color" width="20%"><input type="text"  required="true" name="jspPath"  ></td>              
							<td class="color" width="10%">后台PackagePath:</td>
					        <td class="color" width="20%"><input type="text" name="basePath"  required="true"  ></td>              
				   </tr>
				   <tr>
				   			<td class="color" width="10%">数据库类型：</td>
				   			<td class="color" width="20%">MySQL：<input type="radio" name="databaseType"  required="true" value="mysql" /></td>
				   			<td class="color" width="20%">ORACLE：<input type="radio" name="databaseType" value="oracle" ></td>
							<td class="color" width="10%">生成jsp,js：</td>
							<td class="color" width="30%" colspan="2"><input type="checkbox" name="jsp" checked="checked" value="1" ></td>
					                      
				   </tr>			
					<tr>
					 		<td class="color" width="10%">生成controller：</td>
					 		<td class="color" width="20%"><input type="checkbox" name="controller" checked="checked" value="1" ></td>
					 		<td class="color" width="10%">生成service：</td>
							<td class="color" width="20%"><input type="checkbox" name="service" checked="checked" value="1"></td>
							<td class="color" width="10%">生成dao：</td>
							<td class="color" width="20%"><input type="checkbox" name="dao" checked="checked" value="1"><td>
				   </tr>		
					<tr>
						<td  colspan ="6">
								<input type="hidden" name="tableName" value="${tableName }">
								<input type="submit" id="generatorCode"  target="mainFrame" style="float: left;" value="生成code">
								<input type="reset"  id="generatorReset"  target="mainFrame"  style="float: left;" value="清空">
						</td>		
					 </tr>		
				</table>
			</form>
		</c:otherwise>
		</c:choose>
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