package br.com.devmedia.beansvalidation.exemplo3;

/**
 * @author kennedy
 */
public class Pessoa {

    @ChecarMaisculaMinuscula(ChecarMaisculaMinuscula.MaisculaMinuscula.MAISCULA)
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
