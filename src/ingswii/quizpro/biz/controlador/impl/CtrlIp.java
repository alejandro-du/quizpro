package ingswii.quizpro.biz.controlador.impl;

import ingswii.quizpro.biz.controlador.*;
import ingswii.quizpro.accesoDatos.dao.IDaoIp;
import ingswii.quizpro.biz.vo.ram.Ip;
import java.util.List;

/**
 * 
 * @author Alejandro
 */
public class CtrlIp implements ICtrlIp {
    
    private IDaoIp daoIp;
    private ICtrlServicio ctrlServicio;

    public List<Ip> obtenerIps() {
        return daoIp.buscarTodos();
    }

    public IDaoIp getDaoIp() {
        return daoIp;
    }

    public void setDaoIp(IDaoIp daoIp) {
        this.daoIp = daoIp;
    }

    public ICtrlServicio getCtrlServicio() {
        return ctrlServicio;
    }

    public void setCtrlServicio(ICtrlServicio ctrlServicio) {
        this.ctrlServicio = ctrlServicio;
    }

    public Ip obtenerIpPorId(Long id) {
        return daoIp.buscarPorId(id);
    }

    public List<Ip> buscarIps(Ip ip) {
        if(ip == null) {
            return daoIp.buscarTodos();
        }
        
        return daoIp.buscar(ip);
    }

    public boolean guardarIp(Ip ip) {
        
        if(ip == null) {
            return false;
        }
        
        return daoIp.guardarOActualizarPorId(ip);
    }

    public boolean eliminarIpPorId(Ip ip) {
        return daoIp.eliminarPorId(ip.getId());
    }

}
