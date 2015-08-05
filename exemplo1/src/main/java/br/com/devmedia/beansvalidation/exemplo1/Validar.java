package br.com.devmedia.beansvalidation.exemplo1;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * @author kennedy
 */
public class Validar {

    /**
     * Cria uma instacia do {@link Validator} do Beans Validation
     */
    private static Validator getValidator() {
        final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        return validatorFactory.getValidator();
    }

    public static void main(String[] args) {
        // Cria a pessoa sem especificar nada nas propriedades, o que viola as regras anotadas no objeto
        final Pessoa pessoa = new Pessoa();

        final Validator validator = getValidator();

        final Set<ConstraintViolation<Pessoa>> constraintsVioladas = validator.validate(pessoa);

        for (ConstraintViolation<Pessoa> constraint : constraintsVioladas) {
            System.out.println(constraint.getPropertyPath() + " " + constraint.getMessage());
        }
    }
}