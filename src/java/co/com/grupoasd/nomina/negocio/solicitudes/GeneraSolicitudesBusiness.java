/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.negocio.solicitudes;

import co.com.grupoasd.nomina.dao.NotificacionPagoDao;
import co.com.grupoasd.nomina.modelo.NotificacionPago;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASD
 */
public class GeneraSolicitudesBusiness {

    public static final String COLUMN_NAMES;
    public static final String LINE_FEED = "\n";
    public static final String COLUMN_SEPARATOR = ";";
    public static final String QUOTE_CHARACTER = "\"";

    /**
     * Bloque estatico para inicializar el nombre de las columnas del archivo
     * para exportar de las notificaciones de pago
     */
    static {
        StringBuilder columnNames = new StringBuilder();
        columnNames.append("Cedula;Nombres;Apellidos;Medio Pago;Valor;Fecha Pago;Observaciones Pago;");
        columnNames.append("Observaciones;Leído;Notificado;Confirmado;Fecha Lectura;Prueba;Telefono;Link");
        COLUMN_NAMES = columnNames.toString();
    }

    /**
     * Metodo que retorna el archivo de notificaciones de pago para exportar
     *
     * @param idPrueba
     * @return
     * @throws java.io.IOException
     */
    public File generarArchivoNotificacionPagos(int idPrueba) throws IOException {
        NotificacionPagoDao notificacionDao = new NotificacionPagoDao();
        List<NotificacionPago> listNotificacionPago = notificacionDao.getAllNotificacionPago(idPrueba);
        File archivo = new File("fileTemp");
        try (FileWriter writer = new FileWriter(archivo)) {
            writer.append(COLUMN_NAMES).append(LINE_FEED);
            for (NotificacionPago notificacion : listNotificacionPago) {
                writer.append(generateRow(notificacion)).append(LINE_FEED);
            }
        }
        return archivo;
    }

    /**
     * Metodo para generar cada registro del archivo
     *
     * @param notificacion
     * @return
     */
    private String generateRow(NotificacionPago notificacion) {
        StringBuilder row = new StringBuilder();
        row.append(notificacion.getEmpleado().getNrodoc()).append(COLUMN_SEPARATOR);
        row.append(QUOTE_CHARACTER).append(notificacion.getEmpleado().getNombre1()).append(" ");
        row.append(notificacion.getEmpleado().getNombre2()).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
        row.append(QUOTE_CHARACTER).append(notificacion.getEmpleado().getApellido1()).append(" ");
        row.append(notificacion.getEmpleado().getApellido2()).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
        row.append(QUOTE_CHARACTER).append(notificacion.getMedioPago().getNombre()).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
        row.append(notificacion.getValor()).append(COLUMN_SEPARATOR);
        row.append(QUOTE_CHARACTER).append(notificacion.getFechaPago()).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
        row.append(QUOTE_CHARACTER).append(notificacion.getObservacionesPago()).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
        row.append(QUOTE_CHARACTER).append(notificacion.getObservaciones()!=null ? notificacion.getObservaciones(): "").append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
        row.append(notificacion.getLeido()).append(COLUMN_SEPARATOR);
        row.append(notificacion.getNotificado()).append(COLUMN_SEPARATOR);
        row.append(notificacion.getConfirmado()).append(COLUMN_SEPARATOR);
        row.append(QUOTE_CHARACTER).append(notificacion.getFechaActualiza()).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
        row.append(QUOTE_CHARACTER).append(notificacion.getPrueba().getNombre()).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
        row.append(QUOTE_CHARACTER).append(notificacion.getTelefono()).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
        row.append(QUOTE_CHARACTER).append(notificacion.getLink()).append(QUOTE_CHARACTER);
        return row.toString();
    }

    /**
     * Servicio encargado de obtener el archivo demo de cargue de notificacion
     * de pago
     *
     * @return
     * @throws java.io.IOException
     */
    public File generarDemoNotificacionPago() throws IOException {
        String pathString = "/data/demoFiles/";
        String fileName = "demoNotificacionPago.csv";
        File path = new File(pathString);
        if (path.exists()) {
            File archivoDemo = new File(pathString.concat(fileName));
            if (archivoDemo.exists()) {
                Logger.getLogger(GeneraSolicitudesBusiness.class.getName()).log(Level.INFO, "Archivo demo existente:{0}, {1}", new Object[]{pathString.concat(fileName), archivoDemo.exists()});
                return archivoDemo;
            } else {
                Logger.getLogger(GeneraSolicitudesBusiness.class.getName()).log(Level.INFO, "Se construye archivo demo:{0}", pathString.concat(fileName));
                return crearDemoNotificacionPago(pathString, fileName);
            }
        } else {
            Logger.getLogger(GeneraSolicitudesBusiness.class.getName()).log(Level.INFO, "Se construye path y archivo demo:{0}", pathString.concat(fileName));
            path.mkdirs();
            return crearDemoNotificacionPago(pathString, fileName);
        }
    }

    /**
     * Metodo encargado de crear el archivo cuando este no exista
     * 
     * @param pathString
     * @param fileName
     * @return 
     */
    private File crearDemoNotificacionPago(String pathString, String fileName) throws IOException {
        File archivoDemo = new File(pathString.concat(fileName));
        //Se construye el archivo
        try (FileWriter writer = new FileWriter(archivoDemo)) {
            //Se construyen los encabezados para el archivo demo
            StringBuilder encabezados = new StringBuilder();
            encabezados.append("cedula;medioPago;valor;fechaPago;observacionesPago");
            //Se construyen los valores de ejemplo para el archivo
            StringBuilder valoresEjemplo = new StringBuilder();
            valoresEjemplo.append("351712;5;500000;20/10/2016;Observaciones Pago 1").append(LINE_FEED);
            valoresEjemplo.append("405872;5;60000;20/10/2016;Observaciones Pago 2").append(LINE_FEED);
            valoresEjemplo.append("1018478;5;60000;20/10/2016;Observaciones Pago 3").append(LINE_FEED);
            //Se escriben las cadenas de ejemplo en el archivo
            writer.append(encabezados.toString());
            writer.append(System.lineSeparator());
            writer.append(valoresEjemplo.toString());
            writer.append(System.lineSeparator());
        }
        return archivoDemo;
    }

}
