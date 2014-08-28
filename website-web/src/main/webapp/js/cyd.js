function trim(str){ //删除左右两端的空格 
		return str.replace(/(^\s*)|(\s*$)/g, ""); 
} 
/* 添加客户信息 */
function  addKhxx(x){
	var _hrmc=$("#"+x+"hrmc").val();
	var _hrdh=$("#"+x+"hrdh").val();
	var _hrdz=$("#"+x+"hrdz").val();
	var _sflx=2; // 发
	if(x=="f"){
		if(trim(_hrmc)==""){
			cxf.showMessage("发货人不能为空！");
			return false ;
		}
		if(trim(_hrdh)==""){
			cxf.showMessage("发货人电话不能为空！");
			return false ;
		}
		/*if(trim(_hrdz)==""){
			cxf.showMessage("发货人地址不能为空！");
			return false ;
		}*/
	}else{
		if(trim(_hrmc)==""){
			cxf.showMessage("收货人不能为空！");
			return false ;
		}
		if(trim(_hrdh)==""){
			cxf.showMessage("收货人电话不能为空！");
			return false ;
		}
		/*if(trim(_hrdz)==""){
			cxf.showMessage("收货人地址不能为空！");
			return false ;
		}*/
		_sflx=1;
	}
	 cxf.ajaxAsync("ajax-addKhxx.xhtml",{"ykhxx.khmc":_hrmc,"ykhxx.lxdh":_hrdh,"ykhxx.lxdz":_hrdz,"ykhxx.sflx":_sflx},function(data){
		 	if(data!=null&&data!=""){
		 		var  meg   =data.split(",");
		 		if(meg[0]){
		 			$("#"+x+"hrid").val(meg[1]);
		 			//cxf.showMessage("添加成功！");
		 		}
		 	//	return false;
     		}
	});
}
//验证承运单是否唯一
function checkCydhIsUnique(cydh){
	if(trim(cydh)==""){
		cxf.showMessage("承运单号不能为空");
		$("#txt_cydh").focus();
		return false ;
	}
	cxf.ajaxAsync("ajax-checkCydhIsUnique.xhtml",{"cydh":cydh},function(data){
	 		if(data=="true"){
	 			cxf.showMessage("承运单号["+cydh+"]已经存在，请重新填写");
	 			$("#txt_cydh").focus();
	 			return false;
	 		}else{
		 		cxf.IsSubmit("form0","addBtn");
	 		}
	});
}
function checkCydhIsUnique1(cydh){
	if(trim(cydh)==""){
		cxf.showMessage("承运单号不能为空");
		$("#txt_cydh").focus();
		return false ;
	}
	cxf.ajaxAsync("ajax-checkCydhIsUnique.xhtml",{"cydh":cydh},function(data){
	 		if(data=="true"){
	 			cxf.showMessage("承运单号["+cydh+"]已经存在，请重新填写");
	 			$("#txt_cydh").focus();
	 			return false;
	 		}else{
		 		cxf.IsSubmit("form0","addBtn1");
	 		}
	});
}

/*计算保费*/
jessrun.countBf = function(i){ 
	 if($("input[id$='_BE']").size()>0){
		 $("input[id$='_BE']").change(function(){
			 var be = $(this);
			 cxf.ajaxGet("ajax-countBfByBe.xhtml",{"be":be.val()},function(data){
				    var bf =  be.parent().parent().children().children("input[id$='_BF']");
				    if(bf.size()>0){
				    	bf.val(Number(data).toFixed(2));
				    	resum(i);
				    	return false;
		 			}
			 });
		});
	 }
}
jessrun.countBf1 = function(){ 
			 var be = $("#fyx_BE");
			 cxf.ajaxGet("ajax-countBfByBe.xhtml",{"be":be.val()},function(data){
				    var bf =  $("#fyx_BF");
				    if(bf.size()>0){
				    	bf.val(Number(data).toFixed(2));
				    	return false;
		 			}
			 });
}

