/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.AsignacionSalon;
import co.com.grupoasd.nomina.modelo.Nombramiento;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.ejb.Stateless;
import javax.ws.rs.Path;

/**
 *
 * @author ASD
 */

public class AsignacionSalonDao {
    
      private Operaciones conex = new Operaciones();

    public AsignacionSalonDao() {
    }

    
      public Boolean actualizarSalon(int id, String salon) {
         
        Boolean resultado = false;
        try {
            String sql;

            sql = " update asignacion_salon  set salon= ? where id = ?";

            resultado = conex.ejecutar(sql, salon, id);

        } catch (Exception e) {
            Logger.getLogger(AsignacionSalonDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return resultado;

    }
      
       public Boolean InsertarSalon(AsignacionSalon salon) {
           
        Boolean resultado = false;
        try {
            StringBuilder sql= new StringBuilder();
            sql.append("INSERT INTO asignacion_salon (idprueba, iddivipol, idempleado, salon) ");
            sql.append(" VALUES(?, ?, ?, ?)");

            resultado = conex.ejecutar(sql.toString(), salon.getPrueba().getIdprueba(), salon.getSitio(),
                    salon.getEmpleado().getIdEmpleado(), salon.getSalon());

        } catch (Exception e) {
            Logger.getLogger(AsignacionSalonDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return resultado;
        

    }
      
      public AsignacionSalon buscarAsignacionSalon(int idPrueba, int idDivipol, int idEmpleado){
      
       final AsignacionSalon salon = new AsignacionSalon();
        try {
            String sql = "select id from asignacion_salon where idprueba=? and iddivipol=? and idempleado=?";
            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {

                    try {
                        while (res.next()) {
                          salon.setId(res.getInt("id"));
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(AsignacionSalonDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql,idPrueba, idDivipol, idEmpleado);

        } catch (Exception e) {
            Logger.getLogger(AsignacionSalon.class.getName()).log(Level.SEVERE, null, e);
        }
        return salon;
      }
    
}
