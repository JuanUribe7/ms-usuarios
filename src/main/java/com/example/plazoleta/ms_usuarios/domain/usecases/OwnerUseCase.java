package com.example.plazoleta.ms_usuarios.domain.usecases;

import com.example.plazoleta.ms_usuarios.domain.exceptions.NotFoundException;
import com.example.plazoleta.ms_usuarios.domain.exceptions.UnauthorizedException;
import com.example.plazoleta.ms_usuarios.domain.model.Role;
import com.example.plazoleta.ms_usuarios.domain.model.User;
import com.example.plazoleta.ms_usuarios.domain.ports.in.OwnerServicePort;
import com.example.plazoleta.ms_usuarios.domain.ports.out.UserPersistencePort;
import com.example.plazoleta.ms_usuarios.domain.utils.UserValidator;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

public class OwnerUseCase implements OwnerServicePort {

    private final UserPersistencePort userPersistencePort;
    private final PasswordEncoder passwordEncoder;

    public OwnerUseCase(UserPersistencePort userPersistencePort, PasswordEncoder passwordEncoder) {
        this.userPersistencePort = userPersistencePort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User saveUser(User user) {
        user.setRole(Role.OWNER);
        String hashed = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashed);
        return userPersistencePort.saveUser(user);
    }

    @Override
    public void assignRestaurantToOwner(Long id, Long restaurantId) {
        User user = userPersistencePort.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

        if (user.getRole() != Role.OWNER) {
            throw new UnauthorizedException("Solo los propietarios pueden tener restaurante.");
        }

        user.setRestaurantId(restaurantId);
        userPersistencePort.updateUser(user); // <- este método debe existir en el adapter
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
