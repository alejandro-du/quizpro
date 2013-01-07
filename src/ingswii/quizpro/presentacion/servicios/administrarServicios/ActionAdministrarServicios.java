package ingswii.quizpro.presentacion.servicios.administrarServicios;

import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validation;
import com.opensymphony.xwork2.validator.annotations.Validations;
import ingswii.quizpro.biz.controlador.ICtrlMenu;
import ingswii.quizpro.biz.controlador.ICtrlServicio;
import ingswii.quizpro.biz.vo.ugs.Menu;
import ingswii.quizpro.biz.vo.ugs.Servicio;
import ingswii.quizpro.presentacion.comun.impl.AbstractCrud;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.struts2.interceptor.validation.SkipValidation;


/**
 * CRUD de Grupos.
 * @author Alejandro
 */
@Validation
public class ActionAdministrarServicios extends AbstractCrud {
    
    private Servicio servicio; // VO
    private List<Menu> listaMenus; // lista de todos los menus
    
    private ICtrlServicio ctrlServicio;
    private ICtrlMenu ctrlMenu;
    
    @Override
    public void prepare() {
        try {
            super.prepare();
            setListaMenus(getCtrlMenu().obtenerMenus());
        } catch (Exception ex) {
            Logger.getLogger(ActionAdministrarServicios.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @SkipValidation
    public String formulario() {
        
        if(getAccion().equals(ACCION_MODIFICAR)) {
            setServicio(getCtrlServicio().obtenerServicioPorId(getId()));
        }
        else {
            setServicio(null);
        }
        
        return FORMULARIO;
    }
    
    @SkipValidation
    public String buscar() {
        
        setLista(getCtrlServicio().buscarServicios(getServicio()));
        setAccion("");
        return LISTA;
    }
    
    @SkipValidation
    public String ver() {
        setServicio(getCtrlServicio().obtenerServicioPorId(getId()));
        setAccion(ACCION_VER);
        return FORMULARIO;
    }
    
    public void prepareDoCrear() {
        setAccion(ACCION_CREAR);
    }

    @Validations (
        requiredStrings={
            @RequiredStringValidator(fieldName="servicio.nombre", message="Debe especificar un nombre."),
            @RequiredStringValidator(fieldName="servicio.url", message="Debe especificar una url de inicio.")
        },
        stringLengthFields={
            @StringLengthFieldValidator(fieldName="servicio.nombre", message="El nombre debe tener entre 3 y 50 caracteres.", minLength="3", maxLength="50"),
            @StringLengthFieldValidator(fieldName="servicio.url", message="La url de inicio debe tener entre 1 y 200 caracteres.", minLength="1", maxLength="200")
        },
        regexFields={
            @RegexFieldValidator(fieldName="servico.nombre", message="El nombre sólo puede contener espacios, etras y/o números.", expression="[0-9A-Za-z áéíóúÁÉíÓÚñÑüÜ]*")
        }
    )
    public String crear() {
        
        servicio.setNombre(servicio.getNombre().trim());
        servicio.setUrl(servicio.getUrl().trim());
        
        if(!ctrlServicio.guardarServicio(servicio)) {
            addActionError("No se puede crear el servicio.");
            return ERROR;
        }
        
        addActionMessage("Nuevo servicio creado.");
        setServicio(null);
        return FORMULARIO;
    }
    
    public void prepareDoModificar() {
        setAccion(ACCION_MODIFICAR);
    }
    
    public String modificar() {
        
        servicio.setNombre(servicio.getNombre().trim());
        servicio.setUrl(servicio.getUrl().trim());
        
        if(!ctrlServicio.guardarServicio(servicio)) {
            addActionError("No se puede actualizar el servicio.");
            return ERROR;
        }
        
        addActionMessage("Servicio modificado.");
        setServicio(null);
        setAccion(ACCION_BUSCAR);
        return FORMULARIO;
    }

    @SkipValidation
    public String eliminar() {
        
        servicio = ctrlServicio.obtenerServicioPorId(getId());
        
        if(!ctrlServicio.eliminarServicioPorId(servicio)) {
            addActionError("No se puede eliminar el servicio.");
            return ERROR;
        }
        
        servicio = null;
        addActionMessage("Servicio eliminado.");
        setAccion(ACCION_BUSCAR);
        return FORMULARIO;
    }

    public ICtrlServicio getCtrlServicio() {
        return ctrlServicio;
    }

    public void setCtrlServicio(ICtrlServicio ctrlServicio) {
        this.ctrlServicio = ctrlServicio;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public List<Menu> getListaMenus() {
        return listaMenus;
    }

    public void setListaMenus(List<Menu> listaMenus) {
        this.listaMenus = listaMenus;
    }

    public ICtrlMenu getCtrlMenu() {
        return ctrlMenu;
    }

    public void setCtrlMenu(ICtrlMenu ctrlMenu) {
        this.ctrlMenu = ctrlMenu;
    }

}
