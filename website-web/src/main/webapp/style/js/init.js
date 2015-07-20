var jessrun = typeof $ === "function" ? window.$ : {};
(function(a){a.fn.tableUI=function(b){var c={evenRowClass:"evenTd",oddRowClass:"oddTd",activeRowClass:"onTd"};var b=a.extend(c,b);this.each(function(){var d=a(this);a(d).find("tr:odd").addClass(b.evenRowClass);a(d).find("tr:even").addClass(b.oddRowClass);a(d).find("tr").bind("mouseover",function(){a(this).addClass(b.activeRowClass)});a(d).find("tr").bind("mouseout",function(){a(this).removeClass(b.activeRowClass)})})}})(jQuery);
jQuery.fn.fixedtableheader=function(a){var c=jQuery.extend({headerrowsize:1,highlightrow:false,highlightclass:"highlight"},a);this.each(function(g){var j=$(this);var e=j.find("tr:lt("+c.headerrowsize+")");var f="th";if(e.find(f).length==0){f="td"}if(e.find(f).length>0){e.find(f).each(function(){$(this).css("width",$(this).width())});var h=j.clone().empty();var d=b(j);h.attr("id","fixedtableheader"+g).css({position:"fixed",marginTop:"32px",top:"0",left:j.offset().left}).append(e.clone()).width(d).hide().appendTo($("body"));if(c.highlightrow){$("tr:gt("+(c.headerrowsize-1)+")",j).hover(function(){$(this).addClass(c.highlightclass)},function(){$(this).removeClass(c.highlightclass)})}$(window).scroll(function(){if(jQuery.browser.msie&&jQuery.browser.version=="6.0"){h.css({position:"absolute",marginTop:"32px",top:$(window).scrollTop(),left:j.offset().left})}else{h.css({position:"fixed",marginTop:"32px",top:"0",left:j.offset().left-$(window).scrollLeft()})}var i=$(window).scrollTop();var k=e.offset().top;if(i>k&&i<=(k+j.height()-e.height())){h.show()}else{h.hide()}});$(window).resize(function(){if(h.outerWidth()!=j.outerWidth()){e.find(f).each(function(k){var i=$(this).width();$(this).css("width",i);h.find(f).eq(k).css("width",i)});h.width(j.outerWidth())}h.css("left",j.offset().left)})}});function b(e){var d=e.outerWidth();return d}};
(function(a){a.fn.checkboxAll=function(b){var d={chkName:"chkItem",chkClass:"chkbg",callback:function(){}},b=a.extend(d,b),f=a(this),e=a("input[name='"+b.chkName+"']"),c=0;e.click(function(){var h=0;var g="";a("[name="+b.chkName+"]").each(function(){if(a(this).attr("checked")){h+=Number(a(this).val());g+=a(this).val()+",";a(this).parent().parent().addClass(b.chkClass)}else{a(this).parent().parent().removeClass(b.chkClass)}if(e.filter(":checked").length==e.length){f.attr("checked",true)}else{f.removeAttr("checked")}});c=e.filter(":checked").length;if(typeof b.callback==="function"){b.callback(g,c,h)}});return f.each(function(){a(this).click(function(){var h=0;var g="";a("[name="+b.chkName+"]").each(function(){if(f.attr("checked")){h+=Number(a(this).val());g+=a(this).val()+",";e.attr("checked",true);e.parent().parent().addClass(b.chkClass)}else{e.removeAttr("checked");e.parent().parent().removeClass(b.chkClass)}});c=e.filter(":checked").length;if(typeof b.callback==="function"){b.callback(g,c,h)}})})}})(jQuery);
jessrun.config = {
	domain:"/jessrun/",							//设置url
	state:"/jessrun/bic/state/getListForSelect.act",				//站点
	stateToState:"/jessrun/bic/line/getListForSelect.act",		//站点级联
	companyToState:"/jessrun/bic/line/getListForSelect.act",		//单位取站点
	dept:"/jessrun/bic/dept/getListForSelect.act",				//部门
	car:"/jessrun/bic/car/getListForSelectCar.act",					//车辆
	driver:"/jessrun/bic/cardriver/getListForSelectByCar.act",			//司机
	staff:"/jessrun/bic/staff/getListForSelect.act",				//员工
	partner:"/jessrun/bic/partner/getListForSelect.act",			//合作伙伴
	customer:"/jessrun/bic/customer/getListForSelect.act",		//客户
	custlinkmn:"/jessrun/bic/custlinkmn/getListForSelect.act",	//客户联系人
	depot:"/jessrun/bic/depot/getListForSelect.act",				//仓库
	dict:"/jessrun/bic/dict/getListForSelect.act",				//字典
	smallCar:"/jessrun/bic/car/getListForSelectSmallCar.act" 			//取小车
};
jessrun.url = {
	jsError:"JS文件URL错误,请检查路径"
}

