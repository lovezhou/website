<%@ page contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pagination" uri="/WEB-INF/tld/pagination/pagination.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/pagination/c.tld"%>
<script type="text/javascript">
<!--
function doSearch(){
	document.pagination.action="tree-listYhzgs.xhtml";
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

$(document).ready(function(){
	$("#ds").tableUI();
});

//-->
</script>
<body>
	<s:form name="pagination" namespace="/" theme="simple">
	<div class="fieldset-tip"></div>
		<div class="fieldset-info center">
		<fieldset class="fieldset-line">
		<legend >查询条件</legend>
			公司名称: <input class="input" name="yhzgs.gsmc" id="txt_gsmc" value='<s:property value='yhzgs.gsmc'/>' style="width:80px" />
			法人: <input class="input" name="yhzgs.gsfr" id="txt_gsfr" value='<s:property value='yhzgs.gsfr'/>' style="width:80px" />
			联系电话: <input class="input" name="yhzgs.gsdh" id="txt_gsdh" value='<s:property value='yhzgs.gsdh'/>' style="width:80px" />
			<input type="hidden" name="ggcs.targetFieldId" value="<s:property value="ggcs.targetFieldId" />" />
			<input type="hidden" name="ggcs.srcFieldId" value="<s:property value="ggcs.srcFieldId" />" />
			<input type="hidden" name="ggcs.multiChoise" value="<s:property value="ggcs.multiChoise" />" />
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
								            <th >公司名称</th>
								            <th >拼音简写</th>
								            <th >助词码</th>
								            <th >公司法人</th>
								            <th >公司负责人</th>
								            <th >负责人电话</th>
								    </tr>
									 <s:iterator id="yh" value="yhzgsList" status="cxf">
										    <tr>
										      <td class="nclick"><input class="check" <s:if test="ggcs.multiChoise eq 'yes'">type="checkbox"</s:if><s:else>type="radio"</s:else> id="chk_<s:property value="#cxf.index"/>" 
										      			relVal="<s:iterator id="prop" value="ggcs.fieldIdList" status="dd"><s:property value="#yh[#prop]"/><s:if test="!#dd.last">,</s:if></s:iterator>" 
										      			name="chk_select" /></td>
										      <td ><s:property value="gsmc"/></td>
										      <td ><s:property value="pyjc"/></td>
										      <td ><s:property value="zjm"/></td>
										      <td ><s:property value="gsfr"/></td>
										      <td ><s:property value="gsfzr"/></td>
										      <td ><s:property value="fzrdh"/></td>
										    </tr>
									</s:iterator>
						</table>
						<pagination:paginate items="yhzgsList"/> 
</s:form>
</body>
	<script type="text/javascript">
	bandDsTable("ds");//一定要在加载完成执行,你也可以放在onload里
	
	</script>

