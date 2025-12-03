package com.example.Travelgo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired; // Asumiendo que tienes un ItinerarioRepository
import org.springframework.stereotype.Service;

import com.example.Travelgo.model.Itinerario;
import com.example.Travelgo.repository.ItinerarioRepository;

@Service
public class ItinerarioService {

    @Autowired
    private ItinerarioRepository itinerarioRepository; // Inyecta el repositorio

    // Método para crear/guardar un nuevo itinerario
    public Itinerario guardarItinerario(Itinerario itinerario) {
        return itinerarioRepository.save(itinerario);
    }

    // Método para obtener todos los itinerarios
    public List<Itinerario> obtenerTodos() {
        return itinerarioRepository.findAll();
    }
    
}