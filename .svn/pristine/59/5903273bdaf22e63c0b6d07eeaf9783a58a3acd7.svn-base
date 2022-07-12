/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.DbConnection;
import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.Banco;
import co.com.grupoasd.nomina.modelo.MedioPago;
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
public class BancoDao {

    private Operaciones conex = new Operaciones();

    public BancoDao() {

    }

    public List<Banco> listar() {
        final List<Banco> lstBanco = new ArrayList<>();

        try {
            StringBuilder query = new StringBuilder();
            query.append(" select ");
            query.append(" idbanco,");
            query.append(" codigo,");
            query.append(" nombre,");
            query.append(" activo ");
            query.append(" FROM banco where activo=1 ");

            conex.consultar(query.toString(), new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            Banco banco = new Banco();
                            banco.setIdBanco(res.getInt("idbanco"));
                            banco.setCodigo(res.getString("codigo"));
                            banco.setNombre(res.getString("nombre"));
                            banco.setActivo(res.getInt("activo"));
                            lstBanco.add(banco);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(BancoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (Exception e) {
            Logger.getLogger(BancoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return lstBanco;
    }

}
