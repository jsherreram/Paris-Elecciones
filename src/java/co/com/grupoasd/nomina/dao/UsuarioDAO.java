/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.EmpleadoSesion;
import co.com.grupoasd.nomina.modelo.Usuario;
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
public class UsuarioDAO {

    private Operaciones conex = new Operaciones();

    //Lista todos los usuarios de la base de datos
    public List<Usuario> listar() {
        final List<Usuario> usuarios = new ArrayList<Usuario>();

        StringBuilder sb = new StringBuilder();
        try {
            sb.append("select * from usuario;");

            conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            Usuario usuario = new Usuario();
                            int col = 0;
                            usuario.setCorreo(res.getString(++col));
                            usuario.setPasswd(res.getString(++col));
                            usuarios.add(usuario);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (Exception e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return usuarios;
    }

    //Crea usuarios en base a una lista, pueden ser obviamente uno o muchos
    public void crear(List<Usuario> usuarios) {

        for (Usuario usuario : usuarios) {
            StringBuilder sb = new StringBuilder();
            sb.append("insert into usuario values('");
            sb.append(usuario.getCorreo());
            sb.append("','");
            sb.append(usuario.getPasswd());
            sb.append("');");

            conex.ejecutar(sb.toString());
        }
    }

    public Boolean obtenerContraseña(String usuario, String contraseña) {

        StringBuilder sb = new StringBuilder();

        Boolean respuesta = false;
        sb.append("update empleado set clave = '");
        sb.append(contraseña);
        sb.append("', cambioPassword = 1");
        sb.append(" where nrodoc = '");
        sb.append(usuario);
        sb.append("';");

        respuesta = conex.ejecutar(sb.toString());

        return respuesta;
    }

    public Boolean obtenerContraseñaxIdUsuario(String id, String contraseña) {

        StringBuilder sb = new StringBuilder();

        Boolean respuesta = false;
        sb.append("update empleado set clave = '");
        sb.append(contraseña);
        sb.append("', cambioPassword = 1");
        sb.append(" where idEmpleado = '");
        sb.append(id);
        sb.append("';");
        
        respuesta = conex.ejecutar(sb.toString());

        return respuesta;
    }

    public Boolean esContraseñaReseteada(String usuario) {

        final Object[] result = new Object[1];
        StringBuilder sb = new StringBuilder();
        Boolean esReseteada = false;
        sb.append("select cambioPassword from empleado where nrodoc = '");
        sb.append(usuario);
        sb.append("';");

        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {

            @Override
            public void respuestaConsulta(ResultSet res) {
                try {
                    res.first();

                    result[0] = res.getInt(1) == 1;

                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        esReseteada = (Boolean) result[0];
        return esReseteada;
    }

    public Boolean cambiarContraseña(String usuario, String nuevoPassword) {
       
        StringBuilder sb = new StringBuilder();
        Boolean respuesta;

        sb.append("UPDATE empleado SET clave = '");
        sb.append(nuevoPassword);
        sb.append("', cambioPassword = 0");
        sb.append(" WHERE nrodoc = '");
        sb.append(usuario);
        sb.append("';");
        
        try {
            respuesta = conex.ejecutar(sb.toString());
        } catch (Exception e) {
            Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, e);
            respuesta = false;
        }
        return respuesta;
    }
    
      public Boolean bloqueaUsuario (String id){
        
        StringBuilder sb = new StringBuilder();        
        sb.append("update empleado set bloqueado = 1 where idEmpleado = ");
        sb.append(id);      
        
        
        if(conex.ejecutar(sb.toString())){
            return true;
        }
        return false;
    }
    
    public Boolean verificaCambio(EmpleadoSesion e){
      final Object [] result= new Object[1];
        StringBuilder sb = new StringBuilder();       
        sb.append("select count(0) from empleado where email = '");
        sb.append(e.getEmail());
        sb.append("' and nrodoc = ");
        sb.append(e.getNrodoc());
       
        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {

            @Override
            public void respuestaConsulta(ResultSet res) {
                try {
                    res.first();
                    if(res.getInt(1) > 0)
                        result[0]= true;
                    else
                        result[0]= false;
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        return (Boolean) result[0];
    }

    public String obtenerCorreoUsuario(String idUsuario) {
      
        final Object [] result= new Object[1];
        StringBuilder sb = new StringBuilder();
        String correo = "";
        sb.append("select email from empleado where idEmpleado = ");
        sb.append(idUsuario);
        sb.append(";");
        try {
            conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        if (res.first()){
                            result[0] = res.getString("email");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            
            correo=(String)result[0];
           
        } catch (Exception e) {
            Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return correo;
    }
    
     public Boolean estaBloqueado(EmpleadoSesion e){
       
         final Object[] result= new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("select ifnull(bloqueado,0) as bloqueado from empleado where idEmpleado = ");
        sb.append(e.getIdEmpleado());
        
        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {

            @Override
            public void respuestaConsulta(ResultSet res) {
                try {
                    res.first();
                    if(res.getInt("bloqueado") > 0)
                        result[0]= true;
                    else
                        result[0]= false;
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
     
        return (Boolean) result[0];
    }


}
