package ingswii.quizpro.accesoDatos.dao.impl;

import ingswii.quizpro.accesoDatos.dao.*;
import ingswii.quizpro.biz.vo.ram.Ip;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Alejandro
 */
public class DaoIp extends AbstractDao<Ip> implements IDaoIp {

    public List<Ip> buscar(Ip ip) {
        
        String strQuery = "SELECT i FROM " + Ip.class.getSimpleName() + " i WHERE (i.ip LIKE :ip AND i.alias LIKE :alias)";
        Query query = getEntityManager().createQuery(strQuery);
        query.setParameter("ip", "%" + ip.getIp() + "%");
        query.setParameter("alias", "%" + ip.getAlias() + "%");

        List<Ip> lista = ejecutarSelect(query);
        
        if(lista == null || lista.size() <= 0) {
            return null;
        }
        
        return lista;
    }
    

}
