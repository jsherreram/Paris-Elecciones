/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.negocio.asistencia;

import co.com.grupoasd.nomina.dao.AsistenciaDao;
import co.com.grupoasd.nomina.dao.EmpleadoDao;
import co.com.grupoasd.nomina.dao.EventoDao;
import co.com.grupoasd.nomina.dao.OrdenDao;
import co.com.grupoasd.nomina.dao.PersonaAsignadaDao;
import co.com.grupoasd.nomina.dao.TareaConfirmacionDao;
import co.com.grupoasd.nomina.modelo.Empleado;
import co.com.grupoasd.nomina.modelo.Evento;
import co.com.grupoasd.nomina.modelo.PersonaAsignada;
import co.com.grupoasd.nomina.modelo.TareaConfirmacion;
import java.util.Date;

/**
 *
 * @author ASD
 */
public class AsistenciaBusiness {

    private AsistenciaDao dao = new AsistenciaDao();

    public AsistenciaBusiness() {
    }

    public boolean marcarAsistencia(int idEmpleado, int idEvento, int idDivipol, boolean biometrico, String usuario) {
        EmpleadoDao emdao = new EmpleadoDao();
        boolean registro = dao.RegistrarAsistencia(idEmpleado, idEvento, idDivipol, biometrico, usuario);
        Empleado empleado = emdao.GetByIdSinImagenHuella(idEmpleado);
        if (registro && empleado.getEstado().getCodigoEstado() == 0) {
            emdao.actualizarEstadoEmpleado(1, idEmpleado, "");
        }
        return registro;

    }

}
