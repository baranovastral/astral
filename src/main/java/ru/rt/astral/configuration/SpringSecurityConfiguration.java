package ru.rt.astral.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders
        .AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders
        .HttpSecurity;
import org.springframework.security.config.annotation.web.configuration
        .EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration
        .WebSecurityConfigurerAdapter;
import ru.rt.astral.repository.JDBCUserDAO; 
import ru.rt.astral.service.UserDetailsServiceImplementation;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter{
    
    @Autowired 
    private JDBCUserDAO jDBCUserDAO;
    
    @Autowired
    private UserDetailsServiceImplementation detailsServiceImplementation;
    
    @Autowired
    private AuthSuccessHandler authSuccessHandler;
    
    @Autowired
    private AuthFailureHandler authFailureHandler;
    
    @Autowired
    private RestAuthEntryPoint restAuthEntryPoint;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
            http
                    .exceptionHandling()
                    .authenticationEntryPoint(restAuthEntryPoint)
                    .and()
                    .authorizeRequests()
                    .antMatchers("/").authenticated()
                    .antMatchers("/notes*").authenticated()
                    .antMatchers("/newNote").authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .successHandler(authSuccessHandler)
                    .failureHandler(authFailureHandler)
                    .and()
                    .logout()
                    .deleteCookies("JSESSIONID");
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) 
            throws Exception {
        auth.userDetailsService(detailsServiceImplementation);
    }
        
} 
