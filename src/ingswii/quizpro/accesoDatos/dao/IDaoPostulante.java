/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ingswii.quizpro.accesoDatos.dao;

import ingswii.quizpro.biz.vo.ram.Postulante;
import java.util.List;

/**
 *
 * @author Jorge
 */
public interface IDaoPostulante extends IAbstractDao<Postulante> {
    
    public Postulante buscarPorDocumento(String documento);
    
    public List<Postulante> buscar(Postulante postulante);
}
