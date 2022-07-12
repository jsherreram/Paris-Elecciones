/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.ActualizaRecepcionDevolucion;
import co.com.grupoasd.nomina.modelo.Cargo;
import co.com.grupoasd.nomina.modelo.ConsultaOrdenes;
import co.com.grupoasd.nomina.modelo.DespachoDetalleRespuesta;
import co.com.grupoasd.nomina.modelo.DetalleOrden;
import co.com.grupoasd.nomina.modelo.DetallesOrdenGeneral;
import co.com.grupoasd.nomina.modelo.Empleado;
import co.com.grupoasd.nomina.modelo.EmpleadoSesion;
import co.com.grupoasd.nomina.modelo.EstadosGeneral;
import co.com.grupoasd.nomina.modelo.EstadosOrden;
import co.com.grupoasd.nomina.modelo.Evento;
import co.com.grupoasd.nomina.modelo.InformacionControlPdsSuplencia;
import co.com.grupoasd.nomina.modelo.ListadoRelacionOrden;
import co.com.grupoasd.nomina.modelo.Orden;
import co.com.grupoasd.nomina.modelo.Pds;
import co.com.grupoasd.nomina.modelo.PersonalOrdenSuplencia;
import co.com.grupoasd.nomina.modelo.Prueba;
import co.com.grupoasd.nomina.modelo.RespuestaDespacho;
import co.com.grupoasd.nomina.modelo.InformacionDespachosOrden;
import co.com.grupoasd.nomina.modelo.InformacionDetalladaPDS;
import co.com.grupoasd.nomina.modelo.InformacionOrdenPDS;
import co.com.grupoasd.nomina.modelo.RelacionTransporteEnvio;
import co.com.grupoasd.nomina.modelo.RespuestaGenerica;
import co.com.grupoasd.nomina.modelo.Sitio;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CristianAlexander
 */
public class OrdenDao {

    private Operaciones conex = new Operaciones();

