package com.example.plazoleta.ms_usuarios.owner.infrastructure.mappers;

import com.example.plazoleta.ms_usuarios.owner.domain.model.Owner;
import com.example.plazoleta.ms_usuarios.owner.infrastructure.entities.OwnerEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface OwnerEntityMapper {
    @Mapping(target = "id", ignore = true)
    OwnerEntity toEntity(Owner owner);

    Owner toOwner(OwnerEntity entity);
}