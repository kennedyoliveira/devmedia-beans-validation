package br.com.devmedia.beansvalidation.exemplo4;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;

/**
 * @author kennedy
 */
@WebServlet("pessoa/cadastrar2")
public class PessoaServlet2 extends HttpServlet {

    @Inject
    private Validator validator;

    @Resource
    private ValidatorFactory validatorFactory;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
