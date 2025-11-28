package com.example.Travelgo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Travelgo.model.PaqueteTuristico;

public interface PaqueteTuristicoRepository extends JpaRepository<PaqueteTuristico, Long> {
    List<PaqueteTuristico> findByNombreContainingIgnoreCase(String nombre);
}
