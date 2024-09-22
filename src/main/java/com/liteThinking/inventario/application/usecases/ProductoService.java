package com.liteThinking.inventario.application.usecases;

import com.liteThinking.inventario.application.dto.ProductoDTO;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    ProductoDTO createProducto(ProductoDTO productoDTO);
    ProductoDTO updateProducto(String codigo, ProductoDTO productoDTO);
    Optional<ProductoDTO> getProductoByCodigo(String codigo);
    List<ProductoDTO> getAllProductos();
    void deleteProducto(String codigo);
}