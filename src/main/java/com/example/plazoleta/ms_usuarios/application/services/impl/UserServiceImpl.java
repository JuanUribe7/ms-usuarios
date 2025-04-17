package com.example.plazoleta.ms_usuarios.application.services.impl;

import com.example.plazoleta.ms_usuarios.application.services.UserService;

import com.example.plazoleta.ms_usuarios.domain.exceptions.UnauthorizedException;
import com.example.plazoleta.ms_usuarios.domain.model.Role;
import com.example.plazoleta.ms_usuarios.domain.model.User;
import com.example.plazoleta.ms_usuarios.domain.ports.in.OwnerServicePort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final OwnerServicePort ownerServicePort;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(OwnerServicePort ownerServicePort) {
        this.ownerServicePort = ownerServicePort;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }


    @Override
    public User saveUser(User user, String role) {
        switch (role) {
            case "ADMIN":
                user.setRole(Role.OWNER);
                String hashed = passwordEncoder.encode(user.getPassword());
                user.setPassword(hashed);
                return ownerServicePort.saveUser(user);

            default:
                throw new UnauthorizedException("Unauthorized role: " + role);
        }


    }
    @Override
    public List<User> getAllUsers() {
        return ownerServicePort.findAllUsers();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return ownerServicePort.findById(id);
    }
}



