package com.example.Travelgo.service;

import com.example.Travelgo.model.Cliente;

public interface AuthService {
    
    /**
     * Intenta registrar un nuevo cliente.
     * @param cliente El objeto Cliente con nombre, email y password.
     * @return El cliente guardado (sin la contraseña).
     * @throws RuntimeException Si el email ya está registrado.
     */
    Cliente register(Cliente cliente);

    /**
     * Intenta iniciar sesión con el email y la contraseña.
     * @param email El correo del cliente.
     * @param password La contraseña del cliente.
     * @return El cliente si el login es exitoso (sin la contraseña).
     * @throws RuntimeException Si las credenciales son incorrectas.
     */
    Cliente login(String email, String password);
}