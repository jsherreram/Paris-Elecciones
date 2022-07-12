/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.modelo;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Wilfer Carvajal
 */

@XmlRootElement
public class MisPruebas {


    private int     idprueba;
    private int     idestpersona;
    private int     puedecancelar;

    private String  nombre_prueba;
    private int     idtipo_prueba;
    private String  tipo_rueba;
    private String  estadoprueba;
    private int     dias;
    private Date    fechaaplicacion;
    private String  estpersona_codigo;
    private String  nombre1;
    private String  nombre2;
    private String  apellido1;
    private String  apellido2;
    private String  tipodoc;
    private int     nrodoc;
    private int     idempleado;
    private int     fechanumero;
    private String  cargonombre;
    private String  codigocargo;
    private int     idmedio_pago;
    private String medionombre;
    private String nombres;
    private String nombredocumento;

    public int getPuedecancelar() {
        return puedecancelar;
    }

    public void setPuedecancelar(int puedecancelar) {
        this.puedecancelar = puedecancelar;
    }
    public int getIdestpersona() {
        return idestpersona;
    }

    public void setIdestpersona(int idestpersona) {
        this.idestpersona = idestpersona;
    }

    public String getNombredocumento() {
        return nombredocumento;
    }

    public void setNombredocumento(String nombredocumento) {
        this.nombredocumento = nombredocumento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    

    public int getIdmedio_pago() {
        return idmedio_pago;
    }

    public void setIdmedio_pago(int idmedio_pago) {
        this.idmedio_pago = idmedio_pago;
    }

    public String getMedionombre() {
        return medionombre;
    }

    public void setMedionombre(String medionombre) {
        this.medionombre = medionombre;
    }

    public String getCodigocargo() {
        return codigocargo;
    }

    public void setCodigocargo(String codigocargo) {
        this.codigocargo = codigocargo;
    }

    public String getCargonombre() {
        return cargonombre;
    }

    public void setCargonombre(String cargonombre) {
        this.cargonombre = cargonombre;
    }

    public int getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(int idempleado) {
        this.idempleado = idempleado;
    }

    public int getFechanumero() {
        return fechanumero;
    }

    public void setFechanumero(int fechanumero) {
        this.fechanumero = fechanumero;
    }
    
    public int getIdprueba() {
        return idprueba;
    }

    public void setIdprueba(int idprueba) {
        this.idprueba = idprueba;
    }

    public String getNombre_prueba() {
        return nombre_prueba;
    }

    public void setNombre_prueba(String nombre_prueba) {
        this.nombre_prueba = nombre_prueba;
    }

    public int getIdtipo_prueba() {
        return idtipo_prueba;
    }

    public void setIdtipo_prueba(int idtipo_prueba) {
        this.idtipo_prueba = idtipo_prueba;
    }

    public String getTipo_rueba() {
        return tipo_rueba;
    }

    public void setTipo_rueba(String tipo_rueba) {
        this.tipo_rueba = tipo_rueba;
    }

    public String getEstadoprueba() {
        return estadoprueba;
    }

    public void setEstadoprueba(String estadoprueba) {
        this.estadoprueba = estadoprueba;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public Date getFechaaplicacion() {
        return fechaaplicacion;
    }

    public void setFechaaplicacion(Date fechaaplicacion) {
        this.fechaaplicacion = fechaaplicacion;
    }

    public String getEstpersona_codigo() {
        return estpersona_codigo;
    }

    public void setEstpersona_codigo(String estpersona_codigo) {
        this.estpersona_codigo = estpersona_codigo;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getTipodoc() {
        return tipodoc;
    }

    public void setTipodoc(String tipodoc) {
        this.tipodoc = tipodoc;
    }

    public int getNrodoc() {
        return nrodoc;
    }

    public void setNrodoc(int nrodoc) {
        this.nrodoc = nrodoc;
    }

}
