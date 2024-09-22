package com.liteThinking.inventario.application.usecases;

import com.liteThinking.inventario.application.dto.EmpresaDTO;
import com.liteThinking.inventario.application.dto.ProductoDTO;
import com.liteThinking.inventario.domain.model.Empresa;
import com.liteThinking.inventario.domain.model.Producto;
import com.liteThinking.inventario.domain.repository.EmpresaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmpresaServiceImpl implements EmpresaService {
    private final EmpresaRepository empresaRepository;

    public EmpresaServiceImpl(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    @Override
    public EmpresaDTO createEmpresa(EmpresaDTO empresaDTO) {
        // Convertimos el DTO a una entidad
        Empresa empresa = convertToEntity(empresaDTO);
        // Guardamos la entidad y la convertimos nuevamente a DTO
        Empresa nuevaEmpresa = empresaRepository.save(empresa);
        return convertToDTO(nuevaEmpresa);
    }

    @Override
    public EmpresaDTO updateEmpresa(String nit, EmpresaDTO empresaDTO) {
        // Buscamos la empresa por NIT
        Optional<Empresa> empresaOptional = empresaRepository.findByNit(nit);
        if (empresaOptional.isPresent()) {
            // Si la empresa existe, actualizamos sus propiedades
            Empresa empresaExistente = empresaOptional.get();
            empresaExistente.setNombre(empresaDTO.getNombre());
            empresaExistente.setDireccion(empresaDTO.getDireccion());
            empresaExistente.setTelefono(empresaDTO.getTelefono());

            // Guardamos la empresa actualizada y la convertimos a DTO
            Empresa empresaActualizada = empresaRepository.save(empresaExistente);
            return convertToDTO(empresaActualizada);
        }
        return null; // O lanzar una excepción personalizada si se prefiere
    }

    @Override
    public Optional<EmpresaDTO> getEmpresaByNit(String nit) {
        // Buscamos la empresa por NIT y la convertimos a DTO
        return empresaRepository.findByNit(nit)
                .map(this::convertToDTO);
    }

    @Override
    public List<EmpresaDTO> getAllEmpresas() {
        // Obtenemos todas las empresas y las convertimos a DTOs
        return empresaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteEmpresa(String nit) {
        // Eliminamos la empresa por NIT
        empresaRepository.deleteByNit(nit);
    }

    // Métodos de conversión entre Empresa y EmpresaDTO
    private EmpresaDTO convertToDTO(Empresa empresa) {
        // Convertimos cada producto asociado a ProductoDTO
        List<ProductoDTO> productosDTO = empresa.getProductos().stream()
                .map(this::convertProductoToDTO)
                .collect(Collectors.toList());

        // Creamos el DTO de Empresa con su lista de productos
        EmpresaDTO empresaDTO = new EmpresaDTO(
                empresa.getNit(),
                empresa.getNombre(),
                empresa.getDireccion(),
                empresa.getTelefono(),
                productosDTO // Añadimos la lista de productos al constructor
        );

        return empresaDTO;
    }

    private ProductoDTO convertProductoToDTO(Producto producto) {
        // Convertimos un Producto a ProductoDTO
        return new ProductoDTO(
                producto.getCodigo(),
                producto.getNombre(),
                producto.getCaracteristicas(),
                producto.getPrecioUsd(),
                producto.getPrecioEur(),
                producto.getPrecioLocal()
        );
    }

    private Empresa convertToEntity(EmpresaDTO empresaDTO) {
        // Convertimos un EmpresaDTO a una entidad Empresa
        Empresa empresa = new Empresa();
        empresa.setNit(empresaDTO.getNit());
        empresa.setNombre(empresaDTO.getNombre());
        empresa.setDireccion(empresaDTO.getDireccion());
        empresa.setTelefono(empresaDTO.getTelefono());

        // Aquí puedes mapear los productos si es necesario, aunque normalmente no se hace en esta capa
        return empresa;
    }
}
