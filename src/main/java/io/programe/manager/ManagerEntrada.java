package io.programe.manager;

import io.programe.model.Entrada;
import io.programe.model.Lote;
import io.programe.service.EntradaService;
import io.programe.service.LoteService;
import io.programe.util.Mensagem;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class ManagerEntrada implements Serializable{
    
    @Inject
    private EntradaService entradaService;
    
    @Inject
    private LoteService loteService;
    
    private Entrada entrada;
    private Lote lote;
    private List<Lote> listaLotes;

    @PostConstruct
    public void instanciarEntrada(){
        instanciarLote();
        iniciarListaLote();
        entrada = new Entrada();
    }
    
    public void iniciarListaLote(){
        listaLotes = new ArrayList<>();
    }
    
    public void instanciarLote(){
        lote = new Lote();
    }
    
    public void adicionarLoteNaLista(){
        listaLotes.add(lote);
        instanciarLote();
    }
    
    public void removerLote(Lote l){
        try {
            entrada.getLotesEntrada().remove(l);
            Mensagem.mensagemInfo("Lote removido com sucesso.");
        } catch (Exception e) {
            Mensagem.mensagemErr("Erro ao excluir Lote:" + Mensagem.getMessageErro(e));
        }        
    }
    
    public void salvarListaLotes() {
        for (Lote l : entrada.getLotesEntrada()) {
            loteService.salvar(l);
        }
    }
    
    public void salvarEntrada() {
        try {
            if ((entrada.getId() == null) && (entrada.getData() == null)) {
                entradaService.salvar(entrada);
                salvarListaLotes();
                Mensagem.mensagemInfo("Entrada salva com sucesso.");
            } else {
                if (entrada.getId() != null) {
                    entradaService.atualizar(entrada);
                    Mensagem.mensagemInfo("Entrada atualizada com sucesso.");
                }
                Mensagem.mensagemAlert("Entrada inválida preencha os campos.");
            }
            instanciarEntrada();
//            iniciarListaLote();
//            Mensagem.mensagemInformacao("Produto salvo com sucesso.");
        } catch (Exception e) {
            Mensagem.mensagemErr("Erro ao salvar Entrada:" + Mensagem.getMessageErro(e));
        }

    }

    public Entrada getEntrada() {
        return entrada;
    }

    public void setEntrada(Entrada entrada) {
        this.entrada = entrada;
    }

    public List<Lote> getListaLotes() {
        return listaLotes;
    }

    public void setListaLotes(List<Lote> listaLotes) {
        this.listaLotes = listaLotes;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }
    
    
}
