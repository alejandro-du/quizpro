package ingswii.quizpro.biz.controlador;

import ingswii.quizpro.biz.vo.ugs.Grupo;
import java.util.List;

/**
 * Proporciona funcionalidad para los VOs de tipo Grupo.
 * @author Alejandro
 */
public interface ICtrlGrupo {

    List<Grupo> obtenerGrupos();
    
    Grupo obtenerGrupoPorId(Long id);
    
    List<Grupo> buscarGrupos(Grupo grupo);

    /**
     * Crea un nuevo grupo con los datos especificados o lo actualiza si ya existe.
     * @param grupo Grupo a crear o actualizar.
     * @return true, si se puede crear o actualizar el nuevo Grupo. false, si ocurre un error.
     */
    boolean guardarGrupo(Grupo grupo);
    
    /**
     * Elimina el Grupo con el id especificado.
     * @param grupo Grupo con el id a eliminar.
     * @return true, si se puede eliminar. false si no existe o si ocurre un error.
     */
    boolean eliminarGrupoPorId(Grupo grupo);
    
}
