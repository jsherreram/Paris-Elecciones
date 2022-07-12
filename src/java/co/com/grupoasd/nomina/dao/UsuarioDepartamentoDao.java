/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.UsuarioDepartamento;
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
public class UsuarioDepartamentoDao {
    
    private    Operaciones conex = new Operaciones();

    public UsuarioDepartamentoDao() {

    }

    public List<UsuarioDepartamento> listar(final String usuario,int idPrueba) {
        final List<UsuarioDepartamento> deptos = new ArrayList<>();
     
        try {
            String s1 = Integer.toString(idPrueba);
            String sql="SELECT codigoDepartamento FROM usuario_departamento WHERE usuario='" + usuario + "' and idPrueba="+s1;
            conex.consultar(sql, new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            UsuarioDepartamento dpto = new UsuarioDepartamento();
                            dpto.setCodigo(res.getString("codigoDepartamento"));
                            dpto.setUsuario(usuario);
                            deptos.add(dpto);
                        }       } catch (SQLException ex) {
                        Logger.getLogger(UsuarioDepartamentoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        } catch (Exception e) {
            Logger.getLogger(UsuarioDepartamentoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return deptos;
    }

    public void asignarDepartamento(List<UsuarioDepartamento> usuarioDepartamentos) {
        for (UsuarioDepartamento usudep : usuarioDepartamentos) {
            StringBuilder sb = new StringBuilder();
            sb.append("insert into usuario_departamento (usuario,codigoDepartamento,idPrueba) values (");
            sb.append("'").append(usudep.getUsuario()).append("',");
            sb.append("'").append(usudep.getCodigo()).append("',");
            sb.append("'").append(usudep.getIdPrueba()).append("');");
            conex.ejecutar(sb.toString());
        }
    }

    public static void main(String[] args) {
        UsuarioDepartamento usudep = new UsuarioDepartamento();
        List<UsuarioDepartamento> usuariosdepto = new ArrayList();
        usudep.setUsuario("lisseth.rosado@utnuevofosyga.com");
        usudep.setCodigo("41");
        usudep.setIdPrueba(26);
        usuariosdepto.add(usudep);
        new UsuarioDepartamentoDao().asignarDepartamento(usuariosdepto);
    }
    
    
    public boolean eliminarRegistros(long usuario,int idPrueba) {
        boolean resultado = false;
        StringBuilder sb = new StringBuilder();
        sb.append(" DELETE FROM usuario_departamento ");
        sb.append(" WHERE USUARIO = ? and idPrueba = ? ");

        resultado = conex.ejecutar(sb.toString(), usuario,idPrueba);

        return resultado;
    }
    
    public boolean insertar(String dpto,long usuario,int idPrueba) {

        StringBuilder sb = new StringBuilder();
        sb.append(" INSERT INTO usuario_departamento (");
        sb.append(" usuario,codigoDepartamento,idPrueba, fecha_actualiza)");
        sb.append(" VALUES (?,?,?,current_timestamp)");
        
        return conex.ejecutar(sb.toString(), usuario,dpto,idPrueba);
    }
}
