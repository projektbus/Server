package ProjektBus.Server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //Klasa określa jakie enpointy są zabezpieczone
    //tylko / i /login endpointy będą dostępne bez autoryzacji
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/test2")
                .authenticated()
                .antMatchers("/test3")
                .hasRole("Admin");
    }
}