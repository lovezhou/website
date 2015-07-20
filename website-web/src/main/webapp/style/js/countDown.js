var jessrun = typeof $ === "function" ? window.$ : {};
//默认跳转
jessrun.urlTo = function(urlId,id){
	var url =$("#"+urlId).val();
	var number =$("#"+urlId).attr("number");
	$("#"+id).CRcountDown({
		startNumber:number,
		endNumber: 0,
		callBack:function (){
			window.location.href=url;
		}
	});
}
//默认跳转带多个添加动作
jessrun.urlTonNmber = function(urlId,id,numberId){
	var url =$("#"+urlId).val();
	var number =$("#"+urlId).attr("toNumber");
	$("#"+id).CRcountDown({
		startNumber:number,
		endNumber: 0,
		callBack:function (){
			window.location.href=url;
		}
	});
}
//默认跳转历史页面
jessrun.urlToHistroy = function(urlId,id){
	var url =$("#"+urlId).val();
	var number =$("#"+urlId).attr("number");
	$("#"+id).CRcountDown({
		startNumber:number,
		endNumber: 0,
		callBack:function (){
			window.location.href=url;;
			
			
		}
	});
}
//默认登录
jessrun.urlToLogin = function(urlId,id){
	var url =$("#"+urlId).val();
	var number =$("#"+urlId).attr("number");
	$("#"+id).CRcountDown({
		startNumber:number,
		endNumber: 0,
		callBack:function (){
			window.top.location.href=url+"?time="+new Date().getTime();
		}
	});
}
//默认关闭页面
jessrun.urlToClose = function(urlId,id){
	var url =$("#"+urlId).val();
	$("#"+id).CRcountDown({
		startNumber:5,
		endNumber: 0,
		callBack:function (){
			window.opener = null; 
			window.open("","_self");
			window.close();
		}
	});
}
jQuery.fn.countDown = function(settings,to) {
	if(!to && to != settings.endNumber) { to = settings.startNumber; }
	this.data("CR_currentTime",to);
	$(this).text(to).animate({"none":"none"},settings.duration,'',function() {
		if(to > settings.endNumber ) {
			$(this).countDown(settings,to - 1);
		}else{
			settings.callBack(this);
		}
	});
	return this;
};
//计时&&重新计时
jQuery.fn.CRcountDown = function(settings) {
	settings = jQuery.extend({
		startNumber: 15,
		endNumber: 0,
		duration: 1000,
		callBack: function() { }
	}, settings);
	this.data("CR_duration",settings.duration);
	this.data("CR_endNumber",settings.endNumber);
	this.data("CR_callBack",settings.callBack);
	return this.stop().countDown(settings);
};
//计时暂停
jQuery.fn.pause = function(settings) {
	return this.stop();
};
//暂停后，重新开始
jQuery.fn.reStart = function() {
	return this.pause().CRcountDown({
		startNumber : this.data("CR_currentTime"),
		duration : 	this.data("CR_duration"),
		endNumber : this.data("CR_endNumber"),
		callBack : this.data("CR_callBack")
	});
};