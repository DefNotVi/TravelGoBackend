package com.example.Travelgo.controller;

import com.example.Travelgo.model.Cliente;
import com.example.Travelgo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes") // Usamos /api/clientes como en el frontend
public class AuthController {

    // Cambiamos la inyección del Repository por el AuthService
    @Autowired
    private AuthService authService;

    // Endpoint de Registro: POST /api/clientes/registro
    @PostMapping("/registro")
    public ResponseEntity<Cliente> registrarCliente(@RequestBody Cliente cliente) {
        // Delegamos la lógica al servicio
        Cliente nuevoCliente = authService.register(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
    }
    
    // Endpoint de Login: POST /api/clientes/login
    @PostMapping("/login")
    public ResponseEntity<Cliente> iniciarSesion(@RequestBody Cliente loginRequest) {
        // Delegamos la lógica al servicio
        Cliente clienteLogueado = authService.login(
            loginRequest.getEmail(), 
            loginRequest.getPassword()
        );
        return ResponseEntity.ok(clienteLogueado);
    }
}