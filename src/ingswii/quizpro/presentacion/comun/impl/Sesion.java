package ingswii.quizpro.presentacion.comun.impl;

import ingswii.quizpro.presentacion.comun.*;
import org.apache.struts2.ServletActionContext;

/**
 * @author Alejandro
 */
public class Sesion implements ISesion {
    
    public Object getObjetoDeSesion(String nombreDelObjeto) {
        
        if(ServletActionContext.getRequest() == null
                || ServletActionContext.getRequest().getSession() == null)
        {
            return null;
        }
        
        return ServletActionContext.getRequest().getSession().getAttribute(nombreDelObjeto);
    }
    
    public void setObjetoDeSesion(String nombreDelObjeto, Object objeto) {
        
        if(ServletActionContext.getRequest() == null
                || ServletActionContext.getRequest().getSession() == null)
        {
            return;
        }
        
        ServletActionContext.getRequest().getSession().setAttribute(nombreDelObjeto, objeto);
    }
    
    public void eliminarObjetoDeSesion(String nombreDelObjeto) {
        ServletActionContext.getRequest().getSession().removeAttribute(nombreDelObjeto);
    }

    public String getContextPath() {
        return ServletActionContext.getRequest().getContextPath();
    }

}
