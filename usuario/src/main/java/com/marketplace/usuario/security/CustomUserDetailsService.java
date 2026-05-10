package com.marketplace.usuario.security;
import com.marketplace.usuario.model.Usuario;
import com.marketplace.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // 1. Buscamos al usuario en la base de datos por email
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + email));

        // 2. Construimos el objeto User de Spring Security usando los datos
        return org.springframework.security.core.userdetails.User.withUsername(usuario.getEmail())
                .password(usuario.getPassword()) // Asegúrate que tu modelo Usuario tenga el campo password
                .disabled(!usuario.isActivo())   // Si activo es false, la cuenta queda deshabilitada
                .authorities(List.of(new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toUpperCase())))
                .build();
    }
}

