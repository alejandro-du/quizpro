package ingswii.quizpro.biz.controlador.impl;

import ingswii.quizpro.biz.controlador.*;
import ingswii.quizpro.accesoDatos.dao.IDaoResultado;
import ingswii.quizpro.biz.vo.ram.Examen;
import ingswii.quizpro.biz.vo.ram.Postulante;
import ingswii.quizpro.biz.vo.ram.Resultado;
import java.util.List;

/**
 * 
 * @author Alejandro
 */
public class CtrlResultado implements ICtrlResultado {
    
    private IDaoResultado daoResultado;

    public boolean guardarResultado(Resultado resultado) {
        
        if(resultado == null) {
            return false;
        }
        
        return getDaoResultado().guardarOActualizarPorId(resultado);
    }

    public boolean eliminarPorExamen(Examen examen) {
        return daoResultado.eliminarPorExamenId(examen.getId());
    }

    public List<Resultado> obtenerPrimerosResultados(Examen examen, int cantidad) {
        return daoResultado.obtenerPrimerosPuntajes(examen.getId(), cantidad);
    }
    
    public List<Resultado> obtenerPorPostulanteYExamen(Postulante postulante, Examen examen) {
        return daoResultado.obtenerPorPostulanteIdYExamenId(postulante.getId(), examen.getId());
    }

    public Resultado obtenerFinalPorPostulanteYExamen(Postulante postulante, Examen examen) {
        return daoResultado.obtenerFinalPorPostulanteIdYExamenId(postulante.getId(), examen.getId());
    }

    public IDaoResultado getDaoResultado() {
        return daoResultado;
    }

    public void setDaoResultado(IDaoResultado daoResultado) {
        this.daoResultado = daoResultado;
    }

}
