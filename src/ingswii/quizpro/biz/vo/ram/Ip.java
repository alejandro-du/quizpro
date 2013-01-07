package ingswii.quizpro.biz.vo.ram;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Alejandro
 */
@Entity
@Table(name="ip_autorizada")
public class Ip implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="ipa_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name="ipa_ip")
    private String ip;
    
    @Column(name="ipa_alias")
    private String alias;

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
        if (!(object instanceof Ip)) {
            return false;
        }
        Ip other = (Ip) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ingswii.quizpro.biz.vo.ram.IpAutorizada[id=" + id + "]";
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

}
