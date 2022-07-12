/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.grupoasd.nomina.modelo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ASD
 */
@XmlRootElement
public class CoberturaMedioPago {
    
    int id;
    int idmedio_pago;
    int idPrueba;
    String codigoMunicipio;
    boolean activo;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdmedio_pago() {
        return idmedio_pago;
    }

    public void setIdmedio_pago(int idmedio_pago) {
        this.idmedio_pago = idmedio_pago;
    }

    public int getIdPrueba() {
        return idPrueba;
    }

    public void setIdPrueba(int idPrueba) {
        this.idPrueba = idPrueba;
    }

    public String getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(String codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }


    
    
}
