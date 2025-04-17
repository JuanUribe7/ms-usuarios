package com.example.plazoleta.ms_usuarios.application.services;

import com.example.plazoleta.ms_usuarios.domain.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(User user, String role);
   List<User> getAllUsers();
    Optional<User> getUserById(Long id);
}
