$(function(){
var $container = $('.category-container ul');
$container.imagesLoaded(function(){
  $container.masonry({
    itemSelector: 'li'
  });
});
})