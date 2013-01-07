package ingswii.quizpro.biz.controlador;

import ingswii.quizpro.biz.vo.ugs.Usuario;
import java.util.List;

/**
 * Clase control para usuarios.
 * @author Alejandro
 */
public interface ICtrlUsuario {
    
    /**
     * Obtiene un usuario dado su login y password.
     * @param login Login del usuario a buscar.
     * @param password Contraseña del usuario a buscar.
     * @return Usuario si se encuentra alguno, null si no.
     */
    Usuario buscarUsuario(String login, String password);
    
    /**
     * Obtiene una lista de Usuarios con los parámetros especificados.
     * @param documento Documento.
     * @param login Login.
     * @param nombre Nombre
     * @return Lista con los Usuarios correspondientes.
     */
    List<Usuario> buscarUsuarios(Usuario usuario);
    
    /**
     * Crea un nuevo usuario con los datos especificados o lo actualiza si ya existe.
     * @param usuario Usuario a crear o actualizar.
     * @return true, si se puede crear o actualizar el nuevo Usuario. false, si ocurre un error.
     */
    boolean guardarUsuario(Usuario usuario);
    
    /**
     * Elimina el usuario con el id especificado.
     * @param usuario Usuario con el id a eliminar.
     * @return true, si se puede eliminar. false si no existe o si ocurre un error.
     */
    boolean eliminarUsuarioPorId(Usuario usuario);
    
    /**
     * Obtiene un usuario dado su id.
     * @param id Id del usuario a buscar.
     * @return Usuario con el id especificado.
     */
    Usuario buscarUsuarioPorId(Long id);
    
    boolean crearUsuario(Usuario usuario);
    
}
