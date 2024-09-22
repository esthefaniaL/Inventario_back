package com.liteThinking.inventario.application.usecases;

import com.liteThinking.inventario.application.dto.OrdenDTO;
import com.liteThinking.inventario.domain.model.Orden;

import java.util.List;
import java.util.Optional;

public interface OrdenService {

    OrdenDTO createOrden(OrdenDTO ordenDTO); // Crear una orden

    OrdenDTO updateOrden(OrdenDTO ordenDTO); // Actualizar una orden

    Optional<Orden> getOrdenById(Long id); // Obtener una orden por ID

    List<Orden> getAllOrdenes(); // Obtener todas las órdenes

    void deleteOrden(Long id); // Eliminar una orden

    // Declarar el método de conversión
    OrdenDTO convertToDTO(Orden orden); // Convertir de Orden a OrdenDTO
}