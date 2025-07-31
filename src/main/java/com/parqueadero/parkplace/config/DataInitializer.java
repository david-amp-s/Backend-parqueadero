package com.parqueadero.parkplace.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.parqueadero.parkplace.enums.TipoVehiculo;
import com.parqueadero.parkplace.model.FormaPago;
import com.parqueadero.parkplace.model.Rol;
import com.parqueadero.parkplace.model.Tarifa;
import com.parqueadero.parkplace.repository.FormaPagoRepository;
import com.parqueadero.parkplace.repository.RolRepository;
import com.parqueadero.parkplace.repository.TarifaRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initRoles(RolRepository rolRepository) {
        return args -> {
            if (rolRepository.findByNombre("ADMIN").isEmpty()) {
                rolRepository.save(Rol.builder().nombre("ADMIN").build());
            }
            if (rolRepository.findByNombre("EMPLEADO").isEmpty()) {
                rolRepository.save(Rol.builder().nombre("EMPLEADO").build());
            }
        };
    }

    @Bean
    CommandLineRunner initTarifas(TarifaRepository tarifaRepository) {
        return args -> {
            for (TipoVehiculo tipo : TipoVehiculo.values()) {
                if (tarifaRepository.findByTipoVehiculo(tipo).isEmpty()) {
                    tarifaRepository.save(Tarifa.builder()
                            .tipoVehiculo(tipo)
                            .valorHora(0)
                            .valorMinuto(0)
                            .valorTarifaFija(0)
                            .build());
                }
            }
        };
    }

    @Bean
    CommandLineRunner initFormaPago(FormaPagoRepository formaPagoRepository) {
        return args -> {
            if (formaPagoRepository.findByDescripcion("EFECTIVO").isEmpty()) {
                formaPagoRepository.save(FormaPago.builder().descripcion("EFECTIVO").build());
            }
        };
    }
}
