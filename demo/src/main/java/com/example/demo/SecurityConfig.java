package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import com.example.BarAuthenticationEntryPoint;
import com.example.FooAuthenticationEntryPoint;

@Configuration
public class SecurityConfig {

  @Bean
  @Order(1)
  SecurityFilterChain fooSecurityFilterChain(HttpSecurity http) throws Exception {

    http.securityMatcher("/foo", "/login/oauth2/code/foo", "/oauth2/authorization/foo")
        .authorizeHttpRequests(
            a -> a.requestMatchers("/foo").authenticated().anyRequest().permitAll())
        .oauth2Login(Customizer.withDefaults())
        .securityContext(sec -> sec.securityContextRepository(this.fooSecurityContextRepository()))
        .exceptionHandling(ex -> ex.authenticationEntryPoint(new FooAuthenticationEntryPoint()));
    return http.build();
  }

  @Bean
  @Order(2)
  SecurityFilterChain barSecurityFilterChain(HttpSecurity http) throws Exception {

    http.securityMatcher("/bar", "/login/oauth2/code/bar", "/oauth2/authorization/bar")
        .authorizeHttpRequests(
            a -> a.requestMatchers("/bar").authenticated().anyRequest().permitAll())
        .oauth2Login(Customizer.withDefaults())
        .securityContext(sec -> sec.securityContextRepository(this.barSecurityContextRepository()))
        .exceptionHandling(ex -> ex.authenticationEntryPoint(new BarAuthenticationEntryPoint()));
    return http.build();
  }

  HttpSessionSecurityContextRepository fooSecurityContextRepository() {
    var securityContextRepository = new HttpSessionSecurityContextRepository();
    securityContextRepository.setSpringSecurityContextKey("SPRING_SECURITY_CONTEXT_FOO");
    return securityContextRepository;
  }

  HttpSessionSecurityContextRepository barSecurityContextRepository() {
    var securityContextRepository = new HttpSessionSecurityContextRepository();
    securityContextRepository.setSpringSecurityContextKey("SPRING_SECURITY_CONTEXT_BAR");
    return securityContextRepository;
  }
}
