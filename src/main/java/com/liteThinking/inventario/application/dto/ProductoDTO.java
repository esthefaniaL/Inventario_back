package com.liteThinking.inventario.application.dto;

public class ProductoDTO {

    private String codigo;

    private String nombre;

    private String caracteristicas;

    private double precioUsd;

    private double precioEur;
    private double precioLocal;

    private String empresaId; // Si un producto pertenece a una empresa

    // Constructor por defecto
    public ProductoDTO() {}

    // Constructor con todos los campos
    public ProductoDTO(String codigo, String nombre, String caracteristicas,
                       String empresaId, double precioUsd, double precioEur, double precioLocal) {

        this.codigo = codigo;
        this.nombre = nombre;
        this.caracteristicas = caracteristicas;
        this.empresaId = empresaId;
        this.precioUsd = precioUsd;
        this.precioEur = precioEur;
        this.precioLocal = precioLocal;
    }

    // Constructor sin empresaId (opcional)
    public ProductoDTO(String codigo, String nombre, String caracteristicas,
                       double precioUsd, double precioEur, double precioLocal) {

        this(codigo, nombre, caracteristicas, null, precioUsd, precioEur, precioLocal);
    }

    // Getters y setters

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public String getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(String empresaId) {
        this.empresaId = empresaId;
    }

    public double getPrecioUsd() {
        return precioUsd;
    }

    public void setPrecioUsd(double precioUsd) {
        this.precioUsd = precioUsd;
    }

    public double getPrecioEur() {
        return precioEur;
    }

    public void setPrecioEur(double precioEur) {
        this.precioEur = precioEur;
    }

    public double getPrecioLocal() {
        return precioLocal;
    }

    public void setPrecioLocal(double precioLocal) {
        this.precioLocal = precioLocal;
    }
}
