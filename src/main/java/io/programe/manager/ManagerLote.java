package io.programe.manager;

import io.programe.model.Lote;
import io.programe.model.Produto;
import io.programe.service.LoteService;
import io.programe.service.ProdutoService;
import io.programe.util.Mensagem;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.SessionScoped;
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
public class ManagerLote implements Serializable{
    
    @Inject 
    private LoteService loteService;
    
    @Inject
    private ProdutoService produtoService;
    
    private Lote lote;
    private List<Lote> lotes;
    Boolean btnVerSalvar = true;
    Boolean EditDesabilitado = false;
    String nomeBotao = "Salvar";
    String voltarPara = "pesquisaLote.xhtml";    
    String filtro = "";

    @PostConstruct
    public void iniciarLote(){
        instanciarLote();
        lotes = new ArrayList<>();
        carregarParametro();
    }

    public void instanciarLote(){
        lote = new Lote();
        lote.setProduto(new Produto());
    }    
    
    public void adicionarLoteNaLista(){
        lotes.add(lote);
        instanciarLote();
    }
    
    public void remover(Lote l){
        try {
            lotes.remove(l);
            Mensagem.mensagemInfo("Lote removido com sucesso.");
        } catch (Exception e) {
            Mensagem.mensagemErr("Erro ao excluir Lote:" + Mensagem.getMessageErro(e));
        }        
    }
    
    public void visualizarObjeto(){
        this.nomeBotao = "Visualizar";
        this.btnVerSalvar = false;
        this.EditDesabilitado = true;
    }
        
    public void inativarLote(){
        try {
            loteService.inativaLote(lote);
            Mensagem.mensagemInfo("Lote excluído com sucesso.");
        } catch (Exception e) {
            Mensagem.mensagemErr("Erro ao excluir Lote:" + Mensagem.getMessageErro(e));
        }
    }
    
    public void salvar() {
        try {
            if ((lote.getId() == null) && (!lote.getDescricao().equals(""))) {
                loteService.salvar(lote);
                Mensagem.mensagemInfo("Produto salvo com sucesso.");
            } else {
                if (lote.getId() != null) {
                    loteService.atualizar(lote);
                    Mensagem.mensagemInfo("Lote atualizado com sucesso.");
                }
                Mensagem.mensagemAlert("Lote inválido preencha os campos.");
            }
            instanciarLote();
//            Mensagem.mensagemInformacao("Produto salvo com sucesso.");
        } catch (Exception e) {
            Mensagem.mensagemErr("Erro ao salvar Lote:" + Mensagem.getMessageErro(e));
        }

    }

    public void salvarLista() {
        for (Lote l : lotes) {
            loteService.salvar(l);
        }
        Mensagem.mensagemInfo("Produtos salvos com sucesso.");
        iniciarLote();
    }
        
    public void carregarParametro(){
        Map<String, String> parametro = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String visualizar = parametro.get("visualizar");
        String editar = parametro.get("editar");
            
        if (visualizar != null){
            lote = loteService.buscaPorId(Long.valueOf(visualizar));
            visualizarObjeto();
        }else if (editar != null){
            lote = loteService.buscaPorId(Long.valueOf(editar));
            this.nomeBotao = "Atualizar";
            this.btnVerSalvar = true;
        }else{
            this.btnVerSalvar = true;
            this.nomeBotao = "Salvar";
            this.voltarPara = "index.xhtml";
        }
    }    
    
    public void buscaTodosLotes() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException{
        lotes = loteService.listaTodosLotes();
    }    
    
    public void filtraPorNome() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException{
        lotes = loteService.filtraPorNome(lote, filtro);
    }
        
    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public List<Lote> getLotes() {
        return lotes;
    }

    public void setLotes(List<Lote> lotes) {
        this.lotes = lotes;
    }
    
    public Boolean getBtnVerSalvar() {
        return btnVerSalvar;
    }

    public void setBtnVerSalvar(Boolean btnVerSalvar) {
        this.btnVerSalvar = btnVerSalvar;
    }

    public Boolean getEditDesabilitado() {
        return EditDesabilitado;
    }

    public void setEditDesabilitado(Boolean EditDesabilitado) {
        this.EditDesabilitado = EditDesabilitado;
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

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }    

    public List<Produto> autoCompleteProduto(String nome){
        return produtoService.listaProdutoPorNome(nome);
    }
    
}
