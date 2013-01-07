package ingswii.quizpro.accesoDatos.dao.impl;

import ingswii.quizpro.accesoDatos.dao.IDaoGrupo;
import ingswii.quizpro.biz.vo.ugs.Grupo;
import java.util.List;

/**
 *
 * @author Alejandro
 */
public class DaoGrupo extends AbstractDao<Grupo> implements IDaoGrupo {

    public List<Grupo> buscar(Grupo grupo) {
        
        String strQuery = "SELECT g FROM " + Grupo.class.getSimpleName() + " g WHERE (g.nombre LIKE :nombre)";
        javax.persistence.Query query = getEntityManager().createQuery(strQuery);
        query.setParameter("nombre", "%" + grupo.getNombre() + "%");
        List<Grupo> lista = ejecutarSelect(query);
        
        if(lista == null || lista.size() <= 0) {
            return null;
        }
        
        return lista;
    }

}
