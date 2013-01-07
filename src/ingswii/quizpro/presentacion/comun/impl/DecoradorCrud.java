package ingswii.quizpro.presentacion.comun.impl;

import ingswii.quizpro.biz.vo.ugs.Servicio;
import ingswii.quizpro.presentacion.comun.ISesion;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.displaytag.decorator.TableDecorator;

/**
 * Decorador (displaytag) para CRUDs.
 * @author Alejandro
 */
public class DecoradorCrud extends TableDecorator {

    /**
     * Construye las opciones del CRUD.
     * @return Cadena con el html correspondiente a las opciones.
     */
    public String getOpciones() {
        
        Long id = null;
                
        try {

            Object rowObject = getCurrentRowObject();
            id = (Long) rowObject.getClass().getMethod("getId").invoke(rowObject);

        } catch (Exception ex) {
            Logger.getLogger(DecoradorCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Servicio servicio = (Servicio) getPageContext().getSession().getAttribute(ISesion.SERVICIO);
        String paquete = servicio.getUrl();
        
        if(paquete.endsWith("/")) {
            paquete = paquete.substring(0, paquete.length() -1 );
        }
        
        paquete = paquete.substring(paquete.lastIndexOf("/") + 1, paquete.length());
        
        
        return "<a href=\"ver_" + paquete + ".action?id=" + id + "\">V</a> | " + "<a href=\"formulario_" + paquete + ".action?accion=modificar&id=" + id + "\">M</a> | " + "<a href=\"eliminar_" + paquete + ".action?id=" + id + "\">X</a>";
    }

}
