package ingswii.quizpro.presentacion.servicios.administrarGrupos;

import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validation;
import com.opensymphony.xwork2.validator.annotations.Validations;
import ingswii.quizpro.biz.controlador.ICtrlGrupo;
import ingswii.quizpro.biz.controlador.ICtrlServicio;
import ingswii.quizpro.biz.vo.ugs.Grupo;
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
public class ActionAdministrarGrupos extends AbstractCrud {
    
    private Grupo grupo; // VO
    private List<Servicio> listaServicios; // lista de todos los servicios
    private List<Servicio> serviciosSeleccionados; // lista de servicios seleccionados
    
    private ICtrlGrupo ctrlGrupo;
    private ICtrlServicio ctrlServicio;
    
    @Override
    public void prepare() {
        try {
            super.prepare();
            setListaServicios(getCtrlServicio().obtenerServicios());
        } catch (Exception ex) {
            Logger.getLogger(ActionAdministrarGrupos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
        
    @SkipValidation
    public String formulario() {
        
        if(getAccion().equals(ACCION_MODIFICAR)) {
            setGrupo(getCtrlGrupo().obtenerGrupoPorId(getId()));
        }
        else {
            setGrupo(null);
        }
        
        setListaServicios(getCtrlServicio().obtenerServicios());
        setServiciosSeleccionados(null);
        return FORMULARIO;
    }
    
    @SkipValidation
    public String buscar() {
        
        setLista(getCtrlGrupo().buscarGrupos(getGrupo()));
        setAccion("");
        return LISTA;
    }

    @SkipValidation
    public String ver() {
        setGrupo(ctrlGrupo.obtenerGrupoPorId(getId()));
        setAccion(ACCION_VER);
        return FORMULARIO;
    }

        
    public void prepareDoCrear() {
        setAccion(ACCION_CREAR);
    }
    
    @Validations (
        requiredStrings={
            @RequiredStringValidator(fieldName="grupo.nombre", message="Debe especificar un nombre.")
        },
        stringLengthFields={
            @StringLengthFieldValidator(fieldName="grupo.nombre", message="El nombre debe tener entre 1 y 30 caracteres.", minLength="1", maxLength="30")
        },
        regexFields={
            @RegexFieldValidator(fieldName="grupo.nombre", message="El nombre sólo puede contener espacios, letras y/o números.", expression="[0-9A-Za-z áéíóúÁÉíÓÚñÑüÜ]*")
        }
    )
    public String crear() {
        
        grupo.setNombre(grupo.getNombre().trim());
        
        grupo.setServicios(serviciosSeleccionados);
        
        if(!ctrlGrupo.guardarGrupo(grupo)) {
            addActionError("No se puede crear el grupo.");
            return ERROR;
        }
        
        addActionMessage("Nuevo grupo creado.");
        setGrupo(null);
        return FORMULARIO;
    }
    
    public void prepareDoModificar() {
        setAccion(ACCION_MODIFICAR);
    }
    
    public String modificar() {
        
        grupo.setNombre(grupo.getNombre().trim());
        
        grupo.setServicios(serviciosSeleccionados);

        if(!ctrlGrupo.guardarGrupo(grupo)) {
            addActionError("No se puede actualizar el grupo.");
            return ERROR;
        }
        
        addActionMessage("Grupo modificado.");
        setGrupo(null);
        setAccion(ACCION_BUSCAR);
        return FORMULARIO;
    }

    @SkipValidation
    public String eliminar() {
        
        grupo = ctrlGrupo.obtenerGrupoPorId(getId());
        
        if(!ctrlGrupo.eliminarGrupoPorId(grupo)) {
            addActionError("No se puede eliminar el grupo.");
            return ERROR;
        }
        
        grupo = null;
        addActionMessage("Grupo eliminado.");
        setAccion(ACCION_BUSCAR);
        return FORMULARIO;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public ICtrlGrupo getCtrlGrupo() {
        return ctrlGrupo;
    }

    public void setCtrlGrupo(ICtrlGrupo ctrlGrupo) {
        this.ctrlGrupo = ctrlGrupo;
    }

    public List<Servicio> getListaServicios() {
        return listaServicios;
    }

    public void setListaServicios(List<Servicio> listaServicios) {
        this.listaServicios = listaServicios;
    }

    public ICtrlServicio getCtrlServicio() {
        return ctrlServicio;
    }

    public void setCtrlServicio(ICtrlServicio ctrlServicio) {
        this.ctrlServicio = ctrlServicio;
    }

    public List<Servicio> getServiciosSeleccionados() {
        return serviciosSeleccionados;
    }

    public void setServiciosSeleccionados(List<Servicio> serviciosSeleccionados) {
        this.serviciosSeleccionados = serviciosSeleccionados;
    }

}
