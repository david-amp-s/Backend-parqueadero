package com.parqueadero.parkplace.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parqueadero.parkplace.model.TipoVehiculoEnt;

public interface TipoVehiculoEntRepository extends JpaRepository<TipoVehiculoEnt, Long> {
    Optional<TipoVehiculoEnt> findByTipo(String tipo);
}
