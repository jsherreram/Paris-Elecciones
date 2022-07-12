/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.modelo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Wilfer
 */

@XmlRootElement
public class MisViaticos {
    
private String nombrecargo;
private String nombres;
private String nombreprueba;
private int nrodoc;
private String documento;
private int vr_municipal;
private int vr_rural;
private int vr_interno;
private int total;
private double ndias;
private int pernocta;
private int total_viaticos;
private int total_pagarviatico;
private String estadopago;

    public String getNombreprueba() {
        return nombreprueba;
    }

    public String getEstadopago() {
        return estadopago;
    }

    public void setEstadopago(String estadopago) {
        this.estadopago = estadopago;
    }

    public void setNombreprueba(String nombreprueba) {
        this.nombreprueba = nombreprueba;
    }

    public String getNombrecargo() {
        return nombrecargo;
    }

    public void setNombrecargo(String nombrecargo) {
        this.nombrecargo = nombrecargo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public int getNrodoc() {
        return nrodoc;
    }

    public void setNrodoc(int nrodoc) {
        this.nrodoc = nrodoc;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public int getVr_municipal() {
        return vr_municipal;
    }

    public void setVr_municipal(int vr_municipal) {
        this.vr_municipal = vr_municipal;
    }

    public int getVr_rural() {
        return vr_rural;
    }

    public void setVr_rural(int vr_rural) {
        this.vr_rural = vr_rural;
    }

    public int getVr_interno() {
        return vr_interno;
    }

    public void setVr_interno(int vr_interno) {
        this.vr_interno = vr_interno;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public double getNdias() {
        return ndias;
    }

    public void setNdias(double ndias) {
        this.ndias = ndias;
    }

    public int getPernocta() {
        return pernocta;
    }

    public void setPernocta(int pernocta) {
        this.pernocta = pernocta;
    }

    public int getTotal_viaticos() {
        return total_viaticos;
    }

    public void setTotal_viaticos(int total_viaticos) {
        this.total_viaticos = total_viaticos;
    }

    public int getTotal_pagarviatico() {
        return total_pagarviatico;
    }

    public void setTotal_pagarviatico(int total_pagarviatico) {
        this.total_pagarviatico = total_pagarviatico;
    }

}
