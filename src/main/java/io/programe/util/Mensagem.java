package io.programe.util;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

public class Mensagem {
    public static void addMessagem(FacesMessage.Severity severity, String summary, String detail){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }
    
    public static void mensagemInfo(String mensagem){
        addMessagem(FacesMessage.SEVERITY_INFO, mensagem, mensagem);
    }
    
    public static void mensagemAlert(String mensagem){
        addMessagem(FacesMessage.SEVERITY_WARN, mensagem, mensagem);        
    }
    
    public static void mensagemErr(String mensagem){
        addMessagem(FacesMessage.SEVERITY_ERROR, mensagem, mensagem);

    }

    public static String getMessageErro(Exception e){
        while (e.getCause() != null){
            e = (Exception) e.getCause();            
        }
        String retorno = e.getMessage();
        
        return retorno;
    }
    
}
