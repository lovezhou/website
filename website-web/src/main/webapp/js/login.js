$(function(){
	jessrun.loadJS("../js/validate/jquery.validate-min.js",function(){});
	jessrun.loadJS("../js/validate/jquery.metadata-min.js",function(){});
	jessrun.loadJS("../js/validate/jquery.validate.message_cn.js",function(){});
});


jessrun.formSubmit=function(btnId,formId,loadClass,loadHtml){
	$("#"+btnId).click(function(){
		if ($("#"+formId).valid()){
			$("#"+btnId).hide();
			$("<span class='"+loadClass+"'><span><em>"+loadHtml+"</em></span></span>").insertAfter("#"+btnId);
			function doSubmit(){
				$("#"+formId).submit();
				}
			setTimeout(doSubmit,0);
		}
	});
};

$(function(){
	$("#userName").focus();
	$("#login_Form .txt").bind('keydown',function(event) {
		if(event.keyCode==13){
		function doSubmit(){$("#login_Form").submit();}
		setTimeout(doSubmit,0);
		}
	});

	$("#login_btn").bind("click",function(){
		function doSubmit(){$("#login_Form").submit();}
		setTimeout(doSubmit,0);
	});
	jessrun.formSubmit("login_btn","login_Form","loadFormSubmit","用户登录中");

});


/*$(function(){
	jessrun.formSubmit("userLoginBtn","userLoginForm","用户登录中");
});
jessrun.formSubmit = function(btnId,formId,loadHtml){
	$("#"+btnId).click(function(){
		if ($("#"+formId).valid()) {
				$("#"+btnId).hide();
				$("<span class='loadFormSubmit'><span><em>"+loadHtml+"</em></span></span>").insertAfter("#"+btnId);
				function doSubmit(){
					$("#"+formId).submit();//程序调用请写这句
					}
				setTimeout(doSubmit,0);
		}
	});
};






//加载验证码
jessrun.loadImgCode =function(id){
		$.ajax( {
		url :"/index/index/imgajax.htm?now="+new Date().getTime(),
		cache:false,
		async: false,
		success: function(data){
			rft.showImgDate(id,data);
			$("#userImgCodeLoadChange").show();
		},
		error: function(){
			rft.showImgError(id)
		}
	});
};

jessrun.showImgDate = function (id,html){
	$(id).empty();
	rft.showImgLoading(id);
	setTimeout(function (){$(id).html(html)},500);
};
jessrun.showImgLoading = function (id){
	 var l=$("<div class='loadImgCode'><span>加载验证码</span></div>");
	 $(id).html(l);
};
rft.showImgError = function (id){
	$(id).empty();
	 var l=$("<div class='loadImgCode'><span class='loadInfoError'>加载验证码识别</span></div>");
	 $(id).html(l);
};

//更换验证码
jessrun.loadImgCodeChange =function(){
		$("#userImgCodeLoadChange").live("click",function(){
			setTimeout(function(){
				rft.loadImgCode();
				$("#imgCode").attr("src","/img.htm?now="+new Date().getTime());
			},1);

		})
};

*/