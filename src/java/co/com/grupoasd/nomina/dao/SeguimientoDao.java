/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.Orden;
import co.com.grupoasd.nomina.modelo.PersonaAsignada;
import co.com.grupoasd.nomina.modelo.VacantesSitioCargo;
import co.com.grupoasd.nomina.modelo.PersonaDisponibleVacante;
import co.com.grupoasd.nomina.modelo.ResultadoMonitoreoxSitioxCargo;
import co.com.grupoasd.nomina.modelo.ResultadoMonitoreoxSitioxCargoDet;
import co.com.grupoasd.nomina.modelo.ResultadoMonitoreoxSitioxCargoDet1;
import co.com.grupoasd.nomina.modelo.UsuarioSitio;
import co.com.grupoasd.nomina.modelo.seguimiento.ResultSetConverter;
import com.google.gson.Gson;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Pedro Rodriguez
 */
public class SeguimientoDao {

    private Operaciones conex = new Operaciones();

    public SeguimientoDao() {

    }

    /* Consulta de estado Nacional x departamento */
    public String seguimientoNacional(int idEvento, String usuario) {

        final Object[] result = new Object[1];
        JSONArray json = null;
        try {
            String sql;
            sql = "select a.codigoDepartamento, d.nombre, \n"
                    + "(select count(*)\n"
                    + "from detalle_cargo_x_puesto_x_evento \n"
                    + "where codigoDepartamento = a.codigoDepartamento\n"
                    + "and codigoEvento = a.codigoEvento\n"
                    + "and estado = 0) as estado0,\n"
                    + "(select count(*)\n"
                    + "from detalle_cargo_x_puesto_x_evento \n"
                    + "where codigoDepartamento = a.codigoDepartamento\n"
                    + "and codigoEvento = a.codigoEvento\n"
                    + "and estado = 1) as estado1,\n"
                    + "(select count(*)\n"
                    + "from detalle_cargo_x_puesto_x_evento \n"
                    + "where codigoDepartamento = a.codigoDepartamento\n"
                    + "and codigoEvento = a.codigoEvento\n"
                    + "and estado = 2) as estado2,\n"
                    + "(select count(*)\n"
                    + "from detalle_cargo_x_puesto_x_evento \n"
                    + "where codigoDepartamento = a.codigoDepartamento\n"
                    + "and codigoEvento = a.codigoEvento\n"
                    + "and estado = 3) as estado3,\n"
                    + "(select count(*)\n"
                    + "from detalle_cargo_x_puesto_x_evento \n"
                    + "where codigoDepartamento = a.codigoDepartamento\n"
                    + "and codigoEvento = a.codigoEvento\n"
                    + "and estado = 4) as estado4,\n"
                    + "(select count(*)\n"
                    + "from detalle_cargo_x_puesto_x_evento \n"
                    + "where codigoDepartamento = a.codigoDepartamento\n"
                    + "and codigoEvento = a.codigoEvento\n"
                    + "and estado != 0\n"
                    + "and asistio = 0) as noasistio\n"
                    + "from \n"
                    + "(select distinct d.codigoDepartamento, d.codigoEvento \n"
                    + "from detalle_cargo_x_puesto_x_evento d \n"
                    + "where d.codigoEvento = ? "
                    + "and d.codigoDepartamento in(\n"
                    + " SELECT codigoDepartamento\n"
                    + " FROM usuario_departamento\n"
                    + " WHERE usuario = ? and d.idPrueba=usuario_departamento.idPrueba)  "
                    + ") as a left join departamento d\n"
                    + "on a.codigoDepartamento = d.codigo";

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        result[0] = ResultSetConverter.convert(res);
                    } catch (SQLException ex) {
                        Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql, idEvento, usuario);

            json = (JSONArray) result[0];

        } catch (Exception e) {
            Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return json.toString();
    }
    
