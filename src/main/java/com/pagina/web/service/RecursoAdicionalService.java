package com.pagina.web.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pagina.web.model.recursoAdicional;
import com.pagina.web.repository.RecursoAdicionalRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecursoAdicionalService {

    private final RecursoAdicionalRepository recursoRepository;

    public List<recursoAdicional> listarTodos() {
        return recursoRepository.findAll();
    }

    public Optional<recursoAdicional> buscarPorId(Long id) {
        return recursoRepository.findById(id);
    }

    public recursoAdicional guardar(recursoAdicional recurso) {
        return recursoRepository.save(recurso);
    }

    public void eliminar(Long id) {
        recursoRepository.deleteById(id);
    }

}
