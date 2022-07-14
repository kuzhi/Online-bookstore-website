package pc02674.asmhoanchinh.services;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CookieService {
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpServletResponse response;

	public Cookie get(String name) {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie:cookies) {
				if(cookie.getName().equalsIgnoreCase(name)) {
					return cookie;
				}
			}
		}
		
		return null;
	}

	
	public String getValue(String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equalsIgnoreCase(name)) {
					return cookie.getValue();
				}
			}
		}
		
		return null;
	}

	
	public Cookie add(String name, String value, int day) {
		Cookie cookies = new Cookie(name, value);
		cookies.setMaxAge(day * 60 * 60 * 24);
		cookies.setPath("/");
		response.addCookie(cookies);
		
		return cookies;
	}

	
	public void remove(String name) {
		Cookie cookies = new Cookie(name, "");
		cookies.setMaxAge(0 * 60 * 60 * 24);
		cookies.setPath("/");
		
		response.addCookie(cookies);
	}
}
