package com.camelot.p44camelotbmw.config;


import com.camelot.p44camelotbmw.userdetails.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.HttpStatusRequestRejectedHandler;
import org.springframework.security.web.firewall.RequestRejectedHandler;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;

import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private UsersService usersService;
    
    
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usersService).passwordEncoder(encoder());
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().anyRequest().authenticated();
        http.headers().httpStrictTransportSecurity().includeSubDomains(true).preload(true).maxAgeInSeconds(31536000).requestMatcher(AnyRequestMatcher.INSTANCE);
        http.requiresChannel().antMatchers("/v1/sendEvent").requiresSecure();
        http.requiresChannel().antMatchers("/v1/createShipment").requiresSecure();
        http.requiresChannel().antMatchers("/v1/auth/token").requiresSecure();
    }
    
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/v1/health").antMatchers("/error")
        ;
    }
    
    @Bean
    public HttpFirewall configureFirewall() {
        StrictHttpFirewall strictHttpFirewall = new StrictHttpFirewall();
        strictHttpFirewall.setAllowBackSlash(true);
        strictHttpFirewall.setAllowedHttpMethods(List.of("POST", "GET"));
        return strictHttpFirewall;
    }
    
    @Bean
    public RequestRejectedHandler requestRejectedHandler() {
        return new HttpStatusRequestRejectedHandler();
    }
    
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
}