var dialog = typeof $ === "function" ? window.$ : {};
dialog.txt ={
	load_tips:"数据加载中",
	load_error:"数据加载出错",
	tit_error:"出错了",
	btn_conf:"确定",
	btn_cancel:"取消",
	btn_close:"关闭",
	btn_reset:"重置",
	call_error:["对不起，创建弹出层失败！窗口","已存在！"]
};

jessrun.loadCSS(jessrun.config.domain+"/style/css/dialog.css","dialog");
jessrun.loadJS(jessrun.config.domain+"/style/js/dialog-min.js",function(){

},function(){

});



//表单提交
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




//load Select

var jessrun = typeof $ === "function" ? window.$ : {};
jessrun.tip = {
	state:"开始站点ID不存在",								//站点
	dept:"部门ID不存在",									//部门
	car:"车辆ID不存在",									//车辆
	driver:"司机ID不存在",								//司机
	staff:"员工ID不存在",								//员工
	partner:"合作伙伴不存在",							//合作伙伴
	customer:"客户ID不存在",								//客户
	custlinkmn:"客户联系人不存在",						//客户联系人
	depot:"仓库ID不存在",								//仓库
	dict:"字典ID不存在",									//字典
	defaultOps:"--请选择--",
	selectAll:"--全 部--"
};
//加载站点
jessrun.loadState = function(objId){
	if($("#"+objId).size()>0){
		var stateId = $("#stateId").val();
		var stateCompanyId = $("#stateCompanyId").val();
		//var _url=jessrun.config.state+"?time="+new Date().getTime();
		//if(stateId!=null&&stateId!="")
		var _url=jessrun.config.state+"?stateCompanyId="+stateCompanyId+"&stateId="+stateId+"&time="+new Date().getTime();
		$.ajax( {
			type :"get",
			url :_url,
			cache:false,
			async: false,
			success : function(data) {
				//alert(data);
				$("#"+objId).empty();
				setTimeout(function (){
					$("#"+objId).html(data);
				},1);
			},
			beforeSend:function(data){jessrun.showLoading("#"+objId)},
			complete:function(result){
				jessrun.showComplete("#"+objId);
			},
			error:function(result){
				jessrun.showError("#"+objId);
			}
		})
	}else{
		alert(jessrun.tip.state);
	}
}
//公司取站点
jessrun.loadToCompState = function(stateCompanyId,stateEndId,selectedId){
		var stateCompanyId = $("#"+stateCompanyId).val();
		//alert("取站点的值lineStateStartId："+lineStateStartId+"\n"+"SelectedId的值:"+selectedId);
		if(stateCompanyId==null||stateCompanyId=="")
			stateCompanyId=-1;
		var _url =jessrun.config.state+"?stateCompanyId="+stateCompanyId+"&selectedId="+selectedId+"&time="+new Date().getTime();
		$.ajax ({
			cache:false,
			async: false,
			type: 'get',
			url:_url,
			success: function(data) {
				$("#"+stateEndId).empty();
				//setTimeout(function (){
					$("#"+stateEndId).html("<option value=''>"+jessrun.tip.defaultOps+"</option>"+data)
				//},1);
			},
			beforeSend:function(data){jessrun.showLoading("#"+stateEndId)},
			complete:function(result){
				jessrun.showComplete("#"+stateEndId);
			},
			error:function(result){
				jessrun.showError("#"+stateEndId);
			}
		});
}

