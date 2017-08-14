package web.business.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import core.service.invoke.ServiceUrls;
import service.auth.shared.service.AuthServiceConst;

@Component
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private Environment env;

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        // get all service config
        ServiceUrls.setServiceUrl(AuthServiceConst.SERVICE_CONFIG_NAME, env.getProperty(AuthServiceConst.SERVICE_CONFIG_NAME));
    }
}
