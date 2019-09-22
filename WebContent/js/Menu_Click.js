$(document).ready(function() {
	console.log( "Menu_Click.js!" );
	$("a[data-menu-name]").on("click", function() {	//메뉴 이동용
		var aa = $(this).data("menu-id");
		var f=document.paging; 
	    f.type.value = aa; 
	    f.method="post";
	    f.submit();
	});
});