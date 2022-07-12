/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.Encuesta;
import co.com.grupoasd.nomina.modelo.ParametroEncuesta;
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
public class ParametroEncuestaDao {

    private Operaciones conex = new Operaciones();

    public ParametroEncuestaDao() {
    }

    public ParametroEncuesta GetParametroEvento(int idEvento, String hora) {

        final ParametroEncuesta pEncuesta = new ParametroEncuesta();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" select ts.tipoSesion, p.* from ");
            sql.append(" parametro_encuesta p ");
            sql.append(" inner join tipo_sesion ts on ts.Id=p.idTipoSesion ");
            sql.append(" inner join evento e on  e.tipoSesion=ts.tipoSesion ");
            sql.append(" where e.codigoEvento=");
            sql.append(idEvento);
            sql.append(" and '");
            sql.append(hora);
            sql.append("' between p.horaInicio and p.horaFin ");


            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {

                    try {
                        while (res.next()) {
                            pEncuesta.setIdParametro(res.getInt("idParametro"));
                            pEncuesta.setTipoSesion(res.getString("tipoSesion"));
                            pEncuesta.setParteEncuesta(res.getString("parteEncuesta"));
                            pEncuesta.setHoraInicio(res.getString("horaInicio"));
                            pEncuesta.setHoraFin(res.getString("horaFin"));
                            pEncuesta.setHoraInicioAlerta(res.getString("horaInicioAlerta"));

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EncuestaDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql.toString());

        } catch (Exception e) {
            Logger.getLogger(Encuesta.class.getName()).log(Level.SEVERE, null, e);
        }
        return pEncuesta;
    }

    public List<ParametroEncuesta> listarParametrosEncuestaPorEvento(int idEvento) {

        final List<ParametroEncuesta> listado = new ArrayList<ParametroEncuesta>();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" select ts.tipoSesion, p.* from ");
            sql.append(" parametro_encuesta p ");
            sql.append(" inner join tipo_sesion ts on ts.Id=p.idTipoSesion ");
            sql.append(" inner join evento e on  e.tipoSesion=ts.tipoSesion ");
            sql.append(" where e.codigoEvento=");
            sql.append(idEvento);


            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {

                    try {
                        while (res.next()) {
                            ParametroEncuesta pEncuesta = new ParametroEncuesta();
                            pEncuesta.setIdParametro(res.getInt("idParametro"));
                            pEncuesta.setTipoSesion(res.getString("tipoSesion"));
                            pEncuesta.setParteEncuesta(res.getString("parteEncuesta"));
                            pEncuesta.setHoraInicio(res.getString("horaInicio"));
                            pEncuesta.setHoraFin(res.getString("horaFin"));
                            pEncuesta.setHoraInicioAlerta(res.getString("horaInicioAlerta"));
                            listado.add(pEncuesta);

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EncuestaDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql.toString());

        } catch (Exception e) {
            Logger.getLogger(Encuesta.class.getName()).log(Level.SEVERE, null, e);
        }
        return listado;
    }

}
