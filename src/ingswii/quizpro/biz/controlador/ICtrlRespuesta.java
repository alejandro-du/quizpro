package ingswii.quizpro.biz.controlador;

import ingswii.quizpro.biz.vo.ram.Respuesta;

/**
 * Proporciona funcionalidad para los VOs Respuesta.
 * @author Alejandro
 */
public interface ICtrlRespuesta {
    
    Respuesta obtenerRespuestaPorId(Long id);
    
}
