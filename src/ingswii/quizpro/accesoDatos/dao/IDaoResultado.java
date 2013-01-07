package ingswii.quizpro.accesoDatos.dao;

import ingswii.quizpro.biz.vo.ram.Resultado;
import java.util.List;

/**
 * DAO para los VOs de tipo Resultado.
 * @author Alejandro
 */
public interface IDaoResultado extends IAbstractDao<Resultado> {
    
    boolean eliminarPorExamenId(Long convocatoriaId);
    
    List<Resultado> obtenerPrimerosPuntajes(Long examenId, int cantidad);
    
    List<Resultado> obtenerPorPostulanteIdYExamenId(Long postulanteId, Long examenId);
    
    Resultado obtenerFinalPorPostulanteIdYExamenId(Long postulanteId, Long examenId);
    
}
