package io.programe.service;

import io.program.dao.ProdutoDao;
import io.programe.model.Produto;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@ApplicationScoped
//@Stateless
public class ProdutoService {

    @Inject
    private ProdutoDao produtoDao;

    private List<Produto> produtos;
    
    
    
    @Transactional
    public void salvar(Produto produto){
        produtoDao.save(produto);
    }
    
    @Transactional
    public void atualizar(Produto produto){
        produtoDao.update(produto);
    }
    
    @Transactional
    public void inativaProduto (Produto produto){
        produtoDao.delete(produto);
    }
    
    public Produto buscaPorId(Long id){
        return produtoDao.findById(id);
    }
    
    public List<Produto> listaTodosProdutos(){
        return produtoDao.findAll();
    }

    public List<Produto> filtraPorNome(Produto produto, String filtro) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException{
        return produtoDao.filtraPorNome(produto, filtro);
    }

    public List<Produto> listaProdutoPorNome(String nome) {
        return produtoDao.findProduto(nome);
    }    
    
    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
        
}
