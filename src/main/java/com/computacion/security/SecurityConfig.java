package com.computacion.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private LoggingAccessDeniedHandler accessDeniedHandler;

//	@Autowired
//	private MyCustomUserDetailsService userDetailsService;

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.authenticationProvider(authenticationProvider());
//	}

//	@Bean
//	public DaoAuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//		authProvider.setUserDetailsService(userDetailsService);
//		authProvider.setPasswordEncoder(encoder());
//		return authProvider;
//	}
//
//	@Bean
//	public PasswordEncoder encoder() {
//		return new BCryptPasswordEncoder(11);
//	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		
//		httpSecurity.authorizeRequests().anyRequest().permitAll().and().csrf().disable();
		
//		httpSecurity
//		.authorizeRequests()
//		.antMatchers("/").permitAll()
//		.antMatchers(HttpMethod.GET, "/api/topics/**").hasAnyRole("SA")
//		.antMatchers(HttpMethod.PUT, "/api/topics/**").hasAnyRole("SA")
//		.antMatchers(HttpMethod.PATCH, "/api/topics/**").hasAnyRole("SA")
//		.antMatchers(HttpMethod.DELETE, "/api/topics/**").hasAnyRole("SA")
//		.antMatchers(HttpMethod.GET, "/api/games/**").hasAnyRole("SA","A")
//		.antMatchers(HttpMethod.PUT, "/api/games/**").hasAnyRole("SA","A")
//		.antMatchers(HttpMethod.PATCH, "/api/games/**").hasAnyRole("SA","A")
//		.antMatchers(HttpMethod.DELETE, "/api/games/**").hasAnyRole("SA","A")
//		.antMatchers(HttpMethod.GET, "/api/timecontrols/**").hasAnyRole("SA","A")
//		.antMatchers(HttpMethod.PUT, "/api/timecontrols/**").hasAnyRole("SA","A")
//		.antMatchers(HttpMethod.PATCH, "/api/timecontrols/**").hasAnyRole("SA","A")
//		.antMatchers(HttpMethod.DELETE, "/api/timecontrols/**").hasAnyRole("SA","A")
//		.antMatchers(HttpMethod.GET, "/api/stories/**").hasAnyRole("SA","A")
//		.antMatchers(HttpMethod.PUT, "/api/stories/**").hasAnyRole("SA","A")
//		.antMatchers(HttpMethod.PATCH, "/api/stories/**").hasAnyRole("SA","A")
//		.antMatchers(HttpMethod.DELETE, "/api/stories/**").hasAnyRole("SA","A")
//		.antMatchers("/topics/add").hasAnyRole("SA")
//		.antMatchers("/topics/edit/**").hasAnyRole("SA")
//		.anyRequest().authenticated()
//		.and().csrf().disable()
//		.formLogin().loginPage("/login").permitAll().and().logout()
//		.invalidateHttpSession(true).clearAuthentication(true)
//		.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")
//		.permitAll().and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);
//		

		httpSecurity.authorizeRequests().antMatchers("/topics/add").hasAnyRole("SA").antMatchers("/topics/edit/**").hasAnyRole("SA").antMatchers("/api/**").permitAll().anyRequest().authenticated()
		.and().csrf().disable().formLogin().loginPage("/login").permitAll().and().logout()
				.invalidateHttpSession(true).clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")
				.permitAll().and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);
	}
}