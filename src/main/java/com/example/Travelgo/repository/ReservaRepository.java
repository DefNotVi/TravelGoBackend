package com.example.Travelgo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Travelgo.model.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {}