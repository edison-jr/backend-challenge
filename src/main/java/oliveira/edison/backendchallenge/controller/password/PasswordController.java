package oliveira.edison.backendchallenge.controller.password;

import lombok.RequiredArgsConstructor;
import oliveira.edison.backendchallenge.controller.password.dto.PasswordRequest;
import oliveira.edison.backendchallenge.service.password.IPasswordService;
import oliveira.edison.backendchallenge.service.password.exceptions.PasswordPatternException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PasswordController implements IPasswordController {

    private final IPasswordService passwordService;

    @Override
    public boolean isValid(@RequestBody PasswordRequest request) {
        try {
            passwordService.validate(request.getSecret());
        } catch (PasswordPatternException e) {
            return false;
        }
        return true;
    }

    @Override
    public String validate(@RequestBody PasswordRequest request) throws PasswordPatternException {
        return passwordService.validate(request.getSecret());
    }
}