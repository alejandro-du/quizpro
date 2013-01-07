package ingswii.quizpro.presentacion.servicios.administrarPreguntas;

import com.opensymphony.xwork2.validator.annotations.ExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validation;
import com.opensymphony.xwork2.validator.annotations.Validations;
import ingswii.quizpro.biz.controlador.ICtrlExamen;
import ingswii.quizpro.biz.controlador.ICtrlTema;
import ingswii.quizpro.biz.controlador.ICtrlPregunta;
import ingswii.quizpro.biz.vo.ram.Examen;
import ingswii.quizpro.biz.vo.ram.Tema;
import ingswii.quizpro.biz.vo.ram.Pregunta;
import ingswii.quizpro.biz.vo.ram.Respuesta;
import ingswii.quizpro.presentacion.comun.impl.AbstractCrud;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.struts2.interceptor.validation.SkipValidation;


/**
 * CRUD de Grupos.
 * @author Alejandro
 */
@Validation
public class ActionAdministrarPreguntas extends AbstractCrud {
    
    private Pregunta pregunta; // VO
    private String respuesta1; // respuesta correcta
    private String respuesta2;
    private String respuesta3;
    private String respuesta4;
    private String respuesta5;
    private List<Tema> listaTemas; // lista de todos los temas
    private List<Examen> listaExamenes; // lista de todos los examenes
    
    private ICtrlPregunta ctrlPregunta;
    private ICtrlTema ctrlTema;
    private ICtrlExamen ctrlExamen;
    
