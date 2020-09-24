package org.wucc.backservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.wucc.backservice.security.AuthTokenFilter;
import org.wucc.backservice.service.impl.UserDetailsServiceImpl;

/**
 * Created by foxi.chen on 25/07/20.
 * a java configuration file representing all spring security components
 * @author foxi.chen
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    final UserDetailsServiceImpl userDetailsService;

    final AuthenticationEntryPoint authenticationEntryPoint;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService,
                          AuthenticationEntryPoint authenticationEntryPoint) {
        this.userDetailsService = userDetailsService;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Override
    public void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

     @Override
     public void configure(final HttpSecurity httpSecurity) throws Exception {
         httpSecurity.cors().and().csrf().disable()
             .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
             .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
             .authorizeRequests().antMatchers("/demo/**").permitAll().and()
             .authorizeRequests().antMatchers("/api/auth/**").permitAll().and()
             .authorizeRequests().antMatchers("/api/open/**").permitAll().and()
             .authorizeRequests().antMatchers("/api/comment/getComments").permitAll()
             .anyRequest().authenticated();
         httpSecurity.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
     }

}
