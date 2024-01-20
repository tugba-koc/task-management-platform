package dev.tugba.taskapp.helper;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.regex.Pattern;

import dev.tugba.taskapp.auth.config.constants.Role;

public class Helper {
    // Check the data is email or not
    private static final String EMAIL_REGEX =
    "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";

    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public static boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static final String generateSecretKey(){
        SecureRandom secureRandom = new SecureRandom();
        byte[] secretKeyBytes = new byte[48];
        secureRandom.nextBytes(secretKeyBytes);
        String SECRET_KEY = Base64.getEncoder().encodeToString(secretKeyBytes);
        return SECRET_KEY;
    }

    public static String extractToken(String bearerToken) {
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        throw new IllegalArgumentException("Invalid bearer token format");
    }

    public static Role convert(String source) {
        return Role.valueOf(source.toUpperCase());
    }

}