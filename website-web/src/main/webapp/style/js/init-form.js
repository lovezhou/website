/*
//公司名称
jQuery.validator.addMethod("isCompanyName", function(value, element) {
		return this.optional(element) || jessrun.isChineseEnNum(value);
}, "请输入正确的公司名称");

//营业执照
jQuery.validator.addMethod("isCompanyPermit", function(value, element) {
		return this.optional(element) || jessrun.isChineseEnNum(value);
}, "请输入正确的营业执照");

//法人姓名
jQuery.validator.addMethod("isChinaName", function(value, element) {
		return this.optional(element) || jessrun.isChineWord(value);
}, "请输入正确的法人姓名");

//组织机构代码
jQuery.validator.addMethod("isBusinessCode", function(value, element) {
		return this.optional(element) || jessrun.isChineseEnNum(value);
}, "请输入正确的组织机构代码");
//税务登记号
jQuery.validator.addMethod("isTaxNo", function(value, element) {
		return this.optional(element) || jessrun.isChineseEnNum(value);
}, "请输入正确的税务登记号");
//法人姓名
jQuery.validator.addMethod("isChinaName", function(value, element) {
		return this.optional(element) || jessrun.isChineWord(value);
}, "请输入正确的中文姓名");
//身份证号
jQuery.validator.addMethod("isCardId", function(value, element) {
		return this.optional(element) || jessrun.isCardId(value);
}, "请输入正确真实的身份证号");

//联系电话
jQuery.validator.addMethod("isContactPhone", function(value, element) {
		return this.optional(element) || jessrun.chkContactPhone(value);
}, "请输入正确的联系电话号码，例如：0471-1234567或13312345678");

//地址
jQuery.validator.addMethod("isAddress", function(value, element) {
		return this.optional(element) || jessrun.isChineseEnNum(value);
}, "请输入正确的地址");

//邮政编码
jQuery.validator.addMethod("isPostCode", function(value, element) {
	return this.optional(element) || jessrun.isPostCode(value);
}, "请输入正确的6位邮政编码");

//省份否选择
jQuery.validator.addMethod("isSelectPro", function(value, element) {
	return this.optional(element) || jessrun.chkSelected(value);
}, "请选择省份");
//城市否选择
jQuery.validator.addMethod("isSelectCity", function(value, element) {
	return this.optional(element) || jessrun.chkSelected(value);
}, "请选择城市");
// 添加验证方法 (金额数字)
jQuery.validator.addMethod("isUserAmount", function(value, element) {
	return this.optional(element) || jessrun.isAmount(value);
}, "金额必须为非0的整数或小数，小数点后保留2位");

//银行否选择
jQuery.validator.addMethod("isSelectBank", function(value, element) {
	return this.optional(element) || jessrun.chkSelected(value);
}, "请选择银行");
// 添加验证方法 (银行卡开户行)
jQuery.validator.addMethod("isCardCompany", function(value, element) {
	return this.optional(element) || jessrun.isStrs(value);
}, "请输入正确的银行开户行");
jQuery.validator.addMethod("isCardNo", function(value, element) {
	return this.optional(element) || jessrun.isIntZero(value);
}, "请输入正确的银行卡号");

// 添加验证方法 (密码)
jQuery.validator.addMethod("isUserPassword", function(value, element) {
	return this.optional(element) || jessrun.chkUserPwd(value);
}, "密码为6-16位数字、字母或符号的组合");

// 添加验证方法 (邮箱和手机号)
jQuery.validator.addMethod("isUserName", function(value, element) {
	return this.optional(element) || jessrun.chkUserName(value);
}, "请输入正确的手机号或邮箱地址");

// 添加验证方法 (金额数字)
jQuery.validator.addMethod("isUserAmount", function(value, element) {
	return this.optional(element) || jessrun.isAmount(value);
}, "金额必须为非0的整数或小数，小数点后保留2位");
// 添加验证方法 (数量总数)
jQuery.validator.addMethod("isTotal", function(value, element) {
	return this.optional(element) || jessrun.isIntZero(value);
}, "总数必须为非0的整数");
// 添加验证方法 (手机号码)
jQuery.validator.addMethod("isUserTel", function(value, element) {
	return this.optional(element) || jessrun.isTel(value);
}, "请输入正确的手机号码");
// 添加验证方法 (备注)
jQuery.validator.addMethod("isRmark", function(value, element) {
	return this.optional(element) || jessrun.isStrs(value);
}, "请勿输入特殊字符");

$.validator.addMethod("selectRadioDraw", function(value, element) {
    return value == "0";
}, "必须选择一项");

$.validator.addMethod("selectRadioDraw1", function(value, element) {
    return value == "1";
}, "必须选择一项");
$.validator.addMethod("selectRadioDraw2", function(value, element) {
    return value == "2";
}, "必须选择一项");
$.validator.addMethod("selectRadioDraw3", function(value, element) {
    return value == "3";
}, "必须选择一项");

// 添加验证方法 (手机号码)
jQuery.validator.addMethod("isUserTel", function(value, element) {
	return this.optional(element) || jessrun.isTel(value);
}, "请输入正确的手机号码");
// 添加验证方法 (备注)
jQuery.validator.addMethod("isRmark", function(value, element) {
	return this.optional(element) || jessrun.isStrs(value);
}, "请勿输入特殊字符");

// 添加验证方法 (密码)
jQuery.validator.addMethod("isUserPassword", function(value, element) {
	return this.optional(element) || jessrun.chkUserPwd(value);
}, "密码为6-16位数字、字母或符号的组合");
*/