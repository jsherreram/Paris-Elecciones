/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.DbConnection;
import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.Banco;
import co.com.grupoasd.nomina.modelo.CierreAsistencia;
import co.com.grupoasd.nomina.modelo.MedioPago;
import co.com.grupoasd.nomina.modelo.seguimiento.ResultSetConverter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.time.Clock.system;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;

/**
 *
 * @author Iván Vargas
 */
public class CierreSesionDao {

    private Operaciones conex = new Operaciones();

    public CierreSesionDao() {

    }

    public String listarJsonCierre(int nrodoc, int Prueba) {

        final Object[] resultado = new Object[1];
        JSONArray json = null;
        try {
            StringBuilder sql = new StringBuilder();
            String s1 = Integer.toString(Prueba);
            String s2 = Integer.toString(nrodoc);
            sql.append(" select @rownum:=@rownum+1 AS rownum,d.nombreDepartamento,d.nombreMunicipio,d.codigopuesto,d.nombrePuesto,ifnull(e.nrodoc,'') as nrodoc, ");
            sql.append(" concat(ifnull(e.nombre1,''),' ',ifnull(e.nombre2,''),' ',ifnull(e.apellido1,''),' ',ifnull(e.apellido2,''),'') as RPS, ");
            sql.append(" cs.fecha_actualiza,ev.nombre,ev.fecha,ev.hora_inicial,cs.idDivipol,cs.codigoevento,0 as estado,ev.abrirsesion ");
            sql.append(" from cierre_asistencia_sesion as cs ");
            sql.append(" left join divipol as d      on cs.iddivipol=d.iddivipol ");
            sql.append(" left join evento as ev      on cs.codigoevento=ev.codigoevento ");
            sql.append(" left join nombramiento as n on cs.idprueba=n.idprueba and cs.iddivipol=n.iddivipol and n.codigocargo = 12 ");
            sql.append(" left join empleado as e     on  e.nrodoc = n.nrodoc ");
            sql.append(" left join (SELECT @rownum:=0) r on 1=1 ");
            sql.append(" where cs.iddivipol in(select d.iddivipol from divipol d,usuario_sitio us where d.idprueba=").append(s1).append(" and us.usuario=").append(s2).append(" and d.iddivipol=us.iddivipol) ");
            sql.append(" and   cs.id = (select csx.id from cierre_asistencia_sesion csx where csx.idprueba=cs.idprueba and csx.iddivipol=cs.iddivipol order by csx.fecha_actualiza desc limit 1 ) ");
            conex.consultar(sql.toString(), new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        resultado[0] = ResultSetConverter.convert(res);
                    } catch (SQLException ex) {
                        Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            json = (JSONArray) resultado[0];
        } catch (Exception e) {
            Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return json.toString();
    }

    public Boolean ReversarCierre(CierreAsistencia cierre) {
        
            StringBuilder sql = new StringBuilder();
            
            sql.append(" delete from cierre_asistencia_sesion ");
            sql.append(" where idprueba = ").append(cierre.getIdprueba()).append("");
            sql.append(" and idDivipol = ").append(cierre.getIdDivipol()).append("");
            sql.append(" and codigoEvento= ").append(cierre.getCodigoEvento()).append(";");
            return(this.Ejecutar(sql.toString()));
        }
    
    public Boolean Ejecutar(String ejecutar) {
        
        Boolean resultado     = false;
        resultado = conex.ejecutar(ejecutar);
        return resultado;
      }

    public String listarJsonActualizame(int nrodoc, int Prueba) {

    final Object[] resultado = new Object[1];
    JSONArray json = null;
    try {
        StringBuilder sql = new StringBuilder();
        String s1 = Integer.toString(Prueba);
        String s2 = Integer.toString(nrodoc);
        sql.append(" select d.nombreDepartamento,d.nombreMunicipio,d.codigopuesto,d.nombrePuesto,e.nrodoc, ");
        sql.append(" concat(ifnull(e.nombre1,''),' ',ifnull(e.nombre2,''),' ',ifnull(e.apellido1,''),' ',ifnull(e.apellido2,''),'') as RPS, ");
        sql.append(" ifnull(e.celular,e.telefono) as telefono,");
        sql.append(" ifnull((select max(a.fecha_actualiza) from actualizaciones a where a.cedula=e.nrodoc and DATE_SUB(CURDATE(),INTERVAL 30 DAY) <= a.fecha_actualiza),'SIN ACTUALIZACION') as ultimo_mes,");
        sql.append(" ifnull((select max(date(a.fecha_actualiza)) from actualizaciones a where a.cedula=e.nrodoc and DATE_SUB(CURDATE(),INTERVAL 30 DAY) <= a.fecha_actualiza),'SIN ACTUALIZACION') as fechadia ");
        sql.append(" from divipol d ");
        sql.append(" JOIN usuario_sitio us on us.usuario=").append(s2).append(" and d.iddivipol=us.iddivipol ");
        sql.append(" left join nombramiento n on n.idDivipol = d.idDivipol and n.codigocargo in (12,33) ");
        sql.append(" left join empleado e on e.nrodoc = n.nrodoc ");
        sql.append(" left join cargos c on c.codigocargo=n.codigocargo ");
        sql.append(" where d.idPrueba = ").append(s1);
        sql.append(" order by ultimo_mes,d.nombreDepartamento,d.nombreMunicipio,d.nombrePuesto,RPS ");
        conex.consultar(sql.toString(), new Operaciones.OperacionConsulta() {
            @Override
            public void respuestaConsulta(ResultSet res) {
                try {
                    resultado[0] = ResultSetConverter.convert(res);
                } catch (SQLException ex) {
                    Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JSONException ex) {
                    Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        json = (JSONArray) resultado[0];
    } catch (Exception e) {
        Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, e);
    }
    return json.toString();
}
    
    
    public List<CierreAsistencia> buscarCierre(int idDivipol, int idEvento) {
        final List<CierreAsistencia> cierres = new ArrayList<>();

        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" select * from cierre_asistencia_sesion where idDivipol=");
            sql.append(idDivipol);
            sql.append(" and codigoEvento=").append(idEvento);
            

      
            conex.consultar(sql.toString(), new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            CierreAsistencia cierre = new CierreAsistencia();
                            cierre.setIdDivipol(res.getInt("idDivipol"));
                            cierres.add(cierre);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(CierreSesionDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (Exception e) {
            Logger.getLogger(CierreSesionDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return cierres;
    }


}
