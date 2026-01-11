package za.co.sabs.planningtool.utils;

public class PhoneValidator {
    public static boolean isValidPhone(String msisdn) {
        final String MSISDN_REGEX = "((00|\\+)?(263))?0?(7\\d{2})(\\d{6})";
        return msisdn.matches(MSISDN_REGEX);
    }

    public static boolean isValidEconetPhoneNumber(String msisdn) {
        final String MSISDN_REGEX = "((00|\\+)?(263))?0?(7[7|8])(\\d{7})";
        return msisdn.matches(MSISDN_REGEX);
    }

    public static boolean isValidNetonePhoneNumber(String msisdn) {
        final String MSISDN_REGEX = "((00|\\+)?(263))?0?(71)(\\d{7})";
        return msisdn.matches(MSISDN_REGEX);
    }

    public static boolean isValidTelecelPhoneNumber(String msisdn) {
        final String MSISDN_REGEX = "((00|\\+)?(263))?0?(73)(\\d{6})";
        return msisdn.matches(MSISDN_REGEX);
    }
}
