<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7, IE=9" />
 <title>好运通物流业务管理系统</title>
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/css/top.css" />
<!--[if IE 6]><script type="text/javascript">try { document.execCommand('BackgroundImageCache', false, true); } catch(e) {}</script><![endif]-->
</head>

<body >
	<div class="top">
		
			<!-- 特殊判断 利兴和logo -->
			<c:choose>
			<c:when test="${userCompanyNameSim=='LiXingHe'}">
			<div id="wl-lxhLogo">
				
			</c:when>
				<c:otherwise>
				<div class="jessrun-top">
					<h1><a href="http://www.jessrun.com.cn" target="_blank" title="捷信运"><span>捷信运</span></a></h1>
					<h2>
						<span>
						${userCompanyNameSimCn != null ? userCompanyNameSimCn : '捷信运'}
					</span></h2>
				</c:otherwise>
			</c:choose>
			<h3><em>·</em><span>好运通物流业务管理系统</span></h3>
			<div class="user-top">
				<p id="userCompanyName"><em>${userCompanyName}</em></p>
				<p>
					欢迎您！<em>${userName }</em> |
					<c:choose>
						<c:when test="${userId != null}">
							<a href='${pageContext.request.contextPath}/gotoNewModifyPW.action'  target='mainFrame'>修改密码</a> |
							<a href='${pageContext.request.contextPath}/layout/home.jsp'  target='mainFrame'>首 页</a> |
							<a href='${pageContext.request.contextPath}/newlogout.do' target='_top'>退出</a>
						</c:when>
						<c:otherwise>
							您还未登录，请点此选择 <a href='${pageContext.request.contextPath}/newlogout.do' target='_top'>登录</a>
						</c:otherwise>
					</c:choose>

					 </p>
			</div>
		</div>
	</div>
</body>

</html>

