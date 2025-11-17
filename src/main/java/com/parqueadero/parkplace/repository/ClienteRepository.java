package com.parqueadero.parkplace.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parqueadero.parkplace.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByCedula(String cedula);

    Optional<Cliente> findByNombre(String nombre);

    boolean existsByCedula(String cedula);

    void deleteByCedula(String cedula);
}
