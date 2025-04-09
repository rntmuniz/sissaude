package io.program.dao;

import io.programe.model.Produto;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.jms.Session;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaQuery;
import java.util.List;
import static javax.management.Query.value;

@ApplicationScoped
//@Stateless
public class ProdutoDao extends GenericDao<Produto>{
    
    public ProdutoDao() {
        super(Produto.class);
    }

    public List<Produto> findProduto(String nome) {
        String sql = "select c from Produto c where c.ativo = true ";

        if (nome != null && !nome.equals("")) {
            sql += " and lower (c.nome) like lower(:nome) ";
        }
        Query query = em.createQuery(sql, Produto.class);

        if (nome != null && !nome.equals("")) {
            query.setParameter("nome", "%" + nome.replaceAll(" ", "%") + "%");
        }
        return query.getResultList();
    }

    
}
