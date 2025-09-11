package com.crediya.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.NumberFormat;


public record CreateUserDTO(
        @NotBlank(message = "Some mandatory field can not be blank")
        @NotNull(message = "Some mandatory field can not be null")
        String name,

        @NotBlank(message = "Some mandatory field can not be blank")
        @NotNull(message = "Some mandatory field can not be null")
        String lastName,

        @NotBlank(message = "Some mandatory field can not be blank")
        @NotNull(message = "Some mandatory field can not be null")
        String identityDocument,

        LocalDate birthDate,
        String address,
        String phoneNumber,

        @NotBlank(message = "Some mandatory field can not be blank")
        @NotNull(message = "Some mandatory field can not be null")
        @Pattern(
                regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
                message = "Some field have a invalid format"
        )
        String email,

        @NotNull
        /*@Pattern(
                regexp = "^\\d+(\\.\\d{1,2})?$",
                message = "Solo numeros, hasta dos decimales")*/
                BigDecimal baseSalary,

        @NotNull(message = "Some mandatory field can not be null")
        Integer role,

        @NotBlank(message = "Some mandatory field can not be blank")
        @NotNull(message = "Some mandatory field can not be null")
        String passwordHash
        )
{

}
