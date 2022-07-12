/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.grupoasd.nomina.negocio.mediopago;

import java.util.List;

/**
 *
 * @author ASD
 */
public interface ICoberturaMedioPagoController {
    
     void cargarFileCobertura(String nombreArchivoOrigen, String pathArchivo, int codigoPrueba, String user, int codigoMedioPago);
     
     void GeneraArchivoErrores(int idStatusCargue);
     
     String listarMediosMunicipioJson(String codMunicipio, int medioPagoActivo, int coberturaActiva, int idPrueba) throws Exception;
                  
     int insertar(String objeto) throws Exception;
     
     String listarMediosMunicipioAsignados(String codMunicipio, int medioPagoActivo, int coberturaActiva, int idPrueba) throws Exception;
     
     void generaCoberturaPorMunicipio(int idPrueba);
     
     String listarMediosMunicipioJson(String codMunicipio, int medioPagoActivo, int coberturaActiva) throws Exception ;
}
