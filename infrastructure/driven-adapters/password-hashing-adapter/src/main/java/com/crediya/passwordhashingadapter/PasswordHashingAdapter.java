package com.crediya.passwordhashingadapter;

import com.crediya.model.authresult.gateways.PasswordHashingPort;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
@Component
public class PasswordHashingAdapter implements PasswordHashingPort {

    private final PasswordEncoder encoder;
    private static final Logger LOGGER = LoggerFactory.getLogger(PasswordHashingAdapter.class);

    @Override
    public String encode(String rawPassword) {
        LOGGER.debug("Entering to encode method - rawPassword: {}", rawPassword);
        String enc = encoder.encode(rawPassword);
        LOGGER.debug("Encoder result: {}",enc);
        return encoder.encode(rawPassword);
    }

    @Override
    public boolean matches(String rawPassword, String hashedPassword) {
        LOGGER.debug("Entering to matches method - rawPassword: {} - hashedPassword: {}",rawPassword ,hashedPassword);
        boolean result = encoder.matches(rawPassword, hashedPassword);
        LOGGER.debug("Matches result: {}",result);
        return encoder.matches(rawPassword, hashedPassword);
    }
}
