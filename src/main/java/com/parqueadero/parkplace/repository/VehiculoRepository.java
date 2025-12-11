package com.parqueadero.parkplace.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parqueadero.parkplace.model.Vehiculo;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
    Optional<Vehiculo> findByPlaca(String placa);

    boolean existsByPlaca(String placa);

    void deleteByPlaca(String placa);

    long countByIngresoTrueAndSalidaFalse();
}
