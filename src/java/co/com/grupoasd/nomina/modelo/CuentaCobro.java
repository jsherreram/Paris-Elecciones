/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.modelo;

import java.util.Date;

/**
 *
 * @author Wilfer
 */
public class CuentaCobro {

public CuentaCobro() {
}

private int Id;
private int idprueba;
private int idempleado;
private String idcargo_puesto_evento;
private String medio	;
private Date fecha_generada;
private Date fecha_pago;
private String referencia_pago;
private double valor;
private	String monto_letras;
private double valor_rtfte;
private	double valor_rtica;
private	double valor_xpagar;
private	double	valor_pagado;
private	int idmedio_pago;
private int idBanco;
private String numeroCuenta;
private int idTipoCuenta;
private int pagado;
private int aceptada;
private int nro_envio;
private String observaciones;
private int id_divitrans;
private Date fecha_actualiza;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getIdprueba() {
        return idprueba;
    }

    public void setIdprueba(int idprueba) {
        this.idprueba = idprueba;
    }

    public int getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(int idempleado) {
        this.idempleado = idempleado;
    }

    public String getIdcargo_puesto_evento() {
        return idcargo_puesto_evento;
    }

    public void setIdcargo_puesto_evento(String idcargo_puesto_evento) {
        this.idcargo_puesto_evento = idcargo_puesto_evento;
    }

    public String getMedio() {
        return medio;
    }

    public void setMedio(String medio) {
        this.medio = medio;
    }

    public Date getFecha_generada() {
        return fecha_generada;
    }

    public void setFecha_generada(Date fecha_generada) {
        this.fecha_generada = fecha_generada;
    }

    public Date getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(Date fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public String getReferencia_pago() {
        return referencia_pago;
    }

    public void setReferencia_pago(String referencia_pago) {
        this.referencia_pago = referencia_pago;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getMonto_letras() {
        return monto_letras;
    }

    public void setMonto_letras(String monto_letras) {
        this.monto_letras = monto_letras;
    }

    public double getValor_rtfte() {
        return valor_rtfte;
    }

    public void setValor_rtfte(double valor_rtfte) {
        this.valor_rtfte = valor_rtfte;
    }

    public double getValor_rtica() {
        return valor_rtica;
    }

    public void setValor_rtica(double valor_rtica) {
        this.valor_rtica = valor_rtica;
    }

    public double getValor_xpagar() {
        return valor_xpagar;
    }

    public void setValor_xpagar(double valor_xpagar) {
        this.valor_xpagar = valor_xpagar;
    }

    public double getValor_pagado() {
        return valor_pagado;
    }

    public void setValor_pagado(double valor_pagado) {
        this.valor_pagado = valor_pagado;
    }

    public int getIdmedio_pago() {
        return idmedio_pago;
    }

    public void setIdmedio_pago(int idmedio_pago) {
        this.idmedio_pago = idmedio_pago;
    }

    public int getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(int idBanco) {
        this.idBanco = idBanco;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public int getIdTipoCuenta() {
        return idTipoCuenta;
    }

    public void setIdTipoCuenta(int idTipoCuenta) {
        this.idTipoCuenta = idTipoCuenta;
    }

    public int getPagado() {
        return pagado;
    }

    public void setPagado(int pagado) {
        this.pagado = pagado;
    }

    public int getAceptada() {
        return aceptada;
    }

    public void setAceptada(int aceptada) {
        this.aceptada = aceptada;
    }

    public int getNro_envio() {
        return nro_envio;
    }

    public void setNro_envio(int nro_envio) {
        this.nro_envio = nro_envio;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getId_divitrans() {
        return id_divitrans;
    }

    public void setId_divitrans(int id_divitrans) {
        this.id_divitrans = id_divitrans;
    }

    public Date getFecha_actualiza() {
        return fecha_actualiza;
    }

    public void setFecha_actualiza(Date fecha_actualiza) {
        this.fecha_actualiza = fecha_actualiza;
    }

    
    
}
