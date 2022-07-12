package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.Pago_Conciliado;
import co.com.grupoasd.nomina.modelo.seguimiento.ResultSetConverter;
import java.io.FileWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;


/**
 *
 * @author Pedro Rodriguez
 */
public class PagosConciliacionDao {

    private Operaciones conex = new Operaciones();

    public PagosConciliacionDao() {

    }

    public JSONArray ConsultarPagos(int idPrueba,String codigositio ) {
        final Object[] result = new Object[1];
        JSONArray json = new JSONArray();

        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" select ifnull(c.descripcion,'') as descripcion,ifnull(e.nrodoc,0) nrodoc, ");
            sql.append(" concat(ifnull(e.apellido1,' '),' ',ifnull(e.apellido2, ''),' ',ifnull(e.nombre1,' '),' ',ifnull(e.nombre2,' ')) asistente,");
            sql.append(" ifnull((pp.valor * pp.sesiones),0) valor_presupuestado,");
            sql.append(" ifnull((select sum(pp1.valor) from detalle_cargo_x_puesto_x_evento  d1,empleado e1,asistencia a1,pagoxprueba pp1");
                    sql.append(" where d1.idprueba = d.idprueba");
                    sql.append(" and   d1.nrodoc=e1.nrodoc");
                    sql.append(" and   d1.idDivipol=a1.iddivipol");
                    sql.append(" and   d1.codigoEvento=a1.codigoevento");
                    sql.append(" and   e1.idEmpleado=a1.idempleado");
                    sql.append(" and   d1.idPrueba=pp1.idprueba");
                    sql.append(" and   d1.codigoCargo=pp1.codigoCargo");
                    sql.append(" and   d1.nrodoc = d.nrodoc),0) as valor_asistencia,");
            sql.append(" ifnull(pc.valor_pagado,0) valor_pagado,");
            sql.append(" d.idPrueba,d.idDivipol,ifnull(pc.tipo,1) tipo,ifnull(pc.observaciones_con,'') observaciones_con,");
            sql.append(" ifnull(pc.conciliado,0) conciliado,ifnull(pc.id,0) id");
            sql.append(" from detalle_cargo_x_puesto_x_evento d");
            sql.append(" left join empleado e on e.nrodoc=d.nrodoc");
            sql.append(" left join cargos c on c.codigoCargo=d.codigoCargo");
            sql.append(" left join pagoxprueba pp on pp.idprueba=d.idPrueba and pp.codigoCargo=d.codigoCargo");
            sql.append(" left join pago_conciliado pc on pc.idprueba=d.idprueba and pc.iddivipol=d.iddivipol and pc.tipo=1 and pc.nrodoc=e.nrodoc");
            sql.append(" where d.idprueba = ").append(idPrueba);
            sql.append(" and   d.codigopuesto = ").append(codigositio);
            sql.append(" and   d.codigoevento in (select codigoevento from evento e where e.idprueba=").append(idPrueba).append(" and e.tipoSesion='APLICACIONPM')");
            sql.append(" and   d.codigocargo not in(12,1,21,33)");
            sql.append(" union ");
            sql.append(" select ifnull(c.descripcion,'') as descripcion,ifnull(e.nrodoc,0) nrodoc, ");
            sql.append(" concat(ifnull(e.apellido1,' '),' ',ifnull(e.apellido2, ''),' ',ifnull(e.nombre1,' '),' ',ifnull(e.nombre2,' ')) asistente,");
            sql.append(" round(ifnull((dt.ndias_trans_interno * dt.vr_interno),0),0) as valor_presupuestado,");
            sql.append(" round(ifnull((dt.ndias_trans_interno * dt.vr_interno),0),0) as valor_asistencia,");
            sql.append(" ifnull(pc.valor_pagado,0) valor_pagado,");
            sql.append(" d.idPrueba,d.idDivipol,ifnull(pc.tipo,2) tipo,ifnull(pc.observaciones_con,'') observaciones_con,");
            sql.append(" ifnull(pc.conciliado,0) conciliado,ifnull(pc.id,0) id");
            sql.append(" from detalle_cargo_x_puesto_x_evento d ");
            sql.append(" inner join nombramiento n on d.idPrueba=n.idprueba and n.iddivipol=d.iddivipol and n.codigocargo=d.codigocargo and n.ubicacion=d.ubicacion ");
            sql.append(" inner join divitrans dt on d.idPrueba=dt.idprueba and n.id=dt.idnombramiento and dt.pernocta = 0 ");
            sql.append(" left join empleado e on e.nrodoc = d.nrodoc ");
            sql.append(" left join cargos  c on c.codigoCargo=d.codigoCargo ");
            sql.append(" left  join pago_conciliado pc on pc.idprueba=d.idPrueba and pc.nrodoc=d.nrodoc and pc.tipo=2");
            sql.append(" where d.idPrueba=").append(idPrueba);
            sql.append(" and   d.codigopuesto=").append(codigositio);
            sql.append(" and   d.codigoevento in (select codigoevento from evento e where e.idprueba=").append(idPrueba).append(" and e.tipoSesion='APLICACIONPM')");
            sql.append(" union ");
            sql.append(" select 'Otros' descripcion,ifnull(pc.nrodoc,0) nrodoc, ");
            sql.append(" concat(ifnull(e.apellido1,' '),' ',ifnull(e.apellido2, ''),' ',ifnull(e.nombre1,' '),' ',ifnull(e.nombre2,' ')) asistente,");
            sql.append(" 0 valor_presupuestado,");
            sql.append(" ifnull((select sum(pp1.valor) from detalle_cargo_x_puesto_x_evento  d1,empleado e1,asistencia a1,pagoxprueba pp1");
                    sql.append(" where d1.idprueba = pc.idprueba");
                    sql.append(" and   d1.nrodoc=e1.nrodoc");
                    sql.append(" and   d1.idDivipol=a1.iddivipol");
                    sql.append(" and   d1.codigoEvento=a1.codigoevento");
                    sql.append(" and   e1.idEmpleado=a1.idempleado");
                    sql.append(" and   d1.idPrueba=pp1.idprueba");
                    sql.append(" and   d1.codigoCargo=pp1.codigoCargo");
                    sql.append(" and   d1.nrodoc = pc.nrodoc),0) as valor_asistencia,");
            sql.append(" ifnull(pc.valor_pagado,0) valor_pagado,");
            sql.append(" pc.idPrueba,pc.idDivipol,ifnull(pc.tipo,1) tipo,ifnull(pc.observaciones_con,'') observaciones_con,");
            sql.append(" ifnull(pc.conciliado,0) conciliado,ifnull(pc.id,0) id");
            sql.append(" from pago_conciliado pc");
            sql.append(" inner join divipol dp on pc.iddivipol=dp.iddivipol  and dp.idPrueba=pc.idPrueba and dp.codigoPuesto=").append(codigositio);
            sql.append(" left join empleado e on e.nrodoc=pc.nrodoc");
            sql.append(" where pc.idprueba = ").append(idPrueba);
            sql.append(" and pc.tipo not in(1,2)");
            sql.append(" and not exists (select 1 from detalle_cargo_x_puesto_x_evento d");
            sql.append(" 						where d.idPrueba = pc.idPrueba");
            sql.append(" 						and   d.iddivipol=pc.iddivipol");
            sql.append(" 						and   d.nrodoc = pc.nrodoc");
            sql.append(" 						and  d.codigoevento in (select codigoevento from evento e where e.idprueba=pc.idprueba and e.tipoSesion='APLICACIONPM')");
            sql.append(" 						and   d.codigocargo not in(12,1,21,33))            ");
            sql.append(" order by descripcion, asistente");
     
