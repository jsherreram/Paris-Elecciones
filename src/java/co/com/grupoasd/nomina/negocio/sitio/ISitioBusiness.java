/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.negocio.sitio;

import co.com.grupoasd.nomina.common.model.Foto;
import java.util.List;

/**
 *
 * @author ASD
 */
public interface ISitioBusiness {

    /**
     * Metodo encargado de obtener las fotos del sitio que estan almacenadas en
     * el servidor
     *
     * @param idSitio
     * @param idPrueba
     * @param folderFotos
     * @param idEvento
     * @return
     */
    public List<Foto> obtenerFotosSitioPorPrueba(String idSitio, Integer idPrueba, String folderFotos, Integer idEvento);

    /**
     * Metodo encargado de realizar la eliminacion de la foto del repositorio
     * especifico del sitio y prueba.
     *
     * @param idPrueba
     * @param idSitio
     * @param nombreFoto
     * @param folderFoto
     * @param idEvento
     * @return
     */
    public boolean eliminarFotoSitio(Integer idPrueba, String idSitio, String nombreFoto, String folderFoto, Integer idEvento);

}
