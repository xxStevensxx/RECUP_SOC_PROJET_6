package com.pay.my.budy.config;

import javax.sql.DataSource;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import com.pay.my.budy.model.User;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.pay.my.budy.model.User;
import com.pay.my.budy.services.DetailsServices;

import com.pay.my.budy.services.UserServices;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DetailsServices userDetailsService;


	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth
			.inMemoryAuthentication()
			.withUser("user@user.fr").password("user").roles("user")
			.and()
			.withUser("admin@admin.fr").password("admin").roles("admin", "user");		
				
	}

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf()
        .disable()
        .authorizeRequests()
		.antMatchers("/resources/**", "/signUp", "/login", "/logout").permitAll()
	    .antMatchers("/transfert", "/profil", "/contact").hasAnyRole("user")
		.anyRequest().permitAll()
		.and()
        .formLogin()
        .loginPage("/login")
        .permitAll()
        .and()
        .logout();
        http.exceptionHandling().accessDeniedPage("/403");
		
		} 
    

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();

	}
	
	public void cryptePswrd(User user) {
		
		user.setPassword(passwordEncoder().encode(user.getPassword()));
	}


}
