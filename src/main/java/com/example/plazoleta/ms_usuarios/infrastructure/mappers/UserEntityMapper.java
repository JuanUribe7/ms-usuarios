package com.example.plazoleta.ms_usuarios.infrastructure.mappers;

import com.example.plazoleta.ms_usuarios.domain.model.User;
import com.example.plazoleta.ms_usuarios.infrastructure.entities.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {
    User toModel(UserEntity entity);
    UserEntity toEntity(User user);
    List<User> toModelList(List<UserEntity> entities);
}
