
package ingswii.quizpro.presentacion.servicios.presentarExamen;

import ingswii.quizpro.biz.controlador.ICtrlDecision;
import ingswii.quizpro.biz.controlador.ICtrlPostulante;
import ingswii.quizpro.biz.controlador.ICtrlPregunta;
import ingswii.quizpro.biz.vo.ram.Convocatoria;
import ingswii.quizpro.biz.vo.ram.Examen;
import ingswii.quizpro.biz.vo.ram.Ip;
import ingswii.quizpro.biz.vo.ram.Postulante;
import ingswii.quizpro.biz.vo.ram.Pregunta;
import ingswii.quizpro.biz.vo.ram.Respuesta;
import ingswii.quizpro.biz.vo.ram.Tema;
import ingswii.quizpro.presentacion.comun.ISesion;
import ingswii.quizpro.presentacion.comun.impl.AbstractAction;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.struts2.ServletActionContext;

/**
 * @author Jorge
 */
public class ActionPresentarExamen extends AbstractAction {
    
    private static String HORA_FIN = "horafin";
    private static String TIEMPO_RESTANTE = "tiempoRestante";
    private static String POSTULANTE = "postulante";
    private static String PREGUNTA = "pregunta";
    
    private Examen examen;
    private Pregunta pregunta;
    private Postulante postulante;
    private Long seleccion;
    private int preguntasFaltantes;
    private Tema tema;
    
    private ICtrlPregunta ctrlPregunta;
    private ICtrlPostulante ctrlPostulante;
    private ICtrlDecision ctrlDecision;
    
    
    @Override
    public void prepare() {
        setExamen(((Convocatoria) getSesion().getObjetoDeSesion(ISesion.CONVOCATORIA)).getExamen());
    }
    
    public String inicio() {
        if (examen == null){
            return ERROR;
        }
        
        Calendar hoy = Calendar.getInstance();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String cadenaHoy = formato.format(hoy.getTime());
        Convocatoria convocatoria = (Convocatoria) getSesion().getObjetoDeSesion(ISesion.CONVOCATORIA);

        if(cadenaHoy.compareTo(convocatoria.getFechaInicio())<0 || cadenaHoy.compareTo(convocatoria.getFechaFin())>0){
            addActionMessage("El periodo de presentación del examen comprende desde " + convocatoria.getFechaInicio() + " hasta " + convocatoria.getFechaFin() + ".");
            addActionMessage(cadenaHoy);
            return MENSAJE;
        }
        
        String ipRemota = ServletActionContext.getRequest().getRemoteAddr();
        
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "IP " + ipRemota + " Entrando a presentar examen.");
        
