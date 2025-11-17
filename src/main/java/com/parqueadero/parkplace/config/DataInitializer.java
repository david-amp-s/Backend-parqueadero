package com.parqueadero.parkplace.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.parqueadero.parkplace.enums.EstadoEspacio;
import com.parqueadero.parkplace.exception.RolNoEncontrado;
import com.parqueadero.parkplace.model.Cliente;
import com.parqueadero.parkplace.model.Espacio;
import com.parqueadero.parkplace.model.FormaPago;
import com.parqueadero.parkplace.model.Rol;
import com.parqueadero.parkplace.model.Tarifa;
import com.parqueadero.parkplace.model.TipoVehiculoEnt;
import com.parqueadero.parkplace.model.Usuario;
import com.parqueadero.parkplace.repository.ClienteRepository;
import com.parqueadero.parkplace.repository.EspacioRepository;
import com.parqueadero.parkplace.repository.FormaPagoRepository;
import com.parqueadero.parkplace.repository.RolRepository;
import com.parqueadero.parkplace.repository.TarifaRepository;
import com.parqueadero.parkplace.repository.TipoVehiculoEntRepository;
import com.parqueadero.parkplace.repository.UsuarioRepository;
import com.parqueadero.parkplace.repository.VehiculoRepository;

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
                                .contraseña(passwordEncoder.encode("admin123"))
                                .rol(rol)
                                .build();

                        return usuarioRepository.save(usuario);
                    });
        };
    }

    @Bean
    CommandLineRunner initTarifas(
            TarifaRepository tarifaRepository,
            TipoVehiculoEntRepository tipoVehiculoEntRepository) {

        return args -> {

            // Obtener todos los tipos de vehículo registrados en BD
            List<TipoVehiculoEnt> tipos = tipoVehiculoEntRepository.findAll();

            for (TipoVehiculoEnt tipo : tipos) {

                // Revisar si ya existe una tarifa para ese tipo
                if (tarifaRepository.findByTipoVehiculoEnt(tipo).isEmpty()) {

                    tarifaRepository.save(
                            Tarifa.builder()
                                    .tipoVehiculoEnt(tipo)
                                    .valorHora(0)
                                    .valorMinuto(270)
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
    CommandLineRunner initEspacios(EspacioRepository espacioRepository,
            TipoVehiculoEntRepository tipoVehiculoEntRepository) {
        return args -> {

            if (espacioRepository.count() == 0) {

                // Buscar los tipos de vehículo: CARRO y MOTO
                TipoVehiculoEnt carro = tipoVehiculoEntRepository.findByTipo("CARRO")
                        .orElseThrow(() -> new RuntimeException("Tipo CARRO no encontrado"));

                TipoVehiculoEnt moto = tipoVehiculoEntRepository.findByTipo("MOTO")
                        .orElseThrow(() -> new RuntimeException("Tipo MOTO no encontrado"));

                int espacios = 10;

                // Espacios para carros
                for (int i = 0; i <= espacios; i++) {
                    Espacio espacio = Espacio.builder()
                            .codigo("A" + i)
                            .tipoEspacio(EstadoEspacio.DISPONIBLE)
                            .tipoVehiculoEnt(carro)
                            .build();

                    espacioRepository.save(espacio);
                }

                // Espacios para motos
                for (int i = 0; i <= espacios; i++) {
                    Espacio espacio = Espacio.builder()
                            .codigo("B" + i)
                            .tipoEspacio(EstadoEspacio.DISPONIBLE)
                            .tipoVehiculoEnt(moto)
                            .build();

                    espacioRepository.save(espacio);
                }
            }
        };
    }

    @Bean
    CommandLineRunner initVehiculosPar(TipoVehiculoEntRepository tipoVehiculoEntRepository) {
        return args -> {
            if (tipoVehiculoEntRepository.findByTipo("CARRO").isEmpty()) {
                tipoVehiculoEntRepository.save(
                        TipoVehiculoEnt.builder()
                                .tipo("CARRO")
                                .build());
            }
            if (tipoVehiculoEntRepository.findByTipo("MOTO").isEmpty()) {
                tipoVehiculoEntRepository.save(
                        TipoVehiculoEnt.builder()
                                .tipo("MOTO")
                                .build());
            }
        };
    }

    @Bean
    CommandLineRunner initUserGuest(ClienteRepository clienteRepository) {
        return args -> {
            if (clienteRepository.findByNombre("INVITADO").isEmpty()) {
                clienteRepository.save(
                        Cliente.builder()
                                .nombre("INVITADO")
                                .cedula("0")
                                .correo("invitado.com")
                                .build());
            }
        };
    }
}
