package com.example.plazoleta.ms_usuarios.owner.domain.ports.in;

import com.example.plazoleta.ms_usuarios.owner.domain.model.Owner;

public interface OwnerServicePort {
    Owner createOwner(Owner owner);
}
