package com.example.Travelgo.repository;

import com.example.Travelgo.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Método para buscar un cliente por su email (necesario para login y registro)
    Optional<Cliente> findByEmail(String email);
    
    // Método para verificar si un email ya existe
    boolean existsByEmail(String email);
}