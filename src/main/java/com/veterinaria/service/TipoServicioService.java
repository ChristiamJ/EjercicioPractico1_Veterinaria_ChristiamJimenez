package com.veterinaria.service;

import com.veterinaria.domain.TipoServicio;
import java.util.List;
import java.util.Optional;

public interface TipoServicioService {

    List<TipoServicio> listarTipos();

    Optional<TipoServicio> buscarTipo(Integer id);

    void guardarTipo(TipoServicio tipoServicio);

    void eliminarTipo(Integer id);
}