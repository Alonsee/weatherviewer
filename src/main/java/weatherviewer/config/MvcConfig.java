package weatherviewer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer{

	//path for js and css
	@Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		 		registry.addResourceHandler("/**")
                        .addResourceLocations("classpath:/static/");
	    }

}
