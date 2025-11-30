package com.example.Travelgo.service;

import java.util.List;

import com.example.Travelgo.model.PaqueteTuristico;

public interface PaqueteTuristicoService {
    List<PaqueteTuristico> listar();
    PaqueteTuristico buscarPorId(Long id);
    List<PaqueteTuristico> buscarPorNombre(String q);
    PaqueteTuristico crear(PaqueteTuristico paquete);
    PaqueteTuristico actualizar(Long id, PaqueteTuristico paquete);
    void eliminar(Long id);
}