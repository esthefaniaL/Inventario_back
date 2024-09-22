package com.liteThinking.inventario.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Producto {

    @Id
    @Column(length = 255)
    private String codigo; // Código del producto (Primary Key)

    @Column(nullable = false, length = 100)
    private String nombre; // Nombre del producto

    @Lob
    private String caracteristicas; // Características del producto (opcional)

    @Column(nullable = false)
    private double precioUsd; // Precio en USD

    @Column(nullable = true)
    private double precioEur; // Precio en EUR (opcional)

    @Column(nullable = true)
    private double precioLocal; // Precio en moneda local (opcional)

    @ManyToOne
    @JoinColumn(name = "nit_empresa", nullable = false)
    @JsonBackReference
    private Empresa empresa; // Relación con la entidad Empresa (muchos a uno)

    @ManyToMany
    @JoinTable(
            name = "producto_categoria",
            joinColumns = @JoinColumn(name = "codigo_producto"),
            inverseJoinColumns = @JoinColumn(name = "id_categoria")
    )
    private Set<Categoria> categorias = new HashSet<>();


    @ManyToMany(mappedBy = "productos")
    private List<Orden> ordenes; // Relación con la entidad Orden a través de la tabla intermedia orden_producto

    // Getters y Setters generados por Lombok
}