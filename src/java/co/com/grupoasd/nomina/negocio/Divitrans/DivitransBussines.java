/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.negocio.Divitrans;

import co.com.grupoasd.nomina.dao.DivitransDao;
import co.com.grupoasd.nomina.dao.PruebaDao;
import co.com.grupoasd.nomina.modelo.Divitrans;
import co.com.grupoasd.nomina.modelo.Empleado;
import co.com.grupoasd.nomina.modelo.EstadoDivitrans;
import co.com.grupoasd.nomina.modelo.Prueba;
import co.com.grupoasd.nomina.modelo.Sitio;
import co.com.grupoasd.nomina.modelo.wrapper.DivitransConsultaAdmin;
import co.com.grupoasd.nomina.modelo.wrapper.NombramientoCargo;
import co.com.grupoasd.nomina.negocio.sitio.SitioBusiness;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wilfer
 */
public class DivitransBussines implements IDivitrans {

    /**
     * Nombres de columnas para realizar el cvs de los viaticos
     */
    public static final String COLUMN_NAMES;
    public static final String LINE_FEED = "\n";
    public static final String COLUMN_SEPARATOR = ";";
    public static final String QUOTE_CHARACTER = "\"";
    public static final int ESTADO_DIVITRANS_PARA_APROBAR = 2;
    public static final String TIPO_DOC_CC = "CC";
    public static final String TIPO_DOC_CE = "CE";
    public static final String TIPO_DOC_NIT = "NIT";

    /**
     * Bloque estatico para inicializar el nombre de las columnas del archivo de
     * export Viaticos
     */
    static {
        StringBuilder columnNames = new StringBuilder();
        columnNames.append("Identificación;Tipo Id;Digito V;Apellidos y Nombres;Forma Pago;");
        columnNames.append("Banco;Tipo Cuenta;No Cuenta;Cod Oficina;Año;Mes;Dia;Valor a Pagar;");
        columnNames.append("Concepto1;Departamento;Municipio;Código Sitio;Nombre Sitio;Nombre Cargo;");
        columnNames.append("Valor Municipal;Valor Fluvial;Valor Rural;Valor Tracción Animal;");
        columnNames.append("Número dias transporte interno;Valor Interno;Valor Aereo;Pernocta;Número dias;");
        columnNames.append("Valor Día;Valor Total;Estado;Retención_Fuente;Rete_Ica;Fecha Pago;");
        columnNames.append("Referencia Pago;Valor Pago;Banco Pago;Medio de Pago");
        COLUMN_NAMES = columnNames.toString();
    }

    /**
     *
     * @param idPrueba
     * @return
     */
    @Override
    public List<Divitrans> listarDivitrans(int idPrueba) {
        DivitransDao ListaDivitrans = new DivitransDao();
        List<Divitrans> listado = ListaDivitrans.listarDivitrans(idPrueba);
        return listado;
    }

    /**
     *
     * @param idPruebaOrigen
     * @param idPrueba
     * @return
     */
    @Override
    public String DuplicarDivitrans(int idPruebaOrigen, int idPrueba) {
        DivitransDao ListaDivitrans = new DivitransDao();
        String listado = ListaDivitrans.DuplicarDivitrans(idPruebaOrigen, idPrueba);
        return listado;
    }

    /**
     * Metodo encargado de consultar la tabla Divitrans por los filtros
     * establecidos en la administracion de viaticos
     *
     * @param idDepartamento
     * @param idMunicipio
     * @param nroDocumento
     * @param codigoSitio
     * @param idPrueba
     * @param idEstado
     * @param codigoCargo
     * @return
     */
    @Override
    public List<DivitransConsultaAdmin> consultarPorFiltros(int idDepartamento, int idMunicipio,
            double nroDocumento, long codigoSitio, int idPrueba, int idEstado, int codigoCargo) {
        DivitransDao divitransDao = new DivitransDao();
        return divitransDao.consultarPorFiltros(idDepartamento, idMunicipio, nroDocumento,
                codigoSitio, idPrueba, idEstado, codigoCargo);
    }

    /**
     * Metodo para obtener la informacion de los valores para un registro
     * Divitrans
     *
     * @param idDivitrans
     * @return
     */
    @Override
    public DivitransConsultaAdmin getValoresViaticoById(int idDivitrans) {
        DivitransDao divitransDao = new DivitransDao();
        return divitransDao.getValoresViaticoById(idDivitrans);
    }

