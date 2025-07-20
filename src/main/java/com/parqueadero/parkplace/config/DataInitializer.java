package com.parqueadero.parkplace.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.parqueadero.parkplace.model.Rol;
import com.parqueadero.parkplace.repository.RolRepository;

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
}
