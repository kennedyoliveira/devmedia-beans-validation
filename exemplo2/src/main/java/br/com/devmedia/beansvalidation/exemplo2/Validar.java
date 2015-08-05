package br.com.devmedia.beansvalidation.exemplo2;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

/**
 * @author kennedy
 */
public class Validar {

    private static Validator getValidator() {
        final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        return validatorFactory.getValidator();
    }

    public static void main(String[] args) throws ParseException {
        cadastroComErro();
        cadastroComSucesso();
        validandoGrupos();
    }


    /**
     * Cadastro e validação com erros
     */
    private static void cadastroComErro() {
        System.out.println("Colocando dados errados na pessoa");

        final Pessoa pessoa = new Pessoa();
        pessoa.setNome("Ana"); // Minimo deveria ser 5
        pessoa.setSobreNome(null); // Não pode ser nulo
        pessoa.setIdade(13); // Minimo deve ser 18
        pessoa.setAdministrador(true); // Deve ser falso
        pessoa.setDataNascimento(new Date()); // Deve ser no passado
        pessoa.setCpf("111.111.111.11"); // Deve ser um CPF valido
        pessoa.setEmail("teste"); // DEveria ser um email valido

        final Validator validator = getValidator();
        final Set<ConstraintViolation<Pessoa>> erros = validator.validate(pessoa);

        System.out.println("Resultado:");

        for (ConstraintViolation<Pessoa> erro : erros) {
            System.out.println(erro.getPropertyPath() + " " + erro.getMessage());
        }

        // saida experada
        // Resultado:
        // email not a well-formed email address
        // cpf invalid Brazilian individual taxpayer registry number (CPF)
        // nome size must be between 5 and 50
        // idade must be greater than or equal to 18
        // administrador must be false
        // sobreNome may not be null
    }

    private static void cadastroComSucesso() throws ParseException {
        System.out.println("\n\n\n");
        System.out.println("Colocando dados validos na pessoa");

        final Pessoa pessoa = new Pessoa();

        pessoa.setAdministrador(false);
        pessoa.setIdade(25);
        pessoa.setCpf("882.966.857-52");
        pessoa.setEmail("emailteste@gmail.com.br");
        pessoa.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("25/05/1960"));
        pessoa.setNome("Zupao");
        pessoa.setSobreNome("Yoshitero");

        final Validator validator = getValidator();
        final Set<ConstraintViolation<Pessoa>> erros = validator.validate(pessoa);

        if (erros.isEmpty()) {
            System.out.println("Nenhum erro no cadastro!");
        } else {
            System.out.println("Ocorreram erros no cadastro:");
            for (ConstraintViolation<Pessoa> erro : erros) {
                System.out.println(erro.getPropertyPath() + " " + erro.getMessage());
            }
        }

        // Saida experada
        // Nenhum erro no cadastro!
    }

    private static void validandoGrupos() {
        System.out.println("\n\n\n");
        System.out.println("Validando constraints por grupo");

        final Pessoa pessoa = new Pessoa();

        pessoa.setIdade(15);
        pessoa.setMaiorDeIdade(false);

        final Validator validator = getValidator();
        final Set<ConstraintViolation<Pessoa>> erros = validator.validate(pessoa, MaiorDeIdadeCheck.class);

        for (ConstraintViolation<Pessoa> erro : erros) {
            System.out.println(erro.getPropertyPath() + " " + erro.getMessage());
        }

        // A saida esperada
        // Validando constraints por grupo
        // maiorDeIdade must be true
        // idade A idade deve da pessoa deve ser de no minimo 18 anos.
    }
}
