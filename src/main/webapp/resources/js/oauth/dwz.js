 function dwz_jump(count,url) { 
    window.setTimeout(function(){ 
        count--; 
        if(count > 0) { 
        	$('.count').html(count); 
        	dwz_jump(count,url); 
        } else { 
            window.location.href = url; 
        } 
    }, 1000); 
}
 window.onload = function() {
		dwz_jump(3,$('.redirect_url').attr('href'));
	};