    public List<Orden> listarOrdenes() {
        StringBuilder sb = new StringBuilder();
        final ArrayList<Orden> ordenes = new ArrayList<Orden>();
        sb.append("select * from ordensuplencia");
        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {
            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    resultado.beforeFirst();
                    while (resultado.next()) {
                        Orden orden = new Orden();
                        orden.setIdOrden(resultado.getLong(1));
                        orden.setUsuarioOrden(resultado.getLong(2));
                        orden.setIdDivipolStio(resultado.getLong(3));
                        orden.setCodigoEvento(resultado.getLong(4));
                        ordenes.add(orden);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(OrdenDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        return ordenes;
    }

    public Orden getById(String idOrden) {
        StringBuilder sb = new StringBuilder();
        final Orden orden = new Orden();
        sb.append("select a.*,b.codigopuesto, c.descripcion as estado ");
        sb.append("from ordensuplencia a ");
        sb.append("inner join divipol b on(a.idDivipolSitio = b.idDivipol) ");
        sb.append("inner join estadosordenessuplencia c on(a.idEstadoOrden = c.idEstadoOrden) ");
        sb.append("where idOrden =");
        sb.append(idOrden);
        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {
            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    resultado.first();

                    orden.setIdOrden(resultado.getLong("idOrden"));
                    orden.setUsuarioOrden(resultado.getLong("usuarioOrden"));
                    orden.setIdDivipolStio(resultado.getLong("idDivipolSitio"));
                    orden.setCodigoEvento(resultado.getLong("codigoEvento"));
                    orden.setEstado(resultado.getInt("idEstadoOrden"));
                    orden.setCodigoSitio(resultado.getString("codigopuesto"));
                    orden.setEstadoNombre(resultado.getString("estado"));
                    EstadosOrdenDao edao;                    
                    orden.setEstados(new EstadosOrdenDao().buscarPorId(orden.getEstado()));
                    EstadosOrden estados = new EstadosOrdenDao().buscarPorId(orden.getEstado());
                    orden.setEstados(estados);
                } catch (SQLException ex) {
                    Logger.getLogger(OrdenDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        return orden;
    }

    public List<DetalleOrden> obtenerDetalleOrden(String idOrden) {
        StringBuilder sb = new StringBuilder();
        final ArrayList<DetalleOrden> detalles = new ArrayList<DetalleOrden>();
        detalles.clear();
        sb.append("select a.*, b.descripcion from ordensuplenciadetalle a inner join cargos b on ( BINARY a.codigoCargo = BINARY b.codigoCargo) where idOrden = ");
        sb.append(idOrden);
        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {
            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    resultado.beforeFirst();
                    while (resultado.next()) {
                        DetalleOrden detalle = new DetalleOrden();
                        detalle.setIdOrdenDetalle(resultado.getLong("idOrdenDetalle"));
                        detalle.setIdOrden(resultado.getLong("idOrden"));
                        detalle.setCantidad(resultado.getInt("cantidad"));
                        detalle.setCodigoCargo(resultado.getString("codigoCargo"));
                        detalle.setIdpds(resultado.getLong("idpds"));
                        detalle.setCargoNombre(resultado.getString("descripcion"));
                        detalles.add(detalle);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(OrdenDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        return detalles;
    }

    public Orden registrarOrden(final Orden orden) throws Exception {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Inicio registrarOrden");
        final Orden ordenRetorna = new Orden();
        final RespuestaUpdate respuesta = new RespuestaUpdate();
        StringBuilder sb = new StringBuilder();
        //sb.append("insert into ordensuplencia (usuarioOrden,iDdivipolSitio,codigoEvento,idEstadoOrden) values (");
        sb.append("call retornoSimple(");
        sb.append(orden.getUsuarioOrden());
        sb.append(" , ");
        sb.append(orden.getIdDivipolStio());
        sb.append(" , ");
        sb.append(orden.getCodigoEvento());
        sb.append(" , 1 ");//TODO:Cambiar este machetazo buscando en la tabla el codigo "ING" de ingresada de la tabla de estados...
        sb.append(");");
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, sb.toString());
        try {
            conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet resultado) {
                    
                    try {
                        if (resultado.first()) {
                            ordenRetorna.setIdOrden(resultado.getLong(1));
                            ordenRetorna.setUsuarioOrden(orden.getUsuarioOrden());
                            ordenRetorna.setIdDivipolStio(orden.getIdDivipolStio());
                            ordenRetorna.setCodigoEvento(orden.getCodigoEvento());
                            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Se asigna la orden desde el procedimiento");
                        } else {
                            ordenRetorna.setIdOrden(-1L);
                            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "!!! Se le asigna -1 al idOdeen");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(OrdenDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        } catch (Exception e) {
            Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Fin registrarOrden");
        return ordenRetorna;
    }

    public List<Orden> ordenesxPds(Long idDivipolPds) {
        StringBuilder sb = new StringBuilder();
        final ArrayList<Orden> respuesta = new ArrayList<Orden>();
        final long pds = idDivipolPds;
        sb.append("select distinct a.idOrden, ");
        sb.append("d.descripcion, ");
        sb.append("e.codigopuesto, ");
        sb.append("e.nombrepuesto ");
        sb.append("from ordensuplencia a ");
        sb.append("inner join ordensuplenciadetalle b on (a.idOrden = b.idOrden) ");
        sb.append("inner join suplenciaxdetalle c on (b.idOrdenDetalle = c.idOrdenDetalle and idDivipolPds = ");
        sb.append(idDivipolPds);
        sb.append(") ");
        sb.append("inner join estadosordenessuplencia d on (a.idEstadoOrden = d.idEstadoOrden) ");
        sb.append("inner join divipol e on (e.idDivipol = a.idDivipolSitio) ");
        sb.append("union ");
        sb.append("select distinct a.idOrden,  ");
        sb.append("d.descripcion,  ");
        sb.append("e.codigopuesto,  ");
        sb.append("e.nombrepuesto ");
        sb.append("from ordensuplencia a  ");
        sb.append("inner join ordensuplenciadetalle b on (a.idOrden = b.idOrden)  ");
        sb.append("inner join estadosordenessuplencia d on (a.idEstadoOrden = d.idEstadoOrden) ");
        sb.append("inner join divipol e on (e.idDivipol = a.idDivipolSitio) ");
        sb.append("where a.idEstadoOrden = (select z.idEstadoOrden from estadosordenessuplencia z where z.codigoEstado = 'VAC') ");
        sb.append("and a.idDivipolSitio in (select idDivipolSitio from relacion_pds z where z.idDivipolPds = ");
        sb.append(idDivipolPds);
        sb.append(" and z.prioridad = 1) ");

        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {
            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    resultado.beforeFirst();
                    while (resultado.next()) {
                        Orden resp = new Orden();
                        resp.setIdOrden(resultado.getLong("idOrden"));
                        resp.setEstadoNombre(resultado.getString("descripcion"));
                        resp.setCodigoSitio(resultado.getString("codigopuesto"));
                        resp.setNombrePuesto(resultado.getString("nombrepuesto"));
                        //HashMap estadosDespachos = new OrdenDao().determinarEstadoOrden(resp.getIdOrden());
                        HashMap estadosDespachos = new OrdenDao().determinarEstadoOrdenxPds(resp.getIdOrden(), pds);
                        resp.setDespachados((Integer) estadosDespachos.get("DES"));
                        resp.setTotalAsignados((Integer) estadosDespachos.get("TOTAL"));
                        resp.setDevueltos((Integer) estadosDespachos.get("DEV"));
                        resp.setRecibidos((Integer) estadosDespachos.get("REC"));
                        resp.setPreasignados((Integer) estadosDespachos.get("PRE"));
                        resp.setSolicitados((Integer) estadosDespachos.get("SOL"));
                        respuesta.add(resp);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(OrdenDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //determinarEstadoOrden
        return respuesta;
    }

    public Boolean registraDetalleOrden(List<DetalleOrden> detalles) throws Exception {
        Boolean ordenCompleta = true;
        Boolean ordenVacia = false;
        Long idOrden = ((DetalleOrden) detalles.get(0)).getIdOrden();
        ArrayList<DetalleOrden> detallesNew = new ArrayList<DetalleOrden>();
        for (DetalleOrden detalle : detalles) {
            StringBuilder sb = new StringBuilder();
            Orden orden = new OrdenDao().getById(detalle.getIdOrden().toString());
            Evento evento = new EventoDao().GetById(Integer.parseInt(orden.getCodigoEvento().toString()));
            Prueba prueba = evento.getPrueba();
            sb.append("insert into ordensuplenciadetalle (idOrden,cantidad,codigoCargo,idpds) values (");
            sb.append(detalle.getIdOrden());
            sb.append(" , ");
            sb.append(detalle.getCantidad());
            sb.append(" , '");
            sb.append(detalle.getCodigoCargo());
            sb.append("' , ");
            sb.append(detalle.getIdpds());
            sb.append(" );");
            if (!conex.ejecutar(sb.toString())) {
                return false;
            } else {

            }
            //coje el primer detalle, llama de nuevo a los detalles para traerlos con el id
        }
        detallesNew = (ArrayList<DetalleOrden>) new OrdenDao().obtenerDetalleOrden(idOrden.toString());
        Orden orden = new OrdenDao().getById(idOrden.toString());

        Evento evento = new EventoDao().GetById(orden.getCodigoEvento().intValue());

        Prueba prueba = evento.getPrueba();
        for (DetalleOrden detalleNew : detallesNew) {
            ArrayList<Pds> pds = (ArrayList<Pds>) new PdsDao().listarpdsxSitio(orden.getIdDivipolStio().toString(), prueba.getIdprueba());
            //ArrayList<Pds> pds = (ArrayList<Pds>) new PdsDao().listarpdsxSitio(orden.getIdDivipolStio().toString(), prueba.getIdprueba());
            ArrayList<PersonalOrdenSuplencia> personal = (ArrayList<PersonalOrdenSuplencia>) new OrdenDao().personalSuplencia(orden.getIdDivipolStio(), orden.getCodigoEvento(), Long.valueOf(detalleNew.getCodigoCargo()), detalleNew.getCantidad());
            //si hay personal disponible...
            if (personal.size() > 0) {
                //se separan los que hay disponibles
                new OrdenDao().despachaDetalle(personal, detalleNew.getIdOrdenDetalle(), "", "", "");
                //mira si cumplió lo requerido, si no hay que marcarlo como preasignado incompleto o como preasignado
                /*if (personal.size() < detalleNew.getCantidad()) {
                 ordenCompleta = false;
                 }*/

            } else {
                ordenVacia = true;
            }

            //una vez marcado como orden incompleta...
            /*EstadosOrden estadoOrden = new EstadosOrden();
             if (!ordenCompleta) {
             estadoOrden = new EstadosOrdenDao().buscarPorCodigo("INC");
             } else {
             estadoOrden = new EstadosOrdenDao().buscarPorCodigo("COM");
             }
             if (ordenVacia) {
             estadoOrden = new EstadosOrdenDao().buscarPorCodigo("VAC");
             }
             new OrdenDao().cambiaEstadoOrden(Integer.valueOf(estadoOrden.getIdEstadoOrden().toString()), orden.getIdOrden());*/
            EstadosOrden estadoOrden = new EstadosOrden();
            estadoOrden = new EstadosOrdenDao().buscarPorCodigo(new OrdenDao().determinarEstadoOrdenAsignacion(idOrden));
            new OrdenDao().cambiaEstadoOrden(Integer.valueOf(estadoOrden.getIdEstadoOrden().toString()), idOrden);

        }

        return true;
    }

    public String determinarEstadoOrdenAsignacion(Long idOrden) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb1 = new StringBuilder();
        String respuesta = "";
        final RespuestaUpdate resp = new RespuestaUpdate();

        sb.append("select count(0) ");
        sb.append("from suplenciaxdetalle ");
        sb.append("where idOrdenDetalle in (select idOrdenDetalle from ordensuplenciadetalle where idOrden = ");
        sb.append(idOrden);
        sb.append(" )");

        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {

            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    resultado.first();
                    resp.setCuantos(resultado.getInt(1));
                } catch (SQLException ex) {
                    Logger.getLogger(OrdenDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        sb1.append("select sum(cantidad) ");
        sb1.append("from ordensuplenciadetalle ");
        sb1.append("where idOrden =  ");
        sb1.append(idOrden);

        conex.consultar(sb1.toString(), new Operaciones.OperacionConsulta() {

            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    resultado.first();
                    resp.setTotal(resultado.getInt(1));
                } catch (SQLException ex) {
                    Logger.getLogger(OrdenDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        if (resp.getCuantos() < resp.getTotal()) {
            respuesta = "INC";
        }

        if (resp.getCuantos() == 0) {
            respuesta = "VAC";
        }

        if (resp.getCuantos() == resp.getTotal()) {
            respuesta = "COM";
        }

        return respuesta;

    }

    public Orden traerUltimaOrden() throws Exception {
        final Orden orden = new Orden();
        StringBuilder sb = new StringBuilder();
        sb.append("select * from ordensuplencia where idOrden = (select max(idOrden) from ordensuplencia)");
        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {
            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    resultado.first();
                    orden.setIdOrden(resultado.getLong(1));
                    orden.setUsuarioOrden(resultado.getLong(2));
                    orden.setIdDivipolStio(resultado.getLong(3));
                    orden.setCodigoEvento(resultado.getLong(4));
                } catch (SQLException ex) {
                    Logger.getLogger(OrdenDao.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        return orden;
    }

    public List<ConsultaOrdenes> consultaOrdenes(List<Long> eventos) throws Exception {
        StringBuilder eventosIn = new StringBuilder();
        StringBuilder query = new StringBuilder();
        final ArrayList<ConsultaOrdenes> listaOrdenes = new ArrayList<ConsultaOrdenes>();
        Boolean coma = false;
        for (Long evento : eventos) {
            if (coma) {
                eventosIn.append(",");
            }
            eventosIn.append(evento);
            coma = true;
        }
        query.append("select ");
        query.append("idOrden,");
        query.append("b.descripcion, ");
        query.append("codigoPuesto, ");
        query.append("nombrePuesto, ");
        query.append("nombreMunicipio, ");
        query.append("nombreDepartamento, ");
        query.append("(select x.nombrePuesto ");
        query.append("from relacion_pds z ");
        query.append("inner join divipol x on (z.idDivipolPds = x.iddivipol) ");
        query.append("where z.idDivipolSitio = a.idDivipolSitio ");
        query.append("and prioridad = 1) as pds ");
        query.append("from ordensuplencia a ");
        query.append("inner join estadosordenessuplencia b on (a.idEstadoOrden = b.idEstadoOrden) ");
        query.append("inner join divipol c on (c.idDivipol = a.idDivipolSitio) ");
        query.append("where a.codigoEvento in (");
        query.append(eventosIn.toString());
        query.append(");");

        conex.consultar(query.toString(), new Operaciones.OperacionConsulta() {
            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    resultado.beforeFirst();
                    while (resultado.next()) {
                        ConsultaOrdenes resultadoConsulta = new ConsultaOrdenes();
                        resultadoConsulta.setIdOrden(resultado.getLong(1));
                        resultadoConsulta.setDescripcion(resultado.getString(2));
                        resultadoConsulta.setCodigoPuesto(resultado.getString(3));
                        resultadoConsulta.setNombrePuesto(resultado.getString(4));
                        resultadoConsulta.setNombreMunicipio(resultado.getString(5));
                        resultadoConsulta.setNombreDepartamento(resultado.getString(6));
                        resultadoConsulta.setPds(resultado.getString(7));
                        listaOrdenes.add(resultadoConsulta);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(OrdenDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        return listaOrdenes;
    }

    public PersonalOrdenSuplencia consultarDisponibilidadPersonal(Long idDivipol, Long codigoEvento, Long codigoCargo, Long nrodoc, Integer cambioSuplencia) {
        final PersonalOrdenSuplencia persona = new PersonalOrdenSuplencia();
        StringBuilder sb = new StringBuilder();
        sb.append("select distinct a.id,");
        sb.append("a.nrodoc, concat(apellido1,' ' ,ifnull(apellido2,' ')) as apellidos, concat(nombre1 , ' ' , ifnull(nombre2,' ')) as nombres ");
        sb.append("from detalle_cargo_x_puesto_x_evento a ");
        sb.append("inner join empleado b on (a.nrodoc = b.nrodoc and a.nrodoc > 0) ");
        sb.append("where idDivipol = ");
        sb.append(idDivipol);
        sb.append(" and codigoEvento = ");
        sb.append(codigoEvento);
        sb.append(" and codigoCargo =  ");
        sb.append(codigoCargo);
        sb.append(" and a.nrodoc = ");
        sb.append(nrodoc);
        sb.append(" and (select count(*) from suplenciaxdetalle z where z.nrodoc = a.nrodoc and idEstadoAtencionOrden in (1,2,3)) < 1 ");
        sb.append(" and (select count(0) from asistencia x where x.codigoevento = a.codigoevento  and x.iddivipol = a.idDivipol  and x.idEmpleado = b.idEmpleado) > 0 ");

        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {
            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    if (resultado.first()) {
                        persona.setNrodoc(resultado.getLong("nrodoc"));
                        persona.setApellidos(resultado.getString("apellidos"));
                        persona.setNombres(resultado.getString("nombres"));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(OrdenDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        return persona;
    }

    public List<PersonalOrdenSuplencia> personalSuplencia(Long idDivipol, Long codigoEvento, Long codigoCargo, long cantidad) throws Exception {
        StringBuilder sb = new StringBuilder();
        Integer i = 0;
        final ArrayList<PersonalOrdenSuplencia> personal = new ArrayList<PersonalOrdenSuplencia>();
        ArrayList<Pds> pdss = new ArrayList<Pds>();
        Evento evento = new Evento();
        evento = new EventoDao().GetById(Integer.valueOf(codigoEvento.toString()));
        Prueba prueba = evento.getPrueba();
        pdss = (ArrayList<Pds>) new PdsDao().listarpdsxSitio(idDivipol.toString(), prueba.getIdprueba());
        sb.append("select * from (");
        for (Pds pds : pdss) {
            if (i > 0) {
                sb.append(" union ");
            }
            sb.append("select distinct a.id,a.nrodoc, ");
            sb.append(pds.getPrioridad());
            sb.append(" as prioridad, ");
            sb.append(pds.getIdDivipolPds());
            sb.append(" as codigoPds, fecharegistro ");
            sb.append("from detalle_cargo_x_puesto_x_evento a ");
            sb.append("inner join empleado b on (a.nrodoc = b.nrodoc and a.nrodoc > 0) ");
            sb.append("inner join asistencia c on (c.codigoevento = a.codigoevento  and c.iddivipol = a.idDivipol  and c.idEmpleado = b.idEmpleado) ");
            sb.append("where a.idDivipol = ");
            sb.append(pds.getIdDivipolPds());
            sb.append(" and a.codigoEvento = ");
            sb.append(codigoEvento);
            sb.append(" and codigoCargo = ");
            sb.append(codigoCargo);
            sb.append(" and (select count(*) from suplenciaxdetalle z where z.nrodoc = a.nrodoc and idEstadoAtencionOrden in (1,2,3,4)) < 1  ");
            //sb.append(" and (select count(0) from asistencia x where x.codigoevento = a.codigoevento  and x.iddivipol = a.idDivipol  and x.idEmpleado = b.idEmpleado) > 0 ");
            i = i + 1;
        }
        sb.append(" limit ");
        sb.append(cantidad);
        sb.append(") a  order by prioridad,fecharegistro");

        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {
            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    resultado.beforeFirst();
                    while (resultado.next()) {
                        PersonalOrdenSuplencia persona = new PersonalOrdenSuplencia();
                        persona.setIdDetalle(resultado.getLong(1));
                        persona.setNrodoc(resultado.getLong(2));
                        persona.setPrioridad(resultado.getInt(3));
                        persona.setCodigoPds(resultado.getInt(4));
                        personal.add(persona);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(OrdenDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        return personal;
    }

    public HashMap determinarEstadoOrden(Long idOrden) {
        final HashMap respuesta = new HashMap();
        StringBuilder sb = new StringBuilder();
        sb.append("select count(0) , 'PRE' ");
        sb.append("from suplenciaxdetalle a ");
        sb.append("inner join estadosatencionordenessuplencia b on (b.idEstadoAtencionOrden = a.idEstadoAtencionOrden and b.codigoEstadoAtencion in ('PRE')) ");
        sb.append("where a.idOrdenDetalle in (select z.idOrdenDetalle from ordensuplenciadetalle z where z.idOrden = ");
        sb.append(idOrden);
        sb.append(") ");
        sb.append("UNION ");
        sb.append("select count(0) , 'DES' ");
        sb.append("from suplenciaxdetalle a ");
        sb.append("inner join estadosatencionordenessuplencia b on (b.idEstadoAtencionOrden = a.idEstadoAtencionOrden and b.codigoEstadoAtencion in ('DES')) ");
        sb.append("where a.idOrdenDetalle in (select z.idOrdenDetalle from ordensuplenciadetalle z where z.idOrden = ");
        sb.append(idOrden);
        sb.append(") ");
        sb.append("UNION ");
        sb.append("select count(0) , 'DEV' ");
        sb.append("from suplenciaxdetalle a ");
        sb.append("inner join estadosatencionordenessuplencia b on (b.idEstadoAtencionOrden = a.idEstadoAtencionOrden and b.codigoEstadoAtencion in ('DEV')) ");
        sb.append("where a.idOrdenDetalle in (select z.idOrdenDetalle from ordensuplenciadetalle z where z.idOrden = ");
        sb.append(idOrden);
        sb.append(") ");
        sb.append("UNION ");
        sb.append("select count(0) , 'REC' ");
        sb.append("from suplenciaxdetalle a ");
        sb.append("inner join estadosatencionordenessuplencia b on (b.idEstadoAtencionOrden = a.idEstadoAtencionOrden and b.codigoEstadoAtencion in ('REC')) ");
        sb.append("where a.idOrdenDetalle in (select z.idOrdenDetalle from ordensuplenciadetalle z where z.idOrden = ");
        sb.append(idOrden);
        sb.append(") ");
        sb.append("UNION ");
        sb.append("select count(0) , 'DRE' ");
        sb.append("from suplenciaxdetalle a ");
        sb.append("inner join estadosatencionordenessuplencia b on (b.idEstadoAtencionOrden = a.idEstadoAtencionOrden and b.codigoEstadoAtencion in ('REC')) ");
        sb.append("where a.idOrdenDetalle in (select z.idOrdenDetalle from ordensuplenciadetalle z where z.idOrden = ");
        sb.append(idOrden);
        sb.append(") ");
        sb.append("UNION ");
        sb.append("select count(0) , 'SUM' ");
        sb.append("FROM suplenciaxdetalle a ");
        sb.append("inner join estadosatencionordenessuplencia b on (b.idEstadoAtencionOrden = a.idEstadoAtencionOrden and b.codigoEstadoAtencion in ('PRE','DES','DEV','REC','DRE')) ");
        sb.append("where a.idOrdenDetalle in (select z.idOrdenDetalle from ordensuplenciadetalle z where z.idOrden = ");
        sb.append(idOrden);
        sb.append(") ");
        sb.append("UNION ");
        sb.append("select count(0), 'TOTAL' ");
        sb.append("from suplenciaxdetalle a ");
        sb.append("where a.idOrdenDetalle in (select z.idOrdenDetalle from ordensuplenciadetalle z where z.idOrden = ");
        sb.append(idOrden);
        sb.append(") ");
        sb.append("UNION ");
        sb.append("select sum(cantidad), 'SOL' ");
        sb.append("from ordensuplenciadetalle ");
        sb.append("where idOrden = ");
        sb.append(idOrden);
        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {

            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    resultado.beforeFirst();
                    while (resultado.next()) {
                        respuesta.put(resultado.getString(2), resultado.getInt(1));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(OrdenDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        return respuesta;
    }

    public HashMap determinarEstadoOrdenxPds(Long idOrden, Long idPds) {
        final HashMap respuesta = new HashMap();
        StringBuilder sb = new StringBuilder();
        sb.append("select count(0) , 'PRE' ");
        sb.append("from suplenciaxdetalle a ");
        sb.append("inner join estadosatencionordenessuplencia b on (b.idEstadoAtencionOrden = a.idEstadoAtencionOrden and b.codigoEstadoAtencion in ('PRE')) ");
        sb.append("where a.idOrdenDetalle in (select z.idOrdenDetalle from ordensuplenciadetalle z where z.idOrden = ");
        sb.append(idOrden);
        sb.append(") ");
        sb.append(" and idDivipolPds = ");
        sb.append(idPds);
        sb.append(" UNION ");
        sb.append("select count(0) , 'DES' ");
        sb.append("from suplenciaxdetalle a ");
        sb.append("inner join estadosatencionordenessuplencia b on (b.idEstadoAtencionOrden = a.idEstadoAtencionOrden and b.codigoEstadoAtencion in ('DES')) ");
        sb.append("where a.idOrdenDetalle in (select z.idOrdenDetalle from ordensuplenciadetalle z where z.idOrden = ");
        sb.append(idOrden);
        sb.append(") ");
        sb.append(" and idDivipolPds = ");
        sb.append(idPds);
        sb.append(" UNION ");
        sb.append("select count(0) , 'DEV' ");
        sb.append("from suplenciaxdetalle a ");
        sb.append("inner join estadosatencionordenessuplencia b on (b.idEstadoAtencionOrden = a.idEstadoAtencionOrden and b.codigoEstadoAtencion in ('DEV')) ");
        sb.append("where a.idOrdenDetalle in (select z.idOrdenDetalle from ordensuplenciadetalle z where z.idOrden = ");
        sb.append(idOrden);
        sb.append(") ");
        sb.append(" and idDivipolPds = ");
        sb.append(idPds);
        sb.append(" UNION ");
        sb.append("select count(0) , 'REC' ");
        sb.append("from suplenciaxdetalle a ");
        sb.append("inner join estadosatencionordenessuplencia b on (b.idEstadoAtencionOrden = a.idEstadoAtencionOrden and b.codigoEstadoAtencion in ('REC')) ");
        sb.append("where a.idOrdenDetalle in (select z.idOrdenDetalle from ordensuplenciadetalle z where z.idOrden = ");
        sb.append(idOrden);
        sb.append(") ");
        sb.append(" and idDivipolPds = ");
        sb.append(idPds);
        sb.append(" UNION ");
        sb.append("select count(0) , 'DRE' ");
        sb.append("from suplenciaxdetalle a ");
        sb.append("inner join estadosatencionordenessuplencia b on (b.idEstadoAtencionOrden = a.idEstadoAtencionOrden and b.codigoEstadoAtencion in ('REC')) ");
        sb.append("where a.idOrdenDetalle in (select z.idOrdenDetalle from ordensuplenciadetalle z where z.idOrden = ");
        sb.append(idOrden);
        sb.append(") ");
        sb.append(" and idDivipolPds = ");
        sb.append(idPds);
        sb.append(" UNION ");
        sb.append("select count(0) , 'SUM' ");
        sb.append("FROM suplenciaxdetalle a ");
        sb.append("inner join estadosatencionordenessuplencia b on (b.idEstadoAtencionOrden = a.idEstadoAtencionOrden and b.codigoEstadoAtencion in ('PRE','DES','DEV','REC','DRE')) ");
        sb.append("where a.idOrdenDetalle in (select z.idOrdenDetalle from ordensuplenciadetalle z where z.idOrden = ");
        sb.append(idOrden);
        sb.append(") ");
        sb.append(" and idDivipolPds = ");
        sb.append(idPds);
        sb.append(" UNION ");
        sb.append("select count(0), 'TOTAL' ");
        sb.append("from suplenciaxdetalle a ");
        sb.append("where a.idOrdenDetalle in (select z.idOrdenDetalle from ordensuplenciadetalle z where z.idOrden = ");
        sb.append(idOrden);
        sb.append(") ");
        sb.append(" and idDivipolPds = ");
        sb.append(idPds);
        sb.append(" UNION ");
        sb.append("select sum(cantidad), 'SOL' ");
        sb.append("from ordensuplenciadetalle ");
        sb.append("where idOrden = ");
        sb.append(idOrden);

        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {

            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    resultado.beforeFirst();
                    while (resultado.next()) {
                        respuesta.put(resultado.getString(2), resultado.getInt(1));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(OrdenDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        return respuesta;
    }

    public DespachoDetalleRespuesta getDetalleDespacho(Long id) {
        final DespachoDetalleRespuesta detalleDespacho = new DespachoDetalleRespuesta();
        StringBuilder sb = new StringBuilder();
        sb.append("select idsuplenciadetalle, nrodoc,idOrdenDetalle,idDetalle,idEstadoAtencionOrden, idDivipolPds ");
        sb.append("from suplenciaxdetalle ");
        sb.append("where idsuplenciadetalle = ");
        sb.append(id);

        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {
            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    resultado.first();
                    detalleDespacho.setIdsuplenciadetalle(resultado.getLong(1));
                    detalleDespacho.setNrodoc(resultado.getLong(2));
                    detalleDespacho.setIdOrdenDetalle(resultado.getLong(3));
                    detalleDespacho.setEstadoPersona(resultado.getInt(4));
                    detalleDespacho.setEstadoDespacho(new EstadosOrdenDao().buscarPorIdEstadoAsignacion(String.valueOf(resultado.getInt(5))));
                    detalleDespacho.setIdDivipolPds(resultado.getLong(6));
                } catch (SQLException ex) {
                    Logger.getLogger(OrdenDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        return detalleDespacho;
    }

    public Boolean actualizaEstadoDetalleDespacho(List<DespachoDetalleRespuesta> respuesta) throws Exception {
        HashMap estadosOrden = new HashMap();

        for (DespachoDetalleRespuesta resp : respuesta) {
            if (resp.getTipoTransporte() == null) {
                resp.setTipoTransporte(0);
            }
            if (resp.getPlacaMovil() == null) {
                resp.setPlacaMovil("");
            }
            if (resp.getNombreConductor() == null) {
                resp.setNombreConductor("");
            }
            if (resp.getObservaciones() == null) {
                resp.setObservaciones("");
            }

            StringBuilder sb = new StringBuilder();
            sb.append("update suplenciaxdetalle set idEstadoAtencionOrden = ");
            sb.append(resp.getEstadoPersona());
            sb.append(" , observaciones2 = '");
            sb.append(resp.getObservaciones());
            sb.append("' , fecharesolucion3 = now(), tipoTransporte = ");
            sb.append(resp.getTipoTransporte());
            sb.append(" , placaMovil = '");
            sb.append(resp.getPlacaMovil());
            sb.append("' , nombreConductor = '");
            sb.append(resp.getNombreConductor());
            sb.append("' where idsuplenciadetalle = ");
            sb.append(resp.getIdsuplenciadetalle());

            if (!conex.ejecutar(sb.toString())) {
                return false;
            }
        }
        if (respuesta.size() > 0) {
            DespachoDetalleRespuesta despacho = new OrdenDao().getDetalleDespacho(respuesta.get(0).getIdsuplenciadetalle());
            DetalleOrden detalle = new OrdenDao().obtenerDetalleOrden(despacho.getIdOrdenDetalle());
            estadosOrden = new OrdenDao().determinarEstadoOrden(detalle.getIdOrden());
            Integer suma = (Integer) estadosOrden.get("SUM");
            Integer total = (Integer) estadosOrden.get("SOL");
            Integer preasignadas = (Integer) estadosOrden.get("PRE");
            //Determina si ya toda la orden fue despachada, básicamente revisa que el total de personal solicitado
            //este en despachado, devuelto o recibido            
            if ((suma == total) && preasignadas < 1) {
                EstadosOrden estadoDespachada = new EstadosOrdenDao().buscarPorCodigo("DES");
                new OrdenDao().cambiaEstadoOrden(estadoDespachada.getIdEstadoOrden().intValue(), detalle.getIdOrden());
            }
        }
        return true;
    }
    
    // Metodo actual para despacho
     public Boolean actualizaDetalleDespacho(List<DespachoDetalleRespuesta> respuesta) throws Exception {
        HashMap estadosOrden = new HashMap();

        for (DespachoDetalleRespuesta resp : respuesta) {
            if (resp.getTipoTransporte() == null) {
                resp.setTipoTransporte(0);
            }
            if (resp.getPlacaMovil() == null) {
                resp.setPlacaMovil("");
            }
            if (resp.getNombreConductor() == null) {
                resp.setNombreConductor("");
            }
            if (resp.getObservaciones() == null) {
                resp.setObservaciones("");
            }

            StringBuilder sb = new StringBuilder();
            sb.append("update suplenciaxdetalle set idEstadoAtencionOrden = ");
            sb.append(resp.getEstadoPersona());
            sb.append(" , observaciones2 = '");
            sb.append(resp.getObservaciones());
            sb.append("' , fecharesolucion2 = now(), tipoTransporte = ");
            sb.append(resp.getTipoTransporte());
            sb.append(" , placaMovil = '");
            sb.append(resp.getPlacaMovil());
            sb.append("' , nombreConductor = '");
            sb.append(resp.getNombreConductor());
            sb.append("' where idsuplenciadetalle = ");
            sb.append(resp.getIdsuplenciadetalle());

            if (!conex.ejecutar(sb.toString())) {
                return false;
            }
        }
        if (respuesta.size() > 0) {
            DespachoDetalleRespuesta despacho = new OrdenDao().getDetalleDespacho(respuesta.get(0).getIdsuplenciadetalle());
            DetalleOrden detalle = new OrdenDao().obtenerDetalleOrden(despacho.getIdOrdenDetalle());
            estadosOrden = new OrdenDao().determinarEstadoOrden(detalle.getIdOrden());
            Integer suma = (Integer) estadosOrden.get("SUM");
            Integer total = (Integer) estadosOrden.get("SOL");
            Integer preasignadas = (Integer) estadosOrden.get("PRE");
            //Determina si ya toda la orden fue despachada, básicamente revisa que el total de personal solicitado
            //este en despachado, devuelto o recibido            
            if ((suma == total) && preasignadas < 1) {
                EstadosOrden estadoDespachada = new EstadosOrdenDao().buscarPorCodigo("DES");
                new OrdenDao().cambiaEstadoOrden(estadoDespachada.getIdEstadoOrden().intValue(), detalle.getIdOrden());
            }
        }
        return true;
    }

    public Boolean actualizaNrodocEstadoDetalleDespacho(long nrodoc, long id) throws Exception {

        StringBuilder sb = new StringBuilder();
        sb.append("update suplenciaxdetalle set nrodoc = ");
        sb.append(nrodoc);
        sb.append(" where idsuplenciadetalle = ");
        sb.append(id);

        if (!conex.ejecutar(sb.toString())) {
            return false;
        }

        return true;
    }

    public Boolean actualizaEstadoDetalleDespachoI(List<DespachoDetalleRespuesta> respuesta) throws Exception {

        for (DespachoDetalleRespuesta resp : respuesta) {
            StringBuilder sb = new StringBuilder();
            sb.append("update suplenciaxdetalle set idEstadoAtencionOrden = ");
            sb.append(resp.getEstadoPersona());
            sb.append(" , observaciones = '");
            sb.append(resp.getObservaciones());
            sb.append("' , fecharesolucion = now() ");
            sb.append("where idsuplenciadetalle = ");
            sb.append(resp.getIdsuplenciadetalle());
            if (!conex.ejecutar(sb.toString())) {
                return false;
            }

            EstadosOrden estadoOrden = new EstadosOrdenDao().buscarPorId(resp.getEstadoOrden());
            if (estadoOrden.getCodigoEstado().equals("COM") || estadoOrden.getCodigoEstado().equals("NRP")) {
                String sql = "update ordensuplencia set idEstadoOrden = " + resp.getEstadoOrden() + " where idOrden = " + resp.getIdOrden();
                if (!conex.ejecutar(sql)) {
                    return false;
                }
            }
        }
        return true;
    }

    public Boolean actualizaEstadoImpresionDespacho(Long idOrdenDetalle) throws Exception {
        StringBuilder sb = new StringBuilder();

        sb.append("update ordensuplencia ");
        sb.append("set envioMail = 2 ");
        sb.append(" where idOrden = ");
        sb.append(((DetalleOrden) new OrdenDao().obtenerDetalleOrden(idOrdenDetalle)).getIdOrden());

        conex.ejecutar(sb.toString());
        return true;
    }

    public DetalleOrden obtenerDetalleOrden(Long idOrden) throws Exception {
        final DetalleOrden detalle = new DetalleOrden();
        StringBuilder sb = new StringBuilder();
        sb.append("select idOrden from ordensuplenciadetalle where idOrdenDetalle = ");
        sb.append(idOrden);
        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {
            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    resultado.first();
                    detalle.setIdOrden(resultado.getLong(1));
                } catch (SQLException ex) {
                    Logger.getLogger(OrdenDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        return detalle;
    }

    public Boolean despachaDetalle(List<PersonalOrdenSuplencia> personal, Long idOrdenDetalle, String tipoDespacho, String placa, String conductor) throws Exception {
        Boolean respuesta = true;
        for (PersonalOrdenSuplencia persona : personal) {
            StringBuilder sb = new StringBuilder();
            sb.append("insert into suplenciaxdetalle (nrodoc,idOrdenDetalle,idDetalle,idEstadoAtencionOrden,fecharesolucion,idDivipolPds ) values (");
            sb.append(persona.getNrodoc());
            sb.append(" , ");
            sb.append(idOrdenDetalle);
            sb.append(" , ");
            sb.append(persona.getIdDetalle());
            sb.append(" , 1, now(),");
            sb.append(persona.getCodigoPds());
            sb.append(" );");
            if (!conex.ejecutar(sb.toString())) {
                return false;
            }
        }
        //  new OrdenDao().actualizaEstadoImpresionDespacho(idOrdenDetalle);
        return true;
    }

    public Boolean cambiaEstadoOrden(Integer estado, Long idOrden) {
        StringBuilder sb = new StringBuilder();
        sb.append("update ordensuplencia set idEstadoOrden = ");
        sb.append(estado);
        sb.append(" where idOrden = ");
        sb.append(idOrden);
        return conex.ejecutar(sb.toString());
    }

    public List<ListadoRelacionOrden> listadoRelacionOrden(Long idOrden, Integer tipo) {
        StringBuilder sb = new StringBuilder();
        final ArrayList<ListadoRelacionOrden> relacion = new ArrayList<ListadoRelacionOrden>();
        sb.append("select idsuplenciadetalle, ");
        sb.append("d.descripcion, ");
        sb.append("a.nrodoc, ");
        sb.append("concat(ifnull(b.apellido1,''),' ',ifnull(b.apellido2,'')) as apellidos, ");
        sb.append("concat(ifnull(b.nombre1,''),' ',ifnull(b.nombre2,'')) as nombres, ");
        sb.append(" c.codigoCargo, ");
        sb.append(" idDivipolPds ");
        sb.append("from suplenciaxdetalle a ");
        sb.append("inner join empleado b on a.nrodoc = b.nrodoc ");
        sb.append("inner join ordensuplenciadetalle c on a.idOrdenDetalle = c.idOrdenDetalle and c.idOrden = ");
        sb.append(idOrden);
        sb.append(" inner join cargos d on BINARY c.codigoCargo = BINARY d.codigoCargo ");
        sb.append(" where idEstadoAtencionOrden = ");
        sb.append(tipo);
        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {
            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    resultado.beforeFirst();
                    while (resultado.next()) {
                        ListadoRelacionOrden item = new ListadoRelacionOrden();
                        item.setIdsuplenciadetalle(resultado.getLong(1));
                        item.setDescripcion(resultado.getString(2));
                        item.setNrodoc(resultado.getLong(3));
                        item.setApellidos(resultado.getString(4));
                        item.setNombres(resultado.getString(5));
                        item.setCodigoCargo(resultado.getLong(6));
                        item.setIdDivipolPds(resultado.getInt(7));
                        relacion.add(item);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(OrdenDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        return relacion;
    }

    public List<ListadoRelacionOrden> listadoAsignacionOrdenEstado(Long idOrden, int estado) {
        StringBuilder sb = new StringBuilder();
        final ArrayList<ListadoRelacionOrden> relacion = new ArrayList<ListadoRelacionOrden>();
        sb.append("select idsuplenciadetalle, ");
        sb.append("d.descripcion, ");
        sb.append("a.nrodoc, ");
        sb.append("concat(ifnull(b.apellido1,''),' ',ifnull(b.apellido2,'')) as apellidos, ");
        sb.append("concat(ifnull(b.nombre1,''),' ',ifnull(b.nombre2,'')) as nombres, ");
        sb.append(" idDivipolPds ");
        sb.append("from suplenciaxdetalle a ");
        sb.append("inner join empleado b on a.nrodoc = b.nrodoc ");
        sb.append("inner join ordensuplenciadetalle c on a.idOrdenDetalle = c.idOrdenDetalle and c.idOrden = ");
        sb.append(idOrden);
        sb.append(" inner join cargos d on BINARY c.codigoCargo = BINARY d.codigoCargo ");
        sb.append(" where idEstadoAtencionOrden = ");
        sb.append(estado);
        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {
            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    resultado.beforeFirst();
                    while (resultado.next()) {
                        ListadoRelacionOrden item = new ListadoRelacionOrden();
                        item.setIdsuplenciadetalle(resultado.getLong(1));
                        item.setDescripcion(resultado.getString(2));
                        item.setNrodoc(resultado.getLong(3));
                        item.setApellidos(resultado.getString(4));
                        item.setNombres(resultado.getString(5));
                        item.setIdDivipolPds(resultado.getInt(6));
                        relacion.add(item);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(OrdenDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        return relacion;
    }

    public List<ListadoRelacionOrden> listadoAsignacionOrdenEstadoPds(Long idOrden, int estado, Long pds) {
        StringBuilder sb = new StringBuilder();
        final ArrayList<ListadoRelacionOrden> relacion = new ArrayList<ListadoRelacionOrden>();
        sb.append("select idsuplenciadetalle, ");
        sb.append("d.descripcion, ");
        sb.append("a.nrodoc, ");
        sb.append("concat(ifnull(b.apellido1,''),' ',ifnull(b.apellido2,'')) as apellidos, ");
        sb.append("concat(ifnull(b.nombre1,''),' ',ifnull(b.nombre2,'')) as nombres, ");
        sb.append(" idDivipolPds, placaMovil,nombreConductor ");
        sb.append("from suplenciaxdetalle a ");
        sb.append("inner join empleado b on a.nrodoc = b.nrodoc ");
        sb.append("inner join ordensuplenciadetalle c on a.idOrdenDetalle = c.idOrdenDetalle and c.idOrden = ");
        sb.append(idOrden);
        sb.append(" inner join cargos d on BINARY c.codigoCargo = BINARY d.codigoCargo ");
        sb.append(" where idEstadoAtencionOrden = ");
        sb.append(estado);
        /*sb.append("(select z.idEstadoAtencionOrden from estadosatencionordenessuplencia z where codigoEstadoAtencion = 'PRE' limit 1) ");*/
 /* sb.append(estado);
        sb.append(" and idDivipolPds = ");*/
 /*sb.append(pds);*/


        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {
            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    resultado.beforeFirst();
                    while (resultado.next()) {
                        ListadoRelacionOrden item = new ListadoRelacionOrden();
                        item.setIdsuplenciadetalle(resultado.getLong(1));
                        item.setDescripcion(resultado.getString(2));
                        item.setNrodoc(resultado.getLong(3));
                        item.setApellidos(resultado.getString(4));
                        item.setNombres(resultado.getString(5));
                        item.setIdDivipolPds(resultado.getInt(6));
                        item.setPlaca(resultado.getString(7));
                        item.setNombreConductor(resultado.getString(8));
                        relacion.add(item);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(OrdenDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        return relacion;
    }

    public Boolean actualizaDespacho(List<RespuestaDespacho> respuestas) throws Exception {
        Boolean respuestaB = true;
        for (RespuestaDespacho respuesta : respuestas) {
            StringBuilder sb = new StringBuilder();
            sb.append("update suplenciaxdetalle set idEstadoAtencionOrden = ");
            sb.append(respuesta.getEstado());
            sb.append(", observaciones='");
            sb.append(respuesta.getObservaciones());
            sb.append("' , fecharesolucion3 = now()");
            sb.append(" where idsuplenciadetalle = ");
            sb.append(respuesta.getIdsuplenciadetalle());
            if (!conex.ejecutar(sb.toString())) {
                respuestaB = false;
                return respuestaB;
            }
        }
        return respuestaB;
    }

    public List<InformacionOrdenPDS> obtieneInformacionOrdenesPDS(Long idPds, Long evento) {
        final ArrayList<InformacionOrdenPDS> respuesta = new ArrayList<InformacionOrdenPDS>();
        StringBuilder sb = new StringBuilder();
        sb.append("select distinct a.idOrden, b.descripcion, d.codigoPuesto, d.nombrePuesto ");
        sb.append("from ordensuplencia a  ");
        sb.append("inner join estadosordenessuplencia b on (a.idEstadoOrden = b.idEstadoOrden) ");
        sb.append("inner join evento c on (c.codigoevento = a.codigoevento) ");
        sb.append("inner join divipol d on (d.idprueba = c.idprueba and d.iddivipol = a.idDivipolSitio) ");
        sb.append("inner join ordensuplenciadetalle e on (e.idOrden = a.idOrden) ");
        sb.append("inner join suplenciaxdetalle f on (f.idOrdenDetalle = e.idOrdenDetalle and idDivipolPds = ");
        sb.append(idPds);
        sb.append(") and a.codigoEvento = ");
        sb.append(evento);
        sb.append(" union ");
        sb.append("select distinct a.idOrden, b.descripcion, d.codigoPuesto, d.nombrePuesto ");
        sb.append("from ordensuplencia a  ");
        sb.append("inner join estadosordenessuplencia b on (a.idEstadoOrden = b.idEstadoOrden and b.codigoEstado = 'VAC') ");
        sb.append("inner join evento c on (c.codigoevento = a.codigoevento) ");
        sb.append("inner join divipol d on (d.idprueba = c.idprueba and d.iddivipol = a.idDivipolSitio) ");
        sb.append("inner join ordensuplenciadetalle e on (e.idOrden = a.idOrden) ");
        sb.append(" and a.codigoEvento = ");
        sb.append(evento);
        //obtenerDetalleOrden

        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {
            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    resultado.beforeFirst();
                    while (resultado.next()) {
                        InformacionOrdenPDS detalle = new InformacionOrdenPDS();
                        detalle.setIdOrden(resultado.getLong(1));
                        detalle.setEstado(resultado.getString(2));
                        detalle.setCodigoSitio(resultado.getLong(3));
                        detalle.setNombreSitio(resultado.getString(4));
                        try {

                            detalle.setDetallesPersonal((List<DetalleOrden>) new OrdenDao().obtenerDetalleOrden(detalle.getIdOrden().toString()));
                        } catch (Exception ex) {
                            Logger.getLogger(OrdenDao.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        respuesta.add(detalle);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(OrdenDao.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        return respuesta;
    }

    public List<InformacionDetalladaPDS> obtieneInformacionTodosPDS(Long idEvento) {
        final ArrayList<InformacionDetalladaPDS> respuesta = new ArrayList<InformacionDetalladaPDS>();
        StringBuilder sb = new StringBuilder();

        sb.append("select nombrePuesto,cargo,sum(asistentes) as asistentes,sum(disponibles) as disponibles ,sum(despachados) as despachados, sum(devueltos) as devueltos from  ");
        sb.append("(select ");
        sb.append("f.nombrePuesto,  ");
        sb.append("d.descripcion as cargo,   ");
        sb.append("count(0) as asistentes, ");
        sb.append("( select count(0) ");
        sb.append("from asistencia asis ");
        sb.append("inner join empleado c on (c.idempleado = asis.idempleado and c.nrodoc not in (select nrodoc from suplenciaxdetalle x inner join ordensuplenciadetalle y on (x.idOrdenDetalle = y.idOrdenDetalle) ");
        sb.append("inner join ordensuplencia z on (z.idOrden = y.idOrden and z.codigoevento = ");
        sb.append(idEvento);
        sb.append(") ");
        sb.append("where idEstadoAtencionOrden not in (select idEstadoAtencionOrden from estadosatencionordenessuplencia where codigoEstadoAtencion in ('PRE','DES','DEV','REC')) AND idDivipolPds =asis.iddivipol )) ");
        sb.append("inner join detalle_cargo_x_puesto_x_evento b on (asis.codigoevento = b.codigoevento and asis.iddivipol = b.iddivipol and c.nrodoc = b.nrodoc)  ");
        sb.append("inner join cargos d on (d.codigocargo = b.codigocargo) ");
        sb.append("where asis.iddivipol = a.iddivipol and asis.codigoevento = ");
        sb.append(idEvento);
        sb.append(") as disponibles, ");
        sb.append("0 as despachados, ");
        sb.append("0 as devueltos  ");
        sb.append("from asistencia a ");
        sb.append("inner join empleado c on (c.idempleado = a.idempleado) ");
        sb.append("inner join detalle_cargo_x_puesto_x_evento b on (a.codigoevento = b.codigoevento and a.iddivipol = b.iddivipol and c.nrodoc = b.nrodoc) ");
        sb.append("inner join cargos d on (d.codigocargo = b.codigocargo) ");
        sb.append("inner join divipol f on (f.iddivipol = a.iddivipol  and idTipoSitio  = 5) ");
        sb.append("where a.codigoevento = ");
        sb.append(idEvento);
        sb.append(" group by f.nombrePuesto,d.descripcion ");
        sb.append(" union ");
        sb.append("select ");
        sb.append("f.nombrePuesto, ");
        sb.append("e.descripcion, ");
        sb.append("count(0) as asistentes, ");
        sb.append("0 as disponibles, ");
        sb.append("(select count(distinct x.idempleado) as despachados ");
        sb.append("from asistencia x , ");
        sb.append("cargos v, ");
        sb.append("empleado y ");
        sb.append("inner join suplenciaxdetalle z on (z.nrodoc = y.nrodoc and idEstadoAtencionOrden in (select idEstadoAtencionOrden from estadosatencionordenessuplencia where codigoEstadoAtencion = 'DES')) ");
        sb.append("inner join ordensuplenciadetalle w on (w.idOrdenDetalle = z.idOrdenDetalle) ");
        sb.append("where v.codigocargo = w.codigocargo ");
        sb.append("and v.codigocargo = e.codigocargo ");
        sb.append("and  x.idempleado = y.idempleado) as despachados, ");
        sb.append("(select count(distinct x.idempleado) as despachados ");
        sb.append("from asistencia x , ");
        sb.append("cargos v, ");
        sb.append("empleado y ");
        sb.append("inner join suplenciaxdetalle z on (z.nrodoc = y.nrodoc and idEstadoAtencionOrden in (select idEstadoAtencionOrden from estadosatencionordenessuplencia where codigoEstadoAtencion = 'DEV')) ");
        sb.append("inner join ordensuplenciadetalle w on (w.idOrdenDetalle = z.idOrdenDetalle) ");
        sb.append("where v.codigocargo = w.codigocargo  ");
        sb.append("and v.codigocargo = e.codigocargo ");
        sb.append("and  x.idempleado = y.idempleado) ");
        sb.append("AS devueltos ");
        sb.append("from asistencia a ");
        sb.append("inner join empleado b on ( a.idempleado = b.idempleado) ");
        sb.append("inner join suplenciaxdetalle c on (c.nrodoc = b.nrodoc) ");
        sb.append("inner join ordensuplenciadetalle d on (d.idOrdenDetalle = c.idOrdenDetalle) ");
        sb.append("inner join cargos e on (e.codigocargo = d.codigocargo) ");
        sb.append("inner join divipol f on (f.iddivipol = a.iddivipol and idTipoSitio  = 5) ");
        sb.append("where a.codigoevento = ");
        sb.append(idEvento);
        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {

            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    resultado.beforeFirst();
                    while (resultado.next()) {
                        InformacionDetalladaPDS detalle = new InformacionDetalladaPDS();
                        detalle.setSitio(resultado.getString(1));
                        detalle.setCargo(resultado.getString(2));
                        detalle.setAsistentes(resultado.getInt(3));
                        detalle.setDisponibles(resultado.getInt(4));
                        respuesta.add(detalle);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(OrdenDao.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        return respuesta;
    }

    ;

    public List<InformacionDetalladaPDS> obtieneInformacionPDS(Long idPds, Long idEvento) {

        final ArrayList<InformacionDetalladaPDS> respuesta = new ArrayList<InformacionDetalladaPDS>();
        StringBuilder sb = new StringBuilder();

        sb.append("select * from ");
        sb.append("(select  ");
        sb.append("        d.descripcion as cargo,  ");
        sb.append("    count(0) as asistentes, ");
        sb.append("        ( select count(0)  ");
        sb.append("        from asistencia a ");
        sb.append("        inner join empleado c on (c.idempleado = a.idempleado and c.nrodoc not in (select nrodoc from suplenciaxdetalle x inner join ordensuplenciadetalle y on (x.idOrdenDetalle = y.idOrdenDetalle) inner join ordensuplencia z on (z.idOrden = y.idOrden and z.codigoevento = " + idEvento + ") where idEstadoAtencionOrden not in (select idEstadoAtencionOrden from estadosatencionordenessuplencia where codigoEstadoAtencion in ('PRE','DES','DEV','REC')) AND idDivipolPds =a.iddivipol )) ");
        sb.append("        inner join detalle_cargo_x_puesto_x_evento b on (a.codigoevento = b.codigoevento and a.iddivipol = b.iddivipol and c.nrodoc = b.nrodoc) ");
        sb.append("        inner join cargos d on (d.codigocargo = b.codigocargo) ");
        sb.append("        where a.iddivipol = ");// + idPds);
        sb.append(idPds);
        sb.append("        and a.codigoevento = "); //+ idEvento + "         
        sb.append(idEvento);
        sb.append(") as disponibles, ");
        sb.append("    0 as despachados, ");
        sb.append("   0 as devueltos ");
        sb.append("from asistencia a ");
        sb.append("inner join empleado c on (c.idempleado = a.idempleado) ");
        sb.append("inner join detalle_cargo_x_puesto_x_evento b on (a.codigoevento = b.codigoevento and a.iddivipol = b.iddivipol and c.nrodoc = b.nrodoc) ");
        sb.append("inner join cargos d on (d.codigocargo = b.codigocargo) ");
        sb.append("where a.iddivipol = "); //+ idPds);
        sb.append(idPds);
        sb.append(" and a.codigoevento = ");// + idEvento);
        sb.append(idEvento);
        sb.append(" union ");
        sb.append("select e.descripcion,  ");
        sb.append("        count(0) as asistentes, ");
        sb.append("        0 as disponibles, ");
        sb.append("        (select count(distinct x.idempleado) as despachados ");
        sb.append("       from asistencia x , ");
        sb.append("                 cargos v, ");
        sb.append("                 empleado y  ");
        sb.append("        inner join suplenciaxdetalle z on (z.nrodoc = y.nrodoc and idEstadoAtencionOrden in (select idEstadoAtencionOrden from estadosatencionordenessuplencia where codigoEstadoAtencion = 'DES')) ");
        sb.append("        inner join ordensuplenciadetalle w on (w.idOrdenDetalle = z.idOrdenDetalle) ");
        sb.append("        where v.codigocargo = w.codigocargo   ");
        sb.append("    and v.codigocargo = e.codigocargo  ");
        sb.append("    and  x.idempleado = y.idempleado) as despachados, ");
        sb.append("    (select count(distinct x.idempleado) as despachados ");
        sb.append("        from asistencia x , ");
        sb.append("                 cargos v, ");
        sb.append("                 empleado y  ");
        sb.append("        inner join suplenciaxdetalle z on (z.nrodoc = y.nrodoc and idEstadoAtencionOrden in (select idEstadoAtencionOrden from estadosatencionordenessuplencia where codigoEstadoAtencion = 'DEV')) ");
        sb.append("        inner join ordensuplenciadetalle w on (w.idOrdenDetalle = z.idOrdenDetalle) ");
        sb.append("        where v.codigocargo = w.codigocargo   ");
        sb.append("    and v.codigocargo = e.codigocargo  ");
        sb.append("    and  x.idempleado = y.idempleado) AS devueltos ");
        sb.append("from asistencia a  ");
        sb.append("inner join empleado b on ( a.idempleado = b.idempleado) ");
        sb.append("inner join suplenciaxdetalle c on (c.nrodoc = b.nrodoc) ");
        sb.append("inner join ordensuplenciadetalle d on (d.idOrdenDetalle = c.idOrdenDetalle) ");
        sb.append("inner join cargos e on (e.codigocargo = d.codigocargo) ");
        sb.append("where a.iddivipol = "); //+ idPds);
        sb.append(idPds);
        sb.append(" and a.codigoevento = ");// + idEvento);
        sb.append(idEvento);
        sb.append(" group by e.descripcion) a; ");
        sb.append(" group by f.nombrePuesto, e.descripcion) a group by nombrePuesto,cargo;  ");

        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {

            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    resultado.beforeFirst();
                    while (resultado.next()) {
                        InformacionDetalladaPDS detalle = new InformacionDetalladaPDS();
                        detalle.setCargo(resultado.getString(1));
                        detalle.setAsistentes(resultado.getInt(2));
                        detalle.setDisponibles(resultado.getInt(3));
                        detalle.setDespachados(resultado.getInt(4));
                        detalle.setDevueltos(resultado.getInt(5));
                        respuesta.add(detalle);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(OrdenDao.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        return respuesta;
    }

    public List<InformacionControlPdsSuplencia> obtieneInformacionGeneralPDS(Long idPrueba, Long idEvento) {
        final ArrayList<InformacionControlPdsSuplencia> respuesta = new ArrayList<InformacionControlPdsSuplencia>();
        StringBuilder sb = new StringBuilder();

        sb.append("select  nombrePuesto,codigoPuesto,iddivipol, ");
        sb.append("(select count(0) from asistencia x inner join empleado y on (x.idempleado = y.idempleado) where x.iddivipol =  a.iddivipol and x.codigoevento = ");
        sb.append(idEvento);
        sb.append(" and a.iddivipol = x.iddivipol) as asistentes,");
        sb.append("(select count(0) from asistencia x inner join empleado y on (x.idempleado = y.idempleado) where x.iddivipol =  a.iddivipol and x.codigoevento = ");
        sb.append(idEvento);
        sb.append(" and y.nrodoc not in (select a.nrodoc from suplenciaxdetalle a inner join ordensuplenciadetalle b on (a.idOrdenDetalle = b.idOrdenDetalle) inner join ordensuplencia c on (c.idOrden = b.idOrden and c.codigoevento = ");
        sb.append(idEvento);
        sb.append(") inner join estadosatencionordenessuplencia d on (d.idEstadoAtencionOrden = a.idEstadoAtencionOrden and d.codigoEstadoAtencion IN ('PRE','DES','DEV','MIS','REC')))) as disponibles, ");
        sb.append("(select count(0) from ordensuplencia x inner join estadosordenessuplencia y on (y.idEstadoOrden = x.idEstadoOrden and y.codigoEstado in ('ING','PRE','INC','VAC')) where x.codigoEvento = ");
        sb.append(idEvento);
        sb.append(" and x.iddivipolSitio in (select distinct idDivipolPds from suplenciaxdetalle s inner join ordensuplenciadetalle t on (s.idOrdenDetalle = t.idOrdenDetalle) )) as ordernesNuevas, ");
        sb.append("(select count(0) from ordensuplencia a inner join estadosordenessuplencia b on (b.idEstadoOrden = a.idEstadoOrden and b.codigoEstado in ('DES')) where a.codigoEvento = ");
        sb.append(idEvento);
        sb.append(" and a.iddivipolSitio in (select distinct idDivipolPds from suplenciaxdetalle s inner join ordensuplenciadetalle t on (s.idOrdenDetalle = t.idOrdenDetalle) )) as ordenesDespachadas, ");
        sb.append("((select concat(apellido1,' ', ifnull(apellido2,''),' ',nombre1,' ',ifnull(nombre2,'')) as nombre from detalle_cargo_x_puesto_x_evento z inner join empleado y on (z.nrodoc = y.nrodoc) where codigoCargo = 12 and iddivipol = a.iddivipol and codigoevento = ");
        sb.append(idEvento);
        sb.append(" limit 1)) as nombreRPS, ");
        sb.append("(select y.celular from detalle_cargo_x_puesto_x_evento z inner join empleado y on (z.nrodoc = y.nrodoc) where codigoCargo = 12 and iddivipol = a.iddivipol and codigoevento = ");
        sb.append(idEvento);
        sb.append(" limit 1) as celularRPS  ");
        sb.append("from divipol a  ");
        sb.append("where a.idTipoSitio = 5 ");
        sb.append("and a.idprueba = ");
        sb.append(idPrueba);


        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {
            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    resultado.beforeFirst();
                    while (resultado.next()) {
                        InformacionControlPdsSuplencia info = new InformacionControlPdsSuplencia();
                        info.setNombreSitio(resultado.getString(1));
                        info.setCodigoSitio(resultado.getString(2));
                        info.setIdPds(resultado.getInt(3));
                        info.setPersonasAsistentes(resultado.getInt(4));
                        info.setPersonasDisponibles(resultado.getInt(5));
                        info.setOrdenesNuevas(resultado.getInt(6));
                        info.setOrdenesDespachadas(resultado.getInt(7));
                        info.setNombreRPS(resultado.getString(8));
                        info.setCelularRPS(resultado.getString(9));
                        respuesta.add(info);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(OrdenDao.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        return respuesta;
    }

    public List<DetallesOrdenGeneral> obtieneDetallesGenerales(Long idOrden) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        sb.append("select  ");
        sb.append("a.idsuplenciadetalle as idDetalle,  ");
        sb.append("date_format(a.fecharesolucion,'%d/%m/%Y %h:%i %p' ) as fecha_resolucion, ");
        sb.append("a.observaciones, ");
        sb.append("case tipoTransporte when 1 then 'A pie' when 2 then 'Taxi' when 3 then 'Van' ");
        sb.append("end as tipoTransporte, ");
        sb.append("placaMovil as placa, ");
        sb.append("nombreConductor as conductor, ");
        sb.append("d.descripcion as cargo, ");
        sb.append("a.nrodoc as nroDocumento, ");
        sb.append("concat(ifnull(e.nombre1,''),' ',ifnull(e.apellido1,'')) as nombre, ");
        sb.append("e.celular, ");
        sb.append("a.idEstadoAtencionOrden as estadoDetalle, ");
        sb.append("date_format(a.fecharesolucion2,'%d/%m/%Y %h:%i %p' ) as fecha_resolucion2, ");
        sb.append("date_format(a.fecharesolucion3,'%d/%m/%Y %h:%i %p' ) as fecha_resolucion3, ");
        sb.append("date_format(a.fechadespacho,'%d/%m/%Y %h:%i %p' ) as fecha_despacho, observaciones2 ");
        sb.append("from suplenciaxdetalle a  ");
        sb.append("inner join ordensuplenciadetalle b on (b.idOrdenDetalle = a.idOrdenDetalle) ");
        sb.append("inner join ordensuplencia c on (b.idOrden = c.idOrden and c.idOrden = '");
        sb.append(idOrden);
        sb.append("') inner join cargos d on (d.codigoCargo = b.codigoCargo) ");
        sb.append("inner join empleado e on (a.nrodoc = e.nrodoc) ");
        final ArrayList<DetallesOrdenGeneral> detalles = new ArrayList<DetallesOrdenGeneral>();

        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {
            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {

                    resultado.beforeFirst();
                    while (resultado.next()) {
                        DetallesOrdenGeneral detalle = new DetallesOrdenGeneral();
                        detalle.setIdDetalle(resultado.getLong(1));
                        detalle.setFechaResolucion(resultado.getString(2));
                        detalle.setObservaciones(resultado.getString(3));
                        detalle.setTipoTransporte(resultado.getString(4));
                        detalle.setPlaca(resultado.getString(5));
                        detalle.setConductor(resultado.getString(6));
                        detalle.setCargo(resultado.getString(7));
                        detalle.setDocumento(resultado.getString(8));
                        detalle.setNombre(resultado.getString(9));
                        detalle.setCelular(resultado.getString(10));
                        detalle.setIdEstadoAtencionOrden(resultado.getLong(11));
                        detalle.setFechaResolucion2(resultado.getString(12));
                        detalle.setFechaResolucion3(resultado.getString(13));
                        detalle.setFechaDespacho(resultado.getString(14));
                        detalle.setObservaciones2(resultado.getString(15));
                        detalles.add(detalle);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(OrdenDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        return detalles;

    }

    public List<InformacionDespachosOrden> despachosxOrdenDetalle(Long idOrden) {
        final ArrayList<InformacionDespachosOrden> respuesta = new ArrayList<InformacionDespachosOrden>();
        StringBuilder sb = new StringBuilder();

        sb.append("select ");
        sb.append("a.nrodoc as numero_documento, ");
        sb.append("concat(ifnull(apellido1,''),' ',ifnull(apellido2,''),' ',ifnull(nombre1,''),' ',ifnull(nombre2,'')) as nombre, ");
        sb.append("celular, ");
        sb.append("d.descripcion as cargo, ");
        sb.append("e.descripcion as estado ");
        sb.append("from suplenciaxdetalle a  ");
        sb.append("inner join ordensuplenciadetalle b on (a.idOrdenDetalle = b.idOrdenDetalle and b.idOrden = ");//
        sb.append(idOrden);//199
        sb.append(") ");
        sb.append("inner join empleado c on (c.nrodoc = a.nrodoc) ");
        sb.append("inner join cargos d on (d.codigocargo = b.codigoCargo) ");
        sb.append("inner join estadosatencionordenessuplencia e on (a.idEstadoAtencionOrden = e.idEstadoAtencionOrden) ");

        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {

            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    resultado.beforeFirst();
                    while (resultado.next()) {
                        InformacionDespachosOrden despacho = new InformacionDespachosOrden();
                        despacho.setDocumento(resultado.getLong(1));
                        despacho.setNombre(resultado.getString(2));
                        despacho.setCelular(resultado.getString(3));
                        despacho.setCargo(resultado.getString(4));
                        despacho.setEstado(resultado.getString(5));
                        respuesta.add(despacho);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(OrdenDao.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        return respuesta;
    }

    public List<InformacionDespachosOrden> despachosxOrden(Long idOrden) {
        final ArrayList<InformacionDespachosOrden> respuesta = new ArrayList<InformacionDespachosOrden>();
        StringBuilder sb = new StringBuilder();
        sb.append("select os.idOrden, d.nombrePuesto, fecharesolucion2,sd.idDivipolPds , count(*) as cantidad ");
        sb.append("from suplenciaxdetalle sd left join divipol d ");
        sb.append("on sd.idDivipolpds = d.iddivipol ");
        sb.append("left join ordensuplenciadetalle od ");
        sb.append("on sd.idOrdenDetalle = od.idOrdenDetalle ");
        sb.append("left join ordensuplencia os ");
        sb.append("on od.idOrden = os.idOrden ");
        sb.append("left join cargos ca ");
        sb.append("on od.codigoCargo = ca.codigoCargo ");
        sb.append("where os.idorden = ");
        sb.append(idOrden);
        sb.append(" group by os.idOrden, d.nombrePuesto, fecharesolucion2,sd.idDivipolPds ");
        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {

            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    resultado.beforeFirst();
                    while (resultado.next()) {
                        InformacionDespachosOrden despacho = new InformacionDespachosOrden();
                        despacho.setIdOrden(resultado.getLong(1));
                        despacho.setNombrePds(resultado.getString(2));
                        despacho.setFechaDespacho(resultado.getString(3));
                        despacho.setCantidad(resultado.getInt(5));
                        despacho.setIdDivipolPds(resultado.getLong(4));
                        respuesta.add(despacho);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(OrdenDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        return respuesta;

    }

    public List<Orden> listarOrdenesxUsuario(EmpleadoSesion emp) {
        StringBuilder sb = new StringBuilder();
        final ArrayList<Orden> ordenesUsuario = new ArrayList<Orden>();
        sb.append("select idOrden as idOrden, ");
        sb.append("c.nombre as nombrePrueba, ");
        sb.append("b.nombre as nombreEvento, ");
        sb.append("d.nombrePuesto as nombrePuesto, ");
        sb.append("e.descripcion as estadoOrden ");
        sb.append("from ordensuplencia a ");
        sb.append("inner join evento b on a.codigoEvento = b.codigoEvento ");
        sb.append("inner join prueba c on c.idprueba = b.idprueba ");
        sb.append("inner join divipol d on a.idDivipolSitio = d.idDivipol ");
        sb.append("inner join estadosordenessuplencia e on e.idEstadoOrden = a.idEstadoOrden ");
        sb.append("where a .idDivipolSitio = ");//2864 ");
        sb.append(emp.getSitios().get(0));
        sb.append(" and usuarioOrden = ");
        sb.append(emp.getNrodoc());
        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {

            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    resultado.beforeFirst();
                    while (resultado.next()) {
                        Orden orden = new Orden();
                        orden.setIdOrden(resultado.getLong(1));
                        orden.setNombrePrueba(resultado.getString(2));
                        orden.setNombreEvento(resultado.getString(3));
                        orden.setNombrePuesto(resultado.getString(4));
                        orden.setEstadoNombre(resultado.getString(5));
                        ordenesUsuario.add(orden);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(OrdenDao.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        return ordenesUsuario;
    }

    public Integer consultarCargoEquivalenteSuplencias(Integer cargo) {
        StringBuilder sb = new StringBuilder();
        final RespuestaUpdate respuesta = new RespuestaUpdate();
        sb.append("select equivalente_suplencia ");
        sb.append("from cargos  ");
        sb.append("where codigoCargo =  ");
        sb.append(cargo);

        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {
            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    resultado.first();
                    respuesta.setRespuesta(resultado.getLong(1));
                } catch (SQLException ex) {
                    Logger.getLogger(OrdenDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        return respuesta.getRespuesta().intValue();
    }

    public RespuestaGenerica consultarCapacidadSolicitud(Long idSitio, Integer evento, Integer cargo) {
        StringBuilder sb = new StringBuilder();
        final RespuestaGenerica respuesta = new RespuestaGenerica();
        sb.append("select a.codigoCargo, count(0) ");
        sb.append("from detalle_cargo_x_puesto_x_evento a ");
        sb.append("inner join cargos b on (a.codigocargo = b.codigocargo) ");
        sb.append("where iddivipol = ");
        sb.append(idSitio);
        sb.append(" and codigoevento = ");
        sb.append(evento);
        sb.append(" and a.codigoCargo = ");
        sb.append(new OrdenDao().consultarCargoEquivalenteSuplencias(cargo));
        sb.append(" group by codigoCargo, descripcion; ");
        

        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {

            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    if (resultado.first()) {
                        //respuesta.setRespuesta(resultado.getLong(1));
                        respuesta.setEntero(resultado.getInt(2));
                    } else {
                        respuesta.setEntero(0);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(OrdenDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        return respuesta;
    }

    //EXCLUSIVO PARA CANCELAR ORDENES
    public boolean cambiaEstadoHistorial(String estado, Boolean guardaHistorial, Long idOrden) {
        StringBuilder sb = new StringBuilder();

        sb.append(" update suplenciaxdetalle a ");
        sb.append(" set a.idEstadoAtencionOrden = (select z.idEstadoAtencionOrden from estadosatencionordenessuplencia z where z.codigoEstadoAtencion = '");
        sb.append(estado);
        sb.append("') ");
        sb.append(" where a.idOrdenDetalle in (select z.idOrdenDetalle from ordensuplenciadetalle z where z.idOrden = ");//
        sb.append(idOrden);
        sb.append(")");
        conex.ejecutar(sb.toString());
        if (guardaHistorial) {
            sb = new StringBuilder();
            sb.append(" insert into"
                    + " suplenciaxdetalleHistorico  ");
            sb.append(" select a.*  ");
            sb.append(" from suplenciaxdetalle a  ");
            sb.append(" inner join ordensuplenciadetalle b on (a.idOrdenDetalle = b.idOrdenDetalle) ");
            sb.append(" inner join ordensuplencia c on (b.idOrden = c.idOrden and c.idOrden = ");
            sb.append(idOrden);
            sb.append(" ); ");
            conex.ejecutar(sb.toString());
            sb = new StringBuilder();
            sb.append(" delete from suplenciaxdetalle  ");
            sb.append(" where idOrdenDetalle in (select z.idOrdenDetalle from ordensuplenciadetalle z where z.idOrden = ");
            sb.append(idOrden);
            sb.append("); ");
            conex.ejecutar(sb.toString());
            sb = new StringBuilder();
            sb.append(" update ordensuplencia  ");
            sb.append(" set idEstadoOrden = (select z.idEstadoOrden from estadosordenessuplencia z where codigoEstado = 'CAN') ");
            sb.append(" WHERE idOrden = ");
            sb.append(idOrden);
            conex.ejecutar(sb.toString());
        }
        return true;
    }

    public List<Orden> listarOrdenesxEventoxSitio(String evento, String sitio) {
        StringBuilder sb = new StringBuilder();
        final ArrayList<Orden> ordenesUsuario = new ArrayList<Orden>();
        sb.append("select idOrden as idOrden, ");
        sb.append("c.nombre as nombrePrueba, ");
        sb.append("b.nombre as nombreEvento, ");
        sb.append("d.nombrePuesto as nombrePuesto, ");
        sb.append("e.descripcion as estadoOrden, ");
        sb.append("a.idEstadoOrden as codigoOrden ");
        sb.append("from ordensuplencia a ");
        sb.append("inner join evento b on a.codigoEvento = b.codigoEvento ");
        sb.append("inner join prueba c on c.idprueba = b.idprueba ");
        sb.append("inner join divipol d on a.idDivipolSitio = d.idDivipol ");
        sb.append("inner join estadosordenessuplencia e on e.idEstadoOrden = a.idEstadoOrden ");
        sb.append("where a.idDivipolSitio = ");//2864 ");
        sb.append(sitio);
        sb.append(" and a.codigoEvento = ");
        sb.append(evento);
        /*sb.append(" and usuarioOrden = ");
         sb.append(emp.getNrodoc());*/
        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {

            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    resultado.beforeFirst();
                    while (resultado.next()) {
                        Orden orden = new Orden();
                        orden.setIdOrden(resultado.getLong(1));
                        orden.setNombrePrueba(resultado.getString(2));
                        orden.setNombreEvento(resultado.getString(3));
                        orden.setNombrePuesto(resultado.getString(4));
                        orden.setEstadoNombre(resultado.getString(5));
                        orden.setEstado(resultado.getInt(6));
                        EstadosOrden estado = new EstadosOrdenDao().buscarPorId(orden.getEstado());
                        orden.setEstados(estado);
                        //añade los detalles         

                        HashMap estadosDespachos = new OrdenDao().determinarEstadoOrden(orden.getIdOrden());
                        orden.setDespachados((Integer) estadosDespachos.get("DES"));
                        orden.setTotalAsignados((Integer) estadosDespachos.get("TOTAL"));
                        orden.setDevueltos((Integer) estadosDespachos.get("DEV"));
                        orden.setRecibidos((Integer) estadosDespachos.get("REC"));
                        orden.setPreasignados((Integer) estadosDespachos.get("PRE"));
                        orden.setSolicitados((Integer) estadosDespachos.get("SOL"));

                        List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();
                        detalles = obtenerDetalleOrden(orden.getIdOrden().toString());
                        orden.setDetalle(detalles);
                        ordenesUsuario.add(orden);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(OrdenDao.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        return ordenesUsuario;
    }

    public Boolean actualizarRecepcionDevueltos(List<ActualizaRecepcionDevolucion> recepcionDevueltos) throws Exception {
        Boolean respuesta = true;

        for (ActualizaRecepcionDevolucion detalle : recepcionDevueltos) {
            StringBuilder sb = new StringBuilder();
            sb.append("update suplenciaxdetalle set idEstadoAtencionOrden = 4 , observaciones2 = '");
            sb.append(detalle.getObservacion());
            sb.append("', fecharesolucion3 =  now() ");
            sb.append(" where idsuplenciadetalle = ");
            sb.append(detalle.getIdDetalle());
            conex.ejecutar(sb.toString());
        }
        return respuesta;
    }

    //RelacionTransporteEnvio
    public Boolean actualizarRelacionTransporte(List<RelacionTransporteEnvio> relacion) {
        Boolean respuesta = true;
        for (RelacionTransporteEnvio item : relacion) {
            StringBuilder sb = new StringBuilder();
            sb.append("update suplenciaxdetalle set placaMovil = '");
            sb.append(item.getPlaca());
            sb.append("' , nombreConductor = '");
            sb.append(item.getConductor());
            sb.append("' where idsuplenciadetalle = ");
            sb.append(item.getIdSuplenciaDetalle());
            conex.ejecutar(sb.toString());
        }
        return respuesta;
    }

    public List<DetallesOrdenGeneral> listarDespachosOrdenPDS(Long idOrden, int idDivipolPDS) {
        final ArrayList<DetallesOrdenGeneral> respuesta = new ArrayList<DetallesOrdenGeneral>();

        StringBuilder sb = new StringBuilder();
        sb.append("select sd.idsuplenciadetalle, c.codigoCargo, c.descripcion, concat(e.nombre1,' ', ifnull(e.nombre2, ' '), ' ', e.apellido1, ' ', ifnull(e.apellido2, ' ')) as nombres,");
        sb.append(" e.nrodoc, sd.tipoTransporte, sd.placaMovil, sd.nombreConductor, sd.observaciones2,  eaos.descripcion as estad");
        sb.append(" from");
        sb.append(" suplenciaxdetalle sd ");
        sb.append(" left join ordensuplenciadetalle osd on sd.idOrdenDetalle=osd.idOrdenDetalle");
        sb.append(" left join ordensuplencia o on o.idOrden=osd.idOrden");
        sb.append(" left join cargos c on c.codigoCargo=osd.codigoCargo");
        sb.append(" left join empleado e on e.nrodoc=sd.nrodoc");
        sb.append(" left join estadosatencionordenessuplencia eaos on eaos.idEstadoAtencionOrden=sd.idEstadoAtencionOrden");
        sb.append(" where sd.idDivipolPds=").append(idDivipolPDS);
        sb.append(" and o.idOrden=").append(idOrden);
        sb.append(" and (eaos.descripcion='Preasignado' or eaos.descripcion='Despachado')");

        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {

            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    resultado.beforeFirst();
                    while (resultado.next()) {
                        DetallesOrdenGeneral despacho = new DetallesOrdenGeneral();
                        despacho.setIdDetalle(resultado.getLong("idsuplenciadetalle"));
                        despacho.setCargo(resultado.getString("descripcion"));
                        despacho.setDocumento(resultado.getString("nrodoc"));
                        despacho.setNombre(resultado.getString("nombres"));
                        despacho.setObservaciones(resultado.getString("observaciones2"));
                        despacho.setObservaciones2(resultado.getString("estad"));
                        despacho.setCodigoCargo(resultado.getString("codigoCargo"));

                        String tipoTransporte = " ";
                        switch (resultado.getInt("tipoTransporte")) {
                            case 1:
                                tipoTransporte = "a pie";
                                break;
                            case 2:
                                tipoTransporte = "taxi";
                                break;
                            case 3:
                                tipoTransporte = "van";
                                break;

                        }
                        despacho.setTipoTransporte(tipoTransporte);
                        despacho.setConductor(resultado.getString("nombreConductor"));
                        despacho.setPlaca(resultado.getString("placaMovil"));
                        respuesta.add(despacho);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(OrdenDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        return respuesta;

    }

    public List<Empleado> listarPersonasDisponiblesParaAsignarOrden(Long codigoEvento, String codigoCargo, int idDivipolPDS) throws Exception {
        final ArrayList<Empleado> respuesta = new ArrayList<Empleado>();

        Cargo cargo=new CargoDao().GetById(codigoCargo);
        
        StringBuilder sb = new StringBuilder();
        sb.append(" select e.idEmpleado, e.nrodoc, e.nombre1, e.nombre2, e.apellido1, e.apellido2");
        sb.append(" from asistencia asis");
        sb.append(" inner join empleado e on e.idEmpleado=asis.idempleado");
        sb.append(" inner join detalle_cargo_x_puesto_x_evento d on d.idDivipol=asis.iddivipol and d.nrodoc=e.nrodoc and d.codigoEvento=asis.codigoevento");
        sb.append(" inner join cargos c on c.codigoCargo=d.codigoCargo ");
        sb.append(" where asis.iddivipol=").append(idDivipolPDS);
        sb.append(" and asis.codigoevento=").append(codigoEvento);
        sb.append(" and c.nivel_cargo=").append(cargo.getNivel_cargo());
        sb.append(" and (e.nrodoc not in (");
        sb.append(" select sd.nrodoc ");
        sb.append(" from suplenciaxdetalle sd ");
        sb.append(" left join ordensuplenciadetalle osd on sd.idOrdenDetalle=osd.idOrdenDetalle ");
        sb.append(" left join ordensuplencia o on o.idOrden=osd.idOrden ");
        sb.append(" where sd.idDivipolPds=d.idDivipol and  o.codigoEvento=d.codigoEvento");
        sb.append(" ) || e.nrodoc in ( select sd.nrodoc ");
        sb.append(" from suplenciaxdetalle sd  ");
        sb.append(" left join ordensuplenciadetalle osd on sd.idOrdenDetalle=osd.idOrdenDetalle ");
        sb.append(" left join ordensuplencia o on o.idOrden=osd.idOrden ");
        sb.append(" left join estadosatencionordenessuplencia eaos on eaos.idEstadoAtencionOrden=sd.idEstadoAtencionOrden ");
        sb.append(" where sd.idDivipolPds=d.idDivipol and  o.codigoEvento=d.codigoEvento and eaos.codigoEstadoAtencion='DRE'))");


        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {

            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    resultado.beforeFirst();
                    while (resultado.next()) {
                        Empleado em = new Empleado();
                        em.setNrodoc(resultado.getLong("nrodoc"));
                        em.setIdEmpleado(resultado.getInt("idEmpleado"));
                        em.setApellido1(resultado.getString("apellido1"));
                        em.setApellido2(resultado.getString("apellido2"));
                        em.setNombre1(resultado.getString("nombre1"));
                        em.setNombre2(resultado.getString("nombre2"));
                        respuesta.add(em);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(OrdenDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        return respuesta;

    }

    public static void main(String args[]) throws Exception {
        new OrdenDao().obtieneDetallesGenerales(20L);
    }

    private EventoDao EventoDao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Orden> listarOrdenesxSitio(Long idDivipol) {
        final ArrayList<Orden> ordenes = new ArrayList<Orden>();
        StringBuilder sb = new StringBuilder();
        sb.append("select idOrden,usuarioOrden,idDivipolSitio,codigoEvento,idEstadoOrden,envioMail ");
        sb.append("from ordensuplencia ");
        sb.append("where idDivipolSitio = ");
        sb.append(idDivipol);

        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {
            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    resultado.beforeFirst();
                    while (resultado.next()) {
                        Orden orden = new Orden();
                        orden.setIdOrden(resultado.getLong(1));
                        orden.setUsuarioOrden(resultado.getLong(2));
                        orden.setIdDivipolStio(resultado.getLong(3));
                        orden.setCodigoEvento(resultado.getLong(4));
                        ordenes.add(orden);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(OrdenDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        return ordenes;

    }

    public Boolean eliminaDetalleDespacho(long id) throws Exception {
        
        

        StringBuilder sb = new StringBuilder();
        sb.append("DELETE from suplenciaxdetalle ");
        sb.append(" where idsuplenciadetalle = ");
        sb.append(id);

        if (!conex.ejecutar(sb.toString())) {
            return false;
        }

        return true;
    }

    public Orden buscarOrdenPorId(Long idOrden) {

        final Orden orden = new Orden();
        StringBuilder sb = new StringBuilder();
        sb.append(" select idOrden, idDivipolSitio,codigoEvento");
        sb.append(" from ordensuplencia ");
        sb.append(" where idOrden = ");
        sb.append(idOrden);

        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {
            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    while (resultado.next()) {
                        orden.setIdOrden(resultado.getLong("idOrden"));
                        orden.setIdDivipolStio(resultado.getLong("idDivipolSitio"));
                        orden.setCodigoEvento(resultado.getLong("codigoEvento"));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(OrdenDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        return orden;

    }
    
    //con el cargo y evento devuelve una prueba que necesite suplencia de ese cargo
    public String[] obtenerOrdenIncompleta(String codigoCargo, Long idEvento){
        final String[] parametros  = new String[6];
        StringBuilder sb = new StringBuilder();        
        
        sb.append("select idOrdenDetalle, b.codigoEvento , b.idDivipolSitio, codigoCargo, a.idOrden ");	
	sb.append("from ordensuplenciadetalle a ");
	sb.append("inner join ordensuplencia b on (a.idOrden = b.idOrden and b.codigoEvento = ");
        sb.append(idEvento);
        sb.append(") ");
	sb.append("where a.cantidad > (select count(0) from suplenciaxdetalle z where z.idOrdenDetalle = a.idOrdenDetalle) ");
	sb.append("and a.codigoCargo = '");
        sb.append(codigoCargo);
        sb.append("' ");        
	sb.append("order by idOrdenDetalle ");
	sb.append("limit 1 ");
        
        
        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {
            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    if(resultado.first()){
                        parametros[0] = resultado.getString(1);
                        parametros[1] = resultado.getString(2);
                        parametros[2] = resultado.getString(3);
                        parametros[3] = resultado.getString(4);
                        parametros[4] = resultado.getString(5);
                        parametros[5] = "1";
                    }
                    else 
                        parametros[5] = "0";
                    
                } catch (SQLException ex) {
                    Logger.getLogger(OrdenDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        return parametros;
    }
    
    //asigna personal mediante la asistencia
    public Long asignacionxAsistencia(Long idEmpleado, Integer idDivipol, Long idEvento, String codigoCargo){
        StringBuilder sb = new StringBuilder();
        Empleado empleado;
        EstadosGeneral estado;
        EstadosOrden estadoCompleto, estadoIncompleto;
        Integer asignados, solicitados;
        String[] parametros  = new String[5];
        final Long[] idDcpe = new Long[1];
        empleado = new EmpleadoDao().GetById(idEmpleado);
        Evento evento;
        evento = new EventoDao().GetById(idEvento);
        Sitio sitio;
        sitio = new SitioDao().BuscarSitioPorId(idDivipol);
     
        Long estadoFinal;
        parametros = this.obtenerOrdenIncompleta(codigoCargo, idEvento);
        if(parametros[5].equals("0"))
            return -2L;
        estado = new EstadosOrdenDao().buscarPorCodigoEstadoAsignacion("PRE");
        estadoCompleto = new EstadosOrdenDao().buscarPorCodigo("COM");
        estadoIncompleto = new EstadosOrdenDao().buscarPorCodigo("INC");
        //buscarPorCodigoEstadoAsignacion
        //select * from estadosatencionordenessuplencia where codigoEstadoAtencion = 'PRE';
        
        
        sb.append("select id ");
        sb.append("from detalle_cargo_x_puesto_x_evento a ");
	sb.append("where a.codigoEvento = ");//evento ");
        sb.append(idEvento) ;
	sb.append(" and a.idDivipol = ");//new.iddivipol ");
        sb.append(idDivipol);
	sb.append(" and a.nrodoc = ");//numerodoc ");
        sb.append(empleado.getNrodoc());
	sb.append(" and a.codigoCargo = ");// cargo; ");
        sb.append(codigoCargo);
        
        conex.consultar(sb.toString(),new Operaciones.OperacionConsulta() {

            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    resultado.first();
                    idDcpe[0] = resultado.getLong(1);
                } catch (SQLException ex) {
                    Logger.getLogger(OrdenDao.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
        sb = new StringBuilder();
        sb.append("insert into suplenciaxdetalle (nrodoc,idDetalle,idOrdenDetalle,idEstadoAtencionOrden,idDivipolPds) ");
        sb.append("values(");	
        sb.append(empleado.getNrodoc());
        sb.append(" , ");
        sb.append(idDcpe[0]);
        sb.append(" , ");
        sb.append(parametros[0]);
        sb.append(" , ");
        sb.append(estado.getId());
        sb.append(" , ");
        sb.append(idDivipol);
        sb.append(")");
        
        if(!conex.ejecutar(sb.toString()))
            return -1L;

        sb = new StringBuilder();
        sb.append("select count(0) ");
	sb.append("from suplenciaxdetalle ");
	sb.append("where idOrdenDetalle in (select idOrdenDetalle from ordensuplenciadetalle where idOrden = ");//orden)  ");
        sb.append(parametros[4]);
        sb.append(")");
        
        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {
            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    if(resultado.first())
                        idDcpe[0] = resultado.getLong(1);
                } catch (SQLException ex) {
                    Logger.getLogger(OrdenDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            };
        });   
        
        asignados = idDcpe[0].intValue();        
        
        
        sb = new StringBuilder();
	sb.append("select sum(cantidad) ");
	sb.append("from ordensuplenciadetalle ");
	sb.append("where idOrden = ");
        sb.append(parametros[4]);
        
        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {

            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    if(resultado.first()){
                        idDcpe[0] = resultado.getLong(1);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(OrdenDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        solicitados = idDcpe[0].intValue();
        
        if(asignados < solicitados)
            estadoFinal = estadoIncompleto.getIdEstadoOrden();
        else
            estadoFinal = estadoCompleto.getIdEstadoOrden();
        
        sb = new StringBuilder();
        sb.append("update ordensuplencia set idEstadoOrden = ");//nuevoEstado 
        sb.append(estadoFinal);
        sb.append(" where idOrden = ");
        sb.append(parametros[4]);
        
        if(!conex.ejecutar(sb.toString())){
            return -1L;
        }
        
        return Long.parseLong(parametros[4]);
    }

}

class RespuestaUpdate {

    private Long respuesta;
    private Long codigo;
    private Integer cuantos;
    private Integer total;

    public Long getCodigo() {
        return this.codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Integer getCuantos() {
        return cuantos;
    }

    public void setCuantos(Integer cuantos) {
        this.cuantos = cuantos;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * @return the respuesta
     */
    public Long getRespuesta() {
        return respuesta;
    }

    /**
     * @param respuesta the respuesta to set
     */
    public void setRespuesta(Long respuesta) {
        this.respuesta = respuesta;
    }

}
