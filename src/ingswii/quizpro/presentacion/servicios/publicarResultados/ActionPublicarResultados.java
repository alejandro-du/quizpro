package ingswii.quizpro.presentacion.servicios.publicarResultados;

import ingswii.quizpro.biz.controlador.ICtrlExamen;
import ingswii.quizpro.biz.vo.ram.Convocatoria;
import ingswii.quizpro.presentacion.comun.ISesion;
import ingswii.quizpro.presentacion.comun.impl.AbstractAction;

/**
 *
 * @author Alejandro
 */
public class ActionPublicarResultados extends AbstractAction {
    
    private ICtrlExamen ctrlExamen;

    public String inicio() {
        return OK;
    }
    
    public String publicar() {
        
        if(!ctrlExamen.calificar((Convocatoria) getSesion().getObjetoDeSesion(ISesion.CONVOCATORIA))) {
            addActionError("No se pudo completar el proceso de calificación.");
            return ERROR;
        }
        
        if(!ctrlExamen.clasificar((Convocatoria) getSesion().getObjetoDeSesion(ISesion.CONVOCATORIA))) {
            addActionError("No se pudo completar el proceso de clasificación.");
            return ERROR;
        }
        
        addActionMessage("Los resultados de la convocatoria han sido publicados satisfactoriamente.");
        return MENSAJE;
    }
    
    public ICtrlExamen getCtrlExamen() {
        return ctrlExamen;
    }

    public void setCtrlExamen(ICtrlExamen ctrlExamen) {
        this.ctrlExamen = ctrlExamen;
    }
    
}
