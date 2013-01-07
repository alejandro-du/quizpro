package ingswii.quizpro.presentacion.comun;

/**
 * Permite agregar, modificar, eliminar y obtener objetos (Object) en la sesión del usuario.
 * Cada usuario (autenticado o no) tiene una sesión en la cual se pueden guardar datos.
 * @author Alejandro
 */
public interface ISesion {

    /**
     * Usuario autenticado de la sesión. Si no existe es porque no ha ocurrido la autenticación.
     */
    static String USUARIO_AUTENTICADO = "usuario";
    
    /**
     * Convocatoria seleccionada.
     */
    static String CONVOCATORIA = "convocatoria";
    
    /**
     * Servicio en ejecución.
     */
    static String SERVICIO = "servicio";
    
    /**
     * Javasript con el MenuDeUsuario que se debe presentar.
     */
    static String JAVASCRIPT_MENU = "javascriptMenu";

    /**
     * Elimina o remueve el objeto con el nombre especificado.
     * @param nombreDelObjeto
     * Nombre (key) del objeto que se desea eliminar de la sesión.
     */
    void eliminarObjetoDeSesion(String nombreDelObjeto);

    /**
     * Obtiene el objeto con el nombre especificado.
     * @param nombreDelObjeto
     * Nombre (key) del objeto que se desea obtener de la sesión. Por ejemplo, para obtener al usuario autenticado,
     * use getObjetoDeSesion(ISesion.USUARIO_AUTENTICADO).
     * @return
     * Objeto (Object) con el nombre especificado.
     */
    Object getObjetoDeSesion(String nombreDelObjeto);

    /**
     * Establece o guarda el objeto con el nombre especificado. Si ya existe, se reemplaza.
     * @param nombreDelObjeto
     * Nombre (key) del objeto que se desea establecer o guardar en la sesión.
     * @param objeto
     * Objeto a establecer o a guardar en la sesión.
     */
    void setObjetoDeSesion(String nombreDelObjeto, Object objeto);
    
    /**
     * Obtiene el nombre del context (por ejemplo "/quizpro" o "/webapp").
     * @return Nombre del context.
     */
    String getContextPath();
    
}
