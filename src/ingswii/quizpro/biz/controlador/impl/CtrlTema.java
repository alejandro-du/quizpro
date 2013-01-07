package ingswii.quizpro.biz.controlador.impl;

import ingswii.quizpro.biz.controlador.*;
import ingswii.quizpro.accesoDatos.dao.IDaoTema;
import ingswii.quizpro.biz.vo.ram.Examen;
import ingswii.quizpro.biz.vo.ram.Tema;
import java.util.List;

/**
 * 
 * @author Alejandro
 */
public class CtrlTema implements ICtrlTema {
    
    private IDaoTema daoTema;
    private ICtrlServicio ctrlServicio;

    public List<Tema> obtenerTemas() {
        return daoTema.buscarTodos();
    }

    public IDaoTema getDaoTema() {
        return daoTema;
    }

    public void setDaoTema(IDaoTema daoTema) {
        this.daoTema = daoTema;
    }

    public ICtrlServicio getCtrlServicio() {
        return ctrlServicio;
    }

    public void setCtrlServicio(ICtrlServicio ctrlServicio) {
        this.ctrlServicio = ctrlServicio;
    }

    public Tema obtenerTemaPorId(Long id) {
        return daoTema.buscarPorId(id);
    }

    public List<Tema> buscarTemas(Tema tema) {
        if(tema == null) {
            return daoTema.buscarTodos();
        }
        
        return daoTema.buscar(tema);
    }

    public boolean guardarTema(Tema tema) {
        
        if(tema == null) {
            return false;
        }
        
        return daoTema.guardarOActualizarPorId(tema);
    }

    public boolean eliminarTemaPorId(Tema tema) {
        return daoTema.eliminarPorId(tema.getId());
    }
    
    public List<Tema> obtenerTemasPorExamen(Examen examen) {
        return daoTema.obtenerPorExamenId(examen.getId());
    }

}
