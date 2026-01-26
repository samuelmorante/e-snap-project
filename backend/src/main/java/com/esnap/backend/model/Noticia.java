package com.esnap.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data; // To don't write getters and setters
import java.time.LocalDate;

@Entity
@Data
public class Noticia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String cuerpo;
    private String categoria;
    private LocalDate fecha;
    private String imagenUrl; // To put images
}
