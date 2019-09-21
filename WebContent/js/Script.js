$(document).ready(function() {
	var jbOffset = $('.TitleMenu').offset();
	$(window).scroll(function() {
		if ($(document).scrollTop() > jbOffset.top) {
			$('.TitleMenu').addClass('jbFixed');
		} else {
			$('.TitleMenu').removeClass('jbFixed');
		}
	});
});