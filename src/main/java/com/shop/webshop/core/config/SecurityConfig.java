package com.shop.webshop.core.config;

import com.shop.webshop.core.service.impl.CustomUserServiceDetail;
import com.shop.webshop.web.security.CustomSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Autowired
    private CustomSuccessHandler customSuccessHandler;

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserServiceDetail();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasRole("USER")
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                .loginProcessingUrl("/login")
                .successHandler(customSuccessHandler)
                .failureUrl("/login?incorrect_account")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/login?accessDenied")
                .and()
                .sessionManagement().invalidSessionUrl("/login?invalid_session")
                .and()
                .logout().deleteCookies("JSESSIONID", "remember-me")
                .and()
                .rememberMe().rememberMeParameter("remember-me").key("remember-key").tokenValiditySeconds(1 * 24 * 60 * 60);
        http.csrf().disable();

    }
}
