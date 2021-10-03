package oliveira.edison.backendchallenge.service.password.validator;

import oliveira.edison.backendchallenge.service.password.exceptions.PasswordPatternException;

public interface IPasswordValidator {
    /**
     * Verifica se uma senha é válida.
     *
     * @param secret uma senha
     * @throws PasswordPatternException se não atender os requisitos
     */
    void validate(char[] secret) throws PasswordPatternException;
}
