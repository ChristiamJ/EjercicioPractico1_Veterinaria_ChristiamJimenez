package com.veterinaria.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cita")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre_mascota", nullable = false, length = 100)
    private String nombreMascota;

    @Column(name = "nombre_propietario", nullable = false, length = 100)
    private String nombrePropietario;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "servicio_id", nullable = false)
    private Servicio servicio;
}