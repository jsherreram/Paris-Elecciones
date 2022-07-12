/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.ZonaIcfes;
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
public class ZonaIcfesDao {

    private Operaciones conex = new Operaciones();

    public ZonaIcfesDao() {

    }

    public List<ZonaIcfes> listar() {
        final List<ZonaIcfes> zonas = new ArrayList<>();
        String sql = "select * from zona_icfes order by nombre";
        conex.consultar(sql, new Operaciones.OperacionConsulta() {

            @Override
            public void respuestaConsulta(ResultSet res) {
                try {
                    while (res.next()) {
                        ZonaIcfes zona = new ZonaIcfes();
                        zona.setIdZona(res.getInt("idzona"));
                        zona.setNombre(res.getString("nombre"));
                        zonas.add(zona);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ZonaIcfesDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

        return zonas;
    }

     public ZonaIcfes GetZonaById(int idzona) {

        final ZonaIcfes zona = new ZonaIcfes();

        try {
            String sql = "select * from zona_icfes where  idzona = ?";
            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            zona.setIdZona(res.getInt("idzona"));
                            zona.setNombre(res.getString("nombre"));       
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(ZonaIcfesDao.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }, sql, idzona);

        } catch (Exception e) {
            Logger.getLogger(ZonaIcfesDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return zona;
    }
}
