package oliveira.edison.backendchallenge.service.password.exceptions;

public class EmptyPasswordException extends PasswordLengthException {
    private static final String IS_EMPTY_ERROR = "Is empty";

    public EmptyPasswordException() {
        super(IS_EMPTY_ERROR);
    }
}
