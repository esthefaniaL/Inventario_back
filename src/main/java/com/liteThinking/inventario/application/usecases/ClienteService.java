package com.liteThinking.inventario.application.usecases;

import com.liteThinking.inventario.application.dto.ClienteDTO;
import java.util.List;
import java.util.Optional;

public interface ClienteService {
    ClienteDTO createCliente(ClienteDTO clienteDTO);
    ClienteDTO updateCliente(Long id, ClienteDTO clienteDTO);
    Optional<ClienteDTO> getClienteById(Long id);
    List<ClienteDTO> getAllClientes();
    void deleteCliente(Long id);
}