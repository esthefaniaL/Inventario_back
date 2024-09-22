package com.liteThinking.inventario.domain.repository;

import com.liteThinking.inventario.domain.model.Empresa;
import java.util.Optional;
import java.util.List;

public interface EmpresaRepository {
    Optional<Empresa> findByNit(String nit);
    List<Empresa> findAll();
    Empresa save(Empresa empresa);
    void deleteByNit(String nit);
}
