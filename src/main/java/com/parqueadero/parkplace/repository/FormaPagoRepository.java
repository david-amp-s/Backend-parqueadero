package com.parqueadero.parkplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parqueadero.parkplace.model.FormaPago;
import java.util.Optional;

public interface FormaPagoRepository extends JpaRepository<FormaPago, Long> {
    Optional<FormaPago> findByDescripcion(String descripcion);
}
