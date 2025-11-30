package com.example.Travelgo.model;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cliente; // Puedes agregar más campos según tu necesidad

    @ElementCollection
    private List<Long> paqueteIds; // IDs de los paquetes reservados

    // getters y setters
    // ...existing code...

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public List<Long> getPaqueteIds() {
        return paqueteIds;
    }

    public void setPaqueteIds(List<Long> paqueteIds) {
        this.paqueteIds = paqueteIds;
    }

}
