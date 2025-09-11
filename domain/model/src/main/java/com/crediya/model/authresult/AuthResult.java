package com.crediya.model.authresult;
import lombok.*;
//import lombok.NoArgsConstructor;


@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class AuthResult {
    private final String email;
    private final Integer role;
    private final String token;
}

