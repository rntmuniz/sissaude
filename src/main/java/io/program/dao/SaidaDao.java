package io.program.dao;

import io.programe.model.Saida;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SaidaDao extends GenericDao<Saida>{
    
    public SaidaDao() {
        super(Saida.class);
    }
    
}
