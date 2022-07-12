/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.salones;

import co.com.grupoasd.nomina.dao.SalonesDao;
import co.com.grupoasd.nomina.dao.DivipolDao;
import co.com.grupoasd.nomina.dao.StatusCargueDao;
import co.com.grupoasd.nomina.modelo.Orden;
import co.com.grupoasd.nomina.modelo.StatusCargue;
import co.com.grupoasd.nomina.negocio.statusCargue.StatusBusiness;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wilfer
 */

public class Salones {

    public Salones(){
        
    }
    
    public void cargarSalones(String nombreArchivoOrigen, String pathArchivo, int codigoPrueba, String user){
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
                StringBuilder sb        =   new StringBuilder();
                StringBuilder ejecutar  =   new StringBuilder();
                SalonesDao salonesdao   =   new SalonesDao();
                String s_anterior       =   "";
                boolean continuar;

                int cantidadRegistrosOk=0;
                int cantidadRegistrosErr=0;

                BufferedReader br;
                String line = "";
                String cvsSplitBy = ";";

                //Contar las filas del archivo
                br = new BufferedReader(new FileReader(csvFile));
                while ((line = br.readLine()) != null) {
                    if (cantidadRegistroTotal != 0){
                        ejecutar.delete(0, ejecutar.length());
                        String[] lineas = line.split(cvsSplitBy);
                        if ( s_anterior != lineas[1] ) {
                            ejecutar.append(" update detalle_cargo_x_puesto_x_evento dcpe,cargos c set dcpe.salon = null ");
                            ejecutar.append(" where c.consalon = 1 ");
                            ejecutar.append(" and   dcpe.idPrueba=").append(Integer.toString(idPrueba));
                            ejecutar.append(" and   dcpe.codigoCargo= c.codigoCargo ");
                            ejecutar.append(" and   dcpe.codigoPuesto='").append(lineas[0]).append("';");
                            salonesdao.Ejecutar(ejecutar.toString());
                            s_anterior = lineas[0] ;
                        }
                    }
                    cantidadRegistroTotal ++;
                }
                br.close();

                //Agregar el registro en la tabla de status_cargue para hacer seguiiento del proceso
                StatusBusiness statusBusiness = new StatusBusiness();
                StatusCargue statusCargue = new StatusCargue();
                statusCargue.setUsuario(usuario);
                statusCargue.setIdtipoCargue(6); //tipo de cargue Cargue Salon
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

                while ((line = br.readLine()) != null) {
                    if (cantidadRegistroTotal ==0){
                        //Esta linea corresponde al titulo
                        cantidadRegistroTotal ++;
                    }else
                    {String[] lineas = line.split(cvsSplitBy);
                    cantidadRegistroTotal ++;
                    continuar = false;

                    orden   =   divipol.getIdByCodigoSitio(lineas[0], Integer.toString(idPrueba));
                    if (orden.getIdDivipolStio() == null ){
                        sb.append("Linea:"+cantidadRegistroTotal+ " - Código de Sitio Aún Registrado para Esta Prueba.\n");
                        if(continuar == false){cantidadRegistrosErr ++;}
                        statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                        statusBusiness.ActualizarAvance(statusCargue);
                        continuar = true;
                    }

                    if ( lineas[1].length() < 1){
                        sb.append("Linea:"+cantidadRegistroTotal+ " - Salón al Parecer Vacío\n");
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

                    ejecutar.append("call sp_asignacion_salon_dcpe (").append(Integer.toString(idPrueba)).append(",");
                    ejecutar.append("'").append(lineas[0]).append("',");
                    ejecutar.append("'").append(lineas[1]).append("'").append(");\n");
                    }
                    salonesdao.Ejecutar(ejecutar.toString());
                    statusCargue.setCantidadRegistrosProcesadosOk(cantidadRegistroTotal - cantidadRegistrosErr - 1);
                    statusBusiness.ActualizarAvance(statusCargue);
                }
                br.close();

                statusBusiness.Finalizar(statusCargue, sb);
            } catch (IOException ex) {
                Logger.getLogger(Salones.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
            Logger.getLogger(Salones.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Salones.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Salones.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fwriter.close();
            } catch (IOException ex) {
                Logger.getLogger(Salones.class.getName()).log(Level.SEVERE, null, ex);
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
