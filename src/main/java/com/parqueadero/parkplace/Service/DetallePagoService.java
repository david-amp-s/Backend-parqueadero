package com.parqueadero.parkplace.Service;

import java.util.List;

import com.parqueadero.parkplace.dto.DetallePagoCreateDto;
import com.parqueadero.parkplace.dto.DetallePagoDto;

public interface DetallePagoService {
    DetallePagoDto registrarDetallePago(DetallePagoCreateDto dto);

    List<DetallePagoDto> registrarVariosPagos(List<DetallePagoCreateDto> pagos);

    List<DetallePagoDto> obtenerPagosPorFactura(Long facturaId);

    DetallePagoDto obtenerDetallePago(Long id);

}
