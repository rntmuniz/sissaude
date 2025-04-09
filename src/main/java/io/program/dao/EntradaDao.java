package io.program.dao;

import io.programe.model.Entrada;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EntradaDao extends GenericDao<Entrada>{
    
    public EntradaDao() {
        super(Entrada.class);
    }
    
}
