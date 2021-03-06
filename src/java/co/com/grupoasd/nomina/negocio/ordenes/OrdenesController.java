/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.negocio.ordenes;

import co.com.grupoasd.nomina.conexion.OperacionesReporte;
import co.com.grupoasd.nomina.dao.AsistenciaDao;
import co.com.grupoasd.nomina.dao.EmpleadoDao;
import co.com.grupoasd.nomina.dao.EstadosOrdenDao;
import co.com.grupoasd.nomina.dao.EventoDao;
import co.com.grupoasd.nomina.dao.MiscDao;
import co.com.grupoasd.nomina.dao.OrdenDao;
import co.com.grupoasd.nomina.modelo.ActualizaRecepcionDevolucion;
import co.com.grupoasd.nomina.modelo.ConsultaOrdenes;
import co.com.grupoasd.nomina.modelo.DespachoDetalleRespuesta;
import co.com.grupoasd.nomina.modelo.DetalleOrden;
import co.com.grupoasd.nomina.modelo.DetallesOrdenGeneral;
import co.com.grupoasd.nomina.modelo.Empleado;
import co.com.grupoasd.nomina.modelo.EmpleadoSesion;
import co.com.grupoasd.nomina.modelo.EstadosGeneral;
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
import co.com.grupoasd.nomina.modelo.ParametrosCorreo;
import co.com.grupoasd.nomina.modelo.RespuestaGenerica;
import co.com.grupoasd.parametroscorreo.SingletonCorreo;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
//import static javax.ws.rs.client.Entity.json;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRStyledText;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author CristianAlexander
 */
public class OrdenesController implements IOrdenes {

    @Override
    public List<Orden> listarOrdenes() throws Exception {
        OrdenDao dao = new OrdenDao();
        return dao.listarOrdenes();
    }

    @Override
    public Orden getById(String idOrden) throws Exception {
        OrdenDao dao = new OrdenDao();
        return dao.getById(idOrden);
    }

    @Override
    public List<DetalleOrden> listarDetalleOrden(String idOrden) throws Exception {
        OrdenDao dao = new OrdenDao();
        return dao.obtenerDetalleOrden(idOrden);
    }

    @Override
    public Orden registrarOrden(Orden orden) throws Exception {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Inicio registrarOrden");
        OrdenDao dao = new OrdenDao();
        Orden respuesta = dao.registrarOrden(orden);
        if (respuesta != null) {

        }
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Fin registrarOrden");
        return respuesta;
    }

    @Override
    public Boolean registraDetalleOrden(List<DetalleOrden> detalles) throws Exception {
        File path = new File(".").getAbsoluteFile();
        ArrayList<String> correos = new ArrayList<String>();
        Boolean respuesta;
        OrdenDao dao = new OrdenDao();
        respuesta = dao.registraDetalleOrden(detalles);
        //Envia correo        
        Long idOrden = ((DetalleOrden) detalles.get(0)).getIdOrden();

        Properties datos = new Properties();
        SingletonCorreo datosCorreo;
        datosCorreo = SingletonCorreo.getInstance();
        ParametrosCorreo parametros = (ParametrosCorreo) datosCorreo.getCorreos().get("suplencias");
        datos.setProperty("port", parametros.getPort());
        datos.setProperty("host", parametros.getHost());
        datos.setProperty("enable", parametros.getEnable());
        datos.setProperty("starttls.enable", parametros.getStarttlsEnable());
        datos.setProperty("userEmailSmtp", parametros.getUserEmailSmtp());
        datos.setProperty("userEmail", parametros.getUserEmail());
        datos.setProperty("passwordEmail", parametros.getPasswordEmail());
        datos.setProperty("protocol", parametros.getProtocol());
        datos.setProperty("auth", parametros.getAuth());

        MiscDao misc = new MiscDao();
        correos = misc.getCorreosOrdenSuplencia(idOrden);

        for (String correo : correos) {
            generarPDF(idOrden.intValue(), correo, datos, true);
        }
        return respuesta;

    }

    @Override
    public List<ConsultaOrdenes> consultaOrdenes(Long prueba) throws Exception {
        OrdenDao dao = new OrdenDao();
        EventoDao EDao = new EventoDao();
        ArrayList<Long> eventos = new ArrayList<Long>();
        eventos = (ArrayList) EDao.retornaEventosxPrueba(prueba);
        return dao.consultaOrdenes(eventos);
    }

