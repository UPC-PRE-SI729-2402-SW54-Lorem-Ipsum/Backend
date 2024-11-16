package com.loremipsum.lawconnectplatform.iam.infrastructure.authorization.sfs.configurations;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import com.loremipsum.lawconnectplatform.iam.infrastructure.authorization.sfs.pipeline.BearerAuthorizationRequestFilter;
import com.loremipsum.lawconnectplatform.iam.infrastructure.hashing.bcrypt.BCryptHashingService;
import com.loremipsum.lawconnectplatform.iam.infrastructure.tokens.jwt.BearerTokenService;

import java.util.List;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfiguration {
    private final UserDetailsService userDetailsService;

    private final BearerTokenService tokenService;

    private final BCryptHashingService hashingService;

    private final AuthenticationEntryPoint unauthorizedRequestHandler;


    public WebSecurityConfiguration(@Qualifier("defaultUserDetailsService") UserDetailsService userDetailsService,
                                    BearerTokenService tokenService,
                                    BCryptHashingService hashingService,
                                    AuthenticationEntryPoint unauthorizedRequestHandler) {
        this.userDetailsService = userDetailsService;
        this.tokenService = tokenService;
        this.hashingService = hashingService;
        this.unauthorizedRequestHandler = unauthorizedRequestHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors(configurer -> configurer.configurationSource(_configurer -> {
            var cors = new CorsConfiguration();
            cors.setAllowedOrigins(List.of("*"));
            cors.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
            cors.setAllowedHeaders(List.of("*"));
            return cors;
        }));

        http.csrf(configurer -> configurer.disable())

                .exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(unauthorizedRequestHandler))
                .sessionManagement(customizer ->
                        customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests.requestMatchers(
                                "/api/v1/authentication/**",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/swagger-resources/**",
                                "/webjars/**").permitAll().anyRequest().authenticated());

        http.authenticationProvider(authenticationProvider());

        http.addFilterBefore(authorizationRequestFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public BearerAuthorizationRequestFilter authorizationRequestFilter() {
        return new BearerAuthorizationRequestFilter(tokenService, userDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return hashingService;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        var authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(hashingService);
        return authenticationProvider;
    }
}
