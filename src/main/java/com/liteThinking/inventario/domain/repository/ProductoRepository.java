package com.liteThinking.inventario.domain.repository;

import com.liteThinking.inventario.domain.model.Producto;
import java.util.Optional;
import java.util.List;

import com.liteThinking.inventario.domain.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, String> {

    // Método para encontrar productos por nombre (opcional)
    List<Producto> findByNombreContaining(String nombre);

    // Método para encontrar productos por empresa
    List<Producto> findByEmpresaNit(String nitEmpresa);

    // Método para encontrar productos por rango de precios
    List<Producto> findByPrecioUsdBetween(double precioMin, double precioMax);
}