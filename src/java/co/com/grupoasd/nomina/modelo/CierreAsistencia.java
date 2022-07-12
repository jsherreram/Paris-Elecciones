/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.modelo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Iván Vargas
 */
@XmlRootElement
public class CierreAsistencia {

    private int idprueba;
    private int idDivipol;
    private int codigoEvento;

    public int getIdprueba() {
        return idprueba;
    }

    public void setIdprueba(int idprueba) {
        this.idprueba = idprueba;
    }

    public int getIdDivipol() {
        return idDivipol;
    }

    public void setIdDivipol(int idDivipol) {
        this.idDivipol = idDivipol;
    }

    public int getCodigoEvento() {
        return codigoEvento;
    }

    public void setCodigoEvento(int codigoEvento) {
        this.codigoEvento = codigoEvento;
    }

}
