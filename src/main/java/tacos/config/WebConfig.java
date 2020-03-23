package tacos.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	private static final Logger log = LoggerFactory.getLogger(WebConfig.class);
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		log.info("--------------- HOME PAGE -----------------");
		log.debug("---------CO IN DUOC LEVEL DEBUG KHONG------");
		registry.addViewController("/").setViewName("home");
	}
}
