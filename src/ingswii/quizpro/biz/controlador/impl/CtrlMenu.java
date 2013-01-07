package ingswii.quizpro.biz.controlador.impl;

import ingswii.quizpro.biz.controlador.*;
import ingswii.quizpro.accesoDatos.dao.IDaoMenu;
import ingswii.quizpro.biz.vo.ugs.Menu;
import java.util.List;

/**
 * 
 * @author Alejandro
 */
public class CtrlMenu implements ICtrlMenu {
    
    private IDaoMenu daoMenu;
    private ICtrlServicio ctrlServicio;

    public List<Menu> obtenerMenus() {
        return daoMenu.buscarTodos();
    }

    public IDaoMenu getDaoMenu() {
        return daoMenu;
    }

    public void setDaoMenu(IDaoMenu daoMenu) {
        this.daoMenu = daoMenu;
    }

    public ICtrlServicio getCtrlServicio() {
        return ctrlServicio;
    }

    public void setCtrlServicio(ICtrlServicio ctrlServicio) {
        this.ctrlServicio = ctrlServicio;
    }

    public Menu obtenerMenuPorId(Long id) {
        return daoMenu.buscarPorId(id);
    }

    public List<Menu> buscarMenus(Menu menu) {
        if(menu == null) {
            return daoMenu.buscarTodos();
        }
        
        return daoMenu.buscar(menu);
    }

    public boolean guardarMenu(Menu menu) {
        
        if(menu == null) {
            return false;
        }
        
        return daoMenu.guardarOActualizarPorId(menu);
    }

    public boolean eliminarMenuPorId(Menu menu) {
        return daoMenu.eliminarPorId(menu.getId());
    }

}
