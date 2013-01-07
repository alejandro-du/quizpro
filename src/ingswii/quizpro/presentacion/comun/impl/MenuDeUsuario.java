package ingswii.quizpro.presentacion.comun.impl;

import ingswii.quizpro.presentacion.comun.IMenuDeUsuario;
import ingswii.quizpro.biz.controlador.*;
import ingswii.quizpro.biz.vo.ugs.Menu;
import ingswii.quizpro.biz.vo.ugs.Servicio;
import ingswii.quizpro.biz.vo.ugs.Usuario;
import ingswii.quizpro.presentacion.comun.ISesion;
import java.util.ArrayList;
import java.util.List;

public class MenuDeUsuario implements IMenuDeUsuario {
    
    private String nombre = "";
    private String url = "";
    private List<IMenuDeUsuario> submenuList = new ArrayList<IMenuDeUsuario>();
    
    private ICtrlMenu ctrlMenu;
    private ICtrlServicio ctrlServicio;
    private ISesion sesion;
    
    public void agregarSubmenu(IMenuDeUsuario submenu) {
        getSubmenuList().add(submenu);
    }
    
    public void construirMenu(Usuario usuario, String prefijoUrl) {
        
        getSubmenuList().clear();
        this.setNombre("Menú principal");
        
        for(Menu menu : getCtrlMenu().obtenerMenus()) {
            
            MenuDeUsuario submenu = new MenuDeUsuario();
            submenu.setNombre(menu.getNombre());
            
            for(Servicio servicio : getCtrlServicio().obtenerServiciosDelMenu(menu)) {
                
                if(getCtrlServicio().usuarioPuedeAcceder(usuario, servicio)) {

                    MenuDeUsuario opcion = new MenuDeUsuario();
                    opcion.setNombre(servicio.getNombre());
                    opcion.setUrl(prefijoUrl + servicio.getUrl());
                    submenu.getSubmenuList().add(opcion);
                }
            }
            
            if(submenu.getSubmenuList() != null && submenu.getSubmenuList().size() > 0) {
                this.agregarSubmenu(submenu);
            }
        }
    }

    public String getJavaScript() {
        
        String script = "";
        
        if(this.getSubmenuList() == null || this.getSubmenuList().size() <= 0) {
            return "";
        }
        
        for(int i = 0; i < this.getSubmenuList().size(); i++) {
            
            IMenuDeUsuario menu = this.getSubmenuList().get(i);
            
            script += construirItem(menu, i + 1);
            
            for(int j = 0; j < menu.getSubmenuList().size(); j++) {
                
                IMenuDeUsuario opcion = menu.getSubmenuList().get(j);
                
                script += ",";
                script += construirItem(opcion, j + 1);
                script += ")";
            }
            
            script += "),";
        }
        
        script = script.substring(0, script.length() - 1); // eliminar última coma
        
        return script;
    }
    
    private String construirItem(IMenuDeUsuario item, int i) {
        
        String str = "" + i + ",new Hash('contents','" + item.getNombre() + "'";
        str += ",'uri','" + item.getUrl() + "'";
        
        return str;
    }

    public List<IMenuDeUsuario> getSubmenuList() {
        return submenuList;
    }

    public void setSubmenuList(List<IMenuDeUsuario> submenuList) {
        this.submenuList = submenuList;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public

    ICtrlMenu getCtrlMenu() {
        return ctrlMenu;
    }

    public void setCtrlMenu(ICtrlMenu ctrlMenu) {
        this.ctrlMenu = ctrlMenu;
    }

    public ICtrlServicio getCtrlServicio() {
        return ctrlServicio;
    }

    public void setCtrlServicio(ICtrlServicio ctrlServicio) {
        this.ctrlServicio = ctrlServicio;
    }

    public void cargarMenuEnSesion(Usuario usuario) {
        this.construirMenu((Usuario) getSesion().getObjetoDeSesion(ISesion.USUARIO_AUTENTICADO), getSesion().getContextPath());
        getSesion().setObjetoDeSesion(ISesion.JAVASCRIPT_MENU, this.getJavaScript());
    }

    public ISesion getSesion() {
        return sesion;
    }

    public void setSesion(ISesion sesion) {
        this.sesion = sesion;
    }

}
