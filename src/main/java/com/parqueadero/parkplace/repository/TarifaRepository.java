package com.parqueadero.parkplace.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parqueadero.parkplace.model.Tarifa;
import com.parqueadero.parkplace.model.TipoCliente;
import com.parqueadero.parkplace.model.TipoVehiculoEnt;

public interface TarifaRepository extends JpaRepository<Tarifa, Long> {
    Optional<Tarifa> findByTipoVehiculoEnt(TipoVehiculoEnt tipoVehiculoEnt);

    Optional<Tarifa> findByTipoVehiculoEntAndTipoCliente(
            TipoVehiculoEnt tipoVehiculoEnt, TipoCliente tipoCliente);
}
