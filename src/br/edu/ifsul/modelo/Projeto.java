package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization Instituto Federal Sul-Rio-Grandense Campus Passo Fundo
 */
@Entity
@Table(name = "projeto")
public class Projeto implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_projeto", sequenceName = "seq_projeto_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_projeto", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @NotNull(message = "O nome não pode ser nulo")
    @NotBlank(message = "O nome não pode ser em branco")
    @Length(max = 50, message = "O nome não pode ter mais que {max} caracteres")
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;
    @NotNull(message = "A descrição não pode ser nula")
    @NotBlank(message = "A descrição não pode ser em branco")
    @Column(name = "descricao", nullable = false, columnDefinition = "text")
    private String descricao;
    @NotNull(message = "Data de início não pode ser nula")
    @Temporal(TemporalType.DATE)
    @Column(name = "inicio", nullable = false)
    private Calendar inicio;
    @NotNull(message = "Data de fim não pode ser nula")
    @Temporal(TemporalType.DATE)
    @Column(name = "fim", nullable = false)
    private Calendar fim;
    @NotNull(message = "Campo ativo não pode ser nulo")
    @Column(name = "ativo", nullable = false)
    private Boolean ativo;
    @NotNull(message = "O Setor não pode ser nulo")
    @ManyToOne
    @JoinColumn(name = "setor", referencedColumnName = "id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_projeto_setor"))
    private Setor setor;
    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL, 
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Colaborador> colaboradores = new ArrayList<>();

    public Projeto() {
    }

    public void adicionarColaborador(Colaborador obj){
        obj.setProjeto(this);
        this.colaboradores.add(obj);
    }
    
    public void removerColaborador(int idx){
        this.colaboradores.remove(idx);
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Calendar getInicio() {
        return inicio;
    }

    public void setInicio(Calendar inicio) {
        this.inicio = inicio;
    }

    public Calendar getFim() {
        return fim;
    }

    public void setFim(Calendar fim) {
        this.fim = fim;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Projeto other = (Projeto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public List<Colaborador> getColaboradores() {
        return colaboradores;
    }

    public void setColaboradores(List<Colaborador> colaboradores) {
        this.colaboradores = colaboradores;
    }

}
