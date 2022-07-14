package pc02674.asmhoanchinh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig  implements WebMvcConfigurer{
	
	@Autowired
	AuthInterceptor auth;
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(auth)
				.addPathPatterns("/kgbBookstore.com/customer/**","/kgbBookstore.com/admin/**");
				
	}
	
	
}
