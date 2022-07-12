/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.Estado;
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
public class EstadoDao {

    private Operaciones conex = new Operaciones();

    public EstadoDao() {

    }

    public List<Estado> listar() {
        final List<Estado> Estados = new ArrayList<>();

        try {
            conex.consultar("select * from estado", new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            Estado estado = new Estado();
                            estado.setCodigoEstado(res.getInt("codigoEstado"));
                            estado.setDescripcion(res.getString("descripcion"));
                            Estados.add(estado);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EstadoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (Exception e) {
            Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return Estados;
    }

    public Estado GetById(String id) {

        final Estado estado = new Estado();

        try {
            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            estado.setCodigoEstado(res.getInt("codigoEstado"));
                            estado.setDescripcion(res.getString("descripcion"));
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EstadoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, "select * from estado where codigoEstado = ?", id);

        } catch (Exception e) {
            Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return estado;
    }

}
