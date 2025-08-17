package com.parqueadero.parkplace.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parqueadero.parkplace.model.Salida;
import com.parqueadero.parkplace.model.Ingreso;

public interface SalidaRepository extends JpaRepository<Salida, Long> {

    Optional<Salida> findTopByIngresoOrderByFechaSalidaDesc(Ingreso ingreso);

    void deleteByIngreso(Ingreso ingreso);
}
