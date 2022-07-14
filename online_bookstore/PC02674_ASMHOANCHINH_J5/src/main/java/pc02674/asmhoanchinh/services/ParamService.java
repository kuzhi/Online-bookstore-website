package pc02674.asmhoanchinh.services;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ParamService {
	@Autowired
	HttpServletRequest request;

	@Autowired
	ServletContext app;
	
	public String getString(String name, String defaultValue) {
		String rs = request.getParameter(name);
		if (rs != null) {
			return rs;
		} else {
			return defaultValue;
		}
	}

	
	public int getInt(String name, int defaultValue){
		String rs = request.getParameter(name);
		if (rs != null) {
			return Integer.parseInt(rs);
		} else {
			return defaultValue;
		}
	}
	
	public double getDouble(String name, double defaultValue){
		String rs = request.getParameter(name);
		if (rs != null) {
			return Double.parseDouble(rs);
		} else {
			return defaultValue;
		}
	}
	
	public boolean getBoolean(String name, boolean defaultValue){
		String rs = request.getParameter(name);
		if (rs != null) {
			return Boolean.parseBoolean(rs);
		} else {
			return defaultValue;
		}
	}
	
	public Date getDate(String name, String pattern) 
			throws ParseException{
		String rs = request.getParameter(name);
		if (rs != null) {
			SimpleDateFormat sm = new SimpleDateFormat(pattern);
			Date date = sm.parse(rs);
			return date;
		} else {
			return null;
		}
	}
	
	
	public File save(MultipartFile file, String path) {
		File dir = new File( app.getRealPath(path));
		
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		File saveFile = new File(dir,file.getOriginalFilename());
		
		try {
			file.transferTo(saveFile);
			return saveFile;
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
