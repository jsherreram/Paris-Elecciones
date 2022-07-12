/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.Municipio;
import co.com.grupoasd.nomina.modelo.Departamento;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pedro Rodriguez
 */
public class MunicipioDao {

    private Operaciones conex = new Operaciones();

    public MunicipioDao() {

    }

    public List<Municipio> listar(final String idDepartamento) {
        final List<Municipio> Municipios = new ArrayList<>();

        try {
            String sql = "select * from municipio where codigoDepartamento = ? order by nombre";
            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            Departamento depto = new DepartamentoDao().GetById(idDepartamento);
                            Municipio municipio = new Municipio();
                            municipio.setCodigoMunicipio(res.getString("codigoMunicipio"));
                            municipio.setNombre(res.getString("nombre"));
                            municipio.setDepartamento(depto);
                            Municipios.add(municipio);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(MunicipioDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql, idDepartamento);

        } catch (Exception e) {
            Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return Municipios;
    }

    public Municipio GetById(String idDepartamento, String idMunicipio) {

        final Municipio municipio = new Municipio();

        try {
            String sql = "select * from municipio where codigoDepartamento = ? and codigoMunicipio = ?";
            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            municipio.setCodigoMunicipio(res.getString("codigoMunicipio"));
                            municipio.setNombre(res.getString("nombre"));
                            municipio.setDepartamento(new DepartamentoDao().GetById(res.getString("codigoDepartamento")));
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(MunicipioDao.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }, sql, idDepartamento, idMunicipio);

        } catch (Exception e) {
            Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return municipio;
    }

    public Municipio GetByCodigo(String idMunicipio) {

        final Municipio municipio = new Municipio();

        try {
            String sql = "select * from municipio where codigoMunicipio = ?";
            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            municipio.setCodigoMunicipio(res.getString("codigoMunicipio"));
                            municipio.setNombre(res.getString("nombre"));
                            municipio.setDepartamento(new DepartamentoDao().GetById(res.getString("codigoDepartamento")));
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(MunicipioDao.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }, sql, idMunicipio);

        } catch (Exception e) {
            Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return municipio;
    }

}
