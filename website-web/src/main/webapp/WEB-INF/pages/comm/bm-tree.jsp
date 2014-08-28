<%@ page contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<link rel="stylesheet" type="text/css" href="<%=basePath %>js/zTree-v3.5.14/css/zTreeStyle/zTreeStyle.css" >
<script type="text/javascript" src="<%=basePath %>js/zTree-v3.5.14/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript">

String.prototype.trim = function()
{
    return this.replace(/(^\s*)|(\s*$)/g, "");
}

</script>
	<body>
	<input type="hidden" name="ggcs.targetFieldId" value="<s:property value="ggcs.targetFieldId" />" />
			<input type="hidden" name="ggcs.multiChoise" value="<s:property value="ggcs.multiChoise" />" />
		<div>
			<ul id="infoTree" class="ztree"></ul>
		</div>
	</body>
<script type="text/javascript">
     
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
 				beforeClick: zTreeBeforeClick,
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
 		        <s:iterator id="items" value="bmList" status="status">
	        		{ id:<s:property value="bmId"/>, pId:<s:property value="sjbmId"/>== null ? 0 : <s:property value="sjbmId"/>, name:"<s:property value='bmmc'/>",
	        				open:true,
	        				<s:if test="bmlx==0">
					        	  iconOpen:"js/zTree-v3.5.14/css/zTreeStyle/img/diy/1_open.png", 
				    			  iconClose:"js/zTree-v3.5.14/css/zTreeStyle/img/diy/1_close.png",
	        				</s:if >
		        			<s:elseif test="bmlx==1">
		        			      icon:"js/zTree-v3.5.14/css/zTreeStyle/img/diy/dept.png",
		        			</s:elseif>
		        			<s:elseif test="bmlx==2">
		        			 	  icon:"js/zTree-v3.5.14/css/zTreeStyle/img/diy/site.png",
		        			</s:elseif>
		        			bmlx:<s:property value="bmlx"/>, font:{'font-weight':'bold','color':'#233880'}}
	        		<s:if test="#status.last"></s:if>
	        		<s:else>,</s:else>
	            </s:iterator>
        ];

 		function dblClickExpand(treeId, treeNode) {
 			return treeNode.level > 1;
 		}
 		
 		function  addDiyDom (treeId, treeNode) {
 			    var  editStr = "<span id='diyBtn_space_" +treeNode.id+ "' > </span>";  
 				var aObj = $("#" + treeNode.tId + "_a");
 				if ($("#diyBtn_"+treeNode.id).length>0) return;
 				aObj.append(editStr);
 		}
 		
 		function zTreeBeforeClick(treeId, treeNode, clickFlag) {
 		    return (treeNode.level !== 0);
 		};

 		$(document).ready(function(){
 			$.fn.zTree.init($("#infoTree"), setting, zNodes);
 		});
	</script>

