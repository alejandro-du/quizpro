package ingswii.quizpro.presentacion.comun;

import ingswii.quizpro.biz.vo.ugs.Usuario;
import java.util.List;

/**
 * Permite la creación de menús.
 * @author Alejandro
 */
    public interface IMenuDeUsuario {
    
    /**
     * Agrega el submenú especificado.
     * @param submenu Menú a agregar.
     */
    void agregarSubmenu(IMenuDeUsuario submenu);
    
    /**
     * Devuelve el texto del javascript para construir el menú.
     * @return Script para construir el menú especificado.
     */
    String getJavaScript();

    /**
     * Construye el menú para el usuario especificado.
     * @param usuario Si es null, se construirá un menú con servicios públicos,
     * de lo contrario, se construirá el menú con los servicios públicos más los
     * servicios privados del usuario.
     * @prefijoUrl Prefijo a colocar en cada url.
     */
    void construirMenu(Usuario usuario, String prefijoUrl);
    
    /**
     * Construye y carge el menú en la sesión del usuario especificado.
     * @param usuario Usuario al que se le cargará el menú.
     */
    void cargarMenuEnSesion(Usuario usuario);
    
    List<IMenuDeUsuario> getSubmenuList();

    void setSubmenuList(List<IMenuDeUsuario> submenuList);
    
    String getNombre();

    void setNombre(String nombre);

    String getUrl();

    void setUrl(String url);
    
}
