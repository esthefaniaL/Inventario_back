package com.liteThinking.inventario.application.usecases;

import com.liteThinking.inventario.application.dto.EmpresaDTO;

import java.util.List;
import java.util.Optional;


public interface EmpresaService {
    EmpresaDTO createEmpresa(EmpresaDTO empresaDTO); // Cambiar a EmpresaDTO
    EmpresaDTO updateEmpresa(String nit, EmpresaDTO empresaDTO); // Cambiar a EmpresaDTO
    Optional<EmpresaDTO> getEmpresaByNit(String nit); // Cambiar a EmpresaDTO
    List<EmpresaDTO> getAllEmpresas(); // Cambiar a EmpresaDTO
    void deleteEmpresa(String nit);
}