//取站点
jessrun.loadToState = function(stateStartId,stateEndId,selectedId){

		var lineStateStartId = $("#"+stateStartId).val();
		//alert("取站点的值lineStateStartId："+lineStateStartId+"\n"+"SelectedId的值:"+selectedId);
		if(lineStateStartId==null||lineStateStartId=="")
			lineStateStartId=-1;
		var _url =jessrun.config.stateToState+"?lineStateStartId="
			+lineStateStartId+"&selectedId="+selectedId+"&time="+new Date().getTime();
		$.ajax ({
			cache:false,
			async: false,
			type: 'get',
			url:_url,
			success: function(data) {
				$("#"+stateEndId).empty();
				//setTimeout(function (){
					$("#"+stateEndId).html("<option value=''>"+jessrun.tip.defaultOps+"</option>"+data)
				//},1);
			},
			beforeSend:function(data){jessrun.showLoading("#"+stateEndId)},
			complete:function(result){
				jessrun.showComplete("#"+stateEndId);
			},
			error:function(result){
				jessrun.showError("#"+stateEndId);
			}
		});
}
//取部门
jessrun.loadToDept = function(pid,cid,depId,selectedId,orgTypeId){
	if(orgTypeId == undefined){
		orgTypeId = 0;
	}
	if (orgTypeId!=0){
		orgTypeId=0;
	}
		
		
	var deptStateId = $("#"+pid).val();
	//增加部门类型 1为业务 2为财务3 3为收货点
	//alert("取部门的值deptStateId："+deptStateId+"\n"+"selectedId的值："+selectedId);
	if(deptStateId==null||deptStateId=="")
		deptStateId=-1;
	if(deptTypeId==null || deptTypeId > 0 ){
		var deptTypeId = $("#"+depId).val();
		var _url=jessrun.config.dept+"?deptStateId="+deptStateId+"&deptTypeId="+deptTypeId+"&selectedId="+selectedId+"&orgTypeId="+orgTypeId+"&time="+new Date().getTime();
		
	}else{
		var _url=jessrun.config.dept+"?deptStateId="+deptStateId+"&selectedId="+selectedId+"&orgTypeId="+orgTypeId+"&time="+new Date().getTime();
	}
	$.ajax ({
		cache:false,
		async: false,
		type: 'get',
		url:_url,
		success: function(data) {
			$("#"+cid).empty();
			setTimeout(function (){
				$("#"+cid).html("<option value=''>"+jessrun.tip.defaultOps+"</option>"+data)
			},1);
		},
		beforeSend:function(data){jessrun.showLoading("#"+cid)},
		complete:function(result){
			jessrun.showComplete("#"+cid);
		},
		error:function(result){
			jessrun.showError("#"+cid);
		}

	});
}

//取员工
jessrun.loadToStaff = function(pid,cid,selectedId){
	var staffStateId = $("#"+pid).val();
	if(staffStateId==null||staffStateId=="")
		staffStateId=-1;
	//alert("取员工staffStateId的值"+staffStateId+"\n"+"selectedId的值"+selectedId);
	var _url=jessrun.config.staff+"?staffStateId="+staffStateId+"&selectedId="+selectedId+"&time="+new Date().getTime();
	$.ajax ({
		cache:false,
		async: false,
		type: 'get',
		url:_url,
		success: function(data) {
			$("#"+cid).empty();
			setTimeout(function (){
				$("#"+cid).html("<option value=''>"+jessrun.tip.defaultOps+"</option>"+data)
			},1);
		},
		beforeSend:function(data){jessrun.showLoading("#"+cid)},
		complete:function(result){
			jessrun.showComplete("#"+cid);
		},
		error:function(result){
			jessrun.showError("#"+cid);
		}
	});
}
//取车辆
jessrun.loadToCar = function(id){
	if($("#"+id).size()>0){
		var _url=jessrun.config.car+"?time="+new Date().getTime();
		$.ajax( {
			type :"get",
			url :_url,
			cache:false,
			async: false,
			success : function(data) {
				$("#"+cid).empty();
				setTimeout(function (){
					$("#"+cid).html("<option value=''>"+jessrun.tip.defaultOps+"</option>"+data)
				},1);
			},
			beforeSend:function(data){jessrun.showLoading("#"+cid)},
			complete:function(result){
				jessrun.showComplete("#"+cid);
			},
			error:function(result){
				jessrun.showError("#"+cid);
			}
			})
	}else{
		alert(jessrun.tip.car);
	}
}

