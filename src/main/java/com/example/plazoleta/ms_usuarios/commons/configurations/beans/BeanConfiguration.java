package com.example.plazoleta.ms_usuarios.commons.configurations.beans;

import com.example.plazoleta.ms_usuarios.domain.ports.in.OwnerServicePort;
import com.example.plazoleta.ms_usuarios.domain.ports.out.UserPersistencePort;
import com.example.plazoleta.ms_usuarios.domain.usecases.OwnerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class BeanConfiguration {

    @Bean(name = "ownerServicePort")
    public OwnerServicePort ownerServicePort(UserPersistencePort userPersistencePort) {
        return new OwnerUseCase(userPersistencePort);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
