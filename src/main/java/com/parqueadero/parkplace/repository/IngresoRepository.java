package com.parqueadero.parkplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parqueadero.parkplace.model.Ingreso;
import com.parqueadero.parkplace.model.Vehiculo;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

public interface IngresoRepository extends JpaRepository<Ingreso, Long> {
    List<Ingreso> findByFechaIngresoBetween(LocalDateTime inicio, LocalDateTime fin);

    List<Ingreso> findByVehiculo_Placa(String placa);

    Optional<Ingreso> findFirstByVehiculoOrderByFechaIngresoDesc(Vehiculo vehiculo);
}