//根据站点取小车
jessrun.loadToSmallCar = function(pid, cid, selectedId){
	var endStateId = $("#"+pid).val();
	//alert("取司机carId的值"+stateId+"\n"+"selectedId的值"+selectedId);
	if(endStateId==null||endStateId=="")
		endStateId=-1;
	var _url=jessrun.config.smallCar+"?endStateId="+endStateId+"&selectedId="+selectedId+"&time="+new Date().getTime();
	$.ajax( {
		type :"get",
		url :_url,
		cache:false,
		async: false,
		success : function(data) {
			$("#"+cid).empty();
			setTimeout(function (){
				$("#"+cid).html("<option value=''>"+jessrun.tip.defaultOps+"</option>"+data)
			},1);
		}
	})

}

//取司机
jessrun.loadToDriver = function(pid,cid,selectedId){
	var listCarId = $("#"+pid).val();
	//alert("取司机carId的值"+stateId+"\n"+"selectedId的值"+selectedId);
	if(listCarId==null||listCarId=="")
		listCarId=-1;
	var _url=jessrun.config.driver+"?listCarId="+listCarId+"&selectedId="+selectedId+"&time="+new Date().getTime();
	$.ajax ({
		cache:false,
		async: false,
		type: 'get',
		url:_url,
		success: function(data) {
			$("#"+cid).empty();
			setTimeout(function (){
				$("#"+cid).html("<option value=''>"+jessrun.tip.defaultOps+"</option>"+data)
			},1);
		},
		beforeSend:function(data){jessrun.showLoading("#"+cid)},
		complete:function(result){
			jessrun.showComplete("#"+cid);
		},
		error:function(result){
			jessrun.showError("#"+cid);
		}

	});
}
//加载合作伙伴
jessrun.loadPartner = function(id){
	if($("#"+id).size()>0){
		var _url=jessrun.config.partner+"?time="+new Date().getTime();
		$.ajax( {
			type :"get",
				url :_url,
				cache:false,
				async: false,
				success : function(data) {
					$("#"+id).empty();
					setTimeout(function (){
						$("#"+cid).html("<option value=''>"+jessrun.tip.defaultOps+"</option>"+data)
					},1);
				},
				beforeSend:function(data){jessrun.showLoading("#"+id)},
				complete:function(result){
					jessrun.showComplete("#"+id);
				},
				error:function(result){
					jessrun.showError("#"+id);
				}
			})
	}else{
		alert(jessrun.tip.partner);
	}
}
//加载客户
jessrun.loadCustomer = function(id){
	if($("#"+id).size()>0){
		var _url=jessrun.config.customer+"?time="+new Date().getTime();
		$.ajax( {
			type :"get",
				url :_url,
				cache:false,
				async: false,
				success : function(data) {
					$("#"+id).empty();
					setTimeout(function (){
						$("#"+cid).html("<option value=''>"+jessrun.tip.defaultOps+"</option>"+data)
					},1);
				},
				beforeSend:function(data){jessrun.showLoading("#"+id)},
				complete:function(result){
					jessrun.showComplete("#"+id);
				},
				error:function(result){
					jessrun.showError("#"+id);
				}
			})
	}else{
		alert(jessrun.tip.customer);
	}
}

