package io.programe.service;

import io.program.dao.UnidadeSaudeDao;
import io.programe.model.UnidadeSaude;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@ApplicationScoped
//@Stateless
public class UnidadeSaudeService {

    @Inject
    private UnidadeSaudeDao unidadeSaudeDao;

    @Transactional
    public void salvar(UnidadeSaude unidadeSaude){
        unidadeSaudeDao.save(unidadeSaude);
    }
    
    @Transactional
    public void atualizar(UnidadeSaude unidadeSaude){
        unidadeSaudeDao.update(unidadeSaude);
    }
    
    @Transactional
    public void inativar (UnidadeSaude unidadeSaude){
        unidadeSaudeDao.delete(unidadeSaude);
    }

    public List<UnidadeSaude> listarTodos(){
        return unidadeSaudeDao.findAll();
    }
    
    public UnidadeSaude buscarPorId(Long id){
        return unidadeSaudeDao.findById(id);
    }
        
    public List<UnidadeSaude> filtraPorNome(UnidadeSaude unidadeSaude, String s) 
    throws NoSuchMethodException, IllegalAccessException, InvocationTargetException{
        return unidadeSaudeDao.filtraPorNome(unidadeSaude, s);
    }


}
