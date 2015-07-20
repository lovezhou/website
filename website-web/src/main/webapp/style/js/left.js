$(function(){
    $(".toggleBd:not('.toggleBd:first')").hide();
    $('.toggleHd').click(function(){
	if( $(this).next().is(':hidden') ) {
            $('.toggleHd').removeClass('current').next().slideUp();
            $(this).toggleClass('current').next().slideDown();
	}else{
            $(this).toggleClass('current');
            $(this).next().slideUp();
        }
	//return false;
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
	//$(".sider-item-bd-a :last-child").css("border-bottom","none");
	return false;
    });

});


