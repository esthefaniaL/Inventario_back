package com.liteThinking.inventario.infrastructure.database.jpa;

import com.liteThinking.inventario.domain.model.Empresa;
import com.liteThinking.inventario.domain.repository.EmpresaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaEmpresaRepository extends JpaRepository<Empresa, String>, EmpresaRepository {
    // Aquí puedes definir consultas personalizadas si es necesario.
}
