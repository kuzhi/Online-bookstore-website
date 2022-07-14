package pc02674.asmhoanchinh.services;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadService {

	@Autowired
	ServletContext app;
	public String save(MultipartFile file, String path) {
		File dir = new File( app.getRealPath(path));
		
		if(!dir.exists()) {
			dir.mkdirs();
		}
		String fn = System.currentTimeMillis() + file.getOriginalFilename();
		String filename = Integer.toHexString(fn.hashCode()) + fn.substring(fn.lastIndexOf("."));
		File saveFile = new File(dir, filename);
//		File saveFile = new File(dir,file.getOriginalFilename());
		
		try {
			file.transferTo(saveFile);
			return saveFile.getName();
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
