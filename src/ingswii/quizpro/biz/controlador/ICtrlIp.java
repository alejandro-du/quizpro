package ingswii.quizpro.biz.controlador;

import ingswii.quizpro.biz.vo.ram.Ip;
import java.util.List;

/**
 * Proporciona funcionalidad para los VOs Ip.
 * @author Alejandro
 */
public interface ICtrlIp {
    
    List<Ip> obtenerIps();
    
    Ip obtenerIpPorId(Long id);
    
    List<Ip> buscarIps(Ip ip);
    
    boolean guardarIp(Ip ip);
    
    boolean eliminarIpPorId(Ip ip);
    
}
