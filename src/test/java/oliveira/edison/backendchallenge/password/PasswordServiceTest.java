package oliveira.edison.backendchallenge.password;

import oliveira.edison.backendchallenge.service.password.PasswordService;
import oliveira.edison.backendchallenge.service.password.exceptions.PasswordPatternException;
import oliveira.edison.backendchallenge.service.password.validator.IPasswordValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static oliveira.edison.backendchallenge.service.password.IPasswordService.VALID_PASSWORD;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class PasswordServiceTest {

    @Mock
    private IPasswordValidator validator;

    @InjectMocks
    private PasswordService service;

    @Test
    void givenInvalidPassword_whenValidate_thenThrowException() throws Exception  {
        doThrow(PasswordPatternException.class)
            .when(validator)
            .validate(any(char[].class));

        char[] mockedInput = new char[] {};
        Assertions.assertThrows(PasswordPatternException.class, () -> service.validate(mockedInput));
    }

    @Test
    void givenValidPassword_whenValidate_thenReturnMessage() throws Exception {
        doNothing()
            .when(validator)
            .validate(any(char[].class));

        char[] mockedInput = new char[] {};
        String actual = service.validate(mockedInput);
        Assertions.assertEquals(VALID_PASSWORD, actual);
    }

}
