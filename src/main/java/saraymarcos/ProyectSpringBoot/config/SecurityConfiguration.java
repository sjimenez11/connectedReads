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
                                    // Users
                                        // POST
                                        mvc.pattern("/connectedReads/users/create"),
                                        mvc.pattern("/connectedReads/users/register"),
                                        mvc.pattern("/connectedReads/users/login")
                                        ).permitAll()

                                .requestMatchers(
                                    // Users
                                        // PUT and DELETE
                                        "/connectedReads/users/update/me",
                                        "/connectedReads/users/update",
                                        "/connectedReads/users/delete/{email}",

                                    // Books
                                        // GET
                                        "/connectedReads/books",
                                        "/connectedReads/books/id/{id}",
                                        "/connectedReads/books/isbn/{isbn}",
                                        "/connectedReads/books/author/{author}",
                                        "/connectedReads/books/title/{title}",
                                        "/connectedReads/books/genre/{genre}",
                                        // POST, PUT and DELETE
                                        //"/connectedReads/books/create",
                                        //"/connectedReads/books/update/{id}",
                                        //"/connectedReads/books/delete/{id}"

                                    // Reading Groups
                                        // GET
                                        "/connectedReads/readingGroups",
                                        "/connectedReads/readingGroups/id/{id}",
                                        "/connectedReads/readingGroups/name/{name}"
                                        // POST, PUT and DELETE
//                                        "/connectedReads/readingGroups/create",
//                                        "/connectedReads/readingGroups/update/{id}",
//                                        "/connectedReads/readingGroups/delete/{id}"


                                        ).hasAnyRole("USER", "ADMIN")

                                .requestMatchers(
                                    // Users
                                        // GET
                                        "/connectedReads/users",
                                        "/connectedReads/users/id/{id}",
                                        "/connectedReads/users/email/{email}",

                                    // Books
                                        // POST, PUT and DELETE
                                        "/connectedReads/books/create",
                                        "/connectedReads/books/update/{id}",
                                        "/connectedReads/books/delete/{id}",


                                    //ReadingBooks
                                        // POST, PUT and DELETE
                                        "/connectedReads/readingGroups/create",
                                        "/connectedReads/readingGroups/update/{id}",
                                        "/connectedReads/readingGroups/delete/{id}"
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