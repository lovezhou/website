<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <title>组织结构</title>
 </head>
 <frameset cols="500,*"> 
  <frame src="<%=basePath%>/sysDict/toListView.do" name="leftframe" frameborder="0" scrolling="yes" noresize="noresize" /> 
  <frame src="<%=basePath%>/sysDictDetail/toListView.do" name="rightframe" /> 
 </frameset> 
</html>	