package ingswii.quizpro.accesoDatos.dao;

import ingswii.quizpro.biz.vo.ram.Tema;
import java.util.List;

/**
 * DAO para los VOs Tema.
 * @author Alejandro
 */
public interface IDaoTema extends IAbstractDao<Tema> {

    List<Tema> buscar(Tema tema);
    List<Tema> obtenerPorExamenId(Long examenId);

}
