package com.example.plazoleta.ms_usuarios.infrastructure.repositories;

import com.example.plazoleta.ms_usuarios.infrastructure.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByIdentityDocument(String identityDocument);
}
