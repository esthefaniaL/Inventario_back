package com.liteThinking.inventario.infrastructure.database.jpa;

import com.liteThinking.inventario.domain.model.Categoria;
import com.liteThinking.inventario.domain.repository.CategoriaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCategoriaRepository extends JpaRepository<Categoria, Long>, CategoriaRepository {
    // Métodos personalizados si es necesario
}
