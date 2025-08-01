package com.parqueadero.parkplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parqueadero.parkplace.model.Factura;

public interface FacturaRepository extends JpaRepository<Factura, Long> {

}
