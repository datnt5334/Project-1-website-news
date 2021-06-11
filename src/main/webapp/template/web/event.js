
/* back to top scroll */

$(document).ready(function () {

    $(window).scroll(function(){
        if($(this).scrollTop() > 100){
            $('#topBtn').fadeIn();
        } else {
            $('#topBtn').fadeOut();
        }
    });

    $("#topBtn").click(function () {
        $('html, body').animate({scrollTop : 0},650);
    });
});