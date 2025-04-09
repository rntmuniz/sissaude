package io.program.dao;

import io.programe.model.Lote;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
//@Stateless
public class LoteDao extends GenericDao<Lote> {
    
    public LoteDao() {
        super(Lote.class);
    }
    
}
