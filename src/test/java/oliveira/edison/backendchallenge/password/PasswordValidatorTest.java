package oliveira.edison.backendchallenge.password;

import oliveira.edison.backendchallenge.service.password.exceptions.DuplicateCharException;
import oliveira.edison.backendchallenge.service.password.exceptions.InvalidCharException;
import oliveira.edison.backendchallenge.service.password.exceptions.PasswordLengthException;
import oliveira.edison.backendchallenge.service.password.exceptions.PasswordPatternException;
import oliveira.edison.backendchallenge.service.password.validator.PasswordValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PasswordValidatorTest {

    private static final int MIN_LENGTH = 9;
    private static final int MAX_LENGTH = 12;
    private static final String SYMBOLS = "!@#$%^&*()-+";
    private static final boolean ALLOW_DUPLICATES = false;

    private final PasswordValidator validator = new PasswordValidator(
        MIN_LENGTH,
        MAX_LENGTH,
        SYMBOLS,
        ALLOW_DUPLICATES
    );

    @ParameterizedTest
    @ValueSource(strings = {"", "ab1!", "abcdefghijlmnopqrstu1!"})
    void givenViolateLength_whenValidate_thenThrowException(String secret) {
        validateThrowing(PasswordLengthException.class, secret);
    }

    @ParameterizedTest
    @ValueSource(strings = {"AbTp9 fok"})
    void gibenInvalidChar_whenValidate_thenThrowException(String secret) {
        validateThrowing(InvalidCharException.class, secret);
    }

    @ParameterizedTest
    @ValueSource(strings = {"AbTp9!foo", "AbTp9!foA"})
    void givenDuplicatedChar_whenValidate_thenThrowException(String secret) {
        validateThrowing(DuplicateCharException.class, secret);
    }

    @ParameterizedTest
    @ValueSource(strings = {"AbCdEfHi!", "abcdefg1!", "ABCDEFG1!"})
    void givenMissingChar_whenValidate_thenThrowException(String secret) {
        validateThrowing(PasswordPatternException.class, secret);
    }

    @ParameterizedTest
    @ValueSource(strings = {"AbTp9!fok"})
    void givenValidPassword_whenValidate_thenDoNothing(String secret) throws PasswordPatternException {
        char[] input = secret.toCharArray();
        validator.validate(input);
    }

    private <T extends Throwable> void validateThrowing(Class<T> exceptionClass, String secret) {
        char[] input = secret.toCharArray();
        Assertions.assertThrows(exceptionClass, () -> validator.validate(input));
    }

}