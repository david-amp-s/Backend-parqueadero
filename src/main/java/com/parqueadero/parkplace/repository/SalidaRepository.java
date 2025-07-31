package com.parqueadero.parkplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parqueadero.parkplace.model.Salida;

public interface SalidaRepository extends JpaRepository<Salida, Long> {

}
