<%@page import="java.net.URLDecoder,com.jessrun.certify.common.NewCertifyUtilByCookie,com.jessrun.certify.constant.CookieNames"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7, IE=9" />
 <title>好运通物流业务管理系统</title>
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/css/login.css" />
<!--[if IE 6]><script type="text/javascript">try { document.execCommand('BackgroundImageCache', false, true); } catch(e) {}</script><![endif]-->
</head>
<%
String companyNameSIM = (String)request.getParameter("s");
if(companyNameSIM == null){
	companyNameSIM = (String)request.getAttribute("s");
}


String cookieMessage = NewCertifyUtilByCookie.getLoginErrorMessage(request, response);
if(cookieMessage != null){
	request.setAttribute("errorMessage",URLDecoder.decode(cookieMessage,"UTF-8"));
}
%>
<body >
	<div class="loginBox">
		<h1>捷信运·好运通物流业务管理系统</h1>
		<p class="welcome">欢迎使用</p>
		<form action="${pageContext.request.contextPath}/newlogin.do" id="login_Form" method="POST">
		<div class="form" id="loginForm">
			<input type="hidden" name="s" value="<%=companyNameSIM %>"/>
			<div class="form_tip">
			<c:choose>
				<c:when test="${je.desc != null}">
				<em>${je.desc}</em>
				</c:when>
				<c:when test="${errorMessage != null}">
				<em>${errorMessage}</em>
				</c:when>
				<c:otherwise>
					请输入用户名和密码
				</c:otherwise>
			</c:choose>
			</div>
			<div class="form_lis">
				<label class="n">用户名：</label>
				<div class="v"><span><input type="text" name="account" value="" autocomplete="off" id="userName" class="txt {required:true,messages:{required:'请输入用户名'}}"/></span></div>
			</div>
			<div class="form_lis">
				<label class="n">密　码：</label>
				<div class="v"><span><input type="password" name="password" value=""  autocomplete="off" id="userPwd" class="txt {required:true,messages:{required:'请输入密码'}}" /></span></div>
			</div>
			<div class="form_btn">
				<a href="javascript:;" id="login_btn" class="btn"><span>登 录</span></a>
			</div>
		
		</div>
		</form>
		<div class="copyright">
			Copyrigh &copy; 2013 捷信运 All rights reserved
		</div>
	</div>
	 <script type="text/javascript" src="${pageContext.request.contextPath}/style/js/jquery.js"></script>
	 <jsp:include page="/layout/loadFormJs.jsp" flush="true"></jsp:include>
	 <script type="text/javascript" src="${pageContext.request.contextPath}/style/js/login.js"></script>
</body>

</html>


