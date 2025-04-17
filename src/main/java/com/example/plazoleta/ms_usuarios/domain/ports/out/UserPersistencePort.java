package com.example.plazoleta.ms_usuarios.domain.ports.out;

import com.example.plazoleta.ms_usuarios.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserPersistencePort {
    User saveUser(User user);
    List<User> findAll();
    Optional<User> findById(Long id);
}