    /**
     * Metodo encargado de actualizar los valores de la tabla divitrans
     *
     * @param divitrans
     * @return
     */
    @Override
    public boolean actualizarValoresDivitrans(DivitransConsultaAdmin divitrans) {
        DivitransDao divitransDao = new DivitransDao();
        return divitransDao.actualizarValoresDivitrans(divitrans);
    }

    /**
     * Metodo encargao de invocar al Dao para obtener las frecuencia y tiempo
     * del viatico
     *
     * @param divitrans
     * @return
     */
    @Override
    public DivitransConsultaAdmin getFrecuenciaTiempoViaticoById(int divitrans) {
        DivitransDao divitransDao = new DivitransDao();
        return divitransDao.getFrecuenciaTiempoViaticoById(divitrans);
    }

    /**
     * Metodo que realiza el llamado al Dao para realizar la actualización de
     * los tiempos y frecuencias de la tabla divitrans
     *
     * @param divitrans
     * @return
     */
    @Override
    public boolean actualizarFrecuenciaTiempoDivitrans(DivitransConsultaAdmin divitrans) {
        DivitransDao divitransDao = new DivitransDao();
        return divitransDao.actualizarFrecuenciaTiempoDivitrans(divitrans);
    }

    /**
     * Metodo encargado de invocar al Dao para listar los estados de divitrans
     *
     * @return
     */
    @Override
    public List<EstadoDivitrans> listarEstadosDivitrans() {
        DivitransDao divitransDao = new DivitransDao();
        return divitransDao.listarEstadosDivitrans();
    }

