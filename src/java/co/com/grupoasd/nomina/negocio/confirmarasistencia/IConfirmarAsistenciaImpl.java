/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.negocio.confirmarasistencia;

import co.com.grupoasd.nomina.dao.EmpleadoPruebaEstadoDao;
import co.com.grupoasd.nomina.modelo.ConfirmarAsistencia;


public class IConfirmarAsistenciaImpl implements IConfirmarAsistencia {
    

    @Override
    public Boolean confirmar(ConfirmarAsistencia asistencia) {
        EmpleadoPruebaEstadoDao dao = new EmpleadoPruebaEstadoDao();
        return dao.actualizarAsistencia(asistencia);
    }
    
}
