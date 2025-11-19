package com.parqueadero.parkplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parqueadero.parkplace.model.TipoCliente;
import java.util.Optional;

public interface TipoClienteRepository extends JpaRepository<TipoCliente, Long> {
    Optional<TipoCliente> findByNombre(String nombre);
}
