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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Alejandro, Jorge
 */
@Entity
@Table(name="examen")
public class Examen implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="exa_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name="exa_nombre")
    private String nombre;
    
    @Column(name="exa_publico")
    private Boolean publico;
    
    @Column(name="exa_descripcion")
    private String descripcion;
    
    @OneToMany
    @JoinTable(joinColumns=@JoinColumn(name="exa_id"), inverseJoinColumns=@JoinColumn(name="ipa_id"))
    private List<Ip> ips;

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
        if (!(object instanceof Examen)) {
            return false;
        }
        Examen other = (Examen) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ingswii.quizpro.biz.vo.ram.Examen[id=" + id + "]";
    }

    public Boolean getPublico() {
        return publico;
    }

    public void setPublico(Boolean publico) {
        this.publico = publico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Ip> getIps() {
        return ips;
    }

    public void setIps(List<Ip> ips) {
        this.ips = ips;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
