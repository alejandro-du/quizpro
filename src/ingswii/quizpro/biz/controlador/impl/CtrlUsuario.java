package ingswii.quizpro.biz.controlador.impl;

import ingswii.quizpro.accesoDatos.dao.IDaoUsuario;
import ingswii.quizpro.biz.controlador.ICtrlUsuario;
import ingswii.quizpro.biz.vo.ugs.Grupo;
import ingswii.quizpro.biz.vo.ugs.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alejandro
 */
public class CtrlUsuario implements ICtrlUsuario {
    
    private IDaoUsuario daoUsuario;

    public Usuario buscarUsuario(String login, String password) {
        return daoUsuario.buscarPorLoginYPassword(login, password);
    }

    public IDaoUsuario getDaoUsuario() {
        return daoUsuario;
    }

    public void setDaoUsuario(IDaoUsuario daoUsuario) {
        this.daoUsuario = daoUsuario;
    }

    public List<Usuario> buscarUsuarios(Usuario usuario) {
        
        if(usuario == null) {
            return daoUsuario.buscarTodos();
        }
        
        return daoUsuario.buscar(usuario);
    }
    
    public boolean guardarUsuario(Usuario usuario) {
        
        Usuario usu = daoUsuario.buscarPorLogin(usuario.getLogin());
        
        if(usu == null) {
            return false;
        }
        
        if(usuario.getPassword().equals("")) {
            usuario.setPassword(usu.getPassword());
        }
        
        if(usuario.getGrupos() != null) {
        
            List<Grupo> lista = new ArrayList<Grupo>();

            for(Grupo g: usuario.getGrupos()) {
                if(!lista.contains(g)) {
                    lista.add(g);
                }
            }
        
            usuario.setGrupos(lista);
        }
        
        return daoUsuario.guardarOActualizarPorId(usuario);
    }

    public boolean eliminarUsuarioPorId(Usuario usuario) {
        return daoUsuario.eliminarPorId(usuario.getId());
    }

    public Usuario buscarUsuarioPorId(Long id) {
        return daoUsuario.buscarPorId(id);
    }

    public boolean crearUsuario(Usuario usuario) {
        
        if(daoUsuario.buscarPorLogin(usuario.getLogin()) != null) { // ya existe?
            return false;
        }

        if(usuario.getGrupos() != null) {
        
            List<Grupo> lista = new ArrayList<Grupo>();

            for(Grupo g: usuario.getGrupos()) {
                if(!lista.contains(g)) {
                    lista.add(g);
                }
            }
        
            usuario.setGrupos(lista);
        }
        
        return daoUsuario.guardarOActualizarPorId(usuario);
    }
}
