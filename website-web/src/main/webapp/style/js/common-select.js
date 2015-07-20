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
			}
		});
}
//取部门
jessrun.loadToDept = function(pid,cid,selectedId,orgTypeId){
	var deptStateId = $("#"+pid).val();
	//alert("取部门的值deptStateId："+deptStateId+"\n"+"selectedId的值："+selectedId);
	if(deptStateId==null||deptStateId=="")
		deptStateId=-1;
	if(orgtypeId == undefined)
		orgtypeId=0;
	var _url=jessrun.config.dept+"?deptStateId="+deptStateId+"&selectedId="+selectedId+"&orgTypeId="+orgTypeId+"&time="+new Date().getTime();
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
			}
			})
	}else{
		alert(jessrun.tip.car);
	}
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

//验证实数
function checkFloatAndDefault(obj,def,i)
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
	$(obj).val(Number($(obj).val()).toFixed(i));
	return Number($(obj).val()).toFixed(i);
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
    day = day < 10 ? ("0" + day) : day;
    var today = year+"-"+month+"-"+day;
    
	var v1 =$.trim($(o).val());
	var v2 =$.trim($(e).val());
	var d1 = jessrun.toDate(v1);
	var d2 = jessrun.toDate(v2);
	if(v1==""&&v2==""){//都为空
		$(e).val(today);
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
    var strDay = date.getDate();    
   
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