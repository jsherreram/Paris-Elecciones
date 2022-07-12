/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.OperadorCelular;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eparra
 */
public class OperadorCelularDao {

    private Operaciones conex = new Operaciones();

    public List<OperadorCelular> listar() {
        final List<OperadorCelular> listado = new ArrayList<>();

        try {
            String sql = "select * from operador_celular order by nombre";
            conex.consultar((ResultSet res) -> {
                try {
                    while (res.next()) {
                        OperadorCelular operador = new OperadorCelular();
                        operador.setIdOperador(res.getInt("id_operador"));
                        operador.setNombre(res.getString("nombre"));
                        listado.add(operador);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(MunicipioDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }, sql);

        } catch (Exception e) {
            Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return listado;
    }

}
