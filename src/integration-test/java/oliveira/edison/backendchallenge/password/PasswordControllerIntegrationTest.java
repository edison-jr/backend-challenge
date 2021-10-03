package oliveira.edison.backendchallenge.password;

import com.fasterxml.jackson.databind.ObjectMapper;
import oliveira.edison.backendchallenge.controller.password.dto.PasswordRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PasswordControllerIntegrationTest {

    private static final String IS_VALID_URL = "/password/isValid";
    private static final String VALIDATE_URL = "/password/validate";

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @ParameterizedTest
    @ValueSource(strings = {"AbTp9!fok"})
    void givenValidPassword_whenIsValid_thenReturnTrue(String secret) throws Exception {
        performIsValid(secret, true);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "ab1!", "abcdefghijlmnopqrstu1!", "AbTp9 fok", "AbTp9!foo", "AbTp9!foA"})
    void givenInvalidPassword_whenIsValid_thenReturnFalse(String secret) throws Exception {
        performIsValid(secret, false);
    }

    @ParameterizedTest
    @ValueSource(strings = {"AbTp9!fok"})
    void givenValidPassword_whenValidate_thenReturnHttpOk(String secret) throws Exception {
        buildRequest(VALIDATE_URL, secret)
            .andExpect(status().isOk());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "ab1!", "abcdefghijlmnopqrstu1!", "AbTp9 fok", "AbTp9!foo", "AbTp9!foA"})
    void givenInvalidPassword_whenValidate_thenReturnHttpBadRequest(String secret) throws Exception {
        buildRequest(VALIDATE_URL, secret)
            .andExpect(status().isBadRequest());
    }

    private void performIsValid(String secret, boolean expected) throws Exception {
        buildRequest(IS_VALID_URL, secret)
            .andExpect(status().isOk())
            .andExpect(content().string(String.valueOf(expected)));
    }

    private ResultActions buildRequest(String url, String secret) throws Exception {
        PasswordRequest requestData = new PasswordRequest(secret.toCharArray());

        return mockMvc.perform(post(url)
            .content(new ObjectMapper()
                .writeValueAsString(requestData))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON));
    }

}
