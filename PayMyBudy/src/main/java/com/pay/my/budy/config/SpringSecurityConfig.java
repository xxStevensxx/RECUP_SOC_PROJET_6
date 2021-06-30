package com.pay.my.budy.config;

<<<<<<< Updated upstream
import javax.sql.DataSource;
=======

>>>>>>> Stashed changes

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
<<<<<<< Updated upstream
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.pay.my.budy.model.User;
=======
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.pay.my.budy.model.User;
import com.pay.my.budy.services.DetailsServices;
>>>>>>> Stashed changes


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
<<<<<<< Updated upstream
	UserDetailsService userDetailsService;
	
//	@Autowired
//    private DataSource dataSource;
=======
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

>>>>>>> Stashed changes

	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();

	}
	
	public void cryptePswrd(User user) {
		
		user.setPassword(passwordEncoder().encode(user.getPassword()));
	}

	
	
	@Bean
	DaoAuthenticationProvider daoAuthProvider() {
		
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userDetailsService);
		auth.setPasswordEncoder(passwordEncoder());
			return auth;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/resources/**").permitAll()
		.anyRequest().permitAll()
		.and()
		.formLogin()
		.loginPage("/signUp")
		.loginProcessingUrl("signUp")
		.permitAll()
		.and()
		.formLogin()
		.loginPage("/login")
		.loginProcessingUrl("login")
		.permitAll()
		.and()
		.logout()
		.logoutUrl("/logout")
		.permitAll()
		.deleteCookies("JSESSIONID")
		.invalidateHttpSession(true)
		.clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/login?logout")
		.permitAll();
		
		
		}

}
