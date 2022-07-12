/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.TipoDocumento;

import co.com.grupoasd.nomina.modelo.seguimiento.ResultSetConverter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;

public class TipoDocumentoDao {

    private Operaciones conex = new Operaciones();

    public List<TipoDocumento> listar() {
        final List<TipoDocumento> tiposDocumento = new ArrayList<>();

        try {
            String sql = "select codigo,descripcion from tipo_iden;";

            conex.consultar(sql, new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            TipoDocumento tipo = new TipoDocumento();
                            int col = 0;
                            tipo.setCodigo(res.getString(++col));
                            tipo.setDescripcion(res.getString(++col));
                            tiposDocumento.add(tipo);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(TipoDocumentoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (Exception e) {
            Logger.getLogger(UsuarioSitioDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return tiposDocumento;
    }

    public List<TipoDocumento> listarValidoCargo() {
        final List<TipoDocumento> tiposDocumento = new ArrayList<>();

        try {
            String sql = "select codigo,descripcion from tipo_iden where validoCargo = 1;";

            conex.consultar(sql, new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            TipoDocumento tipo = new TipoDocumento();
                            int col = 0;
                            tipo.setCodigo(res.getString(++col));
                            tipo.setDescripcion(res.getString(++col));
                            tiposDocumento.add(tipo);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(TipoDocumentoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (Exception e) {
            Logger.getLogger(UsuarioSitioDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return tiposDocumento;
    }

}
