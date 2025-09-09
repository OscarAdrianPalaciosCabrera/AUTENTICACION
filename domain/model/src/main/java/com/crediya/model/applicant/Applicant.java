package com.crediya.model.applicant;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.logging.Logger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Applicant {

    private static final Logger LOGGER = Logger.getLogger(Applicant.class.getName());

    private String name;
    private String lastName;
    private String identityDocument;
    private LocalDate birthDate;
    private String address;
    private String phoneNumber;
    private String email;
    private BigDecimal baseSalary;

    public static Applicant create(String name, String lastName,String identityDocument, LocalDate birthDate, String address, String phoneNumber, String email, BigDecimal baseSalary) {
        LOGGER.info("Entering to create applicant method in model class");
        return new Applicant(name, lastName, identityDocument,birthDate, address, phoneNumber, email, baseSalary);
    }

    public boolean isSalaryValid(){
        LOGGER.info("Entering to isSalaryValid method in model class");
        BigDecimal max = new BigDecimal("15000000");
        BigDecimal min = new BigDecimal("0");
        return baseSalary.compareTo(min) > 0 && baseSalary.compareTo(max) <= 15000000;
    }
}
