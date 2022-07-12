/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.Municipio;
import co.com.grupoasd.nomina.modelo.SoporteAsistencia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pedro Rodriguez
 */
public class SoporteAsistenciaDao {

    private Operaciones conex = new Operaciones();

    public SoporteAsistenciaDao() {

    }

    public List<SoporteAsistencia> listar(final int idEvento, final String idDepartamento, final String idMunicipio, final String idCargo, final String idZona, final String idPuesto) {

        final List<SoporteAsistencia> soportes = new ArrayList<SoporteAsistencia>();
        try {
            String sql;
            sql = " select usuario, fecha, id "
                    + " from soporte_asistencia "
                    + " where codigoDepartamento = ? "
                    + " and codigoMunicipio = ? "
                    + " and Zona = ? "
                    + " and Puesto = ? "
                    + " and codigoCargo = ? "
                    + " and codigoEvento = ? ";

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            SoporteAsistencia soporte = new SoporteAsistencia();
                            Municipio municipio = new MunicipioDao().GetById(idDepartamento, idMunicipio);

                            soporte.setMunicipio(municipio);
                            soporte.setZona(idZona);
                            soporte.setPuesto(idPuesto);
                            soporte.setCargo(new CargoDao().GetById(idCargo));
                            soporte.setEvento(new EventoDao().GetById(idEvento));
                            soporte.setUsuario(res.getString("usuario"));
                            soporte.setFecha(res.getDate("fecha"));
                            soporte.setId(res.getInt("id"));

                            soportes.add(soporte);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(SoporteAsistenciaDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql, idDepartamento, idMunicipio, idZona, idPuesto, idCargo, idEvento);

        } catch (Exception e) {
            Logger.getLogger(SoporteAsistenciaDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return soportes;
    }

    public SoporteAsistencia insertar(final SoporteAsistencia soporte) {

        try {
            String sql = " INSERT INTO soporte_asistencia ("
                    + " codigoDepartamento,codigoMunicipio,zona,"
                    + " puesto,codigoCargo,codigoEvento, usuario,"
                    + " fecha)"
                    + " VALUES (?,?,?,?,?,?,?, current_timestamp)";
            conex.ejecutar(new Operaciones.OperacionEjecutar() {

                @Override
                public void llaveGenerada(ResultSet generatedKeys) {
                    try {
                        if (generatedKeys.next()) {
                            soporte.setId((int) generatedKeys.getLong(1));
                        } else {
                            soporte.setId(0);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(SoporteAsistenciaDao.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }, sql, soporte.getMunicipio().getDepartamento().getCodigo(),
                    soporte.getMunicipio().getCodigoMunicipio(),
                    soporte.getZona(),
                    soporte.getPuesto(),
                    soporte.getCargo().getCodigoCargo(),
                    soporte.getEvento().getCodigoEvento(),
                    soporte.getUsuario());

        } catch (Exception e) {
            Logger.getLogger(SoporteAsistenciaDao.class.getName()).log(Level.SEVERE, null, e);
        }

        return soporte;
    }

}
