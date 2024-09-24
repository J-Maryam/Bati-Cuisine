package validators;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class InputValidator {

    public static boolean validateNonEmptyString(String input) {
        return input != null && !input.isEmpty();
    }

    public static boolean validatePhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("\\+\\d{1,3} \\d{3} \\d{3} \\d{3}");
    }

    public static LocalDate validateDate(String dateStr) {
        try {
            return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException e) {
            System.out.println("Format de date incorrect. Veuillez entrer une date au format jj/mm/aaaa.");
            return null;
        }
    }

    public static boolean validatePositiveAmount(float amount) {
        return amount > 0;
    }

    public static boolean validateDates(LocalDate dateEmission, LocalDate dateValidite) {
        return dateValidite != null && dateValidite.isAfter(dateEmission);
    }

    public static boolean validateProductivityFactor(float factor) {
        return factor == 1.0f || factor == 1.1f || factor == 2.0f;
    }
}
