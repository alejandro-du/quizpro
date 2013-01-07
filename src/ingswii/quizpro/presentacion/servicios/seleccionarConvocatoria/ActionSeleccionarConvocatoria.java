package ingswii.quizpro.presentacion.servicios.seleccionarConvocatoria;

import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validation;
import ingswii.quizpro.biz.controlador.ICtrlConvocatoria;
import ingswii.quizpro.presentacion.comun.IMenuDeUsuario;
import ingswii.quizpro.biz.vo.ram.Convocatoria;
import ingswii.quizpro.biz.vo.ugs.Usuario;
import ingswii.quizpro.presentacion.comun.impl.AbstractAction;
import ingswii.quizpro.presentacion.comun.ISesion;
import java.util.List;

/**
 * Servicio para seleccionar una convocatoria y guardarla en la sesión de usuario.
 * Carga un menú en la sesión.
 * @author Alejandro
 */
@Validation()
public class ActionSeleccionarConvocatoria extends AbstractAction {
    
    private Convocatoria convocatoria;
    private List<Convocatoria> listaConvocatorias;
    
    private ICtrlConvocatoria ctrlConvocatoria;
    private IMenuDeUsuario menuDeUsuario;
    
    @Override
    public void prepare() {
        setListaConvocatorias(ctrlConvocatoria.obtenerConvocatorias());
    }
    
    public String inicio() {
        return OK;
    }
    
    @RequiredFieldValidator(fieldName="convocatoria.id", message="Debe seleccionar una convocatoria.")
    public String establecerConvocatoriaSesion() {
        
        convocatoria = ctrlConvocatoria.obtenerConvocatoriaPorId(convocatoria);
        getSesion().setObjetoDeSesion(ISesion.CONVOCATORIA, convocatoria);
        menuDeUsuario.cargarMenuEnSesion((Usuario) getSesion().getObjetoDeSesion(ISesion.USUARIO_AUTENTICADO));
        
        return INFO;
    }
    
    public List<Convocatoria> getListaConvocatorias() {
        return listaConvocatorias;
    }

    public void setListaConvocatorias(List<Convocatoria> listaConvocatorias) {
        this.listaConvocatorias = listaConvocatorias;
    }

    public ICtrlConvocatoria getCtrlConvocatoria() {
        return ctrlConvocatoria;
    }

    public void setCtrlConvocatoria(ICtrlConvocatoria ctrlConvocatoria) {
        this.ctrlConvocatoria = ctrlConvocatoria;
    }

    public Convocatoria getConvocatoria() {
        return convocatoria;
    }

    public void setConvocatoria(Convocatoria convocatoria) {
        this.convocatoria = convocatoria;
    }

    public IMenuDeUsuario getMenuDeUsuario() {
        return menuDeUsuario;
    }

    public void setMenuDeUsuario(IMenuDeUsuario menuDeUsuario) {
        this.menuDeUsuario = menuDeUsuario;
    }

}
