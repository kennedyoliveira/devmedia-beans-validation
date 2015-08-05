package br.com.devmedia.beansvalidation.exemplo3;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author kennedy
 */
@Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
@Retention(RUNTIME)
// Aqui é especificado o validador
@Constraint(validatedBy = ChecarMaiusculaMinusculaValidator.class)
@Documented
public @interface ChecarMaisculaMinuscula {

    enum MaisculaMinuscula {
        MAISCULA,
        MINUSCULA
    }

    String message() default "{br.com.devmedia.beansvalidation.exemplo3.maisculaminuscula}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    MaisculaMinuscula value();

    @Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        MaisculaMinuscula[] value();
    }
}
