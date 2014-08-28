<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
      <link rel="stylesheet" type="text/css" href="<%=basePath %>js/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>js/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/left.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/main.css">
<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/easyui/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/common.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/jquery.blockUI.js"></script>
		<script type="text/javascript" src="<%=basePath %>js/easyuiutil.js"></script>
    <title>接待单</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript">
	function submitThis(){
		cxf.IsPSubmit("form0","addBtn");
	}
	function detail(){
	var ldhm = document.getElementById("ldhm").value;
	if(ldhm==null||ldhm==""){
		palertWarning('来电号码为空!');
		return false;
	}else{
		
	popenIframe('pp','呼叫历史',400,800,'jiedaidan-callHistory.xhtml?jiedaidan.ldhm='+ldhm);
	}
}
	</script>
  </head>
  
  <body>
  <s:actionmessage theme="custom" />
  <form action="jiedaidan-addJiedaidan.xhtml" name="form0" method="post">
	<table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="table_bottom center">
				<tr>
					<td width="7%" class="color table-center">
						来电号码
					</td>
					<td width="10%">
						<input name="jiedaidan.ldhm" size="14" required="true" validType="lxdh[8,12]"  type="text" id="ldhm"
							class="easyui-validatebox" id="jiedaidan.ldhm" maxlength="11"  />
							&nbsp;&nbsp;<img  title="查找" src='<%= request.getContextPath()%>/images/call_search.gif' style="cursor:pointer" onclick="doSearchInfoByPhone()"/>
					</td>
					<td width="7%" class="color table-center">
						来电分类
					</td>
					<td width="10%">
						<s:select list="jiedaileixingList" listKey="jdlxId" listValue="lxmc" value="jiedaileixing.jdlxId" headerKey="0" headerValue="--请选择--"
			name="jiedaidan.ldfl"  id="jdlxId" panelHeight="auto" required="true" validType="comselect['jdlxId']" cssStyle="width:100px;" cssClass="easyui-validatebox"></s:select>
					</td>
					
					<td width="7%" class="color table-center">
						会员号
					</td>
					<td width="10%" >
						<input name="jiedaidan.hyh" type="text" id="hyh"
							class="easyui-validatebox" size="15" required="true" validType="lxdh[2,12]"
							value='<s:property value="jiedaidan.hyh" />'/>&nbsp;&nbsp;
						<img src='<%= request.getContextPath()%>/images/call_search.gif' title="查找" style="cursor:pointer" onClick="searchUserByYqzh2()"/>
						<span id="search_result"></span>
					</td>
					<td width="7%" class="color table-center">
						用户姓名
					</td>
					<td width="10%">
						<input name="jiedaidan.yhmc" type="text" id="yhmc"
							class="easyui-validatebox" size="15" required="true"
							value='<s:property value="jiedaidan.yhmc" />'/>
					</td>
				</tr>
				<tr>
				<td width="7%" class="color table-center">
						会员级别
					</td>
					<td width="10%">
						<input name="jiedaidan.hyjb" type="text" id="hyjb"
							class="easyui-validatebox" size="15" required="true"
							value='VIP会员'/>
					</td>
					<td width="7%" class="color table-center">
						用户地址
					</td>
					<td width="10%" colspan="1">
						<input name="jiedaidan.yhdz" type="text" id="yhdz"
							class="easyui-validatebox" size="25" required="true"
							value='暂无'/>
					</td>
					<td width="7%" class="color table-center" rowspan="2">
						问题描述
					</td>
					<td width="10%" colspan="2">
						<textarea rows="2" name="jiedaidan.wtms" required="true" validType="length[2,100]" id="jiedaidan.wtms" style="WIDTH: 93%;margin-top:10px;" class="easyui-validatebox"></textarea>	
					</td>
					<td  rowspan="1" colspan="1">
							<a href="javascript:void(0);"  onclick="detail();return false;"  class="easyui-linkbutton" style="float: center;" iconCls="icon-search">呼叫历史</a>
						<a href="javascript:void(0);" id="addBtn" target="buttomFrame" onclick="submitThis();return false;"  class="easyui-linkbutton" style="float: center;" iconCls="icon-ok">保存接待单</a>
					</td>
				</tr>
			</table>
			</form>
  </body>

</html>