    @Override
    public RespuestaDespacho despacharOrden(Long idOrden, String tipoDespacho, String placa, String conductor) throws Exception {

        ArrayList<DetalleOrden> detalles = new ArrayList<DetalleOrden>();
        RespuestaDespacho respuesta = new RespuestaDespacho();
        Integer error = 0; // 0 sin problema, 1 no alcanz?? recurso del pds, 2 problemas asignando
        StringBuilder mensaje = new StringBuilder();
        Orden orden = new Orden();
        OrdenDao dao = new OrdenDao();
        orden = dao.getById(idOrden.toString());
        detalles = (ArrayList<DetalleOrden>) dao.obtenerDetalleOrden(idOrden.toString());
        for (DetalleOrden detalle : detalles) {
            List<PersonalOrdenSuplencia> personal = new ArrayList<PersonalOrdenSuplencia>();
            // personal = (ArrayList<PersonalOrdenSuplencia>) dao.personalSuplencia(detalle.getIdpds(), orden.getCodigoEvento(), Long.valueOf(detalle.getCodigoCargo()));
            if (detalle.getCantidad() > personal.size()) {
                error = 1;
            } else {
                personal = (List<PersonalOrdenSuplencia>) personal.subList(0, detalle.getCantidad());
            }

            if (error == 0) {
                if (dao.despachaDetalle(personal, detalle.getIdOrdenDetalle(), tipoDespacho, placa, conductor)) {
                    if (!dao.cambiaEstadoOrden(2, idOrden)) //cambia el estado a despachada
                    {
                        mensaje.append("No se cambio el estado de la orden. ");
                    }
                    mensaje.append("Personal despachado satisfactoriamente.");
                } else {
                    mensaje.append("Problemas asignando personal de suplencia.");
                    error = 2;
                }
            }
        }
        respuesta.setCodigo(error);
        respuesta.setMensaje(mensaje.toString());
        return respuesta;
    }

    @Override
    public List<ListadoRelacionOrden> listarDespachados(Long idOrden, String tipo) throws Exception {
        OrdenDao dao = new OrdenDao();
        EstadosGeneral estadoDespacho = new EstadosOrdenDao().buscarPorCodigoEstadoAsignacion(tipo);
        return dao.listadoRelacionOrden(idOrden, estadoDespacho.getId().intValue());
    }

    @Override
    public Boolean actualizaEstadoDetalleDespacho(List<DespachoDetalleRespuesta> respuesta) throws Exception {
        OrdenDao dao = new OrdenDao();
        return dao.actualizaEstadoDetalleDespacho(respuesta);
    }

    @Override
    public Boolean actualizaEstadoDetalleDespachoI(List<DespachoDetalleRespuesta> respuesta) throws Exception {
        OrdenDao dao = new OrdenDao();
        return dao.actualizaEstadoDetalleDespacho(respuesta);
    }
// Metodo actual despacho
    public Boolean actualizaDetalleDespachoI(List<DespachoDetalleRespuesta> respuesta) throws Exception {
        OrdenDao dao = new OrdenDao();
        return dao.actualizaDetalleDespacho(respuesta);
    }
    @Override
    public List<DetallesOrdenGeneral> obtieneDetallesGenerales(Long idOrden) throws Exception {
        OrdenDao dao = new OrdenDao();
        return dao.obtieneDetallesGenerales(idOrden);
    }

    @Override
    public Boolean actualizarRecepcionDevueltos(List<ActualizaRecepcionDevolucion> recepcionDevueltos) throws Exception {
        OrdenDao dao = new OrdenDao();
        return dao.actualizarRecepcionDevueltos(recepcionDevueltos);
    }

    @Override
    public List<Orden> listarOrdenesxUsuario(EmpleadoSesion emp) throws Exception {
        return new OrdenDao().listarOrdenesxUsuario(emp);
    }

    @Override
    public List<Orden> listarOrdenesxEventoxSitio(String evento, String sitio) throws Exception {
        return new OrdenDao().listarOrdenesxEventoxSitio(evento, sitio);
    }

    @Override
    public List<ListadoRelacionOrden> listadoAsignacionOrdenEstado(Long idOrden, int estado) throws Exception {
        return new OrdenDao().listadoAsignacionOrdenEstado(idOrden, estado);
    }

    @Override
    public PersonalOrdenSuplencia consultarDisponibilidadPersonal(Long idDivipol, Long codigoEvento, Long codigoCargo, Long nrodoc, Integer cambioSuplencia) throws Exception {
        return new OrdenDao().consultarDisponibilidadPersonal(idDivipol, codigoEvento, codigoCargo, nrodoc, cambioSuplencia);
    }

    @Override
    public List<Orden> ordenesxPds(Long idDivipolPds) throws Exception {
        return new OrdenDao().ordenesxPds(idDivipolPds);
    }

