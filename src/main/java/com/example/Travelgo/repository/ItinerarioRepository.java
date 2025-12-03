package com.example.Travelgo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Travelgo.model.Itinerario;

/**
 * Repositorio de Spring Data JPA para la entidad Itinerario
 * Proporciona métodos CRUD básicos automáticamente
 * Extiende JpaRepository<[Clase de la Entidad], [Tipo de la Clave Primaria]>
 */
@Repository
public interface ItinerarioRepository extends JpaRepository<Itinerario, Long> {

    List<Itinerario> findByPaqueteTuristico_Id(Long paqueteId);
}