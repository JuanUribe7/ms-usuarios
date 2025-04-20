package com.example.plazoleta.ms_usuarios.domain.utils;

import com.example.plazoleta.ms_usuarios.domain.model.Role;

public class RoleValidator {

    public static void validate(Role role){
        if (role == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }

    }
}
