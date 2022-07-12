/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.seguimiento.ResultSetConverter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;

/**
 *
 * @author ASD
 */
public class SitioEventoValidadoDao {

    private Operaciones conex = new Operaciones();

    public SitioEventoValidadoDao() {
    }
    
    public boolean insertarSitioEventoValidado(int idDivipol, int idEvento, String usuario) {

        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO sitio_evento_validado (iddivipol, codigoevento, usuario_valida) ");
        sql.append(" VALUES(?,?,?)");
        boolean inserto = conex.ejecutar(sql.toString(), idDivipol, idEvento, usuario);
        return inserto;

    }

    public String buscarSitioEventoValidado(int iddivipol, int idevento) {

        final Object[] result = new Object[1];
        JSONArray json = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("select count(*) as count from sitio_evento_validado s where s.iddivipol=").append(iddivipol);
            sql.append(" and s.codigoevento=").append(idevento);

            conex.consultar(new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        result[0] = ResultSetConverter.convert(res);
                    } catch (SQLException ex) {
                        Logger.getLogger(SitioDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(SitioDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql.toString());
            json = (JSONArray) result[0];
        } catch (Exception e) {
            Logger.getLogger(SitioDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return json.toString();
    }

}
