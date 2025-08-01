package com.parqueadero.parkplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parqueadero.parkplace.model.Factura;
import com.parqueadero.parkplace.model.Usuario;

import java.util.List;
import java.time.LocalDateTime;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
    List<Factura> findByFecha(LocalDateTime fecha);

    List<Factura> findByUsuario(Usuario usuario);

    List<Factura> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin);;
}
