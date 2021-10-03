package oliveira.edison.backendchallenge.service.password.exceptions;

public class InvalidCharException extends PasswordPatternException {
    private static final String INVALID_CHAR_ERROR = "Invalid char: '%c'";

    public InvalidCharException(char c) {
        super(INVALID_CHAR_ERROR, c);
    }
}
