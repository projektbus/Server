package ProjektBus.Server.config;

import ProjektBus.Server.service.UserService;
import ProjektBus.Server.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AppConfig {
    @Bean
    public UserService getUserService(){
        return new UserServiceImpl();
    }
}
