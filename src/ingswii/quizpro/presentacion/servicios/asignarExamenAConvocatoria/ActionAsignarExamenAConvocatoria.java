package ingswii.quizpro.presentacion.servicios.asignarExamenAConvocatoria;

import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validation;
import com.opensymphony.xwork2.validator.annotations.Validations;
import ingswii.quizpro.biz.controlador.ICtrlConvocatoria;
import ingswii.quizpro.biz.controlador.ICtrlExamen;
import ingswii.quizpro.biz.vo.ram.Convocatoria;
import ingswii.quizpro.biz.vo.ram.Examen;
import ingswii.quizpro.presentacion.comun.impl.AbstractAction;
import java.util.List;
import org.apache.struts2.interceptor.validation.SkipValidation;

/**
 *
 * @author Alejandro
 */
@Validation
public class ActionAsignarExamenAConvocatoria extends AbstractAction {
    
    private Convocatoria convocatoria;
    private Examen examen;
    
    private List<Convocatoria> listaConvocatorias;
    private List<Examen> listaExamenes;
    
    private ICtrlConvocatoria ctrlConvocatoria;
    private ICtrlExamen ctrlExamen;
    
    @Override
    public void prepare() {
        setListaConvocatorias(getCtrlConvocatoria().obtenerConvocatorias());
        setListaExamenes(getCtrlExamen().obtenerExamenes());
    }
    
    @SkipValidation
    public String inicio() {
        return OK;
    }
    
    @Validations (
        requiredFields={
            @RequiredFieldValidator(fieldName="convocatoria.id", message="Debe seleccionar una convocatoria."),
            @RequiredFieldValidator(fieldName="examen.id", message="Debe seleccionar un examen.")
        }
    )
    public String asignar() {
        
        convocatoria = ctrlConvocatoria.obtenerConvocatoriaPorId(convocatoria);
        convocatoria.setExamen(examen);
        ctrlConvocatoria.guardarConvocatoria(convocatoria);
        addActionMessage("El examen ha sido asignado satisfactoriamente.");
        
        return MENSAJE;
    }

    ICtrlConvocatoria getCtrlConvocatoria() {
        return ctrlConvocatoria;
    }

    public void setCtrlConvocatoria(ICtrlConvocatoria ctrlConvocatoria) {
        this.ctrlConvocatoria = ctrlConvocatoria;
    }

    public List<Convocatoria> getListaConvocatorias() {
        return listaConvocatorias;
    }

    public void setListaConvocatorias(List<Convocatoria> listaConvocatorias) {
        this.listaConvocatorias = listaConvocatorias;
    }

    public List<Examen> getListaExamenes() {
        return listaExamenes;
    }

    public void setListaExamenes(List<Examen> listaExamenes) {
        this.listaExamenes = listaExamenes;
    }

    public ICtrlExamen getCtrlExamen() {
        return ctrlExamen;
    }

    public void setCtrlExamen(ICtrlExamen ctrlExamen) {
        this.ctrlExamen = ctrlExamen;
    }

    public Convocatoria getConvocatoria() {
        return convocatoria;
    }

    public void setConvocatoria(Convocatoria convocatoria) {
        this.convocatoria = convocatoria;
    }

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }

}
