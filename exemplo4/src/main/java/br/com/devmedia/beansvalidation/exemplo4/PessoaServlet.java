package br.com.devmedia.beansvalidation.exemplo4;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;

/**
 * @author kennedy
 */
@WebServlet("/pessoa/cadastrar")
public class PessoaServlet extends HttpServlet {

    private PessoaService pessoaService;

    /**
     * @deprecated CDI Eyes Only.
     */
    protected PessoaServlet() { this(null); }

    @Inject
    public PessoaServlet(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Pessoa pessoa = new Pessoa();

        try {
            System.out.println("Chamando o cadastro de pessoa com dados manuais");
            pessoaService.cadastrarPessoa(null, 15);
            System.out.println("Fim com Sucesso!"); // Não deve passar por aqui
        } catch (ConstraintViolationException e) {
            for (ConstraintViolation<?> c : e.getConstraintViolations()) {
                System.out.println(c.getPropertyPath() + " " + c.getMessage());
            }
        }

        try {
            System.out.println("\n\nChamadando o cadastro de pessoa passando uma pessoa");
            pessoaService.cadastrarPessoa(pessoa);
            System.out.println("Fim com Sucesso!"); // Não deve passar por aqui
        } catch (ConstraintViolationException e) {
            for (ConstraintViolation<?> c : e.getConstraintViolations()) {
                System.out.println(c.getPropertyPath() + " " + c.getMessage());
            }
        }

        try {
            System.out.println("\n\nObjeto não gerenciado pelo container");
            // Objeto que não é gerenciado pelo container
            new PessoaService().cadastrarPessoa(pessoa);
            System.out.println("Fim com sucesso!"); // Vai passar por aqui, pois o objeto não é gerenciado pelo container e a anotação não irá funcionar
        } catch (Exception e) {
            System.out.println(e.getMessage()); // Não devera dar exception
        }
    }
}
