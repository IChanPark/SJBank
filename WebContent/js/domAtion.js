function goMenu(qq) {	//메뉴 이동용
	var f=document.paging; 
    f.hid_t.value = $(qq).data("menu-name"); 
    f.method="post";
    f.submit();
};