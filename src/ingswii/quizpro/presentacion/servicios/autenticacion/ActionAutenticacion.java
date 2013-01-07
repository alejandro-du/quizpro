package ingswii.quizpro.presentacion.servicios.autenticacion;

import ingswii.quizpro.biz.controlador.ICtrlUsuario;
import ingswii.quizpro.biz.vo.ugs.Usuario;
import ingswii.quizpro.presentacion.comun.IMenuDeUsuario;
import ingswii.quizpro.presentacion.comun.ISesion;
import ingswii.quizpro.presentacion.comun.impl.AbstractAction;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validation;
import com.opensymphony.xwork2.validator.annotations.Validations;

/**
 * Servicio para autenticarse ante el sistema.
 * @author Alejandro
 */
@Validation
public class ActionAutenticacion extends AbstractAction {
    
    private Usuario usuario;
    
    private ICtrlUsuario ctrlUsuario;
    private IMenuDeUsuario menuDeUsuario;
    
    public String inicio() {
        usuario = null;
        return OK;
    }
    
    @Validations (
        requiredStrings={
            @RequiredStringValidator(fieldName="usuario.login", message="Debe especificar un login."),
            @RequiredStringValidator(fieldName="usuario.password", message="Debe especificar una contraseña.")
        }
    )
    public String autenticar() {
        
        usuario = ctrlUsuario.buscarUsuario(usuario.getLogin().trim(), usuario.getPassword());
        
        if(usuario == null) {
            addActionError("Los datos suministrados son incorrectos. Por favor, intente de nuevo.");
            return ERROR;
        }
        
        getSesion().setObjetoDeSesion(ISesion.USUARIO_AUTENTICADO, usuario);
        // recargar el menú
        getMenuDeUsuario().cargarMenuEnSesion((Usuario) getSesion().getObjetoDeSesion(ISesion.USUARIO_AUTENTICADO));
        addActionMessage("Bienvenido al sistema. Use el menú para acceder a los servicios.");
        return MENSAJE;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ICtrlUsuario getCtrlUsuario() {
        return ctrlUsuario;
    }

    public void setCtrlUsuario(ICtrlUsuario ctrlUsuario) {
        this.ctrlUsuario = ctrlUsuario;
    }

    public IMenuDeUsuario getMenuDeUsuario() {
        return menuDeUsuario;
    }

    public void setMenuDeUsuario(IMenuDeUsuario menuDeUsuario) {
        this.menuDeUsuario = menuDeUsuario;
    }

}
