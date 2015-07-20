<%@ page contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pagination" uri="/WEB-INF/tld/pagination/pagination.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/pagination/c.tld"%>
<script type="text/javascript">
<!--
function doSearch(){
	document.pagination.action="tree-listGsFykm.xhtml";
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
	<div class="fieldset-tip"></div>
		<div class="fieldset-info center">
		<fieldset class="fieldset-line">
		<legend >查询条件</legend>
			<input type="hidden" name="ggcs.targetFieldId" value="<s:property value="ggcs.targetFieldId" />" />
			<input type="hidden" name="ggcs.srcFieldId" value="<s:property value="ggcs.srcFieldId" />" />
			<input type="hidden" name="ggcs.multiChoise" value="<s:property value="ggcs.multiChoise" />" />
			<a href="javascript:void(0);" onclick="jessrun.targetSelect('${ggcs.targetFieldId}', '${ggcs.multiChoise}');return false;" class="easyui-linkbutton" style="float: left;" iconCls="icon-ok">确认</a>
		</fieldset>
	</div>
    <table width="98%" id="ds"  align="center" class="table03 center rclick">
	    <tr>
	    		<th>选择
	    			<s:if test="ggcs.multiChoise eq 'yes'">
			      		<input type="checkbox" id="all_chk" name="all_chk" />
			        </s:if>
	    		</th>
	            <th >费用名称</th>
	           
	    </tr>
		<s:iterator id="yh" value="gsfyList" status="cxf">
		    <tr>
		      <td class="nclick"><input class="check" <s:if test="ggcs.multiChoise eq 'yes'">type="checkbox"</s:if><s:else>type="radio"</s:else> id="chk_<s:property value="#cxf.index"/>" 
		      			relVal="<s:iterator id="prop" value="ggcs.fieldIdList" status="dd"><s:property value="#yh[#prop]"/><s:if test="!#dd.last">,</s:if></s:iterator>" 
		      			name="chk_select" /></td>
		      
		      <td ><s:property value="kmmc"/></td>
		      
		    </tr>
		</s:iterator>  
	</table>
</s:form>
</body>
	<script type="text/javascript">
	bandDsTable("ds");//一定要在加载完成执行,你也可以放在onload里
	
	$(function(){
		$("#all_chk").click(function(){
			$("input[id^='chk_']").attr("checked", $(this).attr("checked"));
		});
	});
	</script>

