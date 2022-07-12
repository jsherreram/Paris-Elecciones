package co.com.grupoasd.nomina.portal.helper;

import co.com.grupoasd.nomina.modelo.EmpleadoSesion;
import co.com.grupoasd.nomina.portal.model.Menu;
import co.com.grupoasd.nomina.portal.util.ConfigUtil;
import co.com.grupoasd.nomina.portal.util.LoginUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 2015-03-24 Creacion de la clase
 *
 * @author jccastellanos
 */
public class MenuHelper implements Serializable {

    private static final long serialVersionUID = 1L;

    private HttpServletRequest request;

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    // TODO jcastellanos Pasar esta logica a algo parametrizable
    // TODO FAIL croldan lo siento, antes de volverlo parametrizable toco machetearlo
    public List<Menu> getMenu() {
        String context = request.getContextPath();
        List<Menu> menu = new ArrayList<>();
        //Empieza cambio para menú por roles x prueba
        HttpSession sesion;
        EmpleadoSesion emp = new EmpleadoSesion();
        String rolActual = "";
        sesion = request.getSession();
        emp = (EmpleadoSesion) sesion.getAttribute("empleado");
        
        if(emp == null){
            System.out.println("emp nulo!");
        }    
        rolActual = emp.getRolActual();
        
        
        
        
        //TODO croldan ANTIPATRON, dejaré este código muerto solo por si las moscas...
        //if(request.isUserInRole(LoginUtil.ROL_ADMINISTRADOR)){   
        if(rolActual.equals(LoginUtil.ROL_ADMINISTRADOR)){
            menu.add(new Menu("Administracion de Sitios", context + "/panel/Sitios/main.jsp#/"));
            menu.add(new Menu("Administracion de Pruebas", context + "/panel/Prueba/main.jsp#/"));
            menu.add(new Menu("Administracioón de Cargos", context + "/panel/CrudCargos/main.jsp#/"));
            menu.add(new Menu("Cargue Cuadro Aplicacion", context + "/panel/CargaAplicacion/main.jsp#/"));
            menu.add(new Menu("Cargue Cuadro Capacitacion", context + "/panel/CargaCapacitacion/main.jsp#/"));
            menu.add(new Menu("Cargue Cuadro Salones", context + "/panel/CargaSalones/main.jsp#/"));
            
            menu.add(new Menu("Monitoreo Nombramiento Por Cargo", context + "/panel/MonitoreoNombramiento/main.jsp#/Consulta/N"));
            menu.add(new Menu("Monitoreo Nombramiento Por Sitio", context + "/panel/MonitoreoNombramiento/main.jsp#/Consulta/NT"));
            menu.add(new Menu("Monitoreo Por Estados", context + "/panel/MonitoreoEstados/main.jsp#/"));
            menu.add(new Menu("Monitoreo Asistencia por Cargo", context + "/panel/MonitoreoNombramiento/main.jsp#/Consulta/A")); 
            menu.add(new Menu("Monitoreo Asistencia Por Sitio", context + "/panel/MonitoreoNombramiento/main.jsp#/Consulta/AT"));
            menu.add(new Menu("Monitoreo Encuesta", context + "/panel/MonitoreoEncuesta/main.jsp#/"));
            menu.add(new Menu("Convocatoria Masiva ", context + "/panel/Convocatoria/main.jsp#/"));
            menu.add(new Menu("Aceptacion Convocatoria Masiva", context + "/panel/AceptacionConvocatoria/main.jsp#/"));
            menu.add(new Menu("Nombramiento Masivo", context + "/panel/NombramientoMasivo/main.jsp#/"));
            menu.add(new Menu("Nombramiento Sitio", context + "/panel/NombramientoSitioNodo/main.jsp#/"));
            menu.add(new Menu("Nombramiento Sesion", context + "/panel/NombramientoSitio/main.jsp#/"));
            menu.add(new Menu("Generar Archivo Nombramiento", context + "/panel/ReportarAsistencia/main.jsp#/"));
            menu.add(new Menu("Asistencia Y Entrega de informes Manual", context + "/panel/ReportarAsistenciaSitio/main.jsp#/"));
            menu.add(new Menu("Asistencia Por Cedula", context + "/panel/ReportarAsistenciaCedula/main.jsp#/"));
            menu.add(new Menu("Administrar Personal", context + "/panel/terceros/main.jsp#/"));
            menu.add(new Menu("Lista y Sticker Capacitacion", context + "/panel/ListaAsistenciaCapacitacion/main.jsp#/"));
            menu.add(new Menu("Lista Asitencia Por Sitios", context + "/panel/ListaAsistencia/main.jsp#/"));
            menu.add(new Menu("Contac Center", context + "/panel/ConsultarPersonalCall/main.jsp#/"));
            menu.add(new Menu("Enrolar y Firmar documentos", context + "/panel/ConfirmarFirma/main.jsp#/"));
            menu.add(new Menu("Carga Pagos de Viaticos", context + "/panel/CargaPagos/main.jsp#/"));
            menu.add(new Menu("Plan Suplencia", context + "/panel/Suplencias/main.jsp#/"));            
            menu.add(new Menu("Listado Soporte de Pago", context + "/panel/ArchivoPago/main.jsp#/general"));
            menu.add(new Menu("Listado Soporte de Asistencia", context + "/panel/ArchivoListadoAsistencia/main.jsp#/general"));
            
        }
        else if(rolActual.equals(LoginUtil.ROL_COORDINADOR)){
            menu.add(new Menu("Monitoreo Nombramiento Por Cargo", context + "/panel/MonitoreoNombramientoNodo/main.jsp#/Consulta/N"));
            menu.add(new Menu("Monitoreo Nombramiento Por Sitio", context + "/panel/MonitoreoNombramientoNodo/main.jsp#/Consulta/NT"));
            menu.add(new Menu("Monitoreo Asistencia por Cargo", context + "/panel/MonitoreoNombramientoNodo/main.jsp#/Consulta/A"));
            menu.add(new Menu("Monitoreo Asistencia Por Sitio", context + "/panel/MonitoreoNombramientoNodo/main.jsp#/Consulta/AT"));
            menu.add(new Menu("Nombramiento Sitio", context + "/panel/NombramientoSitioNodo/main.jsp#/"));
            menu.add(new Menu("Nombramiento Sesion", context + "/panel/NombramientoSitio/main.jsp#/"));
            menu.add(new Menu("Generar Archivo Nombramiento", context + "/panel/ReportarAsistencia/main.jsp#/"));
            menu.add(new Menu("Asistencia Y Entrega de informes Manual", context + "/panel/ReportarAsistenciaSitio/main.jsp#/"));
            menu.add(new Menu("Asistencia Biometrica", context + "/panel/tomaAsistencia/main.jsp#/"));
            menu.add(new Menu("Administrar Personal", context + "/panel/terceros/main.jsp#/"));
            menu.add(new Menu("Enrolar y Firmar documentos", context + "/panel/ConfirmarFirma/main.jsp#/"));
            menu.add(new Menu("Lista y Sticker Capacitacion", context + "/panel/ListaAsistenciaCapacitacion/main.jsp#/"));
            menu.add(new Menu("Lista Asitencia Por Sitios", context + "/panel/ListaAsistencia/main.jsp#/"));
            menu.add(new Menu("Personal Call", context + "/panel/ConsultarPersonalCall/main.jsp#/"));
            menu.add(new Menu("Autorizar Pago Viaticos", context + "/panel/GestionPagos/main.jsp#/AutorizaViaticos/"));
            menu.add(new Menu("Listado Soporte de Pago", context + "/panel/ArchivoPago/main.jsp#/general"));
            menu.add(new Menu("Listado Soporte de Asistencia", context + "/panel/ArchivoListadoAsistencia/main.jsp#/general"));
            menu.add(new Menu("Plan Suplencia", context + "/panel/Suplencias/main.jsp#/"));
        }   
        else if(rolActual.equals(LoginUtil.ROL_ADMIN_ICFES)) {
        //else if(request.isUserInRole(LoginUtil.ROL_ADMIN_ICFES)) {
            menu.add(new Menu("Nombramiento Por Cargo", context + "/panel/MonitoreoNombramiento/main.jsp#/Consulta/N"));
            menu.add(new Menu("Nombramiento Por Sitio", context + "/panel/MonitoreoNombramiento/main.jsp#/Consulta/NT"));
            menu.add(new Menu("Monitoreo Por Estados", context + "/panel/MonitoreoEstados/main.jsp#/"));
            menu.add(new Menu("Asistencia por Cargo", context + "/panel/MonitoreoNombramiento/main.jsp#/Consulta/A"));
            menu.add(new Menu("Asistencia Por Sitio", context + "/panel/MonitoreoNombramiento/main.jsp#/Consulta/AT"));
            menu.add(new Menu("Monitoreo Encuesta", context + "/panel/MonitoreoEncuesta/main.jsp#/"));

        }
        else if(rolActual.equals(LoginUtil.ROL_COORDINADOR_ICFES)) {
        //else if(request.isUserInRole(LoginUtil.ROL_COORDINADOR_ICFES)) {
            menu.add(new Menu("Nombramiento Por Cargo", context + "/panel/MonitoreoNombramientoNodo/main.jsp#/Consulta/N"));
            menu.add(new Menu("Nombramiento Por Sitio", context + "/panel/MonitoreoNombramientoNodo/main.jsp#/Consulta/NT"));
            menu.add(new Menu("Asistencia por Cargo", context + "/panel/MonitoreoNombramientoNodo/main.jsp#/Consulta/A"));
            menu.add(new Menu("Asistencia Por Sitio", context + "/panel/MonitoreoNombramientoNodo/main.jsp#/Consulta/AT"));
            menu.add(new Menu("Actualizar Mi Información", context + "/panel/EditarInformacionPersona/main.jsp#/"));
        }        
        else if(rolActual.equals(LoginUtil.ROL_EXAMINADOR)) {
        //else if(request.isUserInRole(LoginUtil.ROL_EXAMINADOR)) {                        
            menu.add(new Menu("Nombramiento", context + "/panel/NombramientoCoordinadorSitio/main.jsp#/"));
            menu.add(new Menu("Actualizar Mi Información", context + "/panel/EditarInformacionPersona/main.jsp#/"));
            menu.add(new Menu("Mis Pagos", context + "/panel/GestionPagos/main.jsp#/"));
            menu.add(new Menu("Medio de Pago del Sitio", context + "/panel/MedioPago/main.jsp#/"));
        } else if (request.isUserInRole(LoginUtil.ROL_CALL)) {
            menu.add(new Menu("Contac Center", context + "/panel/ConsultarPersonalCall/main.jsp#/"));
            menu.add(new Menu("Plan Suplencia Sitio", context + "/panel/SuplenciasSitio/main.jsp#/"));
            menu.add(new Menu("Aplicar Convocatorias Vigentes", context + "/panel/Convocatorias/main.jsp#/"));            
            menu.add(new Menu("Mis Pagos", context + "/panel/GestionPagos/main.jsp#/")); 
            menu.add(new Menu("Plan Suplencia", context + "/panel/Suplencias/main.jsp#/"));            
        }
        else if(rolActual.equals(LoginUtil.ROL_RECTOR)) {
        //else if(request.isUserInRole(LoginUtil.ROL_RECTOR)) {
            menu.add(new Menu("Actualizar Mi Información", context + "/panel/EditarInformacionPersona/main.jsp#/"));
            menu.add(new Menu("Aplicar Convocatorias Vigentes", context + "/panel/Convocatorias/main.jsp#/"));
            menu.add(new Menu("Mis Pagos", context + "/panel/GestionPagos/main.jsp#/"));
            menu.add(new Menu("Medio de Pago del Sitio", context + "/panel/MedioPago/main.jsp#/"));
        }
        else if(rolActual.equals(LoginUtil.ROL_CALL)) {
        //else if(request.isUserInRole(LoginUtil.ROL_CALL)) {
            menu.add(new Menu("Contac Center", context + "/panel/ConsultarPersonalCall/main.jsp#/"));
            menu.add(new Menu("Plan Suplencia Sitio", context + "/panel/Suplencias/main.jsp#/"));
            
        }
        
        else if(rolActual.equals(LoginUtil.ROL_DELEGADO)) {
        //else if(request.isUserInRole(LoginUtil.ROL_DELEGADO)) {
            menu.add(new Menu("Nombramiento", context + "/panel/NombramientoSitioNodo/main.jsp#/"));
            menu.add(new Menu("Administrar Personal", context + "/panel/terceros/main.jsp#/"));
            menu.add(new Menu("Generar Lista de Asistencia", context + "/panel/ListaAsistencia/main.jsp#/"));
            menu.add(new Menu("Diligenciar Encuesta", context + "/panel/ReportarEncuesta/main.jsp#/"));
            menu.add(new Menu("Tomar Asistencia Manual", context + "/panel/ReportarAsistenciaSitio/main.jsp#/"));
            menu.add(new Menu("Tomar Asistencia Biometrica", context + "/panel/tomaAsistencia/main.jsp#/"));
            menu.add(new Menu("Enrolar y Firmar documentos", context + "/panel/ConfirmarFirma/main.jsp#/"));
            menu.add(new Menu("Reporte Firma Contrato y Cuenta Cobro", context + "/panel/ConsultarFirmaEmpleados/main.jsp#/"));
            menu.add(new Menu("Actualizar Mi Información", context + "/panel/EditarInformacionPersona/main.jsp#/"));
            menu.add(new Menu("Aplicar Convocatorias Vigentes", context + "/panel/Convocatorias/main.jsp#/"));            
            menu.add(new Menu("Mis Pagos", context + "/panel/GestionPagos/main.jsp#/")); 
            menu.add(new Menu("Lista Asistencia", context + "/panel/ListaAsistencia/main.jsp#/"));
        }
        else if(rolActual.equals(LoginUtil.ROL_CONTABILIDAD)) {
        //else if(request.isUserInRole(LoginUtil.ROL_CONTABILIDAD)) {
            menu.add(new Menu("Generar Archivo Viaticos", context + "/panel/GestionPagos/main.jsp#/ArchivoViaticos/0"));
            menu.add(new Menu("Carga Pagos de Viaticos", context + "/panel/CargaPagos/main.jsp#/"));
            menu.add(new Menu("Monitoreo Asistencia por Cargo", context + "/panel/MonitoreoNombramiento/main.jsp#/Consulta/A")); 
            menu.add(new Menu("Monitoreo Asistencia Por Sitio", context + "/panel/MonitoreoNombramiento/main.jsp#/Consulta/AT"));
            menu.add(new Menu("Personal Call", context + "/panel/ConsultarPersonalCall/main.jsp#/"));
            menu.add(new Menu("Generar Archivo Nombramiento", context + "/panel/ReportarAsistencia/main.jsp#/"));
            menu.add(new Menu("Listado Soporte de Pago", context + "/panel/ArchivoPago/main.jsp#/consultar"));
            menu.add(new Menu("Configuración Medios de Pago", context + "/panel/ConfigurarMedioPago/main.jsp#/"));
        }

        
    //Rol Capacitador
        else if(rolActual.equals(LoginUtil.ROL_CAPACITADOR)) {
        //else if(request.isUserInRole(LoginUtil.ROL_CAPACITADOR)) {
            //menu.add(new Menu("Nombramiento", context + "/panel/NombramientoSitioNodo/main.jsp#/"));
            menu.add(new Menu("Nombramiento", context + "/panel/NombramientoSitio/main.jsp#/"));
            menu.add(new Menu("Administrar Personal", context + "/panel/terceros/main.jsp#/"));
            menu.add(new Menu("Asignar Salones", context + "/panel/NombramientoSalonSitio/main.jsp#/"));
            menu.add(new Menu("Generar Lista de Asistencia", context + "/panel/ListaAsistencia/main.jsp#/"));
            menu.add(new Menu("Diligenciar Encuesta", context + "/panel/ReportarEncuesta/main.jsp#/"));
            menu.add(new Menu("Tomar Asistencia Manual", context + "/panel/ReportarAsistenciaSitio/main.jsp#/"));
            menu.add(new Menu("Tomar Asistencia Biometrica", context + "/panel/tomaAsistencia/main.jsp#/"));
            menu.add(new Menu("Enrolar y Firmar documentos", context + "/panel/ConfirmarFirma/main.jsp#/"));
            menu.add(new Menu("Reporte Firma Contrato y Cuenta Cobro", context + "/panel/ConsultarFirmaEmpleados/main.jsp#/"));
            menu.add(new Menu("Actualizar Mi Información", context + "/panel/EditarInformacionPersona/main.jsp#/"));
            menu.add(new Menu("Mis Pagos", context + "/panel/GestionPagos/main.jsp#/"));
            menu.add(new Menu("Monitoreo Encuesta", context + "/panel/MonitoreoEncuesta/main.jsp#/"));
            menu.add(new Menu("Encuesta General", context + "/panel/ReportarEncuesta/main.jsp#/EncuestaGeneral"));
            menu.add(new Menu("Subir Archivo Pago", context + "/panel/ArchivoPago/main.jsp#/general"));
            menu.add(new Menu("Subir Archivo Asistencia", context + "/panel/ArchivoListadoAsistencia/main.jsp#/general"));

            menu.add(new Menu("Plan Suplencia Sitio", context + "/panel/SuplenciasSitio/main.jsp#/"));
            menu.add(new Menu("Listado Soporte de Pago", context + "/panel/ArchivoPago/main.jsp#/general"));
            menu.add(new Menu("Listado Soporte de Asistencia", context + "/panel/ArchivoListadoAsistencia/main.jsp#/general"));
        }
        
          //Rol Capacitador
        else if(rolActual.equals(LoginUtil.ROL_COORDINADOR_ICFES)) {
        //else if(request.isUserInRole(LoginUtil.ROL_COORDINADOR_PDS)) {
            menu.add(new Menu("Nombramiento", context + "/panel/NombramientoSitio/main.jsp#/"));
            menu.add(new Menu("Administrar Personal", context + "/panel/terceros/main.jsp#/"));
            menu.add(new Menu("Generar Lista de Asistencia", context + "/panel/ListaAsistencia/main.jsp#/"));
            menu.add(new Menu("Tomar Asistencia Manual", context + "/panel/ReportarAsistenciaSitio/main.jsp#/"));            
            menu.add(new Menu("Tomar Asistencia Biometrica", context + "/panel/tomaAsistencia/main.jsp#/"));
            menu.add(new Menu("Enrolar y Firmar documentos", context + "/panel/ConfirmarFirma/main.jsp#/"));
            menu.add(new Menu("Reporte Firma Contrato y Cuenta Cobro", context + "/panel/ConsultarFirmaEmpleados/main.jsp#/"));
            menu.add(new Menu("Plan Suplencia", context + "/panel/Suplencias/main.jsp#/"));
        }      //El asesor es el Representante de Asd en sitio
        else if(rolActual.equals(LoginUtil.ROL_ASESOR)) {
        //else if(request.isUserInRole(LoginUtil.ROL_ASESOR)) {
            menu.add(new Menu("RPS", context + "/panel/AdministraAsistencia/main.jsp#/"));
            //menu.add(new Menu("Nombramiento", context + "/panel/NombramientoSitio/main.jsp#/"));
            //menu.add(new Menu("Administrar Personal", context + "/panel/terceros/main.jsp#/"));
            //menu.add(new Menu("Asignar Salones", context + "/panel/NombramientoSalonSitio/main.jsp#/"));
            //menu.add(new Menu("Generar Lista de Asistencia", context + "/panel/ListaAsistencia/main.jsp#/"));
            //menu.add(new Menu("Diligenciar Encuesta", context + "/panel/ReportarEncuesta/main.jsp#/"));
            //menu.add(new Menu("Tomar Asistencia Manual", context + "/panel/ReportarAsistenciaSitio/main.jsp#/"));            
            //menu.add(new Menu("Tomar Asistencia Biometrica", context + "/panel/tomaAsistencia/main.jsp#/"));
            //menu.add(new Menu("Enrolar y Firmar documentos", context + "/panel/ConfirmarFirma/main.jsp#/"));
            //menu.add(new Menu("Reporte Firma Contrato y Cuenta Cobro", context + "/panel/ConsultarFirmaEmpleados/main.jsp#/"));
            //menu.add(new Menu("Actualizar Mi Información", context + "/panel/EditarInformacionPersona/main.jsp#/"));
            //menu.add(new Menu("Mis Pagos", context + "/panel/GestionPagos/main.jsp#/")); 
            //menu.add(new Menu("Plan Suplencia Sitio", context + "/panel/SuplenciasSitio/main.jsp#/"));
            //menu.add(new Menu("Listado Soporte de Pago", context + "/panel/ArchivoPago/main.jsp#/general"));
            //menu.add(new Menu("Listado Soporte de Asistencia", context + "/panel/ArchivoListadoAsistencia/main.jsp#/general"));
        }
        else if(rolActual.equals(LoginUtil.ROL_COORDINADOR_ICFES)) {
            menu.add(new Menu("Monitoreo Asistencia por Cargo", context + "/panel/MonitoreoNombramiento/main.jsp#/Consulta/A")); 
        }
 
        return menu;
    }

}
        

            
        
   
