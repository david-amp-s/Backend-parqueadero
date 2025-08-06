package com.parqueadero.parkplace.Service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.parqueadero.parkplace.Service.FacturaService;
import com.parqueadero.parkplace.dto.DetallePagoInput;
import com.parqueadero.parkplace.dto.FacturaCreateDto;
import com.parqueadero.parkplace.dto.FacturaDto;
import com.parqueadero.parkplace.exception.FacturaNoEncontradaException;
import com.parqueadero.parkplace.exception.SalidaNoEncontrada;
import com.parqueadero.parkplace.exception.UsuarioNoEncontrado;
import com.parqueadero.parkplace.model.DetallePago;
import com.parqueadero.parkplace.model.Factura;
import com.parqueadero.parkplace.model.FormaPago;
import com.parqueadero.parkplace.model.Salida;
import com.parqueadero.parkplace.model.Usuario;
import com.parqueadero.parkplace.repository.DetallePagoRepository;
import com.parqueadero.parkplace.repository.FacturaRepository;
import com.parqueadero.parkplace.repository.FormaPagoRepository;
import com.parqueadero.parkplace.repository.SalidaRepository;
import com.parqueadero.parkplace.repository.UsuarioRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class FacturaServiceImpl implements FacturaService {
    private final FacturaRepository facturaRepository;
    private final SalidaRepository salidaRepository;
    private final UsuarioRepository usuarioRepository;
    private final DetallePagoRepository detallePagoRepository;
    private final FormaPagoRepository formaPagoRepository;

    private List<DetallePagoInput> convrDetallePago(List<DetallePago> dto) {
        return dto.stream().map(d -> new DetallePagoInput(d.getId(), d.getMonto()))
                .toList();
    }

    private FacturaDto converDto(Factura dto) {
        return new FacturaDto(dto.getId(), dto.getSalida().getId(), dto.getUsuario().getId(), dto.getTotal(),
                convrDetallePago(dto.getPagos()), dto.getFecha());
    }

    @Override
    @Transactional
    public FacturaDto generarFactura(FacturaCreateDto dto) {
        Salida salida = salidaRepository.findById(dto.salida_id()).orElseThrow(() -> new SalidaNoEncontrada());
        Usuario usuario = usuarioRepository.findById(dto.usuario_id()).orElseThrow(() -> new UsuarioNoEncontrado());
        BigDecimal total = new BigDecimal(salida.getTotal());

        BigDecimal sumaPagos = dto.pagos().stream().map(p -> p.pago())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        if (!sumaPagos.equals(total)) {
            throw new RuntimeException("La suma de los pagos no coincide con el total de la factura");
        }
        LocalDateTime fecha = LocalDateTime.now();

        Factura factura = Factura.builder()
                .salida(salida)
                .usuario(usuario)
                .total(total)
                .fecha(fecha)
                .build();
        facturaRepository.save(factura);

        List<DetallePago> pagos = dto.pagos().stream().map(p -> {
            FormaPago formaPago = formaPagoRepository.findById(p.formaPagoId())
                    .orElseThrow(() -> new RuntimeException("Forma de pago no encontrada"));
            return DetallePago.builder()
                    .factura(factura)
                    .formaPago(formaPago)
                    .monto(p.pago())
                    .build();
        }).toList();

        detallePagoRepository.saveAll(pagos);

        factura.setPagos(pagos);

        return converDto(factura);
    }

    @Override
    public FacturaDto obtenerFactura(Long id) {
        Factura factura = facturaRepository.findById(id).orElseThrow(() -> new FacturaNoEncontradaException());
        return converDto(factura);
    }

    @Override
    public List<FacturaDto> listarTodasFacturas() {
        return facturaRepository.findAll().stream()
                .map(f -> converDto(f)).toList();
    }

    @Override
    public List<FacturaDto> buscarFacturasPorFecha(LocalDate fecha) {
        LocalDateTime inicio = fecha.atStartOfDay();
        LocalDateTime fin = fecha.atTime(LocalTime.MAX);
        return facturaRepository.findByFechaBetween(inicio, fin)
                .stream()
                .map(this::converDto)
                .toList();
    }

    @Override
    public List<FacturaDto> buscarFacturaPorUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontrado());

        return facturaRepository.findByUsuario(usuario)
                .stream()
                .map(this::converDto)
                .toList();
    }

}
