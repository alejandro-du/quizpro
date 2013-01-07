package ingswii.quizpro.biz.controlador;

import ingswii.quizpro.biz.vo.ram.Convocatoria;
import ingswii.quizpro.biz.vo.ram.Examen;
import java.util.List;

/**
 * Proporciona funcionalidad para los VOs Examen.
 * @author Alejandro
 */
public interface ICtrlExamen {
    
    List<Examen> obtenerExamenes();
    
    Examen obtenerExamenPorId(Long id);
    
    List<Examen> buscarExamenes(Examen examen);
    
    boolean guardarExamen(Examen examen);
    
    boolean eliminarExamenPorId(Examen examen);
    
    boolean calificar(Convocatoria convocatoria);
    
    boolean clasificar(Convocatoria convocatoria);
    
}
