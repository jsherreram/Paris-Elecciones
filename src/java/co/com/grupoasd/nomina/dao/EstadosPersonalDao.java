/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.EstadoEmpleado;
import co.com.grupoasd.nomina.modelo.seguimiento.ResultSetConverter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;

public class EstadosPersonalDao {

    private Operaciones conex = new Operaciones();

    public List<EstadoEmpleado> listar() {
        final List<EstadoEmpleado> estados = new ArrayList<EstadoEmpleado>();

        try {
            String sql = "select idfalta, descripcion from msuspension where estado = '1';";

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            EstadoEmpleado estado = new EstadoEmpleado();
                            estado.setCodigoEstado(res.getInt("idfalta"));
                            estado.setDescripcion(res.getString("descripcion"));
                            estados.add(estado);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EstadosPersonalDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql);

        } catch (Exception e) {
            Logger.getLogger(UsuarioSitioDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return estados;
    }    
    
}
