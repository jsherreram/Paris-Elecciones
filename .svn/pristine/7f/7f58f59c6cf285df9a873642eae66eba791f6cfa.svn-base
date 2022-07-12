/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.negocio.seguimiento;

import co.com.grupoasd.nomina.dao.SeguimientoDao;

/**
 *
 * @author Pedro Rodríguez
 */
public class SeguimientoBusiness  implements Iseguimiento {

    @Override
    public String consultar(String tipoConsulta, String codigoCargo, Object... params) {
        SeguimientoDao dao = new SeguimientoDao();
        return dao.consultaMonitoreo(tipoConsulta, codigoCargo,  params);
    }
    
}
