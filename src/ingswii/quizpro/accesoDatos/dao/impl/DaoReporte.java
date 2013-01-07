package ingswii.quizpro.accesoDatos.dao.impl;

import ingswii.quizpro.accesoDatos.dao.*;
import ingswii.quizpro.biz.vo.reportes.Reporte;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Alejandro
 */
public class DaoReporte extends AbstractDao<Reporte> implements IDaoReporte {

    public List<Reporte> buscar(Reporte reporte) {
        
        String strQuery = "SELECT m FROM " + Reporte.class.getSimpleName() + " m WHERE (m.nombre LIKE :nombre)";
        Query query = getEntityManager().createQuery(strQuery);
        query.setParameter("nombre", "%" + reporte.getNombre() + "%");

        List<Reporte> lista = ejecutarSelect(query);
        
        if(lista == null || lista.size() <= 0) {
            return null;
        }
        
        return lista;
    }

}
