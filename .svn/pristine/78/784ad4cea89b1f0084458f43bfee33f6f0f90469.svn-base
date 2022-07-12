/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.parametroscorreo;

import co.com.grupoasd.nomina.dao.MiscDao;
import java.util.HashMap;

/**
 *
 * @author Grupo ASD
 */
public class SingletonCorreo {
    private static SingletonCorreo instance;
    private HashMap correos;
    
    private SingletonCorreo(){
        this.setCorreos(new MiscDao().getParametrosCorreo());        
    }
    
    public static SingletonCorreo getInstance(){
        if(instance==null)
            instance = new SingletonCorreo();
        return instance;
    }

    /**
     * @return the correos
     */
    public HashMap getCorreos() {
        return correos;
    }

    /**
     * @param correos the correos to set
     */
    public void setCorreos(HashMap correos) {
        this.correos = correos;
    }
}
