package ingswii.quizpro.accesoDatos.dao;

import ingswii.quizpro.biz.vo.ugs.Menu;
import java.util.List;

/**
 * DAO para los VOs de tipo Menu.
 * @author Alejandro
 */
public interface IDaoMenu extends IAbstractDao<Menu> {

    List<Menu> buscar(Menu menu);

}
