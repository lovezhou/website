<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title></title>
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="this is my page">
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">


	</head>

	<body>
		<%String mes =(String)request.getAttribute("thisLayOutFlag"); 
    String path = request.getContextPath();   
    if((mes!=null)&&(!mes.equals(""))){
    %>
		<script type="text/javascript">
    var pro = "<%=path%>";
    if(self==top){
    window.location.href=pro+"/login.xhtml";
    }else if(window.parent==top){
    window.parent.location.href=pro+"/login.xhtml";
    }else if(window.parent.parent==top){
     window.parent.parent.location.href=pro+"/login.xhtml";
    }
    </script>

		<%
			}
		%>
	</body>
</html>
