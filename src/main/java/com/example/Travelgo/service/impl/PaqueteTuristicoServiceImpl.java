package com.example.Travelgo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Travelgo.dto.PaqueteTuristicoResponse;
import com.example.Travelgo.exception.NotFoundException;
import com.example.Travelgo.model.Itinerario;
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
@Transactional(readOnly = true)
public PaqueteTuristicoResponse buscarPorIdConDetalle(Long id) {
    // 1. Obtener la entidad de la DB
    PaqueteTuristico paquete = repo.findById(id)
            .orElseThrow(() -> new NotFoundException("Paquete turístico no encontrado: " + id));

    // 2. Lógica para obtener el Itinerario.
    //    *NOTA: ESTA ES LA PARTE CRÍTICA*
    //    Aquí deberías obtener el itinerario real si lo estuvieras cargando desde la DB.
    //    Para fines de prueba, mapeamos el ejemplo de Valparaíso solo para el ID 1.
    List<Itinerario> itinerario;
    if (id == 1L) {
        itinerario = List.of(
            new Itinerario(1, "Llegada y paseo por los cerros Alegre y Concepción."),
            new Itinerario(1, "Almuerzo en el Mercado Puerto."),
            new Itinerario(1, "Recorrido en Ascensor El Peral y cena con vista al mar."),
            new Itinerario(2, "Hola soy un test del dia 2 :D")
        );
    } else {
        // Para todos los demás, devuelve una lista vacía.
        itinerario = List.of();
    }

    // 3. Devolver el DTO con el paquete y el itinerario
    return new PaqueteTuristicoResponse(paquete, itinerario);
}

    @Override
    public void eliminar(Long id) {
        PaqueteTuristico actual = buscarPorId(id);
        repo.delete(actual);
    }
}