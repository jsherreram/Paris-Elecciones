/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.seguimiento.ResultSetConverter;
import java.io.FileWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Pedro Rodriguez
 */
public class OrdenesReportesDao {

    private Operaciones conex = new Operaciones();

    public OrdenesReportesDao() {
    }

    public String seguimientoOrdenesSuplencia(int codigoEvento, int iddivipol) {
        final Object[] result = new Object[1];
        JSONArray json = null;

        StringBuilder sql = new StringBuilder();
        StringBuilder ls_temporal = new StringBuilder();
        ls_temporal.append(" call sp_report_ordsuplencia (").append(codigoEvento).append(",").append(iddivipol).append(");");
        this.Ejecutar(ls_temporal.toString());
        ls_temporal.delete(0,ls_temporal.length());
        ls_temporal.append("DROP TEMPORARY TABLE IF EXISTS tmp_ordensuplenciadetalle;");

            
            try {   sql.append(" select o.idOrden,o.fecha_actualiza,pss.nombrePuesto,pss.direccionPuesto,e.nombre as nombreEvento, ");
                sql.append(" concat(ifnull(eo.apellido1,''),' ',ifnull(eo.apellido2,''),' ',ifnull(eo.nombre1,''),' ',ifnull(eo.nombre2,'')) solicitopersonal, ");
                sql.append(" ifnull(eo.celular,eo.telefono) numerocontacto, ");
                sql.append(" ifnull(eos.descripcion,'Pendiente') estadosuplente, ");
                sql.append(" c.descripcion as cargo,t.secuencia,ifnull(es.nrodoc,'-') nrodoc, ");
                sql.append(" concat(ifnull(es.apellido1,''),' ',ifnull(es.apellido2,''),' ',ifnull(es.nombre1,''),' ',ifnull(es.nombre2,'')) nombresuplente, ");
                sql.append(" ifnull(es.celular,es.telefono) movilsuplente, ");
                sql.append(" pds.nombrePuesto as pdsenvia,pds.direccionPuesto as direccionpdsenvia, ");
                sql.append(" (case s.tipoTransporte when 1 then 'a pie' when 2 then 'Taxi' when 3 then 'van' end) as mediotransporte, ");
                sql.append(" s.observaciones,s.placaMovil,s.nombreConductor, ");
                sql.append(" pr.nombrePuesto as pdsprimario,pds.direccionPuesto as direccionpdsprimario ");
                sql.append(" from tmp_ordensuplenciadetalle t ");
                sql.append(" left join ordensuplencia    o     on t.idOrden=o.idOrden ");
                sql.append(" left join ordensuplenciadetalle d on t.idOrdenDetalle=d.idOrdenDetalle ");
                sql.append(" left join suplenciaxdetalle s     on t.idsuplenciadetalle=s.idsuplenciadetalle ");
                sql.append(" left join divipol pr              on t.idpds_primario=pr.iddivipol ");
                sql.append(" left join divipol pds             on s.idDivipolPds=pds.iddivipol ");
                sql.append(" left join divipol pss             on o.idDivipolSitio=pss.iddivipol ");
                sql.append(" left join empleado eo             on o.usuarioOrden=eo.nrodoc ");
                sql.append(" left join empleado es             on s.nrodoc=es.nrodoc ");
                sql.append(" left join evento   e              on o.codigoEvento=e.codigoEvento ");
                sql.append(" left join estadosatencionordenessuplencia eos on s.idEstadoAtencionOrden=eos.idEstadoAtencionOrden ");
                sql.append(" left join cargos   c              on d.codigoCargo=c.codigoCargo ");
                conex.consultar(new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        result[0] = ResultSetConverter.convert(res);
                    } catch (SQLException ex) {
                        Logger.getLogger(OrdenesReportesDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(OrdenesReportesDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql.toString());

            json = (JSONArray) result[0];

        } catch (Exception e) {
            Logger.getLogger(OrdenesReportesDao.class.getName()).log(Level.SEVERE, null, e);
        }
        this.Ejecutar(ls_temporal.toString());

        return json.toString();
    }
    
    public String seguimientoPersonalPds(int idprueba) {
        final Object[] result = new Object[1];
        JSONArray json = null;

        StringBuilder sql = new StringBuilder();
        sql.append(this.getCadenaConsulta(idprueba));
            try {
                conex.consultar(new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        result[0] = ResultSetConverter.convert(res);
                    } catch (SQLException ex) {
                        Logger.getLogger(OrdenesReportesDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(OrdenesReportesDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql.toString());

            json = (JSONArray) result[0];

        } catch (Exception e) {
            Logger.getLogger(OrdenesReportesDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return json.toString();
    }

    public void seguimientoPersonalPds_Plano(int idprueba) {
        
        final StringBuilder texto = new StringBuilder();
        String nombreArchivo = "/data//Seguimiento_Personal_PDS_Prueba_" + idprueba + ".csv";
        texto.append("Evento;Hora Asistencia;Documento;Cargo;Suplente;PDS;Movil;Estado;Hora Salida;Sitio Destino;Hora Llegada;Rps;Movil RPS\n");

        StringBuilder sql = new StringBuilder();
        sql.append(this.getCadenaConsulta(idprueba));
            try {
                conex.consultar(sql.toString(), new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            texto.append(validaNull(res.getString("nombreevento")) + ";");
                            texto.append(validaNull(res.getString("hasistencia")) + ";");
                            texto.append(validaNull(res.getString("nrodoc")) + ";");
                            texto.append(validaNull(res.getString("cargo")) + ";");
                            texto.append(validaNull(res.getString("asistente")) + ";");
                            texto.append(validaNull(res.getString("pds")) + ";");
                            texto.append(validaNull(res.getString("numerocontacto")) + ";");
                            texto.append(validaNull(res.getString("estado")) + ";");
                            texto.append(validaNull(res.getString("horasalida")) + ";");
                            texto.append(validaNull(res.getString("sitioDestino")) + ";");
                            texto.append(validaNull(res.getString("horallegada")) + ";");
                            texto.append(validaNull(res.getString("rps")) + ";");
                            texto.append(validaNull(res.getString("numerocontactorps")) + ";\n");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(GeneraSolicitudesDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            FileWriter fwriter = new FileWriter(nombreArchivo);
            fwriter.write(texto.toString());
            fwriter.flush();
            fwriter.close();

        } catch (Exception e) {
            Logger.getLogger(GeneraSolicitudesDao.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    private String getCadenaConsulta(int idprueba){

        StringBuilder sql = new StringBuilder();

        sql.append(" select ev.nombre as nombreevento,DATE_FORMAT(a.fecharegistro, '%H:%i:%S' ) as hasistencia,ifnull(e.nrodoc,'') nrodoc,cs.descripcion as cargo,");
        sql.append(" concat(ifnull(e.apellido1,''),' ',ifnull(e.apellido2,''),' ',ifnull(e.nombre1,''),' ',ifnull(e.nombre2,'')) asistente, ");
        sql.append(" d1.nombrePuesto as pds,ifnull(e.celular,e.telefono) numerocontacto, ");
        sql.append(" (case ifnull(a.fecharegistro,'') when '' Then 'Ausente' else (case ifnull(eos.descripcion,'') when '' then 'Disponible' else eos.descripcion end) end) as estado, ");
        sql.append(" DATE_FORMAT(s.fecharesolucion2, '%H:%i:%S' ) as horasalida, ");
        sql.append(" d2.nombrePuesto sitioDestino, ");
        sql.append(" DATE_FORMAT(s.fecharesolucion3, '%H:%i:%S' ) as horallegada, ");
        sql.append(" concat(ifnull(e1.apellido1,''),' ',ifnull(e1.apellido2,''),' ',ifnull(e1.nombre1,''),' ',ifnull(e1.nombre2,'')) rps, ");
        sql.append(" ifnull(e1.celular,e1.telefono) numerocontactorps ");
        sql.append(" from asistencia a  ");
        sql.append(" left join empleado e   on e.idempleado=a.idempleado ");
        sql.append(" left join divipol  d1  on d1.iddivipol=a.iddivipol ");
        sql.append(" left join evento   ev  on ev.codigoevento=a.codigoevento ");
        sql.append(" left join (select o.iddivipolsitio,o.codigoevento,d.idOrdenDetalle,s.nrodoc,max(s.idsuplenciadetalle) as detalle from ordensuplencia o,ordensuplenciadetalle d,suplenciaxdetalle s");
        sql.append("            where o.idOrden=d.idOrden and d.idOrdenDetalle=s.idOrdenDetalle group by o.iddivipolsitio,o.codigoevento,d.idOrdenDetalle,s.nrodoc)det on e.nrodoc=det.nrodoc and ev.codigoEvento=det.codigoevento ");
        sql.append(" left join suplenciaxdetalle s on s.idsuplenciadetalle=det.detalle and s.nrodoc=e.nrodoc ");
        sql.append(" left join ordensuplenciadetalle sd on sd.idOrdenDetalle=det.idOrdenDetalle ");
        sql.append(" left join cargos cs on cs.codigoCargo=sd.codigoCargo ");
        sql.append(" left join estadosatencionordenessuplencia eos on s.idEstadoAtencionOrden=eos.idEstadoAtencionOrden ");
        sql.append(" left join divipol  d2  on d2.iddivipol=det.iddivipolsitio ");
        sql.append(" left join nombramiento n on n.iddivipol=det.iddivipolsitio and n.codigocargo=12 ");
        sql.append(" left join empleado  e1 on e1.nrodoc=n.nrodoc ");
        sql.append(" where a.iddivipol in (select u.iddivipol from divipol d,usuario_sitio u  ");
        sql.append("                     where d.idprueba=").append(idprueba).append(" and d.codigomunicipio='11001' and d.idtipositio=5 and d.iddivipol=u.iddivipol) ");
        sql.append(" and   a.codigoevento in (select e.codigoevento from prueba p,evento e where p.idprueba=").append(idprueba).append(" and p.idprueba=e.idprueba and p.fechaaplicacion=e.fecha) ");
        sql.append(" and  not exists (select 1 from detalle_cargo_x_puesto_x_evento d ");
        sql.append("                  where d.iddivipol=a.iddivipol and d.codigoevento=a.codigoevento and e.nrodoc=d.nrodoc) ");
        sql.append(" union ");
        sql.append(" select ev.nombre as nombreevento,DATE_FORMAT(a1.fecharegistro, '%H:%i:%S' ) as hasistencia,ifnull(e.nrodoc,'') nrodoc,cs.descripcion as cargo, ");
        sql.append(" concat(ifnull(e.apellido1,''),' ',ifnull(e.apellido2,''),' ',ifnull(e.nombre1,''),' ',ifnull(e.nombre2,'')) asistente, ");
        sql.append(" d1.nombrePuesto as pds,ifnull(e.celular,e.telefono) numerocontacto,");
        sql.append(" (case ifnull(a1.fecharegistro,'') when '' Then 'Ausente' else (case ifnull(eos.descripcion,'') when '' then 'Disponible' else eos.descripcion end) end) as estado, ");
        sql.append(" DATE_FORMAT(s.fecharesolucion2, '%H:%i:%S' ) as horasalida, ");
        sql.append(" d2.nombrePuesto sitioDestino, ");
        sql.append(" DATE_FORMAT(s.fecharesolucion3, '%H:%i:%S' ) as horallegada, ");
        sql.append(" concat(ifnull(e1.apellido1,''),' ',ifnull(e1.apellido2,''),' ',ifnull(e1.nombre1,''),' ',ifnull(e1.nombre2,'')) rps, ");
        sql.append(" ifnull(e1.celular,e1.telefono) numerocontactorps ");
        sql.append(" from detalle_cargo_x_puesto_x_evento a  ");
        sql.append(" left join empleado e   on e.nrodoc=a.nrodoc ");
        sql.append(" left join divipol  d1  on d1.iddivipol=a.iddivipol ");
        sql.append(" left join evento   ev  on ev.codigoevento=a.codigoevento ");
        sql.append(" left join (select o.iddivipolsitio,o.codigoevento,s.nrodoc,max(s.idsuplenciadetalle) as detalle from ordensuplencia o,ordensuplenciadetalle d,suplenciaxdetalle s ");
        sql.append("            where o.idOrden=d.idOrden and d.idOrdenDetalle=s.idOrdenDetalle group by o.iddivipolsitio,o.codigoevento,s.nrodoc)det  ");
        sql.append(" 		   on e.nrodoc=det.nrodoc and ev.codigoEvento=det.codigoevento ");
        sql.append(" left join suplenciaxdetalle s on s.idsuplenciadetalle=det.detalle and s.nrodoc=e.nrodoc ");
        sql.append(" left join estadosatencionordenessuplencia eos on s.idEstadoAtencionOrden=eos.idEstadoAtencionOrden ");
        sql.append(" left join cargos cs on cs.codigocargo=a.codigocargo ");
        sql.append(" left join divipol  d2  on d2.iddivipol=det.iddivipolsitio ");
        sql.append(" left join nombramiento n on n.iddivipol=det.iddivipolsitio and n.codigocargo=12 ");
        sql.append(" left join empleado  e1 on e1.nrodoc=n.nrodoc ");
        sql.append(" left join asistencia a1 on a1.codigoevento=a.codigoevento and a1.iddivipol=a.iddivipol and e.idempleado=a1.idempleado ");
        sql.append(" where a.iddivipol in (select u.iddivipol from divipol d,usuario_sitio u  ");
        sql.append("                     where d.idprueba=").append(idprueba).append(" and d.codigomunicipio='11001' and d.idtipositio=5 and d.iddivipol=u.iddivipol) ");
        sql.append(" and   a.codigoevento in (select e.codigoevento from prueba p,evento e where p.idprueba=").append(idprueba).append(" and p.idprueba=e.idprueba and p.fechaaplicacion=e.fecha) ");
        sql.append(" and  ifnull(a.nrodoc,'') <> '' and a.nrodoc <> 0 ");
        sql.append(" order by nombreevento,asistente ");
        return sql.toString();
    }

    public Boolean Ejecutar(String ejecutar) {
        Boolean resultado     = false;
        resultado = conex.ejecutar(ejecutar);
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
