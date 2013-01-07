package ingswii.quizpro.biz.controlador;

import ingswii.quizpro.biz.vo.ram.Examen;
import ingswii.quizpro.biz.vo.ram.Postulante;
import ingswii.quizpro.biz.vo.ram.Pregunta;
import java.util.List;

/**
 * Proporciona funcionalidad para los VOs Pregunta.
 * @author Alejandro
 */
public interface ICtrlPregunta {
    
    Pregunta obtenerPreguntaPorId(Long id);
    
    List<Pregunta> buscarPreguntas(Pregunta pregunta);
    
    boolean guardarPregunta(Pregunta pregunta);
    
    boolean eliminarPreguntaPorId(Pregunta pregunta);
    
    Pregunta getPreguntaNoContestada(Examen examen, Postulante postulante);
    
    int getCantidadPreguntasNoContestadas(Examen examen, Postulante postulante);
    
}
