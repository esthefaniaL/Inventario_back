package com.liteThinking.inventario.application.dto;

import java.util.Date;
import java.util.List;

public class OrdenDTO {

    private Long id; // Identificador de la orden
    private Date fecha; // Fecha de la orden
    private Double total; // Total de la orden
    private Long idCliente; // Identificador del cliente asociado a la orden
    private List<String> productos; // Lista de códigos de productos asociados a la orden

    // Constructor vacío
    public OrdenDTO() {}

    // Constructor con todos los campos
    public OrdenDTO(Long id, Date fecha, Double total, Long idCliente, List<String> productos) {
        this.id = id;
        this.fecha = fecha;
        this.total = total;
        this.idCliente = idCliente;
        this.productos = productos;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public List<String> getProductos() {
        return productos;
    }

    public void setProductos(List<String> productos) {
        this.productos = productos;
    }
}
