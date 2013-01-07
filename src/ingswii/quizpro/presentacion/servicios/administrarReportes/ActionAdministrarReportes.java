package ingswii.quizpro.presentacion.servicios.administrarReportes;

import com.opensymphony.xwork2.validator.annotations.ExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validation;
import com.opensymphony.xwork2.validator.annotations.Validations;
import ingswii.quizpro.biz.controlador.ICtrlReporte;
import ingswii.quizpro.biz.vo.reportes.Reporte;
import ingswii.quizpro.presentacion.comun.impl.AbstractCrud;
import org.apache.struts2.interceptor.validation.SkipValidation;


/**f
 * CRUD de Grupos.
 * @author Alejandro
 */
@Validation
public class ActionAdministrarReportes extends AbstractCrud {
    
    private Reporte reporte; // VO

    private ICtrlReporte ctrlReporte;
    
    @SkipValidation
    public String formulario() {
        
        if(getAccion().equals(ACCION_MODIFICAR)) {
            setReporte(getCtrlReporte().obtenerReportePorId(getId()));
        }
        else {
            setReporte(null);
        }
        
        return FORMULARIO;
    }
    
    @SkipValidation
    public String buscar() {
        
        setLista(getCtrlReporte().buscarReportes(getReporte()));
        setAccion("");
        return LISTA;
    }

    @SkipValidation
    public String ver() {
        setReporte(getCtrlReporte().obtenerReportePorId(getId()));
        setAccion(ACCION_VER);
        return FORMULARIO;
    }
    
    public void prepareDoCrear() {
        setAccion(ACCION_CREAR);
    }

    @Validations (
        requiredStrings={
            @RequiredStringValidator(fieldName="reporte.nombre", message="Debe especificar un nombre."),
            @RequiredStringValidator(fieldName="reporte.consulta", message="Debe especificar una consulta.")
        },
        stringLengthFields={
            @StringLengthFieldValidator(fieldName="reporte.nombre", message="El nombre debe tener entre 1 y 50 caracteres.", minLength="1", maxLength="50")
        },
        regexFields={
            @RegexFieldValidator(fieldName="reporte.nombre", message="El nombre sólo puede contener espacios, letras y/o números.", expression="[0-9A-Za-z áéíóúÁÉíÓÚñÑüÜ]*")
        },
        expressions={
            @ExpressionValidator(message="La consulta debe comenzar con SELECT.", expression="reporte.getConsulta().startsWith('SELECT')")
        }
    )
    public String crear() {
        
        reporte.setNombre(reporte.getNombre().trim());
        
        if(!ctrlReporte.guardarReporte(reporte)) {
            addActionError("No se puede crear el reporte.");
            return ERROR;
        }
        
        addActionMessage("Nuevo reporte creado.");
        setReporte(null);
        return FORMULARIO;
    }
    
    public void prepareDoModificar() {
        setAccion(ACCION_MODIFICAR);
    }
    
    public String modificar() {
        
        reporte.setNombre(reporte.getNombre().trim());
        
        if(!ctrlReporte.guardarReporte(reporte)) {
            addActionError("No se puede actualizar el reporte.");
            return ERROR;
        }
        
        addActionMessage("Reporte modificado.");
        setReporte(null);
        setAccion(ACCION_BUSCAR);
        return FORMULARIO;
    }

    @SkipValidation
    public String eliminar() {
        
        reporte = ctrlReporte.obtenerReportePorId(getId());
        
        if(!ctrlReporte.eliminarReportePorId(reporte)) {
            addActionError("No se puede eliminar el reporte.");
            return ERROR;
        }
        
        reporte = null;
        addActionMessage("Reporte eliminado.");
        setAccion(ACCION_BUSCAR);
        return FORMULARIO;
    }

    public Reporte getReporte() {
        return reporte;
    }

    public void setReporte(Reporte reporte) {
        this.reporte = reporte;
    }

    public ICtrlReporte getCtrlReporte() {
        return ctrlReporte;
    }

    public void setCtrlReporte(ICtrlReporte ctrlReporte) {
        this.ctrlReporte = ctrlReporte;
    }

}
