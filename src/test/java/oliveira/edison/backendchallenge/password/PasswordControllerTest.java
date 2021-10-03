package oliveira.edison.backendchallenge.password;

import com.fasterxml.jackson.databind.ObjectMapper;
import oliveira.edison.backendchallenge.controller.password.PasswordController;
import oliveira.edison.backendchallenge.controller.password.dto.PasswordRequest;
import oliveira.edison.backendchallenge.controller.password.handler.PasswordExceptionHandler;
import oliveira.edison.backendchallenge.service.password.IPasswordService;
import oliveira.edison.backendchallenge.service.password.exceptions.PasswordPatternException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.validation.Validator;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
class PasswordControllerTest {

    private static final String IS_VALID_URL = "/password/isValid";
    private static final String VALIDATE_URL = "/password/validate";

    @Mock
    private IPasswordService passwordService;

    @InjectMocks
    private PasswordController controller;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        Validator mockValidator = mock(Validator.class);

        mockMvc = standaloneSetup(controller)
            .setValidator(mockValidator)
            .setControllerAdvice(new PasswordExceptionHandler())
            .build();
    }

    @Test
    void givenValidPassword_whenIsValid_thenReturnTrue() throws Exception {
        doReturn(IPasswordService.VALID_PASSWORD)
            .when(passwordService)
            .validate(any(char[].class));

        performIsValid(true);
    }

    @Test
    void givenInvalidPassword_whenIsValid_thenReturnFalse() throws Exception {
        doThrow(PasswordPatternException.class)
            .when(passwordService)
            .validate(any(char[].class));

        performIsValid(false);
    }

    @Test
    void givenValidPassword_whenValidate_thenReturnTrue() throws Exception {
        doReturn(IPasswordService.VALID_PASSWORD)
            .when(passwordService)
            .validate(any(char[].class));

        buildRequest(VALIDATE_URL)
            .andExpect(status().isOk());
    }

    @Test
    void givenInvalidPassword_whenValidate_thenReturnHttpBadRequest() throws Exception {
        doThrow(PasswordPatternException.class)
            .when(passwordService)
            .validate(any(char[].class));

        buildRequest(VALIDATE_URL)
            .andExpect(status().isBadRequest());
    }

    void performIsValid(boolean expected) throws Exception {
        buildRequest(IS_VALID_URL)
            .andExpect(status().isOk())
            .andExpect(content().string(String.valueOf(expected)));
    }

    private ResultActions buildRequest(String url) throws Exception {
        char[] mockedInput = new char[] {};
        PasswordRequest requestData = new PasswordRequest(mockedInput);

        return mockMvc.perform(post(url)
            .content(new ObjectMapper()
                .writeValueAsString(requestData))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON));
    }

}
