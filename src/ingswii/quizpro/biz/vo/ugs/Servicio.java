package ingswii.quizpro.biz.vo.ugs;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Alejandro
 */
@Entity
@Table(name="servicio")
public class Servicio implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ser_id")
    private Long id;
    
    @Column(name = "ser_nombre")
    private String nombre;
    
    @Column(name = "ser_url")
    private String url;
    
    @Column(name = "ser_publico")
    private Boolean publico;
    
    @JoinColumn(name="men_id")
    @ManyToOne
    private Menu menu;
    
    @Column(name="ser_requiere_convocatoria")
    private Boolean requiereConvocatoria;
    
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Servicio)) {
            return false;
        }
        Servicio other = (Servicio) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ingswii.quizpro.biz.vo.Servicio[id=" + getId() + "]";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Boolean getPublico() {
        return publico;
    }

    public void setPublico(Boolean publico) {
        this.publico = publico;
    }

    public Boolean getRequiereConvocatoria() {
        return requiereConvocatoria;
    }

    public void setRequiereConvocatoria(Boolean requiereConvocatoria) {
        this.requiereConvocatoria = requiereConvocatoria;
    }

}