    @Override
    public void prepare() {
        try {
            super.prepare();
            setListaTemas(getCtrlTema().obtenerTemas());
            setListaExamenes(getCtrlExamen().obtenerExamenes());
        } catch (Exception ex) {
            Logger.getLogger(ActionAdministrarPreguntas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @SkipValidation
    public String formulario() {
        
        if(getAccion().equals(ACCION_MODIFICAR)) {
            setPregunta(getCtrlPregunta().obtenerPreguntaPorId(getId()));
            setRespuesta1(pregunta.getRespuestaCorrecta().getTexto());
            setRespuesta2(pregunta.getRespuestas().get(0).getTexto());
            setRespuesta3(pregunta.getRespuestas().get(1).getTexto());
            setRespuesta4(pregunta.getRespuestas().get(2).getTexto());
            setRespuesta5(pregunta.getRespuestas().get(3).getTexto());
    
        }
        else {
            setPregunta(null);
        }
        
        setListaTemas(getCtrlTema().obtenerTemas());
        setListaExamenes(getCtrlExamen().obtenerExamenes());
        return FORMULARIO;
    }
    
    @SkipValidation
    public String buscar() {

        setLista(getCtrlPregunta().buscarPreguntas(getPregunta()));
        setAccion("");
        return LISTA;
    }

    @SkipValidation
    public String ver() {
        setPregunta(getCtrlPregunta().obtenerPreguntaPorId(getId()));
        setAccion(ACCION_VER);
        return FORMULARIO;
    }

    @SkipValidation
    public void pupularPregunta() {
        
        List<Respuesta> lista = new ArrayList<Respuesta>();
        Respuesta respuesta;
        
        respuesta = new Respuesta();
        respuesta.setTexto(removerMarcadoresParrafo(getRespuesta1()));
        pregunta.setRespuestaCorrecta(respuesta);
        
        respuesta = new Respuesta();
        respuesta.setTexto(removerMarcadoresParrafo(getRespuesta2()));
        lista.add(respuesta);
        
        respuesta = new Respuesta();
        respuesta.setTexto(removerMarcadoresParrafo(getRespuesta3()));
        lista.add(respuesta);
        
        respuesta = new Respuesta();
        respuesta.setTexto(removerMarcadoresParrafo(getRespuesta4()));
        lista.add(respuesta);
        
        respuesta = new Respuesta();
        respuesta.setTexto(removerMarcadoresParrafo(getRespuesta5()));
        lista.add(respuesta);
        
        pregunta.setRespuestas(lista);
    }

    public String removerMarcadoresParrafo(String cadena){
        if (cadena.startsWith("<p>") && cadena.endsWith("</p>")){
            cadena = cadena.substring(3, cadena.length()-4);
        }
        return cadena;
    } 
    
    public void prepareDoCrear() {
        setAccion(ACCION_CREAR);
    }
    
    @Validations (
        requiredStrings={
            @RequiredStringValidator(fieldName="pregunta.texto", message="Debe especificar un texto."),
            @RequiredStringValidator(fieldName="respuesta1", message="Debe especificar una respuesta correcta."),
            @RequiredStringValidator(fieldName="respuesta2", message="Debe especificar una respuesta incorrecta."),
            @RequiredStringValidator(fieldName="respuesta3", message="Debe especificar una respuesta incorrecta."),
            @RequiredStringValidator(fieldName="respuesta4", message="Debe especificar una respuesta incorrecta."),
            @RequiredStringValidator(fieldName="respuesta5", message="Debe especificar una respuesta incorrecta.")
        },
        requiredFields={
            @RequiredFieldValidator(fieldName="pregunta.examen.id", message="Debe especificar un examen."),
            @RequiredFieldValidator(fieldName="pregunta.ponderacion", message="Debe especificar una ponderación."),
            @RequiredFieldValidator(fieldName="pregunta.tema.id", message="Debe especificar un tema.")
        },
        expressions={
            @ExpressionValidator(expression="pregunta.getTexto().equals('') || !pregunta.getTexto().trim().equals('')",
                                 message="El texto de la pregunta no es válido."),
            @ExpressionValidator(expression="respuesta1.equals('') || !respuesta1.trim().equals('')",
                                 message="El texto de la respuesta 1 no es válido."),
            @ExpressionValidator(expression="respuesta2.equals('') || !respuesta2.trim().equals('')",
                                 message="El texto de la respuesta 2 no es válido."),
            @ExpressionValidator(expression="respuesta3.equals('') || !respuesta3.trim().equals('')",
                                 message="El texto de la respuesta 3 no es válido."),
            @ExpressionValidator(expression="respuesta4.equals('') || !respuesta4.trim().equals('')",
                                 message="El texto de la respuesta 4 no es válido."),
            @ExpressionValidator(expression="respuesta5.equals('') || !respuesta5.trim().equals('')",
                                 message="El texto de la respuesta 5 no es válido.")
        }
    )
    public String crear() {
        
        pupularPregunta();
        
        if(!ctrlPregunta.guardarPregunta(pregunta)) {
            addActionError("No se puede crear la pregunta.");
            return ERROR;
        }
        
        addActionMessage("Nueva pregunta creada.");
        setPregunta(null);
        setRespuesta1(null);
        setRespuesta2(null);
        setRespuesta3(null);
        setRespuesta4(null);
        setRespuesta5(null);
        return FORMULARIO;
    }
    
    public void prepareDoModificar() {
        setAccion(ACCION_MODIFICAR);
    }
    
    public String modificar() {
        
        pupularPregunta();
        
        if(!ctrlPregunta.guardarPregunta(pregunta)) {
            addActionError("No se puede actualizar la pregunta.");
            return ERROR;
        }
        
        addActionMessage("Pregunta modificada.");
        setPregunta(null);
        setRespuesta1(null);
        setRespuesta2(null);
        setRespuesta3(null);
        setRespuesta4(null);
        setRespuesta5(null);
        setAccion(ACCION_BUSCAR);
        return FORMULARIO;
    }

    @SkipValidation
    public String eliminar() {
        
        pregunta = ctrlPregunta.obtenerPreguntaPorId(getId());
        
        if(!ctrlPregunta.eliminarPreguntaPorId(pregunta)) {
            addActionError("No se puede eliminar la pregunta.");
            return ERROR;
        }
        
        pregunta = null;
        addActionMessage("Pregunta eliminada.");
        setAccion(ACCION_BUSCAR);
        return FORMULARIO;
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

    public List<Tema> getListaTemas() {
        return listaTemas;
    }

    public void setListaTemas(List<Tema> listaTemas) {
        this.listaTemas = listaTemas;
    }

    public ICtrlTema getCtrlTema() {
        return ctrlTema;
    }

    public void setCtrlTema(ICtrlTema ctrlTema) {
        this.ctrlTema = ctrlTema;
    }

    public List<Examen> getListaExamenes() {
        return listaExamenes;
    }

    public void setListaExamenes(List<Examen> listaExamenes) {
        this.listaExamenes = listaExamenes;
    }

    public ICtrlExamen getCtrlExamen() {
        return ctrlExamen;
    }

    public void setCtrlExamen(ICtrlExamen ctrlExamen) {
        this.ctrlExamen = ctrlExamen;
    }

    public String getRespuesta1() {
        return respuesta1;
    }

    public void setRespuesta1(String respuesta1) {
        this.respuesta1 = respuesta1;
    }

    public String getRespuesta2() {
        return respuesta2;
    }

    public void setRespuesta2(String respuesta2) {
        this.respuesta2 = respuesta2;
    }

    public String getRespuesta3() {
        return respuesta3;
    }

    public void setRespuesta3(String respuesta3) {
        this.respuesta3 = respuesta3;
    }

    public String getRespuesta4() {
        return respuesta4;
    }

    public void setRespuesta4(String respuesta4) {
        this.respuesta4 = respuesta4;
    }

    public String getRespuesta5() {
        return respuesta5;
    }

    public void setRespuesta5(String respuesta5) {
        this.respuesta5 = respuesta5;
    }

}
