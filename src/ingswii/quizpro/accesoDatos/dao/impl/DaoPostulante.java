/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ingswii.quizpro.accesoDatos.dao.impl;

import ingswii.quizpro.accesoDatos.dao.*;
import ingswii.quizpro.biz.vo.ram.Postulante;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Jorge
 */
public class DaoPostulante extends AbstractDao<Postulante> implements IDaoPostulante{

    public Postulante buscarPorDocumento(String documento) {

        Query query = getEntityManager().createQuery("SELECT u FROM " + Postulante.class.getSimpleName() + " u WHERE u.documento = :documento");
        query.setParameter("documento", documento);
        
        List<Postulante> lista = ejecutarSelect(query);
        
        if(lista == null || lista.size() <= 0) {
            return null;
        }
        
        return lista.get(0);
    }
    
        public List<Postulante> buscar(Postulante postulante) {
        
        String strQuery = "SELECT u FROM " + Postulante.class.getSimpleName() + " u WHERE (u.nombre LIKE :nombre) AND (u.documento LIKE :documento)";
        Query query = getEntityManager().createQuery(strQuery);
        query.setParameter("nombre", "%" + postulante.getNombre() + "%");
        query.setParameter("documento", "%" +  postulante.getDocumento() + "%");
        List<Postulante> lista = ejecutarSelect(query);
        
        if(lista == null || lista.size() <= 0) {
            return null;
        }
        
        return lista;
    }
}
