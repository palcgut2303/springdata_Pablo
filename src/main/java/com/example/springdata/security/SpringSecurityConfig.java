package com.example.springdata.security;

import com.example.springdata.security.filter.JwtAuthenticationFilter;
import com.example.springdata.security.filter.JwtValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(auth -> auth
                        //FILTRO SWAGGER
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll() // Permitir acceso a Swagger UI
                        //FILTRO USERS
                        .requestMatchers(HttpMethod.GET,"/api/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/api/users/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/users/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/users/nombre").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/users/apellido").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/users/edad").hasRole("ADMIN")
                        //FILTRO PRODUCTOS
                        .requestMatchers(HttpMethod.GET, "/api/products", "/api/products/{id}").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/products").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/products/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/products/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/products/nombre").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/api/products/categoria").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/api/products/precio").hasAnyRole("ADMIN", "USER")
                        //FILTRO PEDIDOS
                        .requestMatchers(HttpMethod.GET,"/api/pedidos").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/pedidos").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "/api/pedidos/{id}").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.DELETE, "/api/pedidos/{id}").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/api/pedidos/direccion/{direccion}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/pedidos/usuario/{usuarioId}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/pedidos/fecha").hasRole("ADMIN")
                        //FILTRO PEDIDOS_PRODUCTOS
                        .requestMatchers(HttpMethod.GET,"/api/pedidosProductos").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/pedidosProductos").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "/api/pedidosProductos{id}").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.DELETE, "/api/pedidosProductos{id}").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated())
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtValidationFilter(authenticationManager()))
                .csrf(config -> config.disable())
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

}
