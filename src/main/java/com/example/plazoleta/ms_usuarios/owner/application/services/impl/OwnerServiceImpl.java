package com.example.plazoleta.ms_usuarios.owner.application.services.impl;

import com.example.plazoleta.ms_usuarios.owner.application.dto.request.OwnerRequestDto;
import com.example.plazoleta.ms_usuarios.owner.application.dto.response.OwnerResponseDto;
import com.example.plazoleta.ms_usuarios.owner.application.mappers.OwnerDtoMapper;
import com.example.plazoleta.ms_usuarios.owner.application.services.OwnerService;
import com.example.plazoleta.ms_usuarios.owner.domain.model.Owner;
import com.example.plazoleta.ms_usuarios.owner.domain.ports.in.OwnerServicePort;
import org.springframework.stereotype.Service;

@Service
public class OwnerServiceImpl implements OwnerService {

    private final OwnerServicePort ownerServicePort;
    private final OwnerDtoMapper ownerDtoMapper;

    public OwnerServiceImpl(OwnerServicePort ownerServicePort, OwnerDtoMapper ownerDtoMapper) {
        this.ownerServicePort = ownerServicePort;
        this.ownerDtoMapper = ownerDtoMapper;
    }

    @Override
    public OwnerResponseDto createOwner(OwnerRequestDto request) {
        Owner owner = ownerDtoMapper.toOwner(request);
        Owner savedOwner = ownerServicePort.createOwner(owner);
        return ownerDtoMapper.toResponse(savedOwner);
}
}
