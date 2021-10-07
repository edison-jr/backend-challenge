# Backed Challenge

Api web que valida se uma senha é válida.

## Prerequisitos:

* `jdk 8+`

## Construindo e executando a aplicação:

```
$ ./gradle clean build
$ ./gradlew bootRun
```

Após a execução, você pode testar endpoints da aplicação na porta 8080:

```
$ curl -H "Content-Type: application/json" -X POST -d '{"secret":"AbTp9!fok"}' -X POST http://localhost:8080/password
```

A aplicação segue a especificação [OpenAPI 3.0](https://swagger.io/specification/) encontrada em: 
http://localhost:8080/api

## Executando testes

As coleções de testes estão divididas `test` (unitários) e `integration-test` (integração).

Para executar todos os testes:

```
$ ./gradlew check
```

Ou para executar as coleções de teste individualmente:

```
$ ./gradlew test
$ ./gradlew integrationTest
```

## Sobre o projeto

### Recursos utilizados

* [Spring-Boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/) - Simplificar o processo de 
  configuração e execução da aplicação
* [Spring-MVC](https://spring.io/guides/gs/serving-web-content/) - Simplificar o tratamento de requisições HTTP
* [Gradle](https://gradle.org/) - Build Tool
* [Lombok](https://projectlombok.org/features/all) - Simplificar a criação de classes
* [JUnit](https://junit.org/junit5/docs/current/user-guide/) - Testes automatizados
* [Mockito](https://site.mockito.org/) - Simular instancias de classes e comportamentos de métodos
* [Swagger](https://swagger.io/docs/specification/about/) - Especificação de API (OAS 3.0)
* [Jacoco](https://www.jacoco.org/jacoco/trunk/doc/) - Relatório de cobertura de testes

### Decisões

* A requisição de senha é feita via POST para evitar a exposição de dados sensiveis - 
  [Mais informações](https://owasp.org/www-community/vulnerabilities/Information_exposure_through_query_strings_in_url)
* Java String são imutáveis e armazenadas na String Pool, por isso é recomedado o uso de char[] em dados sensiveis 
  como senhas.
* Apesar de haver um limite lógico no tamanho da senha por não permitir duplicidade de caracteres, foi adicionado 
  uma configuração de máximo de caractéres para evitar trasbordamento de dados ou estouro de campo.
* O endpoint `password/isValid` segue as especificações do projeto, retornando "true" para senhas válidas e "false" 
  para inválidas.
* O endpoint `password/validate` utiliza o HTTP status code para identificar senhas válidas (200) e inválidas (400) 
  com o motivo na mensagem de retorno.