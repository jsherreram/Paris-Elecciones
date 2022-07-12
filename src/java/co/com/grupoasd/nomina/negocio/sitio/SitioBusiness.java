/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.negocio.sitio;

import co.com.grupoasd.nomina.common.model.Foto;
import co.com.grupoasd.nomina.dao.SitioDao;
import co.com.grupoasd.nomina.modelo.Sitio;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASD
 */
public class SitioBusiness implements ISitioBusiness {

    /**
     * Constantes para la ubicacion del directorio de imagenes
     */
    private static final String PREFIX_LOCATION_PHOTOS = "/data/Elecciones/img/fotosSitioElecciones/";
    private static final String PREFIX_FOLDER_PRUEBA = "prueba_";
    private static final String PREFIX_FOLDER_SITIO = "/sitio_";
    private static final String PREFIX_FOLDER_EVENTO= "/evento_";

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

    public List<Foto> obtenerFotosSitioPorPrueba(String idSitio, Integer idPrueba, String folderFotos, Integer idEvento) {
        List<Foto> listaFotos = new ArrayList<>();
        String ubicacion = obtenerUbicacionFotos(idPrueba, idSitio, folderFotos, idEvento);
        File folder = new File(ubicacion);
        if (folder.exists()) {
            File[] listFiles = folder.listFiles();
            for (File listFile : listFiles) {
                Foto foto = new Foto();
                foto.setPath(PREFIX_FOLDER_PRUEBA + idPrueba + PREFIX_FOLDER_SITIO + idSitio + PREFIX_FOLDER_EVENTO+ idEvento+"/" + folderFotos);
                foto.setName(listFile.getName());
                foto.setWeight(String.valueOf(listFile.getTotalSpace()));
                listaFotos.add(foto);
            }
        }
        return listaFotos;
    }

    /**
     * Metodo encargado de realizar la eliminacion de la foto del repositorio
     * especifico del sitio y prueba.
     *
     * @param idPrueba
     * @param idSitio
     * @param nombreFoto
     * @param folderFoto
     * @return
     */
    
    public boolean eliminarFotoSitio(Integer idPrueba, String idSitio, String nombreFoto, String folderFoto, Integer idEvento) {
        String ubicacion = obtenerUbicacionFotos(idPrueba, idSitio, folderFoto, idEvento);
        File folder = new File(ubicacion);
        if (folder.exists()) {
            File image = new File(ubicacion, nombreFoto);
            return image.delete();
        }
        return true;
    }

    /**
     * Metodo para obtener el directorio de las fotos
     *
     * @param idPrueba
     * @param idSitio
     * @return
     */
    private String obtenerUbicacionFotos(Integer idPrueba, String idSitio, String subFolderFotos, Integer idEvento) {
        StringBuilder builder = new StringBuilder(PREFIX_LOCATION_PHOTOS);
        builder.append(PREFIX_FOLDER_PRUEBA);
        builder.append(idPrueba);
        builder.append(PREFIX_FOLDER_SITIO);
        builder.append(idSitio);
        builder.append(PREFIX_FOLDER_EVENTO);
        builder.append(idEvento);
        builder.append("/");
        builder.append(subFolderFotos);
        return builder.toString();
    }

    /**
     * Metodo que realiza el llamado al Dao para consultar los PDS segun los
     * sitios asignados para el usuario
     *
     * @param idPrueba
     * @return
     */
    public List<Sitio> getPDSByPrueba(int idPrueba) {
        SitioDao sitioDao = new SitioDao();
        return sitioDao.getPDSByPrueba(idPrueba);
    }

    /**
     * Metodo encargado de invocar al Dao para consultar el Sitio por el id
     * ingresado
     *
     * @param idDivipol
     * @return
     */
    public Sitio obtenerSitioPorId(int idDivipol) {
        SitioDao sitioDao = new SitioDao();
        return sitioDao.BuscarSitioPorId(idDivipol);
    }


    public List<Foto> obtenerFotosSitioPorPrueba(String idSitio, Integer idPrueba, String folderFotos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
