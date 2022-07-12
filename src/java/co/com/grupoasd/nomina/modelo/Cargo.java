/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.modelo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pedro Rodríguez
 */
@XmlRootElement
public class Cargo {
    
    private String codigoCargo;
    private String descripcion;
    private String nombrecarne;
    private String codigoLogisys;
    private String funciones;
    private int viaticos;
    private int consalon;
    private int capacitacion;
    private int idorder;
    private int nivel_cargo;
    private String desnivel;
    private int esSuplente;
    private int esicfes;
    private int esgsa;
    private int polivalente;
    private int bloqueadomonitoreo;
    private int equivalente_suplencia;
    
    public Cargo() {
    }

    public int getEsgsa() {
        return esgsa;
    }

    public void setEsgsa(int esgsa) {
        this.esgsa = esgsa;
    }

    public int getPolivalente() {
        return polivalente;
    }

    public void setPolivalente(int polivalente) {
        this.polivalente = polivalente;
    }

    public int getBloqueadomonitoreo() {
        return bloqueadomonitoreo;
    }

    public void setBloqueadomonitoreo(int bloqueadomonitoreo) {
        this.bloqueadomonitoreo = bloqueadomonitoreo;
    }

    public int getEsicfes() {
        return esicfes;
    }

    public void setEsicfes(int esicfes) {
        this.esicfes = esicfes;
    }

    public int getEsSuplente() {
        return esSuplente;
    }

    public void setEsSuplente(int esSuplente) {
        this.esSuplente = esSuplente;
    }

    public String getDesnivel() {
        return desnivel;
    }

    public void setDesnivel(String desnivel) {
        this.desnivel = desnivel;
    }

    public String getFunciones() {
        return funciones;
    }

    public void setFunciones(String funciones) {
        this.funciones = funciones;
    }

    public String getCodigoLogisys() {
        return codigoLogisys;
    }

    public void setCodigoLogisys(String codigoLogisys) {
        this.codigoLogisys = codigoLogisys;
    }

    public int getIdorder() {
        return idorder;
    }

    public void setIdorder(int idorder) {
        this.idorder = idorder;
    }

    public int getNivel_cargo() {
        return nivel_cargo;
    }

    public void setNivel_cargo(int nivel_cargo) {
        this.nivel_cargo = nivel_cargo;
    }

    public int getViaticos() {
        return viaticos;
    }

    public void setViaticos(int viaticos) {
        this.viaticos = viaticos;
    }

    public int getConsalon() {
        return consalon;
    }

    public void setConsalon(int consalon) {
        this.consalon = consalon;
    }

    public int getCapacitacion() {
        return capacitacion;
    }

    public void setCapacitacion(int capacitacion) {
        this.capacitacion = capacitacion;
    }
    
    public Cargo(String codigo) {
        this.codigoCargo = codigo;
    }

    public Cargo(String codigo, String nombre) {
        this.codigoCargo = codigo;
        this.descripcion = nombre;
    }

    public String getCodigoCargo() {
        return codigoCargo;
    }

    public void setCodigoCargo(String codigo) {
        this.codigoCargo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String nombre) {
        this.descripcion = nombre;
    }

    /**
     * @return the nombrecarne
     */
    public String getNombrecarne() {
        return nombrecarne;
    }

    /**
     * @param nombrecarne the nombrecarne to set
     */
    public void setNombrecarne(String nombrecarne) {
        this.nombrecarne = nombrecarne;
    }

    /**
     * @return the equivalente_suplencia
     */
    public int getEquivalente_suplencia() {
        return equivalente_suplencia;
    }

    /**
     * @param equivalente_suplencia the equivalente_suplencia to set
     */
    public void setEquivalente_suplencia(int equivalente_suplencia) {
        this.equivalente_suplencia = equivalente_suplencia;
    }
}
