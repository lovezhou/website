<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
    
    <title>经营分析</title>
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
					<li name="li_sub4">
						<a href="javascript:void(0);" onClick="SwitchMenu('sub4');return false;">财务报表</a>
						<ul id="sub4" style="display: none;">
							<li>
								<a href="baobiao-grid.xhtml?type=dsktjb" target="mainFrame">代收款统计</a>
							</li>
							<li>
								<a href="baobiao-grid.xhtml?type=yjhjbb" target="mainFrame">月结回结报表</a>
							</li>
							<li>
								<a href="baobiao-grid.xhtml?type=fhrjsb" target="mainFrame">发货人结算表</a>
							</li>
							<li>
								<a href="baobiao-grid.xhtml?type=fhrtjb" target="mainFrame">发货人统计表</a>
							</li>
							<li>
								<a href="baobiao-grid.xhtml?type=fhzdjsb" target="mainFrame">发货站点结算表</a>
							</li>
								<li>
								<a href="baobiao-grid.xhtml?type=shzdjsb" target="mainFrame">收货站点结算表</a>
							</li>
							<li>
								<a href="baobiao-grid.xhtml?type=wlzmmxb" target="mainFrame">往来账目明细表</a>
							</li>
							<li>
								<a href="baobiao-grid.xhtml?type=zdyskbb" target="mainFrame">站点应收款报表</a>
							</li>
						</ul>
					</li> 
						<li name="li_sub5">
						<a href="javascript:void(0);" onClick="SwitchMenu('sub5');return false;">业务报表</a>
						<ul id="sub5" style="display: none;">
							<li>
								<a href="baobiao-grid.xhtml?type=hwcydbb" target="mainFrame">货物承运单报表</a>
							</li>
							<li>
								<a href="baobiao-grid.xhtml?type=khdzb" target="mainFrame">客户对账表</a>
							</li>
							<li>
								<a href="baobiao-grid.xhtml?type=zdrbb" target="mainFrame">站点日报表</a>
							</li>
							<li>
								<a href="baobiao-grid.xhtml?type=zzjsbb" target="mainFrame">中转结算报表</a>
							</li>
							<li>
								<a href="baobiao-grid.xhtml?type=zzwzbb" target="mainFrame">中转未转报表</a>
							</li>
							<li>
								<a href="baobiao-zzgsdzd.xhtml" target="mainFrame">中转公司对帐单</a>
							</li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
</body>
</html>