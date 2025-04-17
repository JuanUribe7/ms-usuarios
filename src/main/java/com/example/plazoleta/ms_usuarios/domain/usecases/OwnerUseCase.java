package com.example.plazoleta.ms_usuarios.domain.usecases;

import com.example.plazoleta.ms_usuarios.domain.model.Role;
import com.example.plazoleta.ms_usuarios.domain.model.User;
import com.example.plazoleta.ms_usuarios.domain.ports.in.OwnerServicePort;
import com.example.plazoleta.ms_usuarios.domain.ports.out.UserPersistencePort;
import com.example.plazoleta.ms_usuarios.domain.utils.UserValidator;

import java.util.List;
import java.util.Optional;

public class OwnerUseCase implements OwnerServicePort {

    private final UserPersistencePort userPersistencePort;

    public OwnerUseCase(UserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public User saveUser(User user) {
        user.setRole(Role.OWNER);
        UserValidator.validate(user);
        return userPersistencePort.saveUser(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userPersistencePort.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userPersistencePort.findById(id);
    }
}
