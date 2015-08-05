package br.com.devmedia.beansvalidation.exemplo6;

import org.hibernate.validator.constraints.Email;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author kennedy
 */
@Entity
public class Pessoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // O nome não pode ser nulo e devera ter entre 5 e 50 caracteres
    @NotNull
    @Size(min = 5, max = 50, message = "{devmedia.exemplo2.pessoa.nome}")
    private String nome;

    // O sobrenome não pode ser nulo e devera ter menos que 255 caracteres
    @NotNull
    @Size(max = 255, message = "{devmedia.exemplo2.pessoa.sobreNome}")
    private String sobreNome;

    // A idade tem que ser no minimo 18 caso a flag maiorDeIdade seja true
    @Min(value = 18, message = "{devmedia.exemplo2.pessoa.idade}")
    private int idade;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pessoa pessoa = (Pessoa) o;

        if (id != pessoa.id) return false;
        if (idade != pessoa.idade) return false;
        if (administrador != pessoa.administrador) return false;
        if (nome != null ? !nome.equals(pessoa.nome) : pessoa.nome != null) return false;
        if (sobreNome != null ? !sobreNome.equals(pessoa.sobreNome) : pessoa.sobreNome != null) return false;
        return !(email != null ? !email.equals(pessoa.email) : pessoa.email != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (sobreNome != null ? sobreNome.hashCode() : 0);
        result = 31 * result + idade;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (administrador ? 1 : 0);
        return result;
    }
}
