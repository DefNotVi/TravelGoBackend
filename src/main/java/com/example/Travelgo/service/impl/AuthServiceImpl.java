package com.example.Travelgo.service.impl;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Travelgo.model.Cliente;
import com.example.Travelgo.repository.ClienteRepository;

import lombok.RequiredArgsConstructor;

// Ahora inyectamos ClienteRepository porque UserDetailsService volverá a usar la base de datos.
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements UserDetailsService { 

    // Inyectamos el repositorio para buscar clientes en la DB
    private final ClienteRepository clienteRepository; 

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // La lógica vuelve a buscar el usuario en la base de datos.
        
        Cliente cliente = clienteRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        return new User(
                cliente.getEmail(),
                cliente.getPassword(),
                List.of(new SimpleGrantedAuthority(cliente.getRole().name()))
        );
    }
}