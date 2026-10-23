package com.veterinaria.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "servicio")
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, precision = 8, scale = 2)
    private BigDecimal precio;

    @Column(name = "duracion_minutos", nullable = false)
    private Integer duracionMinutos;

    @ManyToOne
    @JoinColumn(name = "tipo_servicio_id", nullable = false)
    private TipoServicio tipoServicio;
}