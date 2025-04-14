package com.example.plazoleta.ms_usuarios.owner.commons.configurations.beans;


import com.example.plazoleta.ms_usuarios.owner.application.mappers.OwnerDtoMapper;
import com.example.plazoleta.ms_usuarios.owner.domain.ports.in.OwnerServicePort;
import com.example.plazoleta.ms_usuarios.owner.domain.ports.out.OwnerPersistencePort;
import com.example.plazoleta.ms_usuarios.owner.domain.usecases.OwnerUseCase;
import com.example.plazoleta.ms_usuarios.owner.infrastructure.adapters.persistence.OwnerJpaAdapter;
import com.example.plazoleta.ms_usuarios.owner.infrastructure.mappers.OwnerEntityMapper;
import com.example.plazoleta.ms_usuarios.owner.infrastructure.repositories.mysql.OwnerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeanConfiguration {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public OwnerPersistencePort ownerPersistencePort(OwnerRepository ownerRepository, PasswordEncoder passwordEncoder, OwnerEntityMapper ownerEntityMapper) {
        return new OwnerJpaAdapter(ownerRepository, passwordEncoder, ownerEntityMapper);
    }
    @Bean
    public OwnerServicePort ownerServicePort(OwnerPersistencePort persistencePort, PasswordEncoder passwordEncoder) {
        return new OwnerUseCase(persistencePort, passwordEncoder);
    }


}
