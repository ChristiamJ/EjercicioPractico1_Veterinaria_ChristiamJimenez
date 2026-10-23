package com.veterinaria.service;

import com.veterinaria.domain.TipoServicio;
import com.veterinaria.repository.TipoServicioRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TipoServicioServiceImpl implements TipoServicioService {

    private final TipoServicioRepository tipoServicioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<TipoServicio> listarTipos() {
        return tipoServicioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TipoServicio> buscarTipo(Integer id) {
        return tipoServicioRepository.findById(id);
    }

    @Override
    @Transactional
    public void guardarTipo(TipoServicio tipoServicio) {
        tipoServicioRepository.save(tipoServicio);
    }

    @Override
    @Transactional
    public void eliminarTipo(Integer id) {
        tipoServicioRepository.deleteById(id);
    }
}