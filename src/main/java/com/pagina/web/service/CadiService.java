package com.pagina.web.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pagina.web.model.cadi;
import com.pagina.web.repository.CadiRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CadiService {
    private final CadiRepository cadiRepository;

    public List<cadi> listarTodos() {
        return cadiRepository.findAll();
    }

    public Optional<cadi> buscarPorId(Long id) {
        return cadiRepository.findById(id);
    }

    public cadi guardar(cadi cadi) {
        return cadiRepository.save(cadi);
    }

    public void eliminar(Long id) {
        cadiRepository.deleteById(id);
    }
}
