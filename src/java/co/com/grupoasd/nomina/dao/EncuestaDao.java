/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.Encuesta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pedro Rodriguez
 */
public class EncuestaDao {

    private Operaciones conex = new Operaciones();

    public EncuestaDao() {

    }

    public Boolean insertar(Encuesta encuesta) {

        Boolean resultado = false;
        StringBuilder sql = new StringBuilder();
        sql.append(" insert into encuesta (idDivipol, codigoEvento, estaListo, horaLLegada, cantidadAsistio,");
        sql.append(" cantidadNoAsistio, cantidadAnulados, motivosAnulacion, novedades, usuario, fecha, actualizado,");
        sql.append(" horaLlegadaDelegado, horaInicioSesion, horaFinSesion, horaDevolucionMaterial,");
        sql.append(" salonesListos, novedadesSitio, estadoEncuesta)");
        sql.append(" values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, current_timestamp, 0, ?, ?, ?, ?, ?, ?, ?)");

        resultado=conex.ejecutar(sql.toString(),
                encuesta.getIdDivipol(),
                encuesta.getCodigoEvento(),
                encuesta.getEstaListo(),
                encuesta.getHoraLlegada(),
                encuesta.getCantidadAsistio(),
                encuesta.getCantidadNoAsistio(),
                encuesta.getCantidadAnulados(),
                encuesta.getMotivosAnulacion(),
                encuesta.getNovedades(),
                encuesta.getUsuario(),
                encuesta.getHoraLlegadaDelegado(),
                encuesta.getHoraInicioSesion(),
                encuesta.getHoraFinSesion(),
                encuesta.getHoraDevolucionMaterial(),
                encuesta.getSalonesListos(),
                encuesta.getNovedadesSitio(),
                encuesta.getEstadoEncuesta());
        return resultado;
    }

    public Boolean actualizar(Encuesta encuesta) {
  
        Boolean resultado = false;
        String sql = " update encuesta set "
                + " estaListo =?, "
                + " horaLlegada=?, "
                + " cantidadAsistio=?, "
                + " cantidadNoAsistio=?, "
                + " cantidadAnulados=?,"
                + " motivosAnulacion=?, "
                + " novedades=?, "
                + " usuario=?,"
                + " fecha = current_timestamp, "
                + " actualizado=1, "
                + " horaInicioSesion =?, "
                + " horaLlegadaDelegado =?, "
                + " horaFinSesion =?,"
                + " horaDevolucionMaterial=?, "
                + " salonesListos=?, "
                + " novedadesSitio=? ,"
                + " estadoEncuesta=? "
                + " where idEncuesta = ?";
        resultado = conex.ejecutar(sql, encuesta.getEstaListo(),
                encuesta.getHoraLlegada(),
                encuesta.getCantidadAsistio(),
                encuesta.getCantidadNoAsistio(),
                encuesta.getCantidadAnulados(),
                encuesta.getMotivosAnulacion(),
                encuesta.getNovedades(),
                encuesta.getUsuario(),
                encuesta.getHoraInicioSesion(),
                encuesta.getHoraLlegadaDelegado(),
                encuesta.getHoraFinSesion(),
                encuesta.getHoraDevolucionMaterial(),
                encuesta.getSalonesListos(),
                encuesta.getNovedadesSitio(),
                encuesta.getEstadoEncuesta(),
                encuesta.getIdEncuesta());

        return resultado;
    }

    public Encuesta GetEvento(int idEvento, int idDivipol) {

        final Encuesta encuesta = new Encuesta();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("select * from encuesta where codigoEvento = ");
            sql.append(idEvento);
            sql.append(" and idDivipol=");
            sql.append(idDivipol).append(" limit 1;");
            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {

                    try {
                        while (res.next()) {
                            encuesta.setIdEncuesta(res.getInt("idEncuesta"));
                            encuesta.setEstaListo(res.getInt("estaListo"));
                            encuesta.setHoraLlegada(res.getString("horaLlegada"));
                            encuesta.setCantidadAsistio(res.getInt("cantidadAsistio"));
                            encuesta.setCantidadNoAsistio(res.getInt("cantidadNoAsistio"));
                            encuesta.setCantidadAnulados(res.getInt("cantidadAnulados"));
                            encuesta.setMotivosAnulacion(res.getString("motivosAnulacion"));
                            encuesta.setNovedades(res.getString("novedades"));
                            encuesta.setActualizado(res.getInt("actualizado"));
                            encuesta.setHoraInicioSesion(res.getString("horaInicioSesion"));
                            encuesta.setHoraFinSesion(res.getString("horaFinSesion"));
                            encuesta.setHoraDevolucionMaterial(res.getString("horaDevolucionMaterial"));
                            encuesta.setSalonesListos(res.getInt("salonesListos"));
                            encuesta.setNovedadesSitio(res.getString("novedadesSitio"));
                            encuesta.setEstaElDelegado(res.getInt("estaElDelegado"));
                            encuesta.setHoraLlegadaDelegado(res.getString("horaLlegadaDelegado"));
                            encuesta.setEstaElPagador(res.getInt("estaElPagador"));
                            encuesta.setEstadoEncuesta(res.getInt("estadoEncuesta"));

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EncuestaDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql.toString());

        } catch (Exception e) {
            Logger.getLogger(Encuesta.class.getName()).log(Level.SEVERE, null, e);
        }
        return encuesta;
    }

    public Encuesta GetEncuestaEventoSitio(int idEvento, int idDivipol) {

        final Encuesta encuesta = new Encuesta();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("select * from encuesta where codigoEvento = ");
            sql.append(idEvento);
            sql.append(" and idDivipol =");
            sql.append(idDivipol);
            sql.append(" limit 1 ");

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {

                    try {
                        while (res.next()) {
                            encuesta.setIdEncuesta(res.getInt("idEncuesta"));
                            encuesta.setEstaListo(res.getInt("estaListo"));
                            encuesta.setHoraLlegada(res.getString("horaLlegada"));
                            encuesta.setCantidadAsistio(res.getInt("cantidadAsistio"));
                            encuesta.setCantidadNoAsistio(res.getInt("cantidadNoAsistio"));
                            encuesta.setCantidadAnulados(res.getInt("cantidadAnulados"));
                            encuesta.setMotivosAnulacion(res.getString("motivosAnulacion"));
                            encuesta.setNovedades(res.getString("novedades"));
                            encuesta.setActualizado(res.getInt("actualizado"));
                            encuesta.setHoraInicioSesion(res.getString("horaInicioSesion"));
                            encuesta.setHoraFinSesion(res.getString("horaFinSesion"));
                            encuesta.setHoraDevolucionMaterial(res.getString("horaDevolucionMaterial"));
                            encuesta.setSalonesListos(res.getInt("salonesListos"));
                            encuesta.setNovedadesSitio(res.getString("novedadesSitio"));
                            encuesta.setEstaElDelegado(res.getInt("estaElDelegado"));
                            encuesta.setHoraLlegadaDelegado(res.getString("horaLlegadaDelegado"));
                            encuesta.setEstaElPagador(res.getInt("estaElPagador"));

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EncuestaDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql.toString());

        } catch (Exception e) {
            Logger.getLogger(Encuesta.class.getName()).log(Level.SEVERE, null, e);
        }
        return encuesta;
    }

}
