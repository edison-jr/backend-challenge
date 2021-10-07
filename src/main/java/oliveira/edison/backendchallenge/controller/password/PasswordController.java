package oliveira.edison.backendchallenge.controller.password;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oliveira.edison.backendchallenge.controller.password.dto.PasswordRequest;
import oliveira.edison.backendchallenge.service.password.IPasswordValidator;
import oliveira.edison.backendchallenge.service.password.exceptions.PasswordPatternException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PasswordController implements IPasswordController {

    private final IPasswordValidator passwordService;

    @Override
    public boolean isValid(@RequestBody PasswordRequest request) {
        try {
            passwordService.validate(request.getSecret());
            log.debug(VALID_PASSWORD);
        } catch (PasswordPatternException e) {
            log.warn(INVALID_PASSWORD, e);
            return false;
        }
        return true;
    }

    @Override
    public String validate(@RequestBody PasswordRequest request) throws PasswordPatternException {
        try {
            passwordService.validate(request.getSecret());
            log.debug(VALID_PASSWORD);
            return VALID_PASSWORD;
        } catch (PasswordPatternException e) {
            log.warn(INVALID_PASSWORD, e);
            throw e;
        }
    }
}