    @Override
    public List<ListadoRelacionOrden> listadoAsignacionOrdenEstadoPds(Long idOrden, String estado, Long pds) throws Exception {
        EstadosGeneral estadoDespacho = new EstadosOrdenDao().buscarPorCodigoEstadoAsignacion(estado);
        return new OrdenDao().listadoAsignacionOrdenEstadoPds(idOrden, estadoDespacho.getId().intValue(), pds);
    }

    @Override
    public List<InformacionDespachosOrden> despachosxOrden(Long idOrden) throws Exception {
        return new OrdenDao().despachosxOrden(idOrden);
    }

    @Override
    public List<InformacionControlPdsSuplencia> obtieneInformacionGeneralPDS(Long idPrueba, Long idEvento) throws Exception {
        return new OrdenDao().obtieneInformacionGeneralPDS(idPrueba, idEvento);
    }

    @Override
    public List<InformacionDetalladaPDS> obtieneInformacionPDS(Long idPds, Long idEvento) throws Exception {
        return new OrdenDao().obtieneInformacionPDS(idPds, idEvento);
    }

    @Override
    public List<InformacionOrdenPDS> obtieneInformacionOrdenesPDS(Long idPds, Long evento) throws Exception {
        return new OrdenDao().obtieneInformacionOrdenesPDS(idPds, evento);
    }

    @Override
    public RespuestaGenerica consultarCapacidadSolicitud(Long idSitio, Integer evento, Integer cargo) throws Exception {
        return new OrdenDao().consultarCapacidadSolicitud(idSitio, evento, cargo);
    }

    @Override
    public boolean cambiaEstadoHistorial(String estado, Boolean guardaHistorial, Long idOrden) throws Exception {
        return new OrdenDao().cambiaEstadoHistorial(estado, guardaHistorial, idOrden);
    }

    @Override
    public Boolean actualizarRelacionTransporte(String relacion) throws Exception {
        ArrayList<RelacionTransporteEnvio> relacionLista = new ArrayList<RelacionTransporteEnvio>();
        JSONArray arr = new JSONArray(relacion);
        JSONObject obj = new JSONObject();
        for (int i = 0; i < arr.length(); i++) {
            RelacionTransporteEnvio item = new RelacionTransporteEnvio();
            JSONObject objeto = arr.getJSONObject(i);
            item.setIdSuplenciaDetalle(objeto.getLong("idSuplenciaDetalle"));
            item.setPlaca(objeto.getString("placa"));
            item.setConductor(objeto.getString("conductor"));
            relacionLista.add(item);
        }

        return new OrdenDao().actualizarRelacionTransporte(relacionLista);
    }

