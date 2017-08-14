package service.auth.web.config;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import core.service.config.BaseServiceMvcConfig;
import service.auth.shared.service.AuthServiceConst;

@Configuration
@ComponentScan(basePackages = { "service.auth.web" })
@EnableWebMvc
public class WebMvcConfig extends BaseServiceMvcConfig {

	@Override
	protected void addByPassAuthorization(List<String> bypasses) {
		bypasses.add(AuthServiceConst.AUTH_SERVICE + AuthServiceConst.LOGIN);
		bypasses.add(AuthServiceConst.AUTH_SERVICE + AuthServiceConst.LOGOUT);
	}
}