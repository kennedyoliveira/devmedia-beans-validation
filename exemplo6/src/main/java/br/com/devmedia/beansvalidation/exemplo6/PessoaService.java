package br.com.devmedia.beansvalidation.exemplo6;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author kennedy
 */
public class PessoaService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void cadastrar(Pessoa pessoa) {
        entityManager.persist(pessoa);
    }

    public List<Pessoa> listar() {
        return entityManager.createQuery("select p from Pessoa p").getResultList();
    }
}
