package com.example.plazoleta.ms_usuarios.domain.usecases;

import com.example.plazoleta.ms_usuarios.domain.exceptions.InvalidEmailException;
import com.example.plazoleta.ms_usuarios.domain.exceptions.InvalidPhoneException;
import com.example.plazoleta.ms_usuarios.domain.exceptions.InvalidIdentityDocumentException;
import com.example.plazoleta.ms_usuarios.domain.exceptions.MinorAgeException;
import com.example.plazoleta.ms_usuarios.domain.model.Role;
import com.example.plazoleta.ms_usuarios.domain.model.User;
import com.example.plazoleta.ms_usuarios.domain.ports.out.UserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OwnerUseCaseTest {

    @Mock
    private UserPersistencePort persistencePort;

    private OwnerUseCase useCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        useCase = new OwnerUseCase(persistencePort);
    }

    private User baseUser() {
        User u = new User();
        u.setEmail("user@example.com");
        u.setPhone("+123456789");
        u.setIdentityDocument("123456789");
        u.setBirthDate(LocalDate.now().minusYears(25));
        u.setRole(Role.OWNER);
        return u;
    }

    @Test
    void whenUnderage_thenMinorAgeException() {
        User u = baseUser();
        u.setBirthDate(LocalDate.now().minusYears(17));
        assertThrows(MinorAgeException.class, () -> useCase.saveUser(u));
        verifyNoInteractions(persistencePort);
    }

    @Test
    void whenInvalidEmail_thenInvalidEmailException() {
        User u = baseUser();
        u.setEmail("bad-email");
        assertThrows(InvalidEmailException.class, () -> useCase.saveUser(u));
        verifyNoInteractions(persistencePort);
    }

    @Test
    void whenInvalidPhone_thenInvalidPhoneException() {
        User u = baseUser();
        u.setPhone("no-digits");
        assertThrows(InvalidPhoneException.class, () -> useCase.saveUser(u));
        verifyNoInteractions(persistencePort);
    }

    @Test
    void whenInvalidDocument_thenInvalidIdentityDocumentException() {
        User u = baseUser();
        u.setIdentityDocument("abc123");
        assertThrows(InvalidIdentityDocumentException.class, () -> useCase.saveUser(u));
        verifyNoInteractions(persistencePort);
    }

    @Test
    void whenValid_thenDelegateToPersistence() {
        User u = baseUser();
        when(persistencePort.saveUser(u)).thenReturn(u);

        User saved = useCase.saveUser(u);
        assertSame(u, saved);
        verify(persistencePort, times(1)).saveUser(u);
    }

    @Test
    void whenFindAllUsers_thenDelegateToPersistence() {
        List<User> dummyList = List.of(baseUser());
        when(persistencePort.findAll()).thenReturn(dummyList);

        List<User> result = useCase.findAllUsers();

        assertSame(dummyList, result);
        verify(persistencePort, times(1)).findAll();
    }

    @Test
    void whenFindByIdExists_thenReturnOptional() {
        User u = baseUser();
        when(persistencePort.findById(42L)).thenReturn(Optional.of(u));

        Optional<User> result = useCase.findById(42L);

        assertTrue(result.isPresent());
        assertSame(u, result.get());
        verify(persistencePort).findById(42L);
    }

    @Test
    void whenFindByIdNotExists_thenReturnEmptyOptional() {
        when(persistencePort.findById(99L)).thenReturn(Optional.empty());

        Optional<User> result = useCase.findById(99L);

        assertTrue(result.isEmpty());
        verify(persistencePort).findById(99L);
    }

}
