package ingswii.quizpro.biz.controlador;

import ingswii.quizpro.biz.vo.ram.Convocatoria;
import java.util.List;

/**
 * Provee funcionalidad de negocio para Convocatorias.
 * @author AlejandroDu
 */
public interface ICtrlConvocatoria {

    /**
     * Obtiene todas las convocatorias existentes.
     * @return List con las convocatorias existentes.
     */
    List<Convocatoria> obtenerConvocatorias();
    
    /**
     * Obtiene la Convocatoria con el id especificado.
     * @param convocatoria Convocatoria con el id que se desea buscar.
     * @return La Convocatoria con el id especificado, null si no existe.
     */
    Convocatoria obtenerConvocatoriaPorId(Convocatoria convocatoria);
    
    Convocatoria obtenerConvocatoriaPorId(Long id);
    
    List<Convocatoria> buscarConvocatorias(Convocatoria convocatoria);
    
    boolean guardarConvocatoria(Convocatoria convocatoria);
    
    boolean eliminarConvocatoriaPorId(Convocatoria convocatoria);

}
