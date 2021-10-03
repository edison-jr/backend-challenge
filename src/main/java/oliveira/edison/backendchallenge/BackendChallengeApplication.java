package oliveira.edison.backendchallenge;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
    info = @Info(
        title = "Backend challenge",
        description = "Api web que valida se uma senha é válida.")
)
@SpringBootApplication
public class BackendChallengeApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackendChallengeApplication.class, args);
    }
}
