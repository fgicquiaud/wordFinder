package fr.tosmu.api.tosmu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import fr.tosmu.api.tosmu.interceptor.LogRestInterceptor;

@Configuration
public class InterceptorsConfig implements WebMvcConfigurer {

	@Autowired
	private LogRestInterceptor logRestInterceptor;
	
	
	@Override
    public void addInterceptors(@NonNull InterceptorRegistry registry){
        registry.addInterceptor(logRestInterceptor);
    }
}
