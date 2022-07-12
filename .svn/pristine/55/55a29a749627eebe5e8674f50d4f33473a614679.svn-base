/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.DepartamentoDane;
import co.com.grupoasd.nomina.modelo.LocalidadDane;
import co.com.grupoasd.nomina.modelo.MunicipioDane;
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
public class LocalidadDaneDao {

    
    private Operaciones conex = new Operaciones();

    public LocalidadDaneDao() {

    }

    public List<LocalidadDane> listar(final String codMunicipio) {
        final List<LocalidadDane> localidades = new ArrayList<>();

        try {
            String sql = "select * from localidad_dane where codigoMunicipio = ? ";
            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            MunicipioDane mcpio = new MunicipioDaneDao().GetMunicipioDaneById(codMunicipio);
                            LocalidadDane localidad = new LocalidadDane();
                            localidad.setIdLocalidad(res.getInt("idLocalidad"));
                            localidad.setNombre(res.getString("nombre"));
                            localidad.setMunicipio(mcpio);
                            localidades.add(localidad);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(MunicipioDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql, codMunicipio);

        } catch (Exception e) {
            Logger.getLogger(LocalidadDaneDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return localidades;
    }

     public LocalidadDane GetLocalidadDaneById(int idLocalidad) {

        final LocalidadDane localidad = new LocalidadDane();

        try {
            String sql = "select * from localidad_dane where  idLocalidad = ?";
            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            localidad.setIdLocalidad(res.getInt("idLocalidad"));
                            localidad.setNombre(res.getString("nombre"));
                            localidad.setMunicipio(new MunicipioDaneDao().GetMunicipioDaneById(res.getString("codigoMunicipio")));
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(MunicipioDao.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }, sql, idLocalidad);

        } catch (Exception e) {
            Logger.getLogger(MunicipioDaneDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return localidad;
    }

}
