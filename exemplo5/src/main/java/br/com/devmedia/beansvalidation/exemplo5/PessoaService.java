package br.com.devmedia.beansvalidation.exemplo5;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

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
}
