package ProjektBus.Server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login")
                .authenticated()
                .and()
                .regexMatcher("[/]([m-z]|[a-k]).*").authorizeRequests()
                .and()
                .addFilter(new JwtFilter(authenticationManager()));
    }
}