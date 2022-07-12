/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.negocio.ordenes;

import co.com.grupoasd.nomina.modelo.ActualizaRecepcionDevolucion;
import co.com.grupoasd.nomina.modelo.ConsultaOrdenes;
import co.com.grupoasd.nomina.modelo.DespachoDetalleRespuesta;
import co.com.grupoasd.nomina.modelo.DetalleOrden;
import co.com.grupoasd.nomina.modelo.DetallesOrdenGeneral;
import co.com.grupoasd.nomina.modelo.EmpleadoSesion;
import co.com.grupoasd.nomina.modelo.InformacionControlPdsSuplencia;
import co.com.grupoasd.nomina.modelo.InformacionDespachosOrden;
import co.com.grupoasd.nomina.modelo.InformacionDetalladaPDS;
import co.com.grupoasd.nomina.modelo.InformacionOrdenPDS;
import co.com.grupoasd.nomina.modelo.ListadoRelacionOrden;
import co.com.grupoasd.nomina.modelo.Orden;
import co.com.grupoasd.nomina.modelo.OrdenCompleta;
import co.com.grupoasd.nomina.modelo.PersonalOrdenSuplencia;
import co.com.grupoasd.nomina.modelo.RelacionTransporteEnvio;
import co.com.grupoasd.nomina.modelo.RespuestaDespacho;
import co.com.grupoasd.nomina.modelo.RespuestaGenerica;
import java.util.List;

/**
 *
 * @author CristianAlexander
 */
public interface IOrdenes {
    public List<Orden> listarOrdenes() throws Exception;
    public Orden getById(String idOrden)   throws Exception;
    public List<DetalleOrden> listarDetalleOrden(String idOrden) throws Exception;
    public Orden registrarOrden(Orden orden) throws Exception;
    public Boolean registraDetalleOrden(List<DetalleOrden> detalles) throws Exception;
    public List<ConsultaOrdenes> consultaOrdenes(Long prueba) throws Exception;
    public RespuestaDespacho despacharOrden(Long idOrden,String tipoDespacho,String placa,String conductor) throws Exception;
    public List<ListadoRelacionOrden> listarDespachados(Long idOrden, String tipo) throws Exception;
    public Boolean actualizaEstadoDetalleDespacho(List<DespachoDetalleRespuesta> respuesta) throws Exception;
    public Boolean actualizaEstadoDetalleDespachoI(List<DespachoDetalleRespuesta> respuesta) throws Exception;
    public List<DetallesOrdenGeneral> obtieneDetallesGenerales(Long idOrden) throws Exception;
    public List<Orden> listarOrdenesxUsuario(EmpleadoSesion emp) throws Exception;
    public List<Orden> listarOrdenesxEventoxSitio(String evento, String sitio) throws Exception;
    public Boolean actualizarRecepcionDevueltos(List<ActualizaRecepcionDevolucion> recepcionDevueltos) throws Exception;
    public List<ListadoRelacionOrden> listadoAsignacionOrdenEstado(Long idOrden, int estado) throws Exception;
    public PersonalOrdenSuplencia consultarDisponibilidadPersonal(Long idDivipol, Long codigoEvento, Long codigoCargo, Long nrodoc, Integer cambioSuplencia) throws Exception;
    public List<Orden> ordenesxPds(Long idDivipolPds) throws Exception;
    public List<ListadoRelacionOrden> listadoAsignacionOrdenEstadoPds(Long idOrden, String estado, Long pds) throws Exception;
    public List<InformacionDespachosOrden> despachosxOrden (Long idOrden) throws Exception;
    public List<InformacionControlPdsSuplencia> obtieneInformacionGeneralPDS(Long idPrueba, Long idEvento) throws Exception;
    public List<InformacionDetalladaPDS> obtieneInformacionPDS(Long idPds, Long idEvento) throws Exception;
    public List<InformacionOrdenPDS> obtieneInformacionOrdenesPDS(Long idPds, Long evento) throws Exception;
    public RespuestaGenerica consultarCapacidadSolicitud(Long idSitio, Integer evento, Integer cargo) throws Exception;
    public boolean cambiaEstadoHistorial(String estado, Boolean guardaHistorial, Long idOrden) throws Exception;
    public Boolean actualizarRelacionTransporte(String relacion) throws Exception;
    public List<InformacionDetalladaPDS> obtieneInformacionTodosPDS(Long idEvento) throws Exception;
    public OrdenCompleta obtieneOrdenCompleta(Long idOrden) throws Exception;
    public List<OrdenCompleta> obtieneOrdenesxSitio(Long idDivipol) throws Exception;
}
