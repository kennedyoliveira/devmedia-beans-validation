package br.com.devmedia.beansvalidation.exemplo6;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author kennedy
 */
@Path("/pessoa")
public class PessoaController {

    @Inject
    private PessoaService pessoaService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrar(@Valid Pessoa pessoa) throws ConstraintViolationException {
        pessoaService.cadastrar(pessoa);

        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar() {
        final List<Pessoa> pessoas = pessoaService.listar();

        return Response.ok(pessoas).build();
    }
}
