package io.programe.service;

import io.program.dao.EntradaDao;
import io.programe.model.Entrada;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@ApplicationScoped
public class EntradaService {

    @Inject
    EntradaDao entradaDao;
    
    @Transactional
    public void salvar(Entrada entrada){
        entradaDao.save(entrada);
    }
    
    @Transactional
    public void atualizar(Entrada entrada){
        entradaDao.update(entrada);
    }
    
    @Transactional
    public void inativaProduto (Entrada entrada){
        entradaDao.delete(entrada);
    }
    
    public Entrada buscaPorId(Long id){
        return entradaDao.findById(id);
    }
    
    public List<Entrada> listaTodosLotes(){
        return entradaDao.findAll();
    }

    public List<Entrada> filtraPorNome(Entrada entrada, String filtro) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException{
        return entradaDao.filtraPorNome(entrada, filtro);
    }
        
    
}
