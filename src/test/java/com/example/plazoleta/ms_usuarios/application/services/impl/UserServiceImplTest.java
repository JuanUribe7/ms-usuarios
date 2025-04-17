package com.example.plazoleta.ms_usuarios.application.services.impl;

import com.example.plazoleta.ms_usuarios.domain.exceptions.UnauthorizedException;
import com.example.plazoleta.ms_usuarios.domain.model.Role;
import com.example.plazoleta.ms_usuarios.domain.model.User;
import com.example.plazoleta.ms_usuarios.domain.ports.in.OwnerServicePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private OwnerServicePort ownerServicePort;

    @InjectMocks
    private UserServiceImpl service;

    @Test
    void saveUser_withAdminRole_encryptsPasswordAndSetsOwnerRole() {
        User input = new User();
        input.setPassword("plain123");

        when(ownerServicePort.saveUser(any(User.class))).thenAnswer(i -> i.getArgument(0));

        User result = service.saveUser(input, "ADMIN");

        assertNotEquals("plain123", result.getPassword());
        assertTrue(new BCryptPasswordEncoder().matches("plain123", result.getPassword()));
        assertEquals(Role.OWNER, result.getRole());
        verify(ownerServicePort, times(1)).saveUser(any(User.class));
    }

    @Test
    void saveUser_withInvalidRole_throwsUnauthorizedException() {
        User input = new User();
        input.setPassword("123");

        UnauthorizedException ex = assertThrows(
                UnauthorizedException.class,
                () -> service.saveUser(input, "CLIENT")
        );

        assertEquals("Unauthorized role: CLIENT", ex.getMessage());
        verify(ownerServicePort, never()).saveUser(any());
    }

    @Test
    void getAllUsers_delegatesToPort() {
        List<User> dummy = List.of(new User(), new User());
        when(ownerServicePort.findAllUsers()).thenReturn(dummy);

        List<User> result = service.getAllUsers();

        assertSame(dummy, result);
        verify(ownerServicePort, times(1)).findAllUsers();
    }

    @Test
    void getUserById_present_delegatesToPort() {
        User u = new User();
        when(ownerServicePort.findById(42L)).thenReturn(Optional.of(u));

        Optional<User> result = service.getUserById(42L);

        assertTrue(result.isPresent());
        assertSame(u, result.get());
        verify(ownerServicePort, times(1)).findById(42L);
    }

    @Test
    void getUserById_notFound_returnsEmpty() {
        when(ownerServicePort.findById(99L)).thenReturn(Optional.empty());

        Optional<User> result = service.getUserById(99L);

        assertTrue(result.isEmpty());
        verify(ownerServicePort, times(1)).findById(99L);
    }
}
