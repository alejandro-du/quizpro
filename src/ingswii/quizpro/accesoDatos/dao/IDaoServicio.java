package ingswii.quizpro.accesoDatos.dao;

import ingswii.quizpro.biz.vo.ugs.Menu;
import ingswii.quizpro.biz.vo.ugs.Servicio;
import java.util.List;

/**
 *
 * @author Alejandro
 */
public interface IDaoServicio extends IAbstractDao<Servicio> {
    
    Servicio buscarServicioPorUrlYPublico(String url, Boolean publico);
    
    Servicio buscarServicioPorUrl(String url);
    
    List<Servicio> buscarPorPublico(Boolean publico);
    
    List<Servicio> buscarPorMenu(Menu menu);
    
    List<Servicio> buscar(Servicio servicio);
    
    Servicio buscarPorNombre(String nombre);

}
