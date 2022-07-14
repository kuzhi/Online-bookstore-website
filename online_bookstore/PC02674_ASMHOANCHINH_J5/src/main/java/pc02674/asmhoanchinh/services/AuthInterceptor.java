package pc02674.asmhoanchinh.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import pc02674.asmhoanchinh.entity.NguoiDung;

@Service
public class AuthInterceptor implements HandlerInterceptor{

	@Autowired
	SessionService session;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
		String uri = request.getRequestURI();
		//lay account tu session
		NguoiDung user = session.get("user");
		
		String errorAuth = "";
		if(user == null) {
			//khi chua dang nhap
			errorAuth = "Not Logged In";
		}
		else if((user.getChucVu()== 1 && user.getChucVu()== 2) && uri.startsWith("/kgbBookstore.com/admin/index")){
			//khong dung vai tro admin
			errorAuth= "Access is denied";
			
		}
		if(errorAuth.length()>0) {
			// co loi
			session.set("errorAuth", errorAuth);
			response.sendRedirect("/kgbBookstore.com/login?error=" + errorAuth);
			return false;
		}
		
		return true;
	}
}
