/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.DepartamentoDane;
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
public class DepartamentoDaneDao {

    private Operaciones conex = new Operaciones();

    public DepartamentoDaneDao() {

    }

    public List<DepartamentoDane> listar() {
        final List<DepartamentoDane> Departamentos = new ArrayList<>();
        String sql = "select * from departamento_dane";
        conex.consultar(sql, new Operaciones.OperacionConsulta() {

            @Override
            public void respuestaConsulta(ResultSet res) {
                try {
                    while (res.next()) {
                        DepartamentoDane departamento = new DepartamentoDane();
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

    public DepartamentoDane GetById(String id) {
        final DepartamentoDane departamento = new DepartamentoDane();
        String sql = "select * from departamento_dane where codigo = '" + id + "'";
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

    /**
     * Metodo encargado de consultar los departamentos dane segun el nodo
     *
     * @param codigoDepartamento
     * @return
     */
    public List<DepartamentoDane> listarDepartamentosNodo(String codigoDepartamento) {
        final List<DepartamentoDane> lista = new ArrayList<>();
        try {
            StringBuilder consulta = new StringBuilder("select ");
            consulta.append("dd.codigo as codigoDepartamento, ");
            consulta.append("dd.nombre as nombreDepartamento ");
            consulta.append("from nodo_departamento_dane ndd ");
            consulta.append("inner join departamento_dane dd on ndd.idDepartamentoDane = dd.codigo ");
            consulta.append("where ndd.idDepartamento = ?");
            conex.consultar(new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet resultado) {
                    try {
                        while (resultado.next()) {
                            DepartamentoDane dpto = new DepartamentoDane();
                            dpto.setCodigo(resultado.getString("codigoDepartamento"));
                            dpto.setNombre(resultado.getString("nombreDepartamento"));
                            lista.add(dpto);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(DepartamentoDaneDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, consulta.toString(), codigoDepartamento);
        } catch (Exception e) {
            Logger.getLogger(DepartamentoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return lista;
    }

}
