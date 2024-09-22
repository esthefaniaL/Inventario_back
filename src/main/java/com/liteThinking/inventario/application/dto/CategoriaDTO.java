package com.liteThinking.inventario.application.dto;

import java.util.List;

public class CategoriaDTO {
    private Long id;
    private String nombre;
    private List<String> productos; // Lista de códigos de productos asociados

    // Constructor por defecto
    public CategoriaDTO() {}

    // Constructor con todos los campos
    public CategoriaDTO(Long id, String nombre, List<String> productos) {
        this.id = id;
        this.nombre = nombre;
        this.productos = productos;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<String> getProductos() {
        return productos;
    }

    public void setProductos(List<String> productos) {
        this.productos = productos;
    }
}