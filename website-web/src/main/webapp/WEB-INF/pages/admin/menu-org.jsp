<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>系统管理</title>
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
						<a href="javascript:void(0);" onClick="SwitchMenu('sub1');return false;">基础设定</a>
						<ul id="sub1">
							<li>
								<a href="user-listXitongyonghu.xhtml" target="mainFrame">用户管理</a>
							</li>
							<li>
								<a href="juesexinxi-listJuesexinxi.xhtml" target="mainFrame">角色管理</a>
							</li>
							<li>
								<a href="menu-bm.xhtml" target="mainFrame">部门管理</a>
							</li>
							<li>
								<a href="yclxlgl-listYclxlgl.xhtml" target="mainFrame">线路管理</a>
							</li>
							<li>
								<a href="yhzgs-listYhzgs.xhtml" target="mainFrame">合作公司管理</a>
							</li>
							<li>
								<a href="yygxx-listYygxx.xhtml" target="mainFrame">员工管理</a>
							</li>
							<li>
								<a href="yhwmcb-hwmc.xhtml" target="mainFrame">货物类型管理</a>
							</li>
							<li>
								<a href="yclxx-listYclxx.xhtml" target="mainFrame">车辆管理</a>
							</li>
							<li>
								<a href="ykhxx-listYkhxx.xhtml" target="mainFrame">客户信息管理</a>
							</li>
							<li>
								<a href="ucwYhkkbl-perpareAddUcwYhkkbl.xhtml?lx=2" target="mainFrame">银行扣款比例</a>
							</li>
							<li>
								<a href="ucwYhkkbl-perpareAddUcwYhkkbl.xhtml?lx=1" target="mainFrame">保额设置</a>
							</li>
						</ul>
					</li>
					<li name="li_sub2">
						<a href="javascript:void(0);" onClick="SwitchMenu('sub2');return false;">日志管理</a>
						<ul id="sub2" style="display: none;">
							<li>
								<a href="xtrizhi-listXitongrizhi.xhtml" target="mainFrame">日志查询</a>
							</li>
						</ul>
					</li>
					<li name="li_sub3">
						<a href="javascript:void(0);" onClick="SwitchMenu('sub3');return false;">通知公告管理</a>
						<ul id="sub3" style="display: none;">
							<li>
								<a href="ytzgg-listYtzgg.xhtml" target="mainFrame">公告管理</a>
							</li>
						</ul>
					</li> 
					<li name="li_sub14">
						<a href="javascript:void(0);" onClick="SwitchMenu('sub14');return false;">知识库管理</a>
						<ul id="sub14" style="display: none;">
							<li>
								<a href="menu-zsklx.xhtml" target="mainFrame">知识库类型</a>
							</li>
							<li>
								<a href="menu-zskxx.xhtml" target="mainFrame">知识库管理</a>
							</li>
						</ul>
					</li> 
				</ul>
			</div>
		</div>
</body>
</html>