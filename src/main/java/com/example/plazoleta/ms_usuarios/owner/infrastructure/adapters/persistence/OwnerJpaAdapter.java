package com.example.plazoleta.ms_usuarios.owner.infrastructure.adapters.persistence;

import com.example.plazoleta.ms_usuarios.owner.domain.model.Owner;
import com.example.plazoleta.ms_usuarios.owner.domain.ports.out.OwnerPersistencePort;
import com.example.plazoleta.ms_usuarios.owner.infrastructure.entities.OwnerEntity;
import com.example.plazoleta.ms_usuarios.owner.infrastructure.mappers.OwnerEntityMapper;
import com.example.plazoleta.ms_usuarios.owner.infrastructure.repositories.mysql.OwnerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

public class OwnerJpaAdapter implements OwnerPersistencePort {
    private final OwnerRepository ownerRepository;
    private final PasswordEncoder passwordEncoder;
    private final OwnerEntityMapper ownerEntityMapper;

    public OwnerJpaAdapter(OwnerRepository ownerRepository, PasswordEncoder passwordEncoder, OwnerEntityMapper ownerEntityMapper) {
        this.ownerRepository = ownerRepository;
        this.passwordEncoder = passwordEncoder;
        this.ownerEntityMapper = ownerEntityMapper;
    }

    @Override
    public Owner saveOwner(Owner owner) {
        OwnerEntity entity = ownerEntityMapper.toEntity(owner);
        entity.setPassword(passwordEncoder.encode(owner.getPassword()));
        OwnerEntity saved = ownerRepository.save(entity);
        return ownerEntityMapper.toOwner(saved);
    }

    @Override
    public boolean existsByEmail(String email) {
        return ownerRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByIdentifyDocument(String identityDocument) {
        return ownerRepository.existsByIdentityDocument(identityDocument);
    }

}
