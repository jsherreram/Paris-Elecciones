/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.negocio.mediopago;

import co.com.grupoasd.nomina.modelo.MedioPago;
import co.com.grupoasd.nomina.modelo.TipoCuenta;
import co.com.grupoasd.nomina.modelo.TipoMedioPago;
import java.util.List;

/**
 *
 * @author Iván Vargas
 */
public interface IMedioPagoController {

    public List<MedioPago> getListMedioPago(int idEmp, int idPrueba) throws Exception;

    public List<TipoCuenta> getListTipoCta() throws Exception;
    
    public String getListJsonMedioPago(int idPrueba) throws Exception;
    
    public List<TipoMedioPago> getListTiposMediosPago() throws Exception;
    
    public int insertar(MedioPago medioPago) throws Exception;
    
    public MedioPago findMedioPago(int idMedioPago, int idprueba) throws Exception;
    
    boolean actualizar(MedioPago medioPago) throws Exception;
    
}
