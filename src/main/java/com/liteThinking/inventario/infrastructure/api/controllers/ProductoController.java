package com.liteThinking.inventario.infrastructure.api.controllers;

import com.liteThinking.inventario.application.usecases.ProductoService;
import com.liteThinking.inventario.domain.model.Empresa;
import com.liteThinking.inventario.domain.model.Producto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import com.liteThinking.inventario.application.dto.ProductoDTO;
import com.liteThinking.inventario.application.usecases.ProductoService;
import com.liteThinking.inventario.domain.model.Producto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> getAllProductos() {
        return ResponseEntity.ok(productoService.getAllProductos());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<ProductoDTO> getProductoByCodigo(@PathVariable String codigo) {
        Optional<ProductoDTO> producto = productoService.getProductoByCodigo(codigo);
        return producto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> createProducto(@RequestBody ProductoDTO productoDTO) {
        ProductoDTO nuevoProducto = productoService.createProducto(productoDTO);
        return ResponseEntity.ok(nuevoProducto);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<ProductoDTO> updateProducto(@PathVariable String codigo, @RequestBody ProductoDTO productoDTO) {
        ProductoDTO productoActualizado = productoService.updateProducto(codigo, productoDTO);
        return ResponseEntity.ok(productoActualizado);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> deleteProducto(@PathVariable String codigo) {
        productoService.deleteProducto(codigo);
        return ResponseEntity.noContent().build();
    }
}