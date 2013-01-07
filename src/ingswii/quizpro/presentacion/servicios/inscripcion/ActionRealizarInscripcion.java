package ingswii.quizpro.presentacion.servicios.inscripcion;

import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.ExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validation;
import com.opensymphony.xwork2.validator.annotations.Validations;
import ingswii.quizpro.biz.controlador.ICtrlConvocatoria;
import ingswii.quizpro.biz.controlador.ICtrlPostulante;
import ingswii.quizpro.biz.vo.ram.Convocatoria;
import ingswii.quizpro.biz.vo.ram.Postulante;
import ingswii.quizpro.presentacion.comun.ISesion;
import ingswii.quizpro.presentacion.comun.impl.AbstractAction;
import java.util.List;

/**
 * Servicio para realizar la inscripcion de un nuevo postulante
 * @author Jorge
 */
@Validation
public class ActionRealizarInscripcion extends AbstractAction{
   
    private Postulante postulanteValidacion;
    private Postulante postulante;
    private Convocatoria convocatoria;
    private String password2;
    private ICtrlPostulante ctrlPostulante;
    private ICtrlConvocatoria ctrlConvocatoria;
    
    public String inicio() {
        return OK;
    }
    @Validations (
        requiredStrings={
            @RequiredStringValidator(fieldName="postulante.nombre", message="Debe especificar un nombre."),
            @RequiredStringValidator(fieldName="postulante.apellido", message="Debe especificar un apellido."),
            @RequiredStringValidator(fieldName="postulante.documento", message="Debe especificar un documento."),
            @RequiredStringValidator(fieldName="postulante.telefono", message="Debe especificar un telefono."),
            @RequiredStringValidator(fieldName="postulante.email", message="Debe especificar un e-mail."),
            @RequiredStringValidator(fieldName="postulante.password", message="Debe especificar una contraseña."),
            @RequiredStringValidator(fieldName="password2", message="Debe confirmar la contraseña.")
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
            @ExpressionValidator(message="Las contraseñas no coinciden.", expression="postulante.getPassword().equals(password2)"),
            @ExpressionValidator(message="Ya se encuentra registrado un postulante con este documento", 
            expression="ctrlPostulante.buscarPostulantePorDocumento(postulante.getDocumento().trim())==null")
        }
    )
    public String realizarInscripcion(){
        postulante.setNombre(postulante.getNombre().trim());
        postulante.setApellido(postulante.getApellido().trim());
        postulante.setEmail(postulante.getEmail().trim());
        postulante.setDocumento(postulante.getDocumento().trim());
        postulante.setTelefono(postulante.getTelefono().trim());
        
        setConvocatoria((Convocatoria)getSesion().getObjetoDeSesion(ISesion.CONVOCATORIA));
        convocatoria.getPostulantes().add(postulante);
        getCtrlConvocatoria().guardarConvocatoria(convocatoria);
        addActionMessage("La inscripción a la convocatoria se ha realizado correctamente.");
        addActionMessage("Convocatoria:  "+getConvocatoria().getNombre());
        addActionMessage("Nombre del Postulante: "+postulante.getNombre()+" "+postulante.getApellido());
        addActionMessage("Documento:  "+postulante.getDocumento());
        addActionMessage("Teléfono:  "+postulante.getTelefono());
        addActionMessage("E-mail:  "+postulante.getEmail());
        postulante = null;
        return MENSAJE;
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

    public Postulante getPostulanteValidacion() {
        return postulanteValidacion;
    }

    public void setPostulanteValidacion(Postulante postulanteValidacion) {
        this.postulanteValidacion = postulanteValidacion;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public Convocatoria getConvocatoria() {
        return convocatoria;
    }

    public void setConvocatoria(Convocatoria convocatoria) {
        this.convocatoria = convocatoria;
    }

    public ICtrlConvocatoria getCtrlConvocatoria() {
        return ctrlConvocatoria;
    }

    public void setCtrlConvocatoria(ICtrlConvocatoria ctrlConvocatoria) {
        this.ctrlConvocatoria = ctrlConvocatoria;
    }

}

