package br.com.devmedia.beansvalidation.exemplo2;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * @author kennedy
 */
public class Pessoa {

    // O nome não pode ser nulo e devera ter entre 5 e 50 caracteres
    @NotNull
    @Size(min = 5, max = 50, message = "{devmedia.exemplo2.pessoa.nome}")
    private String nome;

    // O sobrenome não pode ser nulo e devera ter menos que 255 caracteres
    @NotNull
    @Size(max = 255, message = "{devmedia.exemplo2.pessoa.sobreNome}")
    private String sobreNome;

    // A idade tem que ser no minimo 18 caso a flag maiorDeIdade seja true
    @Min(value = 18, message = "{devmedia.exemplo2.pessoa.idade}", groups = MaiorDeIdadeCheck.class)
    private int idade;

    @AssertTrue(groups = MaiorDeIdadeCheck.class)
    private boolean maiorDeIdade;

    // A data de nascimento deve estar no passado
    @Past
    private Date dataNascimento;

    // Deve ser um CPF valido
    @CPF(message = "O CPF nao e valido")
    private String cpf;

    // Deve ser um email valido nome@dominio.com
    @Email(message = "O email informado nao e valido")
    private String email;

    // Deve ser sempre False
    @AssertFalse
    private boolean administrador;

    public Pessoa() {
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
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

    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isMaiorDeIdade() {
        return maiorDeIdade;
    }

    public void setMaiorDeIdade(boolean maiorDeIdade) {
        this.maiorDeIdade = maiorDeIdade;
    }
}
