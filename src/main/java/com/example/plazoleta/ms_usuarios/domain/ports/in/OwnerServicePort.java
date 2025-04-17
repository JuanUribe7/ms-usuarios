package com.example.plazoleta.ms_usuarios.domain.ports.in;

import com.example.plazoleta.ms_usuarios.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface OwnerServicePort {

    User saveUser(User user);
    List<User> findAllUsers();
    Optional<User> findById(Long id);

}
