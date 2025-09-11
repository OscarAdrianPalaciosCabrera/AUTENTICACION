package com.crediya.model.authresult.gateways;

public interface PasswordHashingPort {
    String encode(String rawPassword);
    boolean matches(String rawPassword, String hashedPassword);
}
