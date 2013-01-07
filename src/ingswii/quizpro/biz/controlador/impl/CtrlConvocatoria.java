package ingswii.quizpro.biz.controlador.impl;

import ingswii.quizpro.biz.controlador.*;
import ingswii.quizpro.accesoDatos.dao.IDaoConvocatoria;
import ingswii.quizpro.biz.vo.ram.Convocatoria;
import java.util.List;

/**
 *
 * @author AlejandroDu
 */
public class CtrlConvocatoria implements ICtrlConvocatoria {
    
    private IDaoConvocatoria daoConvocatoria;
    
    public List<Convocatoria> obtenerConvocatorias() {
        return getDaoConvocatoria().buscarTodos();
    }

    public IDaoConvocatoria getDaoConvocatoria() {
        return daoConvocatoria;
    }

    public void setDaoConvocatoria(IDaoConvocatoria daoConvocatoria) {
        this.daoConvocatoria = daoConvocatoria;
    }

    public Convocatoria obtenerConvocatoriaPorId(Convocatoria convocatoria) {
        return daoConvocatoria.buscarPorId(convocatoria.getId());
    }

    public Convocatoria obtenerConvocatoriaPorId(Long id) {
        return daoConvocatoria.buscarPorId(id);
    }

    public List<Convocatoria> buscarConvocatorias(Convocatoria convocatoria) {
        if(convocatoria == null) {
            return daoConvocatoria.buscarTodos();
        }
        
        return daoConvocatoria.buscar(convocatoria);
    }

    public boolean guardarConvocatoria(Convocatoria convocatoria) {
        
        if(convocatoria == null) {
            return false;
        }
        
        return daoConvocatoria.guardarOActualizarPorId(convocatoria);
    }

    public boolean eliminarConvocatoriaPorId(Convocatoria convocatoria) {
        return daoConvocatoria.eliminarPorId(convocatoria.getId());
    }

}
