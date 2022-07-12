/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.MiAsignacion;
import co.com.grupoasd.nomina.modelo.MiAsistencia;
import co.com.grupoasd.nomina.modelo.MiCoordinador;
import co.com.grupoasd.nomina.modelo.MisPagos;
import co.com.grupoasd.nomina.modelo.MisPruebas;
import co.com.grupoasd.nomina.modelo.MisViaticos;
import co.com.grupoasd.nomina.modelo.PagoViaticos;
import co.com.grupoasd.nomina.modelo.PendienteAprobarViatico;
import java.io.FileWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wilfer Carvajal
 */
public class GestionPagosDao {

    private Operaciones conex = new Operaciones();

    public GestionPagosDao() {

    }
    public List<MisPruebas> listar(int idEmp, int idPrueba, String cargoNombre) {
        final List<MisPruebas> lstPruebas = new ArrayList<>();

        try {
            String s1 = Integer.toString(idPrueba);
            StringBuilder sql = new StringBuilder();
            sql.append(" select e.idempleado,IFNULL(UNIX_TIMESTAMP(p.fechaaplicacion),0) fechanumero,p.idprueba,p.nombre,p.tprueba as idtipo_prueba, ");
            sql.append(" tp.tprueba as tipo_prueba,p.estadoprueba,p.dias,p.fechaaplicacion, ");
            sql.append(" case when epp.codigo='SELECCIONADA' then 'CANCELAR PARTICIPACIÓN' else epp.codigo end as estpersona_codigo, ");
            sql.append(" e.nombre1,e.nombre2,e.apellido1,e.apellido2,e.tipodoc,e.nrodoc,car.descripcion as cargonombre,car.codigocargo,");
            sql.append(" concat(e.nombre1,' ',e.nombre2,' ',e.apellido1,' ',e.apellido2) as nombres,");
            sql.append("(select med.idmedio_pago from medio_pago med where med.idmedio_pago=ep.idmedio_pago) as idmedio_pago,");
            sql.append("(select med.nombre from medio_pago med where med.idmedio_pago=ep.idmedio_pago) as medionombre,tiden.descripcion as nombredocumento");
            sql.append(" from empleado e,empleado_x_prueba_x_estado ep,prueba p,tipoprueba tp,estado_persona_prueba epp,cargos car,tipo_iden tiden ");
            sql.append(" where e.idempleado = ? ");
            sql.append(" and   ep.idempleado= e.idempleado ");
            sql.append(" and   ep.idprueba  = p.idprueba ");
            sql.append(" and   ep.idestpersona not in (6) ");
            sql.append(" and   p.tprueba    = tp.idtprueba ");
            sql.append(" and ep.idestpersona = epp.idestpersona ");
            sql.append(" and car.codigocargo = ep.codigocargo and  tiden.codigo = e.tipodoc ");
            sql.append(" and p.estadoprueba not in ('EN ADMINISTRACION','CERRADA') ");
            if (cargoNombre.length() > 1) {
                sql.append(" and   car.descripcion = '").append(cargoNombre.trim()).append("' ");
            }
            if (idPrueba > 0) {
                sql.append(" and   ep.idprueba= ");
                sql.append(s1);
            }

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {

                            MisPruebas reg = new MisPruebas();
                            reg.setIdempleado(res.getInt("idempleado"));
                            reg.setFechanumero(res.getInt("fechanumero"));
                            reg.setIdprueba(res.getInt("idprueba"));
                            reg.setNombre_prueba(res.getString("nombre"));
                            reg.setIdtipo_prueba(res.getInt("idtipo_prueba"));
                            reg.setTipo_rueba(res.getString("tipo_prueba"));
                            reg.setEstadoprueba(res.getString("estadoprueba"));
                            reg.setDias(res.getInt("dias"));
                            reg.setFechaaplicacion(res.getDate("fechaaplicacion"));
                            reg.setEstpersona_codigo(res.getString("estpersona_codigo"));
                            reg.setNombre1(res.getString("nombre1"));
                            reg.setNombre2(res.getString("nombre2"));
                            reg.setApellido1(res.getString("apellido1"));
                            reg.setApellido2(res.getString("apellido2"));
                            reg.setTipodoc(res.getString("tipodoc"));
                            reg.setNrodoc(res.getInt("nrodoc"));
                            reg.setCargonombre(res.getString("cargonombre"));
                            reg.setCodigocargo(res.getString("codigocargo"));
                            reg.setIdmedio_pago(res.getInt("idmedio_pago"));
                            reg.setMedionombre(res.getString("medionombre"));
                            reg.setNombres(res.getString("nombres"));
                            reg.setNombredocumento(res.getString("nombredocumento"));

                            lstPruebas.add(reg);

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionPagosDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql.toString(), idEmp);

        } catch (Exception e) {
            Logger.getLogger(GestionPagosDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return lstPruebas;
    }
    public List<MiAsistencia> listarAsistencia(int idEmp, int idPrueba, String cargoNombre) {
        final List<MiAsistencia> lstAsistencia = new ArrayList<>();

        try {
            StringBuilder sql = new StringBuilder();
                   sql.append(" select even.nombre as nombreevento,even.escapacitacion,even.fecha as fechaevento,even.tiposesion,  "); 
                   sql.append(" fun_asistio(e.idempleado,even.idprueba,ps.codigocargo,ps.codigoevento) as asistio,ps.descripcion as nombresesion, "); 
                   sql.append(" round((case tbase.aplica when 1 then (case ps.aplicaretencion when 1 then (ps.valor/(100-pcs.porcentaje)*100) else (ps.valor) end ) else (ps.valor) end),2) * fun_asistio(e.idempleado,even.idprueba,ps.codigocargo,ps.codigoevento) as valor, "); 
                   sql.append(" case (fun_asistio(e.idempleado,even.idprueba,ps.codigocargo,ps.codigoevento)) "); 
                   sql.append(" when 0 then 'No Registra' "); 
                   sql.append(" else case (select sum(a.biometrico) from asistencia a where a.idempleado=e.idempleado and a.codigoEvento=even.codigoevento)  "); 
                   sql.append(" when 0 then 'Click' else 'Biometrico' end end as medio_registra  "); 
                   sql.append(" from empleado e,evento even,detalle_cargo_x_puesto_x_evento dcpe,pagoxsesion ps,cargos car, "); 
                   sql.append("      (  select case when ifnull(sum(ps.valor * fun_asistio(e.idempleado,even.idprueba,ps.codigocargo,ps.codigoevento)),0) > base then 1 else 0 end as aplica "); 
                   sql.append("         from empleado e,evento even,detalle_cargo_x_puesto_x_evento dcpe,pagoxsesion ps,cargos car,parametros_impuestos parimp "); 
                   sql.append("         where e.idempleado = ? "); 
                   sql.append("         and   even.idprueba= ? "); 
                   sql.append("         and   e.nrodoc = dcpe.nrodoc  "); 
                   sql.append("         and   even.codigoevento=dcpe.codigoevento  "); 
                   sql.append("         and   car.descripcion = '").append(cargoNombre.trim()).append("' ");
                   sql.append("         and  car.codigocargo=dcpe.codigocargo   "); 
                   sql.append("         and   ps.codigoevento=even.codigoevento   "); 
                   sql.append("         and   ps.codigocargo=dcpe.codigocargo   "); 
                   sql.append("         and   ps.aplicaretencion = 1 "); 
                   sql.append("         and   ps.idPasoXSesion=(case ps.salonhasta when 0 then ps.idPasoXSesion else fun_tarifa(e.idempleado,even.idprueba,ps.codigocargo,ps.codigoevento) end) "); 
                   sql.append("         and   parimp.concepto = 'RETENCION' "); 
                   sql.append("         )tbase, "); 
                   sql.append("         (select sum(porcentaje) as porcentaje from parametros_impuestos parimp where parimp.concepto in ('RETENCION','RETICA')) pcs "); 
                   sql.append(" where e.idempleado = ? "); 
                   sql.append(" and   even.idprueba= ? "); 
                   sql.append(" and   e.nrodoc = dcpe.nrodoc  "); 
                   sql.append(" and   even.codigoevento=dcpe.codigoevento  "); 
                   sql.append(" and   car.descripcion = '").append(cargoNombre.trim()).append("' ");
                   sql.append(" and  car.codigocargo=dcpe.codigocargo   "); 
                   sql.append(" and   ps.codigoevento=even.codigoevento   "); 
                   sql.append(" and   ps.codigocargo=dcpe.codigocargo   "); 
                   sql.append(" and   ps.idPasoXSesion=(case ps.salonhasta when 0 then ps.idPasoXSesion else fun_tarifa(e.idempleado,even.idprueba,ps.codigocargo,ps.codigoevento) end) "); 

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            MiAsistencia reg = new MiAsistencia();
                            reg.setNombreevento(res.getString("nombreevento"));
                            reg.setEscapacitacion(res.getInt("escapacitacion"));
                            reg.setFechaevento(res.getDate("fechaevento"));
                            reg.setTiposesion(res.getString("tiposesion"));
                            reg.setAsistio(res.getInt("asistio"));
                            reg.setValor(res.getInt("valor"));
                            reg.setNombresesion(res.getString("nombresesion"));
                            reg.setMedioregistra(res.getString("medio_registra"));
                            lstAsistencia.add(reg);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionPagosDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql.toString(), idEmp, idPrueba,idEmp, idPrueba);

        } catch (Exception e) {
            Logger.getLogger(GestionPagosDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return lstAsistencia;
    }
    public List<MisPagos> listarpagos(int idEmp, int idPrueba, String cargoNombre) {
        final List<MisPagos> lstAsistencia = new ArrayList<>();

        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" Select case Medio when 'V' then 'Viaticos' else 'Sesiones de Aplicación' end as concepto,mp.nombre mediopago, ");
            sql.append(" cc.referencia_pago,cc.valor_pagado,cc.fecha_pago,mp.descripcion observaciones,cm.nombre,cc.pagado, ");
            sql.append(" cc.id as idcuentacobro,IFNULL(UNIX_TIMESTAMP(cc.fecha_generada),0) fechanumero ");
            sql.append(" from empleado_x_prueba_x_estado eps,cuentacobro cc,medio_pago mp,compania cm,cargos car ");
            sql.append(" where eps.idempleado = cc.idempleado ");
            sql.append(" and   eps.idempleado = ? ");
            sql.append(" and   eps.idprueba = ? ");
            sql.append(" and   car.descripcion = '").append(cargoNombre.trim()).append("' ");
            sql.append(" and  car.codigocargo=eps.codigocargo  ");
            sql.append(" and   cc.idmedio_pago= mp.idmedio_pago ");
            sql.append(" and   eps.idestpersona not in(6)");

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            MisPagos reg = new MisPagos();
                            reg.setConcepto(res.getString("concepto"));
                            reg.setMediopago(res.getString("mediopago"));
                            reg.setReferencia_pago(res.getString("referencia_pago"));
                            reg.setValor_pagado(res.getInt("valor_pagado"));
                            reg.setFecha_pago(res.getDate("fecha_pago"));
                            reg.setObservaciones(res.getString("observaciones"));
                            reg.setNombre(res.getString("nombre"));
                            reg.setPagado(res.getInt("pagado"));
                            reg.setIdcuentacobro(res.getInt("idcuentacobro"));
                            reg.setFechanumero(res.getInt("fechanumero"));

                            lstAsistencia.add(reg);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionPagosDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql.toString(),idEmp,idPrueba);

        } catch (Exception e) {
            Logger.getLogger(GestionPagosDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return lstAsistencia;
    }
    public List<MisViaticos> listarViaticos(int idEmp, int idPrueba, String cargoNombre) {
        final List<MisViaticos> lstViaticos = new ArrayList<>();

        try {
            StringBuilder sql = new StringBuilder();
                sql.append(" select car.descripcion as nombrecargo, pru.nombre as nombreprueba, ");
                sql.append(" concat(e.nombre1,' ',e.nombre2,' ',e.apellido1,' ',e.apellido2) as nombres, ");
                sql.append(" e.nrodoc,tide.descripcion as documento, sum(dtr.vr_aereo) as vr_aereo, ");
                sql.append(" sum(dtr.vr_municipal)  as vr_municipal,sum(dtr.vr_rural) as vr_rural,sum(dtr.vr_interno) as vr_interno, ");
                sql.append(" sum((dtr.vr_municipal + dtr.vr_rural + dtr.vr_interno)) as total, ");
                sql.append(" dtr.ndias, ");
                sql.append(" dtr.pernocta, ");
                sql.append(" sum((dtr.ndias * dtr.vr_dia)) as total_viaticos, ");
                sql.append(" sum((dtr.vr_municipal + dtr.vr_rural + dtr.vr_interno + dtr.vr_aereo) + (dtr.ndias * dtr.vr_dia)) as total_pagarviatico,");
                sql.append(" edv.descripcion as estadopago");
                sql.append(" from  empleado e,tipo_iden tide,nombramiento nm,divitrans dtr, prueba pru,cargos car,estado_divitrans edv");
                sql.append(" where e.idempleado= ?");
                sql.append(" and   e.tipodoc   = tide.codigo");
                sql.append(" and   e.nrodoc    = nm.nrodoc ");
                sql.append(" and   nm.idprueba = ? ");
                sql.append(" and   nm.idprueba = dtr.idprueba ");
                sql.append(" and   nm.id       = dtr.idnombramiento ");
                sql.append(" and   car.codigoCargo= nm.codigoCargo and nm.idprueba = pru.idprueba");
                sql.append(" and   edv.idEstadoDivitrans = dtr.idEstadoDivitrans");

            conex.consultar(new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            MisViaticos reg = new MisViaticos();
                            reg.setNombrecargo(res.getString("nombrecargo"));
                            reg.setNombreprueba(res.getString("nombreprueba"));
                            reg.setNombres(res.getString("nombres"));
                            reg.setNrodoc(res.getInt("nrodoc"));
                            reg.setDocumento(res.getString("documento"));
                            reg.setVr_municipal(res.getInt("vr_municipal"));
                            reg.setVr_rural(res.getInt("vr_rural"));
                            reg.setVr_interno(res.getInt("vr_interno"));
                            reg.setTotal(res.getInt("total"));
                            reg.setNdias(res.getDouble("ndias"));
                            reg.setPernocta(res.getInt("pernocta"));
                            reg.setTotal_viaticos(res.getInt("total_viaticos"));
                            reg.setTotal_pagarviatico(res.getInt("total_pagarviatico"));
                            reg.setEstadopago(res.getString("estadopago"));
                            lstViaticos.add(reg);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionPagosDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql.toString(),idEmp,idPrueba);

        } catch (Exception e) {
            Logger.getLogger(GestionPagosDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return lstViaticos;
    }
    public List<MiAsignacion> listarAsignacion(int idEmp, int idPrueba, String cargoNombre) {
        final List<MiAsignacion> lstAsignacion = new ArrayList<>();

        try {
            StringBuilder sql = new StringBuilder();
                sql.append(" select even.nombre as nombresesion,even.fecha,cast(even.hora_inicial as char(5)) hora_inicial,cast(even.hora_final as char(5)) hora_final, ");
                sql.append(" mun.nombre as municipio,dcpe.codigoPuesto,dep.nombre nombredepartamento,div1.nombrePuesto, ");
                sql.append(" div1.direccionPuesto,div1.cantidadSalones,div1.nombreRector,div1.telefono, ");
                sql.append(" ' ' as nodnombres,' ' as instllegada, ");
                sql.append(" ' ' as email, ");
                sql.append(" ' ' as celular,div1.iddivipol,dcpe.id,");
                sql.append(" case car.consalon when 1 then dcpe.salon else (select ifnull(a.salon,'') from asignacion_salon  a ");
                sql.append(" where a.iddivipol=div1.iddivipol and (a.codigoevento=even.codigoevento or ifnull(a.codigoevento,'')='') and a.idempleado=e.idempleado) end as salon ");
                sql.append(" from empleado e,detalle_cargo_x_puesto_x_evento dcpe,cargos car, ");
                sql.append(" evento even,divipol div1,municipio mun,departamento dep ");
                sql.append(" where e.idempleado   = ? ");
                sql.append(" and   e.nrodoc       = dcpe.nrodoc ");
                sql.append(" and  dcpe.codigocargo= car.codigocargo ");
                sql.append(" and   dcpe.iddivipol = div1.iddivipol ");
                sql.append(" and dcpe.codigoevento= even.codigoevento ");
                sql.append(" and   div1.idprueba   = ? ");
                sql.append(" and   div1.idprueba   = even.idprueba ");
                sql.append(" and   div1.codigoDepartamento= mun.codigoDepartamento ");
                sql.append(" and   div1.codigomunicipio   = mun.codigoMunicipio ");
                sql.append(" and   div1.codigoDepartamento= dep.codigo ");

            conex.consultar(new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            MiAsignacion reg = new MiAsignacion();
                            reg.setNombresesion(res.getString("nombresesion"));
                            reg.setFecha(res.getDate("fecha"));
                            reg.setHora_inicial(res.getString("hora_inicial"));
                            reg.setHora_final(res.getString("hora_final"));
                            reg.setMunicipio(res.getString("municipio"));
                            reg.setCodigoPuesto(res.getString("codigoPuesto"));
                            reg.setNombredepartamento(res.getString("nombredepartamento"));
                            reg.setNombrePuesto(res.getString("nombrePuesto"));
                            reg.setDireccionPuesto(res.getString("direccionPuesto"));
                            reg.setCantidadSalones(res.getInt("cantidadSalones"));
                            reg.setNombreRector(res.getString("nombreRector"));
                            reg.setTelefono(res.getString("telefono"));
                            reg.setNodnombres(res.getString("nodnombres"));
                            reg.setInstllegada(res.getString("instllegada"));
                            reg.setEmail(res.getString("email"));
                            reg.setCelular(res.getString("celular"));
                            reg.setIddivipol(res.getInt("iddivipol"));
                            reg.setId(res.getInt("id"));
                            reg.setSalon(res.getString("salon"));
                            

                            lstAsignacion.add(reg);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionPagosDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql.toString(),idEmp,idPrueba);

        } catch (Exception e) {
            Logger.getLogger(GestionPagosDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return lstAsignacion;
    }
    public MiCoordinador listarCoordinador(int arg1, int arg2) {
        final MiCoordinador lstAsignacion = new MiCoordinador();
        try {
            StringBuilder sql = new StringBuilder();
                sql.append(" select concat(nod.nombre1,' ',nod.nombre2,' ',nod.apellido1,' ',nod.apellido2) as nodnombres, ");
                sql.append(" concat(nod.email,' ',nod.email2) as email, ");
                sql.append(" concat(nod.celular,' ',nod.celular2) as celular ");
                sql.append(" from empleado nod,empleado_x_prueba_x_estado eps,detalle_cargo_x_puesto_x_evento dcpe ");
                sql.append(" where dcpe.id= ? and nod.idempleado=eps.idempleado and eps.codigocargo='12' ");
                sql.append(" and dcpe.iddivipol=eps.iddivipol and nod.idempleado = fun_coor_asd( ? , ? ) ");

            conex.consultar(new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            MiCoordinador reg = new MiCoordinador();
                            lstAsignacion.setEmail(res.getString("email"));
                            lstAsignacion.setCelular(res.getString("celular"));
                            lstAsignacion.setNodnombres(res.getString("nodnombres"));
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionPagosDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql.toString(),arg2,arg1,arg2);

        } catch (Exception e) {
            Logger.getLogger(GestionPagosDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return lstAsignacion;
    }
    public List<PagoViaticos> listarPagoViaticos(int idEmp) {
      final List<PagoViaticos> lstViaticos = new ArrayList<>();

      try {
          StringBuilder sql = new StringBuilder();
            sql.append(" Select ");
            sql.append(" cc.id as codigo_de_pago,");
            sql.append(" 'No'  as se_realizo_pago,");
            sql.append(" cc.fecha_pago as fecha_pago,");
            sql.append(" ''    as hora_de_pago,");
            sql.append(" cc.observaciones,");
            sql.append(" 'Viaticos'  as Concepto,");
            sql.append(" mp.nombre   as medio_de_pago,");
            sql.append(" cc.idbanco,");
            sql.append(" cc.numeroCuenta,");
            sql.append(" cc.idtipocuenta,");
            sql.append(" tiden.descripcion as tipo_documento,");
            sql.append(" '' homologacion,");
            sql.append(" e.nrodoc as identificacion,");
            sql.append(" e.apellido1,");
            sql.append(" e.apellido2,");
            sql.append(" e.nombre1,");
            sql.append(" e.nombre2,");
            sql.append(" cc.valor_xpagar as total_pagar,");
            sql.append(" (select car.descripcion from cargos as car where cast(cc.idcargo_puesto_evento as char(2)) = car.codigoCargo) as Cargo_prueba,");
            sql.append(" e.celular,");
            sql.append(" e.telefono,");
            sql.append(" pru.nombre as nombre_prueba,");
            sql.append(" div1.nombreDepartamento as Nodo,");
            sql.append(" mun.nombre as municipio_sitio,");
            sql.append(" div1.codigopuesto as codigo_sitio,");
            sql.append(" (select mre.nombre from municipio as mre where mre.codigomunicipio=e.codigomunicipio) as residencia,");
            sql.append(" dtr.vr_interno   as transporte_interno,");
            sql.append(" dtr.vr_municipal as transporte_intermunicipal,");
            sql.append(" dtr.vr_rural     as transporte_rural,");
            sql.append(" dtr.vr_aereo     as transporte_aeropuerto,");
            sql.append(" (dtr.vr_municipal + dtr.vr_rural + dtr.vr_interno + dtr.vr_aereo) as total_transporte,");
            sql.append(" dtr.pernocta,");
            sql.append(" dtr.ndias,");
            sql.append(" dtr.vr_dia,");
            sql.append(" case car.codigoCargo when 27 then dtr.vr_interno * 6 else (dtr.ndias * dtr.vr_dia) end as total_viaticos,");
            sql.append(" (cc.valor_rtfte + cc.valor_rtica) as retenciones_aplicadas,");
            sql.append(" (ifnull(nro_envio,0) + 1) as envio_numero");
            sql.append(" from cuentacobro as cc,medio_pago as mp,empleado as e,tipo_iden as tiden,");
            sql.append(" 	  prueba as pru,empleado_x_prueba_x_estado as eps,divipol as div1,municipio as mun,divitrans as dtr");
            sql.append(" where cc.pagado=0 and cc.medio='V'");
            sql.append(" and   cc.idmedio_pago = mp.idmedio_pago");
            sql.append(" and   cc.idempleado = e.idempleado");
            sql.append(" and   e.tipodoc = tiden.codigo");
            sql.append(" and   cc.idprueba = pru.idprueba");
            sql.append(" and   cc.idempleado = eps.idempleado");
            sql.append(" and   cc.idprueba = eps.idprueba");
            sql.append(" and   cast(cc.idcargo_puesto_evento as char(2)) = eps.codigoCargo");
            sql.append(" and   eps.iddivipol = div1.idDivipol");
            sql.append(" and   div1.codigoMunicipio = mun.codigoMunicipio");
            sql.append(" and   cc.id_divitrans = dtr.id");
            if (idEmp > 0) {
             sql.append(" and   cc.idempleado = ? ");
            }
            else{
             sql.append(" and   cc.idempleado > ? ");
            }

          conex.consultar(new Operaciones.OperacionConsulta() {
              @Override
              public void respuestaConsulta(ResultSet res) {
                  try {
                      while (res.next()) {
                          PagoViaticos reg = new PagoViaticos();
                                reg.setCodigo_de_pago(res.getInt("codigo_de_pago"));
                                reg.setSe_realizo_pago(res.getString("se_realizo_pago"));
                                reg.setFecha_pago(res.getDate("fecha_pago"));
                                reg.setHora_de_pago(res.getString("hora_de_pago"));
                                reg.setObservaciones(res.getString("observaciones"));
                                reg.setConcepto(res.getString("Concepto"));
                                reg.setMedio_de_pago(res.getString("medio_de_pago"));
                                reg.setIdbanco(res.getInt("idbanco"));
                                reg.setNumeroCuenta(res.getString("numeroCuenta"));
                                reg.setIdtipocuenta(res.getInt("idtipocuenta"));
                                reg.setTipo_documento(res.getString("tipo_documento"));
                                reg.setHomologacion(res.getString("homologacion"));
                                reg.setIdentificacion(res.getInt("identificacion"));
                                reg.setApellido1(res.getString("apellido1"));
                                reg.setApellido2(res.getString("apellido2"));
                                reg.setNombre1(res.getString("nombre1"));
                                reg.setNombre2(res.getString("nombre2"));
                                reg.setTotal_pagar(res.getInt("total_pagar"));
                                reg.setCargo_prueba(res.getString("Cargo_prueba"));
                                reg.setCelular(res.getString("celular"));
                                reg.setTelefono(res.getString("telefono"));
                                reg.setNombre_prueba(res.getString("nombre_prueba"));
                                reg.setNodo(res.getString("Nodo"));
                                reg.setMunicipio_sitio(res.getString("municipio_sitio"));
                                reg.setCodigo_sitio(res.getString("codigo_sitio"));
                                reg.setResidencia(res.getString("residencia"));
                                reg.setTransporte_interno(res.getInt("transporte_interno"));
                                reg.setTransporte_intermunicipal(res.getInt("transporte_intermunicipal"));
                                reg.setTransporte_rural(res.getInt("transporte_rural"));
                                reg.setTransporte_aeropuerto(res.getInt("transporte_aeropuerto"));
                                reg.setTotal_transporte(res.getInt("total_transporte"));
                                reg.setPernocta(res.getInt("pernocta"));
                                reg.setNdias(res.getDouble("ndias"));
                                reg.setVr_dia(res.getInt("vr_dia"));
                                reg.setTotal_viaticos(res.getDouble("total_viaticos"));
                                reg.setRetenciones_aplicadas(res.getDouble("retenciones_aplicadas"));
                                reg.setEnvio_numero(res.getInt("envio_numero"));
                          lstViaticos.add(reg);
                      }
                  } catch (SQLException ex) {
                      Logger.getLogger(GestionPagosDao.class.getName()).log(Level.SEVERE, null, ex);
                  }
              }
          }, sql.toString(),idEmp);

      } catch (Exception e) {
          Logger.getLogger(GestionPagosDao.class.getName()).log(Level.SEVERE, null, e);
      }
      return lstViaticos;
  }
    public void listarPlanoViaticos(int idEmp,String valor) {
        
        String filename = "/data/Plano_Viaticos_" + valor + ".csv";
        final StringBuilder texto = new StringBuilder();

      try {
          texto.append("CODIGO DE PAGO;SE REALIZO PAGO;FECHA PAGO;HORA DE PAGO;OBSERVACIONES;CONCEPTO;MEDIO DE PAGO;IDBANCO;NUMEROCUENTA;IDTIPOCUENTA;TIPO DOCUMENTO;HOMOLOGACION;IDENTIFICACION;APELLIDO1;APELLIDO2;NOMBRE1;NOMBRE2;TOTAL PAGAR;CARGO PRUEBA;CELULAR;TELEFONO;NOMBRE PRUEBA;NODO;MUNICIPIO SITIO;CODIGO SITIO;RESIDENCIA;TRANSPORTE INTERNO;TRANSPORTE INTERMUNICIPAL;TRANSPORTE RURAL;TRANSPORTE AEROPUERTO;TOTAL TRANSPORTE;PERNOCTA;NDIAS;VR DIA;TOTAL VIATICOS;RETENCIONES APLICADAS;ENVIO NUMERO;\n" );
          StringBuilder sql = new StringBuilder();
            sql.append(" Select ");
            sql.append(" cc.id as codigo_de_pago,");
            sql.append(" 'No'  as se_realizo_pago,");
            sql.append(" cc.fecha_pago as fecha_pago,");
            sql.append(" ''    as hora_de_pago,");
            sql.append(" cc.observaciones,");
            sql.append(" 'Viaticos'  as Concepto,");
            sql.append(" mp.nombre   as medio_de_pago,");
            sql.append(" cc.idbanco,");
            sql.append(" cc.numeroCuenta,");
            sql.append(" cc.idtipocuenta,");
            sql.append(" tiden.descripcion as tipo_documento,");
            sql.append(" '' homologacion,");
            sql.append(" e.nrodoc as identificacion,");
            sql.append(" e.apellido1,");
            sql.append(" e.apellido2,");
            sql.append(" e.nombre1,");
            sql.append(" e.nombre2,");
            sql.append(" cc.valor_xpagar as total_pagar,");
            sql.append(" (select car.descripcion from cargos as car where cast(cc.idcargo_puesto_evento as char(2)) = car.codigoCargo) as Cargo_prueba,");
            sql.append(" e.celular,");
            sql.append(" e.telefono,");
            sql.append(" pru.nombre as nombre_prueba,");
            sql.append(" div1.nombreDepartamento as Nodo,");
            sql.append(" mun.nombre as municipio_sitio,");
            sql.append(" div1.codigopuesto as codigo_sitio,");
            sql.append(" (select mre.nombre from municipio as mre where mre.codigomunicipio=e.codigomunicipio) as residencia,");
            sql.append(" dtr.vr_interno   as transporte_interno,");
            sql.append(" dtr.vr_municipal as transporte_intermunicipal,");
            sql.append(" dtr.vr_rural     as transporte_rural,");
            sql.append(" dtr.vr_aereo     as transporte_aeropuerto,");
            sql.append(" case eps.codigoCargo when 27 then dtr.vr_interno * 6 else (dtr.vr_municipal + dtr.vr_rural + dtr.vr_interno + dtr.vr_aereo) end as total_transporte,");
            sql.append(" dtr.pernocta,");
            sql.append(" dtr.ndias,");
            sql.append(" dtr.vr_dia,");
            sql.append(" case eps.codigoCargo when 27 then dtr.vr_interno * 6 else (dtr.ndias * dtr.vr_dia) end as total_viaticos,");
            sql.append(" (cc.valor_rtfte + cc.valor_rtica) as retenciones_aplicadas,");
            sql.append(" (ifnull(nro_envio,0) + 1) as envio_numero");
            sql.append(" from cuentacobro as cc,medio_pago as mp,empleado as e,tipo_iden as tiden,");
            sql.append(" 	  prueba as pru,empleado_x_prueba_x_estado as eps,divipol as div1,municipio as mun,divitrans as dtr");
            sql.append(" where cc.pagado=0 and cc.medio='V'");
            sql.append(" and   cc.idmedio_pago = mp.idmedio_pago");
            sql.append(" and   cc.idempleado = e.idempleado");
            sql.append(" and   e.tipodoc = tiden.codigo");
            sql.append(" and   cc.idprueba = pru.idprueba");
            sql.append(" and   cc.idempleado = eps.idempleado");
            sql.append(" and   cc.idprueba = eps.idprueba");
            sql.append(" and   cast(cc.idcargo_puesto_evento as char(2)) = eps.codigoCargo");
            sql.append(" and   eps.iddivipol = div1.idDivipol");
            sql.append(" and   div1.codigoMunicipio = mun.codigoMunicipio");
            sql.append(" and   cc.id_divitrans = dtr.id");
            if (idEmp > 0) {
             sql.append(" and   cc.idempleado = ? ");
            }
            else{
             sql.append(" and   cc.idempleado > ? ");
            }

          conex.consultar(new Operaciones.OperacionConsulta() {
              @Override
              public void respuestaConsulta(ResultSet res) {
                  try {
                      while (res.next()) {
                            texto.append(res.getString("codigo_de_pago") +";");
                            texto.append(validaNull(res.getString("se_realizo_pago") )+";");
                            texto.append(validaNull(res.getString("fecha_pago") )+";");
                            texto.append(validaNull(res.getString("hora_de_pago") )+";");
                            texto.append(validaNull(res.getString("observaciones") )+";");
                            texto.append(validaNull(res.getString("Concepto") )+";");
                            texto.append(validaNull(res.getString("medio_de_pago") )+";");
                            texto.append(validaNull(res.getString("idbanco") ) +";");
                            texto.append(validaNull(res.getString("numeroCuenta") )+";");
                            texto.append(validaNull(res.getString("idtipocuenta") )+";");
                            texto.append(validaNull(res.getString("tipo_documento") )+";");
                            texto.append(validaNull(res.getString("homologacion") )+";");
                            texto.append(res.getString("identificacion") +";");
                            texto.append(validaNull(res.getString("apellido1") )+";");
                            texto.append(validaNull(res.getString("apellido2") )+";");
                            texto.append(validaNull(res.getString("nombre1") )+";");
                            texto.append(validaNull(res.getString("nombre2") )+";");
                            texto.append(res.getString("total_pagar") +";");
                            texto.append(validaNull(res.getString("Cargo_prueba") )+";");
                            texto.append(validaNull(res.getString("celular") )+";");
                            texto.append(validaNull(res.getString("telefono") )+";");
                            texto.append(validaNull(res.getString("nombre_prueba") )+";");
                            texto.append(validaNull(res.getString("Nodo") )+";");
                            texto.append(validaNull(res.getString("municipio_sitio") )+";");
                            texto.append(validaNull(res.getString("codigo_sitio") )+";");
                            texto.append(validaNull(res.getString("residencia") )+";");
                            texto.append(res.getString("transporte_interno").replace(".00", "") +";");
                            texto.append(res.getString("transporte_intermunicipal").replace(".00", "") +";");
                            texto.append(res.getString("transporte_rural").replace(".00", "") +";");
                            texto.append(res.getString("transporte_aeropuerto").replace(".00", "") +";");
                            texto.append(res.getString("total_transporte").replace(".00", "") +";");
                            texto.append(res.getString("pernocta") +";");
                            texto.append(res.getString("ndias") +";");
                            texto.append(res.getString("vr_dia").replace(".00", "") +";");
                            texto.append(  res.getString("total_viaticos").replace(".0000", "")+";");
                            texto.append(res.getString("retenciones_aplicadas") +";");
                            texto.append(res.getString("envio_numero") +";\n");
                      }
                  } catch (SQLException ex) {
                      Logger.getLogger(GestionPagosDao.class.getName()).log(Level.SEVERE, null, ex);
                  }
              }
          }, sql.toString(),idEmp);
            FileWriter fwriter = new FileWriter(filename);
            fwriter.write(texto.toString());
            fwriter.flush();
            fwriter.close();
      } catch (Exception e) {
          Logger.getLogger(GestionPagosDao.class.getName()).log(Level.SEVERE, null, e);
      }
  }
    public List<PendienteAprobarViatico> AprobarViaticos(int idEmp, int idPrueba, String cargoNombre) {
      final List<PendienteAprobarViatico> lstViaticos = new ArrayList<>();

      try {
          StringBuilder sql = new StringBuilder();
                sql.append(" select 'V' medio,");
                sql.append(" case (select count(*) from tarea_confirmacion tc where tc.tipo='contrato' and tc.id_empleado=e.idempleado and tc.cod_cargo=car.codigocargo and tc.id_prueba=eps.idprueba)");
                sql.append(" when 0 then 'No' else 'Si' end as firmo_contrato,");
                sql.append(" car.descripcion nombrecargo,e.nrodoc,concat(e.nombre1,' ',e.nombre2,' ',e.apellido1,' ',e.apellido2) as nombres,");
                sql.append(" (select m.nombre from municipio m where m.codigoMunicipio=e.codigoMunicipio)   as municipio_residencia,");
                sql.append(" (select s.nombre from municipio s where s.codigoMunicipio=div1.codigoMunicipio)as municipio_sitio,");
                sql.append(" dtr.ndias,");
                sql.append(" case car.codigoCargo when 27 then dtr.vr_interno * 6 else (dtr.vr_municipal + dtr.vr_rural + dtr.vr_interno + dtr.vr_aereo) end as total_transporte,");
                sql.append(" case car.codigoCargo when 27 then dtr.vr_interno * 6 else (dtr.ndias * dtr.vr_dia) end as total_viaticos,");
                sql.append(" case car.codigoCargo when 27 then dtr.vr_interno * 6 else (dtr.vr_municipal + dtr.vr_rural + dtr.vr_interno + dtr.vr_aereo ) + (dtr.ndias * dtr.vr_dia) end total_pagar,");
                sql.append(" (select count(*) from cuentacobro cc where cc.idempleado=eps.idempleado and cc.idprueba=a.idprueba and cc.medio='V') poraceptar,e.idempleado,div1.iddivipol,car.codigoCargo,eps.idprueba ");
                sql.append(" from empleado_x_prueba_x_estado eps,divipol div1,empleado e,cargos car,divitrans dtr,");
                sql.append("        (select distinct div2.iddivipol,div2.codigopuesto,div2.idprueba from empleado ec,divipol div2,usuario_sitio us");
                sql.append("         where ec.idempleado= ? and div2.idprueba= ? ");
                sql.append("         and   ec.nrodoc=us.usuario and us.iddivipol=div2.iddivipol )a");
                sql.append(" where eps.iddivipol  = a.iddivipol");
                sql.append(" and   eps.idempleado = e.idempleado");
                sql.append(" and   eps.idprueba   = a.idprueba");
                sql.append(" and   eps.idestpersona not in (3,6,7,8) ");
                sql.append(" and   eps.iddivipol  = div1.iddivipol");
                sql.append(" and   ((eps.iddivipol=dtr.iddivipol) or (car.codigoCargo=27 and dtr.div_destino=dtr.div_origen))");
                sql.append(" and   (div1.codigoMunicipio<>e.DaneMunicipio or car.codigoCargo=27)");
                sql.append(" and   div1.codigoPuesto=a.codigoPuesto");
                sql.append(" and   dtr.idprueba=a.idprueba");
                sql.append(" and   car.codigocargo = eps.codigocargo");
                sql.append(" and   dtr.div_destino = div1.codigoMunicipio");
                sql.append(" and   car.viaticos    = 1 ");
                sql.append(" and  exists(select 1 from detalle_cargo_x_puesto_x_evento where idprueba=a.idprueba and nrodoc=e.nrodoc)");
                /*sql.append(" and   not exists(select 1 from cuentacobro cc where cc.idempleado=eps.idempleado and   cc.idprueba=a.idprueba and cc.medio='V')");*/
                sql.append(" order by municipio_sitio,nombres");

          conex.consultar(new Operaciones.OperacionConsulta() {
              @Override
              public void respuestaConsulta(ResultSet res) {
                  try {
                      while (res.next()) {
                          PendienteAprobarViatico reg = new PendienteAprobarViatico();
                                    reg.setMedio(res.getString("medio"));
                                    reg.setFirmo_contrato(res.getString("firmo_contrato"));
                                    reg.setNombrecargo(res.getString("nombrecargo"));
                                    reg.setNrodoc(res.getInt("nrodoc"));
                                    reg.setNombres(res.getString("nombres"));
                                    reg.setMunicipio_residencia(res.getString("municipio_residencia"));
                                    reg.setMunicipio_sitio(res.getString("municipio_sitio"));
                                    reg.setNdias(res.getDouble("ndias"));
                                    reg.setTotal_transporte(res.getInt("total_transporte"));
                                    reg.setTotal_viaticos(res.getInt("total_viaticos"));
                                    reg.setTotal_pagar(res.getInt("total_pagar"));
                                    reg.setPoraceptar(res.getInt("poraceptar"));
                                    reg.setIdempleado(res.getInt("idempleado"));
                                    reg.setIddivipol(res.getInt("iddivipol"));
                                    reg.setCodigoCargo(res.getString("codigoCargo"));
                                    reg.setIdprueba(res.getInt("idprueba"));

                          lstViaticos.add(reg);
                      }
                  } catch (SQLException ex) {
                      Logger.getLogger(GestionPagosDao.class.getName()).log(Level.SEVERE, null, ex);
                  }
              }
        }, sql.toString(),idEmp,idPrueba);

      } catch (Exception e) {
          Logger.getLogger(GestionPagosDao.class.getName()).log(Level.SEVERE, null, e);
      }
      return lstViaticos;
  }
    public Boolean InsertPagoViatico(PendienteAprobarViatico reg) {
        Boolean resultado = false;

        StringBuilder sql = new StringBuilder();
                sql.append(" insert into cuentacobro (id,idprueba,idempleado,idcargo_puesto_evento,medio,fecha_generada,valor,valor_xpagar,valor_pagado,idmedio_pago,idbanco,numeroCuenta,idtipoCuenta,pagado,aceptada,nro_envio,observaciones,id_divitrans) ");
                sql.append(" select 0,eps.idprueba,e.idempleado,eps.codigocargo,'V' medio,curdate(), ");
                sql.append(" case car.codigoCargo when 27 then dtr.vr_interno * 6 else ((dtr.vr_municipal + dtr.vr_rural + dtr.vr_interno + dtr.vr_aereo) + (dtr.ndias * dtr.vr_dia)) end as bruto_pagar, ");
                sql.append(" case car.codigoCargo when 27 then dtr.vr_interno * 6 else ((dtr.vr_municipal + dtr.vr_rural + dtr.vr_interno + dtr.vr_aereo) + (dtr.ndias * dtr.vr_dia)) end as neto_pagar, ");
                sql.append(" 0,ifnull(eps.idmedio_pago,6) idmedio_pago,eps.idbanco,eps.numeroCuenta,eps.idtipoCuenta,0,1,0,'' as observaciones,dtr.id ");
                sql.append(" from empleado_x_prueba_x_estado eps,divipol div1,empleado e,cargos car,divitrans dtr ");
                sql.append(" where eps.idempleado = ? ");
                sql.append(" and   eps.idprueba  = ? ");
                sql.append(" and   eps.idprueba  = dtr.idprueba ");
                sql.append(" and   eps.idempleado = e.idempleado ");
                sql.append(" and   eps.iddivipol  = div1.iddivipol ");
                sql.append(" and   eps.iddivipol  = dtr.iddivipol ");
                sql.append(" and   car.codigocargo = eps.codigocargo ");
                sql.append(" and   dtr.div_destino = div1.codigoMunicipio ");
                sql.append(" and   not exists(select 1 from cuentacobro cc where cc.idempleado=eps.idempleado and cc.idprueba=eps.idprueba and cc.medio='V')");
        
                conex.ejecutar(sql.toString() , reg.getIdempleado(),reg.getIdprueba());
                
        return resultado;
    }
    public Boolean actualizar(MisPagos reg) {
        Boolean resultado = false;

        String sql = " update cuentacobro set pagado=1 where id= ? ";

        conex.ejecutar(sql, reg.getIdcuentacobro());

        return resultado;
    }
    public Boolean cancelaprueba(MisPruebas reg) {
        Boolean resultado = false;

        String sql = " update empleado_x_prueba_x_estado set idestpersona=6 where idempleado = ? and idprueba= ? and codigoCargo= ? ";
        resultado = conex.ejecutar(sql, reg.getIdempleado(), reg.getIdprueba(),reg.getCodigocargo());

        return resultado;
    }  
    private String validaNull(String texto)
    {
        if(texto == null)
        {
            return "";
        }else
        {
            return texto;
        }
    }
}
