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
    
    <title>业务管理</title>
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
						<a href="javascript:void(0);" onClick="SwitchMenu('sub4');return false;">承运单管理</a>
						<ul id="sub4">
							<li>
								<a href="ucyd-perpareAddNormalUcyd.xhtml" target="mainFrame">添加承运单</a>
							</li>
							<li>
								<a href="ucyd-perpareAddUcyd.xhtml" target="mainFrame">添加正常承运单</a>
							</li>
							<li>
								<a href="ucyd-perpareAddZzUcyd.xhtml" target="mainFrame">添加中转承运单</a>
							</li>
							<li>
								<a href="ucyd-perpareAddWbUcyd.xhtml" target="mainFrame">添加外包承运单</a>
							</li>
							<li>
								<a href="ucyd-perpareAddPlUcyd.xhtml" target="mainFrame">批量录入承运单</a>
							</li>
							<li>
								<a href="ucyd-listUcyd.xhtml" target="mainFrame">承运单管理</a>
							</li>
						</ul>
					</li>
					<li name="li_sub8">
						<a href="javascript:void(0);" onClick="SwitchMenu('sub8');return false;">装车管理</a>
						<ul id="sub8" style="display: none;">
							<li>
								<a href="ucydZc-queryZcdList.xhtml" target="mainFrame">装车单列表</a>
							</li>
							<li>
								<a href="ucydZc-clxxList.xhtml" target="mainFrame">正常货物装车</a>
							</li>
							<li>
								<a href="ucydZc-zzclxxList.xhtml" target="mainFrame">配载装车</a>
							</li>
						</ul>
					</li> 
					<li name="li_sub19">
						<a href="javascript:void(0);" onClick="SwitchMenu('sub19');return false;">承运单签收管理</a>
						<ul id="sub19" style="display: none;">
							<li>
								<a href="ucydZc-listUcydZc.xhtml" target="mainFrame">货物到货签单</a>
							</li>
						</ul>
					</li> 
										 
					<li name="li_sub5">
						<a href="javascript:void(0);" onClick="SwitchMenu('sub5');return false;">中转单管理</a>
						<ul id="sub5" style="display: none;">
							<li>
								<a href="ucydZz-perpareAddUcydZz.xhtml" target="mainFrame">添加中转单</a>
							</li>
							<li>
								<a href="ucydZz-listUcydZz.xhtml" target="mainFrame">中转单管理</a>
							</li>
						</ul>
					</li> 
					<li name="li_sub1">
						<a href="javascript:void(0);" onClick="SwitchMenu('sub1');return false;">外包单管理</a>
						<ul id="sub1" style="display: none;">
							<li>
								<a href="ucydWb-perpareAddUcydWb.xhtml" target="mainFrame">添加外包单</a>
							</li>
							<li>
								<a href="ucydWb-listUcydWb.xhtml" target="mainFrame">外包单管理</a>
							</li>
						</ul>
					</li> 
					<li name="li_sub18">
						<a href="javascript:void(0);" onClick="SwitchMenu('sub18');return false;">送货管理</a>
						<ul id="sub18" style="display: none;">
						    <li>
						   	    <a href="ucydSh-perpareAddUcydSh.xhtml" target="mainFrame">添加送货单</a>
						    </li>
						    <li>
						   	    <a href="ucydSh-listUcydZt.xhtml" target="mainFrame">自提管理</a>
						    </li>
							<li>
								<a href="ucydSh-listUcydSh.xhtml" target="mainFrame">送货单查询</a>
							</li>
						</ul>
					</li> 
					<li name="li_sub7">
						<a href="javascript:void(0);" onClick="SwitchMenu('sub7');return false;">客户签收管理</a>
						<ul id="sub7" style="display: none;">
							<li>
								<a href="ucydQs-perpareAddUcydQs.xhtml" target="mainFrame">客户签收</a>
							</li>
							<li>
								<a href="ucydQs-listUcydQs.xhtml" target="mainFrame">签收单查询</a>
							</li>
						</ul>
					</li> 
					<li name="li_sub10">
						<a href="javascript:void(0);" onClick="SwitchMenu('sub10');return false;">签单管理</a>
						<ul id="sub10" style="display: none;">
							<li>
							    <a href="ucydhwQdgl-perpareAddUcydhwQdgl.xhtml?qdlx=1" target="mainFrame">签单回报</a>
							</li>
							<li>
								<a href="ucydhwQdgl-perpareAddUcydhwQdgl.xhtml?qdlx=2" target="mainFrame">签单接收</a>
							</li>
							<li>
								<a href="ucydhwQdgl-perpareAddUcydhwQdgl.xhtml?qdlx=3" target="mainFrame">签单发放</a>
							</li>
							<li>
								<a href="ucydhwQdgl-listUcydhwQdgl.xhtml" target="mainFrame">签单查询</a>
							</li>
						</ul>
					</li> 
					<li name="li_sub11">
						<a href="javascript:void(0);" onClick="SwitchMenu('sub11');return false;">回单销单管理</a>
						<ul id="sub11" style="display: none;">
							<li>
								<a href="ucydhwHdxdgl-listUcydhwHdxdgl.xhtml" target="mainFrame">回单销单管理</a>
							</li>
						</ul>
					</li>
					<li name="li_sub2">
						<a href="javascript:void(0);" onClick="SwitchMenu('sub2');return false;">库存管理</a>
						<ul id="sub2" style="display: none;">
							<li>
								<a href="uhwkc-listUhwkc.xhtml" target="mainFrame">库存管理</a>
							</li>
						</ul>
					</li> 
					<li name="li_sub6">
						<a href="javascript:void(0);" onClick="SwitchMenu('sub6');return false;">挂失管理</a>
						<ul id="sub6" style="display: none;">
							<li>
								<a href="ucydhwGsb-listUcydhwGsb.xhtml" target="mainFrame">挂失管理</a>
							</li>
						</ul>
					</li> 
				</ul>
			</div>
		</div>
</body>
</html>