package com.parqueadero.parkplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parqueadero.parkplace.model.Salida;
import com.parqueadero.parkplace.model.Ingreso;

public interface SalidaRepository extends JpaRepository<Salida, Long> {
    void deleteByIngreso(Ingreso ingreso);
}
