package com.example.Travelgo.dto;

import java.util.List;

import com.example.Travelgo.model.Itinerario; // Importar el nuevo modelo

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaqueteTuristicoResponse {
    private Long id;
    private String nombre; // Antes 'name' en mock
    private String descripcion;
    private Double precio; // Antes 'price' en mock
    private String imagenUrl; // Antes 'imageUrl' en mock
    private String categoria; // Antes 'category' en mock
    
    private List<Itinerario> itinerary; 

    // Constructor de conveniencia desde el modelo PaqueteTuristico (sin itinerario)
    // Usar esto para mapear los campos básicos
    public PaqueteTuristicoResponse(
        com.example.Travelgo.model.PaqueteTuristico paquete, 
        List<Itinerario> itinerario) {
        
        this.id = paquete.getId();
        this.nombre = paquete.getNombre();
        this.descripcion = paquete.getDescripcion();
        this.precio = paquete.getPrecio();
        this.imagenUrl = paquete.getImagenUrl();
        this.categoria = paquete.getCategoria();
        this.itinerary = itinerario;
    }
}