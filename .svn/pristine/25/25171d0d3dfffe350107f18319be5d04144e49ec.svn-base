/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.negocio.aceptacionConvocatoria;

import co.com.grupoasd.nomina.dao.CargoDao;
import co.com.grupoasd.nomina.dao.EmpleadoDao;
import co.com.grupoasd.nomina.dao.EmpleadoPruebaEstadoDao;
import co.com.grupoasd.nomina.dao.PruebaDao;
import co.com.grupoasd.nomina.dao.StatusCargueDao;
import co.com.grupoasd.nomina.modelo.Cargo;
import co.com.grupoasd.nomina.modelo.Empleado;
import co.com.grupoasd.nomina.modelo.EmpleadoPruebaEstado;
import co.com.grupoasd.nomina.modelo.Prueba;
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
 * @author Pedro Rodríguez
 */
public class AceptacionConvocatoria {
    
    
    public AceptacionConvocatoria(){
        
    }
    
    public void cargarAceptacionConvocatoria(String nombreArchivoOrigen, String pathArchivo, int codigoPrueba, String user){
            final String csvFile = pathArchivo;
            final String usuario = user;
            final int idPrueba = codigoPrueba;
            final String nombreArchivo = nombreArchivoOrigen;
            
            Thread hilo;
            hilo = new Thread() {
            @Override
            public void run(){
                    try {
                        int cantidadRegistroTotal=0;
                        int cantidadRegistrosOk=0;
                        int cantidadRegistrosErr=0;
                        
                        BufferedReader br = null;
                        String line = "";
                        String cvsSplitBy = ";";

                        Prueba prueba = new PruebaDao().getById(idPrueba);
                        String textoVtiger = "Fecha de Aplicación:"+ prueba.getFechaaplicacion()+"\n"; 
                        
                        //Contar las filas del archivo 
                        br = new BufferedReader(new FileReader(csvFile));
                        while ((line = br.readLine()) != null) {
                            cantidadRegistroTotal ++;
                        }
                        br.close();

                        //Agregar el registro en la tabla de status_cargue para hacer seguiiento del proceso
                        StatusBusiness statusBusiness = new StatusBusiness();
                        StatusCargue statusCargue = new StatusCargue();
                        statusCargue.setUsuario(usuario);
                        statusCargue.setIdtipoCargue(2); //tipo de cargue Aceptación convocatoria
                        statusCargue.setNombreArchivo(nombreArchivo);
                        statusCargue.setEstadoStatus(0); //estado: 0 en proceso, 1 procesado
                        statusCargue.setCantidadRegistrosTotal(cantidadRegistroTotal-1);
                        statusCargue.setCantidadRegistrosProcesadosOk(cantidadRegistrosOk);
                        statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                        int idStatus = statusBusiness.Insertar(statusCargue);
                        statusCargue.setId(idStatus);
                        
                        cantidadRegistroTotal =0;
                        br = new BufferedReader(new FileReader(csvFile));
                        EmpleadoDao empleadoDao = new EmpleadoDao();
                        EmpleadoPruebaEstadoDao empleadoPruebaEstadoDao = new EmpleadoPruebaEstadoDao();
                        CargoDao cargoDao = new CargoDao();
                        int idEmpleado = 0;
                        StringBuilder sb =new StringBuilder();
                        
                        while ((line = br.readLine()) != null) {
                                if (cantidadRegistroTotal ==0){
                                    //Esta linea corresponde al titulo
                                    cantidadRegistroTotal ++;
                                }else
                                {
                                        String[] lineas = line.split(cvsSplitBy);
                                        cantidadRegistroTotal ++;

                                        //Validar la estructura
                                        if(lineas.length < 2)
                                        {
                                            sb.append("Linea:"+cantidadRegistroTotal+ " - Número de columnas invalido. se requiere identificación y código cargo\n");
                                            cantidadRegistrosErr ++;
                                            statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                            statusBusiness.ActualizarAvance(statusCargue);
                                            continue;
                                        }

                                        if(!lineas[0].matches("-?\\d+(\\.\\d+)?") && !lineas[1].matches("-?\\d+(\\.\\d+)?"))
                                        {
                                            sb.append("Linea:"+cantidadRegistroTotal+ " - Número de Identificación debe ser numérico\n");
                                            sb.append("Linea:"+cantidadRegistroTotal+ " - Código de Cargo debe ser numérico\n");
                                            cantidadRegistrosErr ++;
                                            statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                            statusBusiness.ActualizarAvance(statusCargue);
                                            continue;
                                        }

                                        if(!lineas[0].matches("-?\\d+(\\.\\d+)?"))
                                        {
                                            sb.append("Linea:"+cantidadRegistroTotal+ " - Número de Identificación debe ser numérico\n");
                                            cantidadRegistrosErr ++;
                                            statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                            statusBusiness.ActualizarAvance(statusCargue);
                                            continue;
                                        }

                                        if(lineas[0].length() > 10)
                                        {
                                            sb.append("Linea:"+cantidadRegistroTotal+ " - Número de Identificación tiene mas de 10 digitos, no permitido\n");
                                            cantidadRegistrosErr ++;
                                            statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                            statusBusiness.ActualizarAvance(statusCargue);
                                            continue;
                                        }

                                        if(lineas[0].length() == 10 && !lineas[0].substring(0,1).equals("1"))
                                        {
                                            sb.append("Linea:"+cantidadRegistroTotal+ " - Número de Identificación no valido\n");
                                            cantidadRegistrosErr ++;
                                            statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                            statusBusiness.ActualizarAvance(statusCargue);
                                            continue;
                                        }


                                        if(!lineas[1].matches("-?\\d+(\\.\\d+)?"))
                                        {
                                            sb.append("Linea:"+cantidadRegistroTotal+ " - Código de Cargo debe ser numérico\n");
                                            cantidadRegistrosErr ++;
                                            statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                            statusBusiness.ActualizarAvance(statusCargue);
                                            continue;
                                        }

                                        EmpleadoPruebaEstado empleadoPruebaEstado = new EmpleadoPruebaEstado();

                                        //consultar idEmpleado con la cedula
                                        idEmpleado = empleadoDao.GetIdByNumeroDocumento(Long.getLong(lineas[0]));

                                        if(idEmpleado == 0) {
                                            sb.append("Linea:"+cantidadRegistroTotal+ " - Identificación no existe\n");
                                            cantidadRegistrosErr ++;
                                            statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                            statusBusiness.ActualizarAvance(statusCargue);
                                            continue;
                                        }

                                        Empleado empleado = empleadoDao.GetById(idEmpleado);
                                        Cargo cargo = cargoDao.GetById(lineas[1]);
                                        if(cargo.getCodigoCargo() == null ){
                                            sb.append("Linea:"+cantidadRegistroTotal+ " - Código de Cargo no existe\n");
                                            cantidadRegistrosErr ++;
                                            statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                            statusBusiness.ActualizarAvance(statusCargue);
                                            continue;
                                        }

                                        empleadoPruebaEstado =  empleadoPruebaEstadoDao.getByID(empleadoPruebaEstado.getIdEmpleado(), empleadoPruebaEstado.getIdprueba(), cargo.getCodigoCargo());
                                                
                                        if (empleadoPruebaEstado.getIdestpersona() == 0 )
                                        {
                                            sb.append("Linea:"+cantidadRegistroTotal+ " - No existe información para esta prueba, identificación y cargo \n");
                                            cantidadRegistrosErr ++;
                                        }else
                                        {
                                            if (empleadoPruebaEstado.getIdestpersona() == 7)
                                            {
                                                sb.append("Linea:"+cantidadRegistroTotal+ " - Ya existe confirmacion de convocatoria \n");
                                                cantidadRegistrosErr ++;
                                            }else
                                            {
                                                if (empleadoPruebaEstado.getIdestpersona() != 3)
                                                {
                                                    sb.append("Linea:"+cantidadRegistroTotal+ " - Registro en estado no valido para aceptar convocatoria \n");
                                                    cantidadRegistrosErr ++;
                                                }else
                                                {
                                                    empleadoPruebaEstado.setIdestpersona(7); //Estado aceptacón d convocatoria
                                                    empleadoPruebaEstado.setUsuario(usuario);
                                                    empleadoPruebaEstadoDao.actualizarEstado(empleadoPruebaEstado);
                                                    cantidadRegistrosOk ++;
                                                }
                                            }
                                        }

                                statusCargue.setCantidadRegistrosProcesadosOk(cantidadRegistrosOk);
                                statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                statusBusiness.ActualizarAvance(statusCargue);
                            }                        
                        }
                        br.close();
                        statusBusiness.Finalizar(statusCargue, sb);
                        
                    } catch (IOException ex) {
                        Logger.getLogger(AceptacionConvocatoria.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            };

            hilo.start();
    }
    
    public void GeneraArchivoErrores(int idStatusCargue){

        FileWriter fwriter = null;
        try {
            String nombreArchivo = "/data/tmp/ERR_"+ idStatusCargue+".csv";
            StatusCargueDao statusCargueDao = new StatusCargueDao();
            fwriter = new FileWriter(nombreArchivo);            
            fwriter.write(statusCargueDao.getErrores(idStatusCargue));
            fwriter.flush();
            fwriter.close();           
        } catch (IOException ex) {
            Logger.getLogger(AceptacionConvocatoria.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fwriter.close();
            } catch (IOException ex) {
                Logger.getLogger(AceptacionConvocatoria.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }        
    
}