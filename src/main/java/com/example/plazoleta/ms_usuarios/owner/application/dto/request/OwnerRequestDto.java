package com.example.plazoleta.ms_usuarios.owner.application.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Schema(description="Solicitud para crear propietario")
public class OwnerRequestDto {
    @Schema(description="Nombre", example="Juan")
    @NotBlank(message="El nombre no puede estar vacío")
    private String name;

    @Schema(description="Apellido", example="Pérez")
    @NotBlank(message="El apellido no puede estar vacío")
    private String lastName;

    @Schema(description="Número de documento de identidad(númerico)", example="123456789")
    @NotBlank(message="El número de documento no puede estar vacío")
    private String identityDocument;

    @Schema(description="Número de teléfono(Máximo 13 dígitos, puede incluir +)", example="123456789")
    @NotBlank(message="El número de teléfono no puede estar vacío")
    private String phone;

    @Schema(description="Fecha de nacimiento", example="1990-01-01")
    @NotNull(message="La fecha de nacimiento no puede estar vacía")
    @Past(message="La fecha de nacimiento debe ser una fecha pasada")

    private LocalDate birthDate;

    @Schema(description="Correo electrónico", example="juan@example.com")
    @NotBlank(message="El correo electrónico no puede estar vacío")
    private String email;

    @Schema(description="Contraseña", example="password123")
    @NotBlank(message="La contraseña no puede estar vacía")
    @Size(min=8, message="La contraseña debe tener al menos 8 caracteres")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdentityDocument() {
        return identityDocument;
    }

    public void setIdentityDocument(String identityDocument) {
        this.identityDocument = identityDocument;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
