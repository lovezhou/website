function trim(str){ //删除左右两端的空格 
		return str.replace(/(^\s*)|(\s*$)/g, ""); 
} 
/* 添加合作公司信息 */
function  addHzgs(){
	var _wbgsmc=$("#wbgsmc").val();
	var _gsfzr=$("#gsfzr").val();  /*公司负责人*/
	var _fzrdh=$("#fzrdh").val();  /* 负责人电话*/
	if(trim(_wbgsmc)==""){
		cxf.showMessage("合作公司不能为空！");
		return false ;
	}
	if(trim(_fzrdh)==""){
		cxf.showMessage("联系人不能为空！");
		return false ;
	}
	/*if(trim(_hrdz)==""){
		cxf.showMessage("发货人地址不能为空！");
		return false ;
	}*/
	cxf.ajaxAsync("ajax-addHzgs.xhtml",{"yhzgs.gsmc":_wbgsmc,"yhzgs.gsfzr":_gsfzr,"yhzgs.fzrdh":_fzrdh},function(data){
 	if(data!=null&&data!=""){
 		var  meg   =data.split(",");
 		if(meg[0]){
 			$("#wbgsId").val(meg[1]);
 			//cxf.showMessage("添加成功！");
 		}
 	//	return false;
 		}
	});
}