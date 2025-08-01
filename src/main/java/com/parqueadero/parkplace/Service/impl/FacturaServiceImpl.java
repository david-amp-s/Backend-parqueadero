package com.parqueadero.parkplace.Service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.parqueadero.parkplace.Service.FacturaService;
import com.parqueadero.parkplace.dto.FacturaCreateDto;
import com.parqueadero.parkplace.dto.FacturaDto;
import com.parqueadero.parkplace.repository.FacturaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FacturaServiceImpl implements FacturaService {
    private final FacturaRepository facturaRepository;

    @Override
    public FacturaDto generarFactura(FacturaCreateDto dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generarFactura'");
    }

    @Override
    public FacturaDto obtenerFactura(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerFactura'");
    }

    @Override
    public List<FacturaDto> listarTodasFacturas() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarTodasFacturas'");
    }

    @Override
    public List<FacturaDto> buscarFacturasPorFecha() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarFacturasPorFecha'");
    }

    @Override
    public List<FacturaDto> buscarFacturaPorUsuario() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarFacturaPorUsuario'");
    }

}
