package com.liteThinking.inventario.application.usecases;

import com.liteThinking.inventario.domain.model.Empresa;
import com.liteThinking.inventario.domain.model.Producto;
import com.liteThinking.inventario.domain.repository.EmpresaRepository;
import com.liteThinking.inventario.domain.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import com.liteThinking.inventario.application.dto.ProductoDTO;
import com.liteThinking.inventario.domain.model.Empresa;
import com.liteThinking.inventario.domain.model.Producto;
import com.liteThinking.inventario.domain.repository.EmpresaRepository;
import com.liteThinking.inventario.domain.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.liteThinking.inventario.application.dto.ProductoDTO;
import com.liteThinking.inventario.application.usecases.ProductoService;
import com.liteThinking.inventario.domain.model.Empresa;
import com.liteThinking.inventario.domain.model.Producto;
import com.liteThinking.inventario.domain.repository.EmpresaRepository;
import com.liteThinking.inventario.domain.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final EmpresaRepository empresaRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository, EmpresaRepository empresaRepository) {
        this.productoRepository = productoRepository;
        this.empresaRepository = empresaRepository;
    }

    @Override
    public ProductoDTO createProducto(ProductoDTO productoDTO) {
        // Buscar la empresa a la que pertenece el producto
        Optional<Empresa> empresaOptional = empresaRepository.findByNit(productoDTO.getEmpresaId());
        if (empresaOptional.isPresent()) {
            Producto producto = convertToEntity(productoDTO);
            producto.setEmpresa(empresaOptional.get()); // Asignar la empresa al producto
            Producto nuevoProducto = productoRepository.save(producto);
            return convertToDTO(nuevoProducto);
        } else {
            // Manejar el caso en que la empresa no se encuentre
            throw new IllegalArgumentException("Empresa no encontrada para el ID proporcionado");
        }
    }

    @Override
    public ProductoDTO updateProducto(String codigo, ProductoDTO productoDTO) {
        Optional<Producto> productoOptional = productoRepository.findById(codigo);
        if (productoOptional.isPresent()) {
            Producto productoExistente = productoOptional.get();
            productoExistente.setNombre(productoDTO.getNombre());
            productoExistente.setCaracteristicas(productoDTO.getCaracteristicas());
            productoExistente.setPrecioUsd(productoDTO.getPrecioUsd());
            productoExistente.setPrecioEur(productoDTO.getPrecioEur());
            productoExistente.setPrecioLocal(productoDTO.getPrecioLocal());
            // Asignar la empresa si es necesario
            Optional<Empresa> empresaOptional = empresaRepository.findByNit(productoDTO.getEmpresaId());
            empresaOptional.ifPresent(productoExistente::setEmpresa);
            Producto productoActualizado = productoRepository.save(productoExistente);
            return convertToDTO(productoActualizado);
        }
        return null; // O lanzar una excepción personalizada si se prefiere
    }

    @Override
    public Optional<ProductoDTO> getProductoByCodigo(String codigo) {
        return productoRepository.findById(codigo)
                .map(this::convertToDTO);
    }

    @Override
    public List<ProductoDTO> getAllProductos() {
        return productoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteProducto(String codigo) {
        productoRepository.deleteById(codigo);
    }

    // Métodos de conversión entre Producto y ProductoDTO
    private ProductoDTO convertToDTO(Producto producto) {
        // Verificar que los tipos de datos sean correctos antes de llamar al constructor de ProductoDTO
        String nitEmpresa = (producto.getEmpresa() != null) ? producto.getEmpresa().getNit() : "Sin Empresa"; // Verificar null
        double precioUsd = producto.getPrecioUsd(); // Verificar que es double
        double precioEur = producto.getPrecioEur(); // Verificar que es double
        double precioLocal = producto.getPrecioLocal(); // Verificar que es double

        // Crear y retornar el DTO
        return new ProductoDTO(
                producto.getCodigo(),
                producto.getNombre(),
                producto.getCaracteristicas(),
                nitEmpresa,
                precioUsd,
                precioEur,
                precioLocal
        );
    }

    private Producto convertToEntity(ProductoDTO productoDTO) {
        Producto producto = new Producto();
        producto.setCodigo(productoDTO.getCodigo());
        producto.setNombre(productoDTO.getNombre());
        producto.setCaracteristicas(productoDTO.getCaracteristicas());
        producto.setPrecioUsd(productoDTO.getPrecioUsd());
        producto.setPrecioEur(productoDTO.getPrecioEur());
        producto.setPrecioLocal(productoDTO.getPrecioLocal());
        return producto;
    }
}