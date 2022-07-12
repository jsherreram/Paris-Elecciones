/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.negocio.Divitrans;

import co.com.grupoasd.nomina.modelo.Divitrans;
import co.com.grupoasd.nomina.modelo.EstadoDivitrans;
import co.com.grupoasd.nomina.modelo.wrapper.DivitransConsultaAdmin;
import java.io.File;
import java.util.List;

/**
 *
 * @author ASD
 */
public interface IDivitrans {

    /**
     * Metodo ecanrgado de listar los registros de la tabla divitrans
     *
     * @param idPrueba
     * @return
     */
    public List<Divitrans> listarDivitrans(int idPrueba);

    /**
     *
     * @param idPruebaOrigen
     * @param idPrueba
     * @return
     */
    public String DuplicarDivitrans(int idPruebaOrigen, int idPrueba);

    /**
     * Metodo para consultar la tabla divitrans segun los filtros ingresados
     *
     * @param idDepartamento
     * @param idMunicipio
     * @param nroDocumento
     * @param codigoSitio
     * @param idPrueba
     * @param estado
     * @param cargo
     * @return
     */
    public List<DivitransConsultaAdmin> consultarPorFiltros(int idDepartamento, int idMunicipio,
            double nroDocumento, long codigoSitio, int idPrueba,
            int estado, int cargo);

    /**
     * Metodo para obtener los valores de un registro especifico de la table
     * divitrans
     *
     * @param aInt
     * @return
     */
    public DivitransConsultaAdmin getValoresViaticoById(int aInt);

    /**
     * Metodo para actualizar los valores de un registro de la tabla divitrans
     *
     * @param divitrans
     * @return
     */
    public boolean actualizarValoresDivitrans(DivitransConsultaAdmin divitrans);

    /**
     * Metodo para obtener los valores de frecuencia y tiempo de un registro
     * especifico de la tabla divitrans
     *
     * @param aInt
     * @return
     */
    public DivitransConsultaAdmin getFrecuenciaTiempoViaticoById(int aInt);

    /**
     * Metodo para realizar la acutalizacion de los valores de frecuencia y
     * tiempo de un registro divitrans
     *
     * @param divitrans
     * @return
     */
    public boolean actualizarFrecuenciaTiempoDivitrans(DivitransConsultaAdmin divitrans);

    /**
     * Metodo para listar los estado de divitrans
     *
     * @return
     */
    public List<EstadoDivitrans> listarEstadosDivitrans();

    /**
     * Metodo encargado de cambiar el estado a los registros de divitrans
     * recibidos, segun el estado recibido
     *
     * @param lista
     * @param estado
     * @return
     */
    public String cambiarEstadoViaticos(List<Integer> lista, int estado);

    /**
     * Metodo encargado de realizar la actualizacion del estado a APROBADO del
     * registro divitrans recibido
     *
     * @param optInt
     * @return
     */
    public boolean aprobar(int optInt);

    /**
     * Metodo encargado de generar el archivo que contiene los registros de
     * divitrans de la prueba especifica
     *
     * departamento,municipio, nroDocumento,codigoSitio, idPrueba,idEstado,
     * codigoCargo
     *
     * @param optInt
     * @param optInt0
     * @param optDouble
     * @param codigoSitio
     * @param optInt2
     * @param optInt3
     * @param optInt4
     * @return
     */
    public File generarArchivoViaticos(int optInt, int optInt0, double optDouble,
            long codigoSitio, int optInt2, int optInt3, int optInt4);

    /**
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
    public List<DivitransConsultaAdmin> consultarAprobarPorFiltros(int idDepartamento,
            int idMunicipio, double nroDocumentoEmpleadoNombra, long codigoSitio, int idPrueba,
            int idEstadoDivitrans, int idCargo, double nroDocumentoSession);

    /**
     * Metodo encargado de reversar la aprobación de un viatico, actualiza el
     * estado de APROBADO o PAGADO a POR APROBAR y genera el log de reversion en
     * la tabla
     *
     * @param idDivitrans
     * @return
     */
    public boolean reversarAprobacionViatico(DivitransConsultaAdmin idDivitrans);
}
