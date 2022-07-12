/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.DepartamentoDane;
import co.com.grupoasd.nomina.modelo.MunicipioDane;
import co.com.grupoasd.nomina.modelo.seguimiento.ResultSetConverter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;

/**
 *
 * @author ASD
 */
public class MunicipioDaneDao {

    private Operaciones conex = new Operaciones();

    public MunicipioDaneDao() {

    }
    public List<MunicipioDane> listar(final String idDepartamento) {
        final List<MunicipioDane> Municipios = new ArrayList<>();
        try {
            String sql = "select * from municipio_dane where codigoDepartamento = ? ";
            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            DepartamentoDane depto = new DepartamentoDaneDao().GetById(idDepartamento);
                            MunicipioDane municipio = new MunicipioDane();
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
            Logger.getLogger(MunicipioDaneDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return Municipios;
    }
    public MunicipioDane GetMunicipioDaneById(String idMunicipio) {

       final MunicipioDane municipio = new MunicipioDane();
       try {
           String sql = "select * from municipio_dane where  codigoMunicipio = ?";
           conex.consultar(new Operaciones.OperacionConsulta() {

               @Override
               public void respuestaConsulta(ResultSet res) {
                   try {
                       while (res.next()) {
                           municipio.setCodigoMunicipio(res.getString("codigoMunicipio"));
                           municipio.setNombre(res.getString("nombre"));
                           municipio.setDepartamento(new DepartamentoDaneDao().GetById(res.getString("codigoDepartamento")));
                       }
                   } catch (SQLException ex) {
                       Logger.getLogger(MunicipioDao.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
           }, sql, idMunicipio);
       } catch (Exception e) {
           Logger.getLogger(MunicipioDaneDao.class.getName()).log(Level.SEVERE, null, e);
       }
       return municipio;
   }
    public int existeMunicipioDane(String idMunicipio) {
        final Object[] respuesta = new Object[1];
        respuesta[0]=0;
        try {
            StringBuilder query = new StringBuilder();
            query.append(" select ");
            query.append(" count(*) as total ");
            query.append(" FROM municipio_dane  ");
            query.append(" where codigoMunicipio= ");
            query.append(idMunicipio);

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                         while (res.next()) {
                          if (res.getInt("total") > 0 ){
                              respuesta[0]=1;
                            }
                      }
                    } catch (SQLException ex) {
                        Logger.getLogger(MedioPagoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, query.toString());

        } catch (Exception e) {
            Logger.getLogger(MedioPagoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return (int) respuesta[0];
    }
    public List<MunicipioDane> listarGeo(int prueba) {
        final List<MunicipioDane> Municipios = new ArrayList<>();
        try {
            String sql="select distinct m.* from detalle_cargo_x_puesto_x_evento d,municipio_dane m where d.idprueba=? and d.codigomunicipio=m.codigomunicipio";
            conex.consultar(new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            /*DepartamentoDane depto = new DepartamentoDaneDao().GetById(idDepartamento);*/
                            MunicipioDane municipio = new MunicipioDane();
                            municipio.setCodigoMunicipio(res.getString("codigoMunicipio"));
                            municipio.setNombre(res.getString("nombre"));
                            /*municipio.setDepartamento(depto);*/
                            Municipios.add(municipio);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(MunicipioDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql, prueba);
        } catch (Exception e) {
            Logger.getLogger(MunicipioDaneDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return Municipios;
    }
    
    public String municipioUsuarioPrueba(int idPrueba, String usuario) {

        final Object[] result = new Object[1];
        JSONArray json = null;
        try {
             StringBuilder sql = new StringBuilder();
             sql.append(" select distinct m.codigoMunicipio as codigo,m.nombre from municipio m  ");
             sql.append(" inner join usuario_departamento u on u.usuario=").append(usuario).append(") and u.idPrueba=").append(idPrueba).append(" and  u.codigoDepartamento=m.codigoDepartamento ");
             sql.append(" inner join divipol d on d.idPrueba = u.idPrueba and d.codigoDepartamento=m.codigoDepartamento and d.codigoMunicipio=m.codigoMunicipio ");

            conex.consultar(new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        result[0] = ResultSetConverter.convert(res);
                    } catch (SQLException ex) {
                        Logger.getLogger(DepartamentoDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(DepartamentoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql.toString());
            json = (JSONArray) result[0];
        } catch (Exception e) {
            Logger.getLogger(DepartamentoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return json.toString();
    }

}
