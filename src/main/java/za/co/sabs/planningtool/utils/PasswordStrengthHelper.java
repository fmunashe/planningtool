package za.co.sabs.planningtool.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordStrengthHelper {
    private static final String PASSWORD_PATTERN = "^(?![_/\\-])(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$)(?!.*[_/\\-]$).{8,}$";;
    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    public static boolean validatePasswordStrength(String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
