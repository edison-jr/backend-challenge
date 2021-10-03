package oliveira.edison.backendchallenge.service.password;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oliveira.edison.backendchallenge.service.password.exceptions.PasswordPatternException;
import oliveira.edison.backendchallenge.service.password.validator.IPasswordValidator;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class PasswordService implements IPasswordService {

    private final IPasswordValidator passwordValidator;

    public String validate(char[] secret) throws PasswordPatternException {
        try {
            passwordValidator.validate(secret);
            log.debug(VALID_PASSWORD);
            return VALID_PASSWORD;
        } catch (PasswordPatternException e) {
            log.error(INVALID_PASSWORD, e);
            throw e;
        }
    }
}
