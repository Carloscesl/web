package com.pagina.web.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pagina.web.model.rea;
import com.pagina.web.repository.ReaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReaService {
    private final ReaRepository reaRepository;

    public List<rea> listarTodos() {
        return reaRepository.findAll();
    }

    public Optional<rea> buscarPorId(Long id) {
        return reaRepository.findById(id);
    }

    public rea guardar(rea rea) {
        return reaRepository.save(rea);
    }

    public void eliminar(Long id) {
        reaRepository.deleteById(id);
    }
}
