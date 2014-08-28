<%@ page language="java" import="com.jessrun.exception.ResultProcessType,com.jessrun.exception.JessrunException" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7, IE=9" />

 <title>好运通物流业务管理系统</title>
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/css/main.css" />
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/css/message.css" />
 <script type="text/javascript" src="${pageContext.request.contextPath}/style/js/jquery.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/style/js/countDown.js"></script>
<!--[if IE 6]><script type="text/javascript">try { document.execCommand('BackgroundImageCache', false, true); } catch(e) {}</script><![endif]-->
</head>
<body>
	<input type="hidden"  name="toUrl" id="toUrl" number="5" value="${pageContext.request.contextPath}/${je.gotoURL}"/>
	<!-- 错误提示 -->
	<div class="siteMsgBox">
		<div class="siteMsgHd"></div>
		<div class="siteMsgBd">
			<div class="siteMsg">
				<div class="siteMsgmain">
					<div class="msgIco">
						<span class="helpIcoIco"></span>
					</div>
					<div class="msgMain">
						<!-- je.desc 异常信息 -->
						<h4 class="syshelpIco">${je.desc}</h4>
						<%
						
						JessrunException je = (JessrunException)request.getAttribute("je");
						
						switch(je.getResultProcessType()){
						case AUTO_REDIRECT_FOR_WAIT:
							%>
								<!-- 登录超时 或未登录 -->
								<p>系统默认在 <em id="timeCountDown">5</em> 秒内跳转到登录页面，您也可以点击以下链接继续：</p>
								<div>
									<a href="${pageContext.request.contextPath}/${je.gotoURL}" target="_top" class="jessrun-btn"><span>重新登录</span></a>
								</div>
								<script>
								$(function(){
									jessrun.urlToLogin("toUrl","timeCountDown");
								});
								</script>		
							<%
							break;
						case HISTORY_FOR_WAIT:
							%>
							
								<!-- 默认跳转到历史上一页 这里跳转到首页-->
		
							
							<%
							Object o = request.getParameter("fromDialog");
							if(o == null){
								o = request.getAttribute("fromDialog");
							}
							
							if(o == null){
							%>
							<p>系统默认在 <em id='timeCountDown'>5</em> 秒内跳转到登录后首页，您也可以点击以下链接继续：</p>
							<div>
								<a  href='javascript:history.go(-1)' class='jessrun-btn'><span>返 回</span></a>
							</div>
							<script>
							$(function(){
								jessrun.urlToHistroy('toUrl','timeCountDown');
							});
							</script>
							<%
							}else{
								%>
								<div><a  href='javascript:parent.jessrun.Dialog.removeBox()' class='jessrun-btn'><span>关 闭</span></a></div>
								<%
							}
							
							break;
						case WAIT_FOR_CLOSE:
							%>
							<!-- 默认关闭 -->
								$(function(){
									window.opener = null; 
									window.open("","_self");
									window.close();
								});
							<%
							break;
						}
						%>
					</div>
					<div class="clear"></div>
				</div>
			</div>
		</div>
		<div class="siteMsgFt"></div>
	</div>
	
	 
</body>
</html>


