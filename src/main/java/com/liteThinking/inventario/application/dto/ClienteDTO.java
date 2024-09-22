package com.liteThinking.inventario.application.dto;

public class ClienteDTO {
    private Long id;
    private String nombre;
    private String correo;
    private String telefono;

    // Constructor por defecto
    public ClienteDTO() {}

    // Constructor con todos los campos
    public ClienteDTO(Long id, String nombre, String email, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.correo = email;
        this.telefono = telefono;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}