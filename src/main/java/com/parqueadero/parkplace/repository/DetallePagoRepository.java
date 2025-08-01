package com.parqueadero.parkplace.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parqueadero.parkplace.model.DetallePago;

public interface DetallePagoRepository extends JpaRepository<DetallePago, Long> {
    List<DetallePago> findByFacturaId(Long facturaId);
}
