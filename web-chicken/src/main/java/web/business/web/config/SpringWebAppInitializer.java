package web.business.web.config;

import core.web.config.BaseAnnotationConfigDispatcherServletInitializer;
import web.business.config.AppConfig;

public class SpringWebAppInitializer extends BaseAnnotationConfigDispatcherServletInitializer {
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { AppConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebMvcConfig.class };
	}

}