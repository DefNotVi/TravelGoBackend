package com.example.Travelgo.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        String token = null;
        //EL EMAIL ES EL USERNAME, LO CAMBIÉ PORQUE COMO YA ESTABAMOS USANDO EMAIL PARA LOGIN, ME PARECE MÁS CLARO
        String email = null;

        if (StringUtils.hasText(header) && header.startsWith("Bearer ")) {
            token = header.substring(7);
            try {
                email = jwtService.extractUsername(token);
            } catch (Exception e) {
                // Token inválido o expirado: dejamos seguir sin auth
            }
        }

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails =
                    userDetailsService.loadUserByUsername(email);

            if (jwtService.isTokenValid(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }


        //debug

    /*if (StringUtils.hasText(header) && header.startsWith("Bearer ")) {
        token = header.substring(7);
        System.out.println(">>> DEBUG JWT: Intentando validar token."); // 👈 LOG

        try {
            email = jwtService.extractUsername(token);
            System.out.println(">>> DEBUG JWT: Username extraído: " + email);
        } catch (Exception e) {
            System.err.println("!!! ERROR JWT: Token falló en extracción/validación. Razón: " + e.getMessage()); // 👈 LOG
            // Dejar que siga (lo que lleva al 401)
        }
    }*/

        filterChain.doFilter(request, response);
    }
}
