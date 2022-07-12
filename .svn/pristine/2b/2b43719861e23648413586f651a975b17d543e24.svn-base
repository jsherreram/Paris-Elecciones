/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.negocio.carguepagos;

import co.com.grupoasd.nomina.common.util.Constants;
import co.com.grupoasd.nomina.common.util.EnumColumnsCsvPagos;
import co.com.grupoasd.nomina.dao.DivitransDao;
import co.com.grupoasd.nomina.dao.EmpleadoDao;
import co.com.grupoasd.nomina.dao.EmpleadoPruebaEstadoDao;
import co.com.grupoasd.nomina.dao.StatusCargueDao;
import co.com.grupoasd.nomina.modelo.Divitrans;
import co.com.grupoasd.nomina.modelo.EmpleadoPruebaEstado;
import co.com.grupoasd.nomina.modelo.StatusCargue;
import co.com.grupoasd.nomina.negocio.statusCargue.StatusBusiness;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author Wilfer
 */
public class Pagos {

    public static final int ESTADO_VIATICOS_PAGADOS = 4;
    public static final int ESTADO_VIATICOS_APROBADOS = 3;

    public Pagos() {

    }

    /**
     * Metodo encargado de cargar los pagos de viaticos y generar un log de la
     * carga en la tabla status_cargue
     *
     * @param nombreArchivoOrigen
     * @param pathArchivo
     * @param codigoPrueba
     * @param user
     */
    public void cargarPagoViaticos(String nombreArchivoOrigen, String pathArchivo, int codigoPrueba, String user) {
        final String csvFile = pathArchivo;
        final String usuario = user;
        final int idPrueba = codigoPrueba;
        final String nombreArchivo = nombreArchivoOrigen;
        final EnumColumnsCsvPagos[] enumValues = EnumColumnsCsvPagos.values();
        final EmpleadoDao empleadoDao = new EmpleadoDao();
        final DivitransDao divitransDao = new DivitransDao();
        final SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        Thread hilo;
        hilo = new Thread() {
            Reader readerFile;

            @Override
            public void run() {
                try {
                    int cantidadRegistroTotal = 0;
                    int cantidadRegistrosOk = 0;
                    int cantidadRegistrosErr = 0;

                    BufferedReader br;
                    char csvSplitBy = ';';

                    //Contar las filas del archivo 
                    readerFile = new FileReader(csvFile);
                    List<CSVRecord> records = CSVFormat.newFormat(csvSplitBy).withFirstRecordAsHeader().parse(readerFile).getRecords();
                    cantidadRegistroTotal = records.size();
                    //Agregar el registro en la tabla de status_cargue para hacer seguiiento del proceso
                    StatusBusiness statusBusiness = new StatusBusiness();
                    StatusCargue statusCargue = new StatusCargue();
                    statusCargue.setUsuario(usuario);
                    statusCargue.setIdtipoCargue(10); //tipo de cargue Pagos
                    statusCargue.setNombreArchivo(nombreArchivo);
                    statusCargue.setEstadoStatus(0); //estado: 0 en proceso, 1 procesado
                    statusCargue.setCantidadRegistrosTotal(cantidadRegistroTotal);
                    statusCargue.setCantidadRegistrosProcesadosOk(cantidadRegistrosOk);
                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                    int idStatus = statusBusiness.Insertar(statusCargue);
                    statusCargue.setId(idStatus);

                    StringBuilder sb = new StringBuilder();
                    for (CSVRecord record : records) {
                        if (record.size() != 38) {
                            sb.append("Linea:").append(record.getRecordNumber()).append(" - Número de columnas invalido.\n");
                        } else {
                            Map<Integer, Integer> structureValidateMap = validateRecord(record);
                            if (!structureValidateMap.isEmpty()) {
                                appendErrors(structureValidateMap, sb, record.getRecordNumber(), true);
                                cantidadRegistrosErr++;
                                statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                statusBusiness.ActualizarAvance(statusCargue);
                            } else {
                                Map<Integer, Integer> businessValidateMap = validatePayDivitrans(record, idPrueba);
                                if (!businessValidateMap.isEmpty()) {
                                    appendErrors(businessValidateMap, sb, record.getRecordNumber(), false);
                                    cantidadRegistrosErr++;
                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                    statusBusiness.ActualizarAvance(statusCargue);
                                } else {
                                    boolean actualizarDatosEmpleado = actualizarDatosEmpleado(record);
                                    if (actualizarDatosEmpleado) {
                                        cantidadRegistrosOk++;
                                    }
                                }
                            }
                        }
                    }
                    statusCargue.setCantidadRegistrosProcesadosOk(cantidadRegistrosOk);
                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                    statusBusiness.ActualizarAvance(statusCargue);
                    statusBusiness.Finalizar(statusCargue, sb);
                } catch (IOException ex) {
                    Logger.getLogger(Pagos.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        readerFile.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Pagos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            /**
             *
             * @param recordColumn
             * @param record
             */
            private Map<Integer, Integer> validateRecord(CSVRecord record) {
                Map<Integer, Integer> mapaErrores = new HashMap<>();
                int index = 0;
                for (String column : record) {
                    EnumColumnsCsvPagos infoDato = enumValues[index];
                    if (infoDato.getTipo().equals(String.class)) {
                        if (column.length() > infoDato.getLength()) {
                            mapaErrores.put((index + 1), Constants.ERROR_INVALID_LENGHT);
                        }
                    } else if (infoDato.getTipo().equals(Integer.class)) {
                        try {
                            if (Integer.parseInt(column) > infoDato.getLength()) {
                                System.out.println("Error de longitud:(" + infoDato.getLength() + "): " + column);
                                mapaErrores.put((index + 1), Constants.ERROR_INVALID_LENGHT);
                            }
                        } catch (NumberFormatException nfe) {
                            System.out.println("Error de Tipo de Dato Entero:" + column);
                            mapaErrores.put((index + 1), Constants.ERROR_INVALID_TYPE);
                        }

                    } else if (infoDato.getTipo().equals(Date.class)) {
                        try {
                            df.parse(column);
                        } catch (Exception e) {
                            System.out.println("Error de Tipo de Dato Date:" + column);
                            mapaErrores.put((index + 1), Constants.ERROR_INVALID_TYPE);
                        }
                    } else {
                        mapaErrores.put((index + 1), Constants.ERROR_INVALID_TYPE);
                    }
                    index++;
                }
                return mapaErrores;
            }

            /**
             * Metodo encargado de imprimir los valores del error por linea y
             * columna
             *
             * @param validateRecordMap
             * @param sb
             * @param recordNumber
             */
            private void appendErrors(Map<Integer, Integer> validateRecordMap, StringBuilder sb, long recordNumber, boolean appendColumn) {
                for (Map.Entry<Integer, Integer> entry : validateRecordMap.entrySet()) {
                    sb.append("Linea: ").append(recordNumber + 1);
                    if (appendColumn) {
                        sb.append(" Columna: ").append(entry.getKey());
                    }

                    switch (entry.getValue()) {
                        case Constants.ERROR_INVALID_LENGHT:
                            sb.append(" - Longitud Invalida.\n");
                            break;
                        case Constants.ERROR_INVALID_TYPE:
                            sb.append(" - Tipo de Dato Invalido.\n");
                            break;
                        case Constants.ERROR_INVALID_EMPLOYEE:
                            sb.append(" - Empleado no existe.\n");
                            break;
                        case Constants.ERROR_INVALID_DIVITRANS:
                            sb.append(" - No existe un viatico aprobado para el empleado.\n");
                            break;
                        case Constants.ERROR_INVALID_PAY_VALUE:
                            sb.append(" - El valor del campo Valor Pago no es igual al Valor a Pagar. Verifique por favor! \n");
                            break;
                        case Constants.ERROR_INVALID_TOTAL_VALUE:
                            sb.append(" - El valor del campo Valor Total no es igual al Valor a Pagar. Verifique por favor!\n");
                            break;
                    }
                }
            }

            /**
             * Metodo para validar las reglas de negocio por registro ingresado
             *
             * @param record
             * @return
             */
            private Map<Integer, Integer> validatePayDivitrans(CSVRecord record, int idPrueba) {
                System.out.println("Validating pay Divitrans");
                Map<Integer, Integer> mapaErrores = new HashMap<>();

                //Se valida la existencia del empleado del pago de viaticos
                int idEmpleado = empleadoDao.GetIdByNumeroDocumento(Long.parseLong(record.get(0)));
                if (idEmpleado <= 0) {
                    System.out.println("Error de empleado");
                    mapaErrores.put(1, Constants.ERROR_INVALID_EMPLOYEE);
                } else {
                    //Se valida que exista un viatico aprobado para ese numero de documento
                    Divitrans viaticoAprobado = divitransDao.consultarViaticoEmpleadoPorEstado(idEmpleado, idPrueba, new int[]{3});
                    if (viaticoAprobado.getId() <= 0) {
                        System.out.println("Error de Viaticos");
                        mapaErrores.put(1, Constants.ERROR_INVALID_DIVITRANS);
                    }
                }

                //Se valida que los montos del archivo sean iguales
                long valorPagar = Long.parseLong(record.get("Valor a Pagar").replace(".", ""));
                long valorTotal = Long.parseLong(record.get("Valor Total").replace(".", ""));
                long valorPago = Long.parseLong(record.get("Valor Pago").replace(".", ""));
                if (valorPagar != valorTotal) {
                    System.out.println("Error de valor total");
                    mapaErrores.put(31, Constants.ERROR_INVALID_TOTAL_VALUE);
                } else if (valorPagar != valorPago) {
                    System.out.println("Error de valor pago");
                    mapaErrores.put(37, Constants.ERROR_INVALID_PAY_VALUE);
                }
                return mapaErrores;
            }

            /**
             * Metodo encargado de realizar las actualizaciones a los estados de
             * la persona en la prueba
             *
             * @param record
             * @return
             */
            private boolean actualizarDatosEmpleado(CSVRecord record) {
                long nroDoc = Long.parseLong(record.get(0).trim());
                String cargoDescripcion = record.get(18).trim();
                int idEmpleado = empleadoDao.GetIdByNumeroDocumento(nroDoc);
                //Se valida que exista un viatico aprobado para ese numero de documento
                Divitrans viaticoAprobado = divitransDao.consultarViaticoEmpleadoPorEstado(idEmpleado, idPrueba, new int[]{3});
                if (viaticoAprobado.getEstado().getIdEstadoDivitrans() == ESTADO_VIATICOS_APROBADOS) {
                    boolean successPagoViatico = divitransDao.actualizarPagoViatico(record.toMap(), viaticoAprobado.getId());
                    if (successPagoViatico) {
                        EmpleadoPruebaEstadoDao empleadoPruebaDao = new EmpleadoPruebaEstadoDao();
                        EmpleadoPruebaEstado empleadoPruebaEstado = empleadoPruebaDao.getByDocumento(nroDoc, idPrueba, cargoDescripcion);
                        if (empleadoPruebaEstado.getId() > 0) {
                            empleadoPruebaEstado.setIdestpersona(9);
                            empleadoPruebaEstado.setUsuario(usuario);
                            boolean actualizarEstado = empleadoPruebaDao.actualizarEstado(empleadoPruebaEstado);
                            return actualizarEstado;
                        }
                    }
                    return false;
                } else if (viaticoAprobado.getEstado().getIdEstadoDivitrans() == ESTADO_VIATICOS_PAGADOS) {
                    return true;
                }
                return false;
            }
        };
        hilo.start();
    }

    /**
     *
     * @param idStatusCargue
     */
    public void generaArchivoErrores(int idStatusCargue) {

        FileWriter fwriter = null;
        try {
            String nombreArchivo = "/data/tmp/ERR_" + idStatusCargue + ".csv";
            StatusCargueDao statusCargueDao = new StatusCargueDao();
            fwriter = new FileWriter(nombreArchivo);
            fwriter.write(statusCargueDao.getErrores(idStatusCargue));
            fwriter.flush();
            fwriter.close();
        } catch (IOException ex) {
            Logger.getLogger(Pagos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fwriter.close();
            } catch (IOException ex) {
                Logger.getLogger(Pagos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static boolean isFechaValida(String fecha) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

}
