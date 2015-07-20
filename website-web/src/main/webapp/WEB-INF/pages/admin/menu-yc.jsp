<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>异常管理</title>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/left.css">
	<script type="text/javascript" src="<%=basePath %>js/left.js"></script>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style>
html {
	height: auto;
	overflow-x: hidden;
}
</style>
  </head>
  
   <body style="background: #eef2fb;" onload="setDefault();">
		<div class="sidebar">
			<div class="menu">
				<ul id="masterdiv">
					<li name="li_sub12">
						<a href="javascript:void(0);" onClick="SwitchMenu('sub12');return false;">货物异常管理</a>
						<ul id="sub12" style="display: none;">
							<li>
								<a href="ucydhwHshcgl-listUcydhwHshcgl.xhtml" target="mainFrame">货损管理</a>
							</li>
							<li>
								<a href="ucydhwHshcgl-querySpcx.xhtml" target="mainFrame">货损录入审核</a>
							</li>							
						</ul>
					</li> 		
				</ul>
			</div>
		</div>
</body>
</html>