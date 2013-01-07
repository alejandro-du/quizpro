package ingswii.quizpro.biz.vo.ram;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Alejandro, Jorge
 */
@Entity
@Table(name="pregunta")
public class Pregunta implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="pre_id")
    private Long id;
    
    @Column(name="pre_texto")
    private String texto;
    
    @Column(name="pre_ponderacion")
    private Integer ponderacion;
    
    @ManyToOne
    @JoinColumn(name="tem_id")
    private Tema tema;
    
    @ManyToOne
    @JoinColumn(name="exa_id")
    private Examen examen;
    
    @OneToOne
    @JoinColumn(name="res_id")
    private Respuesta respuestaCorrecta;
    
    @OneToMany
    @JoinTable(joinColumns=@JoinColumn(name="pre_id"), inverseJoinColumns=@JoinColumn(name="res_id"))
    private List<Respuesta> respuestas;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pregunta)) {
            return false;
        }
        Pregunta other = (Pregunta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ingswii.quizpro.biz.vo.ram.Pregunta[id=" + id + "]";
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Integer getPonderacion() {
        return ponderacion;
    }

    public void setPonderacion(Integer ponderacion) {
        this.ponderacion = ponderacion;
    }

    public Respuesta getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public void setRespuestaCorrecta(Respuesta respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public List<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }

}
