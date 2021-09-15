package com.pk.engineering.publisher.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.pk.engineering.publisher.exception.AuthorizationHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConf extends WebSecurityConfigurerAdapter {

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/**").permitAll().and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint());
    }

	@Bean
	AuthorizationHandler authenticationEntryPoint() {
		return new AuthorizationHandler();
	}

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}


