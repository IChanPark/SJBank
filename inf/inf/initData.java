package inf;

import javax.servlet.http.HttpServletRequest;

public class initData {

	public initData(HttpServletRequest request) {
	
		String [] arr = request.getRequestURI().substring(       // 경로지정  지금 어디에 있고 어디로 보내야하는지
				(request.getContextPath()+"/").length()).split("/");
		
		request.setAttribute("type", arr[0]);
		request.setAttribute("cate", arr[1]);
		request.setAttribute("main", arr[2]);
	
		System.out.println("init"+" : "+arr[0]);
		System.out.println("init"+" : "+arr[1]);
		System.out.println("init"+" : "+arr[2]);
	}
}
