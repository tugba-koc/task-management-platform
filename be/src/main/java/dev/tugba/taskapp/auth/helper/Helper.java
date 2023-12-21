package dev.tugba.taskapp.auth.helper;

import java.util.regex.Pattern;

public class Helper {
    // Check the data is email or not
    private static final String EMAIL_REGEX =
    "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";

    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public static boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }
}