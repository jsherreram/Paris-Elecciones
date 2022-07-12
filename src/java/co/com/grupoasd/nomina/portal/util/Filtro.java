/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.portal.util;

import java.io.File;
import java.io.FilenameFilter;


/**
 *
 * @author ASD
 */
public class Filtro implements FilenameFilter {

    String extension;

   public Filtro(String extension) {
        this.extension = extension;
    }

    //implementación del método accept del interface
    @Override
    public boolean accept(File ruta, String nombre) {
//        return nombre.endsWith(extension);
          return nombre.startsWith(extension);
         
    }

}
