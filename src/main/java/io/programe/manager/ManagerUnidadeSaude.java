package io.programe.manager;

import io.programe.model.UnidadeSaude;
import io.programe.service.UnidadeSaudeService;
import io.programe.util.Mensagem;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
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
//@ApplicationScoped
public class ManagerUnidadeSaude implements Serializable{
    
    @Inject
    private UnidadeSaudeService unidadeSaudeService;
    
    private UnidadeSaude unidadeSaude;
    private List<UnidadeSaude> unidadesSaude;
    Boolean btnVerSalvar = true;
    Boolean EditDesabilitado = false;
    String nomeBotao;
    String voltarPara = "pesquisaUnidadeSaude.xhtml";    
    String filtro = "";
    
    @PostConstruct
    public void initUnidadeSaude(){
        instanciarUnidadeSaude();
        unidadesSaude = new ArrayList<>();
        carregarParametro();
    }
    
    public void instanciarUnidadeSaude(){
        unidadeSaude = new UnidadeSaude();
    }
    
    public void adicionarListaUnidadeSaude(){
        unidadesSaude.add(unidadeSaude);
        instanciarUnidadeSaude();
    }

    public void removerListaUnidadeSaude(UnidadeSaude uS){
        try {
            unidadesSaude.remove(uS);
            Mensagem.mensagemInfo("Unidade Saúde removido com sucesso.");
        } catch (Exception e) {
            Mensagem.mensagemErr("Erro ao excluir Unidade Saúde:" + Mensagem.getMessageErro(e));
        }        
    }
    
    public void visualizarObjeto(){
        this.nomeBotao = "Visualizar";
        this.btnVerSalvar = false;
        this.EditDesabilitado = true;
    }
    
    public void salvar(){
//        unidadeSaudeService.salvar(unidadeSaude);
//        instanciarUnidadeSaude();    
        try {
            if (unidadeSaude.getId() == null){ 
//                    && (!unidadeSaude.getNome().equals(""))){
                unidadeSaudeService.salvar(unidadeSaude);
                Mensagem.mensagemInfo("Unidade de Saúde salva com sucesso.");     
                instanciarUnidadeSaude();
            }else{
//                if (unidadeSaude.getId() != null){
                    unidadeSaudeService.atualizar(unidadeSaude);
                    Mensagem.mensagemInfo("Unidade de Saúde atualizado com sucesso.");            
//                }
//                Mensagem.mensagemAlert("Unidade de Saúde inválido preencha os campos.");
            }   

//            Mensagem.mensagemInformacao("Cliente salvo com sucesso.");
        }catch(Exception e){
            Mensagem.mensagemErr("Erro ao salvar Unidade de Saúde:" + Mensagem.getMessageErro(e));
        }
    }
    
    public void inativar(){
//        unidadeSaudeService.inativar(unidadeSaude);   
        try {
            unidadeSaudeService.inativar(unidadeSaude);
            unidadesSaude = unidadeSaudeService.listarTodos();                    
            Mensagem.mensagemInfo("Unidade de Saúde excluído com sucesso.");            
        } catch (Exception e) {
            Mensagem.mensagemErr("Erro ao excluir Unidade de Saúde:" + Mensagem.getMessageErro(e));
        }
    }
    
    public List<UnidadeSaude> listaTodas(){
        unidadesSaude = unidadeSaudeService.listarTodos();
        return unidadesSaude;
    }

//    public List<UnidadeSaude> listaPorNome() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException{
//        unidadesSaude = unidadeSaudeService.listaPorNomeGen(unidadeSaude);
//        return unidadesSaude;
//    }

    public List<UnidadeSaude> filtraPorNome() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException{
        unidadesSaude = unidadeSaudeService.filtraPorNome(unidadeSaude, filtro);
        return unidadesSaude;
    }

    
//    public List<UnidadeSaude> pesquisaPorParteDoNome() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException{
//        unidadesSaude = unidadeSaudeService.pesquisaParteNomeUnidadeSaudeGen(unidadeSaude);
//        return unidadesSaude;
//    }
    
    public void carregarParametro(){
        Map<String, String> parametro = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String visualizar = parametro.get("visualizar");
        String editar = parametro.get("editar");
            
//        this.voltarPara = "pesquisarCliente.xhtml";
//        this.btnSalvar = true;
        
        if (visualizar != null){
            unidadeSaude = unidadeSaudeService.buscarPorId(Long.valueOf(visualizar));
            visualizarObjeto();
        }else if (editar != null){
            unidadeSaude = unidadeSaudeService.buscarPorId(Long.valueOf(editar));
            this.nomeBotao = "Atualizar";
            this.btnVerSalvar = true;
        }else{
            this.btnVerSalvar = true;
            this.nomeBotao = "Salvar";
            this.voltarPara = "index.xhtml";
        }
    }    
    
    public UnidadeSaude getUnidadeSaude() {
        return unidadeSaude;
    }

    public void setUnidadeSaude(UnidadeSaude unidadeSaude) {
        this.unidadeSaude = unidadeSaude;
    }

    public List<UnidadeSaude> getUnidadesSaude() {
        return unidadesSaude;
    }

    public void setUnidadesSaude(List<UnidadeSaude> unidadesSaude) {
        this.unidadesSaude = unidadesSaude;
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
