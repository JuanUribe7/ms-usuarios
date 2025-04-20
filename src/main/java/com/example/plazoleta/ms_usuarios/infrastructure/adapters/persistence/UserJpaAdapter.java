package com.example.plazoleta.ms_usuarios.infrastructure.adapters.persistence;

import com.example.plazoleta.ms_usuarios.domain.exceptions.InvalidEmailException;
import com.example.plazoleta.ms_usuarios.domain.exceptions.InvalidIdentityDocumentException;
import com.example.plazoleta.ms_usuarios.domain.model.User;
import com.example.plazoleta.ms_usuarios.domain.ports.out.UserPersistencePort;
import com.example.plazoleta.ms_usuarios.infrastructure.entities.UserEntity;
import com.example.plazoleta.ms_usuarios.infrastructure.mappers.UserEntityMapper;
import com.example.plazoleta.ms_usuarios.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserJpaAdapter implements UserPersistencePort {

    private final UserRepository userRepository;
    private final UserEntityMapper mapper;

    public UserJpaAdapter(UserRepository userRepository, @Qualifier("userEntityMapperImpl") UserEntityMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public User saveUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new InvalidEmailException("El correo ya está registrado.");
        }
        if (userRepository.findByIdentityDocument(user.getIdentityDocument()).isPresent()) {
            throw new InvalidIdentityDocumentException("El documento de identidad ya está registrado.");
        }
        return mapper.toModel(userRepository.save(mapper.toEntity(user)));
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(mapper.toEntity(user)); // JPA detecta que es un update si ya tiene ID
    }


    @Override
    public List<User> findAll() {
        return mapper.toModelList(userRepository.findAll());
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id).map(mapper::toModel);
    }
}
