package io.wisoft.core.accounts.config;

import io.wisoft.core.accounts.security.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class AccountsSecurityConfig extends WebSecurityConfigurerAdapter {

  @Value("${spring.security.origin}")
  private String ALLOWED_ORIGIN;
  private static final String[] PERMIT_ADDRESS =
      {"/api/accounts/login", "/api/accounts/signup", "/api/accounts/signup/**", "/api/hashtags"};

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authenticationProvider());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .antMatcher("/api/**")
        .authorizeRequests()
        .antMatchers(PERMIT_ADDRESS).permitAll()
        .anyRequest().authenticated()
        .and()
        .addFilterBefore(memberLoginProcessFilter(), UsernamePasswordAuthenticationFilter.class);

    // TODO 추후에 도입
    http.csrf().disable();

    http.cors();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.addAllowedOrigin(ALLOWED_ORIGIN);
    configuration.addAllowedHeader("*");
    configuration.addAllowedMethod("*");
    configuration.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);

    return source;
  }

  @Bean
  public MemberLoginProcessFilter memberLoginProcessFilter() throws Exception {
    MemberLoginProcessFilter memberLoginProcessFilter = new MemberLoginProcessFilter();
    memberLoginProcessFilter.setAuthenticationManager(authenticationManagerBean());
    memberLoginProcessFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
    memberLoginProcessFilter.setAuthenticationFailureHandler(authenticationFailureHandler());
    return memberLoginProcessFilter;
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    return new MemberAuthenticationProvider();
  }

  @Bean
  public AuthenticationSuccessHandler authenticationSuccessHandler() {
    return new MemberAuthenticationSuccessHandler();
  }

  @Bean
  public AuthenticationFailureHandler authenticationFailureHandler() {
    return new MemberAuthenticationFailureHandler();
  }

  @Bean
  public AccessDeniedHandler accessDeniedHandler() {
    return new MemberAccessDeniedHandler();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

}
