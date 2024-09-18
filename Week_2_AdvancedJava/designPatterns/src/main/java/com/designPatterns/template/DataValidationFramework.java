package com.designPatterns.template;

public class DataValidationFramework {
    abstract class DataValidator {
        public final boolean validate(String data) {
            if (!checkLength(data)) return false;
            if (!checkFormat(data)) return false;
            return additionalChecks(data);
        }

        protected abstract boolean checkLength(String data);
        protected abstract boolean checkFormat(String data);
        protected boolean additionalChecks(String data) {
            // Default implementation
            return true;
        }
    }

    class EmailValidator extends DataValidator {
        protected boolean checkLength(String email) {
            return email.length() >= 5 && email.length() <= 254;
        }

        protected boolean checkFormat(String email) {
            return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
        }

        protected boolean additionalChecks(String email) {
            return email.split("@")[1].contains(".");
        }
    }

    class PhoneNumberValidator extends DataValidator {
        protected boolean checkLength(String phoneNumber) {
            return phoneNumber.length() >= 10 && phoneNumber.length() <= 15;
        }

        protected boolean checkFormat(String phoneNumber) {
            return phoneNumber.matches("^\\+?[0-9.-]+$");
        }
    }

    public static void main(String[] args) {
        DataValidationFramework framework = new DataValidationFramework();
        DataValidator emailValidator = framework.new EmailValidator();
        DataValidator phoneValidator = framework.new PhoneNumberValidator();

        System.out.println("Email validation:");
        System.out.println("test@example.com: " + emailValidator.validate("test@example.com"));
        System.out.println("invalid-email: " + emailValidator.validate("invalid-email"));

        System.out.println("\nPhone number validation:");
        System.out.println("+1-555-123-4567: " + phoneValidator.validate("+1-555-123-4567"));
        System.out.println("12345: " + phoneValidator.validate("12345"));
    }
}
