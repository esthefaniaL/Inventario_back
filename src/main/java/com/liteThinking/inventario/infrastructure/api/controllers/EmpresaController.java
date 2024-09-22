package com.liteThinking.inventario.infrastructure.api.controllers;

import com.liteThinking.inventario.application.dto.EmpresaDTO;
import com.liteThinking.inventario.application.usecases.EmpresaService;
import com.liteThinking.inventario.domain.model.Empresa;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @PostMapping
    public ResponseEntity<EmpresaDTO> createEmpresa(@RequestBody EmpresaDTO empresaDTO) {
        try {
            EmpresaDTO nuevaEmpresa = empresaService.createEmpresa(empresaDTO);
            return ResponseEntity.ok(nuevaEmpresa);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build(); // Manejar excepciones según tu lógica de negocio
        }
    }

    @PutMapping("/{nit}")
    public ResponseEntity<EmpresaDTO> updateEmpresa(@PathVariable String nit, @RequestBody EmpresaDTO empresaDTO) {
        try {
            Optional<EmpresaDTO> empresaActualizada = Optional.ofNullable(empresaService.updateEmpresa(nit, empresaDTO));
            return empresaActualizada.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build(); // Manejar excepciones según tu lógica de negocio
        }
    }

    @GetMapping("/{nit}")
    public ResponseEntity<EmpresaDTO> getEmpresaByNit(@PathVariable String nit) {
        Optional<EmpresaDTO> empresaDTO = empresaService.getEmpresaByNit(nit);
        return empresaDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<EmpresaDTO>> getAllEmpresas() {
        List<EmpresaDTO> empresas = empresaService.getAllEmpresas();
        return ResponseEntity.ok(empresas);
    }

    @DeleteMapping("/{nit}")
    public ResponseEntity<Void> deleteEmpresa(@PathVariable String nit) {
        try {
            empresaService.deleteEmpresa(nit);
            return ResponseEntity.noContent().build(); // 204 No Content si se elimina correctamente
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); // 404 Not Found si la empresa no existe
        }
    }
}
