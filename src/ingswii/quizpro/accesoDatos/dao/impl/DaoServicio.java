package ingswii.quizpro.accesoDatos.dao.impl;

import ingswii.quizpro.accesoDatos.dao.*;
import ingswii.quizpro.biz.vo.ugs.Menu;
import ingswii.quizpro.biz.vo.ugs.Servicio;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Alejandro
 */
public class DaoServicio extends AbstractDao<Servicio> implements IDaoServicio {

    public Servicio buscarServicioPorUrlYPublico(String url, Boolean publico) {
        
        Query query = getEntityManager().createQuery("SELECT s FROM " + Servicio.class.getSimpleName() + " s WHERE s.url = :url AND s.publico = :publico");
        query.setParameter("url", url);
        query.setParameter("publico", publico);
        
        List<Servicio> lista = ejecutarSelect(query);
        
        if(lista != null && lista.size() > 0)
            return lista.get(0);
                
        return null;
    }

    public Servicio buscarServicioPorUrl(String url) {
        
        Query query = getEntityManager().createQuery("SELECT s FROM " + Servicio.class.getSimpleName() + " s WHERE s.url = :url");
        query.setParameter("url", url);
        
        List<Servicio> lista = ejecutarSelect(query);
        
        if(lista != null && lista.size() > 0)
            return lista.get(0);
                
        return null;
    }

    public List<Servicio> buscarPorPublico(Boolean publico) {
        
        Query query = getEntityManager().createQuery("SELECT s FROM " + Servicio.class.getSimpleName() + " s WHERE s.publico = :publico");
        query.setParameter("publico", publico);
        
        return ejecutarSelect(query);
    }

    public List<Servicio> buscarPorMenu(Menu menu) {
        Query query = getEntityManager().createQuery("SELECT s FROM " + Servicio.class.getSimpleName() + " s WHERE s.menu = :menu");
        query.setParameter("menu", menu);
        
        return ejecutarSelect(query);
    }

    public List<Servicio> buscar(Servicio servicio) {
        
        String strQuery = "SELECT s FROM " + Servicio.class.getSimpleName() + " s WHERE (s.nombre LIKE :nombre) AND (s.url LIKE :url)";
        Query query = getEntityManager().createQuery(strQuery);
        query.setParameter("nombre", "%" + servicio.getNombre() + "%");
        query.setParameter("url", "%" +  servicio.getUrl() + "%");

        List<Servicio> lista = ejecutarSelect(query);
        
        if(lista == null || lista.size() <= 0) {
            return null;
        }
        
        return lista;
    }

    public Servicio buscarPorNombre(String nombre) {
        Query query = getEntityManager().createQuery("SELECT s FROM " + Servicio.class.getSimpleName() + " s WHERE s.nombre = :nombre");
        query.setParameter("nombre", nombre);
        
        List<Servicio> lista = ejecutarSelect(query);
        
        if(lista != null && lista.size() > 0)
            return lista.get(0);
                
        return null;
    }

}
