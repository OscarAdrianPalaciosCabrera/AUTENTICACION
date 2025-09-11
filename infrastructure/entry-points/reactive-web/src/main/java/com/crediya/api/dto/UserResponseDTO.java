package com.crediya.api.dto;

public record UserResponseDTO(String name,
                              String email,
                              String identityDocument,
                              Integer role) {
}
