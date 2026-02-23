package com.pagina.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pagina.web.model.rea;
import com.pagina.web.model.cadi;
import com.pagina.web.service.ReaService;
import com.pagina.web.service.CadiService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reas")
public class ReaController {

    private final ReaService reaService;
    private final CadiService cadiService;

    @GetMapping
    public List<rea> listar() {
        return reaService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<rea> buscarPorId(@PathVariable Long id) {
        return reaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<rea> crear(@RequestBody rea rea,
            @RequestParam Long cadiId) {

        cadi cadi = cadiService.buscarPorId(cadiId)
                .orElseThrow(() -> new RuntimeException("Cadi no encontrado"));

        rea.setCadi(cadi);

        return ResponseEntity.ok(reaService.guardar(rea));
    }

    @PutMapping("/{id}")
    public ResponseEntity<rea> actualizar(@PathVariable Long id,
            @RequestBody rea nuevo) {

        return reaService.buscarPorId(id)
                .map(r -> {
                    r.setNombre(nuevo.getNombre());
                    r.setDescripcion(nuevo.getDescripcion());
                    return ResponseEntity.ok(reaService.guardar(r));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        reaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
