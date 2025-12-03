package com.example.Travelgo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Travelgo.model.Reserva;
import com.example.Travelgo.repository.ReservaRepository;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/reservas")
@SecurityRequirement(name = "Bearer Authentication")
public class ReservaController {

    @Autowired
    private ReservaRepository reservaRepo;

    @PostMapping
    public Reserva crearReserva(@RequestBody Reserva reserva) {
        return reservaRepo.save(reserva);
    }
}
