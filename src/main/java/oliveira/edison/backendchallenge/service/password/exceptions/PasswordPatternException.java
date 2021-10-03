package oliveira.edison.backendchallenge.service.password.exceptions;

public class PasswordPatternException extends Exception {
    private static final String MISSING_NUMBER_ERROR = "Missing number";
    private static final String MISSING_UCASE_LETTER_ERROR = "Missing upper case letter";
    private static final String MISSING_LCASE_LETTER_ERROR = "Missing lower case letter";
    private static final String NO_SYMBOL_ERROR = "No symbol";

    public PasswordPatternException(String s, Object... args) {
        super(String.format(s, args));
    }

    public static PasswordPatternException missingNumber() {
        return new PasswordPatternException(MISSING_NUMBER_ERROR);
    }

    public static PasswordPatternException missingUpperCaseLetter() {
        return new PasswordPatternException(MISSING_UCASE_LETTER_ERROR);
    }

    public static PasswordPatternException missingLowerCaseLetter() {
        return new PasswordPatternException(MISSING_LCASE_LETTER_ERROR);
    }

    public static PasswordPatternException missingSymbol() {
        return new PasswordPatternException(NO_SYMBOL_ERROR);
    }
}
