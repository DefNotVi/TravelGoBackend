package com.example.Travelgo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Travelgo.dto.PaqueteTuristicoResponse;
import com.example.Travelgo.model.PaqueteTuristico;
import com.example.Travelgo.service.PaqueteTuristicoService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/paquetes")
@SecurityRequirement(name = "Bearer Authentication")
public class PaqueteTuristicoController {

    @Autowired
    private PaqueteTuristicoService service;

    @GetMapping
    public List<PaqueteTuristico> getAll() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public PaqueteTuristicoResponse getById(@PathVariable Long id) { 
        return service.buscarPorIdConDetalle(id); 
    }

    @PostMapping
    public PaqueteTuristico create(@RequestBody PaqueteTuristico paquete) {
        return service.crear(paquete);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.eliminar(id);
    }
}