<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>顶部</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	</head>
	<body>
		<div class="topbg">
			<div>
				<div>
					<div class="top-right right" style="padding-top:1px;">
					<span><img src="<%=basePath %>/images/icon/help.gif" width="14" height="15"
								style="padding-top: 1px;"> <a href="download.xhtml?inputPath=/download/help.doc"
							target="mainFrame">帮助文档</a>
						</span>&nbsp;&nbsp;
						<span><img src="<%=basePath %>/images/icon/home.gif" width="16" height="16"
								style="padding-top: 1px;"> <a href="user-preEditMm.xhtml"
							target="mainFrame">修改密码</a>
						</span>&nbsp;&nbsp;
						 <c:url value="/login-logOut.xhtml" var="logoutUrl"/>  
						<span><img src="<%=basePath %>/images/icon/close.gif" width="16"
								height="16"> <a href="${logoutUrl}" >退出系统</a>
						</span>&nbsp;&nbsp;
						<div style="padding-top: 14px;">
							<span id="vartime"></span>
						</div>

					</div>
					<div class="toplogo left"></div>
					<div>
						<div class="top-user left">
							<div class="top-user-icon left"></div>
							<div class="left">
								xxx管理员
							</div>
						</div>
					</div>

					<div id="topmenu" class="tabsJ">
						<ul>
						<li class="kong"></li>
							<li>
								<a href="#"
									onClick="change(this,'menu-xtgn.do','main/main.html');"
									onfocus="this.blur();"><span>系统功能</span> </a>
							</li>
							<li class="kong"></li>
							<li>
								<a href="#"
									onClick="change(this,'menu-cw.xhtml','main/main.html');"
									onfocus="this.blur();"><span>业务功能</span> </a>
							</li>
							<li class="kong"></li>
							<li>
								<a href="#"
									onClick="change(this,'menu-jyfx.xhtml','main/main.html');"
									onfocus="this.blur();"><span>经营分析</span> </a>
							</li>
							<li class="kong"></li>
							<li>
								<a href="#"
									onClick="change(this,'menu-yc.xhtml','main/main.html');"
									onfocus="this.blur();"><span>异常管理</span> </a>
							</li>
							<li class="kong"></li>
							<li>
								<a href="#"
									onClick="change(this,'menu-org.xhtml','main/main.html');"
									onfocus="this.blur();"><span>系统管理</span> </a>
							</li>
							<li class="kong"></li>
							<s:if test="gsId==1">
							<li>
								<a href="#"
									onClick="change(this,'menu-whxt.xhtml','main/main.html');"
									onfocus="this.blur();"><span>维护系统</span> </a>
							</li>
							<li class="kong"></li>
							</s:if>
						</ul>
					</div>
				</div>
				
	</body>
	 <SCRIPT>
		var enabled = 0;
		today = new Date();
		if(today.getDay()==0) day = "星期日";
		if(today.getDay()==1) day = "星期一";
		if(today.getDay()==2) day = "星期二";
		if(today.getDay()==3) day = "星期三";
		if(today.getDay()==4) day = "星期四";
		if(today.getDay()==5) day = "星期五";
		if(today.getDay()==6) day = "星期六";
		date = "今天是："+(today.getFullYear())  + "年" + (today.getMonth() + 1 ) + "月" + today.getDate() + "日  " + " " + day;
		document.getElementById("vartime").innerHTML=date;
	</SCRIPT>
</html>