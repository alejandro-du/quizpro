package ingswii.quizpro.presentacion.servicios.administrarUsuarios;

import com.opensymphony.xwork2.validator.annotations.ExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validation;
import com.opensymphony.xwork2.validator.annotations.Validations;
import ingswii.quizpro.biz.controlador.ICtrlGrupo;
import ingswii.quizpro.biz.controlador.ICtrlUsuario;
import ingswii.quizpro.biz.vo.ugs.Grupo;
import ingswii.quizpro.biz.vo.ugs.Usuario;
import ingswii.quizpro.presentacion.comun.ISesion;
import ingswii.quizpro.presentacion.comun.impl.AbstractCrud;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.struts2.interceptor.validation.SkipValidation;


/**
 * CRUD de Usuarios.
 * @author Alejandro
 */
@Validation
public class ActionAdministrarUsuarios extends AbstractCrud {
    
    private Usuario usuario; // VO
    private String password2;
    private List<Grupo> listaGrupos; // lista de todos los grupos
    private List<Grupo> gruposSeleccionados; // lista de los grupos seleccionados
    
    private ICtrlUsuario ctrlUsuario;
    private ICtrlGrupo ctrlGrupo;
    
    @Override
    public void prepare() {
        try {
            super.prepare();
            setListaGrupos(getCtrlGrupo().obtenerGrupos());
        } catch (Exception ex) {
            Logger.getLogger(ActionAdministrarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @SkipValidation
    public String formulario() {
        
        if(getAccion().equals(ACCION_MODIFICAR)) {
            usuario = ctrlUsuario.buscarUsuarioPorId(getId());
        }
        else {
            usuario = null;
        }
        
        setListaGrupos(ctrlGrupo.obtenerGrupos());
        setGruposSeleccionados(null);
        return FORMULARIO;
    }
    
    @SkipValidation
    public String buscar() {
        
        setLista(ctrlUsuario.buscarUsuarios(usuario));
        setAccion("");
        return LISTA;
    }

    @SkipValidation
    public String ver() {
        usuario = ctrlUsuario.buscarUsuarioPorId(getId());
        setAccion(ACCION_VER);
        return FORMULARIO;
    }
    
    public void prepareDoCrear() {
        setAccion(ACCION_CREAR);
    }

    @Validations (
        requiredStrings={
            @RequiredStringValidator(fieldName="usuario.documento", message="Debe especificar un documento."),
            @RequiredStringValidator(fieldName="usuario.login", message="Debe especificar un login.")
        },
        stringLengthFields={
            @StringLengthFieldValidator(fieldName="usuario.documento", message="El documento debe tener entre 5 y 30 caracteres.", minLength="5", maxLength="30"),
            @StringLengthFieldValidator(fieldName="usuario.login", message="El login debe tener entre 5 y 30 caracteres.", minLength="5", maxLength="30"),
            @StringLengthFieldValidator(fieldName="usuario.password", message="La contraseña debe tener entre 5 y 30 caracteres.", minLength="5", maxLength="30")
        },
        regexFields={
            @RegexFieldValidator(fieldName="usuario.documento", message="El documento sólo puede contener números.", expression="[0-9]*"),
            @RegexFieldValidator(fieldName="usuario.login", message="El login sólo puede contener letras y/o números.", expression="[0-9A-Za-z]*")
        },
        expressions={
            @ExpressionValidator(message="Las contraseñas no coinciden.", expression="(usuario.getPassword().equals('') && password2.equals('')) || usuario.getPassword().equals(password2)")
        }
    )
    public String crear() {
        
        usuario.setNombre(usuario.getNombre().trim());
        usuario.setLogin(usuario.getLogin().trim());
        usuario.setDocumento(usuario.getDocumento().trim());
        
        usuario.setGrupos(gruposSeleccionados);
        
        if(!ctrlUsuario.crearUsuario(usuario)) {
            addActionError("No se puede crear el usuario. Verifique que no exista en el sistema.");
            return ERROR;
        }
        
        addActionMessage("Nuevo usuario creado.");
        usuario = null;
        return FORMULARIO;
    }
    
    public void prepareDoModificar() {
        setAccion(ACCION_MODIFICAR);
    }
    
    public String modificar() {
        
        usuario.setNombre(usuario.getNombre().trim());
        usuario.setLogin(usuario.getLogin().trim());
        usuario.setDocumento(usuario.getDocumento().trim());
        
        usuario.setGrupos(gruposSeleccionados);

        if(!ctrlUsuario.guardarUsuario(usuario)) {
            addActionError("No se puede actualizar el usuario.");
            return ERROR;
        }
        
        addActionMessage("Usuario modificado.");
        usuario = null;
        setAccion(ACCION_BUSCAR);
        return FORMULARIO;
    }

    @SkipValidation
    public String eliminar() {
        
        usuario = ctrlUsuario.buscarUsuarioPorId(getId());
        
        if(((Usuario) getSesion().getObjetoDeSesion(ISesion.USUARIO_AUTENTICADO)).getId().equals(usuario.getId())){
            addActionError("No se puede eliminar el usuario de la sesión actual.");
            return ERROR;
        }
        
        if(!ctrlUsuario.eliminarUsuarioPorId(usuario)) {
            addActionError("No se puede eliminar el usuario.");
            return ERROR;
        }
        
        usuario = null;
        addActionMessage("Usuario eliminado.");
        setAccion(ACCION_BUSCAR);
        return FORMULARIO;
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

    public List<Grupo> getListaGrupos() {
        return listaGrupos;
    }

    public void setListaGrupos(List<Grupo> listaGrupos) {
        this.listaGrupos = listaGrupos;
    }

    public ICtrlGrupo getCtrlGrupo() {
        return ctrlGrupo;
    }

    public void setCtrlGrupo(ICtrlGrupo ctrlGrupo) {
        this.ctrlGrupo = ctrlGrupo;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public List<Grupo> getGruposSeleccionados() {
        return gruposSeleccionados;
    }

    public void setGruposSeleccionados(List<Grupo> gruposSeleccionados) {
        this.gruposSeleccionados = gruposSeleccionados;
    }

}
