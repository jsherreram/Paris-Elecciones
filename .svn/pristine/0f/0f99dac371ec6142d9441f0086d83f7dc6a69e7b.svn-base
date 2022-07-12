/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.negocio.mediopago;

import co.com.grupoasd.nomina.dao.MedioPagoDao;
import co.com.grupoasd.nomina.dao.TipoCuentaDao;
import co.com.grupoasd.nomina.modelo.MedioPago;
import co.com.grupoasd.nomina.modelo.TipoCuenta;
import co.com.grupoasd.nomina.modelo.TipoMedioPago;
import java.util.List;


/**
 *
 * @author Iván Vargas
 */
public class MedioPagoController implements IMedioPagoController {

    private MedioPagoDao objMedioPagoDao;
    private TipoCuentaDao objTipoCuentaDao;

    public MedioPagoController() {
        objMedioPagoDao = new MedioPagoDao();
        objTipoCuentaDao= new TipoCuentaDao();
    }

    public List<MedioPago> getListMedioPago(int idEmp, int idPrueba) throws Exception {
        return objMedioPagoDao.listar(idEmp, idPrueba);
    }

    @Override
    public List<TipoCuenta> getListTipoCta() throws Exception {
        return objTipoCuentaDao.listar();
    }

    

    public String getListJsonMedioPago(int idPrueba) throws Exception {
        return objMedioPagoDao.listarMediosPagoJson(idPrueba);
    }
    
     public List<TipoMedioPago> getListTiposMediosPago() throws Exception {
        return objMedioPagoDao.listarTiposMediosPago();
    }
    
    public int insertar(MedioPago medioPago) throws Exception {
        return objMedioPagoDao.insertar(medioPago);
    }
    
    public boolean actualizar(MedioPago medioPago) throws Exception {
        return objMedioPagoDao.actualizar(medioPago);
    }
    

    public MedioPago findMedioPago(int idMedioPago, int idPrueba) throws Exception {
        return objMedioPagoDao.findMedioPago(idMedioPago,idPrueba);

    }

    public String getListJsonMedioPago() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
  
}
