package com.liteThinking.inventario.infrastructure.database.jpa;

import com.liteThinking.inventario.domain.model.Producto;
import com.liteThinking.inventario.domain.repository.ProductoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaProductoRepository extends JpaRepository<Producto, String>, ProductoRepository {
    // Aquí puedes definir métodos de consulta personalizada si es necesario
}