//加载客户联系人
jessrun.loadCustlinkmn = function(id){
	if($("#"+id).size()>0){
		var _url=jessrun.config.custlinkmn+"?time="+new Date().getTime();
		$.ajax( {
			type :"get",
				url :_url,
				cache:false,
				async: false,
				success : function(data) {
					$("#"+id).empty();
					setTimeout(function (){
						$("#"+cid).html("<option value=''>"+jessrun.tip.defaultOps+"</option>"+data)
					},1);
				},
				beforeSend:function(data){jessrun.showLoading("#"+id)},
				complete:function(result){
					jessrun.showComplete("#"+id);
				},
				error:function(result){
					jessrun.showError("#"+id);
				}
			})
	}else{
		alert(jessrun.tip.custlinkmn);
	}
}
//加载仓库
jessrun.loadDepot = function(id){
	if($("#"+id).size()>0){
		var _url=jessrun.config.depot+"?time="+new Date().getTime();
		$.ajax( {
			type :"get",
				url :_url,
				cache:false,
				async: false,
				success : function(data) {
					$("#"+id).empty();
					setTimeout(function (){
						$("#"+id).html("<option value=''>"+jessrun.tip.defaultOps+"</option>"+data)
					},1);
				},
				beforeSend:function(data){jessrun.showLoading("#"+id)},
				complete:function(result){
					jessrun.showComplete("#"+id);
				},
				error:function(result){
					jessrun.showError("#"+id);
				}
			})
	}else{
		alert(jessrun.tip.depot);
	}
}
//取仓库
jessrun.loadToDepot = function(pid,cid,selectedId){
	var depotStateId = $("#"+pid).val();
	//alert("取仓库depotStateId的值"+depotStateId+"\n"+"selectedId的值"+selectedId);
	if(depotStateId==null|depotStateId=="")
		depotStateId=-1;
	var _url=jessrun.config.depot+"?depotStateId="+depotStateId+"&selectedId="+selectedId+"&time="+new Date().getTime();
	$.ajax ({
		cache:false,
		async: false,
		type: 'get',
		url:_url,
		success: function(data) {
			$("#"+cid).empty();
			setTimeout(function (){
				$("#"+cid).html("<option value=''>"+jessrun.tip.defaultOps+"</option>"+data)
			},1);
		},
		beforeSend:function(data){jessrun.showLoading("#"+cid)},
		complete:function(result){
			jessrun.showComplete("#"+cid);
		},
		error:function(result){
			jessrun.showError("#"+cid);
		}

	});
}
//加载字典
jessrun.loadDict = function(id,v){
	if($("#"+id).size()>0){
		var _url=jessrun.config.dict+"?type="+v+"&time="+new Date().getTime();
		$.ajax( {
			type :"get",
				url :_url,
				cache:false,
				async: false,
				success : function(data) {
					$("#"+id).empty();
					setTimeout(function (){
						$("#"+id).html("<option value=''>"+jessrun.tip.defaultOps+"</option>"+data)
					},1);
				},
				beforeSend:function(data){jessrun.showLoading("#"+id)},
				complete:function(result){
					jessrun.showComplete("#"+id);
				},
				error:function(result){
					jessrun.showError("#"+id);
				}
			})
	}else{
		alert(jessrun.tip.dict);
	}
}

//验证整数
function checkInt(obj)
{
	//如果为空，则为0
	var getVal = $(obj).val();
	if(getVal=="")
		$(obj).val("0");
	//如果不是数字
	else if(isNaN(getVal))
		$(obj).val("0");
	//如果小于0
	else if(Number($(obj).val())<0)
		$(obj).val("0");
	$(obj).val(parseInt(Number($(obj).val())));
	return parseInt(Number($(obj).val()));
}

//验证实数
function checkFloat(obj,i)
{
	//如果为空，则为0
	var getVal = $(obj).val();
	if(getVal=="")
		$(obj).val("0");
	//如果不是数字
	else if(isNaN(getVal))
		$(obj).val("0");
	//如果小于0
	else if(Number($(obj).val())<0)
		$(obj).val("0");
	//取i小数
	$(obj).val(Number($(obj).val()).toFixed(i));
	return Number($(obj).val()).toFixed(i);
}

//验证整数
function checkIntDefault(obj, def)
{
	//如果为空，则为0
	var getVal = $(obj).val();
	if(getVal=="")
		$(obj).val(def);
	//如果不是数字
	else if(isNaN(getVal))
		$(obj).val(def);
	//如果小于0
	else if(Number($(obj).val())<0)
		$(obj).val(def);
	$(obj).val(parseInt(Number($(obj).val())));
	return $(obj).val();
}

