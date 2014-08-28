//function getMaxDate() {    //不跨月
//    var endTime = $('#date1').val();  
//    var bigMonth = "1,3,5,7,8,10,12";  
//    var month = endTime.substring(endTime.indexOf("-") + 2,endTime.lastIndexOf("-"));  
//    var yearMonth = endTime.substring(0,endTime.lastIndexOf("-") + 1);  
//    if(month == '2') {  
//        return yearMonth + "29";  
//    } else if(month.indexOf(bigMonth) != -1) {  
//        return yearMonth + "31";  
//    } else {  
//        return yearMonth + "30";  
//    }  
//}  
//  
//function getMinDate() {  
//    var startTime = $('#date2').val();  
//    var yearMonth = startTime.substring(0,startTime.lastIndexOf("-") + 1);  
//    return yearMonth + "01";  
//}  

function getMaxDate() {  
    var endTime = $('#date1').val();  
	if(endTime!=""){
	    var year = endTime.substring(0,4);
	    var month = endTime.substring(endTime.indexOf("-") + 1,endTime.lastIndexOf("-"));
	    var day  = endTime.substring(endTime.lastIndexOf("-")+1);
	    if(month=='12'){
	        month='01';
	        year =  (parseInt(year)+1).toString();
	    }else{
	       if(month=='11'|| month=='10'){
	    	month = (parseInt(month)+1).toString();
	       }else{
	    	   month = month.substring(1);
	    	   month ="0"+(parseInt(month)+1).toString();
	       }
	    }
	    return   year+"-"+month+"-"+day;
    }
}  
  
function getMinDate() {  
    var startTime = $('#date2').val();  
    if(startTime!=""){
	    var year  = startTime.substring(0,4);
	    var month = startTime.substring(startTime.indexOf("-") + 1,startTime.lastIndexOf("-"));
	    var day   = startTime.substring(startTime.lastIndexOf("-")+1);
	    if(month=='01'){
	        month='12';
	        year =  (parseInt(year)-1).toString();
	    }else{
		       if(month=='12'||month=='11'){
		    	month = (parseInt(month)-1).toString();
		       }else if(month=='10'){
		    	   month='09';
		       }else{
	    	   month = month.substring(1);
	    	   month = "0"+(parseInt(month)-1).toString();
	       }
	    }
	   	return   year+"-"+month+"-"+day;
    }
}  