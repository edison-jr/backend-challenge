package oliveira.edison.backendchallenge.controller.password;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import oliveira.edison.backendchallenge.controller.password.dto.PasswordRequest;
import oliveira.edison.backendchallenge.service.password.IPasswordService;
import oliveira.edison.backendchallenge.service.password.exceptions.PasswordPatternException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/password")
@Tag(name = "Validar senha", description = "Verifica se uma senha é válida.")
public interface IPasswordController {

    @PostMapping(
        path = "/isValid",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Boolean indicando se a senha é válida.") })
    boolean isValid(@RequestBody PasswordRequest request);

    @PostMapping(
        path = "/validate",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Senha válida",
            content = @Content(
                schema = @Schema(implementation = String.class),
                examples = @ExampleObject(IPasswordService.VALID_PASSWORD))),
        @ApiResponse(responseCode = "400", description = "Senha inválida",
            content = @Content(
                schema = @Schema(implementation = String.class),
                examples = @ExampleObject(IPasswordService.INVALID_PASSWORD))) })
    String validate(@RequestBody PasswordRequest request) throws PasswordPatternException;
}
