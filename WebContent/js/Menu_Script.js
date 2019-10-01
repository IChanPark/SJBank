$(document).ready(function() {
	console.log( "Menu_Fixed.js!" );
	var jbOffset = $('.TitleMenu').offset();
	$(window).scroll(function() {		//스크롤 내릴 시 메뉴 고정
		if ($(document).scrollTop() > jbOffset.top) {
			$('.TitleMenu').addClass('jbFixed');
		} else {
			$('.TitleMenu').removeClass('jbFixed');
		}
	});
	
	$('a[href="#"]').click(function(e) {
		e.preventDefault();
	});
	
	$(document).ready(function() {
		console.log( "Menu_Click.js!" );
		$("a[data-menu-name]").on("click", function() {	//메뉴 이동용
			var f=document.paging; 
			
		    f.type.value = $(this).data("menu-name"); 
		    console.log(f.type.value);
		    f.method="post";
		    f.submit();
		});
	});
});