/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.portal.servlet;

import co.com.grupoasd.biometria.icfes.VOMessageEnrollmentResponse;
import co.com.grupoasd.biometria.icfes.VOMessageMatchResponse;
import co.com.grupoasd.biometria.notificacionServidor.NotificarEvento;
import co.com.grupoasd.nomina.dao.AsistenciaDao;
import co.com.grupoasd.nomina.dao.DetalleCargoPuestoEventoDao;
import co.com.grupoasd.nomina.dao.TareaConfirmacionDao;
import co.com.grupoasd.nomina.modelo.TareaConfirmacion;
import co.com.grupoasd.nomina.modelo.wrapper.Llave;
import co.com.grupoasd.nomina.negocio.empleado.IEmpleadoImpl;
import java.util.Date;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * Proceso Asincrono Clase que recibe los eventos de enrolamiento e
 * identificación. Es invocado desde el componente de biometría
 *
 * @author Administrador
 */
//Siempre se debe añadir la notación con este nombre :NotificarEvento
@WebServlet(name = "NotificarEvento", urlPatterns = {"/NotificarEvento"})
public class ResponseBiometria extends NotificarEvento {

    private IEmpleadoImpl empleado = new IEmpleadoImpl();
    private AsistenciaDao asistenciaDao = new AsistenciaDao();

    // @Override
    public String enrolar(VOMessageEnrollmentResponse mensaje) throws Exception {

        //si no ocurrio alguna excepcion y vienen los datos
        if (mensaje.getEx() == null
                && mensaje.getLlave() != null
                && mensaje.getHuella() != null
                && mensaje.getImagenSerializada() != null) {
            if (empleado.anadirHuella(Integer.parseInt((String) mensaje.getLlave()),
                    mensaje.getHuella(),
                    mensaje.getImagenSerializada())) {
                return "Se ha enrolado con éxito";
            } else {
                return "Ocurrio un error al guardar la huella";
            }

        } else {
            return "El mensaje no viene completo falta la llave, huella o imagen";
        }
    }

    //  @Override
    public String match(VOMessageMatchResponse mensaje) throws Exception {

        if (mensaje.isMatchOK()) {
            String llave = (String) mensaje.getLlave();
            String llaveArray[] = llave.split(";");

            if (llaveArray[3].equals("X")) {
                asistenciaDao.RegistrarAsistencia(Integer.parseInt(llaveArray[0]), Integer.parseInt(llaveArray[1]), Integer.parseInt(llaveArray[2]), true, llaveArray[4]);
            } else {
                TareaConfirmacion tarea = new TareaConfirmacion();
                tarea.setIdEmpleado(Integer.parseInt(llaveArray[4]));
                tarea.setCodigoCargo(llaveArray[2]);
                Date fecha = new Date();
                tarea.setFechaConfirmacion(fecha);
                tarea.setFormaConfirmacion("Huella");
                tarea.setTipo(llaveArray[3]);
                tarea.setIdPrueba(Integer.parseInt(llaveArray[1]));
                tarea.setEstado(0);

                TareaConfirmacionDao tareaDao = new TareaConfirmacionDao();

                if (tareaDao.insertar(tarea)) {
                    return "se realizó la firma de " + tarea.getTipo();
                } else {
                    return "Error al confirmar con la huella";
                }
            }

            return "Se realizo la marcación de asistencia";
        } else {
            return "No se realizo el registro de asistencia";
        }

    }

}
