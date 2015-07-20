<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pagination" uri="/WEB-INF/tld/pagination/pagination.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/pagination/c.tld"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'admin.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    首页！
   <sec:authentication property="name"/>
   <sec:authentication property="authorities" var="authorities" scope="page"/>
<c:forEach items="${authorities}" var="authority">
  ${authority.authority}
</c:forEach>
    <br>
    <s:form name="form1" action="admin" namespace="/">
    <s:iterator value="list1">
    <s:property value="yhmc"/>--<s:property value="zsxm"/>--<s:property value="pwd"/><br/>
    </s:iterator>
    <pagination:paginate items="list1" form="form1"></pagination:paginate>
    </s:form>
  </body>
</html>
