/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrador
 */
public class Operaciones {

    DbConnection dbConnection = new DbConnection();
    DbConnection_1 dbConnection_1 = new DbConnection_1();

    /**
     * Permite ejecutar una consulta y se recuperan los datos a traves del
     * objeto de Operación
     *
     * @param query
     * @param operacion
     */
    public void consultar(String query, OperacionConsulta operacion) {

        ResultSet resultado = null;
        Connection conexion = null;
        Statement sentencia = null;
        try {
            dbConnection.conectar();
            conexion = dbConnection.getConexion();
            //conexion.setReadOnly(true);
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(query);
            operacion.respuestaConsulta(resultado);

        } catch (SQLException e) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, e);

        } finally {
            try {
                if (resultado != null) {
                    resultado.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                if (sentencia != null) {
                    sentencia.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                if (conexion != null) {
                    //conexion.setReadOnly(false);
                    conexion.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


    /**
     * Permite ejecutar una consulta y se recuperan los datos a traves del
     * objeto de Operación
     *
     * @param query
     * @param operacion
     */
    public void consultar_1(String query, OperacionConsulta operacion) {

        ResultSet resultado = null;
        Connection conexion = null;
        Statement sentencia = null;
        try {
            dbConnection_1.conectar();
            conexion = dbConnection_1.getConexion();
            //conexion.setReadOnly(true);
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(query);
            operacion.respuestaConsulta(resultado);

        } catch (SQLException e) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, e);

        } finally {
            try {
                if (resultado != null) {
                    resultado.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DbConnection_1.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                if (sentencia != null) {
                    sentencia.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                if (conexion != null) {
                    //conexion.setReadOnly(false);
                    conexion.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    
    /**
     * Permite ejecutar una consulta y se recuperan los datos a traves del
     * objeto de Operación, adicional se reciben los parametros que van en la
     * consulta
     *
     * @param query
     * @param operacion
     */
    public void consultar(OperacionConsulta operacion, String sql, Object... params) {

        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        int pos = 1;

        try {
            dbConnection.conectar();
            conexion = dbConnection.getConexion();
            //conexion.setReadOnly(true);
            statement = conexion.prepareStatement(sql);
            for (Object dato : params) {
                statement.setObject(pos, dato);
                pos++;
            }

            result = statement.executeQuery();
            operacion.respuestaConsulta(result);
        } catch (SQLException e) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, e);

        } finally {

            try {
                if (result != null) {
                    result.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                if (conexion != null) {
                    //conexion.setReadOnly(false);
                    conexion.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    /**
     * Ejecuta update,insert o delete, se reciben los parametros que requiere la
     * instrucción
     *
     * @param sql
     * @param params
     * @return
     */
    public boolean ejecutar(String sql, Object... params) {

        Connection conexion = null;
        PreparedStatement statement = null;
        int pos = 1;

        try {
            dbConnection.conectar();
            conexion = dbConnection.getConexion();
            //conexion.setReadOnly(false);
            statement = conexion.prepareCall(sql);
            for (Object dato : params) {
                statement.setObject(pos, dato);
                pos++;
            }
            statement.execute();

        } catch (SQLException e) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, e);
            return false;

        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return true;
    }

    /**
     * Permite recuperar el objeto RETURN_GENERATED_KEYS Ejecuta update,insert o
     * delete con parametros
     *
     *
     */
    public boolean ejecutar(OperacionEjecutar operacion, String sql, Object... params) {

        boolean result = false;
        Connection conexion = null;
        PreparedStatement statement = null;
        int pos = 1;

        try {
            dbConnection.conectar();
            conexion = dbConnection.getConexion();
            //conexion.setReadOnly(false);
            statement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            if (params != null) {
                for (Object dato : params) {
                    statement.setObject(pos, dato);
                    pos++;
                }
            }
            statement.execute();
            ResultSet generatedKeys = statement.getGeneratedKeys();

            operacion.llaveGenerada(generatedKeys);
            generatedKeys.close();
            return true;

        } catch (SQLException e) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, e);
            return false;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    /**
     * Ejecuta una instrucción insert, update o delete
     *
     * @param sql
     * @return
     */
    public boolean ejecutar(String sql) {
        Statement sentencia = null;
        Connection conexion = null;
        try {
            dbConnection.conectar();
            conexion = dbConnection.getConexion();
            //conexion.setReadOnly(false);
            sentencia = conexion.createStatement();
            sentencia.executeUpdate(sql);
        } catch (SQLException e) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, e);
            return false;
        } finally {
            try {
                if (sentencia != null) {
                    sentencia.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                if (dbConnection != null) {
                    dbConnection.desconectar();
                }
            } catch (Exception ex) {
                Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return true;

    }

    /**
     * Ejecuta una instrucción insert, update o delete y devuelve el numero de
     * filas afectadas
     *
     * @param sql
     * @return
     */
    public int ejecutarYTomarFilasAfectadas(String sql) {
        int numeroFilasAfectadas = 0;
        Statement sentencia = null;
        Connection conexion = null;
        try {
            dbConnection.conectar();
            conexion = dbConnection.getConexion();
            //conexion.setReadOnly(false);
            sentencia = conexion.createStatement();
            numeroFilasAfectadas = sentencia.executeUpdate(sql);
        } catch (SQLException e) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, e);
            return numeroFilasAfectadas;
        } finally {
            try {
                if (sentencia != null) {
                    sentencia.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                if (dbConnection != null) {
                    dbConnection.desconectar();
                }
            } catch (Exception ex) {
                Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return numeroFilasAfectadas;

    }

    /**
     * Ejecuta update,insert o delete, se reciben los parametros que requiere la
     * instrucción
     *
     * @param sql
     * @param params
     * @return
     */
    public boolean ejecutarStatement(String sql, Object... params) {
        Connection conexion = null;
        PreparedStatement statement = null;
        int pos = 1;
        try {
            dbConnection.conectar();
            conexion = dbConnection.getConexion();
            //conexion.setReadOnly(false);
            statement = conexion.prepareStatement(sql);
            for (Object dato : params) {
                statement.setObject(pos, dato);
                pos++;
            }
            statement.execute();
        } catch (SQLException e) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, e);
            return false;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    public void ejecutarOperacion(OperacionAtomica operacion) {
        Connection conn = null;
        try {
            dbConnection.conectar();
            conn = dbConnection.getConexion();
            //conn.setReadOnly(false);
            operacion.ejecutar(conn);
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public interface OperacionAtomica {

        public void ejecutar(Connection connection);
    }

    public interface OperacionConsulta {

        public void respuestaConsulta(ResultSet resultado);
    }

    public interface OperacionEjecutar {

        public void llaveGenerada(ResultSet genKeys);
    }
}
