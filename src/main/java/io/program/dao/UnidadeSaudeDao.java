package io.program.dao;

import io.programe.model.UnidadeSaude;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.Query;
import java.util.List;

@ApplicationScoped
//@Stateless
public class UnidadeSaudeDao extends GenericDao<UnidadeSaude> {
    
    public UnidadeSaudeDao() {
        super(UnidadeSaude.class);
    }    
    
}
