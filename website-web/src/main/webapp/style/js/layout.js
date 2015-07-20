$(function(){
	$('#switchbar').click(function(){
	if ($("#wapFrame",parent.document.body).attr("cols")=="180,7,*"){
			$("#switchbar").removeClass("current");
			$("#switchbar").attr("title","展开左侧菜单");
			$("#wapFrame",parent.document.body).attr("cols","0,7,*");
		}
		else{
			$("#switchbar").addClass("current");
			$("#switchbar").attr("title","关闭左侧菜单");
			$("#wapFrame",parent.document.body).attr("cols","180,7,*");
		}
	})
})

$(function(){
    $(".toggleBd:not('.toggleBd:eq(1)')").hide();
    $('.toggleHd').click(function(){
	if( $(this).next().is(':hidden') ) {
            $('.toggleHd').removeClass('current').next().slideUp();
            $(this).toggleClass('current').next().slideDown();
	}else{
            $(this).toggleClass('current');
            $(this).next().slideUp();
        }
    });
});


$(function(){
    $(".sider-item-bd").hide();
    $(".sider-li:last-child").css("border-bottom","none");

    $('.sider-item-a').click(function(){
	if( $(this).parent().next().is(':hidden') ) {
            $('.sider-item-a').removeClass('cur').parent().next().slideUp();
            $(this).toggleClass('cur').parent().next().slideDown();
	}else{
            $(this).toggleClass('cur');
            $(this).parent().next().slideUp();
    }
	return false;
    });

});


