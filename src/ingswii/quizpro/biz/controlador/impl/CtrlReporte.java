package ingswii.quizpro.biz.controlador.impl;

import ingswii.quizpro.biz.controlador.*;
import ingswii.quizpro.accesoDatos.dao.IDaoReporte;
import ingswii.quizpro.biz.vo.reportes.Reporte;
import java.util.List;

/**
 * 
 * @author Alejandro
 */
public class CtrlReporte implements ICtrlReporte {
    
    private IDaoReporte daoReporte;
    private ICtrlServicio ctrlServicio;

    public List<Reporte> obtenerReportes() {
        return daoReporte.buscarTodos();
    }

    public IDaoReporte getDaoReporte() {
        return daoReporte;
    }

    public void setDaoReporte(IDaoReporte daoReporte) {
        this.daoReporte = daoReporte;
    }

    public ICtrlServicio getCtrlServicio() {
        return ctrlServicio;
    }

    public void setCtrlServicio(ICtrlServicio ctrlServicio) {
        this.ctrlServicio = ctrlServicio;
    }

    public Reporte obtenerReportePorId(Long id) {
        return daoReporte.buscarPorId(id);
    }

    public List<Reporte> buscarReportes(Reporte reporte) {
        if(reporte == null) {
            return daoReporte.buscarTodos();
        }
        
        return daoReporte.buscar(reporte);
    }

    public boolean guardarReporte(Reporte reporte) {
        
        if(reporte == null) {
            return false;
        }
        
        return daoReporte.guardarOActualizarPorId(reporte);
    }

    public boolean eliminarReportePorId(Reporte reporte) {
        return daoReporte.eliminarPorId(reporte.getId());
    }

}
