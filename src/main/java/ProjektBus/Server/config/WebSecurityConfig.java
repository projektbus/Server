package ProjektBus.Server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Bean
//    PasswordEncoder getPasswordEncoder(){
//        return  new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser(User.builder()
//                .username("test")
//                .password(getPasswordEncoder().encode("test"))
//                .roles("ADMIN"));
//        super.configure(auth);
//    }

    //Klasa określa jakie enpointy są zabezpieczone
    //tylko / i /login endpointy będą dostępne bez autoryzacji
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/test2")
                .authenticated()
                .antMatchers("/test3")
                .hasRole("ADMIN")
                .and()
                .addFilter(new JwtFilter(authenticationManager()));
    }
}