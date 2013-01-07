package ingswii.quizpro.biz.controlador;

import ingswii.quizpro.biz.vo.ugs.Menu;
import ingswii.quizpro.biz.vo.ugs.Servicio;
import ingswii.quizpro.biz.vo.ugs.Usuario;
import java.util.List;

/**
 * Provee funcionalidad de negocio para Servicios
 * @author AlejandroDu
 */
public interface ICtrlServicio {
    
    /**
     * Determina si el usuario especificado tiene permiso para acceder al servicio.
     * @param usuarioAutenticado Usuario a comprobar. Si es null se trata como un usuario no autenticado.
     * @param servicio Servicio a comprobar.
     * @return true si el usuario puede acceder al servicio, false si no.
     */
    boolean usuarioPuedeAcceder(Usuario usuarioAutenticado, Servicio servicio);
            
    /**
     * Determina si el usuario especificado puede acceder al la url.
     * @param url url a comprobar.
     * @param usuario Usuario a comprobar. Si es null se trata como un usuario no autenticado.
     * @return true si el usuario puede acceder a la url, false si no.
     */
    boolean usuarioPuedeAcceder(Usuario usuarioAutenticado, String url);
    
    /**
     * Obtiene un servicio dada su url.
     * @param url url del servicio a buscar.
     * @return Servicio con la url especificada.
     */
    Servicio obtenerServicioPorUrl(String url);
    
    /**
     * Obtiene una lista con todos los servicios públicos.
     * @return Lista de servicios públicos.
     */
    List<Servicio> obtenerServiciosPublicos();
    
    /**
     * Obtiene todos los servicios que se muestran en el menú especificado.
     * @param menu Menú
     * @return Lista de servicios del menú especificado.
     */
    List<Servicio> obtenerServiciosDelMenu(Menu menu);
    
    /**
     * Obtiene todos los servicios.
     * @return Lista con todos los servicios.
     */
    List<Servicio> obtenerServicios();
    
    Servicio obtenerServicioPorId(Long id);
    
    List<Servicio> buscarServicios(Servicio servicio);
    
    boolean guardarServicio(Servicio servicio);
    
    boolean eliminarServicioPorId(Servicio servicio);
    
    /**
     * Obtiene el servicio inicial de la aplicación.
     * @return Servicio inicial de la aplicación.
     */
    Servicio obtenerServicioInicial();
    
}
