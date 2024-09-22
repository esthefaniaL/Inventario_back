package com.liteThinking.inventario.infrastructure.api.controllers;

import com.liteThinking.inventario.application.dto.OrdenDTO;
import com.liteThinking.inventario.application.usecases.OrdenService;
import com.liteThinking.inventario.domain.model.Orden;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ordenes")
public class OrdenController {

    private final OrdenService ordenService;

    // Inyección de dependencias a través del constructor
    public OrdenController(OrdenService ordenService) {
        this.ordenService = ordenService;
    }

    // Endpoint para crear una nueva orden
    @PostMapping
    public ResponseEntity<OrdenDTO> createOrden(@RequestBody OrdenDTO ordenDTO) {
        try {
            OrdenDTO nuevaOrden = ordenService.createOrden(ordenDTO);
            return ResponseEntity.ok(nuevaOrden);
        } catch (Exception e) {
            // Puedes agregar más detalles de la excepción para facilitar el debug
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Endpoint para obtener una orden por ID
    @GetMapping("/{id}")
    public ResponseEntity<OrdenDTO> getOrdenById(@PathVariable Long id) {
        Optional<OrdenDTO> ordenDTO = ordenService.getOrdenById(id).map(ordenService::convertToDTO);
        return ordenDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para obtener todas las órdenes
    @GetMapping
    public ResponseEntity<List<OrdenDTO>> getAllOrdenes() {
        List<OrdenDTO> ordenes = ordenService.getAllOrdenes().stream()
                .map(ordenService::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ordenes);
    }

    // Endpoint para actualizar una orden por ID
    @PutMapping("/{id}")
    public ResponseEntity<OrdenDTO> updateOrden(@PathVariable Long id, @RequestBody OrdenDTO ordenDTO) {
        try {
            // Llamamos directamente al servicio de actualización
            ordenDTO.setId(id); // Aseguramos que el ID en el DTO es correcto
            OrdenDTO ordenActualizada = ordenService.updateOrden(ordenDTO);
            return ResponseEntity.ok(ordenActualizada);
        } catch (Exception e) {
            // Puedes agregar más detalles de la excepción para facilitar el debug
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Endpoint para eliminar una orden por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrden(@PathVariable Long id) {
        try {
            ordenService.deleteOrden(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            // Puedes agregar más detalles de la excepción para facilitar el debug
            return ResponseEntity.notFound().build();
        }
    }
}