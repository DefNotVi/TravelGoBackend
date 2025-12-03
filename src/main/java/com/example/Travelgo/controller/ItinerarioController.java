package com.example.Travelgo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Travelgo.model.Itinerario;
import com.example.Travelgo.service.ItinerarioService;

@RestController
@RequestMapping("/api/itinerarios")
public class ItinerarioController {

    @Autowired
    private ItinerarioService itinerarioService;

    //  Endpoint para CREAR un nuevo itinerario (POST)
    @PostMapping
    public ResponseEntity<Itinerario> crearItinerario(@RequestBody Itinerario itinerario) {
        // Nota: Asegúrate de que el body del JSON incluya el ID del paquete al que pertenece.
        Itinerario nuevoItinerario = itinerarioService.guardarItinerario(itinerario);
        return new ResponseEntity<>(nuevoItinerario, HttpStatus.CREATED);
    }

    //  Endpoint para LISTAR todos los itinerarios (GET)
    @GetMapping
    public ResponseEntity<List<Itinerario>> obtenerTodosItinerarios() {
        List<Itinerario> itinerarios = itinerarioService.obtenerTodos();
        return ResponseEntity.ok(itinerarios);
    }


}
