package ingswii.quizpro.biz.controlador;

import ingswii.quizpro.biz.vo.ugs.Menu;
import java.util.List;

/**
 * Proporciona funcionalidad para los VOs Menu.
 * @author Alejandro
 */
public interface ICtrlMenu {
    
    List<Menu> obtenerMenus();
    
    Menu obtenerMenuPorId(Long id);
    
    List<Menu> buscarMenus(Menu menu);
    
    boolean guardarMenu(Menu menu);
    
    boolean eliminarMenuPorId(Menu menu);
    
}
