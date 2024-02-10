package saraymarcos.ProyectSpringBoot.config;

import org.springframework.beans.factory.annotation.Autowired;
import saraymarcos.ProyectSpringBoot.config.jwt.JwtAuthenticationFilter;
import saraymarcos.ProyectSpringBoot.config.jwt.JwtAuthorizationFilter;
import saraymarcos.ProyectSpringBoot.config.jwt.JwtTokenUtils;
import saraymarcos.ProyectSpringBoot.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    private final JwtTokenUtils tokenUtils;
    private final UserService service;

    @Autowired
    public SecurityConfiguration(JwtTokenUtils tokenUtils, UserService service) {
        this.tokenUtils = tokenUtils;
        this.service = service;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
        AuthenticationManager authManager = authManager(http);
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) -> authorize
                                .requestMatchers("/error/**").permitAll()
                                // login / register
                                .requestMatchers(
                                        mvc.pattern("/connectedReads/users/create"),
                                        mvc.pattern("/connectedReads/users/register"),
                                        mvc.pattern("/connectedReads/users/login"),
                                        mvc.pattern("/connectedReads/libros")
                                        ).permitAll()

                                .requestMatchers(
                                        "/connectedReads/libros/id/{id}",
                                        "/connectedReads/libros/uuid/{uuid}",
                                        "/connectedReads/libros/ISBN/{ISBN}",
                                        "/connectedReads/libros/autor/{autor}",
                                        "/connectedReads/libros/titulo/{titulo}",
                                        "/connectedReads/libros/genero/{genero}",

                                        "/connectedReads/users/update/me",
                                        "/connectedReads/users/update",
                                        "/connectedReads/users/delete/{email}"
                                ).hasAnyRole("USER")

                                .requestMatchers(
                                        "/connectedReads/libros/create",
                                        "/connectedReads/libros/update/{id}",
                                        "/connectedReads/libros/delete/{id}"
                                ).hasAnyRole("ADMIN")

                                .anyRequest().authenticated()
                        //.anyRequest().permitAll()
                )
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilter(jwtAuthorizationFilter(authManager))
                .sessionManagement(customizer -> customizer
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        return http.build();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter(AuthenticationManager manager) {
        return new JwtAuthorizationFilter(tokenUtils, service, manager);
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(service);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(service);
        return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    }
}