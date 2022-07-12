/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.EstadoEmpleado;
import java.sql.PreparedStatement;
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
public class EstadoEmpleadoDao {

    private Operaciones conex = new Operaciones();

    public EstadoEmpleadoDao() {

    }

    public List<EstadoEmpleado> listar() {
        final List<EstadoEmpleado> Estados = new ArrayList<>();

        try {
            conex.consultar("select * from estadoempleado", new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            EstadoEmpleado estado = new EstadoEmpleado();
                            estado.setCodigoEstado(res.getInt("codigoestado"));
                            estado.setDescripcion(res.getString("descripcion"));
                            Estados.add(estado);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EstadoEmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (Exception e) {
            Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return Estados;
    }

    public EstadoEmpleado GetById(int id) {

        final EstadoEmpleado estado = new EstadoEmpleado();

        try {
            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            estado.setCodigoEstado(res.getInt("codigoestado"));
                            estado.setDescripcion(res.getString("descripcion"));
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EstadoEmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, "select * from estadoempleado where codigoestado = ?", id);

        } catch (Exception e) {
            Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return estado;
    }

}
