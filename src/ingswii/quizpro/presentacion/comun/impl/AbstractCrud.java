package ingswii.quizpro.presentacion.comun.impl;

import ingswii.quizpro.biz.vo.ugs.Servicio;
import ingswii.quizpro.presentacion.comun.ISesion;
import java.util.List;


/**
 * Interface que deben implementar los CRUDs.
 * @author Alejandro
 */
public abstract class AbstractCrud extends AbstractAction {
    
    // tipos de formulario
    public static final String ACCION_BUSCAR = "buscar";
    public static final String ACCION_CREAR = "crear";
    public static final String ACCION_VER = "ver";
    public static final String ACCION_MODIFICAR = "modificar";
    
    // posibles resultados del action
    public static final String FORMULARIO = "formulario";
    public static final String LISTA = "lista";

    private String accion; // acción CRUD a realizar
    private List lista; // lista con el resultado de la búsqueda
    private Long id; // id del elemento a ver, modificar o eliminar desde lista

    /**
     * Redirecciona al formulario.
     * @return result correspondiente al formulario.
     */
    public abstract String formulario();
    
    /**
     * Realiza la búsqueda de registros y coloca la información en el campo lista.
     * @return result: FORMULARIO O LISTA.
     */
    public abstract String buscar();
    
    /**
     * Busca el VO con el id especificado en el campo id de esta clase.
     * @return result: FORMULARIO O LISTA.
     */
    public abstract String ver();
    
    /**
     * Crea un nuevo VO.
     * @return result: FORMULARIO O LISTA.
     */
    public abstract String crear();
    
    /**
     * Modifica un VO.
     * @return restult: FORMULARIO O LISTA.
     */
    public abstract String modificar();
    
    /**
     * Elimina el VO con el id indicado en el campo id de esta clase.
     * @return result: FORMULARIO O LISTA.
     */
    public abstract String eliminar();
    
    public String getPaquete() {
        
        Servicio servicio = (Servicio) getSesion().getObjetoDeSesion(ISesion.SERVICIO);
        String paquete = servicio.getUrl();
        
        if(paquete.endsWith("/")) {
            paquete = paquete.substring(0, paquete.length() -1 );
        }
        
        return paquete.substring(paquete.lastIndexOf("/") + 1, paquete.length());
    }

    public List getLista() {
        return lista;
    }

    public void setLista(List lista) {
        this.lista = lista;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    
}
