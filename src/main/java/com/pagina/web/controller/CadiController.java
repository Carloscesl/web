package com.pagina.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pagina.web.model.cadi;
import com.pagina.web.service.CadiService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cadis")
public class CadiController {

    private final CadiService cadiService;

    @GetMapping
    public List<cadi> listar() {
        return cadiService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<cadi> buscarPorId(@PathVariable Long id) {
        return cadiService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<cadi> crear(@RequestBody cadi cadi) {
        return ResponseEntity.ok(cadiService.guardar(cadi));
    }

    @PutMapping("/{id}")
    public ResponseEntity<cadi> actualizar(@PathVariable Long id,
            @RequestBody cadi nuevo) {

        return cadiService.buscarPorId(id)
                .map(c -> {
                    c.setNombre(nuevo.getNombre());
                    c.setDescripcion(nuevo.getDescripcion());
                    c.setTotalActividades(nuevo.getTotalActividades());
                    return ResponseEntity.ok(cadiService.guardar(c));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        cadiService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
