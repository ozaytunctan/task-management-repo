package com.tr.task.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
/**
 * Dao Auth
 * @author ozaytunctan
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	
	@Autowired
	private UserDetailsService userDetailsService;
	
	
//	@Bean
//	public RequestBodyReaderAuthenticationFilter usernamePasswordAuthFilter() {
//		return new RequestBodyReaderAuthenticationFilter();
//	}
	
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	/**
	 * Veritabanındaki user ile kullanıcı doğrulama
	 * @return
	 */
	@Bean()
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(encoder());
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		return daoAuthenticationProvider;
	}
	
//	@Override
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		return super.authenticationManagerBean();
//	}
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.authenticationProvider(daoAuthenticationProvider());
//	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		           .authorizeRequests()
		          .antMatchers("/api/v1/**")
		          .permitAll()
		          .anyRequest()
		          .authenticated()
				  .and()
				  .httpBasic();
		
//		http.csrf().disable()
//		.sessionManagement()
//		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//		.and()
//		.authorizeRequests()
//		.antMatchers("/api/v1/auth/sigIn")
//		.permitAll().anyRequest().authenticated();
//		
//		http.addFilterBefore(usernamePasswordAuthFilter(),UsernamePasswordAuthenticationFilter.class);
//	

	}
	
	
	@Bean
	public CorsFilter corsFilter() {

		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOriginPatterns(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
		configuration.setAllowedHeaders(
				Arrays.asList("X-AUTH-TOKEN", "Cache-Control", "Content-Type", "Cookie", "Set-Cookie", "HARDWARE_CPU"));
		configuration.setMaxAge(Long.valueOf(60 * 60 * 24)); // cache option request for 24 hour
		configuration.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return new CorsFilter(source);
	}

	
	
	
}