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
import org.springframework.web.multipart.MultipartFile;

import com.pagina.web.model.actividad;
import com.pagina.web.service.ActividadService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/actividades")
public class ActividadController {

    private final ActividadService actividadService;

    @GetMapping
    public List<actividad> listar() {
        return actividadService.listarTodas();
    }

    @PostMapping
    public ResponseEntity<actividad> crear(
            @RequestBody actividad actividad,
            @RequestParam Long reaId) {

        return ResponseEntity.ok(
                actividadService.guardar(actividad, reaId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        actividadService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // Subir archivo
    @PostMapping("/{id}/archivo")
    public ResponseEntity<String> subirArchivo(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {

        actividadService.subirArchivo(id, file);
        return ResponseEntity.ok("Archivo subido correctamente");
    }

    // Eliminar archivo
    @DeleteMapping("/{id}/archivo")
    public ResponseEntity<String> eliminarArchivo(@PathVariable Long id) {
        actividadService.eliminarArchivo(id);
        return ResponseEntity.ok("Archivo eliminado");
    }
}
