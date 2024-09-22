package com.liteThinking.inventario.application.usecases;

import com.liteThinking.inventario.domain.model.Categoria;
import java.util.List;
import java.util.Optional;

import java.util.List;
import java.util.Optional;

import com.liteThinking.inventario.application.dto.CategoriaDTO;
import java.util.List;
import java.util.Optional;

public interface CategoriaService {

    // Método para crear una nueva categoría
    CategoriaDTO createCategoria(CategoriaDTO categoriaDTO);

    // Método para actualizar una categoría existente
    CategoriaDTO updateCategoria(Long id, CategoriaDTO categoriaDTO);

    // Método para obtener una categoría por su ID
    Optional<CategoriaDTO> getCategoriaById(Long id);

    // Método para obtener todas las categorías
    List<CategoriaDTO> getAllCategorias();

    // Método para eliminar una categoría por su ID
    void deleteCategoria(Long id);
}