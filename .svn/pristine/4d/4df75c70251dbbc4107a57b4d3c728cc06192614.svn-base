/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.DbConnection;
import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.CierreAsistencia;
import co.com.grupoasd.nomina.modelo.Prueba;
import co.com.grupoasd.nomina.modelo.seguimiento.ResultSetConverter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;

/**
 *
 * @author Iván Vargas
 */
public class AsignacionSuplenteDao {

    private Operaciones conex = new Operaciones();

    public AsignacionSuplenteDao() {

    }

    public String obtenerSuplentes(int dcpe) {
        final Object[] result = new Object[1];
        String s1 = Integer.toString(dcpe);
        JSONArray json = null;
        try {
            StringBuilder sql=new StringBuilder();
            sql.append(" select dcpe1.id,concat(e.nrodoc,' - ',ifnull(e.apellido1,''),' ',ifnull(e.apellido2,''),' ',ifnull(e.nombre1,''),' ',ifnull(e.nombre2,'')) nombrePersonal ");
            sql.append(" from detalle_cargo_x_puesto_x_evento dcpe,cargos c, cargos a,detalle_cargo_x_puesto_x_evento dcpe1,empleado e,asistencia asis ");
            sql.append(" where dcpe.id = ").append(s1);
            sql.append(" and   dcpe.codigoCargo = c.codigoCargo ");
            sql.append(" and   c.nivel_cargo    = a.nivel_cargo ");
            sql.append(" and   a.esSuplente     = 1 ");
            sql.append(" and   dcpe.iddivipol   = dcpe1.iddivipol ");
            sql.append(" and   dcpe.codigoevento= dcpe1.codigoevento ");
            sql.append(" and   dcpe.id          <> dcpe1.id ");
            sql.append(" and   dcpe1.codigoCargo= a.codigoCargo ");
            sql.append(" and   dcpe1.nrodoc     = e.nrodoc ");
            sql.append(" and   dcpe1.iddivipol   = asis.iddivipol ");
            sql.append(" and   e.idempleado      = asis.idempleado ");
            sql.append(" and   dcpe1.codigoevento= asis.codigoevento ");
            
            conex.consultar(sql.toString(),new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        result[0] = ResultSetConverter.convert(res);
                    } catch (SQLException ex) {
                        Logger.getLogger(AsignacionSuplenteDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(AsignacionSuplenteDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            json = (JSONArray) result[0];
        } catch (Exception e) {
            Logger.getLogger(AsignacionSuplenteDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return json.toString();
    }

    public String obtenerfalton(int dcpe) {
        final Object[] result = new Object[1];
        String s1 = Integer.toString(dcpe);
        JSONArray json = null;
        try {
            StringBuilder sql=new StringBuilder();
            sql.append(" select dcpe.id,concat(e.nrodoc,' - ',ifnull(e.apellido1,''),' ',ifnull(e.apellido2,''),' ',ifnull(e.nombre1,''),' ',ifnull(e.nombre2,'')) nombrePersonal ");
            sql.append(" from detalle_cargo_x_puesto_x_evento dcpe ");
            sql.append(" left  join empleado e  on   dcpe.nrodoc = e.nrodoc ");
            sql.append(" where dcpe.id = ").append(s1);
            conex.consultar(sql.toString(),new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        result[0] = ResultSetConverter.convert(res);
                    } catch (SQLException ex) {
                        Logger.getLogger(AsignacionSuplenteDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(AsignacionSuplenteDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            json = (JSONArray) result[0];
        } catch (Exception e) {
            Logger.getLogger(AsignacionSuplenteDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return json.toString();
    }

    public Boolean Actualizar(int falto, int asistio) {
          Boolean resultado     = false;
          Long  faltonrodoc = this.GetNrodoc(falto);

            StringBuilder sql=new StringBuilder();
            sql.append(" update detalle_cargo_x_puesto_x_evento f,detalle_cargo_x_puesto_x_evento a ");
            sql.append(" set f.nrodoc = a.nrodoc ");
            sql.append(" where f.id = ? ");
            sql.append(" and   a.id = ? ;");

            resultado = conex.ejecutar(sql.toString(),falto,asistio);

            sql.delete(0, sql.length());

            sql.append(" update detalle_cargo_x_puesto_x_evento ");
            sql.append(" set nrodoc= ? ");
            sql.append(" where id  = ? ;");

            resultado = conex.ejecutar(sql.toString(),faltonrodoc,asistio);
            return resultado;

      }
    
    public long GetNrodoc(int dcpe) {
        final Object id[] = new Object[1];
        id[0] = 0;
        try {
            conex.consultar(new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            id[0] = res.getLong("nrodoc");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, "SELECT nrodoc FROM detalle_cargo_x_puesto_x_evento where id = ?", dcpe);

        } catch (Exception e) {
            Logger.getLogger(AsignacionSuplenteDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return (long) id[0];
    }

    public String GetRamdon() {
        final Object[] result = new Object[1];
        JSONArray json = null;
        try {
            StringBuilder sql=new StringBuilder();
            sql.append(" select round(rand() * 1000000,0) as numero ");
            conex.consultar(sql.toString(),new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        result[0] = ResultSetConverter.convert(res);
                    } catch (SQLException ex) {
                        Logger.getLogger(AsignacionSuplenteDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(AsignacionSuplenteDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            json = (JSONArray) result[0];
        } catch (Exception e) {
            Logger.getLogger(AsignacionSuplenteDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return json.toString();
    }
    
    public int insertarCierreAsistencia(CierreAsistencia cierre) {
        
        int resultado = 0;
            StringBuilder sql = new StringBuilder();
            StringBuilder sgte_sesion = new StringBuilder();
            sgte_sesion.append(" call sp_igualar_sgte_sesion (");
            sgte_sesion.append("").append(cierre.getIdprueba()).append(",");
            sgte_sesion.append("").append(cierre.getIdDivipol()).append(",");
            sgte_sesion.append("").append(cierre.getCodigoEvento()).append(");");
            
            sql.append(" insert into cierre_asistencia_sesion (id,idprueba,idDivipol,codigoEvento) Values ( 0,");
            sql.append("").append(cierre.getIdprueba()).append(",");
            sql.append("").append(cierre.getIdDivipol()).append(",");
            sql.append("").append(cierre.getCodigoEvento()).append(");");

            final Object result[] = new Object[1];
            conex.ejecutar(new Operaciones.OperacionEjecutar() {
                @Override
                public void llaveGenerada(ResultSet generatedKeys) {
                    try {
                        if (generatedKeys.next()) {
                            result[0] = (int) generatedKeys.getLong(1);
                        } else {
                            result[0] = 0;
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(PruebaDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql.toString());
            resultado = (int) (result[0]);

                this.Ejecutar(sgte_sesion.toString());

            return ((int) result[0]);
        }
    
   public String obtenerFaltantes(int idPrueba,int idDivipol,int codigoEvento) {
        final Object[] result = new Object[1];
        JSONArray json = null;
        try {
            StringBuilder sql=new StringBuilder();
            sql.append(" SELECT count(dcpe.id) as pendientes \n" +
                        "FROM detalle_cargo_x_puesto_x_evento dcpe\n" +
                        "LEFT JOIN empleado emp ON dcpe.nrodoc = emp.nrodoc\n" +
                        "LEFT JOIN asistencia asis ON dcpe.codigoevento = asis.codigoevento \n" +
                        "AND dcpe.iddivipol = asis.iddivipol AND emp.idEmpleado = asis.idempleado\n" +
                        "WHERE dcpe.codigoevento = ");
            sql.append(codigoEvento);
            sql.append(" AND dcpe.iddivipol =  ");
            sql.append(idDivipol);
            sql.append(" AND asis.biometrico is null ");

            conex.consultar(sql.toString(),new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        result[0] = ResultSetConverter.convert(res);
                    } catch (SQLException ex) {
                        Logger.getLogger(AsignacionSuplenteDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(AsignacionSuplenteDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            json = (JSONArray) result[0];
        } catch (Exception e) {
            Logger.getLogger(AsignacionSuplenteDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return json.toString();
    }
   
    public Boolean Ejecutar(String ejecutar) {
        
        Boolean resultado     = false;
        resultado = conex.ejecutar(ejecutar);
        return resultado;
      }

}
