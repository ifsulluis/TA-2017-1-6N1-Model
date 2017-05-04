
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization Instituto Federal Sul-Rio-Grandense Campus Passo Fundo
 */
@Entity
@Table(name = "colaborador")
public class Colaborador implements Serializable {
    @Id
    @SequenceGenerator(name = "seq_colaborador", sequenceName = "seq_colaborador_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_colaborador", strategy = GenerationType.SEQUENCE)    
    private Integer id;
    @NotNull(message = "Campo carga horária não pode ser nulo")
    @Column(name = "carga_horaria", nullable = false)     
    private Integer cargaHoraria;
    @NotNull(message = "Campo gestor não pode ser nulo")
    @Column(name = "gestor", nullable = false)    
    private Boolean gestor;
    @NotNull(message = "O Usuário não pode ser nulo")
    @ManyToOne
    @JoinColumn(name = "usuario", referencedColumnName = "id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_colaborador_usuario"))    
    private Usuario usuario;
    @NotNull(message = "O Projeto não pode ser nulo")
    @ManyToOne
    @JoinColumn(name = "projeto", referencedColumnName = "id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_colaborador_projeto"))        
    private Projeto projeto;

    public Colaborador() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public Boolean getGestor() {
        return gestor;
    }

    public void setGestor(Boolean gestor) {
        this.gestor = gestor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.id);
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
        final Colaborador other = (Colaborador) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
   
}
