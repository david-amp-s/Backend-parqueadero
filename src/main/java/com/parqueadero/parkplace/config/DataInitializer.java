package com.parqueadero.parkplace.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.parqueadero.parkplace.enums.EstadoEspacio;
import com.parqueadero.parkplace.enums.TipoVehiculo;
import com.parqueadero.parkplace.exception.RolNoEncontrado;
import com.parqueadero.parkplace.model.Espacio;
import com.parqueadero.parkplace.model.FormaPago;
import com.parqueadero.parkplace.model.Rol;
import com.parqueadero.parkplace.model.Tarifa;
import com.parqueadero.parkplace.model.Usuario;
import com.parqueadero.parkplace.repository.EspacioRepository;
import com.parqueadero.parkplace.repository.FormaPagoRepository;
import com.parqueadero.parkplace.repository.RolRepository;
import com.parqueadero.parkplace.repository.TarifaRepository;
import com.parqueadero.parkplace.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
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
    CommandLineRunner initUser(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder,
            RolRepository rolRepository) {
        return args -> {
            usuarioRepository.findByEmail("admin@parkplace.com")
                    .orElseGet(() -> {
                        Rol rol = rolRepository.findByNombre("ADMIN")
                                .orElseThrow(() -> new RolNoEncontrado("ADMIN"));

                        Usuario usuario = Usuario.builder()
                                .nombre("Administrador principal")
                                .email("admin@parkplace.com")
                                .contraseña(passwordEncoder.encode("|"))
                                .rol(rol)
                                .build();

                        return usuarioRepository.save(usuario);
                    });
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
            if (formaPagoRepository.findByDescripcion("TARJETA").isEmpty()) {
                formaPagoRepository.save(FormaPago.builder().descripcion("TARJETA").build());
            }
            if (formaPagoRepository.findByDescripcion("TRANSFERENCIA").isEmpty()) {
                formaPagoRepository.save(FormaPago.builder().descripcion("TRANSFERENCIA").build());
            }
        };
    }

    @Bean
    CommandLineRunner initEspacios(EspacioRepository espacioRepository) {
        return args -> {
            if (espacioRepository.count() == 0) {
                Integer espacios = 10;
                for (Integer i = 0; i <= espacios; i++) {
                    Espacio espacio = Espacio.builder()
                            .codigo("A" + i)
                            .tipoEspacio(EstadoEspacio.DISPONIBLE)
                            .tipoVehiculoPermitido(TipoVehiculo.CARRO)
                            .build();
                    espacioRepository.save(espacio);
                }

                for (Integer i = 0; i <= espacios; i++) {
                    Espacio espacio = Espacio.builder()
                            .codigo("B" + i)
                            .tipoEspacio(EstadoEspacio.DISPONIBLE)
                            .tipoVehiculoPermitido(TipoVehiculo.MOTO)
                            .build();
                    espacioRepository.save(espacio);
                }

            }
        };
    }
}
