package com.example.Travelgo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Travelgo.dto.PaqueteTuristicoResponse;
import com.example.Travelgo.exception.NotFoundException;
import com.example.Travelgo.model.Itinerario;
import com.example.Travelgo.model.PaqueteTuristico;
import com.example.Travelgo.repository.ItinerarioRepository; 
import com.example.Travelgo.repository.PaqueteTuristicoRepository;
import com.example.Travelgo.service.PaqueteTuristicoService;

@Service
@Transactional
public class PaqueteTuristicoServiceImpl implements PaqueteTuristicoService {

    private final PaqueteTuristicoRepository repo;
    private final ItinerarioRepository itinerarioRepo; 

    // Constructor actualizado para inyectar ItinerarioRepository
    public PaqueteTuristicoServiceImpl(PaqueteTuristicoRepository repo, ItinerarioRepository itinerarioRepo) {
        this.repo = repo;
        this.itinerarioRepo = itinerarioRepo;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaqueteTuristico> listar() {
        return repo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public PaqueteTuristico buscarPorId(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Paquete turístico no encontrado: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaqueteTuristico> buscarPorNombre(String q) {
        return repo.findByNombreContainingIgnoreCase(q);
    }

    @Override
    public PaqueteTuristico crear(PaqueteTuristico paquete) {
        paquete.setId(null);
        return repo.save(paquete);
    }

    @Override
    public PaqueteTuristico actualizar(Long id, PaqueteTuristico paquete) {
        PaqueteTuristico actual = buscarPorId(id);
        actual.setNombre(paquete.getNombre());
        actual.setDescripcion(paquete.getDescripcion());
        actual.setPrecio(paquete.getPrecio());
        actual.setCategoria(paquete.getCategoria());
        actual.setImagenUrl(paquete.getImagenUrl());
        return repo.save(actual);
    }

    @Override
    @Transactional(readOnly = true)
    public PaqueteTuristicoResponse buscarPorIdConDetalle(Long id) {
        // Obtener la entidad de la DB
        PaqueteTuristico paquete = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Paquete turístico no encontrado: " + id));

        // Obtener el itinerario real desde la DB
        List<Itinerario> itinerario = itinerarioRepo.findByPaqueteTuristico_Id(id);

        System.out.println("Itinerario encontrado para ID " + id + ": " + itinerario.size() + " elementos.");

        // Devolver el DTO con el paquete y el itinerario
        return new PaqueteTuristicoResponse(paquete, itinerario);
    }

    @Override
    public void eliminar(Long id) {
        PaqueteTuristico actual = buscarPorId(id);
        repo.delete(actual);
    }
}