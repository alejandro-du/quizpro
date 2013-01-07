package ingswii.quizpro.accesoDatos.dao;

import ingswii.quizpro.biz.vo.ram.Convocatoria;
import java.util.List;

/**
 *
 * @author Alejandro
 */
public interface IDaoConvocatoria extends IAbstractDao<Convocatoria> {
    
    List<Convocatoria> buscar(Convocatoria convocatoria);

}
