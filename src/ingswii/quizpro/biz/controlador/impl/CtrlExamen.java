package ingswii.quizpro.biz.controlador.impl;

import ingswii.quizpro.accesoDatos.dao.IDaoExamen;
import ingswii.quizpro.biz.controlador.ICtrlDecision;
import ingswii.quizpro.biz.controlador.ICtrlExamen;
import ingswii.quizpro.biz.controlador.ICtrlPostulante;
import ingswii.quizpro.biz.controlador.ICtrlResultado;
import ingswii.quizpro.biz.controlador.ICtrlTema;
import ingswii.quizpro.biz.vo.ram.Convocatoria;
import ingswii.quizpro.biz.vo.ram.Decision;
import ingswii.quizpro.biz.vo.ram.Examen;
import ingswii.quizpro.biz.vo.ram.Ip;
import ingswii.quizpro.biz.vo.ram.Postulante;
import ingswii.quizpro.biz.vo.ram.Resultado;
import ingswii.quizpro.biz.vo.ram.Tema;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alejandro
 */
public class CtrlExamen implements ICtrlExamen {
    
    private IDaoExamen daoExamen;
    private ICtrlDecision ctrlDecision;
    private ICtrlTema ctrlTema;
    private ICtrlResultado ctrlResultado;
    private ICtrlPostulante ctrlPostulante;

    public List<Examen> obtenerExamenes() {
        return daoExamen.buscarTodos();
    }

    public Examen obtenerExamenPorId(Long id) {
        return daoExamen.buscarPorId(id);
    }

    public List<Examen> buscarExamenes(Examen examen) {
        
        if(examen == null) {
            return daoExamen.buscarTodos();
        }
        
        return daoExamen.buscar(examen);
    }

    public boolean guardarExamen(Examen examen) {
        
        if(examen == null) {
            return false;
        }
        
        if(examen.getIps() != null) {
        
            List<Ip> lista = new ArrayList<Ip>();

            for(Ip g: examen.getIps()) {
                if(!lista.contains(g)) {
                    lista.add(g);
                }
            }
        
            examen.setIps(lista);
        }
        
        return daoExamen.guardarOActualizarPorId(examen);
    }

    public boolean eliminarExamenPorId(Examen examen) {
        return daoExamen.eliminarPorId(examen.getId());
    }
    
    public boolean calificar(Convocatoria convocatoria) {
        
        if(!ctrlResultado.eliminarPorExamen(convocatoria.getExamen())) {
            return false;
        }
        
        List<Tema> temas = ctrlTema.obtenerTemasPorExamen(convocatoria.getExamen());
            
        for(Postulante postulante : convocatoria.getPostulantes()) {
            
            Double totalPonderacionTemas = 0.0;
            Double puntajeFinal = 0.0;
            
            for(Tema tema : temas) {
                
                totalPonderacionTemas += tema.getPonderacion();
                
                Resultado resultado = new Resultado();
                List<Decision> decisiones = ctrlDecision.obtenerPorPostulanteYTema(postulante, tema);
                Double totalPonderacionPreguntas = 0.0;
                Double puntaje = 0.0;
                
                resultado.setId(null);
                resultado.setExamen(convocatoria.getExamen());
                resultado.setTema(tema);
                resultado.setPostulante(postulante);
                
                if(decisiones != null) {
                    
                    for(Decision decision : decisiones) {

                        totalPonderacionPreguntas += decision.getPregunta().getPonderacion();

                        if(decision != null && decision.getRespuesta() != null && decision.getRespuesta().getId() != null && decision.getRespuesta().getId().equals(decision.getPregunta().getRespuestaCorrecta().getId())) {
                            puntaje += 0.0 + decision.getPregunta().getPonderacion();
                        }
                    }

                    resultado.setPuntaje(puntaje / totalPonderacionPreguntas * 100);
                    puntajeFinal += resultado.getPuntaje() * tema.getPonderacion();
                    
                }
                else {
                    resultado.setPuntaje(0.0);
                }
                
                if(!ctrlResultado.guardarResultado(resultado)) {
                    return false;
                }
            }
        
            Resultado resultado = new Resultado();
            resultado.setId(null);
            resultado.setExamen(convocatoria.getExamen());
            resultado.setTema(null); // no se asigna tema para identificarlo como el resultado final (total)
            resultado.setPostulante(postulante);
            resultado.setPuntaje(puntajeFinal / totalPonderacionTemas);
            
            if(!ctrlResultado.guardarResultado(resultado)) {
                return false;
            }

        }
        
        return true;
    }

    public boolean clasificar(Convocatoria convocatoria) {
        
        List<Resultado> resultados = ctrlResultado.obtenerPrimerosResultados(convocatoria.getExamen(), convocatoria.getPostulantes().size());
        
        for(Resultado resultado : resultados) {
            
            Postulante postulante = resultado.getPostulante();
            postulante.setClasificado(false);
            
            if(!ctrlPostulante.guardarPostulante(postulante)) {
                return false;
            }
        }
        
        resultados = ctrlResultado.obtenerPrimerosResultados(convocatoria.getExamen(), convocatoria.getVacantes());
        
        for(Resultado resultado : resultados) {
            
            if(resultado.getPuntaje() > 0.0) {
                Postulante postulante = resultado.getPostulante();

                postulante.setClasificado(true);

                if(!ctrlPostulante.guardarPostulante(postulante)) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    public IDaoExamen getDaoExamen() {
        return daoExamen;
    }

    public void setDaoExamen(IDaoExamen daoExamen) {
        this.daoExamen = daoExamen;
    }

    public ICtrlDecision getCtrlDecision() {
        return ctrlDecision;
    }

    public void setCtrlDecision(ICtrlDecision ctrlDecision) {
        this.ctrlDecision = ctrlDecision;
    }

    public ICtrlTema getCtrlTema() {
        return ctrlTema;
    }

    public void setCtrlTema(ICtrlTema ctrlTema) {
        this.ctrlTema = ctrlTema;
    }

    public ICtrlResultado getCtrlResultado() {
        return ctrlResultado;
    }

    public void setCtrlResultado(ICtrlResultado ctrlResultado) {
        this.ctrlResultado = ctrlResultado;
    }

    public ICtrlPostulante getCtrlPostulante() {
        return ctrlPostulante;
    }

    public void setCtrlPostulante(ICtrlPostulante ctrlPostulante) {
        this.ctrlPostulante = ctrlPostulante;
    }

}
