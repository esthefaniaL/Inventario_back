package com.liteThinking.inventario.domain.repository;

import com.liteThinking.inventario.domain.model.Categoria;
import java.util.Optional;
import java.util.List;

public interface CategoriaRepository {
    Optional<Categoria> findById(Long id);
    List<Categoria> findAll();
    Categoria save(Categoria categoria);
    void deleteById(Long id);
}
