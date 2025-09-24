package com.crediya.api.dto;

import java.math.BigDecimal;

public record UserResponseDTO(String name,
                              String email,
                              String identityDocument,
                              Integer role,
                              BigDecimal baseSalary) {
}
