package ingswii.quizpro.biz.controlador.impl;

import ingswii.quizpro.accesoDatos.dao.IDaoGrupo;
import ingswii.quizpro.biz.controlador.ICtrlGrupo;
import ingswii.quizpro.biz.vo.ugs.Grupo;
import ingswii.quizpro.biz.vo.ugs.Servicio;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alejandro
 */
public class CtrlGrupo implements ICtrlGrupo {
    
    private IDaoGrupo daoGrupo;

    public List<Grupo> obtenerGrupos() {
        return daoGrupo.buscarTodos();
    }

    public IDaoGrupo getDaoGrupo() {
        return daoGrupo;
    }

    public void setDaoGrupo(IDaoGrupo daoGrupo) {
        this.daoGrupo = daoGrupo;
    }

    public Grupo obtenerGrupoPorId(Long id) {
        return daoGrupo.buscarPorId(id);
    }

    public List<Grupo> buscarGrupos(Grupo grupo) {
        
        if(grupo == null) {
            return daoGrupo.buscarTodos();
        }
        
        return daoGrupo.buscar(grupo);
    }

    public boolean guardarGrupo(Grupo grupo) {
        
        if(grupo == null) {
            return false;
        }
        
        if(grupo.getServicios() != null) {
        
            List<Servicio> lista = new ArrayList<Servicio>();

            for(Servicio g: grupo.getServicios()) {
                if(!lista.contains(g)) {
                    lista.add(g);
                }
            }
        
            grupo.setServicios(lista);
        }
        
        return daoGrupo.guardarOActualizarPorId(grupo);
    }

    public boolean eliminarGrupoPorId(Grupo grupo) {
        return daoGrupo.eliminarPorId(grupo.getId());
    }

}
