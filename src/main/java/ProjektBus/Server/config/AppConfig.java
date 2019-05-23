package ProjektBus.Server.config;

import ProjektBus.Server.service.*;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Properties;

@Configuration
public class AppConfig {

    @Bean
    public UserService getUserService(){
        return new UserServiceImpl();
    }

    @Bean
    public BusStopService getBusStopService(){
        return new BusStopServiceImpl();
    }

    @Bean
    public BusConnectionService getBusConnectionService(){
        return new BusConnectionServiceImpl();
    }

    @Bean
    public BusLineService getBusLineService(){
        return new BusLineServiceImpl();
    }

    @Bean
    public CarrierService getCarrierService(){
        return new CarrierServiceImpl();
    }

    @Bean
    public ConfirmationTokenService getConfirmationTokenService() {
        return new ConfirmationTokenServiceImpl();
    }

    @Bean
    public SettingPasswordTokenService getSettingPasswordTokenService() {
        return new SettingPasswordTokenServiceImpl();
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

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }
}
