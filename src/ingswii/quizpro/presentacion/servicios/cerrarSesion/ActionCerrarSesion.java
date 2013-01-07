package ingswii.quizpro.presentacion.servicios.cerrarSesion;

import com.opensymphony.xwork2.validator.annotations.Validation;
import ingswii.quizpro.presentacion.comun.IMenuDeUsuario;
import ingswii.quizpro.presentacion.comun.ISesion;
import ingswii.quizpro.presentacion.comun.impl.AbstractAction;


/**
 * Servicio para cerrar la sesion de usuario.
 * @author Jorge
 */
@Validation()
public class ActionCerrarSesion extends AbstractAction{
    
    private IMenuDeUsuario menuDeUsuario;
    
    public String inicio() {
        return OK;
    }
    
    public String cerrarSesion() {
        getSesion().eliminarObjetoDeSesion(ISesion.CONVOCATORIA);
        getSesion().eliminarObjetoDeSesion(ISesion.USUARIO_AUTENTICADO);
        getSesion().eliminarObjetoDeSesion(ISesion.SERVICIO);
        getSesion().eliminarObjetoDeSesion(ISesion.JAVASCRIPT_MENU);
        addActionMessage("La sesión ha sido cerrada correctamente.");
        return INDEX;  
    }

    public IMenuDeUsuario getMenuDeUsuario() {
        return menuDeUsuario;
    }

    public void setMenuDeUsuario(IMenuDeUsuario menuDeUsuario) {
        this.menuDeUsuario = menuDeUsuario;
    }

}
