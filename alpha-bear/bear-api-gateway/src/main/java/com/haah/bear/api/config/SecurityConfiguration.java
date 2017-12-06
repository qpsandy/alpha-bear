/*########################################################################
 *#                                                                      #
 *#                      Copyright (c) 2017 by                           #
 *#          Shanghai Stock Exchange (SSE), Shanghai, China              #
 *#                       All rights reserved.                           #
 *#                                                                      #
 *########################################################################
*/
package com.haah.bear.api.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.haah.bear.api.filter.JwtAuthenticationTokenFilter;
import com.haah.bear.api.filter.JwtLoginFilter;
import com.haah.bear.api.filter.SkipPathRequestMatcher;
import com.haah.bear.api.provider.BossAuthenticationProvider;
import com.haah.bear.api.provider.JwtAuthenticationProvider;
import com.haah.bear.core.constants.ConstantsUtils;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private BossAuthenticationProvider bossAuthenticationProvider;

	@Autowired
	private JwtAuthenticationProvider jwtAuthenticationProvider;

	@Autowired
	private BearAuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	private CustomLogoutHandler customLogoutHandler;
	
	@Autowired
	public void globalUserDetails(AuthenticationManagerBuilder auth){
		auth.authenticationProvider(bossAuthenticationProvider);
	}

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(bossAuthenticationProvider);
        auth.authenticationProvider(jwtAuthenticationProvider);
    }

    @Override
	protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()//使用token ，这里就不需要csrf了
                //基于token，也不需要session了
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy((SessionCreationPolicy.STATELESS))
                .and().authorizeRequests()
				.antMatchers(ConstantsUtils.SKIP_URL).permitAll()//这两个路径为所有人放开
				//前面没有匹配上的请求，全部需要认证
                .anyRequest().authenticated()
                .and().logout().logoutUrl("/logout").logoutSuccessHandler(customLogoutHandler)
                .and().rememberMe()
                ;
        http.headers().cacheControl();
        http
				.addFilterBefore(new JwtLoginFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(authenticationTokenFilterBean(),UsernamePasswordAuthenticationFilter.class)
        ;


	}

    @Bean
	public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception{
		List<String> pathToSkip = Arrays.asList(ConstantsUtils.SKIP_URL);
		SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathToSkip);
        return new JwtAuthenticationTokenFilter(matcher, authenticationManager());
    }

}
