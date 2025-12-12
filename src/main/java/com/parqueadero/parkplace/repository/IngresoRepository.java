package com.parqueadero.parkplace.repository;

import com.parqueadero.parkplace.model.Espacio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.parqueadero.parkplace.model.Ingreso;
import com.parqueadero.parkplace.model.Vehiculo;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

public interface IngresoRepository extends JpaRepository<Ingreso, Long> {
    List<Ingreso> findByFechaIngresoBetween(LocalDateTime inicio, LocalDateTime fin);

    List<Ingreso> findByVehiculo_Placa(String placa);

    Optional<Ingreso> findFirstByVehiculoOrderByFechaIngresoDesc(Vehiculo vehiculo);

    List<Ingreso> findTop10ByOrderByFechaIngresoDesc();

    @Query("SELECT i " +
            "FROM Ingreso i " +
            "WHERE i.vehiculo.ingreso = true " +
            "AND i.fechaIngreso = (" +
            "    SELECT MAX(i2.fechaIngreso) " +
            "    FROM Ingreso i2 " +
            "    WHERE i2.vehiculo.id = i.vehiculo.id" +
            ")")
    List<Ingreso> findIngresosOcupados();






}
