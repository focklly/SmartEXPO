/**
 * @author Ben
 */

var isBottomBarDisplayed = false;
var isSearchBarDisplayed = false;
var isCommentBarDisplayed = false;

$(document).ready(function() {
    $("#frame").click(function() {
        console.log(document.getElementsByTagName("header")[0].getBoundingClientRect().top);
    });

    $(window).scroll(function() {
        if (document.getElementsByTagName("header")[0].getBoundingClientRect().top <= -60) {
            $("#content_fixed").css("position", "fixed");
            $("#content_fixed").css("top", "40px");
            if (isBottomBarDisplayed == false) {
                isBottomBarDisplayed = true;
                $("footer").fadeIn('slow');
            }
        } else {
            $("#content_fixed").css("position", "relative");
            $("#content_fixed").css("top", "0");
            if (isBottomBarDisplayed == true) {
                isBottomBarDisplayed = false;
                $("footer").fadeOut('slow');
            }
        }
    });

    $(".func_button").mouseenter(function() {
        $(this).fadeTo('fast', 1);
    });

    $(".func_button").mouseleave(function() {
        $(this).fadeTo('fast', 0.8);
    })

    $("#search_img").click(function() {
        if (isSearchBarDisplayed == false) {
            isSearchBarDisplayed = true;
            $("#search").animate({
                "width" : "250px",
            }, 500);
            $("#search_box").css('display', 'inline');
            $("#search_box").animate({
                width : '200px',
            }, 500);
            $("#search_box").val("Search");
        } else {
            isSearchBarDisplayed = false;
            $("#search").animate({
                "width" : "40px",
                "height" : "40px",
            }, 500);
            $("#search_box").animate({
                width : '0px',
            }, 500, function() {
                $("#search_box").css('display', 'none');
            });
            $("#search_result").css('display', 'none');

        }
    });

    $("#search_box").focus(function() {
        $(this).val("");
        $("#search").animate({
            height : '300px',
        }, 500, function() {
            $("#search_result").css('display', 'block');

        });
    });

    $("#comment_img").click(function() {
        console.log("aa");
        if ( isCommentBarDisplayed == false) {
            isCommentBarDisplayed = true;
            $("#comment").animate({
                "width" : "250px",
            }, 500);
        } else {
            isCommentBarDisplayed = false;
            $("#comment").animate({
                "width" : "40px",
                'height' : '40px',
            }, 500);

        }
    });
});
