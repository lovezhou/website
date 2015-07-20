<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isErrorPage="true"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>访问错误-500异常</title>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/main.css">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<STYLE type="text/css">
	A:link {
		COLOR: #555555;
		TEXT-DECORATION: none
	}
	
	A:visited {
		COLOR: #555555;
		TEXT-DECORATION: none
	}
	
	A:active {
		COLOR: #555555;
		TEXT-DECORATION: none
	}
	
	A:hover {
		COLOR: #6f9822;
		TEXT-DECORATION: none
	}
	
	.text {
		FONT-SIZE: 12px;
		COLOR: #555555;
		FONT-FAMILY: "";
		TEXT-DECORATION: none
	}
	
	.STYLE1 {
		font-size: 13px
	}
	
	.STYLE2 {
		font-size: 12px
	}
	
	.STYLE3 {
		font-size: 11px
	}
</STYLE>
<script type="text/javascript">
	function goHref(){
	    var pro = "<%=path%>";
	    if(self==top){
	    window.location.href=pro+"/main.xhtml";
	    }else if(window.parent==top){
	    window.parent.location.href=pro+"/main.xhtml";
	    }else if(window.parent.parent==top){
	     window.parent.parent.location.href=pro+"/main.xhtml";
	    }
    }
    </script>
	</head>

	<body>
		<TABLE height="90%" cellSpacing=0 cellPadding=0 width="100%" align=center border=0>

<TR>
<TD valign="middle" align="center">
<TABLE cellSpacing=0 cellPadding=0 width=500 align=center border=0>

<TR>
<TD width=17 height=17><IMG height=17 src="<%=basePath %>images/error/co_01.gif" width=17></TD>
<TD width=316 background="<%=basePath %>images/error/bg01.gif"></TD>
<TD width=17 height=17><IMG height=17 src="<%=basePath %>images/error/co_02.gif" width=17></TD>
</TR>
<TR>
<TD background="<%=basePath %>images/error/bg02.gif"></TD>
<TD>
<TABLE class=text cellSpacing=0 cellPadding=10 width="100%" align=center border=0>
<TR>
<TD>
<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
<TR>
<TD width=20>　</TD>
<TD><IMG height=66 src="<%=basePath %>images/error/500.gif" width=400></TD>
</TR></TABLE></TD></TR>
<TR>
<TD>
<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
<TR>
<TD background="<%=basePath %>images/error/dot_01.gif" height=1></TD></TR></TABLE><BR>
<TABLE class=text cellSpacing=0 cellPadding=0 width="100%" border=0>
<TR>
<TD width=20>　</TD>
<TD>
<P><STRONG><FONT color=#ba1c1c>HTTP500错误</FONT></STRONG>:<BR>
  <BR>
系统异常，session已经失效或其他原因。  <BR><BR>请尝试以下操作：<br>
·请点击退出重新登录后重试。<BR>
·打开该站点主页，然后查找您感兴趣信息的其他链接。<BR>
·单击<A href="javascript:history.back(1)"><font color="#BA1C1C">后退</font></A>链接，尝试其他链接。<BR>
·单击<A href="javascript:goHref();"><font color="#BA1C1C">首页</font></A>链接，尝试其他操作。</P>
<P align="right">如果您在浏览本站时，多次出现此错误，请与管理员联系&nbsp;</P>
<div align="right"></div></TD></TR></TABLE></TD></TR></TABLE></TD>
<TD background="<%=basePath %>images/error/bg03.gif"></TD>
</TR>
<TR>
<TD width=17 height=17><IMG height=17 src="<%=basePath %>images/error/co_03.gif" width=17></TD>
<TD background="<%=basePath %>images/error/bg04.gif" height=17></TD>
<TD width=17 height=17><IMG height=17 src="<%=basePath %>images/error/co_04.gif" width=17></TD>
</TR></TABLE>
<TABLE class=text cellSpacing=0 cellPadding=0 width=500 align=center border=0>
<TR>
<TD></TD></TR>
<TR>
<TD align="center"></TD></TR></TABLE></TD></TR></TABLE>
		<br>
		<div style="display: none;">错误提示:<%=exception.toString()%></div>
	</body>
</html>
