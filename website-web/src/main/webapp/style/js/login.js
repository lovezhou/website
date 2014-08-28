$(function(){
	$("#userName").focus();
	$("#login_Form .txt").bind('keydown',function(event) {
		if(event.keyCode==13){
		function doSubmit(){$("#login_Form").submit()}
		setTimeout(doSubmit,0);
		}
	});

	$("#login_btn").bind("click",function(){
		function doSubmit(){$("#login_Form").submit()}
		setTimeout(doSubmit,0);
	});
	jessrun.formSubmit("login_btn","login_Form","loadFormSubmit","用户登录中");

});

jessrun.formSubmit=function(btnId,formId,loadClass,loadHtml){
	$("#"+btnId).click(function(){
		if ($("#"+formId).valid()){
			$("#"+btnId).hide()
			$("<span class='"+loadClass+"'><span><em>"+loadHtml+"</em></span></span>").insertAfter("#"+btnId);
			function doSubmit(){
				$("#"+formId).submit();
				}
			setTimeout(doSubmit,0);
		}
	});
};

