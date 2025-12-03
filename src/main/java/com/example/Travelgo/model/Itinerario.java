package com.example.Travelgo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity 
public class Itinerario {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Es necesario un ID para la entidad JPA

    private Integer dia;
    private String actividad;
    
    //  Relación con PaqueteTuristico (Foreign Key)
    // Cada Itinerario pertenece a UN Paquete.
    @ManyToOne
    @JoinColumn(name = "paquete_id", nullable = false) // Esto crea la columna "paquete_id" en la tabla "itinerario"
    private PaqueteTuristico paqueteTuristico; 
}