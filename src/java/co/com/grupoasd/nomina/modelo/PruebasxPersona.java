/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.modelo;

import java.io.Serializable;

/**
 *
 * @author Grupo ASD
 */
public class PruebasxPersona implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private String grupo;
    private Integer prueba;

    /**
     * @return the grupo
     */
    public String getGrupo() {
        return grupo;
    }

    /**
     * @param grupo the grupo to set
     */
    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    /**
     * @return the prueba
     */
    public Integer getPrueba() {
        return prueba;
    }

    /**
     * @param prueba the prueba to set
     */
    public void setPrueba(Integer prueba) {
        this.prueba = prueba;
    }
           
}
