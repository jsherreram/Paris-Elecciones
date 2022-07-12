/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.negocio.carguepagos;

import co.com.grupoasd.nomina.common.util.SendMailUtil;
import co.com.grupoasd.nomina.common.util.StringUtil;
import co.com.grupoasd.nomina.dao.EmpleadoDao;
import co.com.grupoasd.nomina.dao.NotificacionPagoDao;
import co.com.grupoasd.nomina.modelo.Empleado;
import co.com.grupoasd.nomina.modelo.EnvioCorreo;
import co.com.grupoasd.nomina.modelo.MedioPago;
import co.com.grupoasd.nomina.modelo.NotificacionPago;
import co.com.grupoasd.nomina.modelo.ParametrosCorreo;
import co.com.grupoasd.nomina.modelo.Prueba;
import co.com.grupoasd.nomina.negocio.mediopago.MedioPagoController;
import co.com.grupoasd.parametroscorreo.SingletonCorreo;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author ASD
 */
public class NotificacionPagoBusiness {

    /**
     * Constantes
     */
    private static final SimpleDateFormat FORMATO_ARCHIVO_FECHA_PAGO = new SimpleDateFormat("dd/MM/yyyy");
    private static final SimpleDateFormat FORMATO_SQL_FECHA_PAGO = new SimpleDateFormat("yyyy-MM-dd");
    private static final String EMAIL_TITLE = "PAGO SERVICIOS PLEBISCITO 2016";
    private static final String LINK_PAGO = "/panel/notificacionPago/index.jsp#/";
    private static final String LINK_CONVOCATORIAS = System.getProperty("urlConvocatorias");
    ;
    private static StringBuilder EMAIL_BODY;
    private final EnvioCorreo correo;
    private ParametrosCorreo parametros;

    /**
     * Constructor para incializar los datos del correo
     */
    public NotificacionPagoBusiness() {
        correo = new EnvioCorreo();
        incializarDatosCorreo();
        EMAIL_BODY = new StringBuilder();
        EMAIL_BODY.append("Estimado(a) \n{nombre}. \n \n");
        EMAIL_BODY.append("El Grupo ASD le informa que el pago de los servicios ");
        EMAIL_BODY.append("prestados en el evento de PLEBISCITO 2016 ha sido efectuado ");
        EMAIL_BODY.append("para ver los detalles de su pago por favor ingrese al siguiente link {link} ");
        EMAIL_BODY.append("donde encontrará la información detallada del pago y ");
        EMAIL_BODY.append("podrá registrar sus observaciones del proceso. \n \n");
        EMAIL_BODY.append("Saludo Cordial. \n");
        EMAIL_BODY.append("Grupo ASD S.A.S ");
    }

    /**
     * Metodo que inicia el hilo de procesamiento para el csv cargado
     *
     * @param name
     * @param pathArchivo
     * @param idPrueba
     * @param usuario
     */
    public void cargarPagoParaNotificar(String name, String pathArchivo, int idPrueba, String usuario) {
        ThreadNotificacionPago hiloNotificacion = new ThreadNotificacionPago(pathArchivo, usuario, name, idPrueba);
        hiloNotificacion.start();
    }

    /**
     * Metodo encargado de insertar la informacion de la notificacion del pago
     *
     * @param record
     * @param idPrueba
     * @return
     */
    public boolean insertarNotificacionPago(CSVRecord record, int idPrueba) {
        try {
            Logger.getLogger(NotificacionPagoBusiness.class.getName()).log(Level.INFO, "Procesando registro [{0}]", record.toString());
            NotificacionPago notificacionPago = new NotificacionPago();
            EmpleadoDao empleadoDao = new EmpleadoDao();
            int idEmpleado = empleadoDao.GetIdByNumeroDocumento(new Long(record.get(0)));
            Empleado empleado = empleadoDao.GetByIdSinImagenHuella(idEmpleado);
            notificacionPago.setEmpleado(empleado);
            MedioPagoController medioPagoControl = new MedioPagoController();
            MedioPago medioPago = medioPagoControl.findMedioPago(new Integer(record.get(1)), idPrueba);
            notificacionPago.setMedioPago(medioPago);
            notificacionPago.setValor(new Long(record.get(2)));
            notificacionPago.setFechaPago(FORMATO_SQL_FECHA_PAGO.format(FORMATO_ARCHIVO_FECHA_PAGO.parse(record.get(3))));
            notificacionPago.setObservacionesPago(record.get(4));
            notificacionPago.setPrueba(new Prueba());
            notificacionPago.getPrueba().setIdprueba(idPrueba);
            NotificacionPagoDao notificacionDao = new NotificacionPagoDao();
            long idInsertado = notificacionDao.insertarNotificacionPago(notificacionPago);
            if (idInsertado > 0L) {
                return enviarEmailNotificacion(empleado, idInsertado);
            }
            return false;
        } catch (ParseException ex) {
            Logger.getLogger(NotificacionPagoBusiness.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (Exception ex) {
            Logger.getLogger(NotificacionPagoBusiness.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * Metodo para enviar el correo al empleado
     *
     * @param empleado
     * @param idNotificacion
     * @return
     * @throws java.security.NoSuchAlgorithmException
     */
    public boolean enviarEmailNotificacion(Empleado empleado, long idNotificacion) throws NoSuchAlgorithmException {
        correo.setCorreoDestino(empleado.getEmail());
        StringBuilder nombreEmpleado = new StringBuilder();
        nombreEmpleado.append(empleado.getNombre1()).append(" ");
        nombreEmpleado.append(empleado.getNombre2()).append(" ");
        nombreEmpleado.append(empleado.getApellido1()).append(" ");
        nombreEmpleado.append(empleado.getApellido2()).append(" ");
        String body = EMAIL_BODY.toString();
        body = body.replace("{nombre}", nombreEmpleado.toString().toUpperCase());
        String id = StringUtil.generateMD5(idNotificacion + "*" + empleado.getIdEmpleado());
        body = body.replace("{link}", LINK_CONVOCATORIAS + LINK_PAGO + id);
        correo.setCuerpoCorreo(body);
        new SendMailUtil().enviarCorreo(correo, this.parametros);
        return true;
    }

    /**
     * Metodo para incializar los datos para enviar el correo de notificacion
     */
    private void incializarDatosCorreo() {
        SingletonCorreo correosS = SingletonCorreo.getInstance();
        HashMap correos = correosS.getCorreos();
        this.parametros = (ParametrosCorreo) correos.get("notificacionpago");
        correo.setTituloCorreo(EMAIL_TITLE);
        correo.setCorreoRemite(parametros.getUserEmail());
        correo.setUsuarioSMTP(parametros.getUserEmailSmtp());
        correo.setPassSMTP(parametros.getPasswordEmail());
    }

}
