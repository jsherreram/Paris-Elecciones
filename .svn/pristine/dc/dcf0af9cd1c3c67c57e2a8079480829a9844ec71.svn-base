/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.Banco;
import co.com.grupoasd.nomina.modelo.TipoCuenta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Iván Vargas
 */
public class TipoCuentaDao {

    private Operaciones conex = new Operaciones();

    public TipoCuentaDao() {

    }

    public List<TipoCuenta> listar() {
        final List<TipoCuenta> lstTipoCta = new ArrayList<>();

        try {
            StringBuilder query = new StringBuilder();
            query.append(" select ");
            query.append(" idTipoCuenta,");
            query.append(" nombre ");
            query.append(" FROM tipo_cuenta where activo=1 ");

            conex.consultar(query.toString(), new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            TipoCuenta tipoCta = new TipoCuenta();
                            tipoCta.setIdTipoCuenta(res.getInt("idTipoCuenta"));
                            tipoCta.setNombre(res.getString("nombre"));
                            lstTipoCta.add(tipoCta);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(TipoCuentaDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (Exception e) {
            Logger.getLogger(TipoCuentaDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return lstTipoCta;
    }

}
