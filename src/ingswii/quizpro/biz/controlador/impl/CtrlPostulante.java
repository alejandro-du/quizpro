package ingswii.quizpro.biz.controlador.impl;

import ingswii.quizpro.accesoDatos.dao.IDaoPostulante;
import ingswii.quizpro.biz.controlador.ICtrlPostulante;
import ingswii.quizpro.biz.vo.ram.Postulante;
import java.util.List;

/**
 *
 * @author Jorge
 */
public class CtrlPostulante implements ICtrlPostulante {
    
    private IDaoPostulante daoPostulante;
    
    public Postulante buscarPostulantePorDocumento(String documento) {
        return daoPostulante.buscarPorDocumento(documento);
    }
    
    public boolean crearPostulante(Postulante postulante) {
        
        if(daoPostulante.buscarPorDocumento(postulante.getDocumento()) != null) { // ya existe?
            return false;
        }
        
        return daoPostulante.guardarOActualizarPorId(postulante);
    }
    
    public boolean guardarPostulante(Postulante postulante) {
        
        if(postulante.getPassword().equals("")) { // contraseña vacia?
            Postulante pos = daoPostulante.buscarPorId(postulante.getId());
            if(pos != null && pos.getPassword() != null) {
                postulante.setPassword(pos.getPassword()); // asignar password antiguo
            }
        }
        
        return daoPostulante.guardarOActualizarPorId(postulante);
    }
    
    public IDaoPostulante getDaPostulante() {
        return daoPostulante;
    }

    public void setDaoPostulante(IDaoPostulante daoPostulante) {
        this.daoPostulante = daoPostulante;
    }

    public Postulante obtenerPostulantePorId(Long id) {
        return daoPostulante.buscarPorId(id);
    }

    public List<Postulante> buscarPostulantes(Postulante postulante) {
        if(postulante == null) {
            return daoPostulante.buscarTodos();
        }
        
        return daoPostulante.buscar(postulante);
    }

    public boolean eliminarPostulantePorId(Postulante postulante) {
        return daoPostulante.eliminarPorId(postulante.getId());
    }
}
