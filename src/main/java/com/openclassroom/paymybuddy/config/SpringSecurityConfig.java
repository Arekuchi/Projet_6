package com.openclassroom.paymybuddy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeRequests().antMatchers("/actuator*").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/actuator/**").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/h2-console/**/**").permitAll();
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.GET, "/*").permitAll();
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.GET, "/**/**").permitAll();
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.POST, "/*").permitAll().anyRequest().authenticated().and().httpBasic();
    }

}
