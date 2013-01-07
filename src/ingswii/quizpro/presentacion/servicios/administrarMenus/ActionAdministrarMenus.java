package ingswii.quizpro.presentacion.servicios.administrarMenus;

import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validation;
import com.opensymphony.xwork2.validator.annotations.Validations;
import ingswii.quizpro.biz.controlador.ICtrlMenu;
import ingswii.quizpro.biz.vo.ugs.Menu;
import ingswii.quizpro.presentacion.comun.impl.AbstractCrud;
import org.apache.struts2.interceptor.validation.SkipValidation;


/**f
 * CRUD de Grupos.
 * @author Alejandro
 */
@Validation
public class ActionAdministrarMenus extends AbstractCrud {
    
    private Menu menu; // VO

    private ICtrlMenu ctrlMenu;
    
    @SkipValidation
    public String formulario() {
        
        if(getAccion().equals(ACCION_MODIFICAR)) {
            setMenu(getCtrlMenu().obtenerMenuPorId(getId()));
        }
        else {
            setMenu(null);
        }
        
        return FORMULARIO;
    }
    
    @SkipValidation
    public String buscar() {
        
        setLista(getCtrlMenu().buscarMenus(getMenu()));
        setAccion("");
        return LISTA;
    }

    @SkipValidation
    public String ver() {
        setMenu(getCtrlMenu().obtenerMenuPorId(getId()));
        setAccion(ACCION_VER);
        return FORMULARIO;
    }

    public void prepareDoCrear() {
        setAccion(ACCION_CREAR);
    }
        
    @Validations (
        requiredStrings={
            @RequiredStringValidator(fieldName="menu.nombre", message="Debe especificar un nombre.")
        },
        stringLengthFields={
            @StringLengthFieldValidator(fieldName="menu.nombre", message="El nombre debe tener entre 1 y 30 caracteres.", minLength="1", maxLength="30")
        },
        regexFields={
            @RegexFieldValidator(fieldName="menu.nombre", message="El nombre sólo puede contener espacios, letras y/o números.", expression="[0-9A-Za-z áéíóúÁÉíÓÚñÑüÜ]*")
        }
    )
    public String crear() {

        menu.setNombre(menu.getNombre().trim());
        
        if(!ctrlMenu.guardarMenu(menu)) {
            addActionError("No se puede crear el menú.");
            return ERROR;
        }
        
        addActionMessage("Nuevo menú creado.");
        setMenu(null);
        return FORMULARIO;
    }
    
    public void prepareDoModificar() {
        setAccion(ACCION_MODIFICAR);
    }
        
    public String modificar() {
        
        menu.setNombre(menu.getNombre().trim());
        
        if(!ctrlMenu.guardarMenu(menu)) {
            addActionError("No se puede actualizar el menú.");
            return ERROR;
        }
        
        addActionMessage("Menú modificado.");
        setMenu(null);
        setAccion(ACCION_BUSCAR);
        return FORMULARIO;
    }

    @SkipValidation
    public String eliminar() {
        
        menu = ctrlMenu.obtenerMenuPorId(getId());
        
        if(!ctrlMenu.eliminarMenuPorId(menu)) {
            addActionError("No se puede eliminar el menú.");
            return ERROR;
        }
        
        menu = null;
        addActionMessage("Menú eliminado.");
        setAccion(ACCION_BUSCAR);
        return FORMULARIO;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public ICtrlMenu getCtrlMenu() {
        return ctrlMenu;
    }

    public void setCtrlMenu(ICtrlMenu ctrlMenu) {
        this.ctrlMenu = ctrlMenu;
    }

}
