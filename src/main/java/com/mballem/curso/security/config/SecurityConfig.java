package com.mballem.curso.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mballem.curso.security.service.UsuarioService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsuarioService service;
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers("/webjars/**" , "/css/**" , "/image/**" , "/js/**").permitAll()
		.antMatchers("/" , "/home").permitAll()
		
		//Acessos privados Admin
		.antMatchers("/u/**").hasAnyAuthority("ADMIN")
		
		//Acessos privados medicos
		.antMatchers("/medicos/dados").hasAnyAuthority("MEDICO")
		
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login")
		.defaultSuccessUrl("/" , true)
		.failureUrl("/login-error")
		.permitAll()
		.and()
		.logout()
		.logoutSuccessUrl("/")
		.and()
		.exceptionHandling()
		.accessDeniedPage("/acesso-negado");
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(service).passwordEncoder(new BCryptPasswordEncoder());
		//Informa qual a criptografia que deseja
	}

	
}
