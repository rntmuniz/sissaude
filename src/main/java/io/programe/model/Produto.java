package io.programe.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Produto implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_produto_id")
    private Long Id;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private String descricao;
    
    @Column(nullable = false)
    private int estoque;
    
    @Column(nullable = false)
    private Boolean ativo;
     
    public Produto() {
       ativo = Boolean.TRUE;
    }

    public Long getId() {
        return Id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.Id);
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
        final Produto other = (Produto) obj;
        return Objects.equals(this.Id, other.Id);
    }

    @Override
    public String toString() {
        return "Produto{" + "Id=" + Id + ", nome=" + nome + ", descricao=" + descricao + ", estoque=" + estoque + ", ativo=" + ativo + '}';
    }   
    
}
