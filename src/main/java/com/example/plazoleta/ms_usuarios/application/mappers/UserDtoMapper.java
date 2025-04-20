package com.example.plazoleta.ms_usuarios.application.mappers;

import com.example.plazoleta.ms_usuarios.domain.model.User;
import com.example.plazoleta.ms_usuarios.application.dto.request.UserRequestDto;
import com.example.plazoleta.ms_usuarios.application.dto.response.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {
    @Mapping(target="id", ignore=true)
    @Mapping(target = "role", source = "role")
    User toModel(UserRequestDto dto);


    UserResponseDto toResponseDto(User model);
}