//验证金额
function checkMoney(obj)
{
	//如果为空，则为0
	var getVal = $(obj).val();
	if(getVal=="")
		$(obj).val("0");
	//如果不是数字
	else if(isNaN(getVal))
		$(obj).val("0");
	//如果小于0
	else if(Number($(obj).val())<0)
		$(obj).val("0");
	//取两位小数
	$(obj).val(Number($(obj).val()).toFixed(2));
	return Number($(obj).val()).toFixed(2);
}

function checkMoneyAndDefault(obj,def)
{
	//如果为空，则为0
	var getVal = $(obj).val();
	if(getVal=="")
		$(obj).val(def);
	//如果不是数字
	else if(isNaN(getVal))
		$(obj).val(def);
	//如果小于0
	else if(Number($(obj).val())<0)
		$(obj).val(def);
	//取两位小数
	$(obj).val(Number($(obj).val()).toFixed(2));
	return Number($(obj).val()).toFixed(2);
}


jessrun.toDate=function (str){
	var sd=str.split("-");
	return new Date(sd[0],sd[1],sd[2]);
}


//检查起止时间
jessrun.chkTimeRange=function (o,e){
	var d = new Date();
	var year = d.getFullYear();
    var month = d.getMonth() + 1;
    month = month < 10 ? ("0" + month) : month;
    var day = d.getDate();
    var day2 = d.getDate()+1;
    day = day < 10 ? ("0" + day) : day;
    day2 = day2 < 10 ? ("0" + day2) : day2;
    var today = year+"-"+month+"-"+day;
    var today2 = year+"-"+month+"-"+day2;

	var v1 =$.trim($(o).val());
	var v2 =$.trim($(e).val());
	var d1 = jessrun.toDate(v1);
	var d2 = jessrun.toDate(v2);
	if(v1==""&&v2==""){//都为空
		$(e).val(jessrun.getEod("t"));
		
		$(o).val(jessrun.getUpMonthDay(today));
	}else if(v1==""&&v2!=""){//开始日期为空
		$(o).focus();
		var t=v2;
		$(o).val(jessrun.getUpMonthDay(t));
	}else if(v1!=""&&v2==""){//结束时间为空
		$(e).focus();
		var t=v1;
		$(e).val(jessrun.getDownMonthDay(t));
	}else if(d1>d2){
		$(e).focus();
		$(o).val(v2);
		$(e).val(v1);
	}else {
		return true;
	}
}


jessrun.getEod= function (yt,date){
	if(date){
		var a_date=date.split("-");
		date=new Date(a_date[0],a_date[1]-1,a_date[2]);
	}else{
		date=new Date();
	}
	var i_milliseconds=date.getTime();
	if(yt=="y"){
		i_milliseconds-=1000*60*60*24;
	}else{
		i_milliseconds+=1000*60*60*24;
	}
	var t_date = new Date();
	t_date.setTime(i_milliseconds);
	var i_year = t_date.getFullYear();
	var i_month = ("0"+(t_date.getMonth()+1)).slice(-2);
	var i_day = ("0"+t_date.getDate()).slice(-2);
	return i_year+"-"+i_month+"-"+i_day;
}
//jessrun.getEod("y");//获取昨天日期 即当前时间前一天
//jessrun.getEod("y","2012-6-1");//获取2012-6-1前一天的日期
//jessrun.getEodgetEod("t");//获取明天日期 即当前时间后一天
//jessrun.getEod("t","2012-6-30");//获取2012-6-30后一天的日期

