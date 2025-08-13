package com.parqueadero.parkplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.parqueadero.parkplace.model.Factura;
import com.parqueadero.parkplace.model.Usuario;

import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
    List<Factura> findByFecha(LocalDateTime fecha);

    List<Factura> findByUsuario(Usuario usuario);

    List<Factura> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin);

    Integer countByFechaBetween(LocalDateTime inicio, LocalDateTime fin);

    @Query(value = "SELECT COALESCE(SUM(f.total), 0) FROM facturas f WHERE DATE(f.fecha) = CURRENT_DATE", nativeQuery = true)
    BigDecimal obtenerTotalDiario();

    @Query(value = "SELECT COALESCE(SUM(f.total), 0) FROM facturas f WHERE EXTRACT(YEAR FROM f.fecha) = EXTRACT(YEAR FROM CURRENT_DATE) AND EXTRACT(MONTH FROM f.fecha) = EXTRACT(MONTH FROM CURRENT_DATE)", nativeQuery = true)
    BigDecimal obtenerTotalMensual();

    @Query(value = "SELECT COALESCE(SUM(f.total), 0) FROM facturas f WHERE EXTRACT(YEAR FROM f.fecha) = EXTRACT(YEAR FROM CURRENT_DATE)", nativeQuery = true)
    BigDecimal obtenerTotalAnual();

}
