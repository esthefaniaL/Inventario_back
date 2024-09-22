package com.liteThinking.inventario.application.usecases;

import com.liteThinking.inventario.application.dto.ClienteDTO;
import com.liteThinking.inventario.domain.model.Cliente;
import com.liteThinking.inventario.domain.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public ClienteDTO createCliente(ClienteDTO clienteDTO) {
        Cliente cliente = convertToEntity(clienteDTO);
        Cliente nuevoCliente = clienteRepository.save(cliente);
        return convertToDTO(nuevoCliente);
    }

    @Override
    public ClienteDTO updateCliente(Long id, ClienteDTO clienteDTO) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isPresent()) {
            Cliente clienteExistente = clienteOptional.get();
            clienteExistente.setNombre(clienteDTO.getNombre());
            clienteExistente.setCorreo(clienteDTO.getCorreo());
            clienteExistente.setTelefono(clienteDTO.getTelefono());
            Cliente clienteActualizado = clienteRepository.save(clienteExistente);
            return convertToDTO(clienteActualizado);
        }
        return null; // O lanzar una excepción personalizada si se prefiere
    }

    @Override
    public Optional<ClienteDTO> getClienteById(Long id) {
        return clienteRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Override
    public List<ClienteDTO> getAllClientes() {
        return clienteRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    // Métodos de conversión entre Cliente y ClienteDTO
    private ClienteDTO convertToDTO(Cliente cliente) {
        return new ClienteDTO(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getCorreo(),
                cliente.getTelefono()
        );
    }

    private Cliente convertToEntity(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.getId());
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setCorreo(clienteDTO.getCorreo());
        cliente.setTelefono(clienteDTO.getTelefono());
        return cliente;
    }
}