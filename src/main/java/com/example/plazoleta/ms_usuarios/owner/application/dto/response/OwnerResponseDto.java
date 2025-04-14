package com.example.plazoleta.ms_usuarios.owner.application.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description="Respuesta después de crear un propietario.")
public class OwnerResponseDto {
    @Schema(description="ID del propietario", example="1")
    private Long id;

    @Schema(description="Nombre", example="Juan")
    private String name;

    @Schema(description="Apellido", example="Pérez")
    private String lastName;

    @Schema(description="Número de documento", example="123456789")
    private String identityDocument;

    @Schema(description="Número de teléfono", example="123456789")
    private String phone;

    @Schema(description="Fecha de nacimiento", example="1990-01-01")
    private LocalDate birthDate;

    @Schema(description="Correo electrónico", example="juan@example.com")
    private String email;

    @Schema(description="Rol", example="ROLE_OWNER")
    private String role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
