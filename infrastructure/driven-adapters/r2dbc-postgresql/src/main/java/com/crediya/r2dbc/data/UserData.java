package com.crediya.r2dbc.data;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table("applicants")
public class UserData {
    @Id
    private String id;
    private String name;
    private String lastName;
    private String identityDocument;
    private LocalDate birthDate;
    private String address;
    private String phoneNumber;
    private String email;
    private BigDecimal baseSalary;
    private Integer role;
    private String passwordHash;
}
