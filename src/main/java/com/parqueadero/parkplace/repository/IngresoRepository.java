package com.parqueadero.parkplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parqueadero.parkplace.model.Ingreso;
import java.util.List;
import java.time.LocalDateTime;

public interface IngresoRepository extends JpaRepository<Ingreso, Long> {
    List<Ingreso> findByFechaingresoBetween(LocalDateTime inicio, LocalDateTime fin);

    List<Ingreso> findByVehiculo_Placa(String placa);
}
