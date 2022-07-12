/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.Usuario;
import co.com.grupoasd.nomina.modelo.UsuarioGrupo;
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
 * @author Grupo ASD
 */
public class UsuarioGrupoDao {

    private Operaciones conex = new Operaciones();

    public List<UsuarioGrupo> listarGruposUsuario(String usuario) {

        final List<UsuarioGrupo> gruposUsuario = new ArrayList<UsuarioGrupo>();
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("select * from usuario_grupo where usuario = '");
            sb.append(usuario);
            sb.append("';");

            conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            int col = 0;
                            UsuarioGrupo grupo = new UsuarioGrupo();
                            Usuario usuarion = new Usuario();
                            usuarion.setCorreo(res.getString(++col));
                            grupo.setUsuario(usuarion);
                            grupo.setGrupo(res.getString(++col));
                            //TODO: Crear y traer el email desde la bd
                            //usuarion.setCorreo(res.getString(++col));
                            gruposUsuario.add(grupo);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(UsuarioGrupoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (Exception e) {
            Logger.getLogger(UsuarioSitioDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return gruposUsuario;
    }

    public Boolean asignarGrupos(List<UsuarioGrupo> grupos) {
        boolean coma = false;
        StringBuilder sb = new StringBuilder();
        sb.append("insert into usuario_grupo (usuario, grupo) values ");
        for (UsuarioGrupo grupo : grupos) {
            if (coma) {
                sb.append(",");
            } else {
                coma = true;
            }
            sb.append("('");
            sb.append(grupo.getUsuario().getCorreo());
            sb.append("','");
            sb.append(grupo.getGrupo());
            sb.append("')");
        }

        return conex.ejecutar(sb.toString());

    }

    public int existeUsuarioGrupo(long usuario, String grupo, int idPrueba) {
        final Object[] respuesta = new Object[1];
        respuesta[0] = 0;
        try {
            StringBuilder query = new StringBuilder();
            query.append(" select ");
            query.append(" count(*) as total ");
            query.append(" FROM usuario_grupo  ");
            query.append(" where USUARIO=? and GRUPO=? and idprueba=? ");

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            if (res.getInt("total") > 0) {
                                respuesta[0] = 1;
                            }
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(MedioPagoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, query.toString(), usuario, grupo, idPrueba);

        } catch (Exception e) {
            Logger.getLogger(MedioPagoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return (int) respuesta[0];
    }

    /**
     * Permite registrar un registro para usuarioGrupo
     *
     * @param Medio de Pago
     */
    public boolean insertar(long usuario, String grupo, int idPrueba, int activo) {
        StringBuilder sb = new StringBuilder();
        sb.append(" INSERT INTO usuario_grupo (");
        sb.append(" USUARIO,GRUPO, idprueba, activo, fecha_actualiza)");
        sb.append(" VALUES (?,?,?,?, current_timestamp)");

        return conex.ejecutar(sb.toString(), usuario, grupo, idPrueba, activo);

    }
    
    public boolean actualizarEstado(Long usuario,  int idPrueba, int activo) {
        StringBuilder sb = new StringBuilder();
        sb.append(" UPDATE usuario_grupo SET ");
        sb.append(" activo =?, fecha_actualiza=current_timestamp ");
        sb.append(" WHERE USUARIO = ?  and idprueba=? ");
        sb.append(" and GRUPO in ('administrador','coordinador','coordinador_icfes',");
        sb.append("'administrador_icfes','contabilidad','call') ");

       return conex.ejecutar(sb.toString(),activo, usuario,  idPrueba );


    }


    public boolean actualizar(long usuario, String grupo, int idPrueba, int activo) {
        StringBuilder sb = new StringBuilder();
        sb.append(" UPDATE usuario_grupo SET ");
        sb.append(" activo =?, fecha_actualiza=current_timestamp ");
        sb.append(" WHERE USUARIO = ? and  GRUPO=? and idprueba=? ");

        return conex.ejecutar(sb.toString(), activo,usuario, grupo, idPrueba );

    }

}
