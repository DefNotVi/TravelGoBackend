package com.example.Travelgo.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy; 
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.Travelgo.security.JwtAuthenticationFilter;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    
    // Mantenemos @Lazy aquí para asegurar que el ciclo anterior esté roto.
    public SecurityConfig(@Lazy JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http
        .csrf(csrf -> csrf.disable())
        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
        .headers(headers -> headers
            .frameOptions(frame -> frame.sameOrigin()) 
        )
        .sessionManagement(session ->
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        
        .authorizeHttpRequests(auth -> auth
            // 🔑 1. DEFINICIÓN DE RUTAS PÚBLICAS (Login y Docs)
            .requestMatchers(
                // Login endpoint
                "/api/v1/auth/login", 
                // Documentación y H2 Console
                "/v3/api-docs/**",
                "/swagger-ui.html",
                "/swagger-ui/**",
                "/doc/swagger-ui/**",
                "/h2-console/**"
            ).permitAll() // Estas rutas permiten el acceso sin autenticación
            
            // 🔒 2. DEFINICIÓN DE RUTAS PRIVADAS (Todas las demás)
            .anyRequest().authenticated() // Cualquier otra solicitud requiere autenticación
        )
        .exceptionHandling(handling -> handling
            // Asegura que las solicitudes no autenticadas (que intenten acceder a rutas protegidas) 
            // no pasen silenciosamente, aunque esto no debería afectar a .permitAll()
            .authenticationEntryPoint((request, response, authException) -> 
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized"))
        )
        // ➡️ 3. Agregar el filtro de JWT *después* de definir las reglas de autorización
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
}

    @Bean
    public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
        AuthenticationConfiguration configuration) throws Exception {
    return configuration.getAuthenticationManager();
    }
    
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOriginPatterns(List.of("http://localhost:3000"));

        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        config.setAllowCredentials(true); 

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

}