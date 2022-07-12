/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.negocio.empleado;

import co.com.grupoasd.nomina.dao.EmpleadoDao;
import co.com.grupoasd.nomina.dao.MunicipioDaneDao;
import co.com.grupoasd.nomina.dao.StatusCargueDao;
import co.com.grupoasd.nomina.modelo.Empleado;
import co.com.grupoasd.nomina.modelo.MunicipioDane;
import co.com.grupoasd.nomina.modelo.StatusCargue;
import co.com.grupoasd.nomina.negocio.statusCargue.StatusBusiness;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asd
 */
public class EmpleadoMasivo {

    public EmpleadoMasivo() {

    }

    public void cargar(String nombreArchivoOrigen, String pathArchivo, String user) {
        final String csvFile = pathArchivo;
        final String usuario = user;
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
                    statusCargue.setIdtipoCargue(11); //tipo de cargue Sitios
                    statusCargue.setNombreArchivo(nombreArchivo);
                    statusCargue.setEstadoStatus(0); //estado: 0 en proceso, 1 procesado
                    statusCargue.setCantidadRegistrosTotal(cantidadRegistroTotal - 1);
                    statusCargue.setCantidadRegistrosProcesadosOk(cantidadRegistrosOk);
                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                    int idStatus = statusBusiness.Insertar(statusCargue);
                    statusCargue.setId(idStatus);

                    cantidadRegistroTotal = 0;
                    br = new BufferedReader(new FileReader(csvFile));
                    StringBuilder sb = new StringBuilder();
                    long cedula = 0;

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
                                if (lineas.length < 11) {
                                    sb.append("Linea:" + cantidadRegistroTotal + " - Número de columnas invalido.\n");
                                    cantidadRegistrosErr++;
                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                    statusBusiness.ActualizarAvance(statusCargue);
                                    continue;
                                }
//                       

                                //validar sitio
                                if (lineas[1].length() > 10) {
                                    sb.append("Linea:" + cantidadRegistroTotal + " - El Número de Identificación debe tener maximo 10 caracteres");
                                    cantidadRegistrosErr++;
                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                    statusBusiness.ActualizarAvance(statusCargue);
                                    continue;
                                }
                                if (!lineas[1].matches("-?\\d+(\\.\\d+)?")) {
                                    sb.append("Linea:" + cantidadRegistroTotal + " - El Numero de Identificación debe ser numérico\n");
                                    cantidadRegistrosErr++;
                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                    statusBusiness.ActualizarAvance(statusCargue);
                                    continue;
                                }
                                if (lineas[1].length() == 10 && !lineas[1].substring(0, 1).equals("1")) {
                                    sb.append("Linea:" + cantidadRegistroTotal + " - Número de Identificación no valido\n");
                                    cantidadRegistrosErr++;
                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                    statusBusiness.ActualizarAvance(statusCargue);
                                    continue;
                                }
                                cedula = Long.parseLong(lineas[1]);
                                int existe = new EmpleadoDao().GetIdByNumeroDocumento(cedula);
                                if (existe > 0) {
                                    sb.append("Linea:" + cantidadRegistroTotal + " - La persona ya esta registrada\n");
                                    cantidadRegistrosErr++;
                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                    statusBusiness.ActualizarAvance(statusCargue);
                                    continue;
                                }

                                if (lineas[0].length() == 0) {
                                    sb.append("Linea:" + cantidadRegistroTotal + " - El tipo de Documento no puede ser vacío\n");
                                    cantidadRegistrosErr++;
                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                    statusBusiness.ActualizarAvance(statusCargue);
                                    continue;
                                }
                                if (lineas[2].length() == 0) {
                                    sb.append("Linea:" + cantidadRegistroTotal + " - Primer Apellido no puede ser vacío\n");
                                    cantidadRegistrosErr++;
                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                    statusBusiness.ActualizarAvance(statusCargue);
                                    continue;
                                }
                                if (lineas[4].length() == 0) {
                                    sb.append("Linea:" + cantidadRegistroTotal + " - Primer Nombre no puede ser vacío\n");
                                    cantidadRegistrosErr++;
                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                    statusBusiness.ActualizarAvance(statusCargue);
                                    continue;
                                }
                                if (lineas[6].length() == 0) {
                                    sb.append("Linea:" + cantidadRegistroTotal + " - Municipio de residencia no puede ser vacío\n");
                                    cantidadRegistrosErr++;
                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                    statusBusiness.ActualizarAvance(statusCargue);
                                    continue;
                                }

                                if (!lineas[6].matches("-?\\d+(\\.\\d+)?")) {
                                    sb.append("Linea:" + cantidadRegistroTotal + " - El código de Municipio debe ser numérico\n");
                                    cantidadRegistrosErr++;
                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                    statusBusiness.ActualizarAvance(statusCargue);
                                    continue;
                                }
                                MunicipioDane mcpio = new MunicipioDaneDao().GetMunicipioDaneById(lineas[6]);

                                if (mcpio.getCodigoMunicipio() == null) {
                                    sb.append("Linea:" + cantidadRegistroTotal + " - No existe un municipio registrado con ese código\n");
                                    cantidadRegistrosErr++;
                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                    statusBusiness.ActualizarAvance(statusCargue);
                                    continue;
                                }

                                if (lineas[7].length() == 0) {
                                    sb.append("Linea:" + cantidadRegistroTotal + " - Celular no puede ser vacío\n");
                                    cantidadRegistrosErr++;
                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                    statusBusiness.ActualizarAvance(statusCargue);
                                    continue;
                                }

                                if (lineas[9].length() == 0) {
                                    sb.append("Linea:" + cantidadRegistroTotal + " - Dirección de la persona no puede ser vacía\n");
                                    cantidadRegistrosErr++;
                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                    statusBusiness.ActualizarAvance(statusCargue);
                                    continue;
                                }

                                if (!lineas[7].matches("-?\\d+(\\.\\d+)?")) {
                                    sb.append("Linea:" + cantidadRegistroTotal + " - Celular debe ser numérico\n");
                                    cantidadRegistrosErr++;
                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                    statusBusiness.ActualizarAvance(statusCargue);
                                    continue;
                                }
//                                if (lineas[8].length() > 0 && !lineas[8].matches("-?\\d+(\\.\\d+)?")) {
//                                    sb.append("Linea:" + cantidadRegistroTotal + " - Teléfono de contacto debe ser numérico\n");
//                                    cantidadRegistrosErr++;
//                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
//                                    statusBusiness.ActualizarAvance(statusCargue);
//                                    continue;
//                                }
                                if (!lineas[10].matches("^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,4}$")) {
                                    sb.append("Linea:" + cantidadRegistroTotal + " - Correo electrónico inválido\n");
                                    cantidadRegistrosErr++;
                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                    statusBusiness.ActualizarAvance(statusCargue);
                                    continue;
                                }

                                if (lineas[11].length() == 0) {
                                    sb.append("Linea:" + cantidadRegistroTotal + " - Codigo de actividad economica no puede ser vacio \n");
                                    cantidadRegistrosErr++;
                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                    statusBusiness.ActualizarAvance(statusCargue);
                                    continue;
                                }

                                Empleado empleado = new Empleado();
                                empleado.setTipodoc(lineas[0]);
                                empleado.setNrodoc(cedula);
                                empleado.setApellido1(lineas[2]);
                                empleado.setApellido2(lineas[3]);
                                empleado.setNombre1(lineas[4]);
                                empleado.setNombre2(lineas[5]);
                                empleado.setMunicipioDane(mcpio);
                                empleado.setCelular(lineas[7]);
                                empleado.setTelefono(lineas[8]);
                                empleado.setDireccion(lineas[9]);
                                empleado.setEmail(lineas[10]);
                                empleado.setCodigoActividad(lineas[11]);
                                empleado.setUsuarioCrea(usuario);

                                IEmpleadoImpl iEmpleadoImpl = new IEmpleadoImpl();

                                int idEmpleado = iEmpleadoImpl.InsertarDatosBasicos(empleado);

                                if (idEmpleado == 0) {
                                    sb.append("Linea:" + cantidadRegistroTotal + " - Error al registrar la Persona. Consulte con el Administrador \n");
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
                        } catch (Exception ex) {
                            Logger.getLogger(EmpleadoMasivo.class.getName()).log(Level.SEVERE, null, ex);
                            sb.append("Linea:" + cantidadRegistroTotal + " - Error: " + ex.getMessage() + " \n");
                            cantidadRegistrosErr++;
                            statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                            statusBusiness.ActualizarAvance(statusCargue);
                        }
                    }
                    br.close();
                    statusBusiness.Finalizar(statusCargue, sb);

                } catch (IOException ex) {
                    Logger.getLogger(EmpleadoMasivo.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(EmpleadoMasivo.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(EmpleadoMasivo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fwriter.close();
            } catch (IOException ex) {
                Logger.getLogger(EmpleadoMasivo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
