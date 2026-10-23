package com.veterinaria.service;

import com.veterinaria.domain.Servicio;
import java.util.List;
import java.util.Optional;

public interface ServicioService {

    List<Servicio> listarServicios();

    Optional<Servicio> buscarServicio(Integer id);

    void guardarServicio(Servicio servicio);

    void eliminarServicio(Integer id);
}