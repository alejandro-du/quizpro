package ingswii.quizpro.presentacion.servicios.administrarPostulantes;

import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.ExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validation;
import com.opensymphony.xwork2.validator.annotations.Validations;
import ingswii.quizpro.biz.controlador.ICtrlPostulante;
import ingswii.quizpro.biz.vo.ram.Postulante;
import ingswii.quizpro.presentacion.comun.impl.AbstractCrud;
import org.apache.struts2.interceptor.validation.SkipValidation;


/**f
 * CRUD de Grupos.
 * @author Alejandro
 */
@Validation
public class ActionAdministrarPostulantes extends AbstractCrud {
    
    private Postulante postulante; // VO
    private String password2;

    private ICtrlPostulante ctrlPostulante;
    
    @SkipValidation
    public String formulario() {
        
        if(getAccion().equals(ACCION_MODIFICAR)) {
            setPostulante(getCtrlPostulante().obtenerPostulantePorId(getId()));
        }
        else {
            setPostulante(null);
        }
        
        return FORMULARIO;
    }
    
    @SkipValidation
    public String buscar() {
        
        setLista(getCtrlPostulante().buscarPostulantes(getPostulante()));
        setAccion("");
        return LISTA;
    }

    @SkipValidation
    public String ver() {
        setPostulante(getCtrlPostulante().obtenerPostulantePorId(getId()));
        setAccion(ACCION_VER);
        return FORMULARIO;
    }

    public void prepareDoCrear() {
        setAccion(ACCION_CREAR);
    }

    
    @Validations (
        requiredStrings={
            @RequiredStringValidator(fieldName="postulante.nombre", message="Debe especificar un nombre."),
            @RequiredStringValidator(fieldName="postulante.apellido", message="Debe especificar un apellido."),
            @RequiredStringValidator(fieldName="postulante.documento", message="Debe especificar un documento."),
            @RequiredStringValidator(fieldName="postulante.telefono", message="Debe especificar un telefono."),
            @RequiredStringValidator(fieldName="postulante.email", message="Debe especificar un e-mail.")
        },
        stringLengthFields={
            @StringLengthFieldValidator(fieldName="postulante.nombre", message="El nombre debe tener entre 1 y 30 caracteres.", minLength="1", maxLength="30"),
            @StringLengthFieldValidator(fieldName="postulante.apellido", message="El apellido debe tener entre 1 y 30 caracteres.", minLength="1", maxLength="30"),
            @StringLengthFieldValidator(fieldName="postulante.documento", message="El documento debe tener entre 1 y 30 caracteres.", minLength="1", maxLength="30"),
            @StringLengthFieldValidator(fieldName="postulante.telefono", message="El telefono debe tener entre 1 y 30 caracteres.", minLength="1", maxLength="30"),
            @StringLengthFieldValidator(fieldName="postulante.email", message="El emal no puede tener más 30 caracteres.", minLength="1", maxLength="30"),
            @StringLengthFieldValidator(fieldName="postulante.password", message="La contraseña debe tener entre 5 y 30 caracteres.", minLength="5", maxLength="30")
        },
        emails={@EmailValidator(fieldName="postulante.email", message="Debe ingresar un e-mail valido")},
        regexFields={
            @RegexFieldValidator(fieldName="postulante.documento", message="El documento sólo puede contener números.", expression="[0-9]*"),
            @RegexFieldValidator(fieldName="postulante.telefono", message="El telefono sólo puede contener números.", expression="[0-9]*"),
            @RegexFieldValidator(fieldName="postulante.nombre", message="El nombre sólo puede contener letras.", expression="[A-Za-záéíóúÁÉíÓÚñÑüÜ. ]*"),
            @RegexFieldValidator(fieldName="postulante.apellido", message="El apellido sólo puede contener letras.", expression="[A-Za-záéíóúÁÉíÓÚñÑüÜ. ]*")
        },
        expressions={
            @ExpressionValidator(message="Las contraseñas no coinciden.", expression="(postulante.getPassword().equals('') && password2.equals('')) || postulante.getPassword().equals(password2)")
        }
    )
    public String crear() {
        postulante.setNombre(postulante.getNombre().trim());
        postulante.setApellido(postulante.getApellido().trim());
        postulante.setEmail(postulante.getEmail().trim());
        postulante.setDocumento(postulante.getDocumento().trim());
        postulante.setTelefono(postulante.getTelefono().trim());       
        
        if(!ctrlPostulante.crearPostulante(postulante)) {
            addActionError("No se puede crear el postulante. Verifique que el postulante no exista en el sistema.");
            return ERROR;
        }
        
        addActionMessage("Nuevo postulante creado.");
        setPostulante(null);
        return FORMULARIO;
    }
    
    public void prepareDoModificar() {
        setAccion(ACCION_MODIFICAR);
    }
    
    public String modificar() {
        
        postulante.setNombre(postulante.getNombre().trim());
        postulante.setApellido(postulante.getApellido().trim());
        postulante.setEmail(postulante.getEmail().trim());
        postulante.setDocumento(postulante.getDocumento().trim());
        postulante.setTelefono(postulante.getTelefono().trim());
        
        if(!ctrlPostulante.guardarPostulante(postulante)) {
            addActionError("No se puede actualizar el postulante.");
            return ERROR;
        }
        
        addActionMessage("Postulante modificado.");
        setPostulante(null);
        setAccion(ACCION_BUSCAR);
        return FORMULARIO;
    }

    @SkipValidation
    public String eliminar() {
        
        postulante = ctrlPostulante.obtenerPostulantePorId(getId());
        
        if(!ctrlPostulante.eliminarPostulantePorId(postulante)) {
            addActionError("No se puede eliminar el postulante.");
            return ERROR;
        }
        
        postulante = null;
        addActionMessage("Postulante eliminado.");
        setAccion(ACCION_BUSCAR);
        return FORMULARIO;
    }

    public Postulante getPostulante() {
        return postulante;
    }

    public void setPostulante(Postulante postulante) {
        this.postulante = postulante;
    }

    public ICtrlPostulante getCtrlPostulante() {
        return ctrlPostulante;
    }

    public void setCtrlPostulante(ICtrlPostulante ctrlPostulante) {
        this.ctrlPostulante = ctrlPostulante;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

}
