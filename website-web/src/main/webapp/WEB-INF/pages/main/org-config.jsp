<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <title>组织结构</title>
 </head>
 <frameset cols="550,*"> 
  <frame src="bmxinxi-listGongsitree.xhtml<s:if test='gsId!=null'>?gsId=<s:property value='gsId'/></s:if>" name="treeframe" frameborder="0" scrolling="yes" noresize="noresize" /> 
  <frame src="bmxinxi-perpareAddGongsiBumenxinxi.xhtml<s:if test='gsId!=null'>?gsId=<s:property value='gsId'/></s:if>" name="basefrm" /> 
 </frameset> 
</html>	