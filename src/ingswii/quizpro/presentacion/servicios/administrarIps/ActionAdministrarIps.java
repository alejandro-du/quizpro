package ingswii.quizpro.presentacion.servicios.administrarIps;

import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validation;
import com.opensymphony.xwork2.validator.annotations.Validations;
import ingswii.quizpro.biz.controlador.ICtrlIp;
import ingswii.quizpro.biz.vo.ram.Ip;
import ingswii.quizpro.presentacion.comun.impl.AbstractCrud;
import org.apache.struts2.interceptor.validation.SkipValidation;


/**f
 * CRUD de Ips.
 * @author Alejandro
 */
@Validation
public class ActionAdministrarIps extends AbstractCrud {
    
    private Ip ip; // VO

    private ICtrlIp ctrlIp;
    
    @SkipValidation
    public String formulario() {
        
        if(getAccion().equals(ACCION_MODIFICAR)) {
            setIp(getCtrlIp().obtenerIpPorId(getId()));
        }
        else {
            setIp(null);
        }
        
        return FORMULARIO;
    }
    
    @SkipValidation
    public String buscar() {
        
        setLista(getCtrlIp().buscarIps(getIp()));
        setAccion("");
        return LISTA;
    }

    @SkipValidation
    public String ver() {
        setIp(getCtrlIp().obtenerIpPorId(getId()));
        setAccion(ACCION_VER);
        return FORMULARIO;
    }

    public void prepareDoCrear() {
        setAccion(ACCION_CREAR);
    }

    
    @Validations (
        requiredStrings={
            @RequiredStringValidator(fieldName="ip.ip", message="Debe especificar una dirección IP."),
            @RequiredStringValidator(fieldName="ip.alias", message="Debe especificar un alias.")
        },
        stringLengthFields={
            @StringLengthFieldValidator(fieldName="ip.ip", message="La IP debe tener entre 1 y 30 caracteres.", minLength="1", maxLength="30"),
            @StringLengthFieldValidator(fieldName="ip.alias", message="El alias debe tener entre 1 y 30 caracteres.", minLength="1", maxLength="30")
        },
        regexFields={
            @RegexFieldValidator(fieldName="ip.alias", message="El alias sólo puede contener espacios, letras y/o números.", expression="[0-9A-Za-z ]*")
        }
    )
    public String crear() {
        
        ip.setAlias(ip.getAlias().trim());
        ip.setIp(ip.getIp().trim());
        
        if(!ctrlIp.guardarIp(ip)) {
            addActionError("No se puede crear la IP.");
            return ERROR;
        }
        
        addActionMessage("Nueva IP creada.");
        setIp(null);
        return FORMULARIO;
    }
    
    public void prepareDoModificar() {
        setAccion(ACCION_MODIFICAR);
    }
    
    public String modificar() {
        
        ip.setAlias(ip.getAlias().trim());
        ip.setIp(ip.getIp().trim());
        
        if(!ctrlIp.guardarIp(ip)) {
            addActionError("No se puede actualizar la IP.");
            return ERROR;
        }
        
        addActionMessage("IP modificada.");
        setIp(null);
        setAccion(ACCION_BUSCAR);
        return FORMULARIO;
    }

    @SkipValidation
    public String eliminar() {
        
        ip = ctrlIp.obtenerIpPorId(getId());
        
        if(!ctrlIp.eliminarIpPorId(ip)) {
            addActionError("No se puede eliminar la IP.");
            return ERROR;
        }
        
        ip = null;
        addActionMessage("IP eliminada.");
        setAccion(ACCION_BUSCAR);
        return FORMULARIO;
    }

    public Ip getIp() {
        return ip;
    }

    public void setIp(Ip ip) {
        this.ip = ip;
    }

    public ICtrlIp getCtrlIp() {
        return ctrlIp;
    }

    public void setCtrlIp(ICtrlIp ctrlIp) {
        this.ctrlIp = ctrlIp;
    }

}
