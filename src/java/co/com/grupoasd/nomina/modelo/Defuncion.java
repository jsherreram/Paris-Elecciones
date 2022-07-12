/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.modelo;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Pedro Rodriguez
 */
@XmlRootElement
public class Defuncion {

    private String nrodoc;
    private String tipodoc;
    private String apellido1;
    private String apellido2;
    private String nombre1;
    private String nombre2;
    
    private String dia;
    private String mes;
    private String ano;
    private String libro;
    private String tomo;
    private String folio;
    private String serialid;
    private String depto;
    private String mpio;
    private String oficina;
    private String sexom;
    private String sexof;
    private String nombreArchivo;
    private String linea;    
    
    /**
     *
     */
    public Defuncion() {

    }

    /**
     * @return the nrodoc
     */
    public String getNrodoc() {
        return nrodoc;
    }

    /**
     * @param nrodoc the nrodoc to set
     */
    public void setNrodoc(String nrodoc) {
        this.nrodoc = nrodoc;
    }

    /**
     * @return the tipodoc
     */
    public String getTipodoc() {
        return tipodoc;
    }

    /**
     * @param tipodoc the tipodoc to set
     */
    public void setTipodoc(String tipodoc) {
        this.tipodoc = tipodoc;
    }

    /**
     * @return the apellido1
     */
    public String getApellido1() {
        return apellido1;
    }

    /**
     * @param apellido1 the apellido1 to set
     */
    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    /**
     * @return the apellido2
     */
    public String getApellido2() {
        return apellido2;
    }

    /**
     * @param apellido2 the apellido2 to set
     */
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    /**
     * @return the nombre1
     */
    public String getNombre1() {
        return nombre1;
    }

    /**
     * @param nombre1 the nombre1 to set
     */
    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    /**
     * @return the nombre2
     */
    public String getNombre2() {
        return nombre2;
    }

    /**
     * @param nombre2 the nombre2 to set
     */
    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    /**
     * @return the dia
     */
    public String getDia() {
        return dia;
    }

    /**
     * @param dia the dia to set
     */
    public void setDia(String dia) {
        this.dia = dia;
    }

    /**
     * @return the mes
     */
    public String getMes() {
        return mes;
    }

    /**
     * @param mes the mes to set
     */
    public void setMes(String mes) {
        this.mes = mes;
    }

    /**
     * @return the ano
     */
    public String getAno() {
        return ano;
    }

    /**
     * @param ano the ano to set
     */
    public void setAno(String ano) {
        this.ano = ano;
    }

    /**
     * @return the libro
     */
    public String getLibro() {
        return libro;
    }

    /**
     * @param libro the libro to set
     */
    public void setLibro(String libro) {
        this.libro = libro;
    }

    /**
     * @return the tomo
     */
    public String getTomo() {
        return tomo;
    }

    /**
     * @param tomo the tomo to set
     */
    public void setTomo(String tomo) {
        this.tomo = tomo;
    }

    /**
     * @return the folio
     */
    public String getFolio() {
        return folio;
    }

    /**
     * @param folio the folio to set
     */
    public void setFolio(String folio) {
        this.folio = folio;
    }

    /**
     * @return the serialid
     */
    public String getSerialid() {
        return serialid;
    }

    /**
     * @param serialid the serialid to set
     */
    public void setSerialid(String serialid) {
        this.serialid = serialid;
    }

    /**
     * @return the depto
     */
    public String getDepto() {
        return depto;
    }

    /**
     * @param depto the depto to set
     */
    public void setDepto(String depto) {
        this.depto = depto;
    }

    /**
     * @return the mpio
     */
    public String getMpio() {
        return mpio;
    }

    /**
     * @param mpio the mpio to set
     */
    public void setMpio(String mpio) {
        this.mpio = mpio;
    }

    /**
     * @return the oficina
     */
    public String getOficina() {
        return oficina;
    }

    /**
     * @param oficina the oficina to set
     */
    public void setOficina(String oficina) {
        this.oficina = oficina;
    }

    /**
     * @return the sexom
     */
    public String getSexom() {
        return sexom;
    }

    /**
     * @param sexom the sexom to set
     */
    public void setSexom(String sexom) {
        this.sexom = sexom;
    }

    /**
     * @return the sexof
     */
    public String getSexof() {
        return sexof;
    }

    /**
     * @param sexof the sexof to set
     */
    public void setSexof(String sexof) {
        this.sexof = sexof;
    }

    /**
     * @return the nombreArchivo
     */
    public String getNombreArchivo() {
        return nombreArchivo;
    }

    /**
     * @param nombreArchivo the nombreArchivo to set
     */
    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    /**
     * @return the linea
     */
    public String getLinea() {
        return linea;
    }

    /**
     * @param linea the linea to set
     */
    public void setLinea(String linea) {
        this.linea = linea;
    }

}
