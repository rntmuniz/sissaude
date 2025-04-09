package io.programe.service;

import io.program.dao.LoteDao;
import io.programe.model.Lote;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@ApplicationScoped
//@Stateless
public class LoteService {

    @Inject
    private LoteDao loteDao;

    @Transactional
    public void salvar(Lote lote){
        loteDao.save(lote);
    }
    
    @Transactional
    public void atualizar(Lote lote){
        loteDao.update(lote);
    }
    
    @Transactional
    public void inativaLote (Lote lote){
        loteDao.delete(lote);
    }
    
    public Lote buscaPorId(Long id){
        return loteDao.findById(id);
    }
    
    public List<Lote> listaTodosLotes(){
        return loteDao.findAll();
    }

    public List<Lote> filtraPorNome(Lote lote, String filtro) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException{
        return loteDao.filtraPorNome(lote, filtro);
    }

    
}
