package ingswii.quizpro.biz.controlador;

import ingswii.quizpro.biz.vo.ram.Examen;
import ingswii.quizpro.biz.vo.ram.Postulante;
import ingswii.quizpro.biz.vo.ram.Resultado;
import java.util.List;

/**
 * Proporciona funcionalidad para los VOs Resultado.
 * @author Alejandro
 */
public interface ICtrlResultado {
    
    boolean guardarResultado(Resultado resultado);
    
    boolean eliminarPorExamen(Examen examen);
    
    List<Resultado> obtenerPrimerosResultados(Examen examen, int cantidad);
    
    public List<Resultado> obtenerPorPostulanteYExamen(Postulante postulante, Examen examen);
    
    Resultado obtenerFinalPorPostulanteYExamen(Postulante postulante, Examen examen);
}
