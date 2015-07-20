//载入验证所需方法
jessrun.loadJS(jessrun.config.domain+"/style/js/init-form.js",function(){
},function(){
	alert(jessrun.url.jsError)
});

//配置提交表单Id
jessrun.formId={
	companyEditId:"companyEditForm"
};
$(function(){
	//公司资料修改
	jessrun.formSubmit("linkManEditBtn","linkManEditForm","loadFormSubmit","联系人信息保存中");
});

/*
$(function(){
	$("#"+jessrun.formId.companyEditId).validate({
		errorPlacement:function(error,element){
			error.appendTo(element.next());
		},
		//errorLabelContainer: "#errorSearch",		//显示错误信息的容器ID
		//wrapper: "span",
		onsubmit: false,
		success:"form-success",
		rules:{
			companyName:{
				required:true,
				isCompanyName:true,
				minlength:4,
				maxlength:30
			},
			companyPermit:{
				required:true,
				isCompanyPermit:true,
				minlength:4,
				maxlength:30
			},
			companyCorporate: {
				required:true,
				isChinaName:true
			},
			companyNameSim: {
				required:true,
				isCompanyName:true
			},
			companyAddress:{
				required:true,
				isAddress:true
			}
		},
		messages:{
			companyName:{
				required:"公司名称不能为空",
				minlength:"公司名称不能小于4个字符",
				maxlength:"公司名称不能大于30个字符"
			},
			companyPermit:{
				required:"营业执照不能为空",
				minlength:"营业执照不能小于4个字符",
				maxlength:"营业执照不能大于30个字符"
			},
			companyCorporate: {
				required:"法人姓名不能为空"
			},
			companyNameSim: {
				required:"公司简称不能为空"
			},
			companyAddress:{
				required:"公司地址不能为空"
			}
		}
	});
});
*/