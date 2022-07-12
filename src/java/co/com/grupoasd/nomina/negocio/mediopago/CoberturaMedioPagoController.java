/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.negocio.mediopago;

import co.com.grupoasd.nomina.dao.CoberturaMedioPagoDao;
import co.com.grupoasd.nomina.dao.MunicipioDaneDao;
import co.com.grupoasd.nomina.dao.PruebaDao;
import co.com.grupoasd.nomina.dao.StatusCargueDao;
import co.com.grupoasd.nomina.modelo.CoberturaMedioPago;
import co.com.grupoasd.nomina.modelo.Prueba;
import co.com.grupoasd.nomina.modelo.StatusCargue;
import co.com.grupoasd.nomina.negocio.convocatoria.Convocatoria;
import co.com.grupoasd.nomina.negocio.statusCargue.StatusBusiness;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;


/**
 *
 * @author ASD
 */
public class CoberturaMedioPagoController implements ICoberturaMedioPagoController {

    private CoberturaMedioPagoDao objCoberturaMedioPagoDao;

    public CoberturaMedioPagoController() {
        objCoberturaMedioPagoDao = new CoberturaMedioPagoDao();
    }

    public void cargarFileCobertura(String nombreArchivoOrigen, String pathArchivo, int codigoPrueba, String user, int codigoMedioPago) {
        final String csvFile = pathArchivo;
        final String usuario = user;
        final int idPrueba = codigoPrueba;
        final int idMedioPago = codigoMedioPago;
        final String nombreArchivo = nombreArchivoOrigen;

        Thread hilo;
        hilo = new Thread() {
            @Override
            public void run() {
                try {
                    int cantidadRegistroTotal = 0;
                    int cantidadRegistrosOk = 0;
                    int cantidadRegistrosErr = 0;
                    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

                    BufferedReader br = null;
                    String line = "";
                    String cvsSplitBy = ";";
                    Prueba prueba = new PruebaDao().getById(idPrueba);
                    String textoVtiger = "Fecha de Aplicación:" + prueba.getFechaaplicacion() + "\n";

                    //Contar las filas del archivo 
                    br = new BufferedReader(new FileReader(csvFile));
                    while ((line = br.readLine()) != null) {
                        cantidadRegistroTotal++;
                    }
                    br.close();

                    //Agregar el registro en la tabla de status_cargue para hacer seguiiento del proceso
                    StatusBusiness statusBusiness = new StatusBusiness();
                    StatusCargue statusCargue = new StatusCargue();
                    statusCargue.setUsuario(usuario);
                    statusCargue.setIdtipoCargue(9); //tipo de cargue Pagos
                    statusCargue.setNombreArchivo(nombreArchivo);
                    statusCargue.setEstadoStatus(0); //estado: 0 en proceso, 1 procesado
                    statusCargue.setCantidadRegistrosTotal(cantidadRegistroTotal - 1);
                    statusCargue.setCantidadRegistrosProcesadosOk(cantidadRegistrosOk);
                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                    statusCargue.setIdentificadorRegistro(idMedioPago);
                    int idStatus = statusBusiness.Insertar(statusCargue);
                    statusCargue.setId(idStatus);

                    cantidadRegistroTotal = 0;
                    br = new BufferedReader(new FileReader(csvFile));
                    CoberturaMedioPagoDao coberturaMedioPagoDao = new CoberturaMedioPagoDao();
                    //MedioPagoDao medioPagoDao = new MedioPagoDao();
                    MunicipioDaneDao municipioDao = new MunicipioDaneDao();

                    StringBuilder sb = new StringBuilder();
                    int existeMunicipio = 0;
                    SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
                    List<CoberturaMedioPago> lstcc = new ArrayList<>();
                    while ((line = br.readLine()) != null) {
                        if (cantidadRegistroTotal == 0) {
                            //Esta linea corresponde al titulo
                            cantidadRegistroTotal++;
                        } else {
                            String[] lineas = line.split(cvsSplitBy);
                            cantidadRegistroTotal++;
                            //Validar la estructura
                            if (lineas.length != 1) {
                                sb.append("Linea:" + cantidadRegistroTotal + " - Número de columnas invalido.\n");
                                cantidadRegistrosErr++;
                                statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                statusBusiness.ActualizarAvance(statusCargue);
                                continue;
                            }

                            if (!lineas[0].matches("-?\\d+(\\.\\d+)?")) {
                                sb.append("Linea:" + cantidadRegistroTotal + " - Código de municipio debe ser numérico\n");
                                cantidadRegistrosErr++;
                                statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                statusBusiness.ActualizarAvance(statusCargue);
                                continue;
                            }
                            if (lineas[0].length() != 5) {
                                sb.append("Linea:" + cantidadRegistroTotal + " - Código de municipio debe tener 5 dígitos\n");
                                cantidadRegistrosErr++;
                                statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                statusBusiness.ActualizarAvance(statusCargue);
                                continue;
                            }

                            //consultar municipio
                            existeMunicipio = municipioDao.existeMunicipioDane(lineas[0]);

                            if (existeMunicipio == 0) {
                                sb.append("Linea:" + cantidadRegistroTotal + " - Municipio Dane no existe\n");
                                cantidadRegistrosErr++;
                                statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                statusBusiness.ActualizarAvance(statusCargue);
                                continue;
                            }

                            if (cantidadRegistrosErr == 0) {
                                CoberturaMedioPago coberturaMedioPago = new CoberturaMedioPago();
                                coberturaMedioPago.setIdmedio_pago(idMedioPago);
                                coberturaMedioPago.setIdPrueba(prueba.getIdprueba());
                                coberturaMedioPago.setCodigoMunicipio(lineas[0]);
                                coberturaMedioPago.setActivo(true);
                                lstcc.add(coberturaMedioPago);
                                cantidadRegistrosOk++;
                            }
                            statusCargue.setCantidadRegistrosProcesadosOk(cantidadRegistrosOk);
                            statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                            statusBusiness.ActualizarAvance(statusCargue);
                        }
                    }
                    br.close();
                    if (cantidadRegistrosErr == 0) {

                        //Actualiza estado de registros antiguos.
                        coberturaMedioPagoDao.actualizarRegistros(idMedioPago, idPrueba, 0);

                        Iterator<CoberturaMedioPago> itr = lstcc.iterator();
                        CoberturaMedioPago cobertura;
                        CoberturaMedioPago coberturaMedioPagoTemporal;
                        while (itr.hasNext()) {
                            cobertura = itr.next();

                            coberturaMedioPagoTemporal = coberturaMedioPagoDao.findCoberturaMedioPago(idMedioPago, idPrueba, cobertura.getCodigoMunicipio());
                            if (coberturaMedioPagoTemporal == null) {
                                coberturaMedioPagoDao.insertar(cobertura);
                            } else {
                                coberturaMedioPagoTemporal.setActivo(true);
                                coberturaMedioPagoDao.actualizar(coberturaMedioPagoTemporal);
                                
                            }
                        }

                    }
                    statusBusiness.Finalizar(statusCargue, sb);

                } catch (IOException ex) {
                    Logger.getLogger(CoberturaMedioPagoController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        };
        hilo.start();
    }

    public void GeneraArchivoErrores(int idStatusCargue) {

        FileWriter fwriter = null;
        try {
            String nombreArchivo = "/data/tmp/ERR_" + idStatusCargue + ".csv";
            StatusCargueDao statusCargueDao = new StatusCargueDao();
            fwriter = new FileWriter(nombreArchivo);
            fwriter.write(statusCargueDao.getErrores(idStatusCargue));
            fwriter.flush();
            fwriter.close();
        } catch (IOException ex) {
            Logger.getLogger(Convocatoria.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fwriter.close();
            } catch (IOException ex) {
                Logger.getLogger(Convocatoria.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


    public String listarMediosMunicipioJson(String codMunicipio, int medioPagoActivo, int coberturaActiva,int idPrueba) throws Exception {
        return objCoberturaMedioPagoDao.listarMediosMunicipioJson(codMunicipio, medioPagoActivo, coberturaActiva, idPrueba);
    }

    public String listarMediosMunicipioAsignados(String codMunicipio, int medioPagoActivo, int coberturaActiva, int idPrueba) throws Exception {
        return objCoberturaMedioPagoDao.listarMediosMunicipioAsignados(codMunicipio, medioPagoActivo, coberturaActiva, idPrueba);
    }

    public void generaCoberturaPorMunicipio(int idPrueba){
        objCoberturaMedioPagoDao.generaCoberturaPorMunicipio(idPrueba);
    }
    public int insertar(String objetoJson) throws Exception {
        int result = 0;
        JSONObject jsonObj = new JSONObject(objetoJson);
        CoberturaMedioPagoDao coberturaMedioPagoDao = new CoberturaMedioPagoDao();

        JSONArray listadoIdMediosPago = (JSONArray) jsonObj.get("listaMedioPagos");
        CoberturaMedioPago coberturaMedioPago;
        int idCobertura;
        int identificador;
        boolean actualizo = false;

        //Actualiza estado de los registros de coberturas para el mismo municipio ,prueba.
        coberturaMedioPagoDao.actualizarRegistros(Integer.valueOf(String.valueOf(jsonObj.get("prueba"))), 0, String.valueOf(jsonObj.get("codMun")));
        for (int i = 0; i < listadoIdMediosPago.length(); i++) {

            identificador = listadoIdMediosPago.getInt(i);

            coberturaMedioPago = coberturaMedioPagoDao.findCoberturaMedioPago(identificador, Integer.valueOf(String.valueOf(jsonObj.get("prueba"))), String.valueOf(jsonObj.get("codMun")));

            if (coberturaMedioPago == null) {
                coberturaMedioPago = new CoberturaMedioPago();
                coberturaMedioPago.setCodigoMunicipio(String.valueOf(jsonObj.get("codMun")));
                coberturaMedioPago.setIdPrueba(Integer.valueOf(String.valueOf(jsonObj.get("prueba"))));
                coberturaMedioPago.setActivo(true);
                coberturaMedioPago.setIdmedio_pago(identificador);

                idCobertura = coberturaMedioPagoDao.insertar(coberturaMedioPago);
                if (idCobertura == 0) {
                    throw new Exception("No se pudo asignar los medios de pago");
                }
            } else {
                coberturaMedioPago.setActivo(true);
                actualizo = coberturaMedioPagoDao.actualizar(coberturaMedioPago);
                if (!actualizo) {
                    throw new Exception("No se pudo asignar los medios de pago.");
                }
            }
        }
        result = 1;
        return result;
    }

    @Override
    public String listarMediosMunicipioJson(String codMunicipio, int medioPagoActivo, int coberturaActiva) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
