package com.marketplace.usuario.security;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    // Spring inyectará automáticamente el CustomUserDetailsService aquí
    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 1. Extraemos el encabezado "Authorization" de la petición
        String authHeader = request.getHeader("Authorization");

        // 2. Si no hay token o no empieza con "Bearer ", dejamos pasar la petición al siguiente filtro
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 3. Limpiamos el texto para quedarnos solo con el token (quitamos "Bearer ")
        String token = authHeader.substring(7);

        // 4. Sacamos el email (username) del token usando el servicio que hicimos antes
        String username = jwtService.extractUsername(token);

        // 5. Si hay un usuario y aún no está autenticado en el sistema (el contexto es nulo)
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Buscamos los detalles del usuario en la base de datos
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // 6. Validamos que el token sea correcto y no haya expirado
            if (jwtService.isTokenValid(token, userDetails.getUsername())) {

                // Creamos el "ticket" de autenticación con sus roles/autoridades
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                // Le añadimos detalles de la petición (como la IP)
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // 7. Seteamos al usuario como "Autenticado" en Spring Security
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // 8. Continuamos con la ejecución de la petición
        filterChain.doFilter(request, response);
    }
}

