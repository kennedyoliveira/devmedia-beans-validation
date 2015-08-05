package br.com.devmedia.beansvalidation.exemplo4;

import javax.ejb.Stateless;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author kennedy
 */
@Stateless
public class PessoaServiceEJB {

    public void cadastrarPessoa(@NotNull String nome, @Min(18) int idade) {
        // Por enquanto não vamos cadastrar no banco, apenas "simular"
        System.out.println("Cadastrando...");
    }

    public void cadastrarPessoa(@Valid Pessoa pessoa) {
        // Por enquanto não vamos cadastrar no banco, apenas "simular"
        System.out.println("Cadastrando...");
    }
}
