package com.securecoding.demo.web.login.security.config;

import com.securecoding.demo.web.login.security.WebLoginAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebLoginSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private WebLoginAuthenticationProvider webLoginAuthenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(webLoginAuthenticationProvider);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public WebLoginAuthenticationEntryPoint customAuthenticationEntryPointBean() {
        return new WebLoginAuthenticationEntryPoint();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
       httpSecurity
               .authorizeRequests()
               .antMatchers("/", "/login**", "/error").permitAll()
               .antMatchers("/**").hasAnyRole("USER", "ADMIN")
               .anyRequest()
               .authenticated()
               .and()
               .formLogin()
               .loginProcessingUrl("/login")
               .defaultSuccessUrl("/home", true)
               .failureUrl("/error")
               .and()
               .logout()
               .logoutUrl("/logout")
               .logoutSuccessUrl("/")
               .and()
               .exceptionHandling()
               .authenticationEntryPoint(customAuthenticationEntryPointBean());
    }
}
