package com.example.plazoleta.ms_usuarios.owner.application.mappers;


import com.example.plazoleta.ms_usuarios.owner.application.dto.request.OwnerRequestDto;
import com.example.plazoleta.ms_usuarios.owner.application.dto.response.OwnerResponseDto;
import com.example.plazoleta.ms_usuarios.owner.domain.model.Owner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface OwnerDtoMapper {
    @Mapping(target="id", ignore=true)
    @Mapping(target="role", ignore=true)
    Owner toOwner(OwnerRequestDto request);

    OwnerResponseDto toResponse(Owner owner);
}
