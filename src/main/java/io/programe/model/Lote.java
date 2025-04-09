package io.programe.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
public class Lote implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_lote_id")
    private Long id;
    
//    @Column(nullable = false)
    private String codigo;
    
//    @Column(nullable = false)
    private String descricao;
    
    @OneToOne
    private Produto produto;
    
//    @Column(nullable = false)
    private Date validade;
    
//    @Column(nullable = false)
    private int quantidade;   
    
//    @Column(nullable = false)
    private Boolean ativo;

    @ManyToOne
    private Entrada entrada;
    
    public Lote() {
        this.ativo = Boolean.TRUE;
    }
    
    public Long getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Entrada getEntrada() {
        return entrada;
    }

    public void setEntrada(Entrada entrada) {
        this.entrada = entrada;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        final Lote other = (Lote) obj;
        return Objects.equals(this.id, other.id);
    }
    
//    @Override
//    public String toString() {
//        return "Lote{" + "id=" + id + ", codigo=" + codigo + ", descricao=" + descricao + ", produto=" + produto + ", validade=" + validade + ", quantidade=" + quantidade + ", ativo=" + ativo + '}';
//    }

    @Override
    public String toString() {
        return "Lote{" + "id=" + id + ", codigo=" + codigo + ", descricao=" + descricao + ", validade=" + validade + ", quantidade=" + quantidade + ", ativo=" + ativo + '}';
    }
    
}
