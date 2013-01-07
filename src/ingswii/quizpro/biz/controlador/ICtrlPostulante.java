package ingswii.quizpro.biz.controlador;

import ingswii.quizpro.biz.vo.ram.Postulante;
import java.util.List;

/**
 * Clase control para postulantes.
 * @author Jorge
 */
public interface ICtrlPostulante {
   
    /**
     * Obtiene un postulante dado su documento.
     * @param documento Documento del postulante s buscar.
     * @return Postulante si se encuentra alguno, null si no.
     */
    Postulante buscarPostulantePorDocumento(String documento);
    
    Postulante obtenerPostulantePorId(Long id);
    
    List<Postulante> buscarPostulantes(Postulante postulante);
    
    boolean crearPostulante(Postulante postulante);

    /**
     * Crea un nuevo postulante o lo actualiza con los datos especificados.
     * @param usuario Usuario a crear o actulizar.
     * @return true, si se puede crear el nuevo Postulante. false, si ocurre un error.
     */
    boolean guardarPostulante(Postulante postulante);
    
    boolean eliminarPostulantePorId(Postulante postulante);   
}
