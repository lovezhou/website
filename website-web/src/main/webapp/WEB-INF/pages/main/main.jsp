<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>信运通物流业务管理系统</title>
    <%@include file="../head_include.jsp" %>
    <link id="page_favicon" href="<%=basePath %>images/favicon.ico" rel="icon" type="image/x-icon" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/left.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/main.css">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
 <script>
 $(function(){
	 setTimeout(function(){
			//$('body').layout('collapse','east');
			 $("div[region='west']").html('<iframe  frameborder="0" name="leftFrame" id="leftFrame"  src="menu.do" style="width:100%;height:100%;"></iframe>');
			 $("div[region='center']").html('<iframe scrolling="yes" frameborder="0" name="mainFrame" id="mainFrame" src="layout/main.html" style="width:100%;height:100%;"></iframe>');
			// $("div[region='south']").html('<iframe scrolling="no" id="buttomFrame" name="buttomFrame" frameborder="0"  src="top-buttom.xhtml" style="width:100%;height:100%;"></iframe>');
		 },0);
	 
		
	});

	</script>
</head>
<body class="easyui-layout">
	<div region="north" border="false" split="false" style="height:100px;background:#B3DFDA;" href="top.do">
	</div>
	<div region="west" split="true" title="导航菜单" iconCls="icon-dhcd" style="width:180px;padding:0px;overflow: hidden;">
	
	</div>
	<div region="south" align="center" split="false"  style="height:30px;background:#A9FACD;padding:9px; ">版权所有 Copyright © 信运通信息产业有限公司 鄂ICP备13002219号</div>
	<div region="center" title="当前位置：首页" iconCls="icon-location" style="width:100%;height:100%;overflow: auto;">
	</div>
</body>
  <div id="pp"
	style="width:500px;height:150px;padding:10px;background:#fafafa;display: none;"/>
</html>
