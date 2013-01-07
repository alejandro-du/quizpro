package ingswii.quizpro.presentacion.servicios.administrarTemas;

import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validation;
import com.opensymphony.xwork2.validator.annotations.Validations;
import ingswii.quizpro.biz.controlador.ICtrlTema;
import ingswii.quizpro.biz.vo.ram.Tema;
import ingswii.quizpro.presentacion.comun.impl.AbstractCrud;
import org.apache.struts2.interceptor.validation.SkipValidation;


/**f
 * CRUD de Temas.
 * @author Alejandro
 */
@Validation
public class ActionAdministrarTemas extends AbstractCrud {
    
    private Tema tema; // VO

    private ICtrlTema ctrlTema;
    
    @SkipValidation
    public String formulario() {
        
        if(getAccion().equals(ACCION_MODIFICAR)) {
            setTema(getCtrlTema().obtenerTemaPorId(getId()));
        }
        else {
            setTema(null);
        }
        
        return FORMULARIO;
    }
    
    @SkipValidation
    public String buscar() {
        
        setLista(getCtrlTema().buscarTemas(getTema()));
        setAccion("");
        return LISTA;
    }

    @SkipValidation
    public String ver() {
        setTema(getCtrlTema().obtenerTemaPorId(getId()));
        setAccion(ACCION_VER);
        return FORMULARIO;
    }

    public void prepareDoCrear() {
        setAccion(ACCION_CREAR);
    }
    
    @Validations (
        requiredStrings={
            @RequiredStringValidator(fieldName="tema.nombre", message="Debe especificar un nombre.")
        },
        requiredFields= {
            @RequiredFieldValidator(fieldName="tema.ponderacion", message="Debe seleccionar una ponderación.")
        },
        stringLengthFields={
            @StringLengthFieldValidator(fieldName="tema.nombre", message="El nombre debe tener entre 1 y 30 caracteres.", minLength="1", maxLength="30")
        },
        regexFields={
            @RegexFieldValidator(fieldName="tema.nombre", message="El nombre sólo puede contener espacios, letras y/o números.", expression="[0-9A-Za-z áéíóúÁÉíÓÚñÑüÜ]*")
        }
    )
    public String crear() {
        
        tema.setNombre(tema.getNombre().trim());
        
        if(!ctrlTema.guardarTema(tema)) {
            addActionError("No se puede crear el tema.");
            return ERROR;
        }
        
        addActionMessage("Nuevo tema creado.");
        setTema(null);
        return FORMULARIO;
    }
    
    public void prepareDoModificar() {
        setAccion(ACCION_MODIFICAR);
    }
    
    public String modificar() {
        
        tema.setNombre(tema.getNombre().trim());
        
        if(!ctrlTema.guardarTema(tema)) {
            addActionError("No se puede actualizar el tema.");
            return ERROR;
        }
        
        addActionMessage("Tema modificado.");
        setTema(null);
        setAccion(ACCION_BUSCAR);
        return FORMULARIO;
    }

    @SkipValidation
    public String eliminar() {
        
        tema = ctrlTema.obtenerTemaPorId(getId());
        
        if(!ctrlTema.eliminarTemaPorId(tema)) {
            addActionError("No se puede eliminar el tema.");
            return ERROR;
        }
        
        tema = null;
        addActionMessage("Tema eliminado.");
        setAccion(ACCION_BUSCAR);
        return FORMULARIO;
    }

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }

    public ICtrlTema getCtrlTema() {
        return ctrlTema;
    }

    public void setCtrlTema(ICtrlTema ctrlTema) {
        this.ctrlTema = ctrlTema;
    }

}
