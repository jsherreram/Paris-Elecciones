/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.Empleado;
import co.com.grupoasd.nomina.modelo.ListaAsistencia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pedro Rodriguez
 */
public class ListaAsistenciaDao {

    private Operaciones conex = new Operaciones();

    public ListaAsistenciaDao() {

    }

    public Boolean actualizar(ListaAsistencia lista) {
        Boolean resultado = false;

        String sql = " update listasAsistencia set archivo  = '" + lista.getNombreArchivo() + "' \n"
                + " where codigoDepartamento = ? and  codigoMunicipio = ? \n"
                + " and codigoZona = ? and codigoPuesto = ? \n"
                + " and codigoCargo = ? and codigoEvento = ?";

       resultado= conex.ejecutar(sql,
                lista.getMunicipio().getDepartamento().getCodigo(),
                lista.getMunicipio().getCodigoMunicipio(),
                lista.getZona(),
                lista.getPuesto(),
                lista.getCargo().getCodigoCargo(),
                lista.getEvento().getCodigoEvento());

       

        return resultado;
    }

    public ListaAsistencia getById(final String idDpto, final String idMpio, final String idZona, final String idPuesto, final String idCargo, final int idEvento) {

        final ListaAsistencia lista = new ListaAsistencia();

        try {

            String sql = " select archivo \n"
                    + " from listasAsistencia\n"
                    + " where codigoDepartamento = ? and  codigoMunicipio = ? \n"
                    + " and codigoZona = ? and codigoPuesto = ? \n"
                    + " and codigoCargo = ? and codigoEvento = ?";

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {

                            lista.setMunicipio(new MunicipioDao().GetById(idDpto, idMpio));
                            lista.setZona(idZona);
                            lista.setPuesto(idPuesto);
                            lista.setCargo(new CargoDao().GetById(idCargo));
                            lista.setEvento(new EventoDao().GetById(idEvento));
                            lista.setNombreArchivo(res.getString("archivo"));
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(ListaAsistenciaDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql, idDpto,
                    idMpio,
                    idZona,
                    idPuesto,
                    idCargo,
                    idEvento);

        } catch (Exception e) {
            Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return lista;
    }

}
