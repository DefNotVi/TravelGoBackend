package com.example.Travelgo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Travelgo.exception.NotFoundException;
import com.example.Travelgo.model.PaqueteTuristico;
import com.example.Travelgo.repository.PaqueteTuristicoRepository;
import com.example.Travelgo.service.PaqueteTuristicoService;

@Service
@Transactional
public class PaqueteTuristicoServiceImpl implements PaqueteTuristicoService {

    private final PaqueteTuristicoRepository repo;

    public PaqueteTuristicoServiceImpl(PaqueteTuristicoRepository repo) {
        this.repo = repo;
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
        return repo.save(paquete);
    }

    @Override
    public PaqueteTuristico actualizar(Long id, PaqueteTuristico paquete) {
        PaqueteTuristico actual = buscarPorId(id);
        actual.setNombre(paquete.getNombre());
        actual.setDescripcion(paquete.getDescripcion());
        actual.setPrecio(paquete.getPrecio());
        // Agrega aquí otros campos si los tienes
        return repo.save(actual);
    }

    @Override
    public void eliminar(Long id) {
        PaqueteTuristico actual = buscarPorId(id);
        repo.delete(actual);
    }
}