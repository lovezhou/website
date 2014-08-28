<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<link rel="stylesheet" type="text/css" href="<%=basePath%>js/zTree-v3.5.14/css/zTreeStyle/zTreeStyle.css">
<script type="text/javascript" src="<%=basePath%>js/zTree-v3.5.14/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript">

</script>

<body>
<input type="hidden" name="ggcs.targetFieldId" value="<s:property value="ggcs.targetFieldId" />" />
	<div>
		<ul id="infoTree" class="ztree"></ul>
	</div>
</body>

<script type="text/javascript">
    bandDsTable("ds");//一定要在加载完成执行,你也可以放在onload里
 	var setting = {
		view: {
			addDiyDom: addDiyDom,
			dblClickExpand: dblClickExpand,
			fontCss: getFont
		},
		data: {
			simpleData: {
				  enable:true
			}
		},
		callback: {
				onClick: getCostType
			}
	};
    	//返回
 	function getCostType(event, treeId, treeNode) {
			aVal=treeNode.id+","+treeNode.name;
			jessrun.treeSelect('${ggcs.targetFieldId}',aVal);
		}
	function getFont(treeId, node) {
		return node.font ? node.font : {};
	}
 		
	var zNodes =[
	        <s:iterator id="items" value="yhwmcList" status="status">
       		{ id:<s:property value="hwlxId"/>, pId:<s:property value="sjlxId"/>== null ? 0 : <s:property value="sjlxId"/>, name:"<s:property value='hwlxmc'/>",
       				open:true,iconOpen:"js/zTree-v3.5.14/css/zTreeStyle/img/diy/1_open.png", 
       				iconClose:"js/zTree-v3.5.14/css/zTreeStyle/img/diy/1_close.png", font:{'font-weight':'bold','color':'#233880'}}
       		<s:if test="#status.last"></s:if>
       		<s:else>,</s:else>
           </s:iterator>
     ];

	function dblClickExpand(treeId, treeNode) {
		return treeNode.level > 0;
	}
	/* 增加按钮 */
	function  addDiyDom (treeId, treeNode) {
		    var editStr ="<span id='diyBtn_space_" +treeNode.id+ "' > </span>";
			var aObj = $("#" + treeNode.tId + "_a");
			if ($("#diyBtn_"+treeNode.id).length>0) return;
			aObj.append(editStr);
	}

	$(document).ready(function(){
		$.fn.zTree.init($("#infoTree"), setting, zNodes);

	});
	</script>