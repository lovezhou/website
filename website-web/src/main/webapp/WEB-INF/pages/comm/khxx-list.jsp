<%@ page contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pagination" uri="/WEB-INF/tld/pagination/pagination.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/pagination/c.tld"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
<!--
function doSearch(){
	document.pagination.action="tree-listKhxx.xhtml";
	document.pagination.submit();
	blockwait();
}

//回车事件
document.onkeydown = function(event){
		
		if(!event) event = window.event;//火狐中是 window.event
     if((event.keyCode || event.which) == 13){
			
			doSearch();
		}
	}

//-->
</script>
<body>
	<s:form name="pagination" namespace="/" theme="simple">
		<input type="hidden" name="ggcs.targetFieldId" value="<s:property value="ggcs.targetFieldId" />" />
			<input type="hidden" name="ggcs.srcFieldId" value="<s:property value="ggcs.srcFieldId" />" />
			<input type="hidden" name="ggcs.multiChoise" value="<s:property value="ggcs.multiChoise" />" />
	<div class="fieldset-tip"></div>
		<div class="fieldset-info center">
		<fieldset class="fieldset-line">
		<legend >查询条件</legend>
		           客户名称： <input class="input" name="ykhxx.khmc" value="<s:property value='ykhxx.khmc'/>" style="width:80px">
			收发类型：<select name="ykhxx.sflx"  id="sflx" panelHeight="auto" style="width:90px" class="easyui-combobox">
			<option value="4">全部</option>
			<option value="0" <s:if test="ykhxx.sflx==0">selected="selected"</s:if>>其他</option>
			<option value="1" <s:if test="ykhxx.sflx==1">selected="selected"</s:if>>收</option>
			<option value="2" <s:if test="ykhxx.sflx==2">selected="selected"</s:if>>发</option>
			<option value="3" <s:if test="ykhxx.sflx==3">selected="selected"</s:if>>收发</option>
			
			</select>
			状态：<select name="ykhxx.khzt"  id="khzt" panelHeight="auto" style="width:90px" class="easyui-combobox">
			<option value="4">全部</option>
			<option value="0" <s:if test="ykhxx.khzt==0">selected="selected"</s:if>>无效</option>
			<option value="1" <s:if test="ykhxx.khzt==1">selected="selected"</s:if>>有效</option>
			<option value="2" <s:if test="ykhxx.khzt==2">selected="selected"</s:if>>停用</option>
			</select>
			<a href="javascript:void(0);" onclick="jessrun.targetSelect('${ggcs.targetFieldId}', '${ggcs.multiChoise}');return false;" class="easyui-linkbutton" style="float: right;" iconCls="icon-ok">确认</a>
			<a href="#" onclick="doSearch();" class="easyui-linkbutton" style="float: right;" iconCls="icon-search">查询</a>
		</fieldset>
	</div>
		          <table width="98%" id="ds"  align="center" class="table03 center rclick">
								    <tr>
								    <th>选择
								    			<s:if test="ggcs.multiChoise eq 'yes'">
										      		<input type="checkbox" id="all_chk" name="all_chk" />
										        </s:if>
								    		</th>
								            <th >客户名称</th>
								            <th >联系电话</th>
								            <th >地址</th>
								            <th >收发类型</th>
								            <th >创建日期</th>
								            <th >状态</th>
								    </tr>
								<s:iterator id="kh" value="ykhxxList" status="cxf">
								    <tr>
								     <td class="nclick"><input class="check" <s:if test="ggcs.multiChoise eq 'yes'">type="checkbox"</s:if><s:else>type="radio"</s:else> id="chk_<s:property value="#cxf.index"/>" 
										      			relVal="<s:iterator id="prop" value="ggcs.fieldIdList" status="dd"><s:property value="#kh[#prop]"/><s:if test="!#dd.last">,</s:if></s:iterator>" 
										      			name="chk_select" /></td>
								    <td>
								    <s:property value="#kh.khmc"/>
								    </td>
								    <td><s:property value="#kh.lxdh"/></td>
								    <td><s:property value="#kh.lxdz"/></td>
								    <td>&nbsp;
								    <s:if test="#kh.sflx==0">其他</s:if>
								    <s:elseif test="#kh.sflx==3">收发</s:elseif>
								    <s:elseif test="#kh.sflx==2">发</s:elseif>
								    <s:else>收</s:else>
								    </td>
								    <td>
								    <s:property value="#kh.cjsj"/>
								    </td>
								    <td>
								    <s:if test="#kh.khzt==1">有效</s:if>
								    <s:elseif test="#kh.khzt==0">无效</s:elseif>
								    <s:elseif test="#kh.khzt==2">停用</s:elseif>
								    <s:else>异常状态</s:else>
								    </td>								    
								    </tr>
								</s:iterator>
						</table>
						<pagination:paginate items="ykhxxList"/> 
</s:form>
</body>
	<script type="text/javascript">
	bandDsTable("ds");//一定要在加载完成执行,你也可以放在onload里
	</script>

