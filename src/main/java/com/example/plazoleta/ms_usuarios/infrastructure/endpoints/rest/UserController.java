package com.example.plazoleta.ms_usuarios.infrastructure.endpoints.rest;

import com.example.plazoleta.ms_usuarios.application.dto.request.UserRequestDto;
import com.example.plazoleta.ms_usuarios.application.dto.response.UserResponseDto;
import com.example.plazoleta.ms_usuarios.application.mappers.UserDtoMapper;
import com.example.plazoleta.ms_usuarios.application.services.UserService;
import com.example.plazoleta.ms_usuarios.domain.model.User;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    private final UserService userService;
    private final UserDtoMapper mapper;

    public UserController(UserService userService,@Qualifier("userDtoMapperImpl") UserDtoMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody  @Valid UserRequestDto dto, @RequestHeader ("X-User-Role")String role) {


        User created = userService.saveUser(mapper.toModel(dto), role);
        return new ResponseEntity<>(mapper.toResponseDto(created), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/assign-restaurant")
    public ResponseEntity<Void> assignRestaurant(@PathVariable Long id, @RequestBody Map<String, Long> body) {
        Long restaurantId = body.get("restaurantId");
        userService.assignRestaurantToOwner(id, restaurantId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers().stream().map(mapper::toResponseDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(user -> new ResponseEntity<>(mapper.toResponseDto(user), HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
