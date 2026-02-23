package com.pagina.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pagina.web.model.rea;
import com.pagina.web.model.recursoAdicional;
import com.pagina.web.service.ReaService;
import com.pagina.web.service.RecursoAdicionalService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recursos")
public class RecursoAdicionalController {

    private final RecursoAdicionalService recursoService;
    private final ReaService reaService;

    @GetMapping
    public List<recursoAdicional> listar() {
        return recursoService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<recursoAdicional> crear(
            @RequestBody recursoAdicional recurso,
            @RequestParam Long reaId) {

        rea rea = reaService.buscarPorId(reaId)
                .orElseThrow(() -> new RuntimeException("Rea no encontrada"));

        recurso.setRea(rea);

        return ResponseEntity.ok(recursoService.guardar(recurso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        recursoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
