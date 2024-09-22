package com.liteThinking.inventario.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date fecha;

    @Column(nullable = false)
    private Double total;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToMany
    @JoinTable(
            name = "orden_producto",
            joinColumns = @JoinColumn(name = "id_orden"),
            inverseJoinColumns = @JoinColumn(name = "codigo_producto")
    )
    private List<Producto> productos;

    // Getters y Setters generados por Lombok
    // Si estás usando Lombok, asegúrate de tener las anotaciones @Getter y @Setter en la clase.
}
