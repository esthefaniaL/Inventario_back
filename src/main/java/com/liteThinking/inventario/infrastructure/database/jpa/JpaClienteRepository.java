package com.liteThinking.inventario.infrastructure.database.jpa;

import com.liteThinking.inventario.domain.model.Cliente;
import com.liteThinking.inventario.domain.repository.ClienteRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaClienteRepository extends JpaRepository<Cliente, Long>, ClienteRepository {
    // Métodos personalizados si es necesario
}
