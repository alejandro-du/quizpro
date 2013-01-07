package ingswii.quizpro.accesoDatos.dao;

import ingswii.quizpro.biz.vo.ugs.Grupo;
import java.util.List;

/**
 *
 * @author Alejandro
 */
public interface IDaoGrupo extends IAbstractDao<Grupo> {
    
    List<Grupo> buscar(Grupo grupo);

}
