package ingswii.quizpro.accesoDatos.dao;

import ingswii.quizpro.biz.vo.ugs.Usuario;
import java.util.List;

/**
 * DAO para los VOs de tipo Usuario.
 * @author Alejandro
 */
public interface IDaoUsuario extends IAbstractDao<Usuario> {
   
    Usuario buscarPorLoginYPassword(String login, String password);
    
    List<Usuario> buscar(Usuario usuario);
    
    Usuario buscarPorLogin(String login);

}
