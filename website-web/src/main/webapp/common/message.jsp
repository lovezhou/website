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
 <link rel="stylesheet" type="text/css" href="style/css/main.css" />
 <link rel="stylesheet" type="text/css" href="style/css/message.css" />
 <script type="text/javascript" src="style/js/jquery.js"></script>
<script type="text/javascript" src="style/js/countDown.js"></script>
<!--[if IE 6]><script type="text/javascript">try { document.execCommand('BackgroundImageCache', false, true); } catch(e) {}</script><![endif]-->
</head>
<body>
	<input type="hidden" name="toUrl" id="toUrl" number="5" toNumber="30" value="${url}" />
	<!-- 正确提示 -->
	<div class="siteMsgBox">
		<div class="siteMsgHd"></div>
		<div class="siteMsgBd">
			<div class="siteMsg">
				<div class="siteMsgmain">
					<div class="msgIco">
						<span class="correctIco"></span>
					</div>
					<div class="msgMain">
					<c:choose>
						<c:when test="${processList == null}">
						    <!-- 原来 -->
							<c:choose>
								<c:when test="${_type eq 'refresh'}">
									<h4 class="syscorrect">${result}</h4>
								</c:when>
								<c:otherwise>
									<h4 class="syscorrect">${result}</h4>
								<p>系统默认在 <em id="timeCountDown">5</em> 秒内跳转，您也可以点击以下链接继续：</p>
								<div>
									<a href="<c:out value="${pageContext.request.contextPath}/${url}"></c:out>" class="jessrun-btn"><span>返 回</span></a>
								</div>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<!-- 我改的 -->
							<h4 class="syscorrect">${result}</h4>
								<p>您也可以点击以下链接继续：</p>
							<div>
							<c:forEach items="${processList}" var="messageInfo">
								<c:if test="${messageInfo.type eq 'redirect'}">
									
										<a href="<c:out value="${pageContext.request.contextPath}/${messageInfo.url}"></c:out>" class="jessrun-btn"><span>${messageInfo.message}</span></a>
									
								</c:if>
								<c:if test="${messageInfo.type eq 'refresh'}">
									<h4 class="syscorrect">${result}</h4>
								</c:if>
							</c:forEach>
						</c:otherwise>
					</c:choose>
					</div>
					<div class="clear"></div>
				</div>
			</div>
		</div>
		<div class="siteMsgFt"></div>
	</div>
	<script>
	
	<c:choose>
		<c:when test="${processList == null}">
		    <!-- 原来 -->
			<c:choose>
				<c:when test="${_type eq 'refresh'}">
					$(function(){
						parent.location="${pageContext.request.contextPath}/${url}";
					});
				</c:when>
				<c:otherwise>
					$(function(){
						jessrun.urlTo("toUrl","timeCountDown");
					});
				</c:otherwise>
			</c:choose>
		</c:when>
		<c:otherwise>
			
		</c:otherwise>
	</c:choose>
	</script>
</body>
</html>


