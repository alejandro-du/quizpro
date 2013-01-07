package ingswii.quizpro.accesoDatos.dao.impl;

import ingswii.quizpro.accesoDatos.dao.*;
import ingswii.quizpro.biz.vo.ram.Convocatoria;
import java.util.List;
import javax.persistence.Query;

/**
 * DAO concreto para el VO Convocatoria.
 * @author Alejandro
 */
public class DaoConvocatoria extends AbstractDao<Convocatoria> implements IDaoConvocatoria {
    
    public List<Convocatoria> buscar(Convocatoria convocatoria) {
        
        String strQuery = "SELECT m FROM " + Convocatoria.class.getSimpleName() + " m WHERE (m.nombre LIKE :nombre) AND (m.fechaFin LIKE :fechaFin) AND (m.fechaInicio LIKE :fechaInicio)";
        Query query = getEntityManager().createQuery(strQuery);
        query.setParameter("nombre", "%" + convocatoria.getNombre() + "%");
        query.setParameter("fechaFin", "%" + convocatoria.getFechaFin() + "%");
        query.setParameter("fechaInicio", "%" + convocatoria.getFechaInicio() + "%");

        List<Convocatoria> lista = ejecutarSelect(query);
        
        if(lista == null || lista.size() <= 0) {
            return null;
        }
        
        return lista;
    }
    
}
