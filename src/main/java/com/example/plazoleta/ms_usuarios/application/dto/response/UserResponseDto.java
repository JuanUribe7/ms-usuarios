package com.example.plazoleta.ms_usuarios.application.dto.response;

import com.example.plazoleta.ms_usuarios.domain.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private Role role;
    private Long restaurantId;


}
