package oliveira.edison.backendchallenge.config;

import oliveira.edison.backendchallenge.service.password.validator.IPasswordValidator;
import oliveira.edison.backendchallenge.service.password.validator.PasswordValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@PropertySource(value = { "classpath:application.yml" })
public class PasswordConfig {

    @Bean
    public IPasswordValidator passwordValidator(
        @Value("${password.minLength}") int minLength,
        @Value("${password.maxLength}") int maxLength,
        @Value("${password.symbols}") String symbols,
        @Value("${password.allowDuplicates}") boolean allowDuplicates
    ) {
        return new PasswordValidator(
            minLength,
            maxLength,
            symbols,
            allowDuplicates
        );
    }
}
