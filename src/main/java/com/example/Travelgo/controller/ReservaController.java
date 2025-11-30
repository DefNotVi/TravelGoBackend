package com.example.Travelgo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.Travelgo.model.Reserva;
import com.example.Travelgo.repository.ReservaRepository;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaRepository reservaRepo;

    @PostMapping
    public Reserva crearReserva(@RequestBody Reserva reserva) {
        return reservaRepo.save(reserva);
    }
}
