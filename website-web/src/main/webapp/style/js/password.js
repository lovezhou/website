$(function(){
	$("#modifyPWForm .txt").bind('keydown',function(event) {
		if(event.keyCode==13){
		function doSubmit(){$("#modifyPWForm").submit()}
		setTimeout(doSubmit,0);
		}
	});

	$("#login_btn").bind("click",function(){
		function doSubmit(){$("#modifyPWForm").submit()}
		setTimeout(doSubmit,0);
	});
	jessrun.formSubmit("modifyPWBtn","modifyPWForm","loadFormSubmit","密码保存中");

});




