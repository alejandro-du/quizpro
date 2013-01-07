package ingswii.quizpro.accesoDatos.dao.impl;

import ingswii.quizpro.accesoDatos.dao.*;
import ingswii.quizpro.biz.vo.ugs.Menu;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Alejandro
 */
public class DaoMenu extends AbstractDao<Menu> implements IDaoMenu {

    public List<Menu> buscar(Menu menu) {
        
        String strQuery = "SELECT m FROM " + Menu.class.getSimpleName() + " m WHERE (m.nombre LIKE :nombre)";
        Query query = getEntityManager().createQuery(strQuery);
        query.setParameter("nombre", "%" + menu.getNombre() + "%");

        List<Menu> lista = ejecutarSelect(query);
        
        if(lista == null || lista.size() <= 0) {
            return null;
        }
        
        return lista;
    }
    

}
