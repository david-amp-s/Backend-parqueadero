package com.parqueadero.parkplace.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parqueadero.parkplace.model.Tarifa;
import com.parqueadero.parkplace.enums.TipoVehiculo;

public interface TarifaRepository extends JpaRepository<Tarifa, Long> {
    Optional<Tarifa> findByTipoVehiculo(TipoVehiculo tipoVehiculo);

}
