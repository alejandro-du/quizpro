package ingswii.quizpro.presentacion.comun.impl;

import ingswii.quizpro.accesoDatos.dao.impl.DaoServicio;
import ingswii.quizpro.biz.controlador.ICtrlServicio;
import ingswii.quizpro.biz.controlador.impl.CtrlServicio;
import ingswii.quizpro.biz.vo.ugs.Servicio;
import ingswii.quizpro.biz.vo.ugs.Usuario;
import ingswii.quizpro.presentacion.comun.ISesion;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Filtro para verificar el acceso a las urls solicitadas (requests).
 * @author Alejandro
 */
public class FiltroAutenticacion implements Filter {
    
    ICtrlServicio ctrlServicio;
    String error;
    
    public void init(FilterConfig filterConfig) throws ServletException {
        
        // TODO: Corregir acoplamiento. Cargar bean de Spring.
        ctrlServicio = new CtrlServicio();
        ((CtrlServicio)ctrlServicio).setDaoServicio(new DaoServicio());
        error = "/comun/error.jsp";
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String urlSolicitada = ((HttpServletRequest) servletRequest).getRequestURL().toString();
        String webapp = ((HttpServletRequest) servletRequest).getContextPath().toString();
        HttpSession sesion = ((HttpServletRequest) servletRequest).getSession();
        Usuario usuarioSesion = (Usuario) sesion.getAttribute(ISesion.USUARIO_AUTENTICADO);
        Servicio servicioSesion = (Servicio) sesion.getAttribute(ISesion.SERVICIO);
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        
        // Si no se especifica el encoding, le indicamos que use el utf-8. De lo contrario, usaría el iso-8859-1
        if(servletRequest.getCharacterEncoding()==null) {
            servletRequest.setCharacterEncoding("utf-8");
	}
        
        // obtener url solicitada
        urlSolicitada = urlSolicitada.substring(urlSolicitada.indexOf(webapp) + webapp.length());
        
        // obtener paquete de la url solicidata
        String paqueteSolicitado;
        paqueteSolicitado = urlSolicitada.substring(0, urlSolicitada.lastIndexOf("/"));
        
        if(paqueteSolicitado.lastIndexOf("/", paqueteSolicitado.length()) >= 0)
            paqueteSolicitado = paqueteSolicitado.substring(paqueteSolicitado.lastIndexOf("/", paqueteSolicitado.length()));
        
        if(urlSolicitada.equals(error)) { // si es la página de error, dejar pasar
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        
        // obtener el parámetro id (único parámetro que se tiene en cuenta para validar urls)
        String id = httpServletRequest.getParameter("id");
        
        if(id != null) {
            urlSolicitada += "?id=" + id;
        }
        
        Servicio servicioSolicitado = ctrlServicio.obtenerServicioPorUrl(urlSolicitada);
        
        // verificar si ya se seleccionó una convocatoria
        if(sesion.getAttribute(ISesion.CONVOCATORIA) == null) {
            if(servicioSolicitado != null && servicioSolicitado.getRequiereConvocatoria()) {
                httpServletResponse.sendRedirect(webapp);
                return;
            }
        }
        
        // el usuario de la sesion puede acceder al servicio solicitado?
        if(ctrlServicio.usuarioPuedeAcceder(usuarioSesion, servicioSolicitado)) {
            // dejar pasar
            log("Usuario entrando a servicio", usuarioSesion, servicioSolicitado, urlSolicitada);
            sesion.setAttribute(ISesion.SERVICIO, ctrlServicio.obtenerServicioPorUrl(urlSolicitada));
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        
        if(servicioSolicitado != null) {
            // la url solicitada es de un servicio al cual el usuario no tiene acceso
            log("El usuario no tiene permiso para acceder al servicio", usuarioSesion, servicioSolicitado, urlSolicitada);
            httpServletResponse.sendRedirect(webapp + error);
            return;
        }

        // ya se está ejecutando un servicio en la sesion?
        if(servicioSesion != null) {
            
            // no tiene permiso el usuario para el servicio de la sesion?
            if(!ctrlServicio.usuarioPuedeAcceder(usuarioSesion, servicioSesion)) {
                // el usuario está accediendo a un servicio nuevo, guardar el nuevo servicio en la sesión.
                log("El usuario no tiene permiso para acceder al servicio de la sesión", usuarioSesion, servicioSolicitado, urlSolicitada);
                httpServletResponse.sendRedirect(webapp + error);
                return;
            }
            
            // obtener paquete del servicio de la sesion
            String paqueteSesion;
            paqueteSesion = servicioSesion.getUrl().substring(0, servicioSesion.getUrl().lastIndexOf("/"));
            
            if(paqueteSesion.lastIndexOf("/", paqueteSesion.length()) >= 0)
                paqueteSesion = paqueteSesion.substring(paqueteSesion.lastIndexOf("/", paqueteSesion.length()));

            // no coinciden los paquetes?
            if(!paqueteSesion.equals(paqueteSolicitado)) {

                // los paquetes no son iguales y el usuario no tiene acceso a la url solicitada
                log("Package solicitado no coincide con el del servicio de la sesión", usuarioSesion, servicioSolicitado, urlSolicitada);
                httpServletResponse.sendRedirect(webapp + error);
                return;

            }
            
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        
        log("Error ejecutando el filtro", usuarioSesion, servicioSolicitado, urlSolicitada);
        sesion.removeAttribute(ISesion.JAVASCRIPT_MENU);
        sesion.removeAttribute(ISesion.SERVICIO);
        sesion.removeAttribute(ISesion.USUARIO_AUTENTICADO);
        sesion.removeAttribute(ISesion.CONVOCATORIA);
        httpServletResponse.sendRedirect(webapp + error);
    }
    
    private void log(String mensaje, Usuario usuario, Servicio servicioSolicitado, String urlSolicitada) {
        
        String str = "FiltroAutenticacion: " + mensaje;
        
        if(usuario != null) {
            str += " (Usuario: " + usuario.getNombre() + ")";
        }
        
        if(servicioSolicitado != null) {
            str += " (Servicio: " + servicioSolicitado.getNombre() + ")";
        }
        
        str += " (Url: " + urlSolicitada + ")";
        
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, str);
    }
    
}
