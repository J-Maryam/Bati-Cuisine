package validators;

public class InputValidator {

    public static boolean validateNonEmptyString(String input) {
        return input != null && !input.isEmpty();
    }

    public static boolean validatePhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("\\+\\d{1,3} \\d{3} \\d{3} \\d{3}");
    }
}
