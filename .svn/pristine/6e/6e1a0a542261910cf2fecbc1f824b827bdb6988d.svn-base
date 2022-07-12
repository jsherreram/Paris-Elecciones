/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.Actividad;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pedro Rodriguez
 */
public class ActividadDao {

    private Operaciones conex = new Operaciones();

    public ActividadDao() {

    }

    public List<Actividad> listar() {
        final List<Actividad> actividades = new ArrayList<>();

        String query = "SELECT * FROM actividad";

        try {
            conex.consultar(query, new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            Actividad actividad = new Actividad();
                            actividad.setCodigo(res.getString("codigo"));
                            actividad.setDescripcion(res.getString("descripcion"));
                            actividades.add(actividad);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(ActividadDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            });

        } catch (Exception e) {
            Logger.getLogger(ActividadDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return actividades;
    }

}
