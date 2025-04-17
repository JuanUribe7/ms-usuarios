package com.example.plazoleta.ms_usuarios.infrastructure.adapters.persistence;

import com.example.plazoleta.ms_usuarios.domain.model.User;
import com.example.plazoleta.ms_usuarios.domain.ports.out.UserPersistencePort;
import com.example.plazoleta.ms_usuarios.infrastructure.entities.UserEntity;
import com.example.plazoleta.ms_usuarios.infrastructure.mappers.UserEntityMapper;
import com.example.plazoleta.ms_usuarios.infrastructure.repositories.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserJpaAdapter implements UserPersistencePort {

    private final UserRepository userRepository;
    private final UserEntityMapper mapper;

    public UserJpaAdapter(UserRepository userRepository, UserEntityMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public User saveUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("El correo ya está registrado.");
        }
        if (userRepository.findByIdentityDocument(user.getIdentityDocument()).isPresent()) {
            throw new IllegalArgumentException("El documento de identidad ya está registrado.");
        }
        return mapper.toModel(userRepository.save(mapper.toEntity(user)));
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
