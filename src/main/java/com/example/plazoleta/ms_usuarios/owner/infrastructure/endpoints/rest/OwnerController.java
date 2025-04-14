package com.example.plazoleta.ms_usuarios.owner.infrastructure.endpoints.rest;


import com.example.plazoleta.ms_usuarios.owner.application.dto.request.OwnerRequestDto;
import com.example.plazoleta.ms_usuarios.owner.application.dto.response.OwnerResponseDto;
import com.example.plazoleta.ms_usuarios.owner.application.services.OwnerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService){
        this.ownerService = ownerService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OwnerResponseDto> createOwner(@Valid @RequestBody OwnerRequestDto request){
        OwnerResponseDto response=ownerService.createOwner(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }
}
