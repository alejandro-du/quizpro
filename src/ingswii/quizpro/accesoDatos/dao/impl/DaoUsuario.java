package ingswii.quizpro.accesoDatos.dao.impl;

import ingswii.quizpro.accesoDatos.dao.*;
import ingswii.quizpro.biz.vo.ugs.Usuario;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Alejandro
 */
public class DaoUsuario extends AbstractDao<Usuario> implements IDaoUsuario {
    
    public Usuario buscarPorLoginYPassword(String login, String password) {

        Query query = getEntityManager().createQuery("SELECT u FROM " + Usuario.class.getSimpleName() + " u WHERE u.login = :login AND u.password = :password");
        query.setParameter("login", login);
        query.setParameter("password", password);
        
        List<Usuario> lista = ejecutarSelect(query);
        
        if(lista == null || lista.size() <= 0) {
            return null;
        }
        
        return lista.get(0);
    }

    public List<Usuario> buscar(Usuario usuario) {
        
        String strQuery = "SELECT u FROM " + Usuario.class.getSimpleName() + " u WHERE (u.nombre LIKE :nombre) AND (u.documento LIKE :documento) AND (u.login LIKE :login)";
        Query query = getEntityManager().createQuery(strQuery);
        query.setParameter("nombre", "%" + usuario.getNombre() + "%");
        query.setParameter("documento", "%" +  usuario.getDocumento() + "%");
        query.setParameter("login", "%" + usuario.getLogin() + "%");
        List<Usuario> lista = ejecutarSelect(query);
        
        if(lista == null || lista.size() <= 0) {
            return null;
        }
        
        return lista;
    }

    public Usuario buscarPorLogin(String login) {
        Query query = getEntityManager().createQuery("SELECT u FROM " + Usuario.class.getSimpleName() + " u WHERE u.login = :login");
        query.setParameter("login", login);
        
        List<Usuario> lista = ejecutarSelect(query);
        
        if(lista == null || lista.size() <= 0) {
            return null;
        }
        
        return lista.get(0);
    }

}
