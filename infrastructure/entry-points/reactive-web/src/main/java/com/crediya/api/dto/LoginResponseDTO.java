package com.crediya.api.dto;

public record LoginResponseDTO(String accessToken,
                               String tokenType,
                               long expiresIn,
                               String email,
                               Integer role) {
}
