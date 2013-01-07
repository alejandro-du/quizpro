package ingswii.quizpro.biz.controlador.impl;

import ingswii.quizpro.accesoDatos.dao.IDaoDecision;
import ingswii.quizpro.biz.controlador.*;
import ingswii.quizpro.biz.vo.ram.Decision;
import ingswii.quizpro.biz.vo.ram.Postulante;
import ingswii.quizpro.biz.vo.ram.Pregunta;
import ingswii.quizpro.biz.vo.ram.Tema;
import java.util.List;

/**
 * 
 * @author Alejandro
 */
public class CtrlDecision implements ICtrlDecision {
    
    private ICtrlRespuesta ctrlRespuesta;
    private IDaoDecision daoDecision;

    public boolean guardarDecision(Postulante postulante, Pregunta pregunta, Long respuestaId) {
        
        Decision d = new Decision();
        d.setPostulante(postulante);
        d.setPregunta(pregunta);
        d.setRespuesta(ctrlRespuesta.obtenerRespuestaPorId(respuestaId));
        
        return daoDecision.guardarOActualizarPorId(d);
    }

    public List<Decision> obtenerPorPostulanteYTema(Postulante postulante, Tema tema) {
        return daoDecision.obtenerPorPostulanteIdYTemaId(postulante.getId(), tema.getId());
    }

    public ICtrlRespuesta getCtrlRespuesta() {
        return ctrlRespuesta;
    }

    public void setCtrlRespuesta(ICtrlRespuesta ctrlRespuesta) {
        this.ctrlRespuesta = ctrlRespuesta;
    }

    public IDaoDecision getDaoDecision() {
        return daoDecision;
    }

    public void setDaoDecision(IDaoDecision daoDecision) {
        this.daoDecision = daoDecision;
    }
    
}
