/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import co.com.grupoasd.nomina.modelo.Departamento;
import co.com.grupoasd.nomina.modelo.seguimiento.ResultSetConverter;
import java.sql.SQLException;
import org.json.JSONArray;
import org.json.JSONException;

/**
 *
 * @author Pedro Rodriguez
 */
public class DepartamentoDao {

    private Operaciones conex = new Operaciones();

    public DepartamentoDao() {
    }

    public List<Departamento> listar() {
        final List<Departamento> Departamentos = new ArrayList<>();
        String sql="select * from departamento order by nombre";
        conex.consultar(sql, new Operaciones.OperacionConsulta() {
            @Override
            public void respuestaConsulta(ResultSet res) {
                try {
                    while (res.next()) {
                        Departamento departamento = new Departamento();
                        departamento.setCodigo(res.getString("codigo"));
                        departamento.setNombre(res.getString("nombre"));
                        Departamentos.add(departamento);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DepartamentoDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        return Departamentos;
    }

    public Departamento GetById(String id) {
        final Departamento departamento = new Departamento();
        String sql="select * from departamento where codigo = '" + id + "'";
        conex.consultar(sql, new Operaciones.OperacionConsulta() {
            @Override
            public void respuestaConsulta(ResultSet res) {
                try {
                    while (res.next()) {
                        departamento.setCodigo(res.getString("codigo"));
                        departamento.setNombre(res.getString("nombre"));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DepartamentoDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        return departamento;
    }

    public String ListarDepartamentoUsuario(int idprueba, String nrodoc) {

        final Object[] result = new Object[1];
        JSONArray json = null;
        try {
             StringBuilder sql = new StringBuilder();
             sql.append(" select distinct d.codigo,concat(d.codigo,' - ',d.nombre) nombre from usuario_departamento u,departamento d ");
             sql.append(" where u.idPrueba=").append(idprueba);
             sql.append(" and   u.usuario=").append(nrodoc);
             sql.append(" and   u.codigoDepartamento=d.codigo  ");

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
