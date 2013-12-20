/*微信页面js文件*/
//返回顶部
  $(window).scroll(function(){
    if ($(this).scrollTop() > 100){
      $('#gotop').fadeIn(100);
    }else{
        $('#gotop').fadeOut(100);
      }
  });
  $('#gotop').click(function(event){
    event.preventDefault();
    $('html,body').animate({scrollTop: 0},500);
  });
//选项却换
$('#tabs li').click(function(){
    $(this).addClass('active').siblings('li').removeClass('active');
    var act = $('#tabs li').index(this);
    $('#tabs-content .hide').eq(act).show().siblings('.hide').hide();
});


