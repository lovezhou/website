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
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/css/left.css" />
<!--[if IE 6]><script type="text/javascript">try { document.execCommand('BackgroundImageCache', false, true); } catch(e) {}</script><![endif]-->
<script src="${pageContext.request.contextPath}/style/js/jquery.js" type="text/javascript"></script>


</head>
</head>
<body>
<div id="left-menu">
	<h1>功能导航</h1>
	<div class="menu" id="menu">

	</div>
</div>
<script type="text/javascript">

function addMenuItem(id,pid,name,type,url){
	var trObj = "";
	if(pid == 0 && type == 3){
		trObj += '<div class="toggleHd "><h2 class="ico-1"><a href="';
		if(url == ""){
			trObj += 'javascript:;';
		}else{
			trObj += '${pageContext.request.contextPath}' + url;
		}
		trObj += '" class="toggleHd-a" target="mainFrame">' + name + '</a></h2></div>';
		trObj += '<div class="toggleBd">';
		trObj += '<div class="siderNav">';
		trObj += '<ul id="menu_' + id + '">';
		trObj += '</ul>';
		trObj += '</div>';
		trObj += '</div>';
		$("#menu").append(trObj);
	}else if(pid > 0 && type == 3){
		trObj += '<li class="sider-li">';
		trObj += '<div class="sider-li-item">';
		trObj += '<a class="sider-item-a" target="mainFrame" href="javascript:;">' + name + '</a>';
		trObj += '</div>';
    	trObj += '<ul class="sider-item-bd" id="menu_' + id +'">';
    	trObj += '</ul>';
		trObj += '</li>';
		$("#menu_" + pid).append(trObj);
	}else if(type == 4){
		trObj += '<li class="sider-li" id="menu_item_' + id + '"><a target="mainFrame" class="sider-a" href="${pageContext.request.contextPath}' + url + '">' + name + '</a></li>';
		$("#menu_" + pid).append(trObj);
	}
	
}

$(document).ready(function(){
	<c:forEach items="${resultList}" var="menuitems">
		addMenuItem(${menuitems.id},${menuitems.pid},'${menuitems.name}',${menuitems.type},'${menuitems.url}');
	</c:forEach>
});


</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/style/js/layout.js"></script>
</body>
</html>


