package com.veterinaria.service;

import com.veterinaria.domain.Servicio;
import com.veterinaria.repository.ServicioRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ServicioServiceImpl implements ServicioService {

    private final ServicioRepository servicioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Servicio> listarServicios() {
        return servicioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Servicio> buscarServicio(Integer id) {
        return servicioRepository.findById(id);
    }

    @Override
    @Transactional
    public void guardarServicio(Servicio servicio) {
        servicioRepository.save(servicio);
    }

    @Override
    @Transactional
    public void eliminarServicio(Integer id) {
        servicioRepository.deleteById(id);
    }
}