package com.liteThinking.inventario.domain.repository;

import com.liteThinking.inventario.domain.model.Cliente;
import java.util.Optional;
import java.util.List;

public interface ClienteRepository {
    Optional<Cliente> findById(Long id);
    Optional<Cliente> findByCorreo(String correo);
    List<Cliente> findAll();
    Cliente save(Cliente cliente);
    void deleteById(Long id);
}
