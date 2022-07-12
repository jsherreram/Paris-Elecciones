/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.negocio.sitio;

import co.com.grupoasd.nomina.dao.CargoDao;
import co.com.grupoasd.nomina.dao.DepartamentoDao;
import co.com.grupoasd.nomina.dao.EmpleadoDao;
import co.com.grupoasd.nomina.dao.EstadoSitioDao;
import co.com.grupoasd.nomina.dao.EventoDao;
import co.com.grupoasd.nomina.dao.MunicipioDao;
import co.com.grupoasd.nomina.dao.NombramientoDao;
import co.com.grupoasd.nomina.dao.PdsDao;
import co.com.grupoasd.nomina.dao.PruebaDao;
import co.com.grupoasd.nomina.dao.SitioDao;
import co.com.grupoasd.nomina.dao.StatusCargueDao;
import co.com.grupoasd.nomina.modelo.Cargo;
import co.com.grupoasd.nomina.modelo.Departamento;
import co.com.grupoasd.nomina.modelo.DepartamentoDane;
import co.com.grupoasd.nomina.modelo.Empleado;
import co.com.grupoasd.nomina.modelo.Estado;
import co.com.grupoasd.nomina.modelo.Evento;
import co.com.grupoasd.nomina.modelo.Municipio;
import co.com.grupoasd.nomina.modelo.Nombramiento;
import co.com.grupoasd.nomina.modelo.Pds;
import co.com.grupoasd.nomina.modelo.Prueba;
import co.com.grupoasd.nomina.modelo.Sitio;
import co.com.grupoasd.nomina.modelo.StatusCargue;
import co.com.grupoasd.nomina.modelo.Usuario;
import co.com.grupoasd.nomina.modelo.UsuarioGrupo;
import co.com.grupoasd.nomina.negocio.statusCargue.StatusBusiness;
import co.com.grupoasd.nomina.negocio.usuario.UsuarioController;
import co.com.grupoasd.nomina.negocio.usuariogrupo.UsuarioGrupoController;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asd
 */
public class PDSSitioMasivo {

    public PDSSitioMasivo() {

    }