            conex.consultar(sql.toString(), new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        result[0] = ResultSetConverter.convert(res);
                    } catch (SQLException ex) {
                        Logger.getLogger(PagosConciliacionDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(PagosConciliacionDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            json = (JSONArray) result[0];
        } catch (Exception e) {
            Logger.getLogger(PagosConciliacionDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return json;
    }
    
    public JSONArray ConsultarCuadreCaja(int idPrueba) {
        final Object[] result = new Object[1];
        JSONArray json = new JSONArray();

        try {
            StringBuilder sql = new StringBuilder();
                sql.append(" select dp.nombreDepartamento,dp.nombreMunicipio,dp.codigoPuesto,dp.nombrePuesto, ");
                sql.append(" sum((ep.cantidadSobres * ep.valorSobre)) enviado_prosegur, ");
                sql.append(" ifnull(sum(pc.valor_pagado),0) as valor_pagado, ");
                sql.append(" ifnull(sum(ep.cantidadSobres * ep.valorSobre),0) - ifnull(sum(pc.valor_pagado),0) difiere, ");
                sql.append(" case when ifnull(e.nrodoc,0) > 0 then ifnull(e.celular,e.telefono) else ifnull(e1.celular,e1.telefono) end telefono, ");
                sql.append(" case when ifnull(e.nrodoc,0) > 0  ");
                sql.append(" then concat(ifnull(e.apellido1,' '),' ',ifnull(e.apellido2, ''),' ',ifnull(e.nombre1,' '),' ',ifnull(e.nombre2,' ')) ");
                sql.append(" else concat(ifnull(e1.apellido1,' '),' ',ifnull(e1.apellido2, ''),' ',ifnull(e1.nombre1,' '),' ',ifnull(e1.nombre2,' ')) end rps ");
                sql.append(" from envio_prosegur ep ");
                sql.append(" left join divipol dp on dp.codigoPuesto=ep.codigoPuesto and dp.idPrueba=ep.idPrueba ");
                sql.append(" left join pago_conciliado pc on pc.idprueba=ep.idPrueba and pc.iddivipol=dp.idDivipol ");
                sql.append(" left join nombramiento n on n.idPrueba=ep.idPrueba and ep.codigoPuesto=n.codigoPuesto and n.codigoCargo=12 and n.ubicacion='001'  ");
                sql.append(" left join nombramiento n1 on n1.idPrueba=ep.idPrueba and ep.codigoPuesto=n1.codigoPuesto and n1.codigoCargo=33 and n1.ubicacion='001' "); 
                sql.append(" left join empleado e on e.nrodoc=n.nrodoc ");
                sql.append(" left join empleado e1 on e1.nrodoc=n1.nrodoc ");
                sql.append(" where ep.idPrueba=").append(idPrueba);
                sql.append(" group by dp.nombreDepartamento,dp.nombreMunicipio,dp.codigoPuesto,dp.nombrePuesto ");
                sql.append(" order by dp.nombreDepartamento,dp.nombreMunicipio,dp.codigoPuesto,dp.nombrePuesto ");

            conex.consultar(sql.toString(), new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        result[0] = ResultSetConverter.convert(res);
                    } catch (SQLException ex) {
                        Logger.getLogger(PagosConciliacionDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(PagosConciliacionDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            json = (JSONArray) result[0];
        } catch (Exception e) {
            Logger.getLogger(PagosConciliacionDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return json;
    }
    
    public void ConsultarPagosCsv(int idPrueba) {

    final StringBuilder texto = new StringBuilder();
    String nombreArchivo = "/data//Conciliacion_pagos" + idPrueba + ".csv";

    texto.append("Codigo Puesto;Nombre Puesto;Cargo;Documento;Asistente;Valor Prespuestado;Valor Por Asistencias;Valor Pagado;Concepto;Observaciones;Conciliado\n");

    try {
            StringBuilder sql = new StringBuilder();
            sql.append(" select d.codigopuesto,dp.nombrePuesto,c.descripcion,ifnull(e.nrodoc,0) nrodoc, ");
            sql.append(" concat(ifnull(e.apellido1,' '),' ',ifnull(e.apellido2, ''),' ',ifnull(e.nombre1,' '),' ',ifnull(e.nombre2,' ')) asistente,");
            sql.append(" ifnull((pp.valor * pp.sesiones),0) valor_presupuestado,");
            sql.append(" ifnull((select sum(pp1.valor) from detalle_cargo_x_puesto_x_evento  d1,empleado e1,asistencia a1,pagoxprueba pp1");
                    sql.append(" where d1.idprueba = d.idprueba");
                    sql.append(" and   d1.nrodoc=e1.nrodoc");
                    sql.append(" and   d1.idDivipol=a1.iddivipol");
                    sql.append(" and   d1.codigoEvento=a1.codigoevento");
                    sql.append(" and   e1.idEmpleado=a1.idempleado");
                    sql.append(" and   d1.idPrueba=pp1.idprueba");
                    sql.append(" and   d1.codigoCargo=pp1.codigoCargo");
                    sql.append(" and   d1.nrodoc = d.nrodoc),0) as valor_asistencia,");
            sql.append(" round(ifnull(pc.valor_pagado,0),0) valor_pagado,");
            sql.append(" case ifnull(pc.tipo,1) when 1 then 'Servicios' else 'Transporte' end tipo,ifnull(pc.observaciones_con,'') observaciones_con,");
            sql.append(" ifnull(pc.conciliado,0) conciliado");
            sql.append(" from detalle_cargo_x_puesto_x_evento d");
            sql.append(" inner join divipol dp on dp.idPrueba= d.idPrueba and dp.codigoPuesto=d.codigoPuesto ");
            sql.append(" inner join empleado e on e.nrodoc=d.nrodoc");
            sql.append(" inner join pago_conciliado pc on pc.idprueba=d.idprueba and pc.iddivipol=d.iddivipol and pc.nrodoc=e.nrodoc and pc.tipo=1 and pc.conciliado > 0");
            sql.append(" left join cargos c on c.codigoCargo=d.codigoCargo");
            sql.append(" left join pagoxprueba pp on pp.idprueba=d.idPrueba and pp.codigoCargo=d.codigoCargo");
            sql.append(" where d.idprueba = ").append(idPrueba);
            sql.append(" and   d.codigoevento in (select codigoevento from evento e where e.idprueba=").append(idPrueba).append(" and e.tipoSesion='APLICACIONPM')");
            sql.append(" and   d.codigocargo not in(12,1,21,33)");
            sql.append(" union all ");
            sql.append(" select d.codigopuesto,dp.nombrePuesto,c.descripcion,ifnull(e.nrodoc,0) nrodoc, ");
            sql.append(" concat(ifnull(e.apellido1,' '),' ',ifnull(e.apellido2, ''),' ',ifnull(e.nombre1,' '),' ',ifnull(e.nombre2,' ')) asistente,");
            sql.append(" round(ifnull(pc.valor_pagado,0),0) valor_asistencia,");
            sql.append(" round(ifnull((dt.ndias_trans_interno * dt.vr_interno),0),0) valor_presupuestado,");
            sql.append(" round(ifnull(pc.valor_pagado,0),0) valor_pagado,");
            sql.append(" case ifnull(pc.tipo,2) when 2 then 'Transporte' else 'Servicios' end  tipo,ifnull(pc.observaciones_con,'') observaciones_con,");
            sql.append(" ifnull(pc.conciliado,0) conciliado");
            sql.append(" from detalle_cargo_x_puesto_x_evento d ");
            sql.append(" inner join divipol dp on dp.idPrueba= d.idPrueba and dp.codigoPuesto=d.codigoPuesto ");
            sql.append(" inner join nombramiento n on d.idPrueba=n.idprueba and n.iddivipol=d.iddivipol and n.codigocargo=d.codigocargo and n.ubicacion=d.ubicacion ");
            sql.append(" inner join divitrans dt on d.idPrueba=dt.idprueba and n.id=dt.idnombramiento and dt.pernocta = 0 ");
            sql.append(" inner join empleado e on e.nrodoc = d.nrodoc ");
            sql.append(" inner join pago_conciliado pc on pc.idprueba=d.idprueba and pc.iddivipol=d.iddivipol and pc.nrodoc=e.nrodoc and pc.tipo=2 and pc.conciliado > 0");
            sql.append(" left join cargos  c on c.codigoCargo=d.codigoCargo ");
            sql.append(" where d.idPrueba=").append(idPrueba);
            sql.append(" and   d.codigoevento in (select codigoevento from evento e where e.idprueba=").append(idPrueba).append(" and e.tipoSesion='APLICACIONPM')");
            sql.append(" order by codigopuesto,descripcion, asistente");
            conex.consultar(sql.toString(), new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            texto.append(validaNull(res.getString("codigopuesto")) + ";");
                            texto.append(validaNull(res.getString("nombrePuesto")) + ";");
                            texto.append(validaNull(res.getString("descripcion")) + ";");
                            texto.append(validaNull(res.getString("nrodoc")) + ";");
                            texto.append(validaNull(res.getString("asistente")) + ";");
                            texto.append(validaNull(res.getString("valor_presupuestado")) + ";");
                            texto.append(validaNull(res.getString("valor_asistencia")) + ";");
                            texto.append(validaNull(res.getString("valor_pagado")) + ";");
                            texto.append(validaNull(res.getString("tipo")) + ";");
                            texto.append(validaNull(res.getString("observaciones_con")) + ";");
                            texto.append(validaNull(res.getString("conciliado")) + ";\n");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(PagosConciliacionDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            FileWriter fwriter = new FileWriter(nombreArchivo);
            fwriter.write(texto.toString());
            fwriter.flush();
            fwriter.close();

        } catch (Exception e) {
            Logger.getLogger(PagosConciliacionDao.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public int insertarPagoConciliado(Pago_Conciliado pago) {

            StringBuilder sql = new StringBuilder();
            sql.append(" insert into pago_conciliado (id,idPrueba,iddivipol,tipo,conciliado,nrodoc,valor_calculado,valor_pagado,usuario_con,usuario_rps,observaciones_rps,observaciones_con) Values ( 0,");
            sql.append("").append(pago.getIdPrueba()).append(",");
            sql.append("").append(pago.getIddivipol()).append(",");
            sql.append("").append(pago.getTipo()).append(",");
            sql.append("").append(pago.getConciliado()).append(",");
            sql.append("").append(pago.getNrodoc()).append(",");
            sql.append("").append(pago.getValor_calculado()).append(",");
            sql.append("").append(pago.getValor_pagado()).append(",");
            sql.append("").append(pago.getUsuario_con()).append(",");
            sql.append("").append(pago.getUsuario_rps()).append(",");
            sql.append("'").append(pago.getObservaciones_rps()).append("',");
            sql.append("'").append(pago.getObservaciones_cont()).append("');");

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
                        Logger.getLogger(PagosConciliacionDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql.toString());
            return ((int) result[0]);
        }

    public Boolean actualizarPagoConciliado(Pago_Conciliado pago) {
        Boolean resultado = false;
        StringBuilder sql = new StringBuilder();
        sql.append(" update pago_conciliado set ");
        sql.append("conciliado=").append(pago.getConciliado()).append(",");
        sql.append("valor_calculado=").append(pago.getValor_calculado()).append(",");
        sql.append("valor_pagado=").append(pago.getValor_pagado()).append(",");
        sql.append("usuario_rps=").append(pago.getUsuario_rps()).append(",");
        sql.append("usuario_con=").append(pago.getUsuario_con()).append(",");
        sql.append("observaciones_con='").append(pago.getObservaciones_cont()).append("',");
        sql.append("observaciones_rps='").append(pago.getObservaciones_rps()).append("' where id=").append(pago.getId());
        resultado = conex.ejecutar(sql.toString());
        return resultado;
    }
    
    private String validaNull(String texto) {
        if (texto == null) {
            return "";
        } else {
            return texto;
        }

    }
}
