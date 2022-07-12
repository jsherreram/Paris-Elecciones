/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.portal.util;

/**
 *
 * @author miguel.ropero
 */
public class Normalizacion {
    
    
    public static String  normalizarDireccion(String direccion){
        
        direccion = direccion.toUpperCase();
        direccion = direccion.replaceAll("N§", "#");
        direccion = direccion.replaceAll("NO.", "#");
        direccion = direccion.replaceAll("NO", "#");
        direccion = direccion.replaceAll("CLLE", "CALLE");
        direccion = direccion.replaceAll("CLL", "CALLE");
        direccion = direccion.replaceAll("CL", "CALLE");
        direccion = direccion.replaceAll("CRA.", "CARRERA");
        direccion = direccion.replaceAll("CR", "CARRERA");        
        direccion = direccion.replaceAll("KRA", "CARRERA");
        direccion = direccion.replaceAll("KR", "CARRERA");        
        direccion = direccion.replaceAll("TV", "TRANSVERSAL");
        direccion = direccion.replaceAll("DIG,", "DIAGONAL");
        direccion = direccion.replaceAll("DG", "DIAGONAL");
        
        int indice = direccion.indexOf("AP");        
        if(indice > 0)
            direccion = direccion.substring(0, indice);
        
        indice = direccion.indexOf("BQ");        
        if(indice > 0)
            direccion = direccion.substring(0, indice);
        
        indice = direccion.indexOf("APTO");        
        if(indice > 0)
            direccion = direccion.substring(0, indice);
        
        indice = direccion.indexOf("INT");        
        if(indice > 0)
            direccion = direccion.substring(0, indice);
        
        indice = direccion.indexOf("BLOQUE");
        if(indice > 0)
            direccion = direccion.substring(0, indice);
        
        indice = direccion.indexOf("BL");
        if(indice > 0)
            direccion = direccion.substring(0, indice);
        
        indice = direccion.indexOf("BLOQ");
        if(indice > 0)
            direccion = direccion.substring(0, indice);
        
        indice = direccion.indexOf("TORRE");
        if(indice > 0)
            direccion = direccion.substring(0, indice);
        
        indice = direccion.indexOf("TRR");
        if(indice > 0)
            direccion = direccion.substring(0, indice);
        
        indice = direccion.indexOf("CASA");        
        if(indice > 0)
            direccion = direccion.substring(0, indice);
        
        indice = direccion.indexOf("CS");        
        if(indice > 0)
            direccion = direccion.substring(0, indice);
        
        indice = direccion.indexOf("ETAPA");        
        if(indice > 0)
            direccion = direccion.substring(0, indice);
        
        indice = direccion.indexOf("BARRIO");        
        if(indice > 0)
            direccion = direccion.substring(0, indice);
                
        return direccion;                
    }
    
    
}
