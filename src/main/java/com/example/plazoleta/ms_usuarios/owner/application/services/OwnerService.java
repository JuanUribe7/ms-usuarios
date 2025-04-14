package com.example.plazoleta.ms_usuarios.owner.application.services;

import com.example.plazoleta.ms_usuarios.owner.application.dto.request.OwnerRequestDto;
import com.example.plazoleta.ms_usuarios.owner.application.dto.response.OwnerResponseDto;

public interface OwnerService {
    OwnerResponseDto createOwner(OwnerRequestDto request);
}
