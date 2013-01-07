package ingswii.quizpro.presentacion.servicios.actualizarDatos;

import com.opensymphony.xwork2.validator.annotations.ExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import ingswii.quizpro.biz.controlador.ICtrlUsuario;
import ingswii.quizpro.biz.vo.ugs.Usuario;
import ingswii.quizpro.presentacion.comun.ISesion;
import ingswii.quizpro.presentacion.comun.impl.AbstractAction;

/**
 *
 * @author Jorge
 */
public class ActionActualizarDatos extends AbstractAction {

    private Usuario usuario;
    private String nombre;
    private String password;
    private String nuevoPassword;
    private String nuevoPassword2; 
    
    private ICtrlUsuario ctrlUsuario;
            
    public String inicio() {
        return OK;
    }
    
    public void prepareDoActualizarDatos() {
        setNombre("");      
        usuario = (Usuario)getSesion().getObjetoDeSesion(ISesion.USUARIO_AUTENTICADO);
    }
    
    @Validations (
        stringLengthFields={
            @StringLengthFieldValidator(fieldName="nombre", message="El nombre debe tener entre 5 y 60 caracteres.", minLength="5", maxLength="60"),
            @StringLengthFieldValidator(fieldName="password", message="la contraseña debe tener entre 5 y 30 caracteres.", minLength="5", maxLength="60"),
            @StringLengthFieldValidator(fieldName="nuevoPassword", message="la contraseña debe tener entre 5 y 30 caracteres.", minLength="5", maxLength="60"),  
            @StringLengthFieldValidator(fieldName="nuevoPassword2", message="la contraseña debe tener entre 5 y 30 caracteres.", minLength="5", maxLength="60")
        },
        regexFields={
            @RegexFieldValidator(fieldName="nombre", message="El nombre sólo puede contener letras validas y numeros.", expression="[A-Za-záéíóúÁÉíÓÚñÑüÜ.0-9 ]*")
        },
        expressions={
            @ExpressionValidator(message="Las contraseñas no coinciden.", expression="nuevoPassword.equals(nuevoPassword2)"),  
            @ExpressionValidator(message="La contraseña actual es incorrecta.", expression="(password.isEmpty() || usuario.getPassword().equals(password))"),
            @ExpressionValidator(message="si desea cambiar la contraseña debe igresar la contraseña actual.", 
            expression="!(password.isEmpty() && (!nuevoPassword.isEmpty() || !nuevoPassword2.isEmpty()))")
        }
    )
    public String actualizarDatos() {
        if (!getNombre().trim().isEmpty()){
            actualizarNombre();
        }
        if (!getNuevoPassword().isEmpty()){
            actualizarPassword();
        }
        if (getActionMessages().isEmpty()){
            addActionMessage("No se realizó ninguna acción.");
        }  
        return MENSAJE;
    }
    public void actualizarNombre() {
        usuario.setNombre(getNombre().trim());
        getSesion().setObjetoDeSesion(ISesion.USUARIO_AUTENTICADO,usuario);
        getCtrlUsuario().guardarUsuario(usuario);
        addActionMessage("El nombre de usuario ha sido cambiado correctamente.");
        addActionMessage("Nuevo nombre: "+usuario.getNombre());
    }
    
    public void actualizarPassword() {
        usuario.setPassword(getNuevoPassword());
        getSesion().setObjetoDeSesion(ISesion.USUARIO_AUTENTICADO,usuario);
        getCtrlUsuario().guardarUsuario(usuario);
        addActionMessage("La contraseña ha sido cambiada correctamente.");
    }    

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ICtrlUsuario getCtrlUsuario() {
        return ctrlUsuario;
    }

    public void setCtrlUsuario(ICtrlUsuario ctrlUsuario) {
        this.ctrlUsuario = ctrlUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNuevoPassword() {
        return nuevoPassword;
    }

    public void setNuevoPassword(String nuevoPassword) {
        this.nuevoPassword = nuevoPassword;
    }

    public String getNuevoPassword2() {
        return nuevoPassword2;
    }

    public void setNuevoPassword2(String nuevoPassword2) {
        this.nuevoPassword2 = nuevoPassword2;
    }
}

