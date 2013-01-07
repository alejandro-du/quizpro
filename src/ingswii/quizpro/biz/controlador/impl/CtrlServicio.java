package ingswii.quizpro.biz.controlador.impl;

import ingswii.quizpro.biz.controlador.*;
import ingswii.quizpro.accesoDatos.dao.IDaoServicio;
import ingswii.quizpro.biz.vo.ugs.Grupo;
import ingswii.quizpro.biz.vo.ugs.Menu;
import ingswii.quizpro.biz.vo.ugs.Servicio;
import ingswii.quizpro.biz.vo.ugs.Usuario;
import java.util.List;

/**
 *
 * @author AlejandroDu
 */
public class CtrlServicio implements ICtrlServicio {
    
    private IDaoServicio daoServicio;
    
    public boolean usuarioPuedeAcceder(Usuario usuarioAutenticado, Servicio servicio) {
        
        if(servicio == null) {
            return false;
        }
        
        if(servicio.getPublico()) {
            return true;
        }
        
        if(usuarioAutenticado == null) {
            return false;
        }
        
        for (Grupo grupo : usuarioAutenticado.getGrupos()) {
            for (Servicio serv : grupo.getServicios()) {
                if(serv.getId().equals(servicio.getId()))
                    return true;
            }
        }
        
        return false;
    }

    public boolean usuarioPuedeAcceder(Usuario usuarioAutenticado, String url) {
        
        Servicio servicioPublico = daoServicio.buscarServicioPorUrlYPublico(url, true);
        
        // permitir el acceso a cualquier servicio público sin importar el usuario
        if(servicioPublico != null)
            return true;
        
        if(usuarioAutenticado == null)
            return false;
        
        for (Grupo grupo : usuarioAutenticado.getGrupos()) {
            for (Servicio servicio : grupo.getServicios()) {
                if(servicio.getUrl().equals(url))
                    return true;
            }
        }
        
        return false;
    }
    
    public Servicio obtenerServicioPorUrl(String url) {
        
        return getDaoServicio().buscarServicioPorUrl(url);
    }
    
    public IDaoServicio getDaoServicio() {
        return daoServicio;
    }

    public void setDaoServicio(IDaoServicio daoServicio) {
        this.daoServicio = daoServicio;
    }

    public List<Servicio> obtenerServiciosPublicos() {
        return daoServicio.buscarPorPublico(true);
    }

    public List<Servicio> obtenerServiciosDelMenu(Menu menu) {
        return daoServicio.buscarPorMenu(menu);
    }

    public List<Servicio> obtenerServicios() {
        return daoServicio.buscarTodos();
    }

    public Servicio obtenerServicioPorId(Long id) {
        return daoServicio.buscarPorId(id);
    }

    public List<Servicio> buscarServicios(Servicio servicio) {
        
        if(servicio == null) {
            return daoServicio.buscarTodos();
        }
        
        return daoServicio.buscar(servicio);
    }

    public boolean guardarServicio(Servicio servicio) {
        
        if(servicio == null) {
            return false;
        }
        
        if(servicio.getMenu().getId().equals(-1L)) {
            servicio.setMenu(null);
        }
        
        return daoServicio.guardarOActualizarPorId(servicio);
    }

    public boolean eliminarServicioPorId(Servicio servicio) {
        return daoServicio.eliminarPorId(servicio.getId());
    }

    public Servicio obtenerServicioInicial() {
        return daoServicio.buscarPorNombre("Seleccionar convocatoria");
    }

}
