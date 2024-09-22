package com.liteThinking.inventario.infrastructure.database.jpa;

import com.liteThinking.inventario.domain.model.Orden;
import com.liteThinking.inventario.domain.repository.OrdenRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaOrdenRepository extends JpaRepository<Orden, Long>, OrdenRepository {
    // Métodos personalizados si es necesario
}
