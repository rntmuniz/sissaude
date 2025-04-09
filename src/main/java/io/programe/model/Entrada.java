package io.programe.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Entrada implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_entrada_id")
    private Long id;
    
//    @Column(nullable = false)
    private Date data;
    
//    @Column(nullable = false)
    private String numeroNota;
    
//    @Column(nullable = false)
    private String fornecedor;
    
    @OneToMany
    private List<Lote> lotesEntrada;
    
//    @Column(nullable = false)
    private Boolean ativo;

    public Entrada() {
    }
    
    public Entrada(Boolean ativo) {
        this.ativo = Boolean.TRUE;
    }

    public Long getId() {
        return id;
    }
    
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getNumeroNota() {
        return numeroNota;
    }

    public void setNumeroNota(String numeroNota) {
        this.numeroNota = numeroNota;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public List<Lote> getLotesEntrada() {
        return lotesEntrada;
    }

    public void setLotesEntrada(List<Lote> lotesEntrada) {
        this.lotesEntrada = lotesEntrada;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Entrada other = (Entrada) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Entrada{" + "id=" + id + ", data=" + data + ", numeroNota=" + numeroNota + ", fornecedor=" + fornecedor + ", itensEntrada=" + lotesEntrada + ", ativo=" + ativo + '}';
    }

}
