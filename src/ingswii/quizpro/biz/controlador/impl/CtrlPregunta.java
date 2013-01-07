package ingswii.quizpro.biz.controlador.impl;

import ingswii.quizpro.biz.controlador.*;
import ingswii.quizpro.accesoDatos.dao.IDaoPregunta;
import ingswii.quizpro.biz.vo.ram.Examen;
import ingswii.quizpro.biz.vo.ram.Postulante;
import ingswii.quizpro.biz.vo.ram.Pregunta;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * 
 * @author Alejandro
 */
public class CtrlPregunta implements ICtrlPregunta {
    
    private IDaoPregunta daoPregunta;
    private ICtrlServicio ctrlServicio;

    public List<Pregunta> obtenerPreguntas() {
        return daoPregunta.buscarTodos();
    }

    public IDaoPregunta getDaoPregunta() {
        return daoPregunta;
    }

    public void setDaoPregunta(IDaoPregunta daoPregunta) {
        this.daoPregunta = daoPregunta;
    }

    public ICtrlServicio getCtrlServicio() {
        return ctrlServicio;
    }

    public void setCtrlServicio(ICtrlServicio ctrlServicio) {
        this.ctrlServicio = ctrlServicio;
    }

    public Pregunta obtenerPreguntaPorId(Long id) {
        return daoPregunta.buscarPorId(id);
    }

    public List<Pregunta> buscarPreguntas(Pregunta pregunta) {
        if(pregunta == null) {
            return daoPregunta.buscarTodos();
        }
        
        return daoPregunta.buscar(pregunta);
    }

    public boolean guardarPregunta(Pregunta pregunta) {
        
        if(pregunta == null) {
            return false;
        }
        
        return daoPregunta.guardarOActualizarPorId(pregunta);
    }

    public boolean eliminarPreguntaPorId(Pregunta pregunta) {
        return daoPregunta.eliminarPorId(pregunta.getId());
    }

    public Pregunta getPreguntaNoContestada(Examen examen, Postulante postulante) {
        
        List<Pregunta> lista = daoPregunta.getPreguntasNoContestadas(examen.getId(), postulante.getId());
        
        if(lista == null || lista.isEmpty()) {
            return null;
        }
        
        Random r = new Random();
        r.setSeed(Calendar.getInstance().getTime().getTime());
        
        if (lista.size()==1){
           return lista.get(0);
        }
        
        return lista.get(r.nextInt(lista.size() - 1));
    }
    
    public int getCantidadPreguntasNoContestadas(Examen examen, Postulante postulante){
        List<Pregunta> lista = daoPregunta.getPreguntasNoContestadas(examen.getId(), postulante.getId());
        
        if(lista == null || lista.isEmpty()) {
            return 0;
        }
    return lista.size();
    }

}
