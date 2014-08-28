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
    
    <title>财务管理</title>
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
				<li name="li_sub6">
						<a href="javascript:void(0);" onClick="SwitchMenu('sub6');return false;">财务准备</a>
						<ul id="sub6" style="display: none;">
							<li>
								<a href="ucwFylb-cwfylb.xhtml" target="mainFrame">财务科目管理</a>
							</li>
							<li>
								<a href="ufykmCwkm-fykmCwkmYs.xhtml" target="mainFrame">财务费用映射</a>
							</li>
						</ul>
					</li> 
				<li name="li_sub1">
						<a href="javascript:void(0);" onClick="SwitchMenu('sub1');return false;">日常费用管理</a>
						<ul id="sub1" style="display: none;">
							<li>
								<a href="ucwRcfygl-addRcfykmgl.xhtml" target="mainFrame">日常费用录入</a>
							</li>
							<li>
								<a href="ucwRcfygl-shUcwRcfygList.xhtml" target="mainFrame">日常费用审批</a>
							</li>
							<li>
								<a href="ucwRcfygl-queryUcwRcfyglList.xhtml" target="mainFrame">日常费用查询</a>
							</li>
						</ul>
					</li> 
					<li name="li_sub32">
						<a href="javascript:void(0);" onClick="SwitchMenu('sub32');return false;">代收款管理</a>
						<ul id="sub32" style="display: none;">
							<li>
								<a href="ucwFyjsb-listJsUcydHwDskgl.xhtml" target="mainFrame">代收款付款申请</a>
							</li>
							<li>
								<a href="ucwFyjsb-listApproveUcwFyjsb.xhtml?bs=dskgl" target="mainFrame">代收款付款审批</a>
							</li>
							<li>
								<a href="ucwFyjsb-listPayUcwFyjsb.xhtml?bs=dskgl" target="mainFrame">代收款付款</a>
							</li>
							
							<li>
								<a href="ucwDskyhfkxx-listUcwDskyhfkxx.xhtml" target="mainFrame">生成银行付款</a>
							</li>
							<li>
								<a href="ucwDskyhfkxx-listUcwDskyhfkxx2Yhcl.xhtml" target="mainFrame">银行处理</a>
							</li>
							<li>
								<a href="ucwFyjsb-listUcwFyjsb.xhtml?bs=dskgl" target="mainFrame">代收款查询</a>
							</li>
						</ul>
					</li>
					<li name="li_sub31">
						<a href="javascript:void(0);" onClick="SwitchMenu('sub31');return false;">应付款管理</a>
						<ul id="sub31" style="display: none;">
							<li>
								<a href="ucwFyjsb-listJsUcydHwSjyffk.xhtml" target="mainFrame">司机运费付款申请</a>
							</li>
							<li>
								<a href="ucwFyjsb-listJsUcydHwWbsfk.xhtml" target="mainFrame">外包收/付款申请</a>
							</li>
							<li>
								<a href="ucwFyjsb-listJsUcydHwZzsfk.xhtml" target="mainFrame">中转收/付款申请</a>
							</li>
							<li>
								<a href="ucwFyjsb-listApproveUcwFyjsb.xhtml?bs=sjydfk,wbfsfk,zzfsfk" target="mainFrame">审批</a>
							</li>
							<li>
								<a href="ucwFyjsb-listPayUcwFyjsb.xhtml?bs=sjydfk,wbfsfk,zzfsfk" target="mainFrame">收/付款</a>
							</li>
							<li>
								<a href="ucwFyjsb-listUcwFyjsb.xhtml?bs=sjydfk,wbfsfk,zzfsfk" target="mainFrame">收/付款查询</a>
							</li>
						</ul>
					</li>
					
					<li name="li_sub34">
						<a href="javascript:void(0);" onClick="SwitchMenu('sub34');return false;">发货站点收款</a>
						<ul id="sub34" style="display: none;">
							<li>
								<a href="ucwFyjsb-listJsUcydHwFhzdsk.xhtml" target="mainFrame">发货站点收款申请</a>
							</li>
							<li>
								<a href="ucwFyjsb-listApproveUcwFyjsb.xhtml?bs=fhzdsk" target="mainFrame">收款审批</a>
							</li>
							<li>
								<a href="ucwFyjsb-listPayUcwFyjsb.xhtml?bs=fhzdsk" target="mainFrame">收款</a>
							</li>
							<li>
								<a href="ucwFyjsb-listUcwFyjsb.xhtml?bs=fhzdsk" target="mainFrame">收款查询</a>
							</li>
						</ul>
					</li>  
					<li name="li_sub35">
						<a href="javascript:void(0);" onClick="SwitchMenu('sub35');return false;">收货站点收款</a>
						<ul id="sub35" style="display: none;">
							<li>
								<a href="ucwFyjsb-listJsUcydHwShzdsk.xhtml" target="mainFrame">收货站点收款</a>
							</li>
							<li>
								<a href="ucwFyjsb-listApproveUcwFyjsb.xhtml?bs=shzdsk" target="mainFrame">收款审批</a>
							</li>
							<li>
								<a href="ucwFyjsb-listPayUcwFyjsb.xhtml?bs=shzdsk" target="mainFrame">收款</a>
							</li>
							<li>
								<a href="ucwFyjsb-listUcwFyjsb.xhtml?bs=shzdsk" target="mainFrame">收款查询</a>
							</li>
						</ul>
					</li> 
					<li name="li_sub36">
						<a href="javascript:void(0);" onClick="SwitchMenu('sub36');return false;">回付/结管理</a>
						<ul id="sub36" style="display: none;">
							<li>
								<a href="ucwFyjsb-listJsUcydHwHfjgl.xhtml" target="mainFrame">回付/结申请</a>
							</li>
							<li>
								<a href="ucwFyjsb-listApproveUcwFyjsb.xhtml?bs=hfjgl" target="mainFrame">回付/结审批</a>
							</li>
							<li>
								<a href="ucwFyjsb-listPayUcwFyjsb.xhtml?bs=hfjgl" target="mainFrame">回付/结收款</a>
							</li>
							<li>
								<a href="ucwFyjsb-listUcwFyjsb.xhtml?bs=hfjgl" target="mainFrame">回付/结查询</a>
							</li>
						</ul>
					</li> 
					<li name="li_sub37">
						<a href="javascript:void(0);" onClick="SwitchMenu('sub37');return false;">信息费</a>
						<ul id="sub37" style="display: none;">
							<li>
								<a href="ucwFyjsb-listJsUcydHwXxf.xhtml" target="mainFrame">信息费申请</a>
							</li>
							<li>
								<a href="ucwFyjsb-listApproveUcwFyjsb.xhtml?bs=xxf" target="mainFrame">信息费审批</a>
							</li>
							<li>
								<a href="ucwFyjsb-listPayUcwFyjsb.xhtml?bs=xxf" target="mainFrame">信息费付款</a>
							</li>
							<li>
								<a href="ucwFyjsb-listUcwFyjsb.xhtml?bs=xxf" target="mainFrame">信息费查询</a>
							</li>
						</ul>
					</li> 
					<li name="li_sub38">
						<a href="javascript:void(0);" onClick="SwitchMenu('sub38');return false;">返中转费</a>
						<ul id="sub38" style="display: none;">
							<li>
								<a href="ucwFyjsb-listJsUcydHwFzzf.xhtml" target="mainFrame">返中转费</a>
							</li>
							<li>
								<a href="ucwFyjsb-listApproveUcwFyjsb.xhtml?bs=fzzf" target="mainFrame">返中转费审批</a>
							</li>
							<li>
								<a href="ucwFyjsb-listPayUcwFyjsb.xhtml?bs=fzzf" target="mainFrame">返中转费收款</a>
							</li>
							<li>
								<a href="ucwFyjsb-listUcwFyjsb.xhtml?bs=fzzf" target="mainFrame">返中转费查询</a>
							</li>
						</ul>
					</li> 
					<li name="li_sub39">
						<a href="javascript:void(0);" onClick="SwitchMenu('sub39');return false;">月结</a>
						<ul id="sub39" style="display: none;">
							<li>
								<a href="ucwFyjsb-listJsUcydHwYj.xhtml" target="mainFrame">月结申请</a>
							</li>
							<li>
								<a href="ucwFyjsb-listApproveUcwFyjsb.xhtml?bs=yj" target="mainFrame">月结审批</a>
							</li>
							<li>
								<a href="ucwFyjsb-listPayUcwFyjsb.xhtml?bs=yj" target="mainFrame">月结收款</a>
							</li>
							<li>
								<a href="ucwFyjsb-listUcwFyjsb.xhtml?bs=yj" target="mainFrame">月结查询</a>
							</li>
						</ul>
					</li>
					<li name="li_sub40">
						<a href="javascript:void(0);" onClick="SwitchMenu('sub40');return false;">装卸费</a>
						<ul id="sub40" style="display: none;">
							<li>
								<a href="ucwFyjsb-listJsUcydHwZxftf.xhtml" target="mainFrame">收货站点装卸费申请</a>
							</li>
							<li>
								<a href="ucwFyjsb-listJsUcydHwZxf.xhtml" target="mainFrame">发货站点装卸费申请</a>
							</li>
							<li>
								<a href="ucwFyjsb-listApproveUcwFyjsb.xhtml?bs=zxf" target="mainFrame">发货站点装卸费审批</a>
							</li>
							<li>
								<a href="ucwFyjsb-listPayUcwFyjsb.xhtml?bs=zxf" target="mainFrame">发货站点装卸费付款</a>
							</li>
							<li>
								<a href="ucwFyjsb-listApproveUcwFyjsb.xhtml?bs=zxftf" target="mainFrame">收货站点装卸费审批</a>
							</li>
							<li>
								<a href="ucwFyjsb-listPayUcwFyjsb.xhtml?bs=zxftf" target="mainFrame">收货站点装卸费付款</a>
							</li>
							<li>
								<a href="ucwFyjsb-listUcwFyjsb.xhtml?bs=zxftf" target="mainFrame">收货站点装卸费查询</a>
							</li>
							<li>
								<a href="ucwFyjsb-listUcwFyjsb.xhtml?bs=zxf" target="mainFrame">发货站点装卸费查询</a>
							</li>
							
						</ul>
					</li>
					<li name="li_sub41">
						<a href="javascript:void(0);" onClick="SwitchMenu('sub41');return false;">送货费</a>
						<ul id="sub41" style="display: none;">
							<li>
								<a href="ucwFyjsb-listJsUcydHwShf.xhtml" target="mainFrame">送货费</a>
							</li>
							<li>
								<a href="ucwFyjsb-listApproveUcwFyjsb.xhtml?bs=shf" target="mainFrame">送货费审批</a>
							</li>
							<li>
								<a href="ucwFyjsb-listPayUcwFyjsb.xhtml?bs=shf" target="mainFrame">送货费付款</a>
							</li>
							<li>
								<a href="ucwFyjsb-listUcwFyjsb.xhtml?bs=shf" target="mainFrame">送货费查询</a>
							</li>
						</ul>
					</li>
					<li name="li_sub16">
						<a href="javascript:void(0);" onClick="SwitchMenu('sub16');return false;">货物结单管理</a>
						<ul id="sub16" style="display: none;">
							<li>
								<a href="ucwHwjdgl-prepareApplyUcwHwjdgl.xhtml" target="mainFrame">货物结单申请</a>
							</li>
							<li>
								<a href="ucwHwjdgl-prepareCheckUcwHwjdgl.xhtml" target="mainFrame">货物结单审批</a>
							</li>
							<li>
								<a href="ucwHwjdgl-listUcwHwjdgl.xhtml" target="mainFrame">货物结单查询</a>
							</li>
						</ul>
					</li> 
				<li name="li_sub15">
						<a href="javascript:void(0);" onClick="SwitchMenu('sub15');return false;">费用查询</a>
						<ul id="sub15" style="display: none;">
							<li>
								<a href="ucwFyjsb-listJkUcydHw.xhtml" target="mainFrame">缴款记录查询</a>
							</li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
</body>
</html>