        Boolean ipValida = false;
        for(Ip ip : examen.getIps()) {                   
            if(ip.getIp().equals(ipRemota)) {
                ipValida = true;
                break;
            }
        }
        if (!ipValida && !examen.getPublico().booleanValue()){
            addActionMessage("La dirección IP del equipo remoto del cual desea acceder al examen no está permitida.");
            //addActionMessage(ipLocal);
            return MENSAJE;    
        }   
        return OK;
    }
    
    public String cargarPostulante() {
        
        String password = postulante.getPassword();
        postulante = ctrlPostulante.buscarPostulantePorDocumento(postulante.getDocumento());
        
        if(postulante == null || !postulante.getPassword().equals(password)) {
            addActionError("Datos incorrectos.");
            return "inicio";
        }
        
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try{
            cal.setTime(formato.parse(((Convocatoria) getSesion().getObjetoDeSesion(ISesion.CONVOCATORIA)).getFechaFin()));
            getSesion().setObjetoDeSesion(HORA_FIN, cal);
        }
        catch(ParseException e){
            return ERROR;
        }
        getSesion().setObjetoDeSesion(POSTULANTE, postulante);
        return mostrarPregunta();
    }
    
    public String mostrarPregunta() {
                
        Calendar horaActual = Calendar.getInstance();
        double tiempoRestante = (((Calendar) getSesion().getObjetoDeSesion(HORA_FIN)).getTime().getTime())-(horaActual.getTime().getTime());
       
        if(tiempoRestante<=0) {
            setPostulante((Postulante) getSesion().getObjetoDeSesion(POSTULANTE));
            while((pregunta = ctrlPregunta.getPreguntaNoContestada(examen, (Postulante) getSesion().getObjetoDeSesion(POSTULANTE))) != null){
                if(!getCtrlDecision().guardarDecision(postulante, pregunta, null)) {
                    return ERROR;
                }
            }
            
            addActionMessage("Ha concluido el tiempo del examen.");
            return MENSAJE;
        }        
        getSesion().setObjetoDeSesion(TIEMPO_RESTANTE, tiempoRestante);
        
        ArrayList<Respuesta> lista = new ArrayList<Respuesta>();
        setPreguntasFaltantes(ctrlPregunta.getCantidadPreguntasNoContestadas(examen,(Postulante) getSesion().getObjetoDeSesion(POSTULANTE)));
        pregunta = ctrlPregunta.getPreguntaNoContestada(examen, (Postulante) getSesion().getObjetoDeSesion(POSTULANTE));

        if(pregunta == null) {
            addActionMessage("El examen ha concluido.");
            return MENSAJE;
        }
        setTema(pregunta.getTema());
        
        pregunta.getRespuestas().add(pregunta.getRespuestaCorrecta());
        
        Random r = new Random();

        r.setSeed(Calendar.getInstance().getTime().getTime());
        
        while(pregunta.getRespuestas().size() > 1) {
            
            int i = r.nextInt(pregunta.getRespuestas().size());
            
            Respuesta respuesta = pregunta.getRespuestas().get(i);
            respuesta.setTexto(respuesta.getTexto()+"<br/><br/>");
            lista.add(respuesta);
            
            pregunta.getRespuestas().remove(pregunta.getRespuestas().get(i));
        }
        
        lista.add(pregunta.getRespuestas().get(0));
        pregunta.setRespuestas(lista);
        
        getSesion().setObjetoDeSesion(PREGUNTA, pregunta);
        
        return OK;
    }
    
    public void prepareDoResponder() {
        
        Calendar horaActual = Calendar.getInstance();
        double tiempoRestante = (((Calendar) getSesion().getObjetoDeSesion(HORA_FIN)).getTime().getTime())-(horaActual.getTime().getTime());
        getSesion().setObjetoDeSesion(TIEMPO_RESTANTE, tiempoRestante);
        pregunta = (Pregunta) getSesion().getObjetoDeSesion(PREGUNTA);
        setPreguntasFaltantes(ctrlPregunta.getCantidadPreguntasNoContestadas(examen,(Postulante) getSesion().getObjetoDeSesion(POSTULANTE)));
        setTema(pregunta.getTema());
    }
       
    public String responder() {
        double tiempoRestante = (Double)getSesion().getObjetoDeSesion("tiempoRestante");
        if(tiempoRestante>0){
            if(seleccion==null){
                addActionError("Debe elegir una respuesta.");
                return "errorP";
            }
        }
        if(!getCtrlDecision().guardarDecision((Postulante) getSesion().getObjetoDeSesion(POSTULANTE), pregunta, seleccion)) {
            return ERROR;
        }
        
        return mostrarPregunta();
    }

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }

    public ICtrlPregunta getCtrlPregunta() {
        return ctrlPregunta;
    }

    public void setCtrlPregunta(ICtrlPregunta ctrlPregunta) {
        this.ctrlPregunta = ctrlPregunta;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public ICtrlPostulante getCtrlPostulante() {
        return ctrlPostulante;
    }

    public void setCtrlPostulante(ICtrlPostulante ctrlPostulante) {
        this.ctrlPostulante = ctrlPostulante;
    }

    public Postulante getPostulante() {
        return postulante;
    }

    public void setPostulante(Postulante postulante) {
        this.postulante = postulante;
    }

    public Long getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(Long seleccion) {
        this.seleccion = seleccion;
    }

    public ICtrlDecision getCtrlDecision() {
        return ctrlDecision;
    }

    public void setCtrlDecision(ICtrlDecision ctrlDecision) {
        this.ctrlDecision = ctrlDecision;
    }

    public int getPreguntasFaltantes() {
        return preguntasFaltantes;
    }

    public void setPreguntasFaltantes(int preguntasFaltantes) {
        this.preguntasFaltantes = preguntasFaltantes;
    }

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }

}

