package com.liteThinking.inventario.application.usecases;

import com.liteThinking.inventario.application.dto.CategoriaDTO;
import com.liteThinking.inventario.domain.model.Categoria;
import com.liteThinking.inventario.domain.model.Producto;
import com.liteThinking.inventario.domain.repository.CategoriaRepository;
import com.liteThinking.inventario.domain.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final ProductoRepository productoRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository, ProductoRepository productoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public CategoriaDTO createCategoria(CategoriaDTO categoriaDTO) {
        // Convertir CategoriaDTO a entidad Categoria
        Categoria categoria = new Categoria();
        categoria.setNombre(categoriaDTO.getNombre());

        // Si hay productos asociados, asignarlos
        if (categoriaDTO.getProductos() != null && !categoriaDTO.getProductos().isEmpty()) {
            // Buscar todos los productos por sus códigos
            List<Producto> productos = productoRepository.findAllById(categoriaDTO.getProductos());

            // Usar un Set para evitar duplicados
            Set<Producto> productosSet = new HashSet<>(productos);

            // Asignar la categoría a cada producto evitando duplicados
            for (Producto producto : productosSet) {
                // Si el producto ya tiene esta categoría, no agregarla de nuevo
                if (!producto.getCategorias().contains(categoria)) {
                    producto.getCategorias().add(categoria);
                }
                // Agregar el producto a la categoría
                categoria.getProductos().add(producto);
            }
        }

        // Guardar la categoría con los productos asociados
        Categoria nuevaCategoria = categoriaRepository.save(categoria);

        // Guardar cada producto para persistir la relación bidireccional
        for (Producto producto : categoria.getProductos()) {
            productoRepository.save(producto);
        }

        // Convertir de nuevo a DTO y retornar
        return convertToDTO(nuevaCategoria);
    }

    @Override
    public CategoriaDTO updateCategoria(Long id, CategoriaDTO categoriaDTO) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
        if (categoriaOptional.isPresent()) {
            Categoria categoriaExistente = categoriaOptional.get();
            categoriaExistente.setNombre(categoriaDTO.getNombre());

            // Actualizar la lista de productos si está presente en el DTO
            if (categoriaDTO.getProductos() != null && !categoriaDTO.getProductos().isEmpty()) {
                // Eliminar relaciones antiguas
                categoriaExistente.getProductos().clear();

                // Buscar los nuevos productos
                List<Producto> productos = productoRepository.findAllById(categoriaDTO.getProductos());

                // Agregar nuevas relaciones evitando duplicados
                for (Producto producto : productos) {
                    if (!producto.getCategorias().contains(categoriaExistente)) {
                        producto.getCategorias().add(categoriaExistente);
                    }
                    categoriaExistente.getProductos().add(producto);
                }
            }

            Categoria categoriaActualizada = categoriaRepository.save(categoriaExistente);
            return convertToDTO(categoriaActualizada);
        }
        return null; // O lanzar una excepción personalizada si se prefiere
    }

    @Override
    public Optional<CategoriaDTO> getCategoriaById(Long id) {
        return categoriaRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Override
    public List<CategoriaDTO> getAllCategorias() {
        return categoriaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }

    // Métodos de conversión entre Categoria y CategoriaDTO
    private CategoriaDTO convertToDTO(Categoria categoria) {
        List<String> productosIds = categoria.getProductos().stream()
                .map(Producto::getCodigo)
                .collect(Collectors.toList());

        return new CategoriaDTO(
                categoria.getId(),
                categoria.getNombre(),
                productosIds // Lista de códigos de productos asociados
        );
    }

    private Categoria convertToEntity(CategoriaDTO categoriaDTO) {
        Categoria categoria = new Categoria();
        categoria.setId(categoriaDTO.getId());
        categoria.setNombre(categoriaDTO.getNombre());

        // Convertir la lista de códigos de productos a la lista de productos si es necesario
        if (categoriaDTO.getProductos() != null && !categoriaDTO.getProductos().isEmpty()) {
            List<Producto> productos = productoRepository.findAllById(categoriaDTO.getProductos());
            categoria.setProductos(new HashSet<>(productos));
        }

        return categoria;
    }
}
