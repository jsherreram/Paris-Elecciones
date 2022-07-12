/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.negocio.nombramientomasivo;

import co.com.grupoasd.nomina.common.util.Constants;
import co.com.grupoasd.nomina.conexion.OperacionesReporte;
import co.com.grupoasd.nomina.dao.AsistenciaDao;
import co.com.grupoasd.nomina.dao.DetalleCargoPuestoEventoDao;
import co.com.grupoasd.nomina.dao.EmpleadoDao;
import co.com.grupoasd.nomina.dao.EmpleadoPruebaEstadoDao;
import co.com.grupoasd.nomina.dao.NombramientoDao;
import co.com.grupoasd.nomina.dao.PersonaAsignadaDao;
import co.com.grupoasd.nomina.dao.SitioDao;
import co.com.grupoasd.nomina.dto.BusquedaPersonasDto;
import co.com.grupoasd.nomina.modelo.DetalleCargoPuestoEvento;
import co.com.grupoasd.nomina.modelo.Empleado;
import co.com.grupoasd.nomina.modelo.EmpleadoPruebaEstado;
import co.com.grupoasd.nomina.modelo.Estado;
import co.com.grupoasd.nomina.modelo.Nombramiento;
import co.com.grupoasd.nomina.modelo.PersonaAsignada;
import co.com.grupoasd.nomina.modelo.Sitio;
import co.com.grupoasd.nomina.modelo.wrapper.EmpleadoPruebaEstadoCargo;
import co.com.grupoasd.nomina.modelo.wrapper.NombramientoCargo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRStyledText;
import org.json.simple.JSONObject;

/**
 *
 * @author ASD
 */
public class NombramientoController {

    public static final int ESTADO_EMPLEADO_PRUEBA_PREINSCRITO = 7;

    public NombramientoController() {
    }

    public String validarSalonesAsignadosaCoordinadores(int idPrueba, String codigoSitio) {

        JSONObject objJson = (JSONObject) new JSONObject();
        String resp = "true";
        List<Nombramiento> nombramientos = new NombramientoDao().listarNombramientoSitioCargo(idPrueba, codigoSitio);

        for (Nombramiento nomb : nombramientos) {
            if (nomb.getEmpleado().getIdEmpleado() != 0 && (nomb.getSalon() == null || nomb.getSalon().isEmpty())) {
                resp = "false";
            }
        }
        objJson.put("respuesta", resp);
        return objJson.toJSONString();
    }

    /**
     * Metodo de negocio de invoca al dao para obtener los nombramientos por
     * cargo segun los parametros ingresados
     *
     * @param idPrueba
     * @param codigoDepartamento
     * @param codigoCargo
     * @param codigoMunicipio
     * @param idZona
     * @param cantidadSalones
     * @return
     */
    public List<NombramientoCargo> getNombramientoCargoFiltros(int idPrueba, int codigoDepartamento,
            int codigoCargo, int codigoMunicipio, int idZona, int cantidadSalones) {
        NombramientoDao dao = new NombramientoDao();
        return dao.getNombramientoCargoFiltros(idPrueba, codigoDepartamento,
                codigoCargo, codigoMunicipio, idZona, cantidadSalones);
    }

    /**
     * Metodo que valida el empleado para realizar la asignacion al nombramiento
     *
     * @param nombramiento
     * @return
     */
    public Constants.Errors validarEmpleadoPrueba(NombramientoCargo nombramiento) {
        EmpleadoDao empleadoDao = new EmpleadoDao();
        int idEmpleado = empleadoDao.GetIdByNumeroDocumento(nombramiento.getEmpleado().getNrodoc());

        if (idEmpleado > 0) {
            nombramiento.getEmpleado().setIdEmpleado(idEmpleado);
            EmpleadoPruebaEstadoDao empleadoPruebaEstadoDao = new EmpleadoPruebaEstadoDao();
            EmpleadoPruebaEstado empleadoEnPrueba = empleadoPruebaEstadoDao.getEmpleadoEnPruebaNivelCargo(idEmpleado,
                    nombramiento.getIdPrueba(), nombramiento.getEmpleado().getCargoobj().getNivel_cargo());

            if (empleadoEnPrueba.getIdEmpleado() > 0 && empleadoEnPrueba.getIdestpersona() == 1) {
                //Si el empleado ya esta asignado devuelve el error correspondiente
                return Constants.Errors.ERROR_USUARIO_YA_ASIGNADO;
            } else if (empleadoEnPrueba.getIdEmpleado() > 0 && empleadoEnPrueba.getIdestpersona() == 7) {
                //Si el empleado es valido y esta pre inscrito se retorna al metodo padre sin errores
                return null;
            } else if (empleadoEnPrueba.getIdEmpleado() > 0 && empleadoEnPrueba.getIdestpersona() != 7) {
                //Si el usuario no esta en estado preinscrito
                return Constants.Errors.ERROR_USUARIO_NO_PREINSCRITO;
            } else {
                return Constants.Errors.ERROR_USUARIO_NO_EXISTE;
            }

        } else {
            return Constants.Errors.ERROR_USUARIO_NO_EXISTE;
        }
    }

