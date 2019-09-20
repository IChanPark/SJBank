package inf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface M_Action {

	void execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
