/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.cargueaplicacion;

import co.com.grupoasd.nomina.dao.AplicacionDao;
import co.com.grupoasd.nomina.dao.CargoDao;
import co.com.grupoasd.nomina.dao.CuentaCobroDao;
import co.com.grupoasd.nomina.dao.DivipolDao;
import co.com.grupoasd.nomina.dao.PruebaDao;
import co.com.grupoasd.nomina.dao.StatusCargueDao;
import co.com.grupoasd.nomina.modelo.Cargo;
import co.com.grupoasd.nomina.modelo.Prueba;
import co.com.grupoasd.nomina.modelo.Orden;
import co.com.grupoasd.nomina.modelo.StatusCargue;
import co.com.grupoasd.nomina.negocio.statusCargue.StatusBusiness;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.apache.commons.lang3.BooleanUtils.or;

/**
 *
 * @author Wilfer
 */

public class Aplicacion {

    public Aplicacion(){
        
    }
    
    public void cargarAplicacion(String nombreArchivoOrigen, String pathArchivo, int codigoPrueba, String user){
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
                boolean continuar;
                boolean habilitado;
                //String pruebaPrimeraFila="";
                //String pruebaActual="";
                StringBuilder ejecutar = new StringBuilder();
                int cantidadRegistrosOk=0;
                int cantidadRegistrosErr=0;

                BufferedReader br;
                String line = "";
                String cvsSplitBy = ";";

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
                statusCargue.setIdtipoCargue(4); //tipo de cargue Cuadro Aplicación
                statusCargue.setNombreArchivo(nombreArchivo);
                statusCargue.setEstadoStatus(0); //estado: 0 en proceso, 1 procesado
                statusCargue.setCantidadRegistrosTotal(cantidadRegistroTotal-1);
                statusCargue.setCantidadRegistrosProcesadosOk(cantidadRegistrosOk);
                statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                int idStatus = statusBusiness.Insertar(statusCargue);
                statusCargue.setId(idStatus);

                cantidadRegistroTotal =0;
                br = new BufferedReader(new FileReader(csvFile));
                DivipolDao  divipol =   new DivipolDao();
                Orden orden;
                CargoDao    cargodao=   new CargoDao();
                AplicacionDao aplicaciondao = new AplicacionDao();
                List<String> lst    =   aplicaciondao.CargosHabilitados(idPrueba);
                
                String  ls_valor;
                Cargo cargo;
                StringBuilder sb    =   new StringBuilder();
                
                while ((line = br.readLine()) != null) {
                    if (cantidadRegistroTotal ==0){
                        //Esta linea corresponde al titulo
                        cantidadRegistroTotal ++;
                    }else
                    {String[] lineas = line.split(cvsSplitBy);
                    cantidadRegistroTotal ++;
                    continuar = false;
                    habilitado= false;

                    orden   =   divipol.getIdByCodigoSitio(lineas[0], Integer.toString(idPrueba));
                    if (orden.getIdDivipolStio() == null ){
                        sb.append("Linea:"+cantidadRegistroTotal+ " - Código de Sitio Aún Registrado para Esta Prueba.\n");
                        if(continuar == false){cantidadRegistrosErr ++;}
                        statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                        statusBusiness.ActualizarAvance(statusCargue);
                        continuar = true;
                    }
                    cargo   =  cargodao.GetById(lineas[1]);
                    if ( cargo.getCodigoCargo() == null ){
                        sb.append("Linea:"+cantidadRegistroTotal+ " - Código de Cargo Aún no Registrado, Favor Registrar Primero el Cargo\n");
                        if(continuar == false){cantidadRegistrosErr ++;}
                        statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                        statusBusiness.ActualizarAvance(statusCargue);
                        continuar = true;
                    }else{Iterator<String> itr = lst.iterator();
                        while(itr.hasNext()){
                            ls_valor    =   itr.next();
                            if(habilitado == false){
                                if ( ls_valor.equals(cargo.getCodigoCargo()) ){
                                    habilitado= true;
                                }
                            }
                        }
                        if(habilitado == false){
                            sb.append("Linea:"+cantidadRegistroTotal+ " - Para el Cargo("+cargo.getCodigoCargo()+"), No se Tienen Eventos/Sesiones, Verificar las Sesiones Estén ya Registradas, con su Tipo de Sesión Indicada. \n");
                            if(continuar == false){cantidadRegistrosErr ++;}
                            statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                            statusBusiness.ActualizarAvance(statusCargue);
                            continuar = true;
                        }
                    }

                    if(!lineas[2].matches("-?\\d+(\\.\\d+)?"))
                    {   sb.append("Linea:"+cantidadRegistroTotal+ " - Número(Cantidad) debe Ser Númerico\n");
                        if(continuar == false){cantidadRegistrosErr ++;}
                        statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                        statusBusiness.ActualizarAvance(statusCargue);
                        continuar = true;
                    }

                    if (Integer.parseInt(lineas[2]) < 1){
                        sb.append("Linea:"+cantidadRegistroTotal+ " - Cantidad debe debería Ser Mayor que CERO(0)\n");
                        if(continuar == false){cantidadRegistrosErr ++;}
                        statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                        statusBusiness.ActualizarAvance(statusCargue);
                        continuar = true;
                    }
                    if (continuar == true){
                        statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                        statusBusiness.ActualizarAvance(statusCargue);
                        continue; 
                    }
                    
                    ejecutar.delete(0, ejecutar.length());

                    ejecutar.append("call sp_cuadro_aplicacion (").append(Integer.toString(idPrueba)).append(",");
                    ejecutar.append("'").append(lineas[0]).append("',");
                    ejecutar.append(lineas[1]).append(",");
                    ejecutar.append(lineas[2]).append(");\n");

                    }
                    aplicaciondao.Ejecutar(ejecutar.toString());
                    statusCargue.setCantidadRegistrosProcesadosOk(cantidadRegistroTotal - cantidadRegistrosErr - 1);
                    statusBusiness.ActualizarAvance(statusCargue);
                }
                br.close();

                statusBusiness.Finalizar(statusCargue, sb);
            } catch (IOException ex) {
                Logger.getLogger(Aplicacion.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
            Logger.getLogger(Aplicacion.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Aplicacion.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Aplicacion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fwriter.close();
            } catch (IOException ex) {
                Logger.getLogger(Aplicacion.class.getName()).log(Level.SEVERE, null, ex);
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
