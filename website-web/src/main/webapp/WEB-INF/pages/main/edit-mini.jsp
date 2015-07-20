<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<link rel="stylesheet" type="text/css" href="<%=basePath %>js/easyui/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=basePath %>js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=basePath %>css/left.css">
<link rel="stylesheet" type="text/css" href="<%=basePath %>css/main.css">
<link rel="stylesheet" type="text/css" href="<%=basePath %>css/reset.css">
<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>js/easyui/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/common.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jquery.blockUI.js"></script>
<script type="text/javascript" src="<%=basePath %>js/easyuiutil.js"></script>
<script type="text/javascript" src="<%=basePath %>js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jessrun.js"></script>
<script type="text/javascript" src="<%=basePath %>js/init.js"></script>
 <body onload="loadContent();return false;">
 <div style="margin-left: 10px; margin-right: auto; margin-top: 5px; width: 400px; height: 230px;">
 	<div>
	 	<textarea id="area_content" name="content" rows="15" cols="57" maxlength="50" style="width: 400px; height: 230px; border: 0;overflow: hidden; background-color: #F5FFFA;"></textarea>
	 	<input type="hidden" id="hid_id" name="id" value="<%=request.getParameter("id")%>"/>
	 	<input type="hidden" id="hid_fun" name="fun" value="<%=request.getParameter("fun")%>"/>
	 </div>
	 <div>
		 <a name="close" class="easyui-linkbutton" iconCls="icon-close" style="float: right" onclick="closeWindow();return false;">关闭</a>
	 	 <a name="enter" class="easyui-linkbutton" iconCls="icon-ok" style="float: right" onclick="pushToParent();return false;" >确定</a>
	 </div>
 </div>
	
</body>
<script type="text/javascript">
	function loadContent(){
		var id = document.getElementById("hid_id").value;
		var content = parent.document.getElementById(id).value;
		//document.getElementById("area_content").value=content;
		$("#area_content").val(content);
		
	}
	function pushToParent(){
		var fun = document.getElementById("hid_fun").value;
		var content = document.getElementById("area_content").value;
		var id = document.getElementById("hid_id").value;
		parent.document.getElementById(id).value=content;
		parent.$("#"+id).change();
		closeWindow();
	}
	function closeWindow(){
		jessrun.closeIframeDialog();return false;
	}
</script>
