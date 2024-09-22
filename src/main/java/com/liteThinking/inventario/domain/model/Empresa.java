package com.liteThinking.inventario.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "empresa")
public class Empresa {
    @Id
    @Column(length = 255)
    private String nit;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 255)
    private String direccion;

    @Column(length = 255)
    private String telefono;

    // Relación con Productos
    @OneToMany(mappedBy = "empresa")
    @JsonManagedReference
    private List<Producto> productos;
}