    /**
     * Metodo encargado de buscar las personas para la asignacion del
     * nombramiento
     *
     * @param dtoFiltros
     * @return
     */
    public List<EmpleadoPruebaEstadoCargo> buscarPersonasNombramiento(BusquedaPersonasDto dtoFiltros) {
        EmpleadoPruebaEstadoDao empleadoPruebadao = new EmpleadoPruebaEstadoDao();
        SitioDao sitioDao = new SitioDao();
        Sitio sitio = sitioDao.BuscarSitioPorId(dtoFiltros.getSitio().getId());
        dtoFiltros.setSitio(sitio);
        List<EmpleadoPruebaEstadoCargo> listaPersonas = empleadoPruebadao.buscarPersonasParaAsignar(dtoFiltros);
        return listaPersonas;
    }

    /**
     * Metodo para hallar la distancia entre el sitio y la persona en kilometros
     *
     * @param longSitio
     * @param latSitio
     * @param longPersona
     * @param latPersona
     * @return
     */
    public static double distanciaCoord(float longSitio, float latSitio, float longPersona, float latPersona) {
        //double radioTierra = 3958.75;//en millas
        double radioTierra = 6371;//en kilómetros
        double dLat = Math.toRadians(latPersona - latSitio);
        double dLng = Math.toRadians(longPersona - longSitio);
        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);
        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(latSitio)) * Math.cos(Math.toRadians(latPersona));
        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));
        double distancia = radioTierra * va2;
        return distancia;
    }

    /**
     * Metodo encargado de obtener el nombramiento por id
     *
     * @param id
     * @return
     */
    public NombramientoCargo getNombramientoCargoById(int id) {
        NombramientoDao nombramientoDao = new NombramientoDao();
        return nombramientoDao.getNombramientoCargoById(id);
    }

    /**
     *
     * @param documentoTitular
     * @param documentoSuplente
     * @param idPrueba
     * @param idEmpleado
     * @param idDivipol
     * @param codigoEvento
     * @param idDcpe
     * @param usuario
     * @return
     */
    public String cambiarNombramiento(long documentoTitular, long documentoSuplente, int idPrueba,
            int idEmpleado, int idDivipol, int codigoEvento, int idDcpe, String usuario) {

        //Consultar la persona asignada en la dcpe
        PersonaAsignada personaAsignada = new PersonaAsignadaDao().getById(idDcpe);

        NombramientoDao nombramientoDao = new NombramientoDao();

        Nombramiento titular;

        //en caso que el documento del titular sea diferente de 0
        if (documentoTitular > 0) {
            titular = nombramientoDao.getNombramiento(idPrueba, documentoTitular);
        } else {
            titular = nombramientoDao.getIdNombramiento(idPrueba, personaAsignada.getUbicacion(), personaAsignada.getCargo().getCodigoCargo(), personaAsignada.getOrden());
            Empleado empleado = new Empleado();
            titular.setEmpleado(empleado);
            Estado estado = new Estado();
            estado.setCodigoEstado(1);
            titular.setEstado(estado);
        }

        Nombramiento suplente = nombramientoDao.getNombramiento(idPrueba, documentoSuplente);

        //Actualizamos el nrodoc Empleado 0 en el nombramiento
        Empleado s = suplente.getEmpleado();
        s.setNrodoc(0);
        suplente.setEmpleado(s);
        suplente.setUsuario(usuario);

        //Actualizamos el nrodoc Empleado por el nrodoc del suplente en el nombramiento
        Empleado t = titular.getEmpleado();
        t.setNrodoc(documentoSuplente);
        titular.setEmpleado(t);
        titular.setUsuario(usuario);

        boolean rta = false;
        if (nombramientoDao.actualizar(suplente)) {
            rta = nombramientoDao.actualizar(titular);
        }
        if (rta) {
            AsistenciaDao dao = new AsistenciaDao();
            dao.actualizarDivipolAsistencia(idEmpleado, codigoEvento, idDivipol);
        }

        JSONObject json = (JSONObject) new JSONObject();
        json.put("respuesta", rta);

        return json.toJSONString();

    }

    /**
     * Metodo encargado de validar la asignacion para el empleado al evento
     *
     * @param documento
     * @param idDcpe
     * @param idPrueba
     * @return
     */
    public Constants.Errors asignacionPersonaEvento(long documento, long idDcpe, int idPrueba) {
        DetalleCargoPuestoEventoDao dcpeDao = new DetalleCargoPuestoEventoDao();
        //Se valida si el detalle_cargo_x_puesto_x_evento ya esta asignado a otra persona
        DetalleCargoPuestoEvento dcpe = dcpeDao.getById(idDcpe, idPrueba);
        if (dcpe.getEmpleado().getNrodoc() > 0L && dcpe.getEmpleado().getNrodoc() != documento) {
            return Constants.Errors.ERROR_EVENTO_YA_ASIGNADO;
        } else if (dcpe.getEmpleado().getNrodoc() > 0L && dcpe.getEmpleado().getNrodoc() == documento) {
            return null;
        }

        //Se valida que el empleado no esta asignado a un detalle_cargo_x_puesto_x_evento
        DetalleCargoPuestoEvento detalleEmpleado = dcpeDao.getByDocumentoAndEvento(documento, idDcpe, idPrueba);
        if (detalleEmpleado.getId() > 0) {
            return Constants.Errors.ERROR_USUARIO_YA_ASIGNADO_EVENTO;
        }

        //Se valida que exista el empleado
        EmpleadoDao empleadoDao = new EmpleadoDao();
        int idEmpleado = empleadoDao.GetIdByNumeroDocumento(documento);
        if (idEmpleado <= 0) {
            return Constants.Errors.ERROR_USUARIO_NO_EXISTE;
        }

        //Se valida que el empleado este en estado preinscrito
        EmpleadoPruebaEstadoDao epsDao = new EmpleadoPruebaEstadoDao();
        EmpleadoPruebaEstado epsEmpleado = epsDao.getByDocumento(documento, idPrueba, null);
        if (epsEmpleado.getIdestpersona() != ESTADO_EMPLEADO_PRUEBA_PREINSCRITO) {
            return Constants.Errors.ERROR_USUARIO_NO_PREINSCRITO;
        } else if (dcpe.getCargo().getNivel_cargo() != epsEmpleado.getCargoObj().getNivel_cargo()) {
            return Constants.Errors.ERROR_NIVEL_CARGO_INVALIDO;
        }

        //Se procede a actualizar el registro de detalle_cargo_x_puesto_x_evento
        boolean successUpdate = dcpeDao.asignarDetalleCargoEvento(documento, idDcpe);
        if (successUpdate) {
            return null;
        } else {
            return Constants.Errors.ERROR_INTERNO;
        }
    }

    /**
     * Metodo encargado de generar el reporte de boletos de suplencia
     *
     * @param idPrueba
     * @param codigoEvento
     * @return
     */
    public File generarBoletosSuplencia(int idPrueba, String codigoEvento) {
        File reporte = null;
        try {
            Map parameters = new HashMap();
            parameters.put("codigoEvento", codigoEvento);
            parameters.put("idPrueba", idPrueba);
            JasperReport jasperReport = JasperCompileManager.compileReport("/data/report/boletosSuplencia.jrxml");
            jasperReport.setProperty(JRStyledText.PROPERTY_AWT_IGNORE_MISSING_FONT, "true");
            byte[] bytes = OperacionesReporte.ejecutar(jasperReport, parameters);
            reporte = new File("reporte");
            FileOutputStream fos = new FileOutputStream(reporte);
            fos.write(bytes);
        } catch (JRException ex) {
            Logger.getLogger(NombramientoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NombramientoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NombramientoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reporte;
    }

}
