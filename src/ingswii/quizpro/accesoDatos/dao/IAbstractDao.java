package ingswii.quizpro.accesoDatos.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 * Interfaz para DAO Abstracto que facilita la creación de DAOs.
 * @author Alejandro
 * @param T Nombre de la clase VO.
 */
public interface IAbstractDao<T> {

    /**
     * Obtiene todos los VOs en la BD.
     * @return List con todos los VOs en la BD.
     */
    List<T> buscarTodos();

    /**
     * Obtiene un VO por id.
     * @param id Id del VO que se desea buscar.
     * @return VO con el id especificado.
     */
    T buscarPorId(Long id);
    
    /**
     * Obtiene el resultado del Query especificado por medio de query.getResultList().
     * Útil para ejecutar sentencias "SELECT".
     * @param query Query a ejecutar.
     * @return Lista con el resultado de la ejecución del Query.
     */
    List<T> ejecutarSelect(Query query);

    /**
     * Ejectuta el Query especificado por medio de query.executeUpdate().
     * Útil para realizar sentencias "UPDATE".
     * @param query Query a ejecutar.
     */
    boolean ejecutarUpdate(Query query, EntityManager em);

    /**
     * Elimina el VO con el id especificado.
     * @param id Id del VO a eliminar.
     */
     boolean eliminarPorId(Long id);

    /**
     * Obtiene un EntityManager.
     * Útil si algún DAO debe realizar alguna consulta que no se incluya en esta clase.
     * @return EntityManager.
     */
    EntityManager getEntityManager();

    /**
     * Obtiene el EntityManagerFactory.
     * @return EntityManagerFactory del proyecto.
     */
    EntityManagerFactory getEntityManagerFactory();

    /**
     * Guarda o actualiza el VO con el id especificado.
     * Si el id corresponde a algún VO existente, éste se actualiza.
     * @param vo VO a guardar o actualizar.
     */
    boolean guardarOActualizarPorId(Object vo);
    
    /**
     * Obtiene el nombre del tipo parámetro T.
     * @return Nombre de la clase correspondiente a T.
     */
    String getNombreT();

}
