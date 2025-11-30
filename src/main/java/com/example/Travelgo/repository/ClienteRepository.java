package com.example.Travelgo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Travelgo.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Método para buscar un cliente por su email (necesario para login )
    Optional<Cliente> findByEmail(String email);
    
    // Método para verificar si un email ya existe
    boolean existsByEmail(String email);
}