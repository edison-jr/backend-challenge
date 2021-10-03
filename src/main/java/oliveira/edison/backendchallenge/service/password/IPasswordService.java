package oliveira.edison.backendchallenge.service.password;

import oliveira.edison.backendchallenge.service.password.exceptions.PasswordPatternException;

public interface IPasswordService {

    String VALID_PASSWORD = "Password matches all requirements!";
    String INVALID_PASSWORD = "Invalid password";

    /**
     * Verifica se uma senha é válida.
     *
     * @param secret uma senha
     * @return <a href="#VALID_PASSWORD">VALID_PASSWORD</a> se atender todos os requisitos
     * @throws PasswordPatternException se não atender os requisitos
     */
    String validate(char[] secret) throws PasswordPatternException;
}
