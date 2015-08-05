package br.com.devmedia.beansvalidation.exemplo3;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * @author kennedy
 */
public class Validar {

    private static Validator getValidator() {
        final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        return validatorFactory.getValidator();
    }

    public static void main(String[] args) {
        final Pessoa pessoa = new Pessoa();
        pessoa.setNome("Zupao");

        final Validator validator = getValidator();
        final Set<ConstraintViolation<Pessoa>> erros = validator.validate(pessoa);

        for (ConstraintViolation<Pessoa> erro : erros) {
            System.out.println(erro.getPropertyPath() + " " + erro.getMessage());
        }

        // Saida esperada
        // nome deve estar todo em MAISCULA.
    }
}