    /* Consulta de avance convocatoria Nacional*/
    public String consultaMonitoreoDpto(String tipoConsulta, String codigoCargo, Object... params) {

        final Object[] result = new Object[1];
        JSONArray json = null;
        try {
            String sql= this.getCadenaConsulta(tipoConsulta, codigoCargo);
            
            conex.consultar(new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        result[0] = ResultSetConverter.convert(res);
                    } catch (SQLException ex) {
                        Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql, params);
            json = (JSONArray) result[0];

        } catch (Exception e) {
            Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return json.toString();
    }

    /* Consulta de avance convocatoria Nacional*/
    public String consultaMonitoreo(String tipoConsulta, String codigoCargo, Object... params) {

        final Object[] result = new Object[1];
        JSONArray json = null;
        try {
            String sql= this.getCadenaConsulta(tipoConsulta, codigoCargo);
            conex.consultar(new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        result[0] = ResultSetConverter.convert(res);
                    } catch (SQLException ex) {
                        Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql, params);
            System.out.println(sql);
            json = (JSONArray) result[0];

        } catch (Exception e) {
            Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return json.toString();
    }

    public String consultaConfirmacionSitios(String tipoConsulta, String nivel, int idPrueba) {

        final Object[] result = new Object[1];
        JSONArray json = null;
        try {

            String sql = this.getCadenaConsultaSitios(tipoConsulta,nivel,idPrueba);
            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        result[0] = ResultSetConverter.convert(res);
                    } catch (SQLException ex) {
                        Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql);

            json = (JSONArray) result[0];

        } catch (Exception e) {
            Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return json.toString();
    }

    public String consultaMonitoreoSitio(String tipoConsulta, String codigoCargo, Object... params) {
        final Object[] result = new Object[1];
        JSONArray json = null;
        try {
            String sql = this.getCadenaConsulta(tipoConsulta, codigoCargo);
            conex.consultar(new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        result[0] = ResultSetConverter.convert(res);
                    } catch (SQLException ex) {
                        Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql, params);
            json = (JSONArray) result[0];
        } catch (Exception e) {
            Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return json.toString();
    }

    private String getCadenaConsulta(String tipoConsulta, String codigoCargo){
        
        StringBuilder sql = new StringBuilder();
        String whereAndCargoDetalle = "";
        String whereAndCargoDetalleAsi = "";
        String whereAndCargo = "";
        String whereAndSitio = "";
        String campoCargo = "";
        
        if (!codigoCargo.equals("TODOS"))
        {   whereAndCargoDetalle = " and codigocargo = a.codigocargo \n";
            whereAndCargoDetalleAsi   =" and (dt.codigocargo in (select cs.codigocargo from cargos cs where cs.esSuplente=1 and cs.equivalente_suplencia=a.codigocargo) or dt.codigocargo=a.codigocargo)\n";
            /*whereAndCargoDetalleAsi =" and  dt.codigocargo = a.codigocargo \n";*/
            whereAndCargo = " and codigocargo = '" + codigoCargo + "'\n";
            whereAndSitio = " and ca.codigocargo = '" + codigoCargo + "'\n";
            campoCargo = " , codigocargo ";
        }

        switch (tipoConsulta) {
            
            case "capacitacionNodos":
                    sql.append("select codigo, nombre, total, pendiente,convocado,convocado_2, avanceconvocatoria, asistio, avanceasistencia ");
                    sql.append(" from (select codigo, nombre, convocado as total, pendiente, convocado,convocado_2,  ");
                    sql.append(" round((convocado_2 * 100/ (pendiente+convocado)),2) as avanceconvocatoria,asistio,  ");
                    sql.append(" round((asistio * 100/ (convocado)),2) as avanceasistencia  ");
                    sql.append(" from   ");
                    sql.append(" (select a.codigocargo as codigo, ca.descripcion as nombre,  ");
                    sql.append(" abs((select count(*) from detalle_cargo_x_puesto_x_evento where codigoDepartamento=").append(codigoCargo).append(" and codigoEvento=a.codigoEvento and codigocargo=a.codigocargo) -  ");
                    sql.append(" (select count(*) from detalle_cargo_x_puesto_x_evento d9,empleado e,asistencia asis   ");
                    sql.append("   where d9.codigoEvento in (select e.codigoEvento from evento e,prueba p where p.idprueba=? and p.idprueba=e.idprueba and e.tipoSesion='CAPACITACION') ");
                    sql.append("   and d9.codigocargo=a.codigocargo and e.nrodoc=d9.nrodoc and asis.codigoevento=d9.codigoEvento and e.idEmpleado=asis.idempleado and d9.iddivipol=asis.iddivipol and d9.codigoDepartamento=").append(codigoCargo).append("  )) as pendiente,  ");
                    sql.append(" (select count(*) from detalle_cargo_x_puesto_x_evento where codigoDepartamento=").append(codigoCargo).append("  and codigoEvento=a.codigoEvento and codigocargo=a.codigocargo) as convocado,  ");
                    sql.append(" (select count(*) from detalle_cargo_x_puesto_x_evento dt,empleado e,asistencia asis   ");
                    sql.append("				  where dt.codigoEvento in (select e.codigoEvento from evento e,prueba p where p.idprueba=? and p.idprueba=e.idprueba and e.tipoSesion='CAPACITACION') and dt.codigoDepartamento=").append(codigoCargo).append(" ");
                    sql.append("                                  and e.nrodoc=dt.nrodoc and asis.codigoevento=dt.codigoEvento and e.idEmpleado=asis.idempleado and dt.iddivipol=asis.iddivipol ");
                    sql.append("				  and (dt.codigocargo in (select cs.codigocargo from cargos cs   ");
                    sql.append("							  where cs.esSuplente=1 and cs.equivalente_suplencia=a.codigocargo) or dt.codigocargo=a.codigocargo) and dt.estado = 1) as convocado_2,  ");
                    sql.append(" (select count(*) from detalle_cargo_x_puesto_x_evento dt   ");
                    sql.append("				  inner join empleado em on dt.nrodoc = em.nrodoc  ");
                    sql.append("				  inner join asistencia asi on em.idEmpleado=asi.idEmpleado and dt.codigoEvento=asi.codigoevento and dt.idDivipol=asi.iddivipol  ");
                    sql.append("				  where dt.codigoDepartamento=").append(codigoCargo).append(" and dt.codigoEvento in(select e.codigoEvento from evento e,prueba p where p.idprueba=? and p.idprueba=e.idprueba and e.tipoSesion='CAPACITACION') ");
                    sql.append("				  and dt.codigocargo=a.codigocargo and asi.id > 0) as asistio  ");
                    sql.append(" from  (select distinct d.codigocargo, d.codigoEvento from detalle_cargo_x_puesto_x_evento d, cargos c   ");
                    sql.append("		where d.codigoDepartamento=").append(codigoCargo).append("  and d.codigoevento=(select codigoevento from evento where idprueba = ? limit 1)  and c.bloqueadomonitoreo=0 and c.codigoCargo=d.codigoCargo and c.esSuplente=0) as a  ");
                    sql.append("left join cargos ca on a.codigocargo = ca.codigocargo  ");
                    sql.append(" ) as b) as c  ");
                    
                   System.out.println("Consulta capacitacion:" + sql.toString());
                   
                return sql.toString();

            case "nombramientoCargosNacional":

                    return "select codigo, nombre, total, pendiente,convocado,convocado_2, avanceconvocatoria, asistio, avanceasistencia\n" +
                            "from (\n" +
                            "select codigo, nombre, pendiente+convocado as total, pendiente, convocado,convocado_2,\n" +
                            "round((convocado_2 * 100/ (pendiente+convocado)),2) as avanceconvocatoria,\n" +
                            "asistio,\n" +
                            "round((asistio * 100/ (pendiente+convocado)),2) as avanceasistencia\n" +
                            "from (\n" +
                            "select a.codigocargo as codigo, ca.descripcion as nombre,\n" +
                            "(select count(*)\n" +
                            "from detalle_cargo_x_puesto_x_evento \n" +
                            "where codigoEvento = a.codigoEvento\n" +
                            "and codigocargo = a.codigocargo\n" +
                            "and estado = 0) as pendiente,\n" +
                            "(select count(*)\n" +
                            "from detalle_cargo_x_puesto_x_evento \n" +
                            "where codigoEvento = a.codigoEvento\n" +
                            "and codigocargo=a.codigocargo \n" +
                            "and estado = 1) as convocado,\n" +
                            "(select count(*) from detalle_cargo_x_puesto_x_evento dt\n" +
                            "where dt.codigoEvento = a.codigoEvento\n" +
                            " and (dt.codigocargo in (select cs.codigocargo from cargos cs where cs.esSuplente=1 and cs.equivalente_suplencia=a.codigocargo) or dt.codigocargo=a.codigocargo)\n" +
                            " and dt.estado = 1) as convocado_2,\n" +
                            "(	select count(*)\n" +
                            "	from detalle_cargo_x_puesto_x_evento dt left join empleado em\n" +
                            "	on dt.nrodoc = em.nrodoc\n" +
                            "	left join asistencia asi\n" +
                            "	on em.idEmpleado = asi.idEmpleado \n" +
                            "	and dt.codigoEvento = asi.codigoevento  \n" +
                            "	and dt.idDivipol = asi.iddivipol\n" +
                            "	where dt.codigoEvento = a.codigoevento\n" +
                            "	and (dt.codigocargo in (select cs.codigocargo from cargos cs where cs.esSuplente=1 and cs.equivalente_suplencia=a.codigocargo) or dt.codigocargo=a.codigocargo)\n" +
                            "	and asi.id > 0) as asistio\n" +
                            "from \n" +
                            "(select distinct d.codigocargo, d.codigoEvento \n" +
                            "from detalle_cargo_x_puesto_x_evento d, cargos c \n" +
                            "where d.codigoEvento= ? and c.bloqueadomonitoreo=0 and c.codigoCargo=d.codigoCargo and c.esSuplente=0) as a left join cargos ca\n" +
                            "on a.codigocargo = ca.codigocargo ) as b) as c";

            case "nombramientoCargosNodo":

                    return "select codigo, nombre, total, pendiente, convocado,convocado_2, avanceconvocatoria, asistio, avanceasistencia\n" +
                            "from (\n" +
                            "select codigo, nombre, pendiente+convocado as total, pendiente, convocado,convocado_2,\n" +
                            "round((convocado_2 * 100/ (pendiente+convocado)),2) as avanceconvocatoria,\n" +
                            "asistio,\n" +
                            "round((asistio * 100/ (pendiente+convocado)),2) as avanceasistencia\n" +
                            "from (\n" +
                            "select a.codigocargo as codigo, ca.descripcion as nombre,\n" +
                            "(select count(*)\n" +
                            "from detalle_cargo_x_puesto_x_evento \n" +
                            "where codigoEvento = a.codigoEvento\n" +
                            "and codigocargo = a.codigocargo\n" +
                            "and codigodepartamento = a.codigodepartamento\n" +
                            "and estado = 0) as pendiente,\n" +
                            "(select count(*)\n" +
                            "from detalle_cargo_x_puesto_x_evento \n" +
                            "where codigoEvento = a.codigoEvento\n" +
                            "and codigocargo=a.codigocargo \n" +
                            "and codigodepartamento = a.codigodepartamento\n" +
                            "and estado = 1) as convocado,\n" +
                            "(select count(*) from detalle_cargo_x_puesto_x_evento dt\n" +
                            "where dt.codigoDepartamento = a.codigoDepartamento\n" +
                            "and dt.codigoEvento = a.codigoEvento\n" +
                            " and (dt.codigocargo in (select cs.codigocargo from cargos cs where cs.esSuplente=1 and cs.equivalente_suplencia=a.codigocargo) or dt.codigocargo=a.codigocargo)\n" +
                            "and dt.estado = 1) as convocado_2,\n" +
                            "(	select count(*)\n" +
                            "	from detalle_cargo_x_puesto_x_evento dt left join empleado em\n" +
                            "	on dt.nrodoc = em.nrodoc\n" +
                            "	left join asistencia asi\n" +
                            "	on em.idEmpleado = asi.idEmpleado \n" +
                            "	and dt.codigoEvento = asi.codigoevento  \n" +
                            "	and dt.idDivipol = asi.iddivipol\n" +
                            "	where dt.codigoEvento = a.codigoevento\n" +
                            "	and (dt.codigocargo in (select cs.codigocargo from cargos cs where cs.esSuplente=1 and cs.equivalente_suplencia=a.codigocargo) or dt.codigocargo=a.codigocargo)\n" +
                            "	and dt.codigodepartamento = a.codigodepartamento\n" +
                            "	and asi.id > 0) as asistio\n" +
                            "from \n" +
                            "(select distinct d.codigocargo, d.codigoEvento, d.codigodepartamento \n" +
                            "from detalle_cargo_x_puesto_x_evento d, cargos c \n" +
                            "where d.codigoEvento = ? and c.bloqueadomonitoreo=0 and c.codigoCargo=d.codigoCargo  and c.esSuplente=0 \n" +
                            "and d.codigodepartamento in(\n" +
                            "	select codigodepartamento from usuario_departamento where usuario = ?\n" +
                            " and d.idPrueba=usuario_departamento.idPrueba )) as a left join cargos ca\n" +
                            "on a.codigocargo = ca.codigocargo ) as b) as c";

            case "nombramientoNacional":
                return "select codigo, nombre, total, pendiente, convocado,convocado_2, avanceconvocatoria, asistio, avanceasistencia\n"
                        + "from (\n"
                        + "select codigo, nombre, pendiente+convocado as total, pendiente, convocado,convocado_2,\n"
                        + "round((convocado_2 * 100/ (pendiente+convocado)),2) as avanceconvocatoria,\n"
                        + "asistio,\n"
                        + "round((asistio * 100/ (pendiente+convocado)),2) as avanceasistencia\n"
                        + "from (\n"
                        + "select a.codigoDepartamento as codigo, d.nombre,\n"
                        + "(select count(*)\n"
                        + "from detalle_cargo_x_puesto_x_evento \n"
                        + "where codigoDepartamento = a.codigoDepartamento\n"
                        + "and codigoEvento = a.codigoEvento\n"
                        + whereAndCargoDetalle
                        + "and estado = 0) as pendiente,\n"
                        + "(select count(*)\n"
                        + "from detalle_cargo_x_puesto_x_evento \n"
                        + "where codigoDepartamento = a.codigoDepartamento\n"
                        + "and codigoEvento = a.codigoEvento\n"
                        + whereAndCargoDetalle
                        + "and estado = 1) as convocado,\n"
                        +    "(select count(*) from detalle_cargo_x_puesto_x_evento dt\n"
                        +    "where dt.codigoDepartamento = a.codigoDepartamento\n"
                        +    "and dt.codigoEvento = a.codigoEvento\n"
                        + whereAndCargoDetalleAsi
                        +    " and dt.estado = 1) as convocado_2,"
                        + "(	select count(*)\n"
                        + "	from detalle_cargo_x_puesto_x_evento dt left join empleado em\n"
                        + "	on dt.nrodoc = em.nrodoc\n"
                        + "	left join asistencia asi\n"
                        + "	on em.idEmpleado = asi.idEmpleado \n"
                        + "	and dt.codigoEvento = asi.codigoevento  \n"
                        + "	and dt.idDivipol = asi.iddivipol\n"
                        + "	where dt.codigodepartamento = a.codigodepartamento\n"
                        + "	and dt.codigoEvento = a.codigoevento\n"
                        + whereAndCargoDetalleAsi
                        + "	and asi.id > 0) as asistio\n"
                        + "from \n"
                        + "(select distinct codigoDepartamento, codigoevento " + campoCargo + " \n"
                        + "from detalle_cargo_x_puesto_x_evento \n"
                        + "where codigoEvento = ? \n"
                        + whereAndCargo
                        + ") as a left join departamento d\n"
                        + "on a.codigoDepartamento = d.codigo) as b) as c";
                
            case "nombramientoNacionalCapacitacion":
                return "select codigo, nombre, total, pendiente, convocado,convocado_2, avanceconvocatoria, asistio, avanceasistencia\n"
                        + "from (\n"
                        + "select codigo, nombre, pendiente+convocado as total, pendiente, convocado,convocado_2,\n"
                        + "round((convocado_2 * 100/ (pendiente+convocado)),2) as avanceconvocatoria,\n"
                        + "asistio,\n"
                        + "round((asistio * 100/ (pendiente+convocado)),2) as avanceasistencia\n"
                        + "from (\n"
                        + "select a.codigoDepartamento as codigo, d.nombre,\n"
                        + "(select count(*)\n"
                        + "from detalle_cargo_x_puesto_x_evento \n"
                        + "where codigoDepartamento = a.codigoDepartamento\n"
                        + "and codigoEvento = a.codigoEvento\n"
                        + whereAndCargoDetalle
                        + "and estado = 0) as pendiente,\n"
                        + "(select count(*)\n"
                        + "from detalle_cargo_x_puesto_x_evento \n"
                        + "where codigoDepartamento = a.codigoDepartamento\n"
                        + "and codigoEvento = a.codigoEvento\n"
                        + whereAndCargoDetalle
                        + "and estado = 1) as convocado,\n"
                        +    "(select count(*) from detalle_cargo_x_puesto_x_evento dt\n"
                        +    "where dt.codigoDepartamento = a.codigoDepartamento\n"
                        +    "and dt.codigoEvento = a.codigoEvento\n"
                        + whereAndCargoDetalleAsi
                        +    " and dt.estado = 1) as convocado_2,"
                        + "(	select count(*)\n"
                        + "	from detalle_cargo_x_puesto_x_evento dt left join empleado em\n"
                        + "	on dt.nrodoc = em.nrodoc\n"
                        + "	inner join asistencia asi\n"
                        + "	on em.idEmpleado = asi.idEmpleado \n"
                        + "	and dt.codigoEvento = asi.codigoevento  \n"
                        + "	and dt.idDivipol = asi.iddivipol\n"
                        + "	where dt.codigodepartamento = a.codigodepartamento\n"
                        + "	and dt.codigoEvento in(select e.codigoEvento from evento e where a.idprueba=e.idprueba and e.tipoSesion='CAPACITACION') \n"
                        + whereAndCargoDetalleAsi
                        + "	and asi.id > 0) as asistio\n"
                        + "from \n"
                        + "(select distinct idprueba,codigoDepartamento, codigoevento " + campoCargo + " \n"
                        + "from detalle_cargo_x_puesto_x_evento \n"
                        + "where codigoevento=(select codigoevento from evento where idprueba = ? limit 1)  \n"
                        + whereAndCargo
                        + ") as a left join departamento d\n"
                        + "on a.codigoDepartamento = d.codigo) as b) as c";
                
            case "nombramientoDepartamental":
                return "select codigo, nombre, total, pendiente, convocado,convocado_2, avanceconvocatoria, asistio, avanceasistencia\n"
                        + "from (\n"
                        + "select codigo, nombre, pendiente+convocado as total, pendiente, convocado,convocado_2,\n"
                        + "round((convocado_2 * 100/ (pendiente+convocado)),2) as avanceconvocatoria,\n"
                        + "asistio,\n"
                        + "round((asistio * 100/ (pendiente+convocado)),2) as avanceasistencia\n"
                        + "from (\n"
                        + "select m.codigoMunicipio as codigo, m.nombre,\n"
                        + "(select count(*)\n"
                        + "from detalle_cargo_x_puesto_x_evento \n"
                        + "where codigoDepartamento = a.codigoDepartamento\n"
                        + "and codigoMunicipio = a.codigoMunicipio\n"
                        + "and codigoEvento = a.codigoEvento\n"
                        + whereAndCargoDetalle
                        + "and estado = 0) as pendiente,\n"
                        + "(select count(*)\n"
                        + "from detalle_cargo_x_puesto_x_evento \n"
                        + "where codigoDepartamento = a.codigoDepartamento\n"
                        + "and codigoMunicipio = a.codigoMunicipio\n"
                        + "and codigoEvento = a.codigoEvento\n"
                        + whereAndCargoDetalle
                        + "and estado = 1) as convocado,\n"
                        +    "(select count(*) from detalle_cargo_x_puesto_x_evento dt\n"
                        +    "where dt.codigoDepartamento = a.codigoDepartamento\n"
                        +    "and dt.codigoMunicipio = a.codigoMunicipio\n"
                        +    "and dt.codigoEvento = a.codigoEvento\n"
                        +    whereAndCargoDetalleAsi  
                        +    " and dt.estado = 1) as convocado_2,"
                        + "(	select count(*)\n"
                        + "	from detalle_cargo_x_puesto_x_evento dt left join empleado em\n"
                        + "	on dt.nrodoc = em.nrodoc\n"
                        + "	left join asistencia asi\n"
                        + "	on em.idEmpleado = asi.idEmpleado \n"
                        + "	and dt.codigoEvento = asi.codigoevento  \n"
                        + "	and dt.idDivipol = asi.iddivipol\n"
                        + "	where dt.codigodepartamento = a.codigodepartamento\n"
                        + "	and dt.codigoMunicipio = a.codigoMunicipio\n"
                        + "	and dt.codigoEvento = a.codigoevento\n"
                        + whereAndCargoDetalleAsi
                        + "	and asi.id > 0) as asistio\n"
                        + "from \n"
                        + "(select distinct codigoDepartamento, codigoMunicipio,codigoEvento  " + campoCargo + " \n"
                        + "from detalle_cargo_x_puesto_x_evento \n"
                        + "where codigoDepartamento = ? \n"
                        + "and codigoEvento = ?\n"
                        + whereAndCargo
                        + ") as a inner join municipio m\n"
                        + "on a.codigoDepartamento = m.codigoDepartamento\n"
                        + "and a.codigoMunicipio = m.codigoMunicipio\n"
                        + ") as b) as c";
            case "nombramientoMunicipal":
                return "select codigo, nombre, total, pendiente, convocado,convocado_2, avanceconvocatoria, asistio, avanceasistencia\n"
                        + "from (\n"
                        + "select codigo, nombre, pendiente+convocado as total, pendiente, convocado,convocado_2,\n"
                        + "round((convocado_2 * 100/ (pendiente+convocado)),2) as avanceconvocatoria,\n"
                        + "asistio,\n"
                        + "round((asistio * 100/ (pendiente+convocado)),2) as avanceasistencia\n"
                        + "from (\n"
                        + "select a.codigoPuesto as codigo, d.nombrePuesto as nombre,\n"
                        + "(select count(*)\n"
                        + "from detalle_cargo_x_puesto_x_evento \n"
                        + "where codigoDepartamento = a.codigoDepartamento\n"
                        + "and codigoMunicipio = a.codigoMunicipio\n"
                        + "and codigoPuesto = a.codigoPuesto\n"
                        + "and codigoEvento = a.codigoEvento\n"
                        + whereAndCargoDetalle
                        + "and estado = 0) as pendiente,\n"
                        + "(select count(*)\n"
                        + "from detalle_cargo_x_puesto_x_evento \n"
                        + "where codigoDepartamento = a.codigoDepartamento\n"
                        + "and codigoMunicipio = a.codigoMunicipio\n"
                        + "and codigoPuesto = a.codigoPuesto\n"
                        + "and codigoEvento = a.codigoEvento\n"
                        + whereAndCargoDetalle
                        + "and estado = 1) as convocado,\n"
                        +    "(select count(*) from detalle_cargo_x_puesto_x_evento dt\n"
                        +    "where dt.codigoDepartamento = a.codigoDepartamento\n"
                        +    "and dt.codigoMunicipio = a.codigoMunicipio\n"
                        +    "and dt.codigoPuesto = a.codigoPuesto\n"
                        +    "and dt.codigoEvento = a.codigoEvento\n"
                        +    whereAndCargoDetalleAsi
                        +    " and dt.estado = 1) as convocado_2,"
                        + "(	select count(*)\n"
                        + "	from detalle_cargo_x_puesto_x_evento dt left join empleado em\n"
                        + "	on dt.nrodoc = em.nrodoc\n"
                        + "	left join asistencia asi\n"
                        + "	on em.idEmpleado = asi.idEmpleado \n"
                        + "	and dt.codigoEvento = asi.codigoevento  \n"
                        + "	and dt.idDivipol = asi.iddivipol\n"
                        + "	where dt.codigodepartamento = a.codigodepartamento\n"
                        + "	and dt.codigoMunicipio = a.codigoMunicipio\n"
                        + "	and dt.codigoPuesto = a.codigoPuesto \n"
                        + "	and dt.codigoEvento = a.codigoevento\n"
                        + whereAndCargoDetalleAsi
                        + "	and asi.id > 0) as asistio\n"
                        + "from\n"
                        + "(select distinct codigoDepartamento, codigoMunicipio, codigoZona, codigoPuesto, det.codigoEvento, det.idprueba  " + campoCargo + " \n"
                        + "from detalle_cargo_x_puesto_x_evento det left join evento ev \n"
                        + "on det.codigoEvento = ev.codigoEvento\n"
                        + "where codigoDepartamento = ? \n"
                        + "and codigoMunicipio = ? \n"
                        + "and det.codigoEvento = ? \n"
                        + whereAndCargo
                        + ") as a inner join divipol d\n"
                        + "on a.codigoDepartamento = d.codigoDepartamento\n"
                        + "and a.codigoMunicipio = d.codigoMunicipio\n"
                        + "and a.codigoPuesto = d.codigoPuesto\n"
                        + "and a.idPrueba = d.idprueba) as b) as c";
            case "nombramientoSitio":
                return "select ubicacion, cargo, nrodoc, apellido1, apellido2, nombre1, nombre2, celular, email, asistio, descripcion\n"
                        + "from (\n"
                        + "select ubicacion, ca.descripcion as cargo, a.nrodoc,  e.apellido1, e.apellido2,\n"
                        + "e.nombre1, e.nombre2, e.celular, e.email, \n"
                        + "case when asi.id is null then 0 else 1 end as asistio, \n"
                        + "es.descripcion\n"
                        + "from detalle_cargo_x_puesto_x_evento a left join empleado e\n"
                        + "on a.nrodoc = e.nrodoc\n"
                        + "left join asistencia asi\n"
                        + "on e.idEmpleado = asi.idEmpleado \n"
                        + "and a.codigoEvento = asi.codigoevento  \n"
                        + "and a.idDivipol = asi.iddivipol\n"
                        + "left join estado es\n"
                        + "on a.estado =  es.codigoEstado\n"
                        + "left join cargos ca\n"
                        + "on a.codigoCargo = ca.codigoCargo\n"
                        + "where a.codigoDepartamento = ? \n"
                        + "and a.codigoMunicipio = ? \n"
                        + "and a.codigoEvento = ? \n"
                        + "and codigopuesto = ? \n"
                        + whereAndSitio
                        + "order by cargo, ubicacion) as b";
            default:
                throw new IllegalArgumentException("Consulta No encontrada " + tipoConsulta);
        }
    }

    private String getCadenaConsultaSitios(String tipoConsulta,String nivel, int idPrueba){

        StringBuilder sql = new StringBuilder();
        String s1 = Integer.toString(idPrueba);
        
        switch(tipoConsulta) {
            case "listarSitiosNodos":
                    sql.append(" select codigo,nombre,(pendiente+convocado) as total,pendiente,convocado, ");
                    sql.append(" round((convocado * 100/ (pendiente+convocado)),2) as avanceconvocatoria,asistio, ");
                    sql.append(" round((asistio * 100/ (pendiente+convocado)),2) as avanceasistencia  ");
                    sql.append(" from ( select d.codigo,d.nombre, ");
                          sql.append(" (select count(*) from divipol where idprueba=").append(s1).append(" and ifnull(idEstadoSitio,0)<>1 and d.codigo=codigoDepartamento) as pendiente, ");
                          sql.append(" (select count(*) from divipol where idprueba=").append(s1).append(" and ifnull(idEstadoSitio,0)=1 and d.codigo=codigoDepartamento) as convocado, ");
                          sql.append(" (select count(*) from divipol where idprueba=").append(s1).append(" and ifnull(idEstadoSitio,0)=1 and d.codigo=codigoDepartamento) as asistio ");
                          sql.append(" from departamento d where d.codigo in (select codigoDepartamento from divipol where idprueba=").append(s1).append(")) as reg ");
                    return(sql.toString());
            case "listarSitiosAll":
                    sql.append(" select  d.nombrePuesto,d.codigoPuesto,d.direccionPuesto,d.nombreRector,d.telefono,d.nombreMunicipio,d.email,d.nExaminandos, ");
                    sql.append(" (select e.descripcion from estado_sitio e where e.idEstadoSitio=d.idEstadoSitio) as EstadoSitio, ");
                    sql.append(" case d.idEstadoSitio when 1 then 1 else 0 end ordenamiento ");
                    sql.append(" from divipol d where d.idprueba =").append(s1).append(" and d.codigoDepartamento = ").append(nivel).append(";");

                    return(sql.toString());
            default: throw new IllegalArgumentException("Consulta No encontrada " + tipoConsulta);
        }
    }
    
    public String estadoNombramientoxDepartamento(int idPrueba, String codigoDepartamento) {

        final Object[] result = new Object[1];
        JSONArray json = null;
        try {
            String sql;
            sql = "select ca.descripcion, \n"
                    + "(select count(id) as total\n"
                    + "from nombramiento n left join evento ev\n"
                    + "on n.codigoEvento = ev.codigoEvento\n"
                    + "where ev.idprueba = a.idprueba\n"
                    + "and n.codigoDepartamento = '" + codigoDepartamento + "'\n"
                    + "and n.codigoCargo = a.codigocargo\n"
                    + " ) as total, \n"
                    + "(select count(*)\n"
                    + "from empleado_x_prueba_x_estado esp left join empleado emp\n"
                    + "on esp.idEmpleado = emp.idEmpleado\n"
                    + "where idPrueba = a.idPrueba\n"
                    + "and esp.codigoCargo = a.codigocargo\n"
                    + "and emp.codigoDepartamento = a.codigoDepartamento\n"
                    + "and idestpersona = 3) as convocada,\n"
                    + "(select count(*)\n"
                    + "from empleado_x_prueba_x_estado esp left join empleado emp\n"
                    + "on esp.idEmpleado = emp.idEmpleado\n"
                    + "where idPrueba = a.idPrueba\n"
                    + "and esp.codigoCargo = a.codigocargo\n"
                    + "and emp.codigoDepartamento = a.codigoDepartamento\n"
                    + "and idestpersona = 6) as noparticipa,\n"
                    + "(select count(*)\n"
                    + "from empleado_x_prueba_x_estado esp left join empleado emp\n"
                    + "on esp.idEmpleado = emp.idEmpleado\n"
                    + "where idPrueba = a.idPrueba\n"
                    + "and esp.codigoCargo = a.codigocargo\n"
                    + "and emp.codigoDepartamento = a.codigoDepartamento\n"
                    + "and idestpersona = 7) as preinscrita,\n"
                    + "(select count(*)\n"
                    + "from empleado_x_prueba_x_estado esp left join empleado emp\n"
                    + "on esp.idEmpleado = emp.idEmpleado\n"
                    + "where idPrueba = a.idPrueba\n"
                    + "and esp.codigoCargo = a.codigocargo\n"
                    + "and emp.codigoDepartamento = a.codigoDepartamento\n"
                    + "and idestpersona = 8) as seleccionada,\n"
                    + "(select count(*)\n"
                    + "from empleado_x_prueba_x_estado esp left join empleado emp\n"
                    + "on esp.idEmpleado = emp.idEmpleado\n"
                    + "where idPrueba = a.idPrueba\n"
                    + "and esp.codigoCargo = a.codigocargo\n"
                    + "and emp.codigoDepartamento = a.codigoDepartamento\n"
                    + "and idestpersona = 1) as asignada\n"
                    + "from (select " + idPrueba + " as idPrueba, '" + codigoDepartamento + "' as codigoDepartamento, codigocargo from cargos) as a\n"
                    + "left join cargos ca on ca.codigoCargo = a.codigocargo";
            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        result[0] = ResultSetConverter.convert(res);
                    } catch (SQLException ex) {
                        Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql);

            json = (JSONArray) result[0];

        } catch (Exception e) {
            Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return json.toString();
    }

    public String seguimientoxEstado(int idPrueba) {

        final Object[] result = new Object[1];
        JSONArray json = null;
        try {
            String sql;
            sql = "select ca.descripcion, \n"
                    + "(select count(id) as total\n"
                    + "from nombramiento n left join evento ev\n"
                    + "on n.codigoEvento = ev.codigoEvento\n"
                    + "where ev.idprueba = a.idprueba and n.codigocargo = a.codigocargo ) as total, \n"
                    + "(select count(*)\n"
                    + "from empleado_x_prueba_x_estado\n"
                    + "where idPrueba = a.idPrueba\n"
                    + "and codigoCargo = a.codigocargo\n"
                    + "and idestpersona = 3) as convocada,\n"
                    + "(select count(*)\n"
                    + "from empleado_x_prueba_x_estado \n"
                    + "where idPrueba = a.idPrueba\n"
                    + "and codigoCargo = a.codigocargo\n"
                    + "and idestpersona = 6) as noparticipa,\n"
                    + "(select count(*)\n"
                    + "from empleado_x_prueba_x_estado \n"
                    + "where idPrueba = a.idPrueba\n"
                    + "and codigoCargo = a.codigocargo\n"
                    + "and idestpersona = 7) as preinscrita,\n"
                    + "(select count(*)\n"
                    + "from empleado_x_prueba_x_estado \n"
                    + "where idPrueba = a.idPrueba\n"
                    + "and codigoCargo = a.codigocargo\n"
                    + "and idestpersona = 8) as seleccionada,\n"
                    + "(select count(*)\n"
                    + "from empleado_x_prueba_x_estado \n"
                    + "where idPrueba = a.idPrueba\n"
                    + "and codigoCargo = a.codigocargo\n"
                    + "and idestpersona = 1) as asignada\n"
                    + "from\n"
                    + "(select " + idPrueba + " as idPrueba, codigocargo\n"
                    + "from cargos) as a\n"
                    + "left join cargos ca on ca.codigoCargo = a.codigocargo";

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        result[0] = ResultSetConverter.convert(res);
                    } catch (SQLException ex) {
                        Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql);

            json = (JSONArray) result[0];

        } catch (Exception e) {
            Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return json.toString();
    }

    /* Consulta de estado Departamental x Municipio */
    public String seguimientoDepartamental(int idEvento, String idDepartamento) {
        final Object[] result = new Object[1];
        JSONArray json = null;
        try {
            String sql;
            sql = "select m.codigoMunicipio, m.nombre,\n"
                    + "(select count(*)\n"
                    + "from detalle_cargo_x_puesto_x_evento \n"
                    + "where codigoDepartamento = a.codigoDepartamento\n"
                    + "and codigoMunicipio = a.codigoMunicipio\n"
                    + "and codigoEvento = a.codigoEvento\n"
                    + "and estado = 0) as estado0,\n"
                    + "(select count(*)\n"
                    + "from detalle_cargo_x_puesto_x_evento \n"
                    + "where codigoDepartamento = a.codigoDepartamento\n"
                    + "and codigoMunicipio = a.codigoMunicipio\n"
                    + "and codigoEvento = a.codigoEvento\n"
                    + "and estado = 1) as estado1,\n"
                    + "(select count(*)\n"
                    + "from detalle_cargo_x_puesto_x_evento \n"
                    + "where codigoDepartamento = a.codigoDepartamento\n"
                    + "and codigoMunicipio = a.codigoMunicipio\n"
                    + "and codigoEvento = a.codigoEvento\n"
                    + "and estado = 2) as estado2,\n"
                    + "(select count(*)\n"
                    + "from detalle_cargo_x_puesto_x_evento \n"
                    + "where codigoDepartamento = a.codigoDepartamento\n"
                    + "and codigoMunicipio = a.codigoMunicipio\n"
                    + "and codigoEvento = a.codigoEvento\n"
                    + "and estado = 3) as estado3,\n"
                    + "(select count(*)\n"
                    + "from detalle_cargo_x_puesto_x_evento \n"
                    + "where codigoDepartamento = a.codigoDepartamento\n"
                    + "and codigoMunicipio = a.codigoMunicipio\n"
                    + "and codigoEvento = a.codigoEvento\n"
                    + "and estado = 4) as estado4,\n"
                    + "(select count(*)\n"
                    + "from detalle_cargo_x_puesto_x_evento \n"
                    + "where codigoDepartamento = a.codigoDepartamento\n"
                    + "and codigoMunicipio = a.codigoMunicipio\n"
                    + "and codigoEvento = a.codigoEvento\n"
                    + "and estado > 0\n"
                    + "and asistio = 0) as noasistio\n"
                    + "from \n"
                    + "(select distinct codigoDepartamento, codigoMunicipio,codigoEvento \n"
                    + "from detalle_cargo_x_puesto_x_evento \n"
                    + "where codigoDepartamento = ? \n"
                    + "and codigoEvento = ?  \n"
                    + ") as a left join municipio m\n"
                    + "on a.codigoDepartamento = m.codigoDepartamento\n"
                    + "and a.codigoMunicipio = m.codigoMunicipio";

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        result[0] = ResultSetConverter.convert(res);
                    } catch (SQLException ex) {
                        Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql, idDepartamento, idEvento);

            json = (JSONArray) result[0];

        } catch (Exception e) {
            Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return json.toString();
    }

    /* consulta de estado Municipal x Cargo */
    public String seguimientoMunicipal(int idEvento, String idDepartamento, String idMunicipio) {
        final Object[] result = new Object[1];
        JSONArray json = null;
        try {
            String sql;

            sql = "select a.codigoCargo, c.descripcion, \n"
                    + "(select count(*)\n"
                    + "from detalle_cargo_x_puesto_x_evento \n"
                    + "where codigoDepartamento = a.codigoDepartamento\n"
                    + "and codigoMunicipio = a.codigoMunicipio\n"
                    + "and codigoEvento = a.codigoEvento\n"
                    + "and codigoCargo = a.codigoCargo\n"
                    + "and estado = 0) as estado0,\n"
                    + "(select count(*)\n"
                    + "from detalle_cargo_x_puesto_x_evento \n"
                    + "where codigoDepartamento = a.codigoDepartamento\n"
                    + "and codigoMunicipio = a.codigoMunicipio\n"
                    + "and codigoEvento = a.codigoEvento\n"
                    + "and codigoCargo = a.codigoCargo\n"
                    + "and estado = 1) as estado1,\n"
                    + "(select count(*)\n"
                    + "from detalle_cargo_x_puesto_x_evento \n"
                    + "where codigoDepartamento = a.codigoDepartamento\n"
                    + "and codigoMunicipio = a.codigoMunicipio\n"
                    + "and codigoEvento = a.codigoEvento\n"
                    + "and codigoCargo = a.codigoCargo\n"
                    + "and estado = 2) as estado2,\n"
                    + "(select count(*)\n"
                    + "from detalle_cargo_x_puesto_x_evento \n"
                    + "where codigoDepartamento = a.codigoDepartamento\n"
                    + "and codigoMunicipio = a.codigoMunicipio\n"
                    + "and codigoEvento = a.codigoEvento\n"
                    + "and codigoCargo = a.codigoCargo\n"
                    + "and estado = 3) as estado3,\n"
                    + "(select count(*)\n"
                    + "from detalle_cargo_x_puesto_x_evento \n"
                    + "where codigoDepartamento = a.codigoDepartamento\n"
                    + "and codigoMunicipio = a.codigoMunicipio\n"
                    + "and codigoEvento = a.codigoEvento\n"
                    + "and codigoCargo = a.codigoCargo\n"
                    + "and estado = 4) as estado4,\n"
                    + "(select count(*)\n"
                    + "from detalle_cargo_x_puesto_x_evento \n"
                    + "where codigoDepartamento = a.codigoDepartamento\n"
                    + "and codigoMunicipio = a.codigoMunicipio\n"
                    + "and codigoEvento = a.codigoEvento\n"
                    + "and codigoCargo = a.codigoCargo\n"
                    + "and estado > 0\n"
                    + "and asistio = 0) as noasistio\n"
                    + "from \n"
                    + "(select distinct codigoDepartamento, codigoMunicipio, codigoCargo, codigoEvento\n"
                    + "from detalle_cargo_x_puesto_x_evento \n"
                    + "where codigoDepartamento = ? \n"
                    + "and codigoMunicipio = ? \n"
                    + "and codigoEvento = ? \n"
                    + ") as a left join cargos c\n"
                    + "on a.codigoCargo = c.codigoCargo";

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        result[0] = ResultSetConverter.convert(res);
                    } catch (SQLException ex) {
                        Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql, idDepartamento, idMunicipio, idEvento);

            json = (JSONArray) result[0];

        } catch (Exception e) {
            Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return json.toString();
    }

    //CON LA OPTIMIZACI??N YA NO SE NECESITA EL PARAMETRO IDPRUEBA, ME DA PEREZA QUITARLO, SE LO DEJO A ALGUIEN M??S
    public String monitoreoSitioXCargo(int idPrueba, int idCodigoEvento, Long usuario) {
        final String COLORBOTONVERDE = "dropdown-toggle btn btn-success";
        final String COLORBOTONAMARILLO = "dropdown-toggle btn btn-warning";
        final String COLORBOTONROJO = "dropdown-toggle btn btn-danger";
        final String COLORBOTONAZUL = "dropdown-toggle btn btn-primary";
        StringBuilder sbsub = new StringBuilder();
        ArrayList<UsuarioSitio> sitios;
        JSONArray json = new JSONArray();
        Gson gson = new Gson();
        //xxyyzz
//GetSitiosByUsuario
        if(usuario > 0){
            sitios = (ArrayList<UsuarioSitio>) new UsuarioSitioDao().GetSitiosByUsuario(usuario);
            int i = 1;
            for(UsuarioSitio sitio: sitios){
                if(i > 1 && i < sitios.size() )
                    sbsub.append(",");
                sbsub.append(sitio.getIdDivipol());                
            }
            
        }
        
        final ArrayList<ResultadoMonitoreoxSitioxCargo> reporteEncabezado = new ArrayList<ResultadoMonitoreoxSitioxCargo>();

        final DecimalFormat df = new DecimalFormat("#.##");

        int idDivipol = 0;
        String cargos;
        String cargosSuplentes;

        int cantidadRecibidos = 0;
        int cantidadDespachados = 0;
        int cantidadRequeridos = 0;
        int asistieron = 0;
        int suplentesAsistieron = 0;

        double porcentajeAsistencia = 0;


        StringBuilder sb = new StringBuilder();

        sb.append("delete from resultados; ");

        conex.ejecutar(sb.toString());

        sb = new StringBuilder();

        sb.append("insert into resultados ");
        sb.append("SELECT iddivipol,  ");
        sb.append("codigopuesto,  ");
        sb.append("nombrepuesto,  ");
        sb.append("direccionpuesto, ");
        sb.append("(select nrodoc from detalle_cargo_x_puesto_x_evento d  ");
        sb.append("where d.idPrueba=a.idprueba and d.codigoCargo=12  ");
        sb.append("and d.idDivipol=a.iddivipol limit 1) as nrodocRPS, ");
        sb.append("(select concat(apellido1 , ' ' , ifnull(apellido2,'') , ' ' , nombre1 ,' ', ifnull(nombre2,'') ) ");
        sb.append("from detalle_cargo_x_puesto_x_evento d  ");
        sb.append("inner join empleado e on (d.nrodoc = e.nrodoc) ");
        sb.append("where d.idPrueba=a.idprueba and d.codigoCargo=12  ");
        sb.append("and d.idDivipol=a.iddivipol limit 1) as nombreRPS, ");
        sb.append("(select celular ");
        sb.append("from detalle_cargo_x_puesto_x_evento d  ");
        sb.append("inner join empleado e on (d.nrodoc = e.nrodoc) ");
        sb.append("where d.idPrueba=a.idprueba and d.codigoCargo=12  ");
        sb.append("and d.idDivipol=a.iddivipol limit 1) as celular, ");        
        sb.append("b.codigocargo as codigocargo, ");
        sb.append("b.descripcion as cargo, ");
        sb.append("(select count(z.id) as cantidad  ");
        sb.append("from detalle_cargo_x_puesto_x_evento z ");
        sb.append("where z.codigoevento = c.codigoevento  ");
        sb.append("and z.codigocargo = b.codigocargo  ");
        sb.append("and z.iddivipol = a.iddivipol) as cantidadRequeridos, ");
        sb.append("(select count(asis.biometrico) as cantidad  ");
        sb.append("from detalle_cargo_x_puesto_x_evento dcpe left join empleado em  ");
        sb.append("on dcpe.nrodoc = em.nrodoc  ");
        sb.append("left join asistencia asis  ");
        sb.append("on (asis.idempleado = em.idEmpleado and asis.codigoevento = dcpe.codigoevento and asis.iddivipol = dcpe.iddivipol)  ");
        sb.append("where dcpe.codigoevento = c.codigoevento ");
        sb.append("and dcpe.codigocargo = b.codigocargo ");
        sb.append("and dcpe.iddivipol = a.iddivipol ");
        sb.append("and asis.biometrico is not null) as asistieron, ");
        sb.append("(select count(id) as cantidad  ");
        sb.append("from detalle_cargo_x_puesto_x_evento  ");
        sb.append("where codigoevento = c.codigoevento  ");
        sb.append("and codigocargo = equivalente_suplencia ");
        sb.append("and iddivipol = a.iddivipol) as suplentes, ");
        sb.append("(select count(asis.biometrico) as cantidad  ");
        sb.append("from detalle_cargo_x_puesto_x_evento dcpe left join empleado em  ");
        sb.append("on dcpe.nrodoc = em.nrodoc  ");
        sb.append("left join asistencia asis  ");
        sb.append("on (asis.idempleado = em.idEmpleado and asis.codigoevento = dcpe.codigoevento and asis.iddivipol = dcpe.iddivipol)  ");
        sb.append("where dcpe.codigoevento = c.codigoevento  ");
        sb.append("and dcpe.codigocargo = equivalente_suplencia ");
        sb.append("and dcpe.iddivipol = a.iddivipol ");
        sb.append("and asis.biometrico is not null ) as suplentesAsistieron, ");
        sb.append(" 'Preasignados' , ");
        sb.append("(select count(0) as cantidad ");
        sb.append("from ordensuplencia os left join ordensuplenciadetalle osd ");
        sb.append("on os.idorden = osd.idorden ");
        sb.append("left join suplenciaxdetalle sd ");
        sb.append("on osd.idOrdenDetalle = sd.idOrdenDetalle ");
        sb.append("where codigoevento = ");
        sb.append(idCodigoEvento);
        sb.append(" and os.idDivipolSitio = a.iddivipol ");
        sb.append("and codigocargo in(select distinct z.equivalente_suplencia from cargos z ) ");
        sb.append("and idestadoAtencionOrden = (select z.idEstadoAtencionOrden from estadosatencionordenessuplencia z where z.codigoEstadoAtencion = 'PRE')) as preasignado, ");
        sb.append(" 'Despachados', ");
        sb.append("(select count(0) as cantidad ");
        sb.append("from ordensuplencia os left join ordensuplenciadetalle osd ");
        sb.append("on os.idorden = osd.idorden ");
        sb.append("left join suplenciaxdetalle sd ");
        sb.append("on osd.idOrdenDetalle = sd.idOrdenDetalle ");
        sb.append("where codigoevento = ");
        sb.append(idCodigoEvento);
        sb.append(" and os.idDivipolSitio = a.iddivipol ");
        sb.append("and codigocargo in(select distinct z.equivalente_suplencia from cargos z ) ");
        sb.append("and idestadoAtencionOrden = (select z.idEstadoAtencionOrden from estadosatencionordenessuplencia z where z.codigoEstadoAtencion = 'DES')) as despachado, ");
        sb.append(" 'Devueltos', ");
        sb.append("(select count(0) as cantidad ");
        sb.append("from ordensuplencia os left join ordensuplenciadetalle osd ");
        sb.append("on os.idorden = osd.idorden ");
        sb.append("left join suplenciaxdetalle sd ");
        sb.append("on osd.idOrdenDetalle = sd.idOrdenDetalle ");
        sb.append("where codigoevento = ");
        sb.append(idCodigoEvento);
        sb.append(" and os.idDivipolSitio = a.iddivipol ");
        sb.append("and codigocargo in(select distinct z.equivalente_suplencia from cargos z ) ");
        sb.append("and idestadoAtencionOrden = (select z.idEstadoAtencionOrden from estadosatencionordenessuplencia z where z.codigoEstadoAtencion = 'DEV')) as devuelto, ");
        sb.append(" 'Recibido', ");
        sb.append("(select count(0) as cantidad ");
        sb.append("from ordensuplencia os left join ordensuplenciadetalle osd ");
        sb.append("on os.idorden = osd.idorden ");
        sb.append("left join suplenciaxdetalle sd ");
        sb.append("on osd.idOrdenDetalle = sd.idOrdenDetalle ");
        sb.append("where codigoevento = ");
        sb.append(idCodigoEvento);
        sb.append(" and os.idDivipolSitio = a.iddivipol ");
        sb.append("and codigocargo in(select distinct z.equivalente_suplencia from cargos z ) ");
        sb.append("and idestadoAtencionOrden = (select z.idEstadoAtencionOrden from estadosatencionordenessuplencia z where z.codigoEstadoAtencion = 'REC')) as recibido, ");
        sb.append(" 'Recibido Devuelto', ");
        sb.append("(select count(0) as cantidad ");
        sb.append("from ordensuplencia os left join ordensuplenciadetalle osd ");
        sb.append("on os.idorden = osd.idorden ");
        sb.append("left join suplenciaxdetalle sd ");
        sb.append("on osd.idOrdenDetalle = sd.idOrdenDetalle ");
        sb.append("where codigoevento = ");
        sb.append(idCodigoEvento);
        sb.append(" and os.idDivipolSitio = a.iddivipol ");
        sb.append("and codigocargo in(select distinct z.equivalente_suplencia from cargos z ) ");
        sb.append("and idestadoAtencionOrden = (select z.idEstadoAtencionOrden from estadosatencionordenessuplencia z where z.codigoEstadoAtencion = 'DRE')) as devueltorecibido, ");
        sb.append(" 'Perdido', ");
        sb.append("(select count(0) as cantidad ");
        sb.append("from ordensuplencia os left join ordensuplenciadetalle osd ");
        sb.append("on os.idorden = osd.idorden ");
        sb.append("left join suplenciaxdetalle sd ");
        sb.append("on osd.idOrdenDetalle = sd.idOrdenDetalle ");
        sb.append("where codigoevento = ");
        sb.append(idCodigoEvento);
        sb.append(" and os.idDivipolSitio = a.iddivipol ");
        sb.append("and codigocargo in(select distinct z.equivalente_suplencia from cargos z ) ");
        sb.append("and idestadoAtencionOrden = (select z.idEstadoAtencionOrden from estadosatencionordenessuplencia z where z.codigoEstadoAtencion = 'MIS')) as Perdido, ");
        sb.append("ifnull((select sum(cantidad) as cantidad from ordensuplencia os left join ordensuplenciadetalle osd on os.idorden = osd.idorden where codigoevento = c.codigoevento and codigocargo = b.codigocargo  and iddivipolsitio = a.iddivipol),0) as solicitados, ");        
        sb.append(" a.nombreMunicipio ");
        sb.append("FROM divipol a  ");
        sb.append("inner join cargos b on ( b.codigocargo in (1,4,6,10,14,16)) ");
        sb.append("inner join evento c on (c.codigoevento = ");
        sb.append(idCodigoEvento);
        sb.append(")  ");
        sb.append("WHERE a.idprueba =  c.idprueba ");
        sb.append("AND a.codigomunicipio = '11001'   ");
        sb.append("AND a.idTipoSitio = 1  ");
        sb.append("and length(a.codigopuesto) = 8  ");
        if(usuario > 0){
            sb.append(" and a.iddivipol in (");
            sb.append(sbsub.toString());
            sb.append(") ");
        }
        sb.append("ORDER BY a.codigopuesto, b.codigocargo ; ");

        conex.ejecutar(sb.toString());

        sb = new StringBuilder();

        sb.append("select distinct iddivipol,codigoPuesto,nombrePuesto,direccionpuesto,nrodocRPS,nombreRPS,celular, nombremunicipio from resultados");

        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {

            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    resultado.beforeFirst();
                    while (resultado.next()) {
                        final ResultadoMonitoreoxSitioxCargo enc = new ResultadoMonitoreoxSitioxCargo();
                        final ArrayList<ResultadoMonitoreoxSitioxCargoDet> cargosDetalle = new ArrayList<ResultadoMonitoreoxSitioxCargoDet>();
                        enc.setIddivipol(resultado.getLong(1));
                        enc.setCodigoPuesto(resultado.getString(2));
                        enc.setNombrePuesto(resultado.getString(3));
                        enc.setDireccionpuesto(resultado.getString(4));
                        enc.setNrodocRPS(resultado.getLong(5));
                        enc.setNombreRPS(resultado.getString(6));
                        enc.setCelular(resultado.getString(7));
                        enc.setNombreMunicipio(resultado.getString(8));
                        StringBuilder sb1 = new StringBuilder();
                        sb1.append("select codigocargo, cargo, cantidadrequeridos,asistieron,suplentes,suplentesasistieron,preasignados,despachados,devueltos,recibidos,devueltosrecibidos,perdidos,titulopreasignados,titulodespachados,titulodevueltos,titulorecibidos,titulodevueltosrecibidos,tituloperdidos,solicitados from resultados ");
                        sb1.append("where iddivipol = ");
                        sb1.append(enc.getIddivipol());
                        conex.consultar(sb1.toString(), new Operaciones.OperacionConsulta() {
                            @Override
                            public void respuestaConsulta(ResultSet resultado) {
                                try {
                                    resultado.beforeFirst();
                                    while (resultado.next()) {
                                        ResultadoMonitoreoxSitioxCargoDet detalle = new ResultadoMonitoreoxSitioxCargoDet();
                                        ArrayList<ResultadoMonitoreoxSitioxCargoDet1> detalle2 = new ArrayList<ResultadoMonitoreoxSitioxCargoDet1>();
                                        //detalle1, info de cargo
                                        detalle.setCodigoCargo(resultado.getLong(1));
                                        detalle.setCargo(resultado.getString(2));

                                        //detalle2 info de valores
                                        ResultadoMonitoreoxSitioxCargoDet1 detallefin = new ResultadoMonitoreoxSitioxCargoDet1();
                                        detallefin.setCantidadRequeridos(resultado.getInt(3));
                                        detallefin.setREQUERIDOS(resultado.getDouble(3));
                                        detallefin.setAsistieron(resultado.getInt(4));
                                        detallefin.setASISTIERON(resultado.getDouble(4));
                                        detallefin.setSuplentes(resultado.getInt(5));
                                        detallefin.setSUPLENTES(resultado.getDouble(5));
                                        detallefin.setSuplentesasistieron(resultado.getInt(6));
                                        detallefin.setSUPLENTESASISTIERON(resultado.getDouble(6));
                                        detallefin.setPreasignados(resultado.getDouble(7));
                                        detallefin.setDespachados(resultado.getDouble(8));
                                        detallefin.setDevueltos(resultado.getDouble(9));
                                        detallefin.setRecibidos(resultado.getDouble(10));
                                        detallefin.setDevueltoRecibido(resultado.getDouble(11));
                                        detallefin.setPerdido(resultado.getDouble(12));
                                        detallefin.setTitulopreasignados(resultado.getString(13));
                                        detallefin.setTitulodespachados(resultado.getString(14));
                                        detallefin.setTitulodevueltos(resultado.getString(16));
                                        detallefin.setTitulorecibidos(resultado.getString(15));
                                        detallefin.setTitulodevueltosrecibidos(resultado.getString(17));
                                        detallefin.setTituloperdidos(resultado.getString(18));
                                        detallefin.setSolicitados(resultado.getDouble(19));
                                        detalle.setDetalleFinal(detallefin);
                                        //Calculos locos que se hac??an antes
                                        if (detallefin.getREQUERIDOS() > 0) {
                                            //Double valor = Double.parseDouble(asistieron.toString()) + Double.parseDouble(suplentesAsistieron.toString()) + Double.parseDouble(cantidadRecibidos.toString()) + Double.parseDouble(cantidadDespachados.toString());                                            
                                            detallefin.setPorcentajeAsistencia(((double) (detallefin.getASISTIERON() + detallefin.getSUPLENTESASISTIERON() + detallefin.getRecibidos() + detallefin.getDespachados()) / (double) (detallefin.getCantidadRequeridos())) * 100);
                                            //porcentajeAsistencia = (int) porcentajeAsistencia;
                                            detallefin.setPorcentajeAsistencia((int) detallefin.getPorcentajeAsistencia());
                                        } else {
                                            detallefin.setPorcentajeAsistencia(100D);
                                        }

                                        if (detallefin.getPorcentajeAsistencia() == 100) {
                                            if (detallefin.getDespachados() > 0) {
                                                detallefin.setCOLOR(COLORBOTONAMARILLO); 
                                            } else {
                                                detallefin.setCOLOR(COLORBOTONVERDE); 
                                            }
                                        }

                                        if (detallefin.getPorcentajeAsistencia() > 100) {
                                            detallefin.setCOLOR(COLORBOTONAZUL); 
                                        }

                                        if (detallefin.getPorcentajeAsistencia() < 100) {
                                            detallefin.setCOLOR(COLORBOTONROJO); 
                                        }
                                        detalle.setDetalleFinal(detallefin);
                                        cargosDetalle.add(detalle);
                                    }
                                } catch (SQLException ex) {
                                    Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            }
                        });
                        enc.setCargos(cargosDetalle);
                        reporteEncabezado.add(enc);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        /*CODIGO BASURA ANTIGUO
         SE INTENTO OPTIMIZAR (Y SE LOGR??!) BAJANDO EL TIEMPO DE CONSULTA DE 4SEGS A 1 O 2 SEGS
         LO QUE SE TRATA ES ELIMINAR EL N??MERO DE CICLOS A 1/4 M??S O MENOS, ESO HACE LA OPTIMIZACI??N
         SE ENCONTR?? EN LA VISTA UNOS NG-SHOW QUE SE CAMBI?? POR EL COLOR DE LOS BOTONES ENVIANDO EL 
         TIPO DE BOTON EN CAMBIO DEL COLOR. FALTA OPTIMIZAR UN POCO M??S EL QUERY DE CONSULTA, TRATAR
         DE ELIMINAR LOS SUBQUERYS POR OTRA COSA, ENTRE OTRAS PENDEJADAS.
         */
        /*
        
         List<Map<String , String>> listCargos  = new ArrayList<>();

         Map<String,String> itemCargo = new HashMap<String, String>();
         itemCargo.put("descripcion", "DELEGADO");
         itemCargo.put("codigoTitular", "1");
         itemCargo.put("codigoSuplente", "21");
         listCargos.add(itemCargo);

         itemCargo = new HashMap<String, String>();
         itemCargo.put("descripcion", "COORDINADOR DE SALON");
         itemCargo.put("codigoTitular", "4,5");
         itemCargo.put("codigoSuplente", "23");
         listCargos.add(itemCargo);

         itemCargo = new HashMap<String, String>();
         itemCargo.put("descripcion", "JEFE DE SALON");
         itemCargo.put("codigoTitular", "6,7");
         itemCargo.put("codigoSuplente", "20");
         listCargos.add(itemCargo);
        
         itemCargo = new HashMap<String, String>();
         itemCargo.put("descripcion", "DACTILOS");
         itemCargo.put("codigoTitular", "10,11");
         itemCargo.put("codigoSuplente", "22");
         listCargos.add(itemCargo);

         itemCargo = new HashMap<String, String>();
         itemCargo.put("descripcion", "LECTOR");
         itemCargo.put("codigoTitular", "13,14");
         itemCargo.put("codigoSuplente", "24");
         listCargos.add(itemCargo);        
        
         itemCargo = new HashMap<String, String>();
         itemCargo.put("descripcion", "INTERPRETE");
         itemCargo.put("codigoTitular", "15,16");
         itemCargo.put("codigoSuplente", "25");
         listCargos.add(itemCargo);        

         try {
         JSONArray jsonSitios = this.monitoreoGetSitios(idPrueba);
            
         for (int i=0; i<jsonSitios.length(); i++) {
         JSONObject itemSitio = jsonSitios.getJSONObject(i);
                
         JSONObject sitio = new JSONObject();
                
         sitio.put("codigoPuesto", itemSitio.getString("codigopuesto"));
         sitio.put("nombrePuesto", itemSitio.getString("nombrepuesto"));
         sitio.put("direccionpuesto", itemSitio.getString("direccionpuesto"));
         int iddivipol=itemSitio.getInt("iddivipol");
         PersonaAsignada rps=new PersonaAsignadaDao().buscarPersonaAsignadaPorSitioCargo(idPrueba,"12",iddivipol);
         idDivipol = itemSitio.getInt("iddivipol");
         if(rps.getEmpleado()!=null){
         sitio.put("nombreRPS", rps.getEmpleado().getNombre1()+" "+rps.getEmpleado().getApellido1());
         sitio.put("celular", rps.getEmpleado().getCelular());
         sitio.put("telefono", rps.getEmpleado().getTelefono());
         }else{
         sitio.put("nombreRPS", "");
         }
         JSONArray jsonListCargos = new JSONArray();
                
         for (Map<String, String> car : listCargos) {
         JSONObject cargo = new JSONObject();
         cargo.put("codigo", car.get("codigoTitular"));
         cargo.put("nombre", car.get("descripcion"));

         cargos = car.get("codigoTitular");
         cargosSuplentes = car.get("codigoSuplente");

         JSONObject detalleCargo = new JSONObject();
                    
         cantidadRequeridos = this.monitoreoCantidadRequeridaXCargo(idCodigoEvento, cargos, idDivipol);
         asistieron = this.monitoreoCantidadAsistenciaXCargo(idCodigoEvento, cargos, idDivipol);
         suplentesAsistieron = this.monitoreoCantidadAsistenciaXCargo(idCodigoEvento, cargosSuplentes, idDivipol);
                    
         detalleCargo.put("REQUERIDOS", cantidadRequeridos);
         detalleCargo.put("ASISTIERON", asistieron);
         detalleCargo.put("SUPLENTES", this.monitoreoCantidadRequeridaXCargo(idCodigoEvento, cargosSuplentes, idDivipol));
         detalleCargo.put("SUPLENTESASISTIERON",suplentesAsistieron );
                    
         detalleCargo.put("SUPLENTESSOLICITADOS", this.monitoreoCantidadSuplenciaSolicitadaXCargo(idCodigoEvento, cargosSuplentes, idDivipol));

                    
         JSONArray itemsSuplencia = this.monitoreoSuplenciaXEstados(idCodigoEvento, cargosSuplentes, idDivipol);
                    
         cantidadRecibidos = 0;
         cantidadDespachados = 0;
                    
         for (int j=0; j<itemsSuplencia.length(); j++) {
         JSONObject itemSuple = itemsSuplencia.getJSONObject(j);
                       
         if(itemSuple.getString("codigoEstadoAtencion").equals("REC") )
         {
         cantidadRecibidos = itemSuple.getInt("cantidad");
         }
                       
         if(itemSuple.getString("codigoEstadoAtencion").equals("DES") )
         {
         cantidadDespachados = itemSuple.getInt("cantidad");
         }                       
         }                   
                    
         if (cantidadRequeridos > 0)
         {
         //Double valor = Double.parseDouble(asistieron.toString()) + Double.parseDouble(suplentesAsistieron.toString()) + Double.parseDouble(cantidadRecibidos.toString()) + Double.parseDouble(cantidadDespachados.toString());
         porcentajeAsistencia = ((double)(asistieron + suplentesAsistieron + cantidadRecibidos + cantidadDespachados) / (double)(cantidadRequeridos)) * 100 ;
         porcentajeAsistencia = (int)porcentajeAsistencia;
         }else
         {
         porcentajeAsistencia = 100;
         }
                            
         if (porcentajeAsistencia == 100) 
                        
         {
         if (cantidadDespachados > 0)
         {
         color = "AMARILLO";
         }else{
         color = "VERDE";
         }
         }
                    
         if (porcentajeAsistencia > 100)
         {
         color = "AZUL";
         }
                    
         if (porcentajeAsistencia < 100)
         {
         color = "ROJO";
         }
                    
                    
         detalleCargo.put("ASISTENCIA", porcentajeAsistencia);
                    
         detalleCargo.put("itemsEstadoSuplencia",itemsSuplencia);
         detalleCargo.put("COLOR", color);
         cargo.put("detalles", detalleCargo);
         jsonListCargos.put(cargo);
         }
         sitio.put("cargos",jsonListCargos);
         json.put(sitio);
         }
            
         } catch (JSONException ex) {
         Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
         }
        
         return json.toString();*/
        return gson.toJson(reporteEncabezado);
    }

    public JSONArray monitoreoGetSitios(int idPrueba) {

        final JSONArray json = new JSONArray();

        String sql = " SELECT iddivipol, codigopuesto, nombrepuesto, direccionpuesto "
                + " FROM divipol d "
                + " WHERE idprueba = ?  "
                + " AND codigomunicipio = '11001'  "
                + " AND d.idTipoSitio = 1 and length(codigopuesto) = 8 "
                + " ORDER BY codigopuesto ";
        conex.consultar(new Operaciones.OperacionConsulta() {

            @Override
            public void respuestaConsulta(ResultSet res) {
                try {
                    while (res.next()) {
                        JSONObject sitio = new JSONObject();
                        sitio.put("iddivipol", res.getInt("iddivipol"));
                        sitio.put("codigopuesto", res.getString("codigopuesto"));
                        sitio.put("nombrepuesto", res.getString("nombrepuesto"));
                        sitio.put("direccionpuesto", res.getString("direccionpuesto"));
                        json.put(sitio);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JSONException ex) {
                    Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }, sql, idPrueba);

        return json;
    }

    public int monitoreoCantidadRequeridaXCargo(int codigoEvento, String Cargos, int idDivipol) {
        final Object[] result = new Object[1];
        result[0] = 0;
        StringBuilder sql = new StringBuilder();
        sql.append("select count(id) as cantidad ");
        sql.append(" from detalle_cargo_x_puesto_x_evento ");
        sql.append(" where codigoevento = ? ");
        sql.append(" and codigocargo in(");
        sql.append(Cargos);//
        sql.append(") ");
        sql.append(" and iddivipol = ? ");
        conex.consultar(new Operaciones.OperacionConsulta() {

            @Override
            public void respuestaConsulta(ResultSet res) {
                try {
                    while (res.next()) {
                        result[0] = res.getInt("cantidad");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }, sql.toString(), codigoEvento, idDivipol);
        return (int) result[0];
    }

    public int monitoreoCantidadAsistenciaXCargo(int codigoEvento, String Cargos, int idDivipol) {
        final Object[] result = new Object[1];
        result[0] = 0;
        StringBuilder sql = new StringBuilder();
        sql.append("select count(asis.biometrico) as cantidad ");
        sql.append("from detalle_cargo_x_puesto_x_evento dcpe left join empleado em ");
        sql.append("on dcpe.nrodoc = em.nrodoc ");
        sql.append("left join asistencia asis ");
        sql.append("on (asis.idempleado = em.idEmpleado and asis.codigoevento = dcpe.codigoevento and asis.iddivipol = dcpe.iddivipol) ");
        sql.append("where dcpe.codigoevento = ? ");
        sql.append("and dcpe.codigocargo in(");
        sql.append(Cargos);//
        sql.append(") ");
        sql.append("and dcpe.iddivipol = ? ");
        sql.append("and asis.biometrico is not null ");
        conex.consultar(new Operaciones.OperacionConsulta() {

            @Override
            public void respuestaConsulta(ResultSet res) {
                try {
                    while (res.next()) {
                        result[0] = res.getInt("cantidad");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }, sql.toString(), codigoEvento, idDivipol);
        return (int) result[0];
    }

    public int monitoreoCantidadSuplenciaSolicitadaXCargo(int codigoEvento, String Cargos, int idDivipol) {
        final Object[] result = new Object[1];
        result[0] = 0;
        String sql = " select sum(cantidad) as cantidad"
                + " from ordensuplencia os left join ordensuplenciadetalle osd "
                + " on os.idorden = osd.idorden "
                + " where codigoevento = ? "
                + " and codigocargo in(" + Cargos + ") "
                + " and iddivipolsitio = ? ";
        conex.consultar(new Operaciones.OperacionConsulta() {

            @Override
            public void respuestaConsulta(ResultSet res) {
                try {
                    while (res.next()) {
                        result[0] = res.getInt("cantidad");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }, sql, codigoEvento, idDivipol);
        return (int) result[0];
    }

    public JSONArray monitoreoSuplenciaXEstados(int codigoEvento, String Cargos, int idDivipol) {
        final JSONArray json = new JSONArray();
        String sql = " select eaos.idestadoAtencionOrden, eaos.codigoEstadoAtencion, eaos.descripcion, \n"
                + " (select count(*) as cantidad "
                + " from ordensuplencia os left join ordensuplenciadetalle osd\n"
                + " on os.idorden = osd.idorden\n"
                + " left join suplenciaxdetalle sd\n"
                + " on osd.idOrdenDetalle = sd.idOrdenDetalle\n"
                + " where codigoevento = ? "
                + " and codigocargo in(" + Cargos + ") "
                + " and iddivipolsitio = ? "
                + " and idestadoAtencionOrden = eaos.idestadoAtencionOrden) as cantidad "
                + " from estadosatencionordenessuplencia eaos ";
        conex.consultar(new Operaciones.OperacionConsulta() {
            @Override
            public void respuestaConsulta(ResultSet res) {
                try {
                    while (res.next()) {
                        JSONObject item = new JSONObject();
                        item.put("idestadoAtencionOrden", res.getInt("idestadoAtencionOrden"));
                        item.put("codigoEstadoAtencion", res.getString("codigoEstadoAtencion"));
                        item.put("descripcion", res.getString("descripcion"));
                        item.put("cantidad", res.getInt("cantidad"));
                        json.put(item);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JSONException ex) {
                    Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }, sql, codigoEvento, idDivipol);
        return json;
    }

    /* consulta de estado Zonal x Cargo */
    public String seguimientoZonal(int idEvento, String idDepartamento, String idMunicipio, String idCargo) {
        final Object[] result = new Object[1];
        JSONArray json = null;
        try {
            String sql;

            sql = "select a.codigoZona, nombreZona, \n"
                    + "(select count(*)\n"
                    + "from detalle_cargo_x_puesto_x_evento \n"
                    + "where codigoDepartamento = a.codigoDepartamento\n"
                    + "and codigoMunicipio = a.codigoMunicipio\n"
                    + "and codigoEvento = a.codigoEvento\n"
                    + "and codigoCargo = a.codigoCargo\n"
                    + "and codigoZona = a.codigoZona\n"
                    + "and estado = 0) as estado0,\n"
                    + "(select count(*)\n"
                    + "from detalle_cargo_x_puesto_x_evento \n"
                    + "where codigoDepartamento = a.codigoDepartamento\n"
                    + "and codigoMunicipio = a.codigoMunicipio\n"
                    + "and codigoEvento = a.codigoEvento\n"
                    + "and codigoCargo = a.codigoCargo\n"
                    + "and codigoZona = a.codigoZona\n"
                    + "and estado = 1) as estado1,\n"
                    + "(select count(*)\n"
                    + "from detalle_cargo_x_puesto_x_evento \n"
                    + "where codigoDepartamento = a.codigoDepartamento\n"
                    + "and codigoMunicipio = a.codigoMunicipio\n"
                    + "and codigoEvento = a.codigoEvento\n"
                    + "and codigoCargo = a.codigoCargo\n"
                    + "and codigoZona = a.codigoZona\n"
                    + "and estado = 2) as estado2,\n"
                    + "(select count(*)\n"
                    + "from detalle_cargo_x_puesto_x_evento \n"
                    + "where codigoDepartamento = a.codigoDepartamento\n"
                    + "and codigoMunicipio = a.codigoMunicipio\n"
                    + "and codigoEvento = a.codigoEvento\n"
                    + "and codigoCargo = a.codigoCargo\n"
                    + "and codigoZona = a.codigoZona\n"
                    + "and estado = 3) as estado3,\n"
                    + "(select count(*)\n"
                    + "from detalle_cargo_x_puesto_x_evento \n"
                    + "where codigoDepartamento = a.codigoDepartamento\n"
                    + "and codigoMunicipio = a.codigoMunicipio\n"
                    + "and codigoEvento = a.codigoEvento\n"
                    + "and codigoCargo = a.codigoCargo\n"
                    + "and codigoZona = a.codigoZona\n"
                    + "and estado = 4) as estado4,\n"
                    + "(select count(*)\n"
                    + "from detalle_cargo_x_puesto_x_evento \n"
                    + "where codigoDepartamento = a.codigoDepartamento\n"
                    + "and codigoMunicipio = a.codigoMunicipio\n"
                    + "and codigoEvento = a.codigoEvento\n"
                    + "and codigoCargo = a.codigoCargo\n"
                    + "and codigoZona = a.codigoZona\n"
                    + "and estado > 0\n"
                    + "and asistio = 0) as noasistio\n"
                    + "from \n"
                    + "( select distinct dc.codigoDepartamento, dc.codigoMunicipio, dc.codigoCargo, dc.codigoZona, dc.codigoEvento \n"
                    + " from detalle_cargo_x_puesto_x_evento dc \n"
                    + " where dc.codigoDepartamento = ? \n"
                    + " and dc.codigoMunicipio = ? \n"
                    + " and dc.codigoEvento = ?  \n"
                    + " and dc.codigoCargo = ? ) as a left join zona z\n"
                    + "on a.codigoDepartamento = z.codigoDepartamento\n"
                    + "and a.codigoMunicipio = z.codigoMunicipio\n"
                    + "and a.codigoZona = z.codigoZona";

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        result[0] = ResultSetConverter.convert(res);
                    } catch (SQLException ex) {
                        Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql, idDepartamento, idMunicipio, idEvento, idCargo);

            json = (JSONArray) result[0];

        } catch (Exception e) {
            Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return json.toString();
    }

    /* consulta de estado Puesto x Cargo */
    public String seguimientoPuesto(int idEvento, String idDepartamento, String idMunicipio, String idCargo, String idZona) {
        final Object[] result = new Object[1];
        JSONArray json = null;
        try {
            String sql;

            sql = "select a.codigoPuesto, d.nombrePuesto, d.direccionPuesto, d.cantidadMesas, \n"
                    + "(select count(*)\n"
                    + "from detalle_cargo_x_puesto_x_evento \n"
                    + "where codigoDepartamento = a.codigoDepartamento\n"
                    + "and codigoMunicipio = a.codigoMunicipio\n"
                    + "and codigoEvento = a.codigoEvento\n"
                    + "and codigoCargo = a.codigoCargo\n"
                    + "and codigoZona = a.codigoZona\n"
                    + "and codigoPuesto = a.codigoPuesto\n"
                    + "and estado = 0) as estado0,\n"
                    + "(select count(*)\n"
                    + "from detalle_cargo_x_puesto_x_evento \n"
                    + "where codigoDepartamento = a.codigoDepartamento\n"
                    + "and codigoMunicipio = a.codigoMunicipio\n"
                    + "and codigoEvento = a.codigoEvento\n"
                    + "and codigoCargo = a.codigoCargo\n"
                    + "and codigoZona = a.codigoZona\n"
                    + "and codigoPuesto = a.codigoPuesto\n"
                    + "and estado = 1) as estado1,\n"
                    + "(select count(*)\n"
                    + "from detalle_cargo_x_puesto_x_evento \n"
                    + "where codigoDepartamento = a.codigoDepartamento\n"
                    + "and codigoMunicipio = a.codigoMunicipio\n"
                    + "and codigoEvento = a.codigoEvento\n"
                    + "and codigoCargo = a.codigoCargo\n"
                    + "and codigoZona = a.codigoZona\n"
                    + "and codigoPuesto = a.codigoPuesto\n"
                    + "and estado = 2) as estado2,\n"
                    + "(select count(*)\n"
                    + "from detalle_cargo_x_puesto_x_evento \n"
                    + "where codigoDepartamento = a.codigoDepartamento\n"
                    + "and codigoMunicipio = a.codigoMunicipio\n"
                    + "and codigoEvento = a.codigoEvento\n"
                    + "and codigoCargo = a.codigoCargo\n"
                    + "and codigoZona = a.codigoZona\n"
                    + "and codigoPuesto = a.codigoPuesto\n"
                    + "and estado = 3) as estado3,\n"
                    + "(select count(*)\n"
                    + "from detalle_cargo_x_puesto_x_evento \n"
                    + "where codigoDepartamento = a.codigoDepartamento\n"
                    + "and codigoMunicipio = a.codigoMunicipio\n"
                    + "and codigoEvento = a.codigoEvento\n"
                    + "and codigoCargo = a.codigoCargo\n"
                    + "and codigoZona = a.codigoZona\n"
                    + "and codigoPuesto = a.codigoPuesto\n"
                    + "and estado = 4) as estado4,\n"
                    + "(select count(*)\n"
                    + "from detalle_cargo_x_puesto_x_evento \n"
                    + "where codigoDepartamento = a.codigoDepartamento\n"
                    + "and codigoMunicipio = a.codigoMunicipio\n"
                    + "and codigoEvento = a.codigoEvento\n"
                    + "and codigoCargo = a.codigoCargo\n"
                    + "and codigoZona = a.codigoZona\n"
                    + "and codigoPuesto = a.codigoPuesto\n"
                    + "and estado > 0\n"
                    + "and asistio = 0) as noasistio\n"
                    + "from\n"
                    + "( select distinct dc.codigoDepartamento, dc.codigoMunicipio, dc.codigoCargo, dc.codigoZona, dc.codigoPuesto, dc.codigoEvento, dc.idprueba \n"
                    + " from detalle_cargo_x_puesto_x_evento dc \n"
                    + " where dc.codigoDepartamento = ? \n"
                    + " and dc.codigoMunicipio = ? \n"
                    + " and dc.codigoEvento = ?  \n"
                    + " and dc.codigoZona = ? \n"
                    + " and dc.codigoCargo = ? order by dc.codigoPuesto ) as a left join divipol d\n"
                    + "on a.codigoDepartamento = d.codigoDepartamento\n"
                    + "and a.codigoMunicipio = d.codigoMunicipio\n"
                    + "and a.codigoZona = d.codigoZona\n"
                    + "and a.codigoPuesto = d.codigoPuesto and a.idprueba = d.idprueba ";

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        result[0] = ResultSetConverter.convert(res);
                    } catch (SQLException ex) {
                        Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql, idDepartamento, idMunicipio, idEvento, idZona, idCargo);

            json = (JSONArray) result[0];

        } catch (Exception e) {
            Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return json.toString();
    }

    /* consulta de estado Ubicacion */
    public String seguimientoUbicacion(int idEvento, String idDepartamento, String idMunicipio, String idCargo, String idZona, String idPuesto) {
        final Object[] result = new Object[1];
        JSONArray json = null;
        try {
            String sql;

            sql = " select ubicacion, a.nrodoc,  e.apellido1, e.apellido2,"
                    + " e.nombre1, e.nombre2, e.celular, e.telefono, asistio, es.descripcion"
                    + " from detalle_cargo_x_puesto_x_evento a left join empleado e\n"
                    + " on a.nrodoc = e.nrodoc\n"
                    + " left join estado es\n"
                    + " on a.estado =  es.codigoEstado"
                    + " where a.codigoDepartamento = ? \n"
                    + " and a.codigoMunicipio = ? \n"
                    + " and codigoEvento = ? "
                    + " and codigoZona = ? \n"
                    + " and codigopuesto = ? \n"
                    + " and codigoCargo = ? \n";

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        result[0] = ResultSetConverter.convert(res);;
                    } catch (SQLException ex) {
                        Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql, idDepartamento, idMunicipio, idEvento, idZona, idPuesto, idCargo);

            json = (JSONArray) result[0];

        } catch (Exception e) {
            Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return json.toString();
    }

    /* consulta datos de capacitacion */
    public String seguimientoCapacitacion(int idEvento, String idDepartamento, String idMunicipio, String idCargo, String idZona, String idPuesto) {
        final Object[] result = new Object[1];
        JSONArray json = null;
        try {
            String sql;

            sql = " select fechaCapacitacion, horaInicialCapacitacion, horaFinalCapacitacion, nombreCapacitador, salonCapacitacion "
                    + "  from cargo_x_puesto_x_evento  "
                    + "  where codigoDepartamento = ? "
                    + "  and codigoMunicipio = ? "
                    + "  and codigoEvento = ? "
                    + "  and codigoZona = ? "
                    + "  and codigopuesto = ? "
                    + "  and codigoCargo = ? ";

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        result[0] = ResultSetConverter.convert(res);
                    } catch (SQLException ex) {
                        Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql, idDepartamento, idMunicipio, idEvento, idZona, idPuesto, idCargo);

            json = (JSONArray) result[0];

        } catch (Exception e) {
            Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return json.toString();
    }

    /* consulta de estado personas asignada de un puesto */
    public String seguimientoPersona(int idEvento, String idDepartamento, String idMunicipio, String idCargo, String idZona, String idPuesto, String idUbicacion) {
        final Object[] result = new Object[1];
        JSONArray json = null;
        try {
            String sql;

            sql = " select a.nrodoc,  e.apellido1, e.apellido2, \n"
                    + " e.nombre1, e.nombre2, e.celular, e.telefono, asistio, a.estado ,es.descripcion\n"
                    + " from detalle_cargo_x_puesto_x_evento a left join empleado e\n"
                    + " on a.nrodoc = e.nrodoc\n"
                    + " left join estado es\n"
                    + " on a.estado =  es.codigoEstado\n"
                    + " where a.codigoDepartamento = ? \n"
                    + " and a.codigoMunicipio = ? \n"
                    + " and codigoEvento = ? \n"
                    + " and codigoZona = ? \n"
                    + " and codigoPuesto = ? \n"
                    + " and ubicacion = ? \n"
                    + " and codigoCargo = ? ";

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        result[0] = ResultSetConverter.convert(res);
                    } catch (SQLException ex) {
                        Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql, idDepartamento, idMunicipio, idEvento, idZona, idPuesto, idUbicacion, idCargo);

            json = (JSONArray) result[0];

        } catch (Exception e) {
            Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return json.toString();
    }

    /* asigna a una persona en un puesto */
    public Boolean asignarPersona(int idEvento, String idDepartamento, String idMunicipio, String idCargo, String idZona, String idPuesto, String idUbicacion, int nroDoc) {
        Boolean resultado = false;

        try {
            String sql;

            sql = " update detalle_cargo_x_puesto_x_evento  set nrodoc = ?, estado = 1 \n"
                    + " where codigoDepartamento = ? \n"
                    + " and codigoMunicipio = ? \n"
                    + " and codigoEvento = ? \n"
                    + " and codigoZona = ? \n"
                    + " and codigoPuesto = ? \n"
                    + " and ubicacion = ? \n"
                    + " and codigoCargo = ? ";

            resultado = conex.ejecutar(sql,
                    nroDoc, idDepartamento, idMunicipio, idEvento, idZona, idPuesto, idUbicacion, idCargo);

        } catch (Exception e) {
            Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return resultado;
    }

    public String seguimientoTerceros(String usuario) {
        final Object[] result = new Object[1];

        JSONArray json = null;
        try {

            String sql = "select de.codigo, de.nombre,\n"
                    + "(select count(*) \n"
                    + "from empleado\n"
                    + "where codigodepartamento = de.codigo\n"
                    + "and codigoestado = 0) as enproceso,\n"
                    + "(select count(*) \n"
                    + "from empleado\n"
                    + "where codigodepartamento = de.codigo\n"
                    + "and codigoestado = 2) as enauditoria,\n"
                    + "(select count(*) \n"
                    + "from empleado\n"
                    + "where codigodepartamento = de.codigo\n"
                    + "and codigoestado = 3) as inconsistente,\n"
                    + "(select count(*) \n"
                    + "from empleado\n"
                    + "where codigodepartamento = de.codigo\n"
                    + "and codigoestado = 4) as aprobado,\n"
                    + "(SELECT 0 ) as recolectorestx,"
                    + "(select sum(cantidad)\n"
                    + "from cargo_x_departamento\n"
                    + "where codigodepartamento = de.codigo) as simulacro1,\n"
                    + "case when (select codigodepartamento \n"
                    + "from usuario_departamento\n"
                    + "where codigodepartamento = de.codigo\n"
                    + "and usuario = '" + usuario + "'\n"
                    + ") is null then 'none' else '' end as display\n"
                    + "from (\n"
                    + "select codigo, nombre\n"
                    + "from departamento) as de ";

            conex.consultar(sql, new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        result[0] = ResultSetConverter.convert(res);
                    } catch (SQLException ex) {
                        Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            json = (JSONArray) result[0];

        } catch (Exception e) {
            Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return json.toString();
    }

    public ArrayList<VacantesSitioCargo> vacantesSitioCargo(VacantesSitioCargo vacantesBusqueda) {

        StringBuilder sb = new StringBuilder();
        final ArrayList<VacantesSitioCargo> vacantes = new ArrayList<VacantesSitioCargo>();

        sb.append("select a.codigopuesto,");
        sb.append("b.nombrepuesto,");
        sb.append("b.direccionPuesto,");
        sb.append("count(0) as vacantes,");
        sb.append("a.codigoZona, ");
        sb.append("a.codigoMunicipio ");
        sb.append("from detalle_cargo_x_puesto_x_evento a ");
        sb.append("inner join divipol b on a.codigoPuesto = b.codigoPuesto ");
        sb.append("where a.estado = 0 ");
        sb.append("and a.codigoMunicipio = '");
        sb.append(vacantesBusqueda.getCodigoMunicipio());
        sb.append("' ");
        sb.append("and a.codigoEvento = ");
        sb.append(vacantesBusqueda.getCodigoEvento().toString());
        sb.append(" and a.codigoCargo = '");
        sb.append(vacantesBusqueda.getCodigoCargo());
        sb.append("' ");
        sb.append("group by a.codigopuesto ");

        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {

            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    resultado.beforeFirst();

                    while (resultado.next()) {
                        VacantesSitioCargo vacante = new VacantesSitioCargo();
                        vacante.setCodigoPuesto(resultado.getString(1));
                        vacante.setNombrePuesto(resultado.getString(2));
                        vacante.setDireccionPuesto(resultado.getString(3));
                        vacante.setVacantes(resultado.getInt(4));
                        vacante.setCodigoZona(resultado.getString(5));
                        vacante.setCodigoMunicipio(resultado.getString(6));
                        vacantes.add(vacante);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        return vacantes;
    }

    public ArrayList<PersonaDisponibleVacante> getPersonasDisponibles(String codigoMunicipio, String codigoCargo) {
        StringBuilder sb = new StringBuilder();
        final ArrayList<PersonaDisponibleVacante> personas = new ArrayList<PersonaDisponibleVacante>();
        sb.append("select distinct b.tipodoc as tipoDocumento,");
        sb.append("b.nrodoc as numeroIdentificacion,");
        sb.append("direccion,");
        sb.append("nombreMunicipio,");
        sb.append("c.codigoZona ");
        sb.append("from detalle_cargo_x_puesto_x_evento a ");
        sb.append("inner join empleado b on a.nrodoc = b.nrodoc ");
        sb.append("inner join divipol c on a.codigoMunicipio = c.codigoMunicipio ");
        sb.append("where a.codigoMunicipio = '");
        sb.append(codigoMunicipio);
        sb.append("' and a.codigoCargo = '");
        sb.append(codigoCargo);
        sb.append("' and a.estado = 5 ");
        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {
            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    resultado.beforeFirst();
                    while (resultado.next()) {
                        PersonaDisponibleVacante persona = new PersonaDisponibleVacante();
                        persona.setTipoDocumento(resultado.getString(1));
                        persona.setNumeroIdentificacion(resultado.getString(2));
                        persona.setDireccion(resultado.getString(3));
                        persona.setNombreMunicipio(resultado.getString(4));
                        persona.setCodigoZona(resultado.getString(5));
                        personas.add(persona);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        return personas;
    }

        /* consulta de estado personas asignada de un puesto */
    public String seguimientoGeneralSitioEvento(int idEvento, int idPrueba, String usuario) {
        final Object[] result = new Object[1];
        JSONArray json = null;
        try {
            StringBuilder sql=new StringBuilder();
            sql.append(" select a.idDivipol, b.codigoPuesto, b.nombrePuesto,b.desconectado, \n");
            sql.append("  (select count(*) from detalle_cargo_x_puesto_x_evento dt2 where dt2.idDivipol=b.idDivipol and dt2.idPrueba=").append(idPrueba).append(" and dt2.codigoEvento=").append(idEvento).append(") as total,\n");
            sql.append(" (select count(*) from  asistencia asis ");
            sql.append( " inner join empleado em on asis.idempleado=em.idEmpleado\n" );
            sql.append(" inner join detalle_cargo_x_puesto_x_evento dcp on dcp.nrodoc=em.nrodoc \n" );
            sql.append(" where asis.iddivipol=b.idDivipol and asis.codigoEvento=").append(idEvento).append(" and dcp.codigoEvento=asis.codigoEvento and dcp.idDivipol=b.iddivipol) as totalAsistencia,\n");
            sql.append(" case (select count(*) from cierre_asistencia_sesion where idDivipol=b.idDivipol and codigoEvento=").append(idEvento).append(")");
            sql.append(" when 0 then 'NO' else 'SI' end as cerrado,\n");
            sql.append(" (select dt3.nrodoc from detalle_cargo_x_puesto_x_evento dt3 where dt3.codigoCargo in (12,33,41) and dt3.idPrueba=");
            sql.append(idPrueba);
            sql.append(" and dt3.idDivipol=b.idDivipol   and dt3.nrodoc is not null limit 1) as usuario");
            sql.append(" from divipol b,usuario_sitio a\n" );
            sql.append(" where b.idPrueba =").append(idPrueba);
            sql.append(" and a.iddivipol = b.idDivipol ");
            sql.append(" and a.usuario =").append(usuario);
            sql.append(" and b.idDivipol in (select distinct dt4.idDivipol from detalle_cargo_x_puesto_x_evento dt4 where dt4.idPrueba=b.idPrueba and dt4.codigoEvento=").append(idEvento).append(" )");

            
            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        result[0] = ResultSetConverter.convert(res);
                    } catch (SQLException ex) {
                        Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql.toString());

            json = (JSONArray) result[0];

        } catch (Exception e) {
            Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return json.toString();
    }
    
}
