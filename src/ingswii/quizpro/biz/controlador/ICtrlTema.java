package ingswii.quizpro.biz.controlador;

import ingswii.quizpro.biz.vo.ram.Examen;
import ingswii.quizpro.biz.vo.ram.Tema;
import java.util.List;

/**
 * Proporciona funcionalidad para los VOs Tema.
 * @author Alejandro
 */
public interface ICtrlTema {
    
    List<Tema> obtenerTemas();
    
    Tema obtenerTemaPorId(Long id);
    
    List<Tema> buscarTemas(Tema tema);
    
    boolean guardarTema(Tema tema);
    
    boolean eliminarTemaPorId(Tema tema);
    
    List<Tema> obtenerTemasPorExamen(Examen examen);
    
}
