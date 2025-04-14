package com.example.plazoleta.ms_usuarios.owner.domain.usecases;

import com.example.plazoleta.ms_usuarios.owner.commons.configurations.constants.utils.DomainConstants;
import com.example.plazoleta.ms_usuarios.owner.domain.exceptions.InvalidEmailException;
import com.example.plazoleta.ms_usuarios.owner.domain.exceptions.InvalidIdentityDocumentException;
import com.example.plazoleta.ms_usuarios.owner.domain.exceptions.InvalidPhoneException;
import com.example.plazoleta.ms_usuarios.owner.domain.exceptions.MinorAgeException;
import com.example.plazoleta.ms_usuarios.owner.domain.model.Owner;
import com.example.plazoleta.ms_usuarios.owner.domain.ports.out.OwnerPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OwnerUseCaseTest {

    @Mock
    private OwnerPersistencePort persistencePort;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private OwnerUseCase useCase;

    private Owner validOwner;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        validOwner = new Owner(null, "Juan", "Pérez", "123456789", "+573005698325",
                LocalDate.of(1990, 1, 1), "juan@example.com", "Password123", null);

        when(passwordEncoder.encode(any())).thenReturn("encodedPassword");
        when(persistencePort.saveOwner(any())).thenAnswer(invocation -> invocation.getArgument(0));
    }

    @Test
    void createOwner_success() {
        Owner savedOwner = useCase.createOwner(validOwner);
        assertEquals("encodedPassword", savedOwner.getPassword());
        assertEquals("OWNER", savedOwner.getRole());
    }

    @Test
    void createOwner_nullName_throwsException() {
        validOwner.setName(null);
        assertThrows(IllegalArgumentException.class, () -> useCase.createOwner(validOwner));
    }

    @Test
    void createOwner_blankLastName_throwsException() {
        validOwner.setLastName(" ");
        assertThrows(IllegalArgumentException.class, () -> useCase.createOwner(validOwner));
    }

    @Test
    void createOwner_invalidEmail_throwsException() {
        validOwner.setEmail("invalid");
        assertThrows(InvalidEmailException.class, () -> useCase.createOwner(validOwner));
    }

    @Test
    void createOwner_invalidPhone_throwsException() {
        validOwner.setPhone("123abc");
        assertThrows(InvalidPhoneException.class, () -> useCase.createOwner(validOwner));
    }

    @Test
    void createOwner_phoneTooLong_throwsException() {
        validOwner.setPhone("+12345678901234");
        assertThrows(InvalidPhoneException.class, () -> useCase.createOwner(validOwner));
    }

    @Test
    void createOwner_invalidDocument_throwsException() {
        validOwner.setIdentityDocument("abc123");
        assertThrows(InvalidIdentityDocumentException.class, () -> useCase.createOwner(validOwner));
    }

    @Test
    void createOwner_minorAge_throwsException() {
        validOwner.setBirthDate(LocalDate.now().minusYears(17));
        assertThrows(MinorAgeException.class, () -> useCase.createOwner(validOwner));
    }

    @Test
    void createOwner_nullPassword_throwsException() {
        validOwner.setPassword(null);
        assertThrows(IllegalArgumentException.class, () -> useCase.createOwner(validOwner));
    }

    @Test
    void createOwner_blankEmail_throwsException() {
        validOwner.setEmail(" ");
        assertThrows(IllegalArgumentException.class, () -> useCase.createOwner(validOwner));
    }

    @Test
    void createOwner_nullBirthDate_throwsException() {
        validOwner.setBirthDate(null);
        assertThrows(IllegalArgumentException.class, () -> useCase.createOwner(validOwner));
    }
    @Test
    void createOwner_roleAssignedCorrectly() {
        Owner savedOwner = useCase.createOwner(validOwner);
        assertEquals(DomainConstants.OWNER_ROLE, savedOwner.getRole());
    }
    @Test
    void createOwner_nullPhone_throwsException() {
        validOwner.setPhone(null);
        assertThrows(IllegalArgumentException.class, () -> useCase.createOwner(validOwner));
    }
    @Test
    void createOwner_nullEmail_throwsException() {
        validOwner.setEmail(null);
        assertThrows(IllegalArgumentException.class, () -> useCase.createOwner(validOwner));
    }
    @Test
    void createOwner_blankName_throwsException() {
        validOwner.setName(" ");
        assertThrows(IllegalArgumentException.class, () -> useCase.createOwner(validOwner));
    }
    @Test
    void createOwner_blankIdentityDocument_throwsException() {
        validOwner.setIdentityDocument(" ");
        assertThrows(IllegalArgumentException.class, () -> useCase.createOwner(validOwner));
    }
    @Test
    void createOwner_blankPhone_throwsException() {
        validOwner.setPhone(" ");
        assertThrows(IllegalArgumentException.class, () -> useCase.createOwner(validOwner));
    }
    @Test
    void createOwner_blankPassword_throwsException() {
        validOwner.setPassword(" ");
        assertThrows(IllegalArgumentException.class, () -> useCase.createOwner(validOwner));
    }
    @Test
    void createOwner_validAge_success() {
        validOwner.setBirthDate(LocalDate.now().minusYears(20)); // Asegúrate de que la persona sea mayor de edad
        Owner savedOwner = useCase.createOwner(validOwner);
        assertNotNull(savedOwner);
    }




}
