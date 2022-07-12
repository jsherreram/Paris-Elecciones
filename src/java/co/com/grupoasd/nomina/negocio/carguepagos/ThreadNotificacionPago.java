/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.negocio.carguepagos;

import co.com.grupoasd.nomina.modelo.StatusCargue;
import co.com.grupoasd.nomina.negocio.statusCargue.StatusBusiness;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author ASD
 */
public class ThreadNotificacionPago extends Thread {

    private static final SimpleDateFormat FORMATO_FECHA_CSV = new SimpleDateFormat("dd/MM/yyyy");
    private static final char CSV_SEPARATOR = ';';
    private String csvFile;
    private String user;
    private String nombreArchivo;
    private int idPrueba;

    /**
     *
     * @param csvFile
     * @param user
     * @param nombreArchivo
     * @param idPrueba
     */
    public ThreadNotificacionPago(String csvFile, String user, String nombreArchivo, int idPrueba) {
        this.csvFile = csvFile;
        this.user = user;
        this.nombreArchivo = nombreArchivo;
        this.idPrueba = idPrueba;
    }

    /**
     * Metodo run que realiza el procesamiento del archivo recibido
     */
    @Override
    public void run() {
        Reader readerFile = null;
        StatusBusiness statusBusiness = new StatusBusiness();
        StatusCargue statusCargue = new StatusCargue();
        int cantidadRegistrosOk = 0;
        int cantidadRegistrosErr = 0;
        StringBuilder logError = new StringBuilder();
        try {
            //Contar las filas del archivo 
            readerFile = new FileReader(this.csvFile);
            List<CSVRecord> records = CSVFormat.newFormat(CSV_SEPARATOR).withFirstRecordAsHeader().parse(readerFile).getRecords();
            int cantidadRegistroTotal = records.size();

            //Agregar el registro en la tabla de status_cargue para hacer seguiiento del proceso
            statusCargue.setUsuario(getUser());
            statusCargue.setIdtipoCargue(12); //tipo de cargue Pagos
            statusCargue.setNombreArchivo(getNombreArchivo());
            statusCargue.setEstadoStatus(0); //estado: 0 en proceso, 1 procesado
            statusCargue.setCantidadRegistrosTotal(cantidadRegistroTotal);
            statusCargue.setCantidadRegistrosProcesadosOk(cantidadRegistrosOk);
            statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
            int idStatus = statusBusiness.Insertar(statusCargue);
            statusCargue.setId(idStatus);

            //Se inicia con el procesamiento del csv
            for (CSVRecord record : records) {
                if (record.size() < 5) {
                    logError.append("Linea:").append(record.getRecordNumber()).append(" - Número de columnas menor a lo esperado ").append(record.size()).append(".\n");
                    cantidadRegistrosErr++;
                } else {
                    try {
                        NotificacionPagoBusiness notificacionNegocio = new NotificacionPagoBusiness();
                        boolean insertado = notificacionNegocio.insertarNotificacionPago(record, idPrueba);
                        if (insertado) {
                            cantidadRegistrosOk++;
                        } else {
                            cantidadRegistrosErr++;
                            logError.append("Linea:").append(record.getRecordNumber()).append(" - No se pudo registrar la informacion consulte el log del servidor - ").append(".\n");
                        }
                    } catch (Exception e) {
                        Logger.getLogger(ThreadNotificacionPago.class.getName()).log(Level.SEVERE, null, e);
                        logError.append("Linea:").append(record.getRecordNumber()).append(" - Error inesperado - ").append(e.getMessage()).append(".\n");
                        cantidadRegistrosErr++;
                    }
                }
                statusCargue.setCantidadRegistrosProcesadosOk(cantidadRegistrosOk);
                statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                statusBusiness.ActualizarAvance(statusCargue);
            }
        } catch (Exception ex) {
            Logger.getLogger(ThreadNotificacionPago.class.getName()).log(Level.SEVERE, null, ex);
            try {
                readerFile.close();
            } catch (IOException ex1) {
                Logger.getLogger(ThreadNotificacionPago.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        statusCargue.setCantidadRegistrosProcesadosOk(cantidadRegistrosOk);
        statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
        statusBusiness.ActualizarAvance(statusCargue);
        statusBusiness.Finalizar(statusCargue, logError);
    }

    /**
     * @return the csvFile
     */
    public String getCsvFile() {
        return csvFile;
    }

    /**
     * @param csvFile the csvFile to set
     */
    public void setCsvFile(String csvFile) {
        this.csvFile = csvFile;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the nombreArchivo
     */
    public String getNombreArchivo() {
        return nombreArchivo;
    }

    /**
     * @param nombreArchivo the nombreArchivo to set
     */
    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    /**
     * @return the idPrueba
     */
    public int getIdPrueba() {
        return idPrueba;
    }

    /**
     * @param idPrueba the idPrueba to set
     */
    public void setIdPrueba(int idPrueba) {
        this.idPrueba = idPrueba;
    }

}
