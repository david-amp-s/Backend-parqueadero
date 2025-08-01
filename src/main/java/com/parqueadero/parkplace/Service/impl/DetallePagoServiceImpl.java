package com.parqueadero.parkplace.Service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.parqueadero.parkplace.Service.DetallePagoService;
import com.parqueadero.parkplace.dto.DetallePagoCreateDto;
import com.parqueadero.parkplace.dto.DetallePagoDto;
import com.parqueadero.parkplace.exception.FacturaNoEncontradaException;
import com.parqueadero.parkplace.exception.FormapagoNoEncontrada;
import com.parqueadero.parkplace.exception.PagoExcedidoException;
import com.parqueadero.parkplace.model.DetallePago;
import com.parqueadero.parkplace.model.Factura;
import com.parqueadero.parkplace.model.FormaPago;
import com.parqueadero.parkplace.repository.DetallePagoRepository;
import com.parqueadero.parkplace.repository.FacturaRepository;
import com.parqueadero.parkplace.repository.FormaPagoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DetallePagoServiceImpl implements DetallePagoService {
    private final DetallePagoRepository detallePagoRepository;
    private final FacturaRepository facturaRepository;
    private final FormaPagoRepository formaPagoRepository;

    private DetallePagoDto conversorDto(DetallePago dto) {
        return new DetallePagoDto(dto.getId(), dto.getFactura().getId(), dto.getFormaPago().getDescripcion(),
                dto.getMonto());
    }

    @Override
    public DetallePagoDto registrarDetallePago(DetallePagoCreateDto dto) {
        Factura factura = facturaRepository.findById(dto.factura_id())
                .orElseThrow(() -> new FacturaNoEncontradaException());
        FormaPago formaPago = formaPagoRepository.findById(dto.formaPago_id())
                .orElseThrow(() -> new FormapagoNoEncontrada());

        BigDecimal totalPagado = detallePagoRepository.findByFacturaId(dto.factura_id())
                .stream()
                .map(DetallePago::getMonto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal nuevoTotal = totalPagado.add(dto.monto());
        if (nuevoTotal.compareTo(factura.getTotal()) > 0) {
            throw new PagoExcedidoException();
        }

        DetallePago nuevoPago = DetallePago.builder()
                .factura(factura)
                .formaPago(formaPago)
                .monto(dto.monto())
                .build();

        detallePagoRepository.save(nuevoPago);
        return conversorDto(nuevoPago);
    }

    @Override
    public List<DetallePagoDto> registrarVariosPagos(List<DetallePagoCreateDto> pagos) {

    }

    @Override
    public List<DetallePagoDto> obtenerPagosPorFactura(Long facturaId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerPagosPorFactura'");
    }

    @Override
    public DetallePagoDto obtenerDetallePago(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerDetallePago'");
    }

}
