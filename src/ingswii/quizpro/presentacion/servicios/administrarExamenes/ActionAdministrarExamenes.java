package ingswii.quizpro.presentacion.servicios.administrarExamenes;

import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validation;
import com.opensymphony.xwork2.validator.annotations.Validations;
import ingswii.quizpro.biz.controlador.ICtrlExamen;
import ingswii.quizpro.biz.controlador.ICtrlIp;
import ingswii.quizpro.biz.vo.ram.Examen;
import ingswii.quizpro.biz.vo.ram.Ip;
import ingswii.quizpro.presentacion.comun.impl.AbstractCrud;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.struts2.interceptor.validation.SkipValidation;


/**
 * CRUD de Examenes.
 * @author Alejandro
 */
@Validation
public class ActionAdministrarExamenes extends AbstractCrud {
    
    private Examen examen; // VO
    private List<Ip> listaIps; // lista de todos los ips
    private List<Ip> ipsSeleccionadas; // lista de ips seleccionadas
    
    private ICtrlExamen ctrlExamen;
    private ICtrlIp ctrlIp;
    
    @Override
    public void prepare() {
        try {
            super.prepare();
            setListaIps(getCtrlIp().obtenerIps());
        } catch (Exception ex) {
            Logger.getLogger(ActionAdministrarExamenes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @SkipValidation
    public String formulario() {
        
        if(getAccion().equals(ACCION_MODIFICAR)) {
            setExamen(getCtrlExamen().obtenerExamenPorId(getId()));
        }
        else {
            setExamen(null);
        }
        
        setListaIps(getCtrlIp().obtenerIps());
        setIpsSeleccionadas(null);
        return FORMULARIO;
    }
    
    @SkipValidation
    public String buscar() {
        
        setLista(getCtrlExamen().buscarExamenes(getExamen()));
        setAccion("");
        return LISTA;
    }

    @SkipValidation
    public String ver() {
        setExamen(ctrlExamen.obtenerExamenPorId(getId()));
        setAccion(ACCION_VER);
        return FORMULARIO;
    }

    public void prepareDoCrear() {
        setAccion(ACCION_CREAR);
    } 
    
    @Validations (
        requiredStrings={
            @RequiredStringValidator(fieldName="examen.nombre", message="Debe especificar un nombre.")
        },
        stringLengthFields={
            @StringLengthFieldValidator(fieldName="examen.nombre", message="El nombre debe tener entre 1 y 30 caracteres.", minLength="1", maxLength="30")
        },
        regexFields={
            @RegexFieldValidator(fieldName="examen.nombre", message="El nombre sólo puede contener espacios, letras y/o números.", expression="[0-9A-Za-z áéíóúÁÉíÓÚñÑüÜ]*")
        }
    )
    public String crear() {
        
        examen.setNombre(examen.getNombre().trim());
                
        examen.setIps(ipsSeleccionadas);
        
        if(!ctrlExamen.guardarExamen(examen)) {
            addActionError("No se puede crear el examen.");
            return ERROR;
        }
        
        addActionMessage("Nuevo examen creado.");
        setExamen(null);
        return FORMULARIO;
    }
    
    public void prepareDoModificar() {
        setAccion(ACCION_MODIFICAR);
    }
    
    public String modificar() {
        
        examen.setNombre(examen.getNombre().trim());
        
        examen.setIps(ipsSeleccionadas);

        if(!ctrlExamen.guardarExamen(examen)) {
            addActionError("No se puede actualizar el examen.");
            return ERROR;
        }
        
        addActionMessage("Examen modificado.");
        setExamen(null);
        setAccion(ACCION_BUSCAR);
        return FORMULARIO;
    }

    @SkipValidation
    public String eliminar() {
        
        examen = ctrlExamen.obtenerExamenPorId(getId());
        
        if(!ctrlExamen.eliminarExamenPorId(examen)) {
            addActionError("No se puede eliminar el examen.");
            return ERROR;
        }
        
        examen = null;
        addActionMessage("Examen eliminado.");
        setAccion(ACCION_BUSCAR);
        return FORMULARIO;
    }

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }

    public ICtrlExamen getCtrlExamen() {
        return ctrlExamen;
    }

    public void setCtrlExamen(ICtrlExamen ctrlExamen) {
        this.ctrlExamen = ctrlExamen;
    }

    public List<Ip> getListaIps() {
        return listaIps;
    }

    public void setListaIps(List<Ip> listaIps) {
        this.listaIps = listaIps;
    }

    public ICtrlIp getCtrlIp() {
        return ctrlIp;
    }

    public void setCtrlIp(ICtrlIp ctrlIp) {
        this.ctrlIp = ctrlIp;
    }

    public List<Ip> getIpsSeleccionadas() {
        return ipsSeleccionadas;
    }

    public void setIpsSeleccionadas(List<Ip> ipsSeleccionadas) {
        this.ipsSeleccionadas = ipsSeleccionadas;
    }

}
