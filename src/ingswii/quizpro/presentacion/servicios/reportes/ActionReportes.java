package ingswii.quizpro.presentacion.servicios.reportes;

import ingswii.quizpro.biz.controlador.ICtrlReporte;
import ingswii.quizpro.biz.vo.ram.Convocatoria;
import ingswii.quizpro.biz.vo.reportes.Reporte;
import ingswii.quizpro.presentacion.comun.ISesion;
import ingswii.quizpro.presentacion.comun.impl.AbstractAction;
import java.util.List;


/**
 * Action para la visualización de reportes.
 * @author Alejandro
 */
public class ActionReportes extends AbstractAction {
    
    private List resultados;
    private Long id;
    private Reporte reporte;
    
    private ICtrlReporte ctrlReporte;
    
    public String ejecutar() {
        
        setReporte(ctrlReporte.obtenerReportePorId(getId()));
        
        if(getReporte() == null) {
            addActionError("No se encuentra el reporte " + getId() + ".");
            return ERROR;
        }
        
        String conId = "" + ((Convocatoria)getSesion().getObjetoDeSesion(ISesion.CONVOCATORIA)).getId();
        reporte.setConsulta(reporte.getConsulta().replace(":con_id", conId));
        return OK;
    }
    
    public List getResultados() {
        return resultados;
    }

    public void setResultados(List resultados) {
        this.resultados = resultados;
    }

    public ICtrlReporte getCtrlReporte() {
        return ctrlReporte;
    }

    public void setCtrlReporte(ICtrlReporte ctrlReporte) {
        this.ctrlReporte = ctrlReporte;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Reporte getReporte() {
        return reporte;
    }

    public void setReporte(Reporte reporte) {
        this.reporte = reporte;
    }

}
