/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.EstadoSitio;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASD
 */
public class EstadoSitioDao {
    
     private Operaciones conex = new Operaciones();
     
     public EstadoSitioDao(){
     }
     
     public List<EstadoSitio> listar() {
        final List<EstadoSitio> estados = new ArrayList<>();
        String sql = "select * from estado_sitio";
        conex.consultar(sql, new Operaciones.OperacionConsulta() {

            @Override
            public void respuestaConsulta(ResultSet res) {
                try {
                    while (res.next()) {
                        EstadoSitio estado = new EstadoSitio();
                        estado.setIdEstadoSitio(res.getInt("idEstadoSitio"));
                        estado.setDescripcion(res.getString("descripcion"));
                        estados.add(estado);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(EstadoSitioDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }


        });

        return estados ;
    }

     public EstadoSitio GetEstadoSitioById(int idEstado) {

        final EstadoSitio estado= new EstadoSitio();

        try {
            String sql = "select * from estado_sitio where  idEstadoSitio= ?";
            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            estado.setIdEstadoSitio(res.getInt("idEstadoSitio"));
                            estado.setDescripcion(res.getString("descripcion"));       
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EstadoSitioDao.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }, sql, idEstado);

        } catch (Exception e) {
            Logger.getLogger(EstadoSitioDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return estado;
    }
    
}
