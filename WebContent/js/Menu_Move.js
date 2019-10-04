$(document).ready(function() {
	console.log("Ready to Move");
	$('a[href="#"]').click(function(e) {
		e.preventDefault();
	});
	
	$("a[data-menu-name]").on("click", function() {	//메뉴 이동용
		go_menu($(this).data("menu-name"));
	});

	console.log( "팀장님 살려줘요!!!!!" );
	$("button[data-menu-name]").on("click", function() {	//메뉴 이동용
		go_menu($(this).data("menu-name"));
	});
	
	function go_menu(me) {
		document.paging.hid_t.value = me;
		document.paging.submit();
	};
});