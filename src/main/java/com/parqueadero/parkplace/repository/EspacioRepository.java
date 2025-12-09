package com.parqueadero.parkplace.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parqueadero.parkplace.enums.EstadoEspacio;
import com.parqueadero.parkplace.model.Espacio;
import com.parqueadero.parkplace.model.TipoVehiculoEnt;

public interface EspacioRepository extends JpaRepository<Espacio, Long> {

        List<Espacio> findByTipoEspacioAndTipoVehiculoEntOrderByIdAsc(
                        EstadoEspacio tipoEspacio,
                        TipoVehiculoEnt tipoVehiculoEnt);

        Optional<Espacio> findFirstByTipoEspacioAndTipoVehiculoEntOrderByIdAsc(
                        EstadoEspacio tipoEspacio,
                        TipoVehiculoEnt tipoVehiculoEnt);

        Optional<Espacio> findByCodigo(String codigo);

        Integer countByTipoEspacio(EstadoEspacio tipoEspacio);

        List<Espacio> findByTipoEspacio(EstadoEspacio espacio);
}
