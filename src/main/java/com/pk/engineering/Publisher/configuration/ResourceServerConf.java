package com.pk.engineering.Publisher.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import com.pk.engineering.Publisher.exception.AuthorizationHandler;

//@Configuration
//@EnableResourceServer
//public class ResourceServerConf extends WebSecurityConfigurerAdapter  {
//  @Override
//  public void configure(HttpSecurity http) throws Exception {
//      http
//          .authorizeRequests()
//          .antMatchers("/").authenticated();
//  }
//  @Bean
//  @Override
//  public AuthenticationManager authenticationManagerBean() throws Exception {
//    return super.authenticationManagerBean();
//  }
//
//  @Bean
//  public BCryptPasswordEncoder passwordEncoder() {
//    return new BCryptPasswordEncoder();
//  }
//}	

@Configuration
@EnableResourceServer
public class ResourceServerConf extends ResourceServerConfigurerAdapter {

	private static final String RESOURCE_ID = "resource_id";

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(RESOURCE_ID).stateless(false);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
        http.
                anonymous().disable()
                .authorizeRequests()
                .antMatchers("/customer/**").permitAll().and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint());
	}

	@Bean
	AuthorizationHandler authenticationEntryPoint() {
		return new AuthorizationHandler();
	}

}
