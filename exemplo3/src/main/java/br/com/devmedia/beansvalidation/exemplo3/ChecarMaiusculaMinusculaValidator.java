package br.com.devmedia.beansvalidation.exemplo3;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author kennedy
 */
public class ChecarMaiusculaMinusculaValidator implements ConstraintValidator<ChecarMaisculaMinuscula, String> {

    private ChecarMaisculaMinuscula.MaisculaMinuscula modo;

    // Método que vai ser chamada ao executar o validador, antes de validar a regra
    public void initialize(ChecarMaisculaMinuscula constraintAnnotation) {
        // Guardamos a configuração da anotação na propriedade
        this.modo = constraintAnnotation.value();
    }

    // Método que executara a validação, deve retornar true se for valido e false do contrario
    public boolean isValid(String valor, ConstraintValidatorContext context) {
        // Caso for nulo, consideramos valido, por que quem valida se é não nulo ou não são as
        // constraints @NotNUll e @Null respectivamente
        if (valor == null)
            return true;

        // Validamos se está correto
        if (modo == ChecarMaisculaMinuscula.MaisculaMinuscula.MAISCULA) {
            return valor.equals(valor.toUpperCase());
        } else {
            return valor.equals(valor.toLowerCase());
        }
    }
}
