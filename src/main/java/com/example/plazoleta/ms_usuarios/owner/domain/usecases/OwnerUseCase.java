package com.example.plazoleta.ms_usuarios.owner.domain.usecases;

import com.example.plazoleta.ms_usuarios.owner.domain.model.Owner;
import com.example.plazoleta.ms_usuarios.owner.domain.exceptions.InvalidEmailException;
import com.example.plazoleta.ms_usuarios.owner.domain.exceptions.InvalidIdentityDocumentException;
import com.example.plazoleta.ms_usuarios.owner.domain.exceptions.InvalidPhoneException;
import com.example.plazoleta.ms_usuarios.owner.domain.exceptions.MinorAgeException;
import com.example.plazoleta.ms_usuarios.owner.domain.ports.in.OwnerServicePort;
import com.example.plazoleta.ms_usuarios.owner.domain.ports.out.OwnerPersistencePort;
import com.example.plazoleta.ms_usuarios.owner.commons.configurations.constants.utils.DomainConstants;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

public class OwnerUseCase implements OwnerServicePort {

    private final OwnerPersistencePort persistencePort;
    private final PasswordEncoder passwordEncoder;

    public OwnerUseCase(OwnerPersistencePort persistencePort, PasswordEncoder passwordEncoder){
        this.persistencePort=persistencePort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Owner createOwner(Owner owner){

        if (owner.getName() == null || owner.getName().isBlank()) {
            throw new IllegalArgumentException("Nombre obligatorio");
        }

        if (owner.getLastName() == null || owner.getLastName().isBlank()) {
            throw new IllegalArgumentException("Apellido obligatorio");
        }

        if (owner.getIdentityDocument() == null || owner.getIdentityDocument().isBlank()) {
            throw new IllegalArgumentException("Documento de identidad obligatorio");
        }

        if (owner.getPhone() == null || owner.getPhone().isBlank()) {
            throw new IllegalArgumentException("Teléfono obligatorio");
        }

        if (owner.getBirthDate() == null) {
            throw new IllegalArgumentException("Fecha de nacimiento obligatoria");
        }

        if (owner.getEmail() == null || owner.getEmail().isBlank()) {
            throw new IllegalArgumentException("Correo electrónico obligatorio");
        }

        if (owner.getPassword() == null || owner.getPassword().isBlank()) {
            throw new IllegalArgumentException("Contraseña obligatoria");
        }

        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (!Pattern.matches(emailRegex, owner.getEmail())) {
            throw new InvalidEmailException("Invalid email format: " + owner.getEmail());
        }

        String phoneRegex = "^\\+?\\d{1,12}$";
        if (!Pattern.matches(phoneRegex, owner.getPhone()) || owner.getPhone().length() > 13) {
            throw new InvalidPhoneException("Invalid phone format or exceeds 13 characters: " + owner.getPhone());
        }

        String documentRegex = "^\\d+$";
        if (!Pattern.matches(documentRegex, owner.getIdentityDocument())) {
            throw new InvalidIdentityDocumentException("Identity document must be numeric: " + owner.getIdentityDocument());
        }

        if (Period.between(owner.getBirthDate(), LocalDate.now()).getYears() < 18) {
            throw new MinorAgeException("Owner must be at least 18 years old");
        }

        owner.setPassword(passwordEncoder.encode(owner.getPassword()));

        owner.setRole(DomainConstants.OWNER_ROLE);

        return persistencePort.saveOwner(owner);
    }
}