    //Crea el pdf de generacion de orden
    public static void generarPDF(int orden, String email, Properties properties, boolean primera) throws JRException, IOException, SQLException {

        try {
            JasperReport reporte = null;
            Map parameters = new HashMap();
            parameters.put("idOrden", orden);

            if (primera) {
                reporte = JasperCompileManager.compileReport("/data/report/suplencia.jrxml");
            } else {
                reporte = JasperCompileManager.compileReport("/data/report/suplenciaPersonalEnviado.jrxml");
            }
            //DbConnection db = new DbConnection().conectar();
            reporte.setProperty(JRStyledText.PROPERTY_AWT_IGNORE_MISSING_FONT, "true");

            byte[] bytes = OperacionesReporte.ejecutar(reporte, parameters);
            parameters.clear();
            //byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);

            //db.desconectar();
            //LLama el m??todo enviar correo y le envia el pdf generado
            enviarCorreo(orden, email, properties, bytes, primera);
        } catch (JRException e) {
            Logger.getLogger(OrdenesController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static void enviarCorreo(int orden, String email, Properties properties, byte[] pdf, boolean primera) throws IOException {

        StringBuilder asunto = new StringBuilder();
        StringBuilder nombreArchivo = new StringBuilder();
        if (primera) {
            asunto.append("Envio de Orden de Suplencia Nro." + orden);
            nombreArchivo.append("OrdenCreada_Nro");
            nombreArchivo.append(orden);
            nombreArchivo.append(".pdf");
        } else {
            asunto.append("Envio de Orden de Suplencia Despachada Nro." + orden);
            nombreArchivo.append("OrdenDespachada_Nro");
            nombreArchivo.append(orden);
            nombreArchivo.append(".pdf");
        }

        JavaMail mail = new JavaMail();
        mail.setPassword(properties.getProperty("passwordEmail"));
        mail.setFrom(properties.getProperty("userEmail"));
        mail.setTo(email);
        System.out.print(email);
        mail.setSubject(asunto.toString());
        mail.setNombreAdjunto(nombreArchivo.toString());
        //Ruta para leer el reporte
        mail.setAdjunto(pdf);
        try {
            if (mail.SEND(properties)) {

                if (primera) {
                    //    actualizarEstadoCreada(orden);
                } else {
                    //    actualizarEstadoDespachada(orden);
                }

            }

        } catch (Exception ex) {
            Logger.getLogger(OrdenesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<InformacionDetalladaPDS> obtieneInformacionTodosPDS(Long idEvento) throws Exception {
        return new OrdenDao().obtieneInformacionTodosPDS(idEvento);
    }

    @Override
    public OrdenCompleta obtieneOrdenCompleta(Long idOrden) throws Exception {
        OrdenCompleta orden = new OrdenCompleta();
        orden.setOrden(new OrdenDao().getById(idOrden.toString()));
        orden.setDetalles(new OrdenDao().obtenerDetalleOrden(idOrden.toString()));
        orden.setDespachos(new OrdenDao().despachosxOrdenDetalle(idOrden));
        return orden;
    }

    @Override
    public List<OrdenCompleta> obtieneOrdenesxSitio(Long idDivipol) throws Exception {
        ArrayList<Orden> ordenes;
        ArrayList<OrdenCompleta> ordenesSitio = new ArrayList<OrdenCompleta>();

        ordenes = (ArrayList<Orden>) new OrdenDao().listarOrdenesxSitio(idDivipol);
        for (Orden orden : ordenes) {
            ordenesSitio.add(new OrdenesController().obtieneOrdenCompleta(orden.getIdOrden()));
        }

        return ordenesSitio;

    }

    public List<DetallesOrdenGeneral> listarInformacionDetalleDespacho(Long idOrden, int idDivipolPDS) throws Exception {
        OrdenDao dao = new OrdenDao();
        return dao.listarDespachosOrdenPDS(idOrden, idDivipolPDS);
    }

    public List<Empleado> listarPersonasDisponibles(Long idOrden, int idDivipolPDS, String codigoCargo) throws Exception {

        OrdenDao dao = new OrdenDao();

        Orden orden = dao.getById(idOrden.toString());

        return dao.listarPersonasDisponiblesParaAsignarOrden(orden.getCodigoEvento(), codigoCargo, idDivipolPDS);
    }

    public String actualizarNrodocDetalleSuplencia(Long nrodoc, long id) throws Exception {
        JSONObject objJson = (JSONObject) new JSONObject();

        String resultado = "false";
        OrdenDao dao = new OrdenDao();
        if (dao.actualizaNrodocEstadoDetalleDespacho(nrodoc, id)) {
            resultado = "true";
        }
        objJson.put("respuesta", resultado);
        return objJson.toString();

    }

    public String quitarPersonaAsignadaSuplencia(long nrodoc, long idOrden, long idDetalle, long codigoCargo) throws Exception {

        JSONObject objJson = (JSONObject) new JSONObject();
        String resultado = "false";

        int idEmpleado = new EmpleadoDao().GetIdByNumeroDocumento(nrodoc);
        OrdenDao dao = new OrdenDao();
        Orden orden = dao.buscarOrdenPorId(idOrden);

        AsistenciaDao asistencia = new AsistenciaDao();
        // consulto el detalle
        DespachoDetalleRespuesta detalleSup = dao.getDetalleDespacho(idDetalle);

        // obtengo cuantos detalles tiene la orden
        List<DetallesOrdenGeneral> detalles = dao.obtieneDetallesGenerales(idOrden);

        //Elimino el detalle suplencia
        if (dao.eliminaDetalleDespacho(idDetalle)) {
            // elimino la asistencia de la persona
            boolean asistio=asistencia.EliminarAsistencia(idEmpleado, orden.getCodigoEvento().intValue(), detalleSup.getIdDivipolPds().intValue());
            resultado = "true";

            // Se consulta si hay personas disponibles para asignarse a esa orden
            List<PersonalOrdenSuplencia> personal = dao.personalSuplencia(orden.getIdDivipolStio(), orden.getCodigoEvento(), codigoCargo, 1);
            // Si no hay personas disponibles en el PDS, se valida la cantidad de personas requeridas para validar si se cambia el estado
            // de la orden por incompleta o vac??a

            dao.despachaDetalle(personal, detalleSup.getIdOrdenDetalle(), "", "", "") ;

            if (personal.isEmpty()) {
                if (detalles.size() > 1) {
                    dao.cambiaEstadoOrden(6, idOrden);
                } else {
                    dao.cambiaEstadoOrden(7, idOrden);
                }
            }

        }

        objJson.put("respuesta", resultado);
        return objJson.toString();
    }

}
