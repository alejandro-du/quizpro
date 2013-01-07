package ingswii.quizpro.accesoDatos.dao;

import ingswii.quizpro.biz.vo.reportes.Reporte;
import java.util.List;

/**
 * DAO para los VOs de tipo Reporte.
 * @author Alejandro
 */
public interface IDaoReporte extends IAbstractDao<Reporte> {

    List<Reporte> buscar(Reporte reporte);

}