    /**
     * Metodo encargado de generar el archivo para exportar los viaticos segun
     * la prueba a la que ingresa el usuario departamento,municipio,
     * nroDocumento,codigoSitio, idPrueba,idEstado, codigoCargo
     *
     *
     * @param departamento
     * @param municipio
     * @param nroDocumento
     * @param codigoCargo
     * @param codigoSitio
     * @param idEstado
     * @param idPrueba
     * @return
     */
    @Override
    public File generarArchivoViaticos(int departamento, int municipio, double nroDocumento,
            long codigoSitio, int idPrueba, int idEstado, int codigoCargo) {
        try {
            DivitransDao divitransDao = new DivitransDao();
            PruebaDao pruebaDao = new PruebaDao();
            List<DivitransConsultaAdmin> consulta = divitransDao.consultarPorFiltros(departamento, municipio, nroDocumento, codigoSitio, idPrueba, idEstado, codigoCargo);
            Prueba prueba = pruebaDao.getById(idPrueba);
            Calendar fechaMaximaViaticos = calcularFechaMaxima(prueba);
            File archivo = new File("fileTemp");
            try (FileWriter writer = new FileWriter(archivo)) {
                writer.append(COLUMN_NAMES).append(LINE_FEED);
                for (DivitransConsultaAdmin divitrans : consulta) {
                    writer.append(generateRow(divitrans, fechaMaximaViaticos)).append(LINE_FEED);
                }
            }
            return archivo;
        } catch (IOException ex) {
            Logger.getLogger(DivitransBussines.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Metodo encargado de generar una linea dentro del archivo CVS a exportar
     *
     * @param divitrans
     *
     * Identificación;Tipo Id;Digito V;Apellidos y Nombres;Forma Pago;Banco;Tipo
     * Cuenta;No Cuenta; Cod Oficina;Anio;Mes;Dia;Valor a
     * Pagar;Concepto1;Departamento;Municipio;Código Sitio;Nombre Sitio; Nombre
     * Cargo;Valor Municipal;Valor Fluvial;Valor Rural;Valor Tracción
     * Animal;Número dias transporte interno; Valor Interno;Valor
     * Aereo;Pernocta;Número dias;Valor Día;Valor
     * Total;Estado;Retención_Fuente;Rete_Ica; Fecha Pago;Referencia Pago;Valor
     * Pago;Banco Pago;Medio de Pago
     * @return
     */
    private CharSequence generateRow(DivitransConsultaAdmin divitrans, Calendar fechaMaximaPago) {
        StringBuilder row = new StringBuilder();
        row.append(QUOTE_CHARACTER).append(divitrans.getEmpleadoNombramiento().getNrodoc()).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
        row.append(QUOTE_CHARACTER).append(obtenerCodigoTipoIdentificacion(divitrans.getEmpleadoNombramiento().getTipodoc())).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
        row.append(QUOTE_CHARACTER).append(0).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);//Digito Verificacion
        row.append(QUOTE_CHARACTER).append(validarNulo(divitrans.getNombreEmpleadoNombramiento())).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
        row.append(QUOTE_CHARACTER).append(divitrans.getIdMedioPago()).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);//Forma Pago
        row.append(QUOTE_CHARACTER).append(0).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);//Banco
        row.append(QUOTE_CHARACTER).append(0).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);//Tipo Cuenta
        row.append(QUOTE_CHARACTER).append(0).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);//Nro Cuenta
        row.append(QUOTE_CHARACTER).append(0).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);//Cod Oficina
        row.append(QUOTE_CHARACTER).append(fechaMaximaPago.get(Calendar.YEAR)).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);//Anio
        row.append(QUOTE_CHARACTER).append((fechaMaximaPago.get(Calendar.MONTH) + 1)).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);//Mes
        row.append(QUOTE_CHARACTER).append(fechaMaximaPago.get(Calendar.DAY_OF_MONTH)).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);//dia
        row.append(QUOTE_CHARACTER).append(divitrans.getValorTotal().longValue()).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
        row.append(QUOTE_CHARACTER).append(0).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);//Concepto 1
        row.append(QUOTE_CHARACTER).append(divitrans.getDepartamento_destino()).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
        row.append(QUOTE_CHARACTER).append(divitrans.getMunicipio_destino()).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
        row.append(QUOTE_CHARACTER).append(divitrans.getCodigoSitio()).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
        row.append(QUOTE_CHARACTER).append(divitrans.getNombreSitio()).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
        row.append(QUOTE_CHARACTER).append(divitrans.getNombreCargo()).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
        row.append(QUOTE_CHARACTER).append(divitrans.getVr_municipal().longValue()).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
        row.append(QUOTE_CHARACTER).append(divitrans.getVr_fluvial().longValue()).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
        row.append(QUOTE_CHARACTER).append(divitrans.getVr_rural().longValue()).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
        row.append(QUOTE_CHARACTER).append(divitrans.getVr_traccion_animal().longValue()).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
        row.append(QUOTE_CHARACTER).append(divitrans.getNdias_trans_interno().longValue()).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
        row.append(QUOTE_CHARACTER).append(divitrans.getVr_interno().longValue()).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
        row.append(QUOTE_CHARACTER).append(divitrans.getVr_aereo().longValue()).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
        row.append(QUOTE_CHARACTER).append(divitrans.getPernocta() > 0 ? "SI" : "NO").append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
        row.append(QUOTE_CHARACTER).append(divitrans.getNdias().longValue()).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
        row.append(QUOTE_CHARACTER).append(divitrans.getVr_dia().longValue()).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
        row.append(QUOTE_CHARACTER).append(divitrans.getValorTotal().longValue()).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
        row.append(QUOTE_CHARACTER).append(divitrans.getEstado().getDescripcion()).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
        row.append(QUOTE_CHARACTER).append(0).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);//Retencion Fuente
        row.append(QUOTE_CHARACTER).append(0).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);//ReteIca
        row.append(QUOTE_CHARACTER).append(0).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);//Fecha Pago
        row.append(QUOTE_CHARACTER).append(0).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);//Referencia Pago
        row.append(QUOTE_CHARACTER).append(0).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);//Valor pago
        row.append(QUOTE_CHARACTER).append(0).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);//Banco Pago
        row.append(QUOTE_CHARACTER).append(0).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);//Medio Pago
        return row.toString();
    }

    /**
     * Metodo para invocar al Dao que realiza la actualizacion de los estados de
     * los divitrans recibidos
     *
     * @param listaParaAprobar
     * @param estado
     */
    @Override
    public String cambiarEstadoViaticos(List<Integer> listaParaAprobar, int estado) {
        DivitransDao divitransDao = new DivitransDao();
        return divitransDao.cambiarEstadoViaticos(listaParaAprobar, estado);
    }

    /**
     * Metodo encargado de invocar al DAO para realizar la actualizacion del
     * estado a APROBADO
     *
     * @param idDivitrans
     * @return
     */
    @Override
    public boolean aprobar(int idDivitrans) {
        DivitransDao divitransDao = new DivitransDao();
        return divitransDao.aprobar(idDivitrans);
    }

    /**
     * Metodo encargado de invocar al DAO para consultar los registros divitrans
     * para aprobar segun los filtros ingresados
     *
     * @param idDepartamento
     * @param idMunicipio
     * @param nroDocumentoEmpleadoNombra
     * @param codigoSitio
     * @param idPrueba
     * @param idEstadoDivitrans
     * @param idCargo
     * @param nroDocumentoSession
     * @return
     */
    @Override
    public List<DivitransConsultaAdmin> consultarAprobarPorFiltros(int idDepartamento,
            int idMunicipio, double nroDocumentoEmpleadoNombra, long codigoSitio, int idPrueba,
            int idEstadoDivitrans, int idCargo, double nroDocumentoSession) {

        DivitransDao divitransDao = new DivitransDao();
        return divitransDao.consultarAprobarPorFiltros(idDepartamento, idMunicipio,
                nroDocumentoEmpleadoNombra, codigoSitio, idPrueba, idEstadoDivitrans,
                idCargo, nroDocumentoSession);
    }

    /**
     * Metodo encargado de generar el cambio de estado del viatico a POR
     * APROBAR, e insertar el log de reverso en la tabla
     *
     * @param divitrans
     * @return
     */
    @Override
    public boolean reversarAprobacionViatico(DivitransConsultaAdmin divitrans) {
        List<Integer> listaDivitrans = new ArrayList<>();
        listaDivitrans.add(divitrans.getId());
        String error = cambiarEstadoViaticos(listaDivitrans, ESTADO_DIVITRANS_PARA_APROBAR);
        if (error.isEmpty()) {
            DivitransDao divitransDao = new DivitransDao();
            divitransDao.generarLogReverso(divitrans);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo para generar el archivo plano de demo para descarga
     *
     * @return
     * @throws java.io.IOException
     */
    public File generarDemoPagoViaticos() throws IOException {
        String pathString = "/data/demoFiles/";
        String fileName = "demoPagoViaticos.csv";
        File path = new File(pathString);
        if (path.exists()) {
            File archivoDemo = new File(pathString.concat(fileName));
            if (archivoDemo.exists()) {
                Logger.getLogger(DivitransBussines.class.getName()).log(Level.INFO, "Archivo demo existente:{0}, {1}", new Object[]{pathString.concat(fileName), archivoDemo.exists()});
                return archivoDemo;
            } else {
                Logger.getLogger(DivitransBussines.class.getName()).log(Level.INFO, "Se construye archivo demo:{0}", pathString.concat(fileName));
                return crearArchivoDemo(pathString, fileName);
            }
        } else {
            Logger.getLogger(DivitransBussines.class.getName()).log(Level.INFO, "Se construye path y archivo demo:{0}", pathString.concat(fileName));
            path.mkdirs();
            return crearArchivoDemo(pathString, fileName);
        }
    }

    /**
     * Metodo para homologar los tipos de identificacion
     *
     * @param tipodoc
     * @return
     */
    private String obtenerCodigoTipoIdentificacion(String tipodoc) {
        if (tipodoc != null) {
            switch (tipodoc) {
                case TIPO_DOC_CC:
                    return "01";
                case TIPO_DOC_CE:
                    return "02";
                default:
                    return "";
            }
        } else {
            return "";
        }
    }

    /**
     * Metodo para devolver el valor vacio cuando es nulo el parametro
     *
     * @param dato
     * @return
     */
    private String validarNulo(String dato) {
        return (dato == null ? "" : dato);
    }

    /**
     * Metodo para calcular la fecha maxima de pago de viaticos
     *
     * @param prueba
     * @return
     */
    private Calendar calcularFechaMaxima(Prueba prueba) {
        Date fechaFinalAplicacion = prueba.getFechaaplicacion();
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fechaFinalAplicacion);
        calendario.set(Calendar.DAY_OF_MONTH, calendario.get(Calendar.DAY_OF_MONTH) - 1);
        return calendario;
    }

    /**
     * Metodo encargado de actualizar el viatico de un empleado segun su lugar
     * de residencia y el sitio de aplicacion de la prueba
     *
     * @param nombramiento
     * @param empleado
     * @param idPrueba
     * @return
     */
    public boolean actualizarViaticoNombramiento(NombramientoCargo nombramiento, Empleado empleado, int idPrueba) {
        Logger.getLogger(DivitransBussines.class.getName()).log(Level.INFO, "actualizando viatico para nombramiento:{0}", nombramiento.getId());
        DivitransDao divitransDao = new DivitransDao();
        SitioBusiness sitioNegocio = new SitioBusiness();
        //Se consulta el viatico segun el id de nombramiento
        Divitrans viaticoNombramiento = divitransDao.consultarViaticoNombramiento(nombramiento.getId());
        Logger.getLogger(DivitransBussines.class.getName()).log(Level.INFO, "viatico para nombramiento (idDivitrans):{0}", viaticoNombramiento.getId());
        //Se consulta el sitio del nombramiento
        Sitio sitioNombramiento = sitioNegocio.obtenerSitioPorId(nombramiento.getDivipol().getId());
        Logger.getLogger(DivitransBussines.class.getName()).log(Level.INFO, "sitio para nombramiento (idDivipol):{0}", nombramiento.getDivipol().getId());
        //Se valida si existe viatico para el nombramiento
        if (viaticoNombramiento.getId() > 0) {
            String divOrigen = viaticoNombramiento.getDiv_origen();
            Logger.getLogger(DivitransBussines.class.getName()).log(Level.INFO, "Municipio Origen:{0}", divOrigen);
            //Se valida si el empleado reside o no en el municipio del sitio de aplicacion
            if (divOrigen.equals(empleado.getMunicipioDane().getCodigoMunicipio())) {
                Logger.getLogger(DivitransBussines.class.getName()).log(Level.INFO, "Origen igual a la residencia empleado:{0} No se cambia viatico", empleado.getMunicipioDane().getCodigoMunicipio());
                return true;
            } else if (!divOrigen.equals(empleado.getMunicipioDane().getCodigoMunicipio())) {
                if (empleado.getMunicipioDane().getCodigoMunicipio().equals(sitioNombramiento.getMunicipio().getCodigoMunicipio())) {
                    //Codigo cuando el empleado vive en el mismo municipio del sitio de aplicacion
                    Logger.getLogger(DivitransBussines.class.getName()).log(Level.INFO, "Empleado reside en el mismo municipio del sitio de aplicacion");
                } else {
                    Logger.getLogger(DivitransBussines.class.getName()).log(Level.INFO, "Origen diferente a la residencia empleado:{0}", empleado.getMunicipioDane().getCodigoMunicipio());
                    Divitrans viaticoMaestro = divitransDao.buscarViaticoMaestro(empleado.getMunicipioDane().getCodigoMunicipio(),
                            sitioNombramiento.getMunicipio().getCodigoMunicipio());
                    Logger.getLogger(DivitransBussines.class.getName()).log(Level.INFO, "Buscando Viatico Maestro \n Viatico Encontrado:{0}", viaticoMaestro.getId());
                    reemplazarValoresViatico(viaticoNombramiento, viaticoMaestro);
                    //Se actualizan los valores del viatico segun el maestro encontrado para ese origen - destino
                    boolean valoresSuccess = divitransDao.actualizarValoresDivitrans(viaticoNombramiento);
                    Logger.getLogger(DivitransBussines.class.getName()).log(Level.INFO, "Valores Actualizados :{0}", valoresSuccess);
                    //Se actualizan las frecuencias y tiempos del viatico segun el maestro encontrado para ese origen - destino
                    boolean frecuenciaSuccess = divitransDao.actualizarFrecuenciaTiempoDivitrans(viaticoMaestro);
                    Logger.getLogger(DivitransBussines.class.getName()).log(Level.INFO, "Frecuencias y Tiempos Actualizados :{0}", frecuenciaSuccess);
                    //retorno
                    return (valoresSuccess && frecuenciaSuccess);
                }
            } else {
                Logger.getLogger(DivitransBussines.class.getName()).log(Level.INFO, "Error al encontrar ubicaciones de empleado y sitio");
            }
        } else {
            Logger.getLogger(DivitransBussines.class.getName()).log(Level.INFO, "Nombramiento no tiene asignado un viatico a la fecha");
        }
        return true;
    }

    /**
     * Metodo encargado de reemplazar los valores de un viatico con el de otro
     *
     * @param viaticoNombramiento
     * @param viaticoMaestro
     */
    private void reemplazarValoresViatico(Divitrans viaticoNombramiento, Divitrans viaticoMaestro) {
        viaticoNombramiento.setDiv_destino(viaticoMaestro.getDiv_destino());
        viaticoNombramiento.setDiv_origen(viaticoMaestro.getDiv_origen());
        viaticoNombramiento.setEstado(viaticoMaestro.getEstado());
        viaticoNombramiento.setFrecuencia_fluvial(viaticoMaestro.getFrecuencia_fluvial());
        viaticoNombramiento.setFrecuencia_intermunicipal(viaticoMaestro.getFrecuencia_intermunicipal());
        viaticoNombramiento.setFrecuencia_rural(viaticoMaestro.getFrecuencia_rural());
        viaticoNombramiento.setFrecuencia_traccion_animal(viaticoMaestro.getFrecuencia_traccion_animal());
        viaticoNombramiento.setFrecuencia_trans_interno(viaticoMaestro.getFrecuencia_trans_interno());
        viaticoNombramiento.setFrecuencia_vuelos(viaticoMaestro.getFrecuencia_vuelos());
        viaticoNombramiento.setIdprueba(viaticoMaestro.getIdprueba());
        viaticoNombramiento.setMunicipio_destino(viaticoMaestro.getMunicipio_destino());
        viaticoNombramiento.setMunicipio_origen(viaticoMaestro.getMunicipio_origen());
        viaticoNombramiento.setNdias(viaticoMaestro.getNdias());
        viaticoNombramiento.setNdias_trans_interno(viaticoMaestro.getNdias_trans_interno());
        viaticoNombramiento.setPernocta(viaticoMaestro.getPernocta());
        viaticoNombramiento.setTime_aereo(viaticoMaestro.getTime_aereo());
        viaticoNombramiento.setTime_fluvial(viaticoMaestro.getTime_fluvial());
        viaticoNombramiento.setTime_intermunicipal(viaticoMaestro.getTime_intermunicipal());
        viaticoNombramiento.setTime_interno(viaticoMaestro.getTime_interno());
        viaticoNombramiento.setTime_rural(viaticoMaestro.getTime_rural());
        viaticoNombramiento.setTime_traccion_animal(viaticoMaestro.getTime_traccion_animal());
        viaticoNombramiento.setTrans_aereo(viaticoMaestro.getTrans_aereo());
        viaticoNombramiento.setVr_aereo(viaticoMaestro.getVr_aereo());
        viaticoNombramiento.setVr_dia(viaticoMaestro.getVr_dia());
        viaticoNombramiento.setVr_fluvial(viaticoMaestro.getVr_fluvial());
        viaticoNombramiento.setVr_interno(viaticoMaestro.getVr_interno());
        viaticoNombramiento.setVr_municipal(viaticoMaestro.getVr_municipal());
        viaticoNombramiento.setVr_rural(viaticoMaestro.getVr_rural());
        viaticoNombramiento.setVr_traccion_animal(viaticoMaestro.getVr_traccion_animal());
    }

    /**
     * Metodo encargado de generar el archivo demo cuando este no este creado en
     * el servidor
     *
     * @param pathString
     * @param fileName
     * @return
     * @throws IOException
     */
    private File crearArchivoDemo(String pathString, String fileName) throws IOException {
        File archivoDemo = new File(pathString.concat(fileName));
        //Se construye el archivo
        try (FileWriter writer = new FileWriter(archivoDemo)) {
            //Se construyen los encabezados para el archivo demo
            StringBuilder encabezados = new StringBuilder();
            encabezados.append("Identificación;Tipo Id;Digito V;Apellidos y Nombres;Forma Pago;Banco;Tipo Cuenta;No Cuenta;");
            encabezados.append("Cod Oficina;Año;Mes;Dia;Valor a Pagar;Concepto1;Departamento;Municipio;Código Sitio;Nombre Sitio;Nombre Cargo;");
            encabezados.append("Valor Municipal;Valor Fluvial;Valor Rural;Valor Tracción Animal;Número dias transporte interno;Valor Interno;");
            encabezados.append("Valor Aereo;Pernocta;Número dias;Valor Día;Valor Total;Estado;Retención_Fuente;Rete_Ica;Fecha Pago;Referencia Pago;");
            encabezados.append("Valor Pago;Banco Pago;Medio de Pago");
            //Se construyen los valores de ejemplo para el archivo
            StringBuilder valoresEjemplo = new StringBuilder();
            valoresEjemplo.append("123123123;1;0;MARIA PAOLA SUAREZ DUARTE ;3;13;1;124124123;9999;2016;8;11;71000;0;");
            valoresEjemplo.append("VIATICOS PRUEBA 11A;NODO BOGOTA;6486;INSTITUCION EDUCATIVA  REPUBLICA DE COLOMBIA SEDE A;");
            valoresEjemplo.append("DACTILOS SUPLENTE ;10000;10000;10000;10000;1;10000;21000;NO;0;0;71000;APROBADO;1232;1123;");
            valoresEjemplo.append("16/07/21;123124-1231;71000;1;1");
            //Se escriben las cadenas de ejemplo en el archivo
            writer.append(encabezados.toString());
            writer.append(System.lineSeparator());
            writer.append(valoresEjemplo.toString());
            writer.append(System.lineSeparator());
        }
        return archivoDemo;
    }

}
