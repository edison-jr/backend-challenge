package oliveira.edison.backendchallenge.service.password.exceptions;

public class PasswordLengthException extends PasswordPatternException {
    private static final String MIN_LENGTH_ERROR = "Min length: %d";
    private static final String MAX_LENGTH_ERROR = "Max length: %d";

    public static PasswordLengthException minLength(int minLength) {
        return new PasswordLengthException(MIN_LENGTH_ERROR, minLength);
    }

    public static PasswordLengthException maxLength(int maxLength) {
        return new PasswordLengthException(MAX_LENGTH_ERROR, maxLength);
    }

    public PasswordLengthException(String s, Object... args) {
        super(s, args);
    }
}
