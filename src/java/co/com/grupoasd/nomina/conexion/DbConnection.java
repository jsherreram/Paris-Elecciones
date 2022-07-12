package co.com.grupoasd.nomina.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

//import javax.ejb.*;
import javax.naming.*;
import javax.sql.*;

/**
 *
 * @author Pedro Rodriguez
 */
public class DbConnection {

    private Connection conexion;
    private DataSource ds = null;
    private Context ctx = null;

    protected Connection getConexion() {
        return conexion;
    }

    protected void conectar() {
        try {

            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("jdbc/paris");
            conexion = ds.getConnection();

            if (getConexion() == null) {
                System.out.println("Conexion Fallida!");
            }

        } catch (Exception e) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void desconectar(){
        try {
            getConexion().close();
        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

}
