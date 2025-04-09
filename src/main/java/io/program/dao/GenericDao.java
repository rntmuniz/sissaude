package io.program.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;

//@ApplicationScoped
public abstract class GenericDao<T> {
    
    @PersistenceContext
    protected EntityManager em;
    
    private final Class<T> entityClass;

    public GenericDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    
    public void save(T entity){
        em.persist(entity);
    }
    
    public void update(T entity){
        em.merge(entity);
    }

    public void delete(T entity){
        try {
            entity.getClass().getMethod("setAtivo", Boolean.class).invoke(entity, false);
            em.merge(entity);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao inativar a entidade:" + entity.getClass().getSimpleName(),e);
        }
    }
    
    public T findById(Long id){
        return em.find(entityClass, id);
    }
    
    public List<T> findAll(){
        try {
           entityClass.getDeclaredField("ativo");
           return em.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e WHERE e.ativo = true", entityClass)
           .getResultList();         
        } catch (NoSuchFieldException e) {
           return em.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e ", entityClass)
           .getResultList();         
        }
    }
    
//    public List<T> pesquisaParteNome(T entity, String table) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException{
//
////        if (table.equals("")){
////          table = "produto";   
////        }
//        
//        String jpql = "select c from " + table +" c where c.ativo = true";
//                
//        if ((entity.getClass().getMethod("getNome", String.class).invoke(entity)!= null) && (!entity.getClass().getMethod("getNome", String.class).invoke(entity).equals(""))){
//            jpql += " and c.nome like :nome";
//        }
//        
//        Query query = em.createQuery(jpql);
//        if ((entity.getClass().getMethod("getNome", String.class).invoke(entity) != null) && (!entity.getClass().getMethod("getNome", String.class).invoke(entity).equals(""))){        
//            query.setParameter("nome", "%" + entity.getClass().getMethod("getNome", String.class).invoke(entity) + "%");
//        }
//        
//        return query.getResultList();
//    }
    
//    public List<T> buscaPorNome(T entity, String table) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException{
//
////        if (table.equals("")){
////          table = "produto";   
////        }
//        
//        String jpql = "select c from " + table +" c where c.ativo = true";
//        
//        if ((entity.getClass().getMethod("getNome", String.class).invoke(entity) != null)&& (!entity.getClass().getMethod("getNome", String.class).invoke(entity).equals(""))){
//            jpql += " and c.nome = :nome";
//        }
//                
//        Query query = em.createQuery(jpql);
//        query.setParameter("nome", entity.getClass().getMethod("getNome", String.class).invoke(entity));
//        
//        return query.getResultList();
//    }


//-----------
    
    public List<T> filtraPorNome(T entity, String s) {

        String jpql = "select c from " + entityClass.getSimpleName() + " c where c.ativo = true";
        Query query;

        try {
            if (s.equals("")) {
                query = em.createQuery(jpql);
            } else {
                jpql += " and c.nome like :cNome";
                query = em.createQuery(jpql);
                query.setParameter("cNome", "%" + s + "%");
            }
            return query.getResultList();
        } catch (Exception e) {
           return em.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e WHERE e.ativo = true", entityClass)
           .getResultList();               
        }
    }
    
}
