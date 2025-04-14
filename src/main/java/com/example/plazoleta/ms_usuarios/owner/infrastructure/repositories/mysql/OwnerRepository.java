package com.example.plazoleta.ms_usuarios.owner.infrastructure.repositories.mysql;


import com.example.plazoleta.ms_usuarios.owner.infrastructure.entities.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<OwnerEntity, Long> {
    boolean existsByEmail(String email);
    boolean existsByIdentityDocument(String identityDocument);
}
