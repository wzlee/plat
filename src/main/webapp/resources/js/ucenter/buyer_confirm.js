$(function(){
	var radio = document.getElementsByName("enter");
	var rid = document.getElementsByName("rid");
	var bid = document.getElementById("bid").value;
	radio[0].checked = true;	
	$('#choosebtn').click(function() {
		for(var i = 0; i < radio.length; i ++){				
			if(radio[i].checked){
				$.ajax({
					type: "POST",
					url: "bidding/chooseBidding",
					dataType:"json",
					data: {
						bid:bid,
						rid:rid[i].value
					}
				}).done(function(msg) {
					if(msg.success){
						alert(msg.message);
					}else{
						alert(msg.message);
					}
				}).fail(function(){ alert("error"); });
			}
		}  		
	});
	$('#cancelbtn').click(function() {		
		$.ajax({
			type: "POST",
		 	url: "bidding/cancelBidding",
		 	dataType:"json",
		 	data: {
				bid:bid				
			}
		}).done(function(msg) {
			if(msg.success){
				alert(msg.message);
			}else{
				alert(msg.message);
			}
		}).fail(function(){ alert("error"); });	
	});
	$('#closebtn').click(function() {
		
	});
});
