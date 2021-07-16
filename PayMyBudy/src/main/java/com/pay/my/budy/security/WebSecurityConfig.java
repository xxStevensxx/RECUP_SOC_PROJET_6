package com.pay.my.budy.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import com.pay.my.budy.services.DetailsServices;



@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DetailsServices userDetailsService;


	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth
			.inMemoryAuthentication()
			.withUser("user@user.fr").password(passwordEncoder().encode("user")).roles("user")
			.and()
			.withUser("admin@admin.fr").password(passwordEncoder().encode("admin")).roles("admin", "user");		
				
	}

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf()
        .disable()
        .authorizeRequests()
	    .antMatchers("/transfert", "/profil", "/contact").hasRole("user")
		.antMatchers("/resources/**", "/signUp", "/login", "/logout").anonymous()
		.anyRequest().permitAll()
		.and()
        .formLogin();
//        .loginPage("/login");
//        .loginProcessingUrl("/login")
//        .defaultSuccessUrl("/signUp",true)
//        .failureUrl("/login")
//        .permitAll()
//        .and()
//        .logout()
//        .logoutUrl("/logout");
        http.exceptionHandling().accessDeniedPage("/403");
		
		} 
    

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();

	}
	
	public void cryptePswrd(String password) {
		
		passwordEncoder().encode(password);
	}


}
