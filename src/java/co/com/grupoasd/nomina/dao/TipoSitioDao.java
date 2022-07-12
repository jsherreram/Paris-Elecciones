/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.TipoSitio;
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
public class TipoSitioDao {
    
    private Operaciones conex= new Operaciones();
    
    public TipoSitioDao()
    {
        
    }
    
    public List<TipoSitio> listar() {
     
     final List <TipoSitio> tsitio= new ArrayList<TipoSitio>();


     try {
         
         String sql=" select * from tipo_sitio ";
         
         conex.consultar( new Operaciones.OperacionConsulta() {

             @Override
             public void respuestaConsulta(ResultSet res) {
                 try {
                     while(res.next()){
                         TipoSitio ts=new TipoSitio();
                        ts.setIdTipoSitio(res.getInt("idTipoSitio"));
                        ts.setNombre(res.getString("nombre"));
                        tsitio.add(ts);
                     }     } catch (SQLException ex) {
                     Logger.getLogger(SitioDao.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
         }, sql);        
           
     } catch (Exception e) {
           Logger.getLogger(TipoSitioDao.class.getName()).log(Level.SEVERE, null, e);
     }
     return tsitio;
    }     
    public TipoSitio GetById(final int idTipo) {

        final TipoSitio tsitio= new TipoSitio();

        try {

            String sql = " select * from tipo_sitio where idTipoSitio= ? ; ";
            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                         tsitio.setIdTipoSitio(res.getInt("idTipoSitio"));
                         tsitio.setNombre(res.getString("nombre"));
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(SitioDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql, idTipo);

        } catch (Exception e) {
            Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return tsitio;
    }
    
}
