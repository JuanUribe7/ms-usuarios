package com.example.plazoleta.ms_usuarios.owner.domain.ports.out;

import com.example.plazoleta.ms_usuarios.owner.domain.model.Owner;

public interface OwnerPersistencePort {
    Owner saveOwner(Owner owner);
    boolean existsByEmail(String email);
    boolean existsByIdentifyDocument(String identityDocument);
}
