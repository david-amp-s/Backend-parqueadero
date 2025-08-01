package com.parqueadero.parkplace.Service;

import java.time.LocalDate;
import java.util.List;

import com.parqueadero.parkplace.dto.FacturaCreateDto;
import com.parqueadero.parkplace.dto.FacturaDto;

public interface FacturaService {
    FacturaDto generarFactura(FacturaCreateDto dto);

    FacturaDto obtenerFactura(Long id);

    List<FacturaDto> listarTodasFacturas();

    List<FacturaDto> buscarFacturasPorFecha(LocalDate fecha);

    List<FacturaDto> buscarFacturaPorUsuario(Long id);

}