    public void cargar(String nombreArchivoOrigen, String pathArchivo, int codigoPrueba, String user) {
        final String csvFile = pathArchivo;
        final String usuario = user;
        final int idPrueba = codigoPrueba;
        final String nombreArchivo = nombreArchivoOrigen;

        Thread hilo;
        hilo = new Thread() {
            @Override
            public void run() {
                try {
                    int cantidadRegistroTotal = 0;
                    int cantidadRegistrosOk = 0;
                    int cantidadRegistrosErr = 0;

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
                    statusCargue.setIdtipoCargue(8); //tipo de cargue Sitios
                    statusCargue.setNombreArchivo(nombreArchivo);
                    statusCargue.setEstadoStatus(0); //estado: 0 en proceso, 1 procesado
                    statusCargue.setCantidadRegistrosTotal(cantidadRegistroTotal - 1);
                    statusCargue.setCantidadRegistrosProcesadosOk(cantidadRegistrosOk);
                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                    int idStatus = statusBusiness.Insertar(statusCargue);
                    statusCargue.setId(idStatus);

                    cantidadRegistroTotal = 0;
                    br = new BufferedReader(new FileReader(csvFile));
                    SitioDao sitioDao = new SitioDao();
                    StringBuilder sb = new StringBuilder();
                    DepartamentoDao departamentoDao = new DepartamentoDao();
                    MunicipioDao municipioDao = new MunicipioDao();
                    String codigositio = "";

                    //DESDE AQUI INICIAN LAS VALIDACIONES    
                    while ((line = br.readLine()) != null) {
                        try {
                            if (cantidadRegistroTotal == 0) {
                                //Esta linea corresponde al titulo
                                cantidadRegistroTotal++;
                            } else {
                                String[] lineas = line.split(cvsSplitBy);
                                cantidadRegistroTotal++;

                                //Validar la estructura
                                if (lineas.length < 3) {
                                    sb.append("Linea:" + cantidadRegistroTotal + " - Número de columnas invalido.\n");
                                    cantidadRegistrosErr++;
                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                    statusBusiness.ActualizarAvance(statusCargue);
                                    continue;
                                }

                                if (!lineas[2].matches("-?\\d+(\\.\\d+)?")) {
                                    sb.append("Linea:" + cantidadRegistroTotal + " - La prioridad debe ser numerica.\n");
                                    cantidadRegistrosErr++;
                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                    statusBusiness.ActualizarAvance(statusCargue);
                                    continue;
                                }
//                       
                                codigositio = lineas[0];
                                String codigoPDS = lineas[1];

//
                                //validar sitio
                                if (codigositio.length() < 8 || codigositio.length() > 12) {
                                    sb.append("Linea:" + cantidadRegistroTotal + " - Codigo de sitio deber tener minimo 8, maximo 12 caracteres");
                                    cantidadRegistrosErr++;
                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                    statusBusiness.ActualizarAvance(statusCargue);
                                    continue;
                                }
                                Sitio sitio = sitioDao.GetSitio(codigositio, prueba);
                                Sitio pds = sitioDao.GetSitio(codigoPDS, prueba);

                                if (sitio.getId() == 0) {
                                    sb.append("Linea:" + cantidadRegistroTotal + " - El sitio no  Existe \n");
                                    cantidadRegistrosErr++;
                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                    statusBusiness.ActualizarAvance(statusCargue);
                                    continue;
                                }

                                if (pds.getId() == 0) {
                                    sb.append("Linea:" + cantidadRegistroTotal + " - El pds no  Existe\n");
                                    cantidadRegistrosErr++;
                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                    statusBusiness.ActualizarAvance(statusCargue);
                                    continue;
                                }
                                if (!sitio.getCodigoMunicipio().equals(pds.getCodigoMunicipio())) {
                                    sb.append("Linea:" + cantidadRegistroTotal + " - El municipio del sitio no es el mismo del PDS, para agregar un pds al sitio debe ser en la misma ciudad\n");
                                    cantidadRegistrosErr++;
                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                    statusBusiness.ActualizarAvance(statusCargue);
                                    continue;
                                }
                                
                                Pds existePds = new PdsDao().buscarPdsxSitioxPrioridad(sitio.getId(), pds.getId(), 0);
                                Pds existePrioridad = new PdsDao().buscarPdsxSitioxPrioridad(sitio.getId(), 0, Integer.parseInt(lineas[2]));

                                if (existePds.getIdDivipolSitio()!=null) {
                                    sb.append("Linea:" + cantidadRegistroTotal + " - Ya existe un registro creado con el sitio y el pds \n");
                                    cantidadRegistrosErr++;
                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                    statusBusiness.ActualizarAvance(statusCargue);
                                    continue;

                                }

                                if (existePrioridad.getIdDivipolSitio()!=null) {
                                    sb.append("Linea:" + cantidadRegistroTotal + " - Ya existe un registro creado para el sitio con la prioridad "+lineas[2]+"\n");
                                    cantidadRegistrosErr++;
                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                    statusBusiness.ActualizarAvance(statusCargue);
                                    continue;

                                }

                                if (!sitioDao.insertarPDSSitio(sitio.getId(), pds.getId(), Integer.parseInt(lineas[2]), idPrueba)) {
                                    sb.append("Linea:" + cantidadRegistroTotal + " - Error al registrar . Consulte con el Administrador \n");
                                    cantidadRegistrosErr++;
                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                    statusBusiness.ActualizarAvance(statusCargue);
                                    continue;
                                } else {
                                    cantidadRegistrosOk++;
                                    statusCargue.setCantidadRegistrosProcesadosOk(cantidadRegistrosOk);
                                    statusBusiness.ActualizarAvance(statusCargue);
                                }

                            }
                        } catch (IOException ex) {
                            Logger.getLogger(PDSSitioMasivo.class.getName()).log(Level.SEVERE, null, ex);
                            sb.append("Linea:" + cantidadRegistroTotal + " - Error: " + ex.getMessage() + " \n");
                            cantidadRegistrosErr++;
                            statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                            statusBusiness.ActualizarAvance(statusCargue);
                        } catch (Exception ex) {
                            Logger.getLogger(PDSSitioMasivo.class.getName()).log(Level.SEVERE, null, ex);
                            sb.append("Linea:" + cantidadRegistroTotal + " - Error: " + ex.getMessage() + " \n");
                            cantidadRegistrosErr++;
                            statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                            statusBusiness.ActualizarAvance(statusCargue);
                        }
                    }
                    br.close();
                    statusBusiness.Finalizar(statusCargue, sb);

                } catch (IOException ex) {
                    Logger.getLogger(PDSSitioMasivo.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(PDSSitioMasivo.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(PDSSitioMasivo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fwriter.close();
            } catch (IOException ex) {
                Logger.getLogger(PDSSitioMasivo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
