package ingswii.quizpro.biz.controlador;

import ingswii.quizpro.biz.vo.reportes.Reporte;
import java.util.List;

/**
 * Proporciona funcionalidad para los VOs Reporte.
 * @author Alejandro
 */
public interface ICtrlReporte {
    
    Reporte obtenerReportePorId(Long id);
    
    List<Reporte> buscarReportes(Reporte reporte);
    
    boolean guardarReporte(Reporte reporte);
    
    boolean eliminarReportePorId(Reporte reporte);
    
}
