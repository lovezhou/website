<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>维护系统</title>
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
					<li name="li_sub1">
						<a href="javascript:void(0);" onClick="SwitchMenu('sub1');return false;"></a>
						<ul id="sub1">
							<li>
								<a href="" target="mainFrame">用户管理</a>
							</li>
							<li>
								<a href="" target="mainFrame">角色管理</a>
							</li>
							<li>
								<a href="" target="mainFrame">权限管理</a>
							</li>
						
							<li>
								<a href="" target="mainFrame">ip访问监控</a>
							</li>
							<li>
								<a href="monitoring" target="mainFrame">系统性能监控</a>
							</li>
							<li>
								<a href="<%=basePath %>/sysDict/toConfig.do" target="mainFrame">数据字典维护</a>
							</li>
							<li>
								<a href="<%=basePath %>/codegenerator/codegenerator.do" target="mainFrame">代码自动生成</a>
							</li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
</body>
</html>