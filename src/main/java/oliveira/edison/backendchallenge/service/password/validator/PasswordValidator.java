package oliveira.edison.backendchallenge.service.password.validator;

import lombok.AllArgsConstructor;
import oliveira.edison.backendchallenge.service.password.exceptions.DuplicateCharException;
import oliveira.edison.backendchallenge.service.password.exceptions.EmptyPasswordException;
import oliveira.edison.backendchallenge.service.password.exceptions.InvalidCharException;
import oliveira.edison.backendchallenge.service.password.exceptions.PasswordLengthException;
import oliveira.edison.backendchallenge.service.password.exceptions.PasswordPatternException;

import java.util.Set;
import java.util.TreeSet;

@AllArgsConstructor
public class PasswordValidator implements IPasswordValidator {

    private final int minLength;
    private final int maxLength;
    private final String symbols;
    private final boolean allowDuplicates;

    /**
     * Verifica se a senha fornecida atende todos os requisitos.
     * <ul>
     *   <li>Mais que <a href="#minLength">minLength</a> caracteres
     *   <li>Até <a href="#minLength">maxLength</a> caracteres
     *   <li>Ao menos 1 dígito
     *   <li>Ao menos 1 letra maiúscula
     *   <li>Ao menos 1 letra minúscula
     *   <li>Ao menos 1 <a href="#symbols">symbol</a>
     *   <li>Não possuir caracteres repetidos dentro do conjunto
     * </ul>
     */
    @Override
    public void validate(char[] secret) throws PasswordPatternException {
        validateLength(secret);
        validatePattern(secret);
    }

    private void validateLength(char[] secret) throws PasswordPatternException {
        if (secret.length == 0) throw new EmptyPasswordException();
        if (secret.length < minLength) throw PasswordLengthException.minLength(minLength);
        if (secret.length > maxLength) throw PasswordLengthException.maxLength(maxLength);
    }

    private void validatePattern(char[] secret) throws PasswordPatternException {
        boolean hasNumber = false;
        boolean hasUpperCaseLetter = false;
        boolean hasLowerCaseLetter = false;
        boolean hasSymbol = false;
        Set<Character> chars = new TreeSet<>();
        for (char c : secret) {
            if (Character.isDigit(c)) {
                hasNumber = true;
            } else if (Character.isUpperCase(c)) {
                hasUpperCaseLetter = true;
            } else if (Character.isLowerCase(c)) {
                hasLowerCaseLetter = true;
            } else if (symbols.indexOf(c) > -1) {
                hasSymbol = true;
            } else {
                throw new InvalidCharException(c);
            }
            if (!allowDuplicates && !chars.add(c)) throw new DuplicateCharException(c);
        }
        if (!hasNumber) throw PasswordPatternException.missingNumber();
        if (!hasUpperCaseLetter) throw PasswordPatternException.missingUpperCaseLetter();
        if (!hasLowerCaseLetter) throw PasswordPatternException.missingUpperCaseLetter();
        if (!hasSymbol) throw PasswordPatternException.missingSymbol();
    }

    @Override
    public String toString() {
        return "PasswordValidator{" + "minLength=" + minLength + ", maxLength=" + maxLength + ", symbols='" + symbols +
            '\'' + ", allowDuplicates=" + allowDuplicates + '}';
    }
}
