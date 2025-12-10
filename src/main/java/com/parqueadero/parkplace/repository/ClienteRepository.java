package com.parqueadero.parkplace.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.parqueadero.parkplace.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByCedula(String cedula);

    Optional<Cliente> findByNombre(String nombre);

    boolean existsByCedula(String cedula);

    void deleteByCedula(String cedula);

    @Query("SELECT COUNT(c) FROM Cliente c")
    Integer totalClientes();

    @Query("SELECT COUNT(c) FROM Cliente c WHERE c.tipoCliente.nombre = :tipoNombre")
    Integer countByTipo(@Param("tipoNombre") String tipoNombre);

}
