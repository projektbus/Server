package ProjektBus.Server.config;

import ProjektBus.Server.service.ConfirmationTokenService;
import ProjektBus.Server.service.ConfirmationTokenServiceImpl;
import ProjektBus.Server.service.UserService;
import ProjektBus.Server.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.Properties;

@Configuration
public class AppConfig {

    @Bean
    public UserService getUserService(){
        return new UserServiceImpl();
    }

    @Bean
    public ConfirmationTokenService getConfirmationTokenService() {
        return new ConfirmationTokenServiceImpl();
    }

    @Bean(name="mailSender")
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("projektbus2019@gmail.com");
        mailSender.setPassword("projektpk2019");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        return mailSender;
    }

    @Bean  // Magic entry
    public DispatcherServlet dispatcherServlet() {
        DispatcherServlet ds = new DispatcherServlet();
        ds.setThrowExceptionIfNoHandlerFound(true);
        return ds;
    }
}
