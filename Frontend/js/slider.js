/**
 * Created by Alexey on 05.11.2016.
 */
/*$(function()
 {
 alert('Подключена jQuery через Google хостинг');
 });*/
var slideWidth=500;
var sliderTimer;
$(function(){
    $('.slidewrapper').width($('.slidewrapper').children().size()*slideWidth);
    sliderTimer=setInterval(nextSlide,3000);
     $('.viewport').hover(function(){
     clearInterval(sliderTimer);
     },function(){
     sliderTimer=setInterval(nextSlide,3000);
     });
    $('#next_slide').click(function(){
        clearInterval(sliderTimer);
        nextSlide();
        sliderTimer=setInterval(nextSlide,3000);
    });
    $('#prev_slide').click(function(){
        clearInterval(sliderTimer);
        prevSlide();
        sliderTimer=setInterval(nextSlide,3000);
    });
});

function nextSlide(){
    var currentSlide=parseInt($('.slidewrapper').data('current'));
    currentSlide++;
    if(currentSlide>=$('.slidewrapper').children().size())
    {
        currentSlide=0;
    }
    $('.slidewrapper').animate({left: -currentSlide*slideWidth},300).data('current',currentSlide);
}

function prevSlide(){
    var currentSlide=parseInt($('.slidewrapper').data('current'));
    currentSlide--;
    if(currentSlide<0)
    {
        currentSlide=$('.slidewrapper').children().size()-1;
    }
    $('.slidewrapper').animate({left: -currentSlide*slideWidth},300).data('current',currentSlide);
}