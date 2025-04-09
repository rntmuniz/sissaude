package io.programe.manager;

import io.programe.model.Produto;
import io.programe.service.ProdutoService;
import io.programe.util.Mensagem;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Named
@ViewScoped
public class ManagerProduto implements Serializable{

    @Inject 
    private ProdutoService produtoService;
            
    private Produto produto;
    private List<Produto> produtos;
    Boolean btnVerSalvar = true;
    Boolean EditDesabilitado = false;
    String nomeBotao = "Salvar";
    String voltarPara = "pesquisaProduto.xhtml";    
    String filtro = "";
    
    @PostConstruct
    public void iniciarProduto(){
        instanciarProduto();
        produtos = new ArrayList<>();
        carregarParametro();
    }

    public void instanciarProduto(){
        produto = new Produto();
    }    
    
    public void adicionarProdutoNaLista(){
        produtos.add(produto);
        instanciarProduto();
    }

    public void remover(Produto p){
        try {
            produtos.remove(p);
            Mensagem.mensagemInfo("Produto removido com sucesso.");
        } catch (Exception e) {
            Mensagem.mensagemErr("Erro ao excluir Produto:" + Mensagem.getMessageErro(e));
        }        
    }
    
    public void visualizarObjeto(){
        this.nomeBotao = "Visualizar";
        this.btnVerSalvar = false;
        this.EditDesabilitado = true;
    }
        
    public void inativarProduto(){
        try {
            produtoService.inativaProduto(produto);
            Mensagem.mensagemInfo("Unidade de Saúde excluído com sucesso.");
        } catch (Exception e) {
            Mensagem.mensagemErr("Erro ao excluir Unidade de Saúde:" + Mensagem.getMessageErro(e));
        }
    }
    
    public void salvar() {
        try {
            if ((produto.getId() == null) && (!produto.getNome().equals(""))) {
                produtoService.salvar(produto);
                Mensagem.mensagemInfo("Produto salvo com sucesso.");
            } else {
                if (produto.getId() != null) {
                    produtoService.atualizar(produto);
                    Mensagem.mensagemInfo("Produto atualizado com sucesso.");
                }
                Mensagem.mensagemAlert("Produto inválido preencha os campos.");
            }
            instanciarProduto();
//            Mensagem.mensagemInformacao("Produto salvo com sucesso.");
        } catch (Exception e) {
            Mensagem.mensagemErr("Erro ao salvar Produto:" + Mensagem.getMessageErro(e));
        }

    }

//    public void salvarLista() {
//        for (Produto p : produtos) {
//            produtoService.salvar(p);
//        }
//        Mensagem.mensagemInfo("Produtos salvos com sucesso.");
//        iniciarProduto();
//    }
        
    public void salvarLista() {
        if (produtos.isEmpty()) {
            Mensagem.mensagemAlert("Adicione pelo menos um produto na lista.");
        } else {
            for (Produto p : produtos) {
                produtoService.salvar(p);
            }
            produtos.clear();
            iniciarProduto();
            Mensagem.mensagemInfo("Produtos salvos com sucesso.");
        }
    }
        
    public void carregarParametro(){
        Map<String, String> parametro = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String visualizar = parametro.get("visualizar");
        String editar = parametro.get("editar");
            
//        this.voltarPara = "pesquisarCliente.xhtml";
//        this.btnSalvar = true;
        
        if (visualizar != null){
            produto = produtoService.buscaPorId(Long.valueOf(visualizar));
            visualizarObjeto();
        }else if (editar != null){
            produto = produtoService.buscaPorId(Long.valueOf(editar));
            this.nomeBotao = "Atualizar";
            this.btnVerSalvar = true;
        }else{
            this.btnVerSalvar = true;
            this.nomeBotao = "Salvar";
            this.voltarPara = "index.xhtml";
        }
    }    
    
//    public void buscaProduto(){
//        produtos = produtoServico.buscaProduto(produto);
//    }

    public void buscaTodosProdutos() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException{
        produtos = produtoService.listaTodosProdutos();
    }    
    
    public void filtraPorNome() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException{
        produtos = produtoService.filtraPorNome(produto, filtro);
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Boolean getBtnVerSalvar() {
        return btnVerSalvar;
    }

    public void setBtnVerSalvar(Boolean btnVerSalvar) {
        this.btnVerSalvar = btnVerSalvar;
    }

    public String getNomeBotao() {
        return nomeBotao;
    }

    public void setNomeBotao(String nomeBotao) {
        this.nomeBotao = nomeBotao;
    }

    public String getVoltarPara() {
        return voltarPara;
    }

    public void setVoltarPara(String voltarPara) {
        this.voltarPara = voltarPara;
    }

    public Boolean getEditDesabilitado() {
        return EditDesabilitado;
    }

    public void setEditDesabilitado(Boolean EditDesabilitado) {
        this.EditDesabilitado = EditDesabilitado;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }
        
}
