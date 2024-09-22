package com.liteThinking.inventario.domain.repository;

import com.liteThinking.inventario.domain.model.Orden;
import java.util.Optional;
import java.util.List;

public interface OrdenRepository {
    Optional<Orden> findById(Long id);
    List<Orden> findAll();
    Orden save(Orden orden);
    void deleteById(Long id);
}
