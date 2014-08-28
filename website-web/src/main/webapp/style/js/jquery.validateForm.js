
//页面提示正则
jessrun.regEx ={
	Strs:/^[\u0391-\uFFE5\w]+$/, //中文字、英文字母、数字和下划线
	NumEn:/^[_a-z0-9]+$/,//检查数字英文
	IntZero:/^[0-9]*[1-9][0-9]*$/,//检查非0正整数
	Int6:/^\d{6}$/,//检查6位数字
	Amount:/^(0\.\d{1,2}|[1-9]\d{0,8}(\.\d{1,2})?)$/,//检查金额数字 0.00 和 小数的后两位
	Tel:/^1(3|4|5|8)\d{9}$/,//检查手机号码
	Mobile:/^((\(\d{3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}$/,//检查固定电话 //0471-1234567
	PostCode:/^[1-9]\d{5}(?!\d)$/,//检查中国邮政编码
	Email:/^[_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[a-z0-9-]+)*$/,//检查电子邮件
	ChineWord:/^[\u4e00-\u9fa5]+$/,//检查汉字
	SimpCardId:/^(\d{6})(18|19|20)?(\d{2})([01]\d)([0123]\d)(\d{3})(\d|X|x)?$/,//简单检查身份证方法
	ChineseEnNum:/^[\u4e00-\u9fa5a-zA-Z0-9]+$/,//检查中文和英文 数字
	Time:/^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$/ //检查时间格式YYYY-MM-DD
};

//页面提示
jessrun.formTips ={
	"serviceTip":["请阅读同意并勾选协议"],
	"btnTips":["用户登录中","用户激活中","用户注册中","用户资料完善中","数据保存中"],
	"userName":["请输入您的邮箱地址或手机号码","邮箱地址或手机号码不正确"],
	"userPwd":["请输入登录密码","密码为6-16位数字、字母或符号的组合","请再次输入登录密码","两次输入密码不一致，请重新输入"],
	"userPayPwd":["请输入支付密码","密码为6-16位数字、字母或符号的组合","请再次输入支付密码","两次输入支付密码不一致，请重新输入"],
	"imgCode":["请输入验证码","请输入6位验证码"],
	"msgCode":["请输入短信验证码","请输入6位短信验证码"],
	"tel":["请输入手机号码","请输入正确的手机号码"],
	"mobile":["请输入固定电话","请输入正确的固定电话号码，例如0471-1234567"],
	"email":["请输入邮箱地址","请输入正确的邮箱地址","邮箱地址长度不能超过100个字符"],
	"postCode":["请输入邮政编码","请输入正确的6位邮政编码"],
	"cardId":["请输入身份证号码","请输入正确的身份证号码","请输入证件号码"],
	"chineseName":["请输入您的真实姓名","请输入正确的姓名","姓名长度不能超过15个字"],
	"companyName":["请输入公司名称","请输入正确的公司名称,请勿输入特殊字符","公司名称长度不能超过25个字"],
	"time":["请选择开始时间","请选择结束时间","结束时间不能晚于开始时间，请重新选择"],
	"amount":["请输入金额","金额数不正确，金额必须为非0的整数或小数，小数点后不超过2位","请输入最小金额","请输入最大金额","最小金额大于最大金额，请重新输入"],
	"total":["请输入总数","总数数不正确，总数必须为非0的整数"],
	"select":["请从列表选择一项"],
	"radio":["请选择一项","请选择一项"],
	"remark":["请输入备注信息","备注信息输入错误，请勿输入特殊字符","备注信息不能超过","个字符，汉字是2个字符"],
	"bankNo":["请输入银行卡号","请输入正确格式的银行卡号"],
	"bankName":["请输入银行卡开户行名称","请输入正确格式的银行卡开户行名称","银行卡开户行名称不能超过25个字符，汉字是2个字符"],
	"merName":["请输入商户名称","请输入正确的商户名称,请勿输入特殊字符","商户名称长度不能超过50个字符"],
	"merId":["请输入工商登录号","请输入正确的工商登录号,请勿输入特殊字符","工商登录号长度不能超过50个字符"],
	"taxId":["请输入税务登记号","请输入正确的税务登记号,请勿输入特殊字符","税务登记号长度不能超过50个字符"],
	"businessId":["请输入组织机构编码","请输入正确的组织机构编码,请勿输入特殊字符","组织机构编码长度不能超过50个字符"]
};

//身份证
jessrun.personCard =function(idcard){
	var Errors=new Array(
	"OK",
	"身份证号码位数不对!",
	"身份证号码出生日期超出范围或含有非法字符!",
	"身份证号码校验错误!",
	"身份证地区非法!"
	);
	var area={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"}
	var idcard,Y,JYM;
	var S,M;
	var idcard_array = new Array();
	idcard_array = idcard.split("");
	if(area[parseInt(idcard.substr(0,2))]==null){
		return Errors[4];
	}
	switch(idcard.length){
	case 15:
		if ( (parseInt(idcard.substr(6,2))+1900) % 4 == 0 || ((parseInt(idcard.substr(6,2))+1900) % 100 == 0 && (parseInt(idcard.substr(6,2))+1900) % 4 == 0 )){
		ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/;//测试出生日期的合法性
		} else {
		ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/;//测试出生日期的合法性
		}
		if(ereg.test(idcard))
			return Errors[0];
		else
			return Errors[2];
		break;
	case 18:
		if ( parseInt(idcard.substr(6,4)) % 4 == 0 || (parseInt(idcard.substr(6,4)) % 100 == 0 && parseInt(idcard.substr(6,4))%4 == 0 )){
		ereg=/^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/;//闰年出生日期的合法性正则表达式
	} else {
		ereg=/^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/;//平年出生日期的合法性正则表达式
	}
	if(ereg.test(idcard)){
		S = (parseInt(idcard_array[0]) + parseInt(idcard_array[10])) * 7
		+ (parseInt(idcard_array[1]) + parseInt(idcard_array[11])) * 9
		+ (parseInt(idcard_array[2]) + parseInt(idcard_array[12])) * 10
		+ (parseInt(idcard_array[3]) + parseInt(idcard_array[13])) * 5
		+ (parseInt(idcard_array[4]) + parseInt(idcard_array[14])) * 8
		+ (parseInt(idcard_array[5]) + parseInt(idcard_array[15])) * 4
		+ (parseInt(idcard_array[6]) + parseInt(idcard_array[16])) * 2
		+ parseInt(idcard_array[7]) * 1
		+ parseInt(idcard_array[8]) * 6
		+ parseInt(idcard_array[9]) * 3 ;
		Y = S % 11;
		M = "F";
		JYM = "10X98765432";
		M = JYM.substr(Y,1);
		if(M == idcard_array[17])
			return Errors[0];
		else
			return Errors[3];
	}else{
		return Errors[2];
	}
		break;
	default:
		return Errors[1];
		break;
	}
}
//时间分割
jessrun.toDate=function (str){
	var sd=str.split("-");
	return new Date(sd[0],sd[1],sd[2]);
}

//字符串字节 $("#id").val().len() 长度获取
String.prototype.len = function(){
   return this.replace(/[^\x00-\xff]/g, "xx").length;
}
//检查字符
jessrun.isStrs=function(s){
	if(jessrun.regEx.Strs.test(s)){
		return true;
	}else{
		return false;
	}
}
//检查数字英文
jessrun.isNumEn =function(s){
	if(jessrun.regEx.NumEn.test(s)){
		return true;
	}else{
		return false;
	}
}
//检查非0正整数
jessrun.isIntZero=function(s){
	if(jessrun.regEx.IntZero.test(s)){
		return true;
	}else{
		return false;
	}
}
//检查纯数字
jessrun.isInt=function(s){
	if(jessrun.regEx.Int.test(s)){
		return true;
	}else{
		return false;
	}
}
//检查6位数字
jessrun.isInt6=function(s){
	if(jessrun.regEx.Int6.test(s)){
		return true;
	}else{
		return false;
	}
}

//检查金额数字 0.00 和 小数的后两位
jessrun.isAmount=function (s){
	if(jessrun.regEx.Amount.test(s)&&s!=0.00){
		return true;
	}else{
		return false;
	}
}

//检查手机号码
jessrun.isTel=function (s){
	if(jessrun.regEx.Tel.test(s)){
		return true;
	}else{
		return false;
	}
}

//检查固定电话 //0471-1234567
jessrun.isMobile=function(s){
   if(jessrun.regEx.Mobile.test(s)){
	   return true;
   }else{
	  return false;
	}
}
//检查中国邮政编码
jessrun.isPostCode=function (s){
   if(jessrun.regEx.PostCode.test(s)){
	   return true;
   }else{
	  return false;
	}
}
//检查电子邮件
jessrun.isEmail=function (s){
	if(jessrun.regEx.Email.test(s)){
		return true;
	}else{
		return false;
	}
}
//检查密码 6-16位英文字母、数字或者符号至少2种的组合的登录密码
jessrun.isPwd=function(s){
	var reg=/^[\a-\z\A-\Z0-9\`\-\=\,\.\/\;\'\[\]\\~\!\@\#\$\%\^\&\*\(\)\_\+\{\}\|\:\"\<\>\?\\\@\.]+$/;
	var onlyabc=/^[A-Za-z]+$/;
	var onlynum=/^[0-9]+$/;
	var onlyspc =/^[\`\-\=\,\.\/\;\'\[\]\~\!\@\#\$\%\^\&\*\(\)\_\+\{\}\|\:"\<\>\?\@]+$/;
	if(onlynum.test(s)){
		return false;
	}
	if(onlyabc.test(s)){
		return false;
	}
	if(onlyspc.test(s)){
		return false;
	}
	if(s.length<6||s.length>16){
		return false;
	}else if(reg.test(s)){
		return true;
	}else{
		return false;
	}
}
//检查汉字
jessrun.isChineWord=function(s){
	if(jessrun.regEx.ChineWord.test(s)){
		return true;
	}else{
		return false;
	}
}
//检查身份证
jessrun.isCardId=function(s){
	var s=jessrun.personCard(s);
	if(s=="OK"||s=="ok"){
		return true;
	}else {
		return false;
	}
}
//简单检查身份证方法
jessrun.isSimpCardId=function(s){
	if(jessrun.regEx.SimpCardIdtest(s)){
		return true;
	}else{
		return false;
	}
}
//检查中文和英文
jessrun.isChineseEnNum=function(s){
	if(jessrun.regEx.ChineseEnNum.test(s)){
		return true;
	}else{
		return false;
	}
}

//检查时间格式YYYY-MM-DD
jessrun.isDateTime=function(s){
	if(jessrun.regEx.Time.test(s)){
		return true;
	}else{
		return false;
	}
}
/*
	公用检查方法，以后直接引用
*/

//检查登录名（邮件或手机号码）
jessrun.chkUserName=function (v){
	if(!jessrun.isEmail(v)&&!jessrun.isTel(v)){
		return false
	}else{
		return true;
	}
}
//检查登录密码
jessrun.chkUserPwd = function(v){
	if(!jessrun.isPwd(v)){
		return false;
	}else{
		return true;
	}
}
//检查支付密码
jessrun.chkPayPwd = function(v){
	if(!jessrun.isPwd(v)){
		return false
	}else{
		return true;
	}
}
//检查图片验证码
jessrun.chkImgCode =function (v){
	if(!jessrun.isInt6(v)){
		return false
	}else{
		return true;
	}
}
//检查是否勾选协议
jessrun.chkServie=function (v){
	if($(v).attr("checked")==false){
		return false;
	}else{
		return true;
	}
}

//检查手机号
jessrun.chkTel=function (v){
	if(!jessrun.isTel(v)){
		return false;
	}else{
		return true;
	}
}
//检查固定电话
jessrun.chkMobile=function (v){
	if(!jessrun.isMobile(v)){
		return false;
	}else{
		return true;
	}
}
//检查邮箱
jessrun.chkEmail=function (v){
	if(!jessrun.isEmail(v)){
		return false;
	}else{
		return true;
	}
}
//检查邮政编码
jessrun.chkPostCode=function (v){
	if(!jessrun.isPostCode(v)){
		return false;
	}else{
		return true;
	}
}
//检查中文姓名
jessrun.chkChineseName=function (v){
	if(!jessrun.isChineWord(v)){
		return false;
	}else{
		return true;
	}
}
//检查公司名称
jessrun.chkCompanyName=function (v){
	if(!jessrun.isChineseEnNum(v)){
		return false;
	}else{
		return true;
	}
}
//检查身份证号
jessrun.chkCardId=function (v){
	if(!jessrun.isCardId(v)){
		return false;
	}else{
		return true;
	}
}
//检查金额
jessrun.chkAmount=function (v){
	if(!jessrun.isAmount(v)){
		return false;
	}else{
		return true;
	}
}

//检查数量
jessrun.chkTotal=function (v){
	if(!jessrun.isIntZero(v)){
		return false;
	}else{
		return true;
	}
}

//检查radio checkbox是否选择
jessrun.chkChkBoxRadio = function(o){
	var item = $("[name="+o+"]:checked");
	var len=item.length;
	if(len<0){
		return false;
	}else{
		return true;
	}
}


//检查银行卡号
jessrun.chkBankNo=function (v){
	if(!jessrun.isIntZero(v)){
		return false;
	}else{
		return true;
	}
}

//检查银行卡开户行
jessrun.chkBankName=function (v){
	if(!jessrun.isStrs(v)){
		return false;
	}else{
		return true;
	}
}

//检查商户名称
jessrun.chkMerName=function (v){
	if(!jessrun.isChineseEnNum(v)){
		return false;
	}else{
		return true;
	}
}

//检查工商登录号
jessrun.chkMerId=function (v){
	if(!jessrun.isChineseEnNum(v)){
		return false;
	}else{
		return true;
	}
}
///检查税务登记号
jessrun.chkTaxId=function (v){
	if(!jessrun.isIntZero(v)){
		return false;
	}else{
		return true;
	}
}
///检查组织机构编码
jessrun.chkBusinessId=function (v){
	if(!jessrun.isIntZero(v)){
		return false;
	}else{
		return true;
	}
}