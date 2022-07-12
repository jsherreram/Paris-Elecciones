/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.negocio.pagos;

import java.io.File;
import java.util.List;

/**
 *
 * @author Pedro Rodríguez
 */
public interface IPagosBusiness {

    /**
     * @param idSitio
     * @param idPrueba
     */
    public File generaArchivoPagos(String codigoDepartamento, String nombreDepartamento, int idPrueba);

}
