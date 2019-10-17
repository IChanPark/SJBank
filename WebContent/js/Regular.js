function check(re, what, message) {
    if(re.test(what.val())) {
      	what.next().remove();
        return true;
    }
    what.val('');
  	//what.focus();
  	what.next().remove();
    what.after("<div style='color: #4375DB'>"+message+"</div>");
};

function check2(re, what1, what2, message) {
	if(what1.val() == what2.val()){
		if(re.test(what1.val())) {
      		what1.next().remove();
      		return true;
    	}
	} else {
		message = '비밀번호가 일치하지 않습니다.'
	}
    what1.val('');
    what2.val('');
  	//what.focus();
  	what1.next().remove();
    what1.after("<div style='color: #4375DB'>"+message+"</div>");
};