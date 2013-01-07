package ingswii.quizpro.biz.controlador;

import ingswii.quizpro.biz.vo.ram.Decision;
import ingswii.quizpro.biz.vo.ram.Postulante;
import ingswii.quizpro.biz.vo.ram.Pregunta;
import ingswii.quizpro.biz.vo.ram.Tema;
import java.util.List;

/**
 * Proporciona funcionalidad para los VOs Decision.
 * @author Alejandro
 */
public interface ICtrlDecision {
    
    boolean guardarDecision(Postulante postulante, Pregunta pregunta, Long respuestaId);
    
    List<Decision> obtenerPorPostulanteYTema(Postulante postulante, Tema tema);
    
}
