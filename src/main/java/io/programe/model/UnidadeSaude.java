package io.programe.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
public class UnidadeSaude implements Serializable{
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_unida_saude_id")
    private Long id;
    
    @OneToMany(mappedBy = "unidadeSaude")
    private List<Saida> saidas;
    
    @Column(nullable = false)
    private String codigo;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private Boolean ativo;

    public UnidadeSaude() {
       ativo = Boolean.TRUE;
    }

    public List<Saida> getSaidas() {
        return saidas;
    }

    public Long getId() {
        return id;
    }
    
    public void setSaidas(List<Saida> saidas) {
        this.saidas = saidas;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final UnidadeSaude other = (UnidadeSaude) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "UnidadeSaude{" + "id=" + id + ", codigo=" + codigo + ", nome=" + nome + ", ativo=" + ativo + '}';
    }
    
}
