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
import co.com.grupoasd.nomina.modelo.Prueba;
import co.com.grupoasd.nomina.modelo.Sitio;
import co.com.grupoasd.nomina.modelo.StatusCargue;
import co.com.grupoasd.nomina.modelo.TipoSitio;
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
public class SitioMasivo {

    public SitioMasivo() {

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
                    statusCargue.setIdtipoCargue(5); //tipo de cargue Sitios
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
                                if (lineas.length < 2) {
                                    sb.append("Linea:" + cantidadRegistroTotal + " - Número de columnas invalido.\n");
                                    cantidadRegistrosErr++;
                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                    statusBusiness.ActualizarAvance(statusCargue);
                                    continue;
                                }
//                       
                                codigositio = lineas[5];

                                if (codigositio.length() == 7) {
                                    codigositio = "0" + codigositio;
                                }
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

                                if (sitio.getId() > 0) {
                                    sb.append("Linea:" + cantidadRegistroTotal + " - El sitio ya Existe\n");
                                    cantidadRegistrosErr++;
                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                    statusBusiness.ActualizarAvance(statusCargue);
                                    continue;
                                }

                                String codigoDpto = lineas[0];
                                Departamento dpto = departamentoDao.GetById(codigoDpto);
                                if (!lineas[0].matches("-?\\d+(\\.\\d+)?")) {
                                    sb.append("Linea:" + cantidadRegistroTotal + " - El código de Nodo debe ser numérico\n");
                                    cantidadRegistrosErr++;
                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                    statusBusiness.ActualizarAvance(statusCargue);
                                    continue;
                                }
                                if (dpto.getCodigo() == null) {
                                    sb.append("Linea:" + cantidadRegistroTotal + " - No existe un nodo registrado con ese codigo\n");
                                    cantidadRegistrosErr++;
                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                    statusBusiness.ActualizarAvance(statusCargue);
                                    continue;
                                }
                                String codigoMcpio = lineas[2];
                                Municipio mcpio = municipioDao.GetById(codigoDpto, codigoMcpio);
                                if (!lineas[2].matches("-?\\d+(\\.\\d+)?")) {
                                    sb.append("Linea:" + cantidadRegistroTotal + " - El código de Municipio debe ser numérico\n");
                                    cantidadRegistrosErr++;
                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                    statusBusiness.ActualizarAvance(statusCargue);
                                    continue;
                                }
                                if (mcpio.getCodigoMunicipio() == null) {
                                    sb.append("Linea:" + cantidadRegistroTotal + " - No existe un muNIcipio registrado con ese código\n");
                                    cantidadRegistrosErr++;
                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                    statusBusiness.ActualizarAvance(statusCargue);
                                    continue;
                                }

                                if (lineas[4].length() == 0) {
                                    sb.append("Linea:" + cantidadRegistroTotal + " - Nombre de Sitio no puede ser vacío\n");
                                    cantidadRegistrosErr++;
                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                    statusBusiness.ActualizarAvance(statusCargue);
                                    continue;
                                }

                                if (lineas[8].length() == 0) {
                                    sb.append("Linea:" + cantidadRegistroTotal + " - Dirección de Sitio no puede ser vacía\n");
                                    cantidadRegistrosErr++;
                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                    statusBusiness.ActualizarAvance(statusCargue);
                                    continue;
                                }

                                if (lineas[14].length() > 0 && !lineas[14].matches("-?\\d+(\\.\\d+)?")) {
                                    sb.append("Linea:" + cantidadRegistroTotal + " - Teléfono de contacto debe ser numérico\n");
                                    cantidadRegistrosErr++;
                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                    statusBusiness.ActualizarAvance(statusCargue);
                                    continue;
                                }
                                if (lineas[15].length() > 0 && !lineas[15].matches("-?\\d+(\\.\\d+)?")) {
                                    sb.append("Linea:" + cantidadRegistroTotal + " - Fax debe ser numérico\n");
                                    cantidadRegistrosErr++;
                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                    statusBusiness.ActualizarAvance(statusCargue);
                                    continue;
                                }
                                if (lineas[16].length() > 0 && !lineas[16].matches("-?\\d+(\\.\\d+)?")) {
                                    sb.append("Linea:" + cantidadRegistroTotal + " - Cantidad de salones debe ser numérico\n");
                                    cantidadRegistrosErr++;
                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                    statusBusiness.ActualizarAvance(statusCargue);
                                    continue;
                                }
                                if (lineas[17].length() > 0 && !lineas[17].matches("-?\\d+(\\.\\d+)?")) {
                                    sb.append("Linea:" + cantidadRegistroTotal + " - Examinados por sesión debe ser numérico\n");
                                    cantidadRegistrosErr++;
                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                    statusBusiness.ActualizarAvance(statusCargue);
                                    continue;
                                }

                                Sitio sitioNuevo = new Sitio();
                                sitioNuevo.setIdPrueba(idPrueba);
                                sitioNuevo.setCodigoSitio(codigositio);
                                sitioNuevo.setNombreSitio(lineas[4]);
                                sitioNuevo.setMunicipio(mcpio);
                                sitioNuevo.setDepartamento(dpto);
                                sitioNuevo.setDireccionSitio(lineas[8]);
                                sitioNuevo.setBarrio(lineas[9]);
                                if (lineas[10].length() == 0) {
                                    sitioNuevo.setIdZona(0);
                                } else {
                                    sitioNuevo.setIdZona(Integer.parseInt(lineas[10]));
                                }

                                sitioNuevo.setInstruccionesLlegada(lineas[11]);
                                sitioNuevo.setNombreRector(lineas[12]);
                                sitioNuevo.setEmailRector(lineas[13]);
                                sitioNuevo.setTelefonoRector(lineas[14]);
                                sitioNuevo.setFaxRector(lineas[15]);
                                sitioNuevo.setEmailSede(lineas[13]);
                                sitioNuevo.setDepartamentoSede(dpto);
                                sitioNuevo.setMunicipioSede(mcpio);

                                if (lineas[16].length() == 0) {
                                    sitioNuevo.setCantidadSalones(0);
                                } else {
                                    sitioNuevo.setCantidadSalones(Integer.parseInt(lineas[16]));
                                }
                                if (lineas[17].length() == 0) {
                                    sitioNuevo.setnExaminandos(0);
                                } else {
                                    sitioNuevo.setnExaminandos(Integer.parseInt(lineas[17]));
                                }
                                sitioNuevo.setFechaReunionPrevia(lineas[18]);
                                sitioNuevo.setHoraReunionPrevia(lineas[19]);
                                sitioNuevo.setCodigoSede(lineas[6]);
                                sitioNuevo.setNombreSede(lineas[7]);
                                sitioNuevo.setDireccionSede(lineas[8]);
                                sitioNuevo.setTelefonoSede(lineas[14]);
                                TipoSitio ts = new TipoSitio();
                                ts.setIdTipoSitio(1);
                                sitioNuevo.setTipoSitio(ts);
                                sitioNuevo.setCategoriaSitio(1);
                                sitioNuevo.setTomaAsistencia("Huella");
                                sitioNuevo.setMedioPago("Efectivo");
                                sitioNuevo.setConfirmado("0");
                                sitioNuevo.setEspolivalente(0);
                                sitioNuevo.setDesconectado(0);
                                sitioNuevo.setEstadoSitio(new EstadoSitioDao().GetEstadoSitioById(2));

                                if (!sitioDao.insertarSitio(sitioNuevo)) {
                                    sb.append("Linea:" + cantidadRegistroTotal + " - Error al registrar el sitio. Consulte con el Administrador \n");
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
                            Logger.getLogger(SitioMasivo.class.getName()).log(Level.SEVERE, null, ex);
                            sb.append("Linea:" + cantidadRegistroTotal + " - Error: " + ex.getMessage() + " \n");
                            cantidadRegistrosErr++;
                            statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                            statusBusiness.ActualizarAvance(statusCargue);
                        } catch (Exception ex) {
                            Logger.getLogger(SitioMasivo.class.getName()).log(Level.SEVERE, null, ex);
                            sb.append("Linea:" + cantidadRegistroTotal + " - Error: " + ex.getMessage() + " \n");
                            cantidadRegistrosErr++;
                            statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                            statusBusiness.ActualizarAvance(statusCargue);
                        }
                    }
                    br.close();
                    statusBusiness.Finalizar(statusCargue, sb);

                } catch (IOException ex) {
                    Logger.getLogger(SitioMasivo.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(SitioMasivo.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SitioMasivo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fwriter.close();
            } catch (IOException ex) {
                Logger.getLogger(SitioMasivo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
