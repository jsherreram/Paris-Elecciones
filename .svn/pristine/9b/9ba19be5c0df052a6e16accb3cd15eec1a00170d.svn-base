package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.dto.DataFiltros;
import co.com.grupoasd.nomina.modelo.CuadroAp;
import co.com.grupoasd.nomina.modelo.seguimiento.ResultSetConverter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Pedro Rodriguez
 */
public class EdicionCuadroDao {

    private Operaciones conex = new Operaciones();

    public EdicionCuadroDao() {
    }

    public Boolean actualizar(CuadroAp cp) {
        Boolean resultado = false;

        StringBuilder sql = new StringBuilder();
        sql.append(" call sp_cuadro_capacitacion_elecciones(").append(cp.getIdprueba()).append(",'").append(cp.getIddivipol()).append("',");
        sql.append("'").append(cp.getCodigocargo()).append("',").append(cp.getCodigoevento()).append(",").append(cp.getPuestos()).append(");");
        resultado = conex.ejecutar(sql.toString());
        return resultado;
    }

    public JSONArray ConsultarFiltro(DataFiltros data) throws JSONException {
        final Object[] result = new Object[1];
        JSONArray json = new JSONArray();
        StringBuilder sdepartamento = new StringBuilder().append("");
        StringBuilder smunicipio = new StringBuilder().append("");
        StringBuilder ssitio = new StringBuilder().append("");
        StringBuilder sevento = new StringBuilder().append("");
        StringBuilder scargo = new StringBuilder().append("");
        StringBuilder szona = new StringBuilder().append("");
        String s1 = "";

        if (data.getZona() != null && data.getZona().length() > 0) {
            szona.append(" and d.codigoZona in (").append(data.getZona()).append(")");
        }
        if (data.getCargo() != null && data.getCargo().length() > 0) {
            scargo.append(" and c.codigoCargo in (").append(data.getCargo()).append(")");
        }
        if (data.getEvento() != null && data.getEvento().length() > 0) {
            sevento.append(" and e.codigoEvento in (").append(data.getEvento()).append(")");
        } else {
        }
        if (data.getDepartamento() != null && data.getDepartamento().length() > 0) {
            sdepartamento.append(" and dp.codigoDepartamento in (").append(data.getDepartamento()).append(")");
        }
        if (data.getMunicipio() != null && data.getMunicipio().length() > 0) {
            smunicipio.append(" and dp.codigoMunicipio in (").append(data.getMunicipio()).append(")");
        }
        if (data.getSitio() != null && data.getSitio().length() > 0) {
            ssitio.append(" and dp.idDivipol in (").append(data.getSitio()).append(")");
        }

        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" select distinct ").append(data.getColumna1()).append(" as codigo,").append(data.getColumna2()).append(" as nombre ");
            sql.append(" from detalle_cargo_x_puesto_x_evento d  ");
            sql.append(" inner join prueba p         on p.idprueba = d.idprueba and p.idprueba=").append(data.getIdprueba()).append(szona.toString());
            sql.append(" inner join divipol dp       on dp.idDivipol=d.idDivipol  ").append(ssitio.toString()).append(sdepartamento.toString()).append(smunicipio.toString());
            sql.append(" inner join evento e         on e.codigoEvento = d.codigoEvento  ").append(sevento.toString());
            sql.append(" inner join usuario_sitio us on us.usuario=").append(data.getNrodoc()).append(" and us.iddivipol=d.idDivipol  ");
            sql.append(" inner join cargos  c        on c.codigoCargo=d.codigoCargo   ").append(scargo.toString());
            if (data.getColumna1().equals("c.codigoCargo")) {
                sql.append("union ");
                sql.append("select c.codigocargo as codigo,c.descripcion as nombre from cargos c where 1=1").append(scargo.toString());
            }
            if (data.getColumna1().equals("e.codigoEvento")) {
                sql.append("union ");
                sql.append(" select distinct e.codigoEvento as codigo,concat(e.coddepartamento,' - ',e.nombre) as nombre  ");
                sql.append(" from evento e ");
                sql.append(" inner join divipol dp       on dp.idprueba=").append(data.getIdprueba()).append(sdepartamento.toString()).append(" and e.coddepartamento=dp.codigoDepartamento ");
                sql.append(" inner join usuario_sitio us on us.usuario=").append(data.getNrodoc()).append(" and us.iddivipol=dp.idDivipol  ");
                sql.append(" where 1=1 ").append(sevento.toString());
            }
            sql.append(" order by nombre ");
            conex.consultar(sql.toString(), new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        result[0] = ResultSetConverter.convert(res);
                    } catch (SQLException ex) {
                        Logger.getLogger(EdicionCuadroDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(EdicionCuadroDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            json = (JSONArray) result[0];
        } catch (Exception e) {
            Logger.getLogger(EdicionCuadroDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return json;
    }

    public JSONArray Consulta(DataFiltros data) throws JSONException {
        final Object[] result = new Object[1];
        JSONArray json = new JSONArray();
        StringBuilder sdepartamento = new StringBuilder().append("");
        StringBuilder sdepartamentoe = new StringBuilder().append("");
        StringBuilder smunicipio = new StringBuilder().append("");
        StringBuilder ssitio = new StringBuilder().append("");
        StringBuilder sevento = new StringBuilder().append("");
        StringBuilder scargo = new StringBuilder().append("");
        StringBuilder scargos = new StringBuilder().append("");
        StringBuilder szona = new StringBuilder().append("");

        if (data.getZona() != null && data.getZona().length() > 0) {
            szona.append(" and dp.codigoZona in (").append(data.getZona()).append(")");
        }
        if (data.getCargo() != null && data.getCargo().length() > 0) {
            scargo.append(" and c.codigoCargo in (").append(data.getCargo()).append(")");
        } else {
            scargos.append(" inner join (select distinct codigocargo from detalle_cargo_x_puesto_x_evento where idprueba= ").append(data.getIdprueba()).append(" ) cp on cp.codigocargo=c.codigoCargo ");
        }
        if (data.getEvento() != null && data.getEvento().length() > 0) {
            sevento.append(" and e.codigoEvento in (").append(data.getEvento()).append(")");
        }
        if (data.getDepartamento() != null && data.getDepartamento().length() > 0) {
            sdepartamento.append(" and dp.codigoDepartamento in (").append(data.getDepartamento()).append(")");
            sdepartamentoe.append(" and e.coddepartamento in (").append(data.getDepartamento()).append(")");
        }
        if (data.getMunicipio() != null && data.getMunicipio().length() > 0) {
            smunicipio.append(" and dp.codigoMunicipio in (").append(data.getMunicipio()).append(")");
        }
        if (data.getSitio() != null && data.getSitio().length() > 0) {
            ssitio.append(" and dp.idDivipol in (").append(data.getSitio()).append(")");
        }

        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" select c.codigocargo,c.descripcion,ifnull(d.solicitados,0) as puestos,ifnull(d.asignados,0) asignados,e.codigoevento,e.nombre,dp.iddivipol,dp.codigopuesto,dp.nombrepuesto,p.idprueba ");
            sql.append(" from cargos c ");
            sql.append(scargos.toString());
            sql.append(" inner join prueba p   on p.idprueba=").append(data.getIdprueba());
            sql.append(" inner join evento e   on e.idprueba=p.idprueba ").append(sevento.toString());
            sql.append(" inner join divipol dp on dp.idPrueba=p.idprueba ").append(sdepartamento.toString()).append(smunicipio.toString()).append(ssitio.toString()).append(szona.toString());
            sql.append(" left  join (select d.iddivipol,d.codigoDepartamento,d.codigoMunicipio,d.codigoZona,d.codigoEvento,d.codigocargo, ");
            sql.append(" 		sum(1) solicitados,sum(case when ifnull(d.nrodoc,0)=0 then 0 else 0 end) asignados from detalle_cargo_x_puesto_x_evento d where d.idprueba=").append(data.getIdprueba()).append(" group by 1,2,3,4,5,6) d  ");
            sql.append(" 		on d.iddivipol=dp.idDivipol and e.codigoEvento=d.codigoEvento and d.codigocargo=c.codigoCargo and dp.codigoZona=d.codigoZona  ");
            sql.append(" where 1=1 ").append(scargo.toString());

            conex.consultar(sql.toString(), new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        result[0] = ResultSetConverter.convert(res);
                    } catch (SQLException ex) {
                        Logger.getLogger(EdicionCuadroDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(EdicionCuadroDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            json = (JSONArray) result[0];
        } catch (Exception e) {
            Logger.getLogger(EdicionCuadroDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return json;
    }

    public JSONArray ConsultarCuadroPrueba(int idPrueba, String usuario) {
        final Object[] result = new Object[1];
        JSONArray json = new JSONArray();

        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" select d.idPrueba,d.idDivipol,d.codigoPuesto,dp.nombrePuesto,c.codigoCargo,c.descripcion,d.codigoEvento,e.nombre, ");
            sql.append(" sum(1) Puestos,  ");
            sql.append(" sum(1) minimo,  ");
            sql.append(" sum(case when ifnull(d.nrodoc,0)>0 then 1 else 0 end) NombradoEvento ");
            sql.append(" from detalle_cargo_x_puesto_x_evento d ");
            sql.append(" inner join prueba p         on p.idprueba = d.idprueba and p.idprueba=").append(idPrueba);
            sql.append(" inner join evento e         on e.codigoEvento = d.codigoEvento ");
            sql.append(" inner join usuario_sitio us on us.usuario=").append(usuario).append(" and us.iddivipol=d.idDivipol ");
            sql.append(" inner join divipol dp       on dp.idDivipol=d.idDivipol ");
            sql.append(" inner join cargos  c        on c.codigoCargo=d.codigoCargo  ");
            sql.append(" group by d.idPrueba,d.idDivipol,d.codigoPuesto,dp.nombrePuesto,c.codigoCargo,c.descripcion,d.codigoEvento,e.nombre ");
            sql.append(" order by d.idPrueba,d.idDivipol,d.codigoPuesto,dp.nombrePuesto,c.codigoCargo,e.nombre ");

            conex.consultar(sql.toString(), new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        result[0] = ResultSetConverter.convert(res);
                    } catch (SQLException ex) {
                        Logger.getLogger(EdicionCuadroDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(EdicionCuadroDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            json = (JSONArray) result[0];
        } catch (Exception e) {
            Logger.getLogger(EdicionCuadroDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return json;
    }

    public JSONArray ConsultarEstadoCuadroSinEvento(int idPrueba, String codigositio) {
        final Object[] result = new Object[1];
        JSONArray json = new JSONArray();

        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" select 1 as primero,dp.nombreDepartamento,dp.nombreMunicipio,dp.codigoPuesto,dp.nombrePuesto,e.codigoEvento,e.nombre,  ");
            sql.append(" case ifnull(cas.Id,0) when 0 Then 'Abierto' Else 'Cerrado' end EstadoEvento,c.codigoCargo,c.descripcion,e.fecha,e.hora_inicial,cm.disminuir,  ");
            sql.append(" sum(1) Puestos, ");
            sql.append(" sum(1) minimo, ");
            sql.append(" sum(case when ifnull(d.nrodoc,0)>0 then 1 else 0 end) NombradoEvento, ");
            sql.append(" sum(case when ifnull(n.nrodoc,0)>0 then 1 else 0 end) Nombrado,d.idPrueba ");
            sql.append(" from detalle_cargo_x_puesto_x_evento d ");
            sql.append(" inner join compania cm on cm.nit=860510031 ");
            sql.append(" inner join prueba p on p.idprueba = d.idprueba");
            sql.append(" inner join evento e on e.codigoEvento=d.codigoEvento and e.tipoSesion='APLICACIONAM' and e.idprueba=p.idprueba and e.fecha=p.fechaaplicacion");
            sql.append(" inner join cargos c  on c.codigoCargo=d.codigoCargo and c.codigocargo in(select distinct codigocargo from nombramiento where idprueba=").append(idPrueba).append(")");
            sql.append(" inner join divipol dp on dp.idDivipol=d.idDivipol ");
            sql.append(" left  join evento e1 on e1.codigoEvento=d.codigoEvento and e1.tipoSesion='APLICACIONPM' and e1.idprueba=p.idprueba and e1.fecha=p.fechaaplicacion  ");
            sql.append(" left  join cierre_asistencia_sesion cas on cas.idDivipol=d.idDivipol and cas.codigoEvento=d.codigoEvento  ");
            sql.append(" left  join nombramiento n on n.idPrueba=d.idPrueba and n.codigoCargo=d.codigoCargo and n.idDivipol=d.idDivipol and n.ubicacion=d.ubicacion");
            sql.append(" where d.idPrueba=").append(idPrueba);
            sql.append(" and   d.codigopuesto='").append(codigositio).append("'");
            sql.append(" group by 1,2,3,4,5,6,7,8,9,10,11,12,13 ");
            sql.append(" union ");
            sql.append(" select 2 as primero,dp.nombreDepartamento,dp.nombreMunicipio,dp.codigoPuesto,dp.nombrePuesto,e.codigoEvento,e.nombre,  ");
            sql.append(" case ifnull(cas.Id,0) when 0 Then 'Abierto' Else 'Cerrado' end EstadoEvento,c.codigoCargo,c.descripcion,e.fecha,e.hora_inicial,cm.disminuir,  ");
            sql.append(" sum(1) Puestos,  ");
            sql.append(" sum(1) minimo, ");
            sql.append(" sum(case when ifnull(d.nrodoc,0)>0 then 1 else 0 end) NombradoEvento,  ");
            sql.append(" sum(case when ifnull(n.nrodoc,0)>0 then 1 else 0 end) Nombrado,d.idPrueba  ");
            sql.append(" from detalle_cargo_x_puesto_x_evento d  ");
            sql.append(" inner join compania cm on cm.nit=860510031 ");
            sql.append(" inner join prueba p on p.idprueba = d.idprueba ");
            sql.append(" inner join evento e on e.codigoEvento=d.codigoEvento and e.tipoSesion='APLICACIONPM' and e.idprueba=p.idprueba and e.fecha=p.fechaaplicacion ");
            sql.append(" inner join cargos c  on c.codigoCargo=d.codigoCargo and c.codigocargo in(select distinct codigocargo from nombramiento where idprueba=").append(idPrueba).append(")");
            sql.append(" inner join divipol dp on dp.idDivipol=d.idDivipol  ");
            sql.append(" left  join cierre_asistencia_sesion cas on cas.idDivipol=d.idDivipol and cas.codigoEvento=d.codigoEvento   ");
            sql.append(" left  join nombramiento n on n.idPrueba=d.idPrueba and n.codigoCargo=d.codigoCargo and n.idDivipol=d.idDivipol and n.ubicacion=d.ubicacion ");
            sql.append(" where d.idPrueba=").append(idPrueba);
            sql.append(" and   d.codigopuesto='").append(codigositio).append("'");
            sql.append(" and   not exists (select 1 from detalle_cargo_x_puesto_x_evento d,evento e ");
            sql.append(" 					  where d.idPrueba=").append(idPrueba);
            sql.append(" 					  and   d.codigopuesto='").append(codigositio).append("'");
            sql.append(" 					  and   d.codigoEvento=e.codigoEvento and e.tipoSesion='APLICACIONAM' ");
            sql.append(" 					  and   d.codigoCargo=c.codigoCargo) ");
            sql.append(" group by 1,2,3,4,5,6,7,8,9,10,11,12,13 ");
            sql.append(" union ");
            sql.append(" select 2 as primero,dp.nombreDepartamento,dp.nombreMunicipio,dp.codigoPuesto,dp.nombrePuesto,'' as codigoEvento,'' as nombre, ");
            sql.append(" '' EstadoEveto,c.codigoCargo,c.descripcion, ");
            sql.append(" '2100-12-12' as fecha,'00:00' as hora_inicial,cm.disminuir, ");
            sql.append(" 0 Puestos, ");
            sql.append(" 0 minimo, ");
            sql.append(" 0 NombradoEvento, ");
            sql.append(" 0 Nombrado,dp.idPrueba");
            sql.append(" from cargos c ");
            sql.append(" inner join compania cm on cm.nit=860510031 ");
            sql.append(" inner join divipol dp on dp.idPrueba=").append(idPrueba).append(" and dp.codigopuesto='").append(codigositio).append("'");
            sql.append(" where not exists(select 1 from detalle_cargo_x_puesto_x_evento d ");
            sql.append(" 					  where d.idPrueba=").append(idPrueba);
            sql.append(" 					  and   d.codigopuesto='").append(codigositio).append("'");
            sql.append(" 					  and   d.codigoCargo=c.codigoCargo) ");
            sql.append("  and c.codigocargo in(select distinct codigocargo from nombramiento where idprueba=").append(idPrueba).append(")");
            sql.append(" group by 1,2,3,4,5,6,7,8,9,10,11,12,13 ");
            sql.append(" order by primero,descripcion,fecha,hora_inicial ");
            /*System.out.println(sql.toString());*/

            conex.consultar(sql.toString(), new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        result[0] = ResultSetConverter.convert(res);
                    } catch (SQLException ex) {
                        Logger.getLogger(EdicionCuadroDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(EdicionCuadroDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            json = (JSONArray) result[0];
        } catch (Exception e) {
            Logger.getLogger(EdicionCuadroDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return json;
    }

    public JSONArray ConsultarEstadoCuadro(int idPrueba, String codigositio) {
        final Object[] result = new Object[1];
        JSONArray json = new JSONArray();

        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" select 1 as primero,dp.nombreDepartamento,dp.nombreMunicipio,dp.codigoPuesto,dp.nombrePuesto,e.codigoEvento,e.nombre, ");
            sql.append(" case ifnull(cas.Id,0) when 0 Then 'Abierto' Else 'Cerrado' end EstadoEvento,c.codigoCargo,c.descripcion,e.fecha,e.hora_inicial, ");
            sql.append(" sum(1) Puestos, ");
            sql.append(" sum(case when ifnull(d.nrodoc,0)>0 then 1 else 0 end) NombradoEvento, ");
            sql.append(" sum(case when ifnull(n.nrodoc,0)>0 then 1 else 0 end) Nombrado ");
            sql.append(" from detalle_cargo_x_puesto_x_evento d ");
            sql.append(" inner join evento e on e.codigoEvento=d.codigoEvento ");
            sql.append(" inner join cargos c  on c.codigoCargo=d.codigoCargo ");
            sql.append(" inner join divipol dp on dp.idDivipol=d.idDivipol ");
            sql.append(" left  join cierre_asistencia_sesion cas on cas.idDivipol=d.idDivipol and cas.codigoEvento=d.codigoEvento  ");
            sql.append(" left  join nombramiento n on n.idPrueba=d.idPrueba and n.codigoCargo=d.codigoCargo and n.idDivipol=d.idDivipol and n.ubicacion=d.ubicacion");
            sql.append(" where d.idPrueba=").append(idPrueba);
            sql.append(" and   d.codigopuesto=").append(codigositio);
            sql.append(" group by 1,2,3,4,5,6,7,8,9,10,11,12 ");
            sql.append(" union ");
            sql.append(" select 2 as primero,dp.nombreDepartamento,dp.nombreMunicipio,dp.codigoPuesto,dp.nombrePuesto,'' as codigoEvento,'' as nombre, ");
            sql.append(" '' EstadoEveto,c.codigoCargo,c.descripcion, ");
            sql.append(" '2100-12-12' as fecha,'00:00' as hora_inicial, ");
            sql.append(" 0 Puestos, ");
            sql.append(" 0 NombradoEvento, ");
            sql.append(" 0 Nombrado ");
            sql.append(" from cargos c ");
            sql.append(" inner join divipol dp on dp.idPrueba=").append(idPrueba).append(" and dp.codigopuesto=").append(codigositio);
            sql.append(" where not exists(select 1 from detalle_cargo_x_puesto_x_evento d ");
            sql.append(" 					  where d.idPrueba=").append(idPrueba);
            sql.append(" 					  and   d.codigopuesto=").append(codigositio);
            sql.append(" 					  and   d.codigoCargo=c.codigoCargo) ");
            sql.append(" group by 1,2,3,4,5,6,7,8,9,10,11,12 ");
            sql.append(" order by primero,descripcion,fecha,hora_inicial ");

            conex.consultar(sql.toString(), new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        result[0] = ResultSetConverter.convert(res);
                    } catch (SQLException ex) {
                        Logger.getLogger(EdicionCuadroDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(EdicionCuadroDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            json = (JSONArray) result[0];
        } catch (Exception e) {
            Logger.getLogger(EdicionCuadroDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return json;
    }

    private String validaNull(String texto) {
        if (texto == null) {
            return "";
        } else {
            return texto;
        }

    }
}
