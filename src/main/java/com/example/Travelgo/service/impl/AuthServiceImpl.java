package com.example.Travelgo.service.impl;

import com.example.Travelgo.model.Cliente;
import com.example.Travelgo.repository.ClienteRepository;
import com.example.Travelgo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente register(Cliente cliente) {
        // 1. Verificar si el email ya existe
        if (clienteRepository.existsByEmail(cliente.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El correo ya está registrado.");
        }
        
        // NOTA: Aquí DEBERÍAS hashear la contraseña antes de guardar.
        // Por simplicidad en este ejemplo, se guarda directo.
        
        // 2. Guardar el cliente
        Cliente nuevoCliente = clienteRepository.save(cliente);
        
        // 3. Eliminar la contraseña antes de devolver el objeto
        nuevoCliente.setPassword(null);
        return nuevoCliente;
    }

    @Override
    public Cliente login(String email, String password) {
        // 1. Buscar el cliente por email
        return clienteRepository.findByEmail(email)
            .map(cliente -> {
                // 2. Comparar la contraseña
                if (cliente.getPassword().equals(password)) {
                    cliente.setPassword(null); // Eliminar la contraseña antes de devolver
                    return cliente; // Login exitoso
                }
                // Contraseña incorrecta
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Correo o contraseña incorrectos.");
            })
            .orElseThrow(() -> // Cliente no encontrado
                new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Correo o contraseña incorrectos.")
            );
    }
}
