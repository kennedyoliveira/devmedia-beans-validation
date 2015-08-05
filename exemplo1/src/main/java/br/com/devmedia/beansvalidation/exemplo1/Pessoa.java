package br.com.devmedia.beansvalidation.exemplo1;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author kennedy
 */
public class Pessoa {

    @NotNull
    @Size(min = 3, max = 50)
    private String nome;

    @Min(18)
    private int idade;

    public Pessoa() {
    }

    public Pessoa(int idade, String nome) {
        this.idade = idade;
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
