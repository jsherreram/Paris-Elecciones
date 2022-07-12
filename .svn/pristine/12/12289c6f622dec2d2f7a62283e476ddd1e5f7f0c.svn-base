/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.negocio.pagoconciliado;

import co.com.grupoasd.nomina.dao.PagosConciliacionDao;
import co.com.grupoasd.nomina.modelo.Pago_Conciliado;
import org.json.JSONArray;

/**
 *
 * @author Pedro Rodríguez
 */
public class PagoConciliadoController {
    
    public PagoConciliadoController()
    {
        
    }
    
    public JSONArray ConsultarPagos(int idPrueba, String codigoSitio){
        PagosConciliacionDao dao = new PagosConciliacionDao();
        return dao.ConsultarPagos(idPrueba, codigoSitio);
    }
    
    
    public int insertarPagoConciliado(Pago_Conciliado pago) {
        PagosConciliacionDao dao = new PagosConciliacionDao();
        return dao.insertarPagoConciliado(pago);        
    }

    public boolean actualizarPagoConciliado(Pago_Conciliado pago) {
        PagosConciliacionDao dao = new PagosConciliacionDao();
        return dao.actualizarPagoConciliado(pago);
    }
    
     public JSONArray ConsultarCuadreCaja(int idPrueba){
        PagosConciliacionDao dao = new PagosConciliacionDao();
        return dao.ConsultarCuadreCaja(idPrueba);
    }
    
}
