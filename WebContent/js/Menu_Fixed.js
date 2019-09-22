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
});