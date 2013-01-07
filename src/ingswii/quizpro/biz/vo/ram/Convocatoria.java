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
import javax.persistence.Table;

/**
 *
 * @author Alejandro
 */
@Entity
@Table(name="convocatoria")
public class Convocatoria implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="con_id")
    private Long id;
    
    @Column(name="con_nombre")
    private String nombre;
    
    @Column(name="con_fecha_inicio")
    private String fechaInicio;
    
    @Column(name="con_fecha_fin")
    private String fechaFin;
    
    @Column(name="con_descripcion")
    private String descripcion;
    
    @Column(name="con_vacantes")
    private Integer vacantes;
    
    @ManyToOne
    @JoinColumn(name="exa_id")
    private Examen examen;
    
    @OneToMany
    @JoinTable(joinColumns=@JoinColumn(name="con_id"), inverseJoinColumns=@JoinColumn(name="pos_id"))
    private List<Postulante> postulantes;
    
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
        if (!(object instanceof Convocatoria)) {
            return false;
        }
        Convocatoria other = (Convocatoria) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ingswii.quizpro.biz.vo.convocatoria.Convocatoria[id=" + id + "]";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<Postulante> getPostulantes() {
        return postulantes;
    }

    public void setPostulantes(List<Postulante> postulantes) {
        this.postulantes = postulantes;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }

    public Integer getVacantes() {
        return vacantes;
    }

    public void setVacantes(Integer vacantes) {
        this.vacantes = vacantes;
    }

}
