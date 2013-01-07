package ingswii.quizpro.presentacion.servicios.consultarResultados;

import ingswii.quizpro.biz.controlador.ICtrlPostulante;
import ingswii.quizpro.biz.controlador.ICtrlResultado;
import ingswii.quizpro.biz.vo.ram.Convocatoria;
import ingswii.quizpro.biz.vo.ram.Examen;
import ingswii.quizpro.biz.vo.ram.Postulante;
import ingswii.quizpro.biz.vo.ram.Resultado;
import ingswii.quizpro.presentacion.comun.ISesion;
import ingswii.quizpro.presentacion.comun.impl.AbstractAction;
import java.util.List;

/**
 *
 * @author Alejandro
 */
public class ActionConsultarResultados extends AbstractAction {
    
    private Postulante postulante;
    private Examen examen;
    private List<Resultado> resultados;
    private Resultado resultadoFinal;
    
    private ICtrlPostulante ctrlPostulante;
    private ICtrlResultado ctrlResultado;
    
    public String inicio() {
        return OK;
    }
    
    public String consultar() {
        
        String password = postulante.getPassword();
        postulante = ctrlPostulante.buscarPostulantePorDocumento(postulante.getDocumento());
        
        if(postulante == null || !postulante.getPassword().equals(password)) {
            addActionError("Los datos suministrados son incorrectos.");
            return INPUT;
        }
        
        examen = ((Convocatoria) getSesion().getObjetoDeSesion(ISesion.CONVOCATORIA)).getExamen();
        resultados = ctrlResultado.obtenerPorPostulanteYExamen(postulante, examen);
        resultadoFinal = ctrlResultado.obtenerFinalPorPostulanteYExamen(postulante, examen);
        
        return OK;
    }

    public Postulante getPostulante() {
        return postulante;
    }

    public void setPostulante(Postulante postulante) {
        this.postulante = postulante;
    }

    public ICtrlPostulante getCtrlPostulante() {
        return ctrlPostulante;
    }

    public void setCtrlPostulante(ICtrlPostulante ctrlPostulante) {
        this.ctrlPostulante = ctrlPostulante;
    }

    public List<Resultado> getResultados() {
        return resultados;
    }

    public void setResultados(List<Resultado> resultados) {
        this.resultados = resultados;
    }

    public ICtrlResultado getCtrlResultado() {
        return ctrlResultado;
    }

    public void setCtrlResultado(ICtrlResultado ctrlResultado) {
        this.ctrlResultado = ctrlResultado;
    }

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }

    public Resultado getResultadoFinal() {
        return resultadoFinal;
    }

    public void setResultadoFinal(Resultado resultadoFinal) {
        this.resultadoFinal = resultadoFinal;
    }

}
