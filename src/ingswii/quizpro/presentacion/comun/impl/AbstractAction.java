package ingswii.quizpro.presentacion.comun.impl;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import ingswii.quizpro.presentacion.comun.ISesion;

/**
 * Proporciona funcionalidad común para los actions.
 * @author Alejandro
 */
public class AbstractAction extends ActionSupport implements Preparable {
    
    public static String OK = "ok";
    public static String INDEX = "index";
    public static String ERROR = "error";
    public static String MENSAJE = "mensaje";
    public static String INFO = "info";

    private ISesion sesion;
    
    public void prepare() throws Exception {
        clearErrorsAndMessages();
    }

    public ISesion getSesion() {
        return sesion;
    }

    public void setSesion(ISesion sesion) {
        this.sesion = sesion;
    }

}
