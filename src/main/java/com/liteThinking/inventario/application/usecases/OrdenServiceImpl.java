package com.liteThinking.inventario.application.usecases;

import com.liteThinking.inventario.application.dto.OrdenDTO;
import com.liteThinking.inventario.application.usecases.OrdenService;
import com.liteThinking.inventario.domain.model.Cliente;
import com.liteThinking.inventario.domain.model.Orden;
import com.liteThinking.inventario.domain.model.Producto;
import com.liteThinking.inventario.domain.repository.ClienteRepository;
import com.liteThinking.inventario.domain.repository.OrdenRepository;
import com.liteThinking.inventario.domain.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrdenServiceImpl implements OrdenService {

    private final OrdenRepository ordenRepository;
    private final ClienteRepository clienteRepository; // Repositorio para clientes
    private final ProductoRepository productoRepository; // Repositorio para productos

    public OrdenServiceImpl(OrdenRepository ordenRepository, ClienteRepository clienteRepository, ProductoRepository productoRepository) {
        this.ordenRepository = ordenRepository;
        this.clienteRepository = clienteRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public OrdenDTO createOrden(OrdenDTO ordenDTO) {
        Orden orden = convertToEntity(ordenDTO);
        Orden nuevaOrden = ordenRepository.save(orden);
        return convertToDTO(nuevaOrden);
    }

    @Override
    public OrdenDTO updateOrden(OrdenDTO ordenDTO) {
        // Implementación de actualización de orden
        return null; // Implementa la lógica de actualización aquí
    }

    @Override
    public Optional<Orden> getOrdenById(Long id) {
        return ordenRepository.findById(id);
    }

    @Override
    public List<Orden> getAllOrdenes() {
        return ordenRepository.findAll();
    }

    @Override
    public void deleteOrden(Long id) {
        ordenRepository.deleteById(id);
    }

    // Implementación del método de conversión
    @Override
    public OrdenDTO convertToDTO(Orden orden) {
        return new OrdenDTO(
                orden.getId(),
                orden.getFecha(),
                orden.getTotal(),
                orden.getCliente().getId(),
                orden.getProductos().stream().map(Producto::getCodigo).collect(Collectors.toList())
        );
    }

    private Orden convertToEntity(OrdenDTO ordenDTO) {
        Orden orden = new Orden();
        orden.setFecha(ordenDTO.getFecha());
        orden.setTotal(ordenDTO.getTotal());

        Cliente cliente = clienteRepository.findById(ordenDTO.getIdCliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        orden.setCliente(cliente);

        if (ordenDTO.getProductos() != null && !ordenDTO.getProductos().isEmpty()) {
            List<Producto> productos = productoRepository.findAllById(ordenDTO.getProductos());
            orden.setProductos(productos);
        }

        return orden;
    }
}