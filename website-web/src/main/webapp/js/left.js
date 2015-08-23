function setDefault(){
			/*$("ul").each(function()
			{
				if($(this).css("display")=="block")	
					$(this).parents("li").addClass("on");
			});*/
			
			 var $lastEl;
		     $("a").click(function(){
		        if($lastEl){
		         $lastEl.css("color","");
		        }
		       
		        $lastEl =$(this);
		        $(this).css("color","red");
		        
		     });
		     
		      //自动加载第一个mainframe
		     var a_= $("a").eq(0);
		     a_.click();
}
function SwitchMenu(obj){
	var el = document.getElementById(obj);
	var ar = document.getElementById("masterdiv").getElementsByTagName("ul"); 
	if(el.style.display != "block"){ 
		for (var i=0; i<ar.length; i++){
			ar[i].style.display = "none"; 
		}
		el.style.display = "block";
	}
	else{
		el.style.display = "none";
	}
	//设置on样式
	$("li[name^='li_']").removeClass("on");
	$("li[name='li_"+obj+"']").addClass("on");
}
		