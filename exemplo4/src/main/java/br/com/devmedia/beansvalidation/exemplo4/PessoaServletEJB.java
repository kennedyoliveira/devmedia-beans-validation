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
@WebServlet("pessoa/cadastrar3")
public class PessoaServletEJB extends HttpServlet {
    private PessoaServiceEJB pessoaService;

    /**
     * @deprecated CDI Eyes Only.
     */
    protected PessoaServletEJB() { this(null); }

    @Inject
    public PessoaServletEJB(PessoaServiceEJB pessoaService) {
        this.pessoaService = pessoaService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Pessoa pessoa = new Pessoa();

        try {
            System.out.println("Chamando o cadastro de pessoa com dados manuais");
            pessoaService.cadastrarPessoa(null, 15);
            System.out.println("Fim com Sucesso!"); // Não deve passar por aqui
        } catch (Exception e) {
            // As exceptions quando os objetos são EJBs vem encapsuladas dentro de outras exceptions, será necessario tratar
            // antes de captura-las
            final ConstraintViolationException ce = (ConstraintViolationException) e.getCause();
            for (ConstraintViolation<?> c : ce.getConstraintViolations()) {
                System.out.println(c.getPropertyPath() + " " + c.getMessage());
            }
        }

        try {
            System.out.println("\n\nChamadando o cadastro de pessoa passando uma pessoa");
            pessoaService.cadastrarPessoa(pessoa);
            System.out.println("Fim com Sucesso!"); // Não deve passar por aqui
        } catch (Exception e) {
            final ConstraintViolationException ce = (ConstraintViolationException) e.getCause();
            for (ConstraintViolation<?> c : ce.getConstraintViolations()) {
                System.out.println(c.getPropertyPath() + " " + c.getMessage());
            }
        }

        try {
            System.out.println("\n\nObjeto não gerenciado pelo container");
            // Objeto que não é gerenciado pelo container
            new PessoaServiceEJB().cadastrarPessoa(pessoa);
            System.out.println("Fim com sucesso!"); // Vai passar por aqui, pois o objeto não é gerenciado pelo container e a anotação não irá funcionar
        } catch (Exception e) {
            System.out.println(e.getMessage()); // Não devera dar exception
        }
    }
}
