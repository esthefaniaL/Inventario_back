package com.liteThinking.inventario.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL) // Evitar campos nulos en el JSON
public class EmpresaDTO {

    private String nit;


    private String nombre;

    private String direccion;

    private String telefono;

    private List<ProductoDTO> productos;

    // Constructor por defecto
    public EmpresaDTO() {
    }

    // Constructor con todos los campos
    public EmpresaDTO(String nit, String nombre, String direccion, String telefono, List<ProductoDTO> productos) {
        this.nit = nit;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.productos = productos;
    }

    // Constructor sin la lista de productos
    public EmpresaDTO(String nit, String nombre, String direccion, String telefono) {
        this(nit, nombre, direccion, telefono, null);
    }

    // Getters y setters
    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<ProductoDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoDTO> productos) {
        this.productos = productos;
    }
}
