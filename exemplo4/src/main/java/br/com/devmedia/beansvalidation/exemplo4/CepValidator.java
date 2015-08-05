package br.com.devmedia.beansvalidation.exemplo4;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author kennedy
 */
public class CepValidator implements ConstraintValidator<CEP, String> {

    @Inject
    private ValidadorCepBrasil validadorCep;

    public void initialize(CEP constraintAnnotation) { }

    public boolean isValid(String value, ConstraintValidatorContext context) {
        return validadorCep.validar(value);
    }
}
