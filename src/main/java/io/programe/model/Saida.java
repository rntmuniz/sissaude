package io.programe.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Saida implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_saida_id")
    private Long id;

    @Column(nullable = false)    
    private Date data;
    
    @ManyToOne
    @JoinColumn(name = "unidadesaude_id", referencedColumnName = "id")
    private UnidadeSaude unidadeSaude;
    
    @OneToMany
    @JoinColumn(name = "lotesaida_id", referencedColumnName = "id")
    private List<Lote> lotesSaida;
    
    @Column(nullable = false)
    private Boolean ativo;

    public Saida() {
    }

    public Saida(Boolean ativo) {
        this.ativo = Boolean.TRUE;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public UnidadeSaude getUnidadeSaude() {
        return unidadeSaude;
    }

    public void setUnidadeSaude(UnidadeSaude unidadeSaude) {
        this.unidadeSaude = unidadeSaude;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public List<Lote> getLotesSaida() {
        return lotesSaida;
    }

    public void setItensSaida(List<Lote> lotesSaida) {
        this.lotesSaida = lotesSaida;
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
        final Saida other = (Saida) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Saida{" + "id=" + id + ", data=" + data + ", unidadeSaude=" + unidadeSaude + ", ativo=" + ativo + ", itensSaida=" + lotesSaida + '}';
    }
    
}
