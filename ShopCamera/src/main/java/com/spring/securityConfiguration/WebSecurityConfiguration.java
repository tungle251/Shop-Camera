package com.spring.securityConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/admin/login").permitAll().antMatchers(HttpMethod.GET, "/**/*.*")
				.permitAll().antMatchers("/CameraGiamSat/**").permitAll().antMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().authenticated()
				// .antMatchers("/admin/index").authenticated()
				.antMatchers("/admin/**").authenticated().and().formLogin().loginProcessingUrl("/admin/login")
				.loginPage("/admin/login").usernameParameter("email").passwordParameter("password")
				.failureUrl("/admin/login?error").defaultSuccessUrl("/admin/index").and().logout() // logout
																									// configuration
				.logoutUrl("/logout").logoutSuccessUrl("/admin/login");
		http.csrf().disable();

	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/**/*.*");

	}
}
