package br.com.devmedia.beansvalidation.exemplo5;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

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
        final PrintWriter writer = resp.getWriter();
        writer.append("<html><body>");

        final Pessoa pessoa = getPessoaComErro();

        try {
            pessoaService.cadastrar(pessoa);

            writer.append("Cadastrado com sucesso!");
        } catch (ConstraintViolationException e) {
            final Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();

            for (ConstraintViolation<?> c : constraintViolations) {
                writer.append("<li>" + c.getPropertyPath() + ": " + c.getMessage() + "</li>");
            }

            writer.append("</ul>");
        }

        writer.append("</body></html>");
    }

    private Pessoa getPessoaComErro() {
        final Pessoa pessoa = new Pessoa();
        pessoa.setEmail("teste");
        pessoa.setNome("Ana");
        return pessoa;
    }

    private Pessoa getPessoaCorreta() {
        final Pessoa pessoa = new Pessoa();
        pessoa.setNome("Zupão");
        pessoa.setSobreNome("Da Montanha");
        pessoa.setEmail("teste@gmail.com.br");
        pessoa.setIdade(18);

        return pessoa;
    }
}
