package oliveira.edison.backendchallenge.service.password.exceptions;

public class DuplicateCharException extends PasswordPatternException {
    private static final String DUPLICATED_CHAR_ERROR = "Duplicated char: '%c'";

    public DuplicateCharException(char c) {
        super(DUPLICATED_CHAR_ERROR, c);
    }
}
