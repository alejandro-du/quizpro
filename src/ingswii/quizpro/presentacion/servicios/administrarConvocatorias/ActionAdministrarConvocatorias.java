package ingswii.quizpro.presentacion.servicios.administrarConvocatorias;

import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validation;
import com.opensymphony.xwork2.validator.annotations.Validations;
import ingswii.quizpro.biz.controlador.ICtrlConvocatoria;
import ingswii.quizpro.biz.controlador.ICtrlExamen;
import ingswii.quizpro.biz.vo.ram.Convocatoria;
import ingswii.quizpro.presentacion.comun.ISesion;
import ingswii.quizpro.presentacion.comun.impl.AbstractCrud;
import org.apache.struts2.interceptor.validation.SkipValidation;


/**f
 * CRUD de Grupos.
 * @author Alejandro
 */
@Validation
public class ActionAdministrarConvocatorias extends AbstractCrud {
    
    private Convocatoria convocatoria; // VO

    private ICtrlConvocatoria ctrlConvocatoria;
    
    @SkipValidation
    public String formulario() {
        
        if(getAccion().equals(ACCION_MODIFICAR)) {
            setConvocatoria(getCtrlConvocatoria().obtenerConvocatoriaPorId(getId()));
        }
        else {
            setConvocatoria(null);
        }
        
        return FORMULARIO;
    }
    
    @SkipValidation
    public String buscar() {
        
        setLista(getCtrlConvocatoria().buscarConvocatorias(getConvocatoria()));
        setAccion("");
        return LISTA;
    }

    @SkipValidation
    public String ver() {
        setConvocatoria(getCtrlConvocatoria().obtenerConvocatoriaPorId(getId()));
        setAccion(ACCION_VER);
        return FORMULARIO;
    }

    public void prepareDoCrear() {
        setAccion(ACCION_CREAR);
    }
    
    @Validations (
        requiredStrings={
            @RequiredStringValidator(fieldName="convocatoria.nombre", message="Debe especificar un nombre.")
        },
        requiredFields = {
            @RequiredFieldValidator(fieldName="convocatoria.vacantes", message="Debe especificar una cantidad de vacantes.")
        },
        stringLengthFields={
            @StringLengthFieldValidator(fieldName="convocatoria.nombre", message="El nombre debe tener entre 1 y 30 caracteres.", minLength="1", maxLength="30")
        },
        regexFields={
            @RegexFieldValidator(fieldName="convocatoria.nombre", message="El nombre sólo puede contener espacios, letras y/o números.", expression="[0-9A-Za-z áéíóúÁÉíÓÚñÑüÜ]*"),
            @RegexFieldValidator(fieldName="convocatoria.vacantes", message="El campo vacantes, sólo puede contener números.", expression="[0-9]*")
        }
    )
    public String crear() {
        convocatoria.setNombre(convocatoria.getNombre().trim());
        convocatoria.setExamen(null);
        
        if(!ctrlConvocatoria.guardarConvocatoria(convocatoria)) {
            addActionError("No se puede crear la convocatoria.");
            return ERROR;
        }
        
        addActionMessage("Nueva convocatoria creada.");
        setConvocatoria(null);
        return FORMULARIO;
    }
    
    public void prepareDoModificar() {
        setAccion(ACCION_MODIFICAR);
    }
    
    public String modificar() {
        
        convocatoria.setNombre(convocatoria.getNombre().trim());
        convocatoria.setPostulantes(ctrlConvocatoria.obtenerConvocatoriaPorId(convocatoria).getPostulantes());
        convocatoria.setExamen(ctrlConvocatoria.obtenerConvocatoriaPorId(convocatoria).getExamen());
        
        if(!ctrlConvocatoria.guardarConvocatoria(convocatoria)) {
            addActionError("No se puede actualizar la convocatoria.");
            return ERROR;
        }
        
        addActionMessage("Convocatoria modificada.");
        setConvocatoria(null);
        setAccion(ACCION_BUSCAR);
        return FORMULARIO;
    }

    @SkipValidation
    public String eliminar() {
        
        convocatoria = ctrlConvocatoria.obtenerConvocatoriaPorId(getId());
        
        if(((Convocatoria) getSesion().getObjetoDeSesion(ISesion.CONVOCATORIA)).getId().equals(getConvocatoria().getId())){
            addActionError("No se puede eliminar la convocatoria de la sesión actual.");
            return ERROR;
        }
               
        if(!ctrlConvocatoria.eliminarConvocatoriaPorId(convocatoria)) {
            addActionError("No se puede eliminar la convocatoria.");
            return ERROR;
        }
        
        convocatoria = null;
        addActionMessage("Convocatoria eliminada.");
        setAccion(ACCION_BUSCAR);
        return FORMULARIO;
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