//获得上一月的当前日期
jessrun.getUpMonthDay =function(date){
	var date = new Date();
    var daysInMonth = new Array([0],[31],[28],[31],[30],[31],[30],[31],[31],[30],[31],[30],[31]);
    var strYear = date.getFullYear();
    var strDay = date.getDate();
    var strMonth = date.getMonth()+1;
    if(strYear%4 == 0 && strYear%100 != 0){
       daysInMonth[2] = 29;
    }
    if(strMonth - 1 == 0)
    {
       strYear -= 1;
       strMonth = 12;
    }
    else
    {
       strMonth -= 1;
    }
    strDay = daysInMonth[strMonth] >= strDay ? strDay : daysInMonth[strMonth];
    if(strMonth<10)
    {
       strMonth="0"+strMonth;
    }
    if(strDay<10)
    {
       strDay="0"+strDay;
    }
    datastr = strYear+"-"+strMonth+"-"+strDay;
    return datastr;
}
//获得当前日期下一月
jessrun.getDownMonthDay=function(date){
	var date = new Date();
    var daysInMonth = new Array([0],[31],[28],[31],[30],[31],[30],[31],[31],[30],[31],[30],[31]);
    var strYear = date.getFullYear();
    var strDay = date.getDate()+1;

    var strMonth = date.getMonth()+1;
    if(strYear%4 == 0 && strYear%100 != 0){
       daysInMonth[2] = 29;
    }
    if(strMonth + 1 == 13)
    {
       strYear += 1;
       strMonth = 1;
    }
    else
    {
       strMonth += 1;
    }
    strDay = daysInMonth[strMonth] >= strDay ? strDay : daysInMonth[strMonth];
    if(strMonth<10)
    {
       strMonth="0"+strMonth;
    }
    if(strDay<10)
    {
       strDay="0"+strDay;
    }
    datastr = strYear+"-"+strMonth+"-"+strDay;
    return datastr;
 }
//折叠
jessrun.toggleTag = function(toggleId,obj){
	 if($(obj).is(":hidden")){
		$(toggleId).removeClass("toggle_down").addClass("toggle_up");
		$(obj).show();

	 }else{
		 $(toggleId).removeClass("toggle_up").addClass("toggle_down");
		 $(obj).hide();
	 }
}
//删除
jessrun.listDel = function (id,formId,hId){
	$("#"+hId).attr("value",$(id).attr("id"));
	var delName = $(id).attr("rel");
	jessrun.Dialog({
		title:"系统提示",
		callback:function(){
			jessrun.Dialog.removeBox();
			$("#"+formId).submit();
		},
		button:["确定","取消"],
		content:"text:<div class='error_alert_box'><p class='error_alert_content'>确认要删除<em>"+delName+"</em>此条记录吗？</p></div>"
	});
}
//注销
jessrun.listLogout = function (id,formId,hId){
	$("#"+hId).attr("value",$(id).attr("id"));
	var delName = $(id).attr("rel");
	jessrun.Dialog({
		title:"系统提示",
		callback:function(){
			jessrun.Dialog.removeBox();
			$("#"+formId).submit();
		},
		button:["确定","取消"],
		content:"text:<div class='error_alert_box'><p class='error_alert_content'>确认要注销<em>"+delName+"</em>此用户吗？</p></div>"
	});
}

//显示详情
jessrun.showDetails = function(url){
	var _url=$(url).attr("relUrl");
	jessrun.Dialog({
		title:"详情",
		width:$(window).width()-20,
		height:$(window).height()-90,
		showbg:true,
		drag:false,
		content:"iframe:"+_url
	});
}

//ajxa loading
jessrun.showLoading = function (id){ 
	 var l=$("<span id='loadInfo'><span>正在加载数据</span></span>");
	 $(id).hide();
	 $("#loadInfo").remove();
	 $(l).insertBefore(id);
};
jessrun.showError = function (id){
	 var l=$("<span id='loadInfo'><span>加载数据失败，请检查网络</span></span>");
	 $(id).hide();
	 $(l).insertBefore(id);;
};
jessrun.showComplete = function (id){
	 $(id).show();
	 $("#loadInfo").remove();
};


/*唯一性验证
jessrun.chkOnlyName=function(o){
	var url=$(o).attr("date-url");
	var v=$.trim($(o).val());
	if (v!="")) {
		$("#chkOnlyName").empty();
		return false;
	}else{
		var _url = url + "=" + $(o).val();
		$("#chkOnlyName").html("唯一性验证中");
		$.ajax({
			type: "post",
			url: _url,//请求数据
			data: "",
			success: function(data){//请求成功 返回数据和添加数据
				$("#chkOnlyName").html("<em>" + data + "</em>");
			}
		});
	}
};
*/