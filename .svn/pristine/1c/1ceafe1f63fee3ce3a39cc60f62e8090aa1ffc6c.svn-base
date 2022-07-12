package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.DbConnection;
import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.EmpleadoPruebaEstado;
import co.com.grupoasd.nomina.modelo.EstadoPersonaPrueba;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Grupo ASD
 */
public class EstadoPersonaPruebaDAO {
    

    private Operaciones conex = new Operaciones();
    

    public List<EstadoPersonaPrueba> getAll() {
        final ArrayList<EstadoPersonaPrueba> listEstadoPersonaPrueba = new ArrayList<EstadoPersonaPrueba>();
        StringBuilder sb = new StringBuilder();

        try {
            String sql = "SELECT epp.idestpersona AS id, epp.codigo, epp.descripcion, epp.fecha_actualiza FROM estado_persona_prueba epp";
            conex.consultar(sql, new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            EstadoPersonaPrueba epp = new EstadoPersonaPrueba();
                            epp.setId(res.getInt("id"));
                            epp.setCodigo(res.getString("codigo"));
                            epp.setDescripcion(res.getString("descripcion"));
                            epp.setFechaActualiza(res.getDate("fecha_actualiza"));
                            listEstadoPersonaPrueba.add(epp);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EstadoPersonaPruebaDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (Exception e) {
            Logger.getLogger(UsuarioSitioDao.class.getName()).log(Level.SEVERE, null, e);
        }

        return listEstadoPersonaPrueba;
    }
    
    public EmpleadoPruebaEstado getEstadoActual(int nrodoc, int idPrueba, String codigoCargo){
        final Object[] resultado = new Object[1];
        EmpleadoPruebaEstado empleadoPruebaEstado = new EmpleadoPruebaEstado();
        resultado[0] = empleadoPruebaEstado;
        
        try {
            String sql = " select idestpersona "+
                    " from empleado_x_prueba_x_estado epe left join empleado emp "+
                    " on epe.idempleado = emp.idEmpleado "+
                    " where emp.nrodoc = "+ nrodoc +
                    " and epe.idprueba = "+ idPrueba +
                    " and codigocargo =  '" + codigoCargo + "'" ;
            
            conex.consultar(sql, new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            EmpleadoPruebaEstado empleadoPruebaEstado = new EmpleadoPruebaEstado();
                            empleadoPruebaEstado.setIdestpersona(res.getInt("idestpersona"));
                            resultado[0] =  empleadoPruebaEstado;
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EstadoPersonaPruebaDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (Exception e) {
            Logger.getLogger(EstadoPersonaPruebaDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return (EmpleadoPruebaEstado)resultado[0] ;
    }
    
}
