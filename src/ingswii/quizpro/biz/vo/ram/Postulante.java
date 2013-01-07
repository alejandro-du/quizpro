package ingswii.quizpro.biz.vo.ram;

import ingswii.quizpro.biz.vo.ugs.*;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Jorge
 */
@Entity
@Table(name="postulante",
       uniqueConstraints={@UniqueConstraint(columnNames={"pos_documento", "con_id"})})
public class Postulante implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pos_id")
    private Long id;
    
    @Column(name = "pos_documento")
    private String documento;
    
    @Column(name = "pos_nombre")
    private String nombre;
    
    @Column(name = "pos_apellido")
    private String apellido;
    
    @Column(name = "pos_email")
    private String email;
    
    @Column(name = "pos_telefono")
    private String telefono;
    
    @Column(name = "pos_password")
    private String password;
    
    @Column(name = "pos_clasificado")
    private Boolean clasificado;
    
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Postulante other = (Postulante) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ingswii.quizpro.accesoDatos.vo.Postulante[id=" + getId() + "]";
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Boolean getClasificado() {
        return clasificado;
    }

    public void setClasificado(Boolean clasificado) {
        this.clasificado = clasificado;
    }
    
}
