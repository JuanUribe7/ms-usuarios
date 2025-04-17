package com.example.plazoleta.ms_usuarios.infrastructure.adapters.persistence;

import com.example.plazoleta.ms_usuarios.domain.model.Role;
import com.example.plazoleta.ms_usuarios.domain.model.User;
import com.example.plazoleta.ms_usuarios.infrastructure.entities.UserEntity;
import com.example.plazoleta.ms_usuarios.infrastructure.mappers.UserEntityMapper;
import com.example.plazoleta.ms_usuarios.infrastructure.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserJpaAdapterTest {

    private UserRepository userRepository;
    private UserEntityMapper userEntityMapper;
    private UserJpaAdapter userJpaAdapter;

    @BeforeEach
    public void setUp() {
        userRepository = mock(UserRepository.class);
        userEntityMapper = mock(UserEntityMapper.class);
        userJpaAdapter = new UserJpaAdapter(userRepository, userEntityMapper);
    }

    @Test
    public void saveUser_success() {
        // Arrange
        User user = new User(1L, "John", "Doe", "123456", "123-456-7890", LocalDate.of(1990, 1, 1), "john@example.com", "password", Role.ADMIN);
        UserEntity userEntity = new UserEntity(1L, "John", "Doe", "123-456-7890", LocalDate.of(1990, 1, 1), "123456", "password", Role.ADMIN, "john@example.com");

        when(userEntityMapper.toEntity(user)).thenReturn(userEntity);
        when(userRepository.save(userEntity)).thenReturn(userEntity);
        when(userEntityMapper.toModel(userEntity)).thenReturn(user);

        // Act
        User savedUser = userJpaAdapter.saveUser(user);

        // Assert
        assertNotNull(savedUser);
        assertEquals("John", savedUser.getName());
        verify(userRepository, times(1)).save(userEntity);
    }

    @Test
    public void saveUser_throwsException() {
        // Arrange
        User user = new User(1L, "Jane", "Doe", "654321", "987-654-3210", LocalDate.of(1985, 5, 5), "jane@example.com", "password", Role.EMPLOYEE);
        UserEntity userEntity = new UserEntity(1L, "Jane", "Doe", "987-654-3210", LocalDate.of(1985, 5, 5), "654321", "password", Role.EMPLOYEE, "jane@example.com");

        when(userEntityMapper.toEntity(user)).thenReturn(userEntity);
        when(userRepository.save(userEntity)).thenThrow(new RuntimeException("DB error"));

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> userJpaAdapter.saveUser(user));
        assertEquals("DB error", exception.getMessage());
    }
}
