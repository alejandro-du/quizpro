package ingswii.quizpro.accesoDatos.dao;

import ingswii.quizpro.biz.vo.ram.Ip;
import java.util.List;

/**
 * DAO para los VOs de tipo Ip.
 * @author Alejandro
 */
public interface IDaoIp extends IAbstractDao<Ip> {

    List<Ip> buscar(Ip